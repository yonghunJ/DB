--DROP INDEX zip;
--DROP  INDEX buyer_zip; 

CREATE INDEX zip on  cse532.ZIPPOP(zip); 
CREATE INDEX buyer_zip on  cse532.DEA_NY(buyer_zip) 
