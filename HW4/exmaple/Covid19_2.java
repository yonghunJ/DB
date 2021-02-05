
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.WordUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;


public class MyWordCount {
	
	// 4 types declared: Type of input key, type of input value, type of output key, type of output value
	public static class MyMapper extends Mapper<Object, Text, Text, LongWritable> {
		private final static LongWritable one = new LongWritable(1);
		private Text word = new Text();
		
		// The 4 types declared here should match the types that was declared on the top
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			
			Configuration conf = context.getConfiguration();
			String startDate = conf.get("startDate");
			String endDate = conf.get("endDate");

			String line = value.toString();
			String[] arrLine = line.split(",");

			try{
				if( startDate ==null || endDate == null){
					throw IllegalArgumentException("wrong input format")
				}
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			
			dateValidateCheck(startDate,endDate);


			//NumberFormatException
			// throw new IllegalArgumentException("Wrong Input Argument");
			LongnewCase = Long.parseLong(arrLine[2].trim());

			word.set(arrLine[1]);
			newCase = new LongWritable(LongnewCase);


			context.write(word, newCase);

		}

		public void dateValidateCheck(String startDate,String endDate){
			startDate = startDate.trim();
			endDate = endDate.trim();

			String[] start = startDate.split("-");
			String[] end = endDate.split("-");
			Date date1;
			Date date2;

			try{
				if(start.length != 3 || end.length !=3){
					throw IllegalArgumentException("Wrong Input Date")
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				date1 = sdf.parse(startDate);
				date2 = sdf.parse(endDate);	
				if (date2.compareTo(date1) >= 0) {
				// return true;
				} else if (date1.compareTo(date2) < 0) {
					throw IllegalArgumentException("Wrong Input Date")
				}
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			




			

		}
		
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
		conf.set("startDate",args[1]);
		conf.set("endDate",args[2]);
		Job myjob = Job.getInstance(conf, "my word count test");
		myjob.setJarByClass(MyWordCount.class);
		myjob.setMapperClass(MyMapper.class);
		myjob.setReducerClass(MyReducer.class);
		myjob.setOutputKeyClass(Text.class);
		myjob.setOutputValueClass(LongWritable.class);
		// Uncomment to set the number of reduce tasks
		// myjob.setNumReduceTasks(2);
		FileInputFormat.addInputPath(myjob, new Path(args[0]));
		FileOutputFormat.setOutputPath(myjob,  new Path(args[3]));
		System.exit(myjob.waitForCompletion(true) ? 0 : 1);
	}
}
