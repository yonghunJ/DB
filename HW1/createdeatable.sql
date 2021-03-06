--DROP TABLE cse532.dea_ny;

CREATE TABLE cse532.dea_ny(
REPORTER_DEA_NO VARCHAR(100),
REPORTER_BUS_ACT VARCHAR(50),
REPORTER_NAME VARCHAR(100),
REPORTER_ADDL_CO_INFO VARCHAR(100),
REPORTER_ADDRESS1 VARCHAR(100),
REPORTER_ADDRESS2 VARCHAR(100),
REPORTER_CITY VARCHAR(100),
REPORTER_STATE VARCHAR(100),
REPORTER_ZIP INTEGER,
REPORTER_COUNTY VARCHAR(100),
BUYER_DEA_NO VARCHAR(100),
BUYER_BUS_ACT VARCHAR(100),
BUYER_NAME VARCHAR(100),
BUYER_ADDL_CO_INFO VARCHAR(100),
BUYER_ADDRESS1 VARCHAR(100),
BUYER_ADDRESS2 VARCHAR(100),
BUYER_CITY VARCHAR(100),
BUYER_STATE VARCHAR(100),
BUYER_ZIP VARCHAR(100),
BUYER_COUNTY VARCHAR(100),
TRANSACTION_CODE VARCHAR(100),
DRUG_CODE INTEGER,
NDC_NO VARCHAR(100),
DRUG_NAME VARCHAR(100),
QUANTITY DOUBLE,
UNIT CHAR(1),
ACTION_INDICATOR CHAR(1),
ORDER_FORM_NO VARCHAR(100),
CORRECTION_NO  VARCHAR(100),
STRENGTH VARCHAR(100),
TRANSACTION_DATE DATE,
CALC_BASE_WT_IN_GM DOUBLE,
DOSAGE_UNIT DOUBLE,
TRANSACTION_ID INTEGER,
Product_Name VARCHAR(100),
Ingredient_Name VARCHAR(100),
Measure VARCHAR(100),
MME_Conversion_Factor  VARCHAR(100),
Combined_Labeler_Name VARCHAR(100),
Reporter_family VARCHAR(100),
dos_str DOUBLE,
MME DOUBLE
);