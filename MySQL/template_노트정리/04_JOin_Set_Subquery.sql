/*
  JOIN
   : 한번의 SELECT문장으로 2개 이상의 테이블에 있는 컬럼의 정보를 검색하고 싶을 때 사용한다.
   : JOIN의 종류
     1) INNER JOIN
         - EQUI JOIN = 동등조인 = NATURAL JOIN
         - NON EQUI JOIN : 조인 대상 테이블의 어떤 컬럼의 값도 일치하지 않을 때 사용
                          EX) BETWEEN AND , IS NULL, IS NOT NULL, IN, > , < 등의  조건문을 사용할 때 쓴다.
        
      2) OUTER JOIN
           : 기본 EQUI JOIN을 하면서 별도의 테이블의 모든 정보를 검색하고 싶을 때 사용한다.
              - LEFT OUTER JOIN
              - RIGHT OUTER JOIN
              - FULL OUTER JOIN
    
      3) SELF JOIN
           : 자기 자신 테이블을 조인하는 것(하나의 테이블을 2개처럼 사용하는 것)
           : 주로 재귀적 관계일 때 많이 사용한다. (재귀적 관계란 자기 자신 테이블의 PK를 FK로 참조하는 것)
           
    : JOIN 코딩 방법
      1) SQL JOIN  - FULL OUTER JOIN은 제공하지 않는다. 
      2) ANSI JOIN : 미국국립표준연구소에서 정한 미국의 표준을 기본으로 한다. - 권장
*/

use mytest;

CREATE TABLE TEST1(
    ID VARCHAR(10) PRIMARY KEY,
    NAME VARCHAR(30),
    ADDR VARCHAR(50)
);

INSERT INTO TEST1 VALUES('JANG', '희정', '서울');
INSERT INTO TEST1 VALUES('KIM', '나용', '대구');
INSERT INTO TEST1 VALUES('GYEEB', '미나', '부산');
INSERT INTO TEST1 VALUES('HEE', '미영', '서울');
INSERT INTO TEST1 VALUES('KING', '소현', '제주도');

SELECT * FROM TEST1;

-- TEST1을 참조하는 테이블 생성(ID에 해당하는 사람이 갖고 있는 JOB, SAL의 정보 제공)
CREATE TABLE TEST2(
    CODE CHAR(3) PRIMARY KEY,
    ID VARCHAR(10) ,  
    JOB VARCHAR(30),
    SAL int  ,
   foreign key(id) REFERENCES TEST1(ID) -- FK - 비식별관계(다른 테이블의 PK를 일반 속성으로 참조)
);


INSERT INTO TEST2 VALUES('A01', 'JANG','강사',200);
INSERT INTO TEST2 VALUES('A02', 'JANG','개발자',300);
INSERT INTO TEST2 VALUES('A03', 'HEE','디자이너',250);
INSERT INTO TEST2 VALUES('A04', 'KING','기획자',400);
INSERT INTO TEST2 VALUES('A05', NULL,'백조',500);

SELECT * FROM TEST1;
SELECT * FROM TEST2;

-- ID, NAME, JOB, SAL 검색하고 싶다 -> JOIN한다.
-- 1. 코딩 방식 (SQL 조인)
select *
from test1, test2
where test1.id = test2.id;

-- 특정 컬럼만 조회
select test1.id, name, job, sal
from test1, test2
where test1.id = test2.id;

-- 테이블 이름에 별칭 추가
select t1.id, name, job, sal
from test1 t1, test2 t2
where t1.id = t2.id;

-- 2. 코딩 방식 (ANSI 조인) - 권장
select *
from test1 join test2
on test1.id = test2.id; -- on은 조인의 조건을 준다.

-- 원하는 컬럼만 조회
select t1.id, name, code, job, sal
from test1 t1 join test2 t2
on t1.id = t2.id;

-- 테이블 이름에 별칭
select t1.id, name, code, job, sal
from test1 t1 join test2 t2
on t1.id = t2.id;

-- USING 사용하기 (양쪽 테이블의 조건 컬럼 이름이 같은 경우)
select * -- 중복되는 컬럼(조인 대상 컬럼)은 한 개만 나온다.
from test1 join test2
using(id); -- id 컬럼을 기준으로 조인한다.

-- 특정한 컬럼만 조회
select id, name, job, sal
from test1 join test2
using(id);

-- NATURAL JOIN
select *
from test1 natural join test2; -- 양쪽에 동일한 컬럼을 찾아서 자동으로 조인해준다.

select * from test1;
select * from test2;

-- 2) ANSI 조인 방식 - LEFT
select *
from test1 left join test2 -- left join = left outer join
using(id);

-- 2) ANSI 조인 방식 - RIGHT
select *
from test1 right outer join test2
using(id);

-- 2) ANSI 조인 방식 - FULL
/* select *
from test1 full outer join test2
using(id); */ -- full outer join은 MySQL에서 지원 X

select *
from test1 full join test2
using(id); -- 실행은 되지만 결과는 동등조인으로 나온다.

-- 3개의 테이블 조인하기
CREATE TABLE TEST3(
    CODE CHAR(3)      ,
    MANAGER_NAME VARCHAR(30),
    PHONE VARCHAR(30),
   PRIMARY KEY(code) ,
   foreign key(code) REFERENCES TEST2(CODE)  -- PK, FK (식별관계)
);

INSERT INTO TEST3 VALUES('A01', '유재석', '111-1111');
INSERT INTO TEST3 VALUES('A02', '송중기', '222-2222');
INSERT INTO TEST3 VALUES('A03', '이효리', '333-3333');

SELECT * FROM TEST1;
SELECT * FROM TEST2;
SELECT * FROM TEST3;

-- EX) ID, NAME, ADDR, JOB, SAL, MANAGER_NAME, PHONE 검색

-- ANSI 조인 방식
select *
from test1 join test2 on test1.id = test2.id join test3 on test2.code = test3.code;

-- using 사용하기
select *
from test1 join test2 using(id) join test3 using(code);

-- 특정 컬럼 선택하기
select id, name, job, sal, manager_name, phone
from test1 join test2 using(id) join test3 using(code);

-- USING 사용
-- USING 사용 - 특정 컬럼만 선택

-- 조인에 조건 넣기 -- SAL가 300 이상인 레코드 조인하기
select * -- test1.id, name, job, sal, manager_name, phone
from test1 join test2 on test1.id = test2.id and sal >= 300;

select * -- test1.id, name, job, sal, manager_name, phone
from test1 join test2 on test1.id = test2.id where sal >= 300;

--  ANSI 조인 방식에 조건 주기

 
-- 3) USING에 조건 주기
/* select *
from test1 join test2 using(id) and sal >= 300; */ -- X

select *
from test1 join test2 using(id) where sal >= 300;

-- NON-EQUI JOIN
-- EMP 테이블에서 사원의 정보 + 급여 등급을 함께 검색하고 싶다
SELECT * FROM EMP;
SELECT * FROM SALGRADE;

select *
from emp join salgrade on sal between losal and hisal order by sal;

select empno, ename, job, sal, grade, losal, hisal
from emp join salgrade on sal between losal and hisal order by sal;
 
 
-- SELF JOIN - 자기 자신 테이블을 2개로 만들어서 조인(재귀적 관계)
-- EX) SMITH 사원의 관리자는 FORD입니다. 출력
select * from emp;

select e1.empno 사원번호, e1.ename 사원이름,  e2.empno 관리자의사원번호, e2.ename 관리자이름
from emp e1 join emp e2 -- e1: 사원 테이블, e2: 관리자 정보 테이블
on e1.mgr = e2.empno;

/*
1) 합집합
	UNION ALL - 중복 레코드를 포함
	UNION - 중복 레코드 제외
*/

 -- 테이블 복사
CREATE TABLE SET_COPY
AS SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE SAL > 2000;

SELECT * FROM SET_COPY;

-- drop table set_copy;

-- 레코드를 3개 정도 추가
INSERT INTO SET_COPY VALUES(8000,'HEEJUNG','TEACHER',3000);
INSERT INTO SET_COPY VALUES(8001,'HEE','PROGRAMER',4000);
INSERT INTO SET_COPY VALUES(8002,'KIM','PROGRAMER',2500);

SELECT * FROM SET_COPY; -- 9개의 레코드 

-- 1) 합집합
SELECT EMPNO, ENAME, JOB, SAL FROM EMP -- 14
UNION ALL -- 23(중복 포함)
SELECT EMPNO, ENAME, JOB, SAL FROM SET_COPY; -- 9

SELECT EMPNO, ENAME, JOB, SAL FROM EMP
UNION -- 17개(중복 제거)
SELECT EMPNO, ENAME, JOB , SAL FROM SET_COPY;

 -- -----------------------------------------------------------
 /*
   SUBQUERY - 부질의
    : 서브 쿼리(subqueries)는 쿼리 안에 포함되어 있는 또 다른 쿼리를 말한다.
    : () 괄호로 묶으며 괄호 안에 쿼리가 먼저 실행된 후 그 결과를 메인 쿼리의 조건으로 주로 사용한다. 
    : 서브 쿼리의 결과 행이 한 개일 때  비교 연산자 사용한다.
    : 서브 쿼리의 결과 행이 여러 개 일 때는 ANY, ALL, IN 연산자를 사용한다. 
    : 주로 SELECT에서 많이 사용하지만 CREATE, INSERT, UPDATE, DELETE, 
			 HAVING, WHERE, FROM, ORDER에서도 사용 가능하다.
 */
 
 use mytest;
 
-- EMP 테이블에서 평균 급여보다 더 많이 받는 사원 검색
-- 1) 평균 급여를 구한다.
select avg(sal) from emp;
  
-- 2) 1)에서 구한 평균 급여를 조건으로 사용한다. 
select * from emp where sal > (select avg(sal) from emp);
                  
      
-- JOB에 'A' 문자열이 들어간 사원의 부서와 같은 곳에서 근무하는 사원의 부서 이름을 검색하고 싶다. 
-- 1) JOB에 'A' 문자열이 들어간 사원의 부서 번호를 구한다.
select distinct deptno from emp where job like '%A%';

-- 2) 1)에서 구한 부서 번호에 해당하는 부서 이름을 dept에서 검색한다.
select dname from dept
where deptno in (select distinct deptno from emp where job like '%A%');

-- 부서 번호가 30인 사원들의 급여 중에서 가장 많이 받는 사원보다 더 많이 받는 사원 정보를 검색하고 싶다. 
-- 1) 부서 번호가 30인 사원들의 급여 중에서 최대 값 구하기
select max(sal) from emp where deptno = 30;

-- 2) 1)을 조건으로 사용한다.
select * from emp where sal > (select max(sal) from emp where deptno = 30); -- 2850보다 많이 받는 사원 검색

select * from emp where sal > all (select sal from emp where deptno = 30); -- ALL 연산자 활용

/*
- 검색 결과와 하나 이상이 일치하면 참
 ex) 컬럼명 < any(100, 200, 300) => 최대값보다 작다.
       컬럼명 > any(100, 200, 300) => 최소값보다 크다.

- 검색 결과의 모든 값이 일치하면 참
 ex) 컬럼명 < all(100, 200, 300) => 최소값보다 작다.
       컬럼명 > all(100, 200, 300) => 최대값보다 크다.
*/

-- SUBQUERY를 이용한 INSERT
-- 테이블 복사
CREATE TABLE SUB_EMP
AS SELECT * FROM EMP WHERE 1 = 0; -- 테이블은 복사하고 레코드는 빈 값

SELECT * FROM SUB_EMP;

-- 특정한 칼럼만 다른 테이블로부터 가져와서 INSERT
insert into sub_emp(empno, ename, job, sal)
(select empno, ename, job, sal from emp where sal >= 3000);

SET SQL_SAFE_UPDATES = 0;  

select * from sub_emp where empno = 7902;
select * from emp where empno = 7900;
-- SUBQUERY를 UPDATE
-- EX) EMP 테이블에서 EMPNO 7900인 사원의 JOB, MGR, DEPTNO로 SUB_EMP 테이블의 7902의 사원의 정보로 수정해보자.
update sub_emp
set job = (select job from emp where empno = 7900),
	 mgr = (select mgr from emp where empno = 7900),
     deptno = (select deptno from emp where empno = 7900)
where empno = 7902;

-- SUBQUERY 대신 JOIN 문장으로 변경하면
update sub_emp sub join emp e on e.empno = 7499
set sub.job = e.job, sub.mgr = e.mgr, sub.deptno = e.deptno
where sub.empno = 7839;

-- SUBQUERY를 DELETE
-- EX) EMP 테이블의 평균 급여를 조건으로 사용해서, 평균 급여보다 많이 받는 사원들을 삭제한다.
delete from sub_emp
where sal > (select avg(sal) from emp);