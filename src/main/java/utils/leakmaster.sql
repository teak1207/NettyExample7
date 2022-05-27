

#CREATE DATABASE  LEAKMASTER_DB

SHOW DATABASES ;

USE leakmaster_db;


CREATE TABLE  LEAKMASTER_INIT_INFOS (
                                        ID INT PRIMARY KEY  AUTO_INCREMENT,
                                        NAME VARCHAR(32) NOT NULL,
                                        SID VARCHAR(32) NOT NULL,
                                        SN VARCHAR(32) NOT NULL,
                                        SIZE INT NOT NULL,
                                        PHONENUM VARCHAR(32) NOT NULL,
                                        REGDATE DATE NOT NULL
);

SELECT * FROM LEAKMASTER_INIT_INFOS;


INSERT INTO  LEAKMASTER_INIT_INFOS VALUES  (3,'NAME','SID','SN',123,'PHONENUM',CURRENT_TIMESTAMP() );