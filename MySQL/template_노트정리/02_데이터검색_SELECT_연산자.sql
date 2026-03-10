/*
   SELECT문장 - DQL문장
     구조
    select distinct | * | 컬럼명 as 별칭, 컬럼명 별칭,....   : 열을 제한 :PROJECTION
    from 테이블이름     
    [where 조건식]  : 레코드(튜플)제한  - SELECTION
    [order by 컬럼명 desc | asc , .. ] -정렬
    
    
    * distinct 는 중복레코드를 제거
    * AS는 컬럼에 별칭 만들기 
    * 실행 순서
      SELECT   3) 
      FROM     1)
      WHERE    2) 
      ORDER BY 4) 
    
*/

-- 데이터 베이스 선택
use mytest;

SELECT * FROM EMP; -- 사원 테이블
SELECT * FROM DEPT; -- 부서정보 테이블

-- 1) EMP 테이블에서 원하는 컬럼(별칭)
select empno as 사원번호, ename 이름, job from emp;

-- 2) 중복 행 제거하기 - DISTINCT
-- EX) 우리 회사에 어떤 JOB이 있는지 JOB의 종류를 알고 싶다!!!
select distinct job from emp;
select distinct DEPTNO from emp;

-- 3) 조건 만들기 
-- 급여가 3000 이상인 사원 검색
select * from emp where sal >= 3000;

 -- 4) 정렬
 -- 급여가 2000 이상인 사원을 검색하고 급여를 기준으로 정렬
select * from emp where sal >= 2000 order by sal;

 -- JOB을 기준으로 내림차순 정렬하고 JOB이 같으면 급여를 기준으로 정렬
select * from emp order by job desc, sal;

-- 컬럼 별칭을 조건으로 사용할 수 있을까?  --- X
select empno 사원번호, ename 이름 , sal 급여 -- 3)
from emp      -- 1)
where 급여 >=3000; -- 2)
-- 작성 순서: SELECT - FROM - WHERE - GROUP BY -  HAVING - ORDER BY
-- 실행 순서: FROM - WHERE - GROUP BY - HAVING - SELECT - ORDER BY

select empno 사원번호, ename 이름 , sal 급여 -- 3)
from emp      -- 1)
where sal >= 3000;

-- 컬럼 별칭을  정렬 대상으로  사용할 수 있을까? -- OK
select empno 사원번호, ename 이름 , sal 급여 
from emp      
order by 급여 ;

-- 정렬 대상을 컬럼 index로 가능하다.
select empno 사원번호, ename 이름 , sal 급여 
from emp 
order by 3 desc; -- 급여를 기준으로 정렬

select empno 사원번호, ename 이름 , sal 급여 
from emp 
order by 3, 1; -- 급여 기준으로 정렬, 값이 같으면 사원번호로 정렬

-- NULL이 있는 컬럼을 대상으로 정렬을 해보자
SELECT * FROM EMP ORDER BY COMM; -- 오름차순일 때 NULL은 처음에 조회된다.
SELECT * FROM EMP ORDER BY COMM DESC; -- 내림차순일 때 NULL은 마지막에 조회된다.

-- 칼럼들끼리 연산이 가능하다
select * from emp;

-- ex) 급여와 커미션을 더하고 12을 곱해서 연봉 컬럼 출력
select empno, ename, sal, comm, (sal + comm) * 12 as 연봉 from emp;

-- NULL값을 다른 값으로 변경해서 연산할 수 있다  -->  IFNULL(칼럼명, 변경값)
select comm, ifnull(comm, 100) from emp;

-- ex) 급여와 커미션을 더하고 12을 곱해서 연봉 컬럼 출력
select empno, ename, sal, comm, (sal + ifnull(comm, 0)) * 12 as 연봉 from emp;

--  문자열 연결 : concat() 함수 사용가능.(~님 년봉은 ~ 입니다)
select ename, sal, concat(ename, '님 연봉은 ', (sal + ifnull(comm, 0)) * 12, '입니다.') as message from emp;


/*
  연산자 종류
  1) 산술연산자
     +, -, *, / 
     나머지 : MOD(값, 나눌수)
     
   2) 관계연산자
       > , <, >= , <= , !=, <>
       같다  :  =
       
   3) 비교연산자
    - AND
    - OR
    - IN :  컬럼명 IN (값, 값, 값)  - 하나의 컬럼을 대상으로 또는으로 비교할때 사용한다.
    
    - BETWEEN AND :  컬럼명 BETWEEN 최소 AND 최대 - 하나의 컬럼을 대상으로 최소 ~ 최대를 비교할때
    
    - LIKE  : 와일드카드 문자와 함께 사용한다.
        1. % : 0개이상의 문자
        2. _ : 한글자  
        
        EX)  name like 'J%' ;   - NAME에 첫글자가 J로 시작하는 모든 문자
             name like '___' ;  - NAME이 3글자 
             name like 'J_J%';  - NAME의 첫글자가 J로 시작하고 3번째 글자 A인 정보 검색
             
    
    - NOT : 위의 모든 연산자들 앞에 NOT을 붙히면 반대 개념.
        
*/


-- EX) SAL 가 2000 ~ 4000사원 검색(AND, BETWEEN AND )
select * from emp where sal >= 2000 and sal <= 4000 order by sal;
select * from emp where sal between 2000 and 4000 order by sal;
 
-- EX) SAL 가 2000 ~ 4000사원 아닌 레코드 검색 -  NOT
select * from emp where not(sal >= 2000 and sal <= 4000) order by sal;
select * from emp where not sal between 2000 and 4000 order by sal;
select * from emp where sal not between 2000 and 4000 order by sal;

-- EX) EMPNO 가 7566, 7782, 7844인 사원검색 ( OR, IN)
select * from emp where empno = 7566 or empno = 7782 or empno = 7844;
select * from emp where empno in(7566, 7782, 7844);

-- EX) EMPNO 가 7566, 7782, 7844인 사원이 아닌 검색 ( NOT)
select * from emp where not (empno = 7566 or empno = 7782 or empno = 7844);
select * from emp where not empno in (7566, 7782, 7844);
select * from emp where empno not in (7566, 7782, 7844);

-- 1) JOB에 'A' 문자로 시작하는 레코드 검색
select * from emp where job like 'A%';

-- 2) JOB에 끝 끌자가 'N'으로 끝나는 레코드 검색
select * from emp where job like '%N';

-- 3) ENAME이 4글자인 레코드 검색
select * from emp where ename like '____';

-- 4) ENAME에 A글자가 포함된 레코드 검색
select * from emp where '%A%';

-- 5) ENAME 전체 글자가 5글자이고, 두번째 글자가 m이면서 끝 글자가 h인 레코드 검색
select * from emp where ename like '_m___h';

-- 6) ENAME 문자열에 % 포함한 레코드 검색
select * from copy_emp;

insert into copy_emp(empno, ename, job, hiredate) values(9000, 'ja%ee', 'teacher', now());
insert into copy_emp(empno, ename, job, hiredate) values(9001, 'kin%g', 'progra', now());
insert into copy_emp(empno, ename, job, hiredate) values(9001, 'king', 'progra', now());

select * from copy_emp where ename like '%%%';
select * from copy_emp where ename like '%#%%' escape '#';
select * from copy_emp where ename like '%$%%' escape '$';


/*
    NULL 찾기
    1) IS NULL
    2) IS NOT NULL
*/

-- COMM이 NULL인 레코드 검색
select * from emp where comm = null; -- 잘못된 쿼리문

select * from emp where comm is null;
select * from emp where comm is NULL;

select * from emp where comm is not NULL;

commit;

-- COPY_EMP 테이블에서 COMM이 NULL인 COMM의 값을 100으로 변경
select * from emp copy_emp;
update copy_emp set comm = 100 where comm is null;

/*
LIMIT으로 상위 데이터 조회하기
특정 조건에 해당하는 데이터 중에서 상위 N개의 데이터만 보고 싶은 경우 SELECT 문에 LIMIT을 조합하면 된다. 
예를 들면 SELECT ~ FROM ORDER BY ~ LIMIT 10 과 같은 방식으로 LIMIT 다음에 조회하려는 행의 개수를 입력하면 된다.
LIMIT의 경우 상위 N개의 데이터를 반환하므로 정렬 우선순위가 매우 중요하다.
*/

use mytest;

-- 급여를 가장 많이 받는 사원 3명 검색
select * from emp order by sal desc limit 3;

-- 급여를 가장 적게 받는 사원 3명 검색
select * from emp order by sal limit 3;

-- 아래 쿼리는 SAL 열을 기준으로 내림차순 뒤, 6번째 행부터 3행을 조회함 
SELECT * FROM EMP ORDER BY SAL DESC limit 5, 3;

-- 아래 쿼리는 SAL 열을 기준으로 내림차순 뒤, 3개를 건너 뛰고 4개의 데이터를 조회 
SELECT * FROM EMP ORDER BY SAL DESC  LIMIT 4 OFFSET  3;