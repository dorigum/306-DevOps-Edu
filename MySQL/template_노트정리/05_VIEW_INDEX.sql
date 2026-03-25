/*
   VIEW : 가상 테이블
     - 실제 존재하지 않지만 실제 테이블과 똑같이 사용함 : (select, insert, delete, update 가능)
       : 생성할 때 옵션을 어떻게 설정하느냐에 따라 DML 여부가 달라진다.
       : JOIN을 뷰로 만들었을 때는 DML 안됨
       
     -사용하는 이유?? 
        :  복잡한 쿼리문을(조인, 서브 쿼리) 미리 뷰로 만들어서 사용하면 간단하게 검색 가능함. (단 insert, delete, update 안됨)
        
        :  보안을 위해 사용함(관리자 유형에 따라 특정 컬럼만 선택하여 보여줌)
           - 테이블의 특정한 컬럼과 레코드를 선택해서 뷰를 만들어 권한에 제한이 있는 DBA에게 원본 테이블이 아닌 뷰의 이름을
              알려줌으로써 DBA는 뷰만 접근 할수 있도록 제한을 둔다. 

    -뷰 생성 방법
     CREATE VIEW 뷰이름
     AS 뷰의내용;
     
     --뷰 수정 + 생성
     CREATE OR REPLACE VIEW 뷰이름
     AS 뷰의 내용;
     
     --뷰 삭제
     DROP VIEW 뷰이름;
     
     *VIEW를 생성 할 때 옵션 설정
     WITH CHECK OPTION : VIEW를 생성할 때 조건을 이용해서 만들게 되면 INSERT, UPDATE, DELETE를 할 때 
     조건에 충족하지 않는 정보는 DML을 할 수 없도록 하는 것.

   -- 뷰정보 확인하기
   DESCRIBE 뷰이름;
    
*/

USE MYTEST;

drop table copy_emp;

-- 테이블 복사
CREATE TABLE COPY_EMP
AS SELECT * FROM EMP;

SELECT * FROM COPY_EMP;

-- COPY_EMP 원본으로 사용하는 VIEW 만들어보자.
-- 1) 보안적인 측면
create or replace view v_emp
as select empno, ename, job, hiredate, deptno from copy_emp where deptno = 20;
 
 -- 뷰 검색
select * from v_emp;
 
 -- 테이블 검색
select * from copy_emp;

-- 뷰는 테이블처럼 사용되기 때문에 SELECT, INSERT, UPDATE, DELETE 기본적으로 가능하다.
insert into v_emp values (9000, '도연', '학생', now(), 20);
insert into copy_emp(empno, ename, job, hiredate, deptno) values (9001, '도연2', '학생2', now(), 20);

-- 뷰에 INSERT 해본다. deptno = 30인 정보 추가하기
insert into v_emp values (9002, '도연3', '학생3', now(), 30);

-- 뷰에서 삭제해본다.
-- COPY_EMP 수정해보자.
-- 보안적인 측면으로 VIEW를 만들어보자 (조건- DEPTNO = 20 에 해당하는 레코드와 컬럼을 선택해서 뷰을 만들어보자)
-- 뷰에 레코드 등록(DEPTNO = 20)
-- 뷰에 조건에 해당하지 않는 즉, 20 부서가 아닌 정보를 등록해보자 

/*
위 문장이 등록이 성공한다. 실제로 V_EMP를 검색하면 등록한 30 부서 정보는 검색되지 않는다.
원본 테이블 COPY_EMP를 조회했을 때 검색된다. 뷰를 사용하는 입장에서는 황당!! 
이러한 부분을 개선하기 위해서 뷰를 생성할 때 WITH CHECK OPTION를 설정하면 조건에 만족하지 않는 DML을 오류 발생한다.
*/
 
create or replace view v_emp
as select empno, ename, job, hiredate, deptno from copy_emp where deptno = 20
with check option;

insert into v_emp values (9003, '도연4', '학생4', now(), 20);
insert into v_emp values (9005, '도연5', '학생5', now(), 30); -- Error Code: 1369. CHECK OPTION failed 'mytest.v_emp'

-- 2) 복잡한 쿼리(JOIN, SUBQUERY)를 미리 뷰로 만들어서 조회할때 간소화하게 조회할 수 있도록 하는 용도
-- EX) 어떤 사원과 동일한 근무지(LOC)에서 근무하는 사원의 이름을 출력하고 싶다!!!
use exam;

create or replace view v_subemp
as
select * from subemp
where sal > all (select avg(sal) 평균급여 from subemp
					    where dept_id is not null
                        group by dept_id);

-- 뷰 검색
select * from v_subemp where job = '부장';

use mytest;
describe v_emp;
show create view v_emp;

-- 뷰 삭제


 /*
   INDEX
     : 인덱스는 색인을 만들어서 조회 성능을 극대화 하기 위해 만드는 객체이다.
     : 인덱스는 무조건 만든다고 해서 좋은 것은 아니고, 전체 테이블의 구조를 잘 보고 선택해야한다.
     : 왜냐하면, 인덱스는 특정 테이블의 레코드 검색 속도를 빠르게 하는데 도움이 되지만, 전체 시스템 내에서
       너무 많은 인덱스가 있으면 전체 성능은 저하될 수 있다. 잦은 INSERT, UPDATE, DELETE를 하게 되면
       그때마다 INDEX 설정이 변경되기 때문에, 전체 계정 안에서 시스템 부하가 생긴다.(느려진다)
       검색 속도를 높이기 위해서 인덱스를 먼저 선택하기 보다는, SQL 문장을 좀 더 효율적으로 짜는 노력을 하는 것이 더 좋다!!
      
	 : 주로 인덱스 대상이 되는 컬럼은 검색의 조건으로 많이 활용되는 컬럼으로 만든다.
      
      : 인덱스 생성
       CREATE INDEX 인덱스명 ON 테이블이름(컬럼명, 컬럼명, ....) ;
       
      : 인덱스 삭제
       DROP INDEX 인덱스명 ON 테이블이름;
 */
 
-- EMP테이블의 SAL에 INDEX를 설정해 보자.

 
 -- 설정한 INDEX를 삭제하자.

 

 
 
 
 
 
 
 
 
 
 
 
 
 





