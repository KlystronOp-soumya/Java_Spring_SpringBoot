select * from batch_job_execution_params;
select * from batch_job_execution ;
select * from batch_step_execution ;

CREATE TABLE AGENT
(
	ID int PRIMARY KEY AUTO_INCREMENT,
    AGT_ID varchar(10) not null,
    NAME VARCHAR(30) NOT NULL DEFAULT '',
    DESIGNATION VARCHAR(35) NOT NULL DEFAULT '',
    LOB CHAR(2) NOT NULL default '',
    BONUS double NOT NULL DEFAULT 0.00
);

CREATE TABLE AGENT_BONUS_QUALIF
(
 ID INT PRIMARY KEY auto_increment,
 AGT_ID varchar(10) NOT NULL,
 NAME VARCHAR(30) NOT NULL DEFAULT '',
 DESIGNATION VARCHAR(35) NOT NULL DEFAULT '',
	LOB CHAR(2) NOT NULL default '',
 BONUS double NOT NULL DEFAULT 0.00,
 bonus_pct double not null default 0.00,
 bonus_payout double not null default 0.00,
 calDay int,
 calMonth int,
 calYear int,
 qualif_status int
 );
 
ALTER table AGENT
ADD constraint AGENT_UK
unique (AGT_ID) ; 

ALTER TABLE AGENT_BONUS_QUALIF
ADD CONSTRAINT AGENT_BONUS_QUALIF_FK
FOREIGN KEY ( AGT_ID ) REFERENCES AGENT( AGT_ID );
 
 INSERT INTO AGENT(AGT_ID,NAME,DESIGNATION,LOB,BONUS) VALUES ('12345','ABCD','DEV','10',1500);
 INSERT INTO AGENT(AGT_ID,NAME,DESIGNATION,LOB,BONUS) VALUES ('10056','EFGH','DEV JR','10',1000);
 INSERT INTO AGENT(AGT_ID,NAME,DESIGNATION,LOB,BONUS) VALUES ('38846','IJK','MANG','10',2000);
 INSERT INTO AGENT(AGT_ID,NAME,DESIGNATION,LOB,BONUS) VALUES ('60552','ABCD','MANG','10',2200);
 
 SELECT * FROM AGENT;
 select * from agent_bonus_qualif ;
delete from agent_bonus_qualif ;
delete from agent ;

create table psratedet
(
rateId int not null primary key auto_increment,
coll_off char(3) not null,
agt_no varchar(9) not null,
pol_no varchar(10) not null,
issue_date date not null
) ;

create table vudhhhc
(
lapseId int not null primary key auto_increment,
coll_off char(3) not null,
agt_no varchar(9) not null,
pol_no varchar(10) not null,
actual_issue_date date not null,
lapsed_status char(2) not null default 'no'
) ;

/* alter table vudhhhc add column agt_no varchar(9) not null after coll_off ; */
alter table psratedet add column split decimal(6,2) not null default 1.0000 ;
select * from psratedet ;
delete from psratedet ;
select * from vudhhhc ;
insert into psratedet (coll_off,agt_no,pol_no,issue_date) values ('227', '11Z06203', 'P1001', '2019-01-01');
insert into psratedet (coll_off,agt_no,pol_no,issue_date) values ('227', '11Z06203', 'P1002', '2019-01-31');
insert into psratedet (coll_off,agt_no,pol_no,issue_date) values ('227', '11Z06203', 'P1003', '2020-04-25');
insert into psratedet (coll_off,agt_no,pol_no,issue_date) values ('227', '11Z06204', 'P1001', '2019-08-22');
insert into psratedet (coll_off,agt_no,pol_no,issue_date) values ('227', '11Z06204', 'P1003',  '2019-01-01');
insert into psratedet (coll_off,agt_no,pol_no,issue_date) values ('227', '11Z06205', 'P1002', '2019-03-22');
insert into psratedet (coll_off,agt_no,pol_no,issue_date) values ('227', '11Z06209', 'P1003', '2019-12-15');

insert into vudhhhc (coll_off,agt_no,pol_no,actual_issue_date) values ('227', '11Z06203', 'P1001', '2019-01-01');
insert into vudhhhc (coll_off,agt_no,pol_no,actual_issue_date,lapsed_status) values ('227', '11Z06203', 'P1002', '2018-01-01','ys');
insert into vudhhhc (coll_off,agt_no,pol_no,actual_issue_date,lapsed_status) values ('227', '11Z06203', 'P1003', '2020-01-25','ys');
insert into vudhhhc (coll_off,agt_no,pol_no,actual_issue_date) values ('227', '11Z06204', 'P1001', '2019-08-22');
insert into vudhhhc (coll_off,agt_no,pol_no,actual_issue_date) values ('227', '11Z06204', 'P1003',  '2019-01-01');
insert into vudhhhc (coll_off,agt_no,pol_no,actual_issue_date) values ('227', '11Z06205', 'P1002', '2019-03-22');
insert into vudhhhc (coll_off,agt_no,pol_no,actual_issue_date) values ('227', '11Z06209', 'P1003', '2019-12-15');


select pdt.coll_off,pdt.agt_no,pdt.pol_no,vc1.lapsed_status,
case when pdt.pol_no in (
select  vc2.actual_issue_date from vudhhhc vc2 where vc2.agt_no = '11Z06203' and vc2.lapsed_status <> 'no' group by coll_off,agt_no,pol_no ) 
then pdt.issue_date else pdt.issue_date end 
from psratedet pdt,vudhhhc vc1
where pdt.agt_no='11Z06203' ;

select case when vc.lapsed_status <> 'no' and vc.pol_no = pdt.pol_no then vc.actual_issue_date else pdt.issue_date end as pol_iss_dt
from psratedet pdt 
left join vudhhhc vc  on pdt.coll_off = vc.coll_off and pdt.agt_no = vc.agt_no and pdt.pol_no = vc.pol_no 
where pdt.agt_no = '11Z06203';

select coalesce(sum(pdt.split),0), pdt.coll_off,pdt.pol_no,pdt.agt_no,
case pdt.issue_date when  vc.lapsed_status<>'no' then vc.actual_issue_date else pdt.issue_date end filtered_issue_date
from psratedet pdt , vudhhhc vc
where pdt.coll_off = vc.coll_off
and pdt.pol_no = vc.pol_no
and pdt.agt_no = vc.agt_no
and pdt.agt_no = '11Z06203'
;


