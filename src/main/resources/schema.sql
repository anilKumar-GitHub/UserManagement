DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
	ID INTEGER,
	FIRST_NAME VARCHAR(120),
	LAST_NAME VARCHAR(120),
	MOBILE_NUM VARCHAR(50),
	CITY VARCHAR(255),
	PRIMARY KEY(ID)
);


CREATE SEQUENCE IF NOT EXISTS user_table_seq START WITH 1000;
