Writer: Yonghun Jeong
SBUID : 111802100
EMAIL : yonghun.jeong@stonybrook.edu

CSE 532 HOMEWORK 1

1a. Create a DEA New York database table with following information (column descriptions) and put the 
    SQL into a file createdeatable.sql
   - Everything works as expected
   - Attached 1. createdeatable.sql

1b. Load the csv file into the database by modifying the loading script.
   - Everything works as expected
   - Attached 1. load.sql

1c. Based on queries in 3, create proper indexes to make the queries more efficient (createdeaindexes.sql)
   - Everything works as expected
   - Attached 1. createdeaindexes.sql

2a. Create a table CSE532.ZIPPOP (ZIP, COUNTY,  GEOID, ZPOP) on populations of zip codes in NY
   - Everything works as expected
   - Attached 1. createzip.sql

2b. Create a loading script to load the csv file to the database (zipload.sql). 
   - Everything works as expected
   - In case of load function, there is no option for exclude header row, so I added one more sql for deleting header in the zipload.sql file.
   - Attached 1. zipload.sql

3a. Return monthly counts of pills and smooth counts of pills with a two-month window (preceding 
    one month, following one month) (q3a.sql). DOSAGE_UNIT is the total number of pills in a transaction. 
    Draw the two curves with Excel or other drawing tools to show the difference (q3a.jpg).
   - Everything works as expected
   - Attached 1. q3a.sql, 
              2. q3a.jpg(Draw the two curves with Excel or other drawing tools to show the difference)
              3. q3a_result.jpg(Capture for the result) 
              4. q3a_result.txt(text file for the result)

3b. Find the top five zip codes with most pills sold in terms of MME when normalized by the 
    population in the zip codes, i.e., zip codes with most sold total MME of pills per person (q3b.sql). 
   - Everything works as expected
   - USED RANK() function for EXTRA 1 point credit
   - Attached 1. q3b.sql, 
              2. q3b_result.jpg(Capture for the result) 
              3. q3a_result.txt(text file for the result)
