
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class Covid19_1 {
	
	// 4 types declared: Type of input key, type of input value, type of output key, type of output value
	public static class MyMapper extends Mapper<Object, Text, Text, LongWritable> {
		private LongWritable newCase = new LongWritable(1);
		private Text word = new Text();
		private Long LongnewCase;
		// The 4 types declared here should match the types that was declared on the top
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

			Configuration conf = context.getConfiguration();
			String line = value.toString();
			String[] arrLine = line.split(",");

			HashMap<String, Long> map =setup(context);

			try{
				if (Long.parseLong(arrLine[2].trim()) < 0 ){  return; } //minus newcase filtering
			}catch (NumberFormatException e) { //header filtering
				return ;
			}


			// if (conf.get("world").equals("false")){
			// 	if(arrLine[1].trim().equals("World")){
			// 		return ;
			// 	}
			// }else if(conf.get("world").equals("true")){

			// }else{
			// 	throw new IllegalArgumentException("Wrong Input Argument");
			// }

			

			LongnewCase = Long.parseLong(arrLine[2].trim());

			word.set(arrLine[1]);
			newCase = new LongWritable(LongnewCase);


			context.write(word, newCase);

		}
		
	}
	public void setup(Context context){
	
		URI[] files = context.getCacheFiles();
		HashMap<String, Long> map = new HashMap<>(); 
		if(uris==null||uris.length==0){
				if(throwExceptionIfNotFound)
					throw new RuntimeException("Unable to find file in distributed cache");
				return null;
			}

		for (URI file : files){

			try {
				if(FilenameUtils.getBaseName(url.getPath()).equlas("populations")){ //File name check
					URLConnection data = file.openConnection();
					Scanner input = new Scanner(data.getInputStream());
					// if (input.hasNext()) // remove
					// 	input.nextLine();  //remove

					while (input.hasNextLine()) {									//Make hash 
						String line = input.nextLine();
						String[] arrLine = line.split(",");

						if(arrLine[1].equals("location")){
							continue;
						}

						map.put(arrLine[1],Long.parseLong(arrLine[4]))

					}
					break;
				}
				
			}
			catch (Exception e) {
				System.out.print(e);
			}
			// read population.csv file data   
			

		}
		return map;

}

	
	

	// 4 types declared: Type of input key, type of input value, type of output key, type of output value
	// The input types of reduce should match the output type of map
	public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
		private LongWritable total = new LongWritable();
		
		// Notice the that 2nd argument: type of the input value is an Iterable collection of objects 
		//  with the same type declared above/as the type of output value from map
		public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
			long sum = 0;
			for (LongWritable tmp: values) {
				sum += tmp.get();
			}
			total.set(sum);
			// This write to the final output
			context.write(key, total);
		}
	}
	
	
	public static void main(String[] args)  throws Exception {
		Configuration conf = new Configuration();
		// conf.set("world",args[1]);
		Job myjob = Job.getInstance(conf, "my word count test");
		myjob.addCacheFile(new Path("hdfs://localhost:9000/populations.csv").toUri());

		myjob.setJarByClass(Covid19_1.class);
		myjob.setMapperClass(MyMapper.class);
		myjob.setReducerClass(MyReducer.class);
		myjob.setOutputKeyClass(Text.class);
		myjob.setOutputValueClass(LongWritable.class);
		// Uncomment to set the number of reduce tasks
		// myjob.setNumReduceTasks(2);
		FileInputFormat.addInputPath(myjob, new Path(args[0]));
		FileOutputFormat.setOutputPath(myjob,  new Path(args[2]));
		System.exit(myjob.waitForCompletion(true) ? 0 : 1);
	}
}
