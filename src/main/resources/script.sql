Create table customer (
                          Cid NUMBER(10) NOT NULL ,
                          Cname varchar2(45) DEFAULT NULL,
                          Cnum NUMBER(10) NOT NULL,
                          balance varchar2(45) DEFAULT NULL);

ALTER TABLE customer ADD (
    CONSTRAINT cust_pk PRIMARY KEY (Cid));

CREATE SEQUENCE cust_seq START WITH 1;


CREATE OR REPLACE TRIGGER cust_bir
    BEFORE INSERT ON customer
    FOR EACH ROW

BEGIN
    SELECT cust_seq.NEXTVAL
    INTO   :new.Cid
    FROM   dual;
END;
