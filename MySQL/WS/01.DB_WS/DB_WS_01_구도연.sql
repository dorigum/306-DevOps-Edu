create database ex0227;

use ex0227;

-- 과목 테이블
create table Subject (
	Sub_code varchar(5) primary key, -- 수강 코드
    Sub_name varchar(20) not null
    );
    
-- 강의실 테이블
CREATE TABLE Room (
    Room_no INT PRIMARY KEY,
    Room_cnt INT NOT NULL
);

-- 강사 테이블
create table Teacher (
	tc_no int primary key,
    tc_name varchar(12) not null,
    rrn varchar(14) not null,
    phone varchar(14),
    addr varchar(50),
    email varchar(20),
    Sub_code varchar(5) not null,
    foreign key (Sub_code) references Subject(Sub_code)
);

-- drop table Teachar;

-- 학생 테이블
create table Student (
	stu_no int primary key,
    stu_name varchar(12) not null,
    stu_rrn varchar(14) not null,
    stu_phone varchar(14) not null,
    stu_addr varchar(50),
    stu_email varchar(20)
);

-- 수강 테이블
create table Sugang (
	stu_no int primary key,
    foreign key (stu_no) references Student(stu_no),
    Sub_code varchar(5),
    foreign key (Sub_code) references Subject(Sub_code)
);

-- 수강 과목 테이블
create table SugangTB (
	Sub_code varchar(5) primary key,
    foreign key (Sub_code) references Subject(Sub_code),
    Room_no int,
    foreign key (Room_no) references Room(Room_no),
    sub_time int not null,
    tc_no int,
	foreign key (tc_no) references Teacher(tc_no)
);




-- 과목 테이블 데이터 추가
insert into Subject values('j1001','SQL');
insert into Subject values('j1002','Win2000');
insert into Subject values('j1003','ASP');
insert into Subject values('j1004','PHP');
insert into Subject values('j1005','Java');
insert into Subject values('j1006','JavaScript');

select * from Subject;
-- delete from Subject
-- where Sub_code like 'j0%';

-- 강의실 테이블 데이터 추가
insert into Room values (901, 34);
insert into Room values (902, 35);
insert into Room values (903, 25);
insert into Room values (904, 32);
insert into Room values (905, 30);
insert into Room values (906, 35);

select * from Room;

-- 강사 테이블 데이터 추가
insert into Teacher values(1, '홍길동', '700918-1622011', '031-295-1234', '성남시 중원구 신흥동', 'kkk@jok.or.kr', 'j1001');
insert into Teacher values(2, '유관순', '710419-2018916', '02-345-2345', '서울 강남구 개포동', 'kkk@jok.or.kr', 'j1002');
insert into Teacher values(3, '이승복', '730112-1344911', '031-1234-6789', '서울시 강남구 논현동', 'sss@jok.or.kr', 'j1003');
insert into Teacher values(4, '모택동', '730301-1167623', '031-567-5678', '서울시 서초구 서초동', 'mmm@jok.or.kr', 'j1004');
insert into Teacher values(5, '주롱지', '730322-2037921', '031-789-3456', '서울시 강서구 가양동', 'xxx@jok.or.kr', 'j1005');
insert into Teacher values(6, '이순신', '731122-1646213', '031-234-8901', '서울시 강북구 미아동', 'ppp@jok.or.kr', 'j1006');

select * from Teacher;

-- 학생 테이블 데이터 추가
insert into Student values(1, '김현진', '771212-2346111', '017-888-8888', '부산', 'aaa@hanmail.net');
insert into Student values(2, '김석주', '720112-1234812', '016-999-9999', '경기', 'bbb@hanmail.net');
insert into Student values(3, '고훈기', '730102-1555555', '017-555-5555', '서울', 'ccc@hanmail.net');
insert into Student values(4, '유민경', '801111-2222222', '017-222-2222', '전남', 'ddd@hanmail.net');
insert into Student values(5, '김영수', '811231-1777777', '017-777-7777', '충주', 'eee@hanmail.net');
insert into Student values(6, '박상원', '790915-1333333', '017-333-3333', '강원', 'fff@hanmail.net');

select * from Student;

-- 수강 테이블 데이터 추가
insert into Sugang values(1, 'j1001');
insert into Sugang values(2, 'j1002');
insert into Sugang values(3, 'j1003');
insert into Sugang values(4, 'j1004');
insert into Sugang values(5, 'j1005');
insert into Sugang values(6, 'j1006');

select * from Sugang;

-- 수강 과목 테이블 데이터 추가
insert into SugangTB values('j1001', 901, 2, 1);
insert into SugangTB values('j1002', 902, 2, 2);
insert into SugangTB values('j1003', 903, 2, 3);
insert into SugangTB values('j1004', 904, 2, 4);
insert into SugangTB values('j1005', 905, 2, 5);
insert into SugangTB values('j1006', 906, 2, 6);

select * from SugangTB;


select * from room;
select * from student;
select * from sugang;
select * from sugangTB;
select * from teacher;
select * from SugangTB;