-- 각자의 데이터베이스 연결


/*
  Datatype의 종류
  1) 문자열   
      CHAR(n) : 고정길이 - 1~ 255 크기의 문자열 저장
      VARCHAR(n) : 가변길이 - 0~16383 문자열 저장
      
      * 위의 n의 의미는 byte수가 아닌 문자열의 수이다.
      * 반드시 문자는 '값'  or "값"  형식으로 사용한다.
      * Mysql에서  UTF-8 인코딩으로 한글자는 3 BYTE이다!!
      
		EX) CHAR(6) : 한글, 영문 6글자
			  VARCHAR(6) : 한글, 영문 6글자
            
    
  2) 숫자
      - 정수
       SMALLINT - 2byte
       INT - 4byte
       
      - 실수
          -부동소수점( float  , double)
          -고정소수점(decimal , numeric)
           ex) DECIMAL(20, 5)라고 하면 정수 15자리, 소수 5자리를 표현함
           
           
  3) 날짜      
     : DATE : 날짜만 있는 데이터 , YYYY-MM-DD 형태
       DATETIME : 날짜와 시간을 모두 포함하는 데이터 사용 , YYYY-MM-DD hh:mm:ss

      
      *날짜는 반드시 '년-월-일' 형식으로 묶어서 사용한다.
      * 현재 날짜와시간을 구하는 함수 now() 사용한다.
        EX) SELECT now();
        
	4) ENUM 
        : 선택 가능한 값 중 하나
		: ENUM('남', '여') 
    
    5) SET - 1정규화에 위배 될 수 있다.
        : 여러개 선택가능
        : SET('축구', '농구', '야구', '독서', '게임')
*/


/*
  -테이블 생성
create table 테이블이름( 
컬럼명 datatype  [ null | not null ] [제약조건 ] , 
컬럼명 datatype  [ null | not null ] [제약조건 ]  , 
..
)


  - 제약조건의 종류
   1) PRIMARY KEY : 대표키, 중복X, NOT NULL
   2) FOREIGN KEY :다른 테이블의 컬럼을 참조
   3) UNIQUE : PK유사(테이블에서 대표키는 될수 없는 그렇지만 유일해야하는 속성에 설정) 
   4) CHECK : 특정 컬럼의 값에 허용 될 수있는 조건 설정
   5) DEFAULT  : 기본값 설정

*/

/*
   1) PRIMARY KEY - PK, 기본키, 대표키
      : PK를 설정하면 중복안됨, NOT NULL , 자동의로 INDEX설정 - EX) 학번, 군번, 주민번호, 상품코드,...아이디...
      : PK는 하나의 테이블에 반드시 한개만 존재
      : 2개의 이상의 컬럼을 하나로 묶어서 PK설정 가능 
          - 복합키설정 : 사용할때 불편해서 모델링 과정속에서 복합키를 대리키로 변환하는 경우 많다!!!
*/
-- 테이블 생성
use mytest;

create table student(
	st_no int primary key, -- not null, 중복 X
    st_name varchar(5) not null,
    age int, -- null 허용
	reg_date date
);
-- 검색
select * from student;

-- 테이블 구조 확인
desc student;

/*
  레코드 등록
  INSERT INTO 테이블이름(컬럼명, 컬럼명,....) VALUES(값, 값,값,....);
  INSERT INTO 테이블이름 VALUES(값, 값,값,....); -- 모든 컬럼에 순서대로 값을 넣을때!!!
   INSERT INTO 테이블이름(컬럼명, 컬럼명,....) VALUES(값, 값,값,....) ,(값, 값,값,....) , (값, 값,값,....) ..;  -- 여러데이터를 한번에 삽입 
*/

insert into student values(1, '도연', 20, now());
insert into student(st_no, st_name, reg_date) values(2, '도연', '2026-02-27');

-- PK 특징 테스트
insert into student values(1, '도연이', 22, now()); -- X 잘못된 문법 X
insert into student(st_name, reg_date) values('도연', '2026-02-27'); -- X 잘못된 문법 X

-- 한번에 여러 개의 레코드 추가
insert student values(3, '도연', 30, now()), (4, '도연', 31, now()), (5, '도연', 32, now());

-- MySQL에서 CHAR(n) vs VARCHAR(n)의 차이점 (공백 처리 차이)
/* char 고정길이는 오른쪽 공백을 자동 제거한다.  검색 할때 오른쪽  공백 무시된다. */

CREATE TABLE test_char (
    col1 CHAR(5)
);

INSERT INTO test_char (col1) VALUES ('A   '); -- 'A' 뒤에 공백 3개 포함
INSERT INTO test_char (col1) VALUES ('AB  '); -- 'AB' 뒤에 공백 2개 포함

select * , length(col1) as byte수 , char_length(col1) as 문자길이수 from test_char;

select * from test_char where col1='A';
select * from test_char where col1='A   '; 

/* varchar 가변 길이는 입력한 값 그대로 저장되므로 오른쪽 공백도 포함한다.. */
CREATE TABLE test_varchar (
    col1 VARCHAR(5)
);

INSERT INTO test_varchar (col1) VALUES ('A   '); -- 'A' 뒤에 공백 3개 포함
INSERT INTO test_varchar (col1) VALUES ('AB  '); -- 'AB' 뒤에 공백 2개 포함

select * , length(col1) as byte수 , char_length(col1) as 문자길이수 from test_varchar;
select * from test_varchar where col1='A';
select * from test_varchar where col1='A   '; 

-- 테이블 삭제
drop table student;

-- 하나의 테이블에 2개의 컬럼을 하나로 묶어서 PK설정
create table member(
	id varchar(20),
    name varchar(20) not null,
    jumin char(13),
    age int,
    reg_date datetime,
    primary key(id, jumin)
);

select * from member;

-- 레코드 등록
insert into member values('koo', '도연', '1111', 20, now());
insert into member values('Koo', '도연', '1111', 20, now());
-- MySQL은 컬럼에 있는 영문 대소문자를 가리지 않는다. (동일한 값으로 인식)
-- (Oracle은 컬럼에 있는 영문 대소문자를 가린다.)

insert into member values('Koo2', '도연', '1111', 20, now()); -- 등록 가능
insert into member values('Koo2', '도연', '2222', 20, now()); -- 등록 가능
insert into member values('Koo2', '도연', '2222', 20, now());


select * from emp; -- 사원 테이블


/*
  2) FOREIGN KEY - FK = 외래키
      : 다른 테이블의 PK를 참조하는 것.
      : 테이블에 레코드를 추가할 때 참조되는 대상의 값 이외에는 등록할 수 없다.
          - 참조 무결성 원칙!!!
      : NULL 허용, 중복 가능!!
      : 하나의 테이블에 여러 개의 컬럼이 FK 설정 가능하다.
      
      : 재귀적 관계 설계 - 자기 자신 테이블의 PK를 참조하는 것!!!
      
      *주의 사항
        INSERT 할 때 : 부모키가 INSERT -> 자식 INSERT 
        DELETE 할 때 :  참조하고 있는 자식 DELETE -> 부모 DELETE 해야 한다.
      
		* 이러한 주의사항에 대한 불편함을 해결하기 위해서
		FK를 설정할 때 ON DELETE CASCADE 를 추가하면 부모레코드를 삭제하고
		그 부모키를 참조하는 모든 테이블의 레코드를 함께 삭제한다.
		또는 FK 설정할 때 on delete set null 를 추가하면
		부모 레코드 삭제될 때 참조되는 자식 레코드의 값이 null이된다.
      
      FOREIGN KEY는 InnoDB 엔진에서만 동작한다.
      MyISAM엔진에서는 외래 키 제약 조건이 무시되므로 데이터 무결성이 보장되지 않는다.
     테이블을 만들 때 반드시 ENGINE=InnoDB를 명시해야 한다.
       기존 테이블이 MyISAM이라면 ALTER TABLE ... ENGINE=InnoDB; 명령어로 변경 가능하다.
       
       -- 사용하고 있는 엔진 확인
       SHOW VARIABLES LIKE 'default_storage_engine';
       

      MySQL에서는 반드시 테이블 생성의 마지막 부분에서 FOREIGN KEY를 별도로 선언해야 한다.
     MySQL에서는 REFERENCES를 바로 컬럼 선언 뒤에 쓰는 방식을 지원하지 않는다.
*/

-- 데이터베이스 생성
create database exam;
use exam;

-- 테이블 생성 : 부서 테이블
CREATE TABLE DEPT(
  DEPT_CODE CHAR(3) PRIMARY KEY,
  DNAME VARCHAR(20) NOT NULL,
  LOC VARCHAR(20)
);

-- 레코드 등록
INSERT INTO DEPT VALUES('A01', '경리부','서울');
INSERT INTO DEPT VALUES('A02', '교육부','대구');
INSERT INTO DEPT VALUES('A03', '인사부','서울');

-- 레코드 검색
select * from dept;
select * from emp;
desc emp;

-- 사원 테이블 생성
create table emp (
	emp_no int primary key,
    ename varchar(5),
    sal int,
    dept_code char(3),
    foreign key(dept_code) references dept(dept_code)
);

-- 레코드 등록
 insert into emp(emp_no, ename, dept_code) values(200, '나영', 'A01');
 insert into emp(emp_no, ename, dept_code) values(300, '미영', NULL); -- 외래키 NULL 값 허용
 insert into emp(emp_no, ename, dept_code) values(400, '효리', 'A01');

-- 부모에 존재하지 않는 값을 넣어본다.
 insert into emp(emp_no, ename, dept_code) values(500, '효리', 'A09'); -- 등록 X
-- FK inline 형식 X

-- 부모 레코드 삭제
delete from dept where dept_code = 'A03'; -- 참조되는 자식이 없어서 삭제 가능
delete from dept where dept_code = 'A01'; -- 참조되는 자식이 있는 경우에는 삭제 불가

-- 먼저 참조하고 있는 자식 레코드 삭제하고 --> 부모 레코드 삭제
delete from emp where dept_code = 'A01';
delete from dept where dept_code = 'A01';

-- 직접 순서대로 삭제하는 부분이 번거롭다!!!!
-- FK 설정할 때 옵션(On delete cascade) 을 설정하면 자식 + 부모 함께 삭제해준다.
-- EMP 테이블을 삭제하고 다시 옵션 설정해서 생성한다.
select * from dept;
select * from emp;

 -- on delete set null 사용
drop table emp;

-- 사원 테이블 생성
 create table emp (
	emp_no int primary key,
    ename varchar(5),
    sal int,
    dept_code char(3),
    foreign key(dept_code) references dept(dept_code) on delete set null
);
-- on delete cascade(참조되는 대상 데이터도 함께 삭제)
 
 -- 레코드 추가(emp)
 insert into emp(emp_no, ename, dept_code) values(200, '나영', 'A01');
 insert into emp(emp_no, ename, dept_code) values(300, '미영', NULL); -- 외래키 NULL 값 허용
 insert into emp(emp_no, ename, dept_code) values(400, '효리', 'A01');

-- 삭제(부모(emp)를 삭제 - 참조되는 대상(dept)) --> 자식도 함께 삭제되는지 확인
delete from dept where dept_code = 'A01';
delete from dept where dept_code = 'A02';
delete from dept where dept_code = 'A03';

/*
   레코드 삭제방법
    1) ROLLBACK 처리가능 - DML
    
      DELETE FROM 테이블이름
      [WHERE 조건식]
      
        * where절 없이 delete or update를 MySQL Workbench 등에서 실행할 때 
         , safe update mode(안전 업데이트 모드)가 활성화되어 있으면 기본 키(PK) 또는 인덱스(KEY)가 없는 DELETE나 UPDATE 문을 허용하지 않음. 에러 발생
         
          즉, WHERE 절 없이 DELETE를 실행하면 전체 데이터가 삭제되므로, 실수로 데이터를 삭제하는 것을 방지하기 위해 제한이 걸려 있는 것이다.
          SET SQL_SAFE_UPDATES = 0;  -- 일시적으로 해제
          SET SQL_SAFE_UPDATES = 1;  -- 다시 활성화
          
            MySQL Workbench에서 영구적으로 Safe Mode를 끄려면 
				MySQL Workbench 실행
				Edit → Preferences → SQL Editor 선택
				"Safe Updates" 체크 해제
				MySQL Workbench를 다시 연결(Reconnect)
          
    2) ROLLBACK 안된다. - DDL, rollback은 DML 문장만 가능(INSERT, UPDATE, DELETE)
     TRUNCATE TABLE 테이블이름; -- 모든 레코드를 삭제
      
      
      -- 기본적으로 MySQL은 AUTOCOMMIT 모드가 활성화 되어 있음
      SET AUTOCOMMIT = 0;  -- 자동 커밋 비활성화
      
          START TRANSACTION;    -- 트랜잭션 시작
           DELETE FROM userlist WHERE id = 1;  -- 레코드 삭제
           ROLLBACK;  -- 변경 사항 되돌리기
*/


/*
  3) UNIQUE
      : 중복안됨, NULL허용(NOT NULL을 설정하면 PK와 동일) 
      : 후보키중에 대표키가 될수 없는 키를 UNIQUE 설정한다. 
      :  한테이블에 여러개의 컬럼에 설정가능
      
  4) CHECK
      : 조건을 설정하여 조건에 만족하지 않는 정보는 INSERT 할수 없다!
    
  5) DEFAULT
      : 기본값 설정(자주사용되는 값을 미리 설정해놓고 자동으로 값이 들어갈수 있도록 하는것)
      : EX) 등록일, 조회수....
      
*/
use exam;

-- 테이블 생성
create table test (
	id varchar(10) primary key,
    jumin char(13) not null unique,
    name varchar(20) unique, -- 한 테이블의 여러 컬럼에 유니크를 사용해도 된다.
    age int check(age >= 20 and age <= 30), -- 나중에 between and 연산자로 바꾼다.
    gender enum('남', '여'),
	reg_data datetime default now() not null
);
 
 -- 레코드 추가
 insert into test values(1, '9912122245960', '도연', 20, '여', now());
select * from test;

-- jumin 중복
-- name null 허용
-- age 30 넘은 값 추가
-- 성별에 남, 여 이외의 값 추가

-- AUTO_INCREMENT 설정
/*
  •INT 타입에서 사용 가능하며 새로운 레코드가 추가될 때 자동 증가.
  •PRIMARY KEY 또는 UNIQUE가 필요함.
  • AUTO_INCREMENT는 하나의 테이블에서 오직 하나의 컬럼에만 사용할 수 있다
  •자동 증가하는 값은 테이블 내에서 하나의 시퀀스로 관리되므로, 
  여러 개의 AUTO_INCREMENT가 있으면 충돌이 발생할 수 있기 때문에 하나의 테이블에 한 개만 가능하다.
*/
create table member (
	id int primary key auto_increment,
    username varchar(20) not null, 
    reg_date datetime
);

select * from member;

-- 테이블 구조
desc member;

-- 레코드 추가
insert into member (username, reg_date) values ('도연', now());
insert into member (username, reg_date) values ('나연', now());

-- id에 직접 값을 넣어 본다.
insert into member (id, username, reg_date) values (50, '도연', now());

-- 레코드 추가
insert into member (username, reg_date) values ('도연2', now()); -- id의 값이 51

-- 복합 키(Composite Key)와 AUTO_INCREMENT 조합

-- 하나의 AUTO_INCREMENT를 여러 개의 컬럼에서  사용하고 싶다!
CREATE TABLE sequence_table ( 
    seq_id INT AUTO_INCREMENT PRIMARY KEY 
); 
 
CREATE TABLE products ( 
    product_id INT PRIMARY KEY, 
    batch_number INT NOT NULL 
); 
 
INSERT INTO sequence_table () VALUES ();  -- 새로운 시퀀스 값 생성 
SET @new_id = LAST_INSERT_ID();           -- 방금 생성된 ID 가져오기 
 
INSERT INTO products (product_id, batch_number) VALUES (@new_id, 101);

SELECT * FROM sequence_table;
SELECT * FROM products;

INSERT INTO sequence_table () VALUES ();
SET @new_id = LAST_INSERT_ID();

INSERT INTO products (product_id, batch_number) VALUES (@new_id, @new_id);

  
-- 테이블 수정
-- ① 컬럼추가
-- alter table 테이블이름 add (컬럼명 자료형 [제약조건] , 컬럼명 자료형 [제약조건] , ....)

 
-- ② 컬럼삭제
-- alter table 테이블이름 drop column 컬럼이름
 
-- ③ datatype변경
-- alter table 테이블이름 modify 컬럼이름 변경자료형  [ not null | null ]
    
④ 컬럼이름 변경
 alter table 테이블이름 rename column 기존컬럼명 to 변경컬럼명
 
 ⑤ 제약조건 추가
  alter table 테이블이름 ADD CONSTRAINT 별칭 제약조건종류 ;
  
 -제약조건 삭제
  ALTER TABLE 테이블이름 DROP 제약조건;
  
 
 - 테이블 삭제
 drop table 테이블이름;

*/

-- 테이블 만들기
create table test(
  id varchar(20),
  name varchar(10),
  gender char(3)
);

select * from test;

-- 1. id에 pk 제약조건 추가
alter table test add primary key(id);

-- 1. id에 pk 제약조건 삭제
 alter table test modify name varchar(20) not null;

-- name에 varcher(20)  not null로 변경

-- 컬럼 추가

-- 컬럼이름 변경

-- 컬럼 추가

-- 컬럼 삭제
 
-- 컬럼추가

/*
SQL의 종류
 - DDL 문장(CREATE, DROP, ALTER, TRUNCATE)
 - DML 문장(INSERT ,UPDATE, DELETE)
*/

/*
  데이터 조작 : DML(INSERT , UPDATE, DELETE)
   - ROLLBACK OR COMMIT 가능
   
   1) INSERT 문장
       -INSERT INTO 테이블이름(컬럼명, 컬럼명,....) VALUES(값, 값,값,....);
       -INSERT INTO 테이블이름 VALUES(값, 값, 값, ....); -- 모든 컬럼에 순서대로 값을 넣을 때!!!
   
   2) DELETE 문장
       DELETE [FROM] 테이블이름
       [WHERE 조건식]
   
   3) UPDATE 문장
      UPDATE 테이블이름
      SET 컬럼명 = 변경 값, 컬럼명 = 변경 값, ....
      [WHERE 조건식]
*/


/*
   -- 테이블 복사
   CREATE TABLE 테이블이름
   AS 복사할 테이블 정보;
   
    주의 : 테이블을 복사하면 제약 조건은 복사 안된다!!! - 복사한 후에 제약조건을 ALTER를 이용해서 추가한다.

*/

use myTest;
select * from emp;
desc emp;
-- 1) 모든 레코드 모든 컬럼 복사해보자
create table copy_emp
as select * from emp;

select * from copy_emp;
desc copy_emp; -- 제약 조건은 복사되지 않는다.

-- 2) 특정 레코드 , 특정 컬럼만 복사해보자
create table copy_emp2
as select empno, ename, job, sal from emp where sal >= 3000;

select * from copy_emp2;

-- 3) 테이블의 구조만 복사해보자.
create table copy_emp3
as select * from emp where 1 = 0;

select * from copy_emp3;

-- copy_emp 테이블에서 empno가 7566 사원의 sal을 4000 , comm을 100 으로 변경해보자
update copy_emp
set sal = 4000, comm = 100
where empno = 7566;

rollback;

SET AUTOCOMMIT = 0;
select * from copy_emp;

delete from copy where sal >= 20;

update copy_emp set job = 'teacher' where deptno = 30;

rollback;

commit;
select * from copy_emp;

-- 모든 레코드 삭제
delete from copy_emp;

-- 모든 레코드 삭제 = 절삭
truncate table copy_emp; -- DDL 문장으로 ROLLBACK 불가!!
rollback;