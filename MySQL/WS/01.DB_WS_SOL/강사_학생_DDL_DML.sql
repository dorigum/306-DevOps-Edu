-- 데이터베이스 생성
create database ex0227;

-- 데이터 베이스 접속
use ex0227;

-- 과목테이블
create table subject(
	수강코드 varchar(5)  primary key,
	과목 varchar(20) not null
);

-- 강의실 테이블
create table room(
	강의실번호 int  primary key ,
	수용인원 int not null
);

desc room;

-- 강사 테이블
create table teacher(
	강사번호 int  primary key,
	강사이름 varchar(12) not null,
	주민등록번호 varchar(14) not null,
	연락처 varchar(14) not null,
	주소 varchar(50),
	이메일 varchar(20),
	수강코드 varchar(5) not null ,
    foreign key(수강코드) references subject(수강코드)
);
select * from teacher;


-- 수강생 테이블
create table student(
	수강생번호 int  primary key,
	수_이름 varchar(12) not null,
	수_주민등록번호 varchar(14)  not null,
	수_연락처 varchar(14) not null,
	수_주소 varchar(50),
	수_이메일 varchar(20)
);

-- 수강 테이블
create table sugang(
	수강생번호 int  primary KEY  ,
	수강코드  varchar(5) ,
     foreign key(수강생번호) references student(수강생번호),
     foreign key(수강코드)  references subject(수강코드)
);


-- 수강과목
create table sugangTB(
	수강코드 varchar(5) primary KEY   ,
	강의실번호  int ,
	시간 int not null,
	강사번호 int   ,
	foreign key(수강코드) references subject(수강코드),
   foreign key(강의실번호) references Room(강의실번호) , 
   foreign key(강사번호)  references teacher(강사번호)
);

desc teacher;
desc sugangTB;

select *from subject;

/*insert into subject values('j1001','sql');
insert into subject values('j1002','Win2000');
insert into subject values('j1003','ASP');
insert into subject values('j1004','Php');
insert into subject values('j1005','java');
insert into subject values('j1006','javascript');*/

insert into subject 
values('j1001','sql'),('j1002','Win2000'),
('j1003','ASP'),('j1004','Php')
,('j1005','java'),('j1006','javascript');


insert into room values(901,34);
insert into room values(902,35);
insert into room values(903,25);
insert into room values(904,32);
insert into room values(905,30);
insert into room values(906,35);

insert INTO teacher values(01,'홍길동','700918-1622011','031-295-1234','성남시 중원구 신흥동','kkk@jok.or.kr','j1001');
insert INTO teacher values(02,'유관순','710419-2018916','02-345-2345','서울 강남구 개포동','kkk@jok.or.kr','j1002');
insert INTO teacher values(03,'이승복','730112-1344911','031-1234-6789','서울시 강남구 논현동','sss@jok.or.kr','j1003');
insert INTO teacher values(04,'모택동','730301-1167623','031-567-5678','서울시 서초구 서초동','mmm@jok.or.kr','j1004');
insert INTO teacher values(05,'주롱지','730322-2037921','031-789-3456','서울시 강서구 가양동','xxx@jok.or.kr','j1005');
insert INTO teacher values(06,'이순신','731122-1646213','031-234-8901','서울시 강북구 미아동','ppp@jok.or.kr','j1006');


insert INTO student values(01,'김현진','771212-2346111','017-888-8888','부산','aaa@hanmail.net');
insert INTO student values(02,'김석주','720112-1234812','016-999-9999','경기','bbb@hanmail.net');
insert INTO student values(03,'고훈기','730102-1555555','017-555-5555','서울','ccc@hanmail.net');
insert INTO student values(04,'유민경','801111-2222222','017-222-2222','전남','ddd@hanmail.net');
insert INTO student values(05,'김영수','811231-1777777','017-777-7777','충주','eee@hanmail.net');
insert INTO student values(06,'박상원','790915-1333333','017-333-3333','강원','fff@hanmail.net');


insert into sugang values(1,'j1001');
insert into sugang values(2,'j1002');
insert into sugang values(3,'j1003');
insert into sugang values(4,'j1004');
insert into sugang values(5,'j1005');
insert into sugang values(6,'j1006');


insert into sugangtb values('j1001',901,2,01);
insert into sugangtb values('j1002',902,2,02);
insert into sugangtb values('j1003',903,2,03);
insert into sugangtb values('j1004',904,2,04);
insert into sugangtb values('j1005',905,2,05);
insert into sugangtb values('j1006',906,2,06);



select *from  subject;
select *from  room;
select *from  teacher;
select *from  student;
select *from  sugang;
select *from  sugangtb;






