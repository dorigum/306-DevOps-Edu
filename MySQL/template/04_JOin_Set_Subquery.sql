/*
  JOIN
   : 한번의 SELECT문장으로 2개 이상의 테이블에 있는 컬럼의 정보를 검색하고 싶을 때 사용한다.
   : JOIN의 종류
     1) INNER JOIN
         - EQUI JOIN = 동등조인 = NATURAL JOIN
         - NON EQUI JOIN : 조인 대상 테이블의 어떤 컬럼의 값도 일치하지 않을 때 사용
                          EX) BETWEEN AND , IS NULL, IS NOT NULL, IN, > , < 등의  조건문을 사용할때 쓴다.
        
      2) OUTER JOIN
           : 기본 EQUI JOIN을 하면서 별도의 테이블의 모든 정보를 검색하고 싶을때 사용한다.
              - LEFT OUTER JOIN
              - RIGHT OUTER JOIN
              - FULL OUTER JOIN
    
      3) SELF JOIN
           : 자기 자신테이블을 조인하는 것(하나의 테이블을 2개처럼 사용하는 것)
           : 주로 재귀적관계일 때 많이 사용한다. (재귀적관계란 자신자신테이블의 PK를 FK로 참조하는 것)
           
    : JOIN 코딩 방법
      1) SQL JOIN  - FULL OUTER JOIN은 제공하지 않는다. 
      2) ANSI JOIN : 미국국립표준연구소에서 정한 미국의 표준을 기본으로 한다. - 권장
*/

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
   foreign key(id) REFERENCES TEST1(ID) -- FK - 비식별관계(다른테이블의 PK를 일반속성으로 참조)
);


INSERT INTO TEST2 VALUES('A01', 'JANG','강사',200);
INSERT INTO TEST2 VALUES('A02', 'JANG','개발자',300);
INSERT INTO TEST2 VALUES('A03', 'HEE','디자이너',250);
INSERT INTO TEST2 VALUES('A04', 'KING','기획자',400);
INSERT INTO TEST2 VALUES('A05', NULL,'백조',500);


SELECT * FROM TEST1;
SELECT * FROM TEST2;

-- ID, NAME, JOB, SAL 검색하고 싶다 -> JOIN한다.
-- 1.코딩방식 (SQL조인)


-- 2..코딩방식 (ANSI조인) - 권장


-- USING사용하기 (양쪽 테이블의 조건 컬럼이름이  같은경우)


-- NATURAL JOIN

 
-- 2) ANSI조인방식 - LEFT


-- 2) ANSI조인방식 - RIGHT


-- 2) ANSI조인방식 - FULL


-- 3개의 테이블 조인하기
CREATE TABLE TEST3(
    CODE CHAR(3)      ,
    MANAGER_NAME VARCHAR(30),
    PHONE VARCHAR(30),
   PRIMARY KEY(code) ,
   foreign key(code) REFERENCES TEST2(CODE)  -- PK, FK (식별관계)
);


INSERT INTO TEST3 VALUES('A01', '유재석','111-1111');
INSERT INTO TEST3 VALUES('A02', '송중기','222-2222');
INSERT INTO TEST3 VALUES('A03', '이효리','333-3333');

SELECT * FROM TEST1;
SELECT * FROM TEST2;
SELECT * FROM TEST3;

-- EX) ID, NAME, ADDR, JOB, SAL, MANAGER_NAME, PHONE 검색

-- ANSI 조인방식


-- 특정 컬럼 선택하기


-- USING사용


-- USING사용 - 특정컬럼만 선택




-- 조인에 조건 넣기 -- SAL가 300이상인 레코드 조인하기

 --  ANSI조인방식에 조건 주기

 
 -- 3) USING에 조건 주기 




-- NON-EQUI JOIN 
-- EMP테이블에서 사원의 정보 + 급여등급을 함께 검색하고 싶다
SELECT * FROM EMP;
SELECT * FROM SALGRADE;

 
-- SELF JOIN - 자기자신 테이블을 2개로 만들어서 조인(재귀적관계)
-- EX) SMITH사원의 관리자는 FORD입니다. 출력


/*

   1) 합집합
        UNION ALL - 중복레코드를포함
        UNION - 중복레코드 제외
        
*/

 -- 테이블 복사
CREATE TABLE SET_COPY
AS SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE SAL > 2000;

SELECT * FROM SET_COPY;

-- 레코드르 3개정도 추가
INSERT INTO SET_COPY VALUES(8000,'HEEJUNG','TEACHER',3000);
INSERT INTO SET_COPY VALUES(8001,'HEE','PROGRAMER',4000);
INSERT INTO SET_COPY VALUES(8002,'KIM','PROGRAMER',2500);

SELECT * FROM SET_COPY; -- 9개의 레코드 

-- 1) 합집합
SELECT EMPNO, ENAME, JOB, SAL FROM EMP
UNION ALL
SELECT EMPNO, ENAME, JOB, SAL FROM SET_COPY;

SELECT EMPNO, ENAME, JOB, SAL FROM EMP
UNION 
SELECT EMPNO, ENAME, JOB , SAL FROM SET_COPY;



 -- -----------------------------------------------------------
 /*
   SUBQUERY - 부질의
    : 서브 쿼리(subqueries)는 쿼리 안에 포함되어 있는 또 다른 쿼리를 말한다.
    : ()괄호로 묶으며 괄호 안에 쿼리가 먼저 실행된 후 그 결과를 메인 쿼리의 조건으로 주로 사용한다. 
    : 서브쿼리의 결과 행이 한 개일 때  비교연산자 사용한다.
    : 서브쿼리의 결과 행이 여러 개 일 때는 ANY, ALL, IN 연산자를 사용한다. 
    : 주로 SELECT에서 많이 사용하지만 CREATE, INSERT, UPDATE ,DELTE, 
           HAVING, WHERE , FROM ,ORDER 에서도 사용가능 하다.

 */
 
 -- EMP테이블에서 평균 급여보다 더 많이 받는 사원 검색
  -- 1) 평균 급여를 구한다.
     
  
  -- 2) 1)에서 구한 평균급여를 조건으로 사용한다. 
  
                  
      
-- JOB에 'A'문자열이 들어간 사원의 부서와 같은 곳에서 근무하는 사원의 부서이름 검색하고 싶다. 

  
 -- 부서번호가 30인 사원들에 급여중에서 가장 많이 받는 사원보다 더 많이 받는 사원정보를 검색하고 싶다. 


/*
 검색결과와 하나이상이 일치하면 참
 ex) 컬럼명 < any(100, 200, 300) => 최대값보다 작다
      컬럼명 > any(100, 200, 300) => 최소값 보다 크다


- 검색결과의 모든 값이 일치하면 참.
 ex) 컬럼명 < all(100, 200, 300) => 최소값보다 작다
     컬럼명 > all(100, 200, 300) => 최대값보다 크다.
*/



-- SUBQUERY를 이용한 INSERT
-- 테이블 복사
CREATE TABLE SUB_EMP
AS SELECT * FROM EMP WHERE 1=0;

SELECT * FROM SUB_EMP;


-- 특정한 칼럼만 다른테이블로부터 가져와서 INSERT



SET SQL_SAFE_UPDATES = 0;  

-- SUBQUERY를 UPDATE
-- EX) EMP테이블에서 EMPNO 7900인 사원의 JOB, MGR, DEPTNO로 SUB_EMP테이블의 7566의 사원의 정보로 수정해보자.


-- SUBQUERY 대신 JOIN문장으로 변경하면



-- SUBQUERY를 DELETE
  -- EX) EMP테이블의 평균 급여를 조건으로 사용해서 평균급여보다 많이 받는 사원들을 삭제한다. 
  

 




























