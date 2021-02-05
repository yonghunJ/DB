SELECT RANK() OVER (ORDER BY tb1.MME / tb2.zpop_sum desc) as ranking, 
		tb2.zipcode, 
		tb1.MME, 
		tb2.zpop_sum, 
		tb1.MME / tb2.zpop_sum AS measure
from 
	( SELECT BUYER_ZIP, SUM(MME) AS MME
		FROM cse532.DEA_NY
		GROUP BY BUYER_ZIP 
	)as tb1, 
	 ( SELECT zipcode, SUM(zpop) AS zpop_sum
		FROM 
			(SELECT ZPOP,
				CASE 
				    WHEN (  LENGTH(zip) = 3 ) 
				         THEN '00'  || CAST( zip AS VARCHAR(10))
				    WHEN (  CAST( LENGTH(zip) AS VARCHAR(10)) = 4 ) 
				         THEN '0'  || CAST( zip AS VARCHAR(10)) 
				    ELSE CAST( zip AS VARCHAR(10))
				END AS zipcode
			FROM cse532.ZIPPOP)
		
		WHERE zpop >0
		GROUP BY zipcode
	)as tb2
WHERE tb1.BUYER_ZIP= tb2.zipcode 
LIMIT 5;