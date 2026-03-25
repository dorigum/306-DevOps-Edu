-- 데이터베이스 접속
USE MYTEST;

SELECT * FROM emp; -- 사원 정보
SELECT * FROM dept; -- 부서 정보
SELECT * FROM salgrade; -- 급여 등급

-- 1. SMITH 에 대한 정보 검색(ename, emp.deptno, loc)
select ename, emp.deptno, loc
from emp join dept
using(deptno)
where ename = 'smith';

/*
2. NEW YORK에 근무하는 사원의 이름과 급여를 출력 - 서브 쿼리로도 가능하다.
먼저 dept 테이블에서 NEW YORK의 부서 번호를 구한다. 그 구한 부서 번호를 부서 번호로 사용하고 있는 사원의 정보를 검색한다.
*/
select ename, sal, loc
from emp e join dept d
using(deptno) -- on e.deptno = d.deptno
where loc = 'new york';

-- 3. ACCOUNTING 부서 소속 사원의 이름과 입사일 출력 - 서브쿼리 가능
select ename, hiredate, e.deptno, dname
from emp e join dept d
on e.deptno = d.deptno
where dname = 'accounting';

-- 4. 직급이 MANAGER인 사원의 이름, 부서명 출력
select ename, dname, job
from emp e join dept d
using(deptno)
where job = 'manager';

-- 5. 사원의 급여가 몇 등급인지를 검색
-- between A and B
select * from emp;
select * from salgrade;

select ename, sal, grade, losal, hisal
from emp e join salgrade s on sal between losal and hisal;

-- 6. 사원 테이블의 부서 번호로 부서 테이블을 참조해서 부서명, 급여 등급도 검색
-- select dname, grade
-- from emp e join dept d on emp.DEPTNO = dept.DEPTNO join salgrade;

select ename, deptno, dname, grade
from emp e join dept d
using(deptno) join salgrade
on sal between losal and hisal;

SELECT * FROM emp;
SELECT * FROM dept; -- 부서정보
SELECT * FROM salgrade; -- 급여등급


-- 7. SMITH의 매니저(mgr) 이름(ename) 검색
-- 'SMITH의 관리자는 ~ 입니다.'  - CONCAT함수 이용
select concat(e1.ename, '의 관리자는 ', e2.ename, '입니다.') as INFO
from emp e1 join emp e2
on e1.mgr = e2.empno and e1.ename = 'smith';
  
-- 8. 관리자가 KING인 사원들의 이름과 직급(job) 검색
  select e1.ename, e1.job, e2.ename
  from emp e1 join emp e2
  on e1.mgr = e2.empno and e2.ename = 'KING';
  
-- 9. SMITH와 동일한 부서번호(DEPTNO)에서 근무하는 사원의 이름 출력
-- 단, SMITH 데이터 출력 불가

-- smith deptno : 20
select e1.ename ename1, e2.ename ename2
from emp e1 join emp e2 -- e1: 사원 정보 / e2: 동일한 부서에서 근무하는 사원 정보
on e1.deptno = e2.deptno
where e1.ename = 'smith' and e2.ename != 'smith';

desc emp;
desc dept;

-- 10. SMITH와 동일한 근무지(LOC)에서 근무하는 사원의 이름 출력
-- 단, SMITH 데이터 출력 불가
SELECT D1.ENAME , D1.LOC , D2.ENAME , D2.LOC
FROM
(SELECT * FROM EMP JOIN DEPT USING(DEPTNO)) D1
JOIN
(SELECT * FROM EMP JOIN DEPT USING(DEPTNO)) D2
ON D1.LOC = D2.LOC  WHERE D1.ENAME = 'SMITH' AND D2.ENAME != 'SMITH';

-- 11. 사원명, 해당하는 매니저명 검색
-- 반드시 모든 사원들(CEO 포함) 정보 검색
-- CEO인 경우 매니저 정보 null
select e1.ename 사원이름, e2.ename 관리자이름
from emp e1 left join emp e2 -- e1: 사원 / e2: 관리자
on e1.mgr = e2.empno;