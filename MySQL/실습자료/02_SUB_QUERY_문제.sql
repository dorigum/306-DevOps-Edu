-- 데이터베이스 선택
USE exam;

DROP TABLE subdept;
SELECT * FROM subdept;

CREATE TABLE subdept(dept_id int, dname VARCHAR(20));

INSERT INTO subdept VALUES(100,'관리부');
INSERT INTO subdept VALUES(200,'경리부');
INSERT INTO subdept VALUES(300,'생산부');
INSERT INTO subdept VALUES(400,'영업부');

DROP TABLE subemp;

-- emp 테이블 생성
Create Table subemp(
	emp_id 	   int Primary Key, -- 사원번호
	emp_name	   Varchar(10) Not Null,  -- 사원이름
	job      	               Varchar(10) Not Null,  -- 직업
	dept_id  	  int,        -- 부서번호
	sal 	     int Not Null,-- 급여
	bonus	     int,-- 보너스
	mgr_id	     int,-- 관리자번호
	hiredate      date  Not Null -- 입사일
);

-- 데이터 삽입
INSERT INTO subEMP VALUES (6200,'박철수','대표',200,  5000,NULL,NULL, '1997-12-17');
INSERT INTO subEMP VALUES (6311,'김대정','부장',100, 3500,Null,6200, '1998-12-17');
INSERT INTO subEMP VALUES (7489,'민동규','세일즈',400,  1850,200,6321, '1999-02-27') ;
INSERT INTO subEMP VALUES (7522,'정종철','세일즈',400, 1850,300,6321,'1998-02-28') ;
INSERT INTO subEMP VALUES (6321,'이종길','부장',400, 3800,500,6200,'1999-04-20') ;
INSERT INTO subEMP VALUES (6351,'진대준','부장',300,   2850,NULL,6200,'2000-05-31');
INSERT INTO subEMP VALUES (7910,'이영희','경리',300, 1000,NULL,6351,'2001-05-01');
INSERT INTO subEMP VALUES (6361,'김철수','부장',200, 3200,NULL,6200,'2000-06-09') ;
INSERT INTO subEMP VALUES (7878,'백기수','연구직',200, 3000,NULL,6361,'2001-06-05') ;
INSERT INTO subEMP VALUES (7854,'진영진','세일즈',400, 1500,0,6321,'2001-09-08') ;
INSERT INTO subEMP VALUES (7872,'이문정','사무직',100, 1500,NULL,6311,'2001-02-12') ;
INSERT INTO subEMP VALUES (7920,'마리아','사무직',300, 1050,NULL,6351,'2001-03-18');
INSERT INTO subEMP VALUES (7901,'정동길','연구직',NULL, 3000,NULL,NULL,'2001-12-03');
INSERT INTO subEMP VALUES (7933,'김철수','사무직',200,  1050,NULL,6361,'2002-01-02');

SELECT * FROM subemp;
SELECT * FROM subdept;

-- ex1) 이종길 사원의 부서명(dname)을 검색하시오.
select dname from subdept where dept_id in (select dept_id from subemp where emp_name = '이종길');

-- ex2) dept_id가 100인 사원 급여의 최대 값보다 많이 받는 사원을 검색하시오.
select * from subemp
where sal > all (select sal from subemp where dept_id = 100);

select * from subemp
where sal > (select max(sal) from subemp where dept_id = 100);

-- select max(sal) from subemp where dept_id = 100;

-- select dname from dept
-- where deptno in (select distinct deptno from emp where job like '%A%');

-- ex3) 급여를 3000 이상 받는 사원이 소속된 부서와 동일한 부서에서 근무하는 사원들의 정보
 select *
 from subemp
 where dept_id in (select distinct dept_id from subemp where sal >= 3000 and dept_id is not null)
 order by dept_id;
 
-- ex4) 부서 번호가 300인 사원들 중에서 급여를 가장 많이 받는 사원보다 더 많은 급여를 받는 사람의 정보를 검색
select *
from subemp
where sal > all (select sal from subemp where dept_id = 300);

select max(sal) from subemp where dept_id = 300; -- 2850

-- ex5) 부서 번호가 300인 사원들 중에서 급여를 가장 적게 받는 사원보다 더 많은 급여를 받는 사람의 정보를 검색
select *
from subemp
where sal > any(select sal from subemp where dept_id = 300);

-- select min(sal) from subemp where dept_id = 300;

-- ex6) 정동길의 급여와 동일하거나 더 많이 받는 사원의 정보 검색
 select *
 from subemp
 where sal >= (select sal name from subemp where emp_name = '정동길');
 
-- ex7) 직급(JOB)이 사무직인 사원의 부서번호와 부서명 출력
select dept_id, dname
from subdept
where dept_id in(select dept_id from subemp where job like '%사무직%'); -- job = '사무직'

-- ex8) 부서가 경리부인 모든 사원의 정보 출력
select *
from subemp
where dept_id = (select dept_id from subdept where dname = '경리부');

-- ex9) 대표에게 보고를(관리자가 대표) 하는 모든 사원의 정보 출력
select *
from subemp
where emp_id in (select mgr_id from subemp where job = '대표');

-- select * from subemp where mgr_id is null;

-- ex10) 이름에 '정'이 들어가면서 평균급여보다 높은 급여를 받는 사원과 동일한 부서에서 근무하는 사원의 정보 검색
--          (단, 부서번호 null은 제외함)
select *
from subemp
where dept_id in (select dept_id from subemp where emp_name like '%정%' and
						  sal > (select avg(sal) from subemp) and dept_id is not null);

-- ex11) 각 부서의 어떤 평균 급여보다 급여를 많이 받는 사원의 정보를 검색
-- 11번 답 - 1
select * from subemp
where sal > all (select avg(sal) 평균급여 from subemp where dept_id is not null group by dept_id);

-- 11번 답 - 2
-- MySQL에서는 집계 함수(Aggregate Function) 안에 또 다른 집계 함수를 직접 중첩하는 것을 허용하지 않음.
-- 오라클에서는 MAX(AVG(sal))을 허용하지만, MySQL에서는 이를 서브쿼리를 분리하여 해결
/*
SELECT * FROM subEMP
WHERE sal > (SELECT max(AVG(sal)) FROM subEMP
	                where dept_id is not null
	                group BY dept_id);
*/

 -- 서브 쿼리로
SELECT * FROM subEMP
WHERE sal > (
    SELECT MAX(avg_sal)
    FROM (
        SELECT AVG(sal) AS avg_sal
        FROM subEMP
        WHERE dept_id IS NOT NULL
        GROUP BY dept_id
    ) AS avg_salaries
);

 select dept_id, avg(sal) 평균급여 from subemp
 where dept_id is not null
 group by dept_id;

-- ex12) 모든 사무직 사원보다 급여가 적으면서 사무직이 아닌 모든 사원의 정보 검색
select * from subemp
where sal < all (select sal from subemp where job = '사무직')
						and job != '사무직';