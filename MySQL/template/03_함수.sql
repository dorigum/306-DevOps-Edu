  /*문자열 함수
    - upper(문자열) => 모두 대문자
    - lower(문자열) => 모두 소문자
 
    - length(문자열) => 문자열의 byte 길이
     - char_length(문자열) => 문자열의 길이
     
     -concat(문자열, 문자열,...) => 문자열과 문자열을 연결
         ex) SELECT CONCAT('I ', 'Love ', 'MySQL') ;

     
    - substring(문자열, 시작, 개수) => 문자의 일부분 추출
       EX) substring(문자열, INDEX) : 문자열에서 INDEX 부터 끝까지 추출(index는 1부터 시작)
           substring(문자열, INDEX, 개수) : 문자열에서 INDEX 부터 개수 까지 추출
           
           * substring()함수를   substr()로 해도 가능하다.substr함수는 substring 함수의 Alias로 완전히 동일한 기능.
			  sql 표쥰은 substring 사용

		
    - instr(문자열, 찾을문자열) => 문자열 내에서 특정 문자열이 처음 등장하는 위치(인덱스)를 반환
          * 만약, 찾는 문자열이 없으면 0 이다. 
          
          ex)  SELECT INSTR('Hello World', 'World');  -- 7
          
	- locate(찾을문자열, 문자열)  => 문자열 내에서 특정 문자열이 처음 등장하는 위치(인덱스)를 반환 
    
        주의 : instr 함수와 locate 함수는 거의동일하지만 매개변수 순서가 다르다.
          
	-position( '특정문자열'  in '문자열') : 특정문자까지의 문자열 길이을 반환
                                                        특정문자가 존재하지 않으면 0
                                                        
		*instr, locate, position은 모두 특정 문자열이 처음 등장하는 우치를 반환 한다.
		 instr, locate 는 mysql의 고유함수, postion 은 sql 표준
        
                                                        
        
        예)
		SELECT POSITION('d' IN 'abcd abcd abcd'); -- 결과 4
        SELECT POSITION('f' IN 'abcd abcd abcd'); -- 결과 0
        

     -left(문자열 , 길이) : 지정한 길이만큼 왼쪽부터 문자열 반환
     -right(문자열 , 길이) : 지정한 길이만큼 오른쪽부터 문자열 반환
      
        SELECT left('jang hee jung',  4); -- 결과 jang
        SELECT right('jang hee jung', 4); -- 결과 jung
     

 
    - ltrim() => 왼쪽 공백제거
    - rtrim()=> 오른쪽 공백제거
    - trim() => 양쪽공백제거

    - replace(문자열, 지정한문자 , 다른문자) : 지정한 문자를 다른 문자로 대체함.
				 ex) SELECT  REPLACE('jang hee jung', 'j', 'k'); 
                       주의 :  대소문자 가림.
                 
     -repeat('문자', 반복횟수) : 지정한 문자를 반복    
     
    - reverse() : 문자열을 거꾸로 만듦
    
    - strcmp(문자열, 문자열) : 문자열 비교 
					STRCMP 함수는 두 문자열을 비교하여 동일한지를 알려 줌
                   비교하는 두 문자열이 동일할 경우 0을, 다를 경우 -1을 반환함

 */
 
 -- 데이터베이스 선택alter
 
 --  문자열 비교
 select strcmp("jang heejung","jang Heejung2") ;


SELECT ENAME ,UPPER(ENAME), LOWER(ENAME),  LENGTH(ENAME), char_length(ENAME)
  FROM EMP;
  
select '장희정' , length('장희정') , char_length('장희정') ;


-- SUBSTRing(문자열, 시작, 개수) 문자열에서 시작부터 개수만큼 문자열 추출, 개수 생략하면 시작부터 끝까지 추출
SELECT JOB, substring(JOB, 1, 3) ,substring(JOB, 2, 3), substring(JOB, 3)
  FROM EMP;
  
-- SUBSTR(문자열, 시작, 개수):  음수를 주면 오른쪽 부터
SELECT JOB,  substring(JOB, -3 ,3 ) FROM EMP;

select 'ABCDEfgh', substring('ABCDEfgh', -1 ) , substring('ABCDEfgh', -1 , 3 ) ,
substring('ABCDEfgh', -3 , 3 ) ,substring('ABCDEfgh', -3 , 2 )
from dual;

-- INSTR(문자열, 문자): 문자열에서 두번째인수문자가 몇 번째에 있는 지 찾아주는 함수
SELECT 'ABCDE ABCDE ABCDE ABCDE', INSTR('ABCDE ABCDE ABCDE ABCDE', 'C') , INSTR('ABCDE ABCDE ABCDE ABCDE', 'g');

-- postion(찾는문자열 in 전체문자열): 문자열에서 두번째인수문자가 몇 번째에 있는 지 찾아주는 함수
SELECT 'ABCDE ABCDE ABCDE ABCDE', position('C' in  'ABCDE ABCDE ABCDE ABCDE') , position('g' in  'ABCDE ABCDE ABCDE ABCDE');
  
  
SELECT * FROM teacher;
  
  use exam;
-- ex) 이메일 주소에 @전까지 출력, @이후부터 출력 
select * from teacher;




SELECT left('jang hee jung',  4); -- 결과 jang
SELECT right('jang hee jung', 4); -- 결과 jung
  
-- ---------------------------------------------------------------------
/*
   날짜 함수
  
    CURRENT_date() :  서버의 현재 날짜
    CURRENT_TIME () : 서버의 현재 현재시간
    CURRENT_TIMESTAMP() : 서버의 현재 날짜와 시간
	now() : 서버의 현재 날짜와 시간
      
     date_add(날짜, INTERVAL 숫자  단위) : 날짜 더하기
     date_sub(날짜, INTERVAL 숫자  단위) : 날짜 빼기
	
        DATE_ADD , DATE_SUB 함수는 첫 번째 인수로 날짜 데이터를 입력하고,
		두 번째 인자로 INTERVAL과 함께 더하거나 빼고자 하는 숫자 그리고 연, 월, 일 등의 단위를 넣음.
	    더하거나 빼는 숫자와 함께 사용하는 단위 - YEAR ,QUARTER ,  MONTH, DAY, WEEK, HOUR , MINUTE,SECOND , MICROSECOND
        
        ex)
           SELECT NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR);
           SELECT NOW(), DATE_ADD(NOW(), INTERVAL -1 YEAR);
           SELECT NOW(), DATE_SUB(NOW(), INTERVAL 1 YEAR), DATE_SUB(NOW(), INTERVAL -1 YEAR);

        
     datediff(시작날짜, 종료날짜) : 날짜 간의  일 수 차 를 구함 , 실행결과는 일수를 반환
          
     timestampdiff() : 일수가 아닌 연 또는 시간 등 다양한 단위로 확인하고 싶다면 TIMESTAMPDIFF 함수
				ex) SELECT DATEDIFF('2025-2-10', now()) ;
					SELECT TIMESTAMPDIFF(year, '2025-1-10', now() );
					SELECT TIMESTAMPDIFF(MONTH, '2025-1-10', now() );
					SELECT TIMESTAMPDIFF(day, '2025-1-10', now() );
					SELECT TIMESTAMPDIFF(hour, '2025-1-10', now() );
					SELECT TIMESTAMPDIFF(minute, '2025-1-10', now() );
					SELECT TIMESTAMPDIFF(second, '2025-1-10', now() );
     
     dayname(날짜) : 지정한 날짜의 요일을 반환
                           SELECT DAYNAME(now());

     year(날짜) :  날짜에서 년도 추출
     month(날짜) :  날짜에서 월 추출
     week(날짜) :  날짜에서 주 추출
     day(날짜) :  날짜에서  일 추출
     
       ex) SELECT YEAR(now()), MONTH(now()), WEEK(now()), DAY(now());
     
     date_format() : DATE_FORMAT 함수는 날짜를 다양한 형식으로 표현해야 할 때 사용함.
						 나라마다 날짜를 표현하는 방식이 다르므로 날짜 형식으로 변환해야 할 때 DATE_FORMAT 함수가 필요함
                         
                         ex)  SELECT DATE_FORMAT( now() , '%m/%d/%Y');
                               SELECT DATE_FORMAT( now() , '%m/%d/%Y');
								SELECT DATE_FORMAT(now(), '%Y%m%d');
								SELECT DATE_FORMAT(now(), '%Y.%m.%d');
								SELECT DATE_FORMAT(now(), '%H:%i:%s');
                         
      
*/

-- 현재 날짜와 시간
select CURRENT_date() , CURRENT_TIME (), CURRENT_TIMestamp() , now();

SELECT NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR);
SELECT NOW(), DATE_ADD(NOW(), INTERVAL -1 YEAR);

SELECT NOW(), DATE_SUB(NOW(), INTERVAL 1 YEAR), DATE_SUB(NOW(), INTERVAL -1 YEAR);


 SELECT DATEDIFF('2025-2-10', now()) ;
SELECT TIMESTAMPDIFF(year, '2025-1-10', now() );
SELECT TIMESTAMPDIFF(MONTH, '2025-1-10', now() );
SELECT TIMESTAMPDIFF(day, '2025-1-10', now() );
SELECT TIMESTAMPDIFF(hour, '2025-1-10', now() );
SELECT TIMESTAMPDIFF(minute, '2025-1-10', now() );
SELECT TIMESTAMPDIFF(second, '2025-1-10', now() );


SELECT DAYNAME(now());
select hiredate, dayname(hiredate) from emp;

-- 우리가 만난지 얼마나?


-- 우리 과정 총 기간 일수


-- 100일은 언제?


-- 수료일 요일

 
 -- 현재 날짜에서 년 , 월, 주 ,일 
SELECT YEAR(now()), MONTH(now()), WEEK(now()), DAY(now());

-- DATE_FORMAT
SELECT DATE_FORMAT( now() , '%m/%d/%Y');
SELECT DATE_FORMAT(now(), '%Y%m%d');
SELECT DATE_FORMAT(now(), '%Y.%m.%d');
SELECT DATE_FORMAT(now(), '%H:%i:%s');

-- ----------------------------------------------------------------------------------------
  /*데이터타입 변환
     숫자를 날짜로 또는 날짜를 숫자로 변환하는 등 데이터를 다양한 형태로 변환해야 하는 경우가 있다.
   - cast() 
      : SELECT CAST(열 AS 데이터 유형) FROM 테이블;

   - convert()
      : SELECT CONVERT(열, 데이터 유형) FROM 테이블;

 */

 --  datetime을 정수형으로 변환
SELECT NOW(), CAST(NOW() AS SIGNED) , CAST(NOW() AS unSIGNED) ;

select cast(20250215231531 as date);
select cast(20250215231531 as datetime);

select cast('20250215231531' as date);
select cast('20250215231531' as datetime);

select cast('20250215231531' as char);
select cast('20250215231531' as char(4));
select cast('20250215231531' as char(8));


select convert(20250215231531 , date);
select convert(20250215231531 , datetime);

select convert('20250215231531' , date);
select convert('20250215231531' , datetime);

select convert('20250215231531' , char);
select convert('20250215231531' ,char(4));
select convert('20250215231531' , char(8));

-- ----------------------------------------------------------------------------------------
/* NULL에 관련된 함수 : IFNULL()  , COALESCE()
     - IFNULL(열, 대체할 값)
     - COALESCE(열 1, 열 2, …) : COALESCE는 NULL이 아닌 값이 나올 때까지 후보군의 여러 열을 입력할 수 있음

 */
 
 
-- COMM 이  NULL이면 100으로 표시


-- COALESCE함수 ; 가장 먼저 NULL이 아닌 것을 반환
SELECT ENAME, COMM, SAL, COALESCE(COMM, SAL, 50) RESULT
FROM EMP;

  
  SELECT COALESCE(100, NULL,200, 300) FROM DUAL; 
  SELECT COALESCE( NULL,100,200, 300) FROM DUAL;
  SELECT COALESCE(NULL, NULL,200, 300) FROM DUAL;
  SELECT COALESCE(NULL, NULL,NULL, 300) FROM DUAL;

-- ---------------------------------------------------------------------------------------

 /*
   조건에 따라 값을 다르게 반환
    
    1)  IF(condition, true_value, false_value)
     
         ex) SELECT IF(10 > 5, '참', '거짓') AS result;

   2) case [대상]
        when 조건1 then 문장
        when 조건2 then 문장
        when 조건3 then 문장
        ...
        else 문장
     end;
     
	ex) 
       SELECT name, 
			CASE  
				WHEN gender = 'M' THEN 'Male'
				WHEN gender =  'F' THEN 'Female'
				ELSE 'Other'
			END AS gender_text
		FROM employees;
      
       SELECT name, 
			CASE gender 
				WHEN 'M' THEN 'Male'
				WHEN 'F' THEN 'Female'
				ELSE 'Other'
			END AS gender_text
       FROM employees;
     
 */

-- 샘플 테이블 생성
CREATE TABLE REPORT(
  NAME VARCHAR(20)  PRIMARY KEY,
  BAN CHAR(1),
  KOR  int CHECK(KOR BETWEEN 0 AND 100),
  ENG  int CHECK(ENG BETWEEN 0 AND 100),
  MATH  int CHECK(MATH BETWEEN 0 AND 100)
);

SELECT * FROM REPORT;

-- 샘플레코드 등록
INSERT INTO REPORT VALUES('희정', 1 , 80,70, 90);
INSERT INTO REPORT VALUES('효리', 1 , 90,50, 90);

INSERT INTO REPORT VALUES('나영', 2 , 100,65, 85);
INSERT INTO REPORT VALUES('재석', 2 , 80,70, 95);
INSERT INTO REPORT VALUES('희선', 2 , 85,45, 80);

INSERT INTO REPORT VALUES('승기', 3 , 50,70, 70);
INSERT INTO REPORT VALUES('중기', 3 , 90,75, 80);
INSERT INTO REPORT VALUES('혜교', 3 , 70,90, 95);
INSERT INTO REPORT VALUES('미미', 3 , NULL,80, 80);


-- EX) 성적테이블에서 국어점수가 80이상이면 합격, 아니면 불합격  합격여부 필드를 만든다. - if 로사용


  -- EX) 성적테이블에서 국어점수가 80이상이면 합격, 아니면 불합격  합격여부 필드를 만든다. - CASE END 로사용
  
  

  /*EX)성적테이블에서 BAN이 1이면 'MAS과정', 2이면 'IOT과정', 
  3이면 'DESIGN과정' 이외는 'FULL STACK과정' 라는 과정명 필드를
 만든다.*/

-- CASE END 



/*
  Emp에서 sal가 3000 이상이면 고액년봉자 출력

*/
-- IF 이용

/*
EX) EMP테이블에서 DEPNO가 10 이면 관리부, 20이면 총무부, 30이면 영업부 
     이외의 값은 기타부 로 출력하고 컬럼명은 부서명 으로 한다.
     (CASE END)
   
*/



/*
ex)job이 manager인 경우 sal*0.1, ANALYST 인경우는   sal *0.2
     SALESMAN인 경우는 sal * 0.3을 구해서 성과급 필드를 만든다.
      (case end) 
*/



/*
ex) sal이 2000이하이면 '저소득층'
      sal이 2001 ~ 4000사이면 '중산층'
      sal이 4001 이상이면 '고소득층'  구하여 등급 별칭 해준다.
      (case end) 
*/


-- -------------------------------------------------------------------------------------
/*
   숫자 관련 함수
   - round(숫자, 자리수)=> 반올림
   
   - ceiling(숫자) => 올림 한 후 정수반환
   - floor(숫자)=>내림 한 후 정수 반환
  
   - mod(나누어지는수, 나누는 수) : 나먼지 연산자 , %와동일
   
 */
  
-- ROUND
SELECT 231.45136, ROUND(231.45136,2), ROUND(231.45136 , -2), ROUND(231.45136, 0) ;

SELECT SAL, MOD(SAL, 2) FROM EMP;

-- ----------------------------------------------------------------------------------------------
/*
  집계함수
     - sum(컬럼명) => 합계
     - avg(컬럼명) => 평균(null값은 제외하고 나눔)
     - max(컬럼명) => 최대값
     - min(컬럼명) => 최소값
     
     - count(컬럼명) => 총 레코드수(null값은 제외함)
     - count(*) => null을 포함한 총 레코드수
     
 
*/

select * FROM REPORT;

CREATE TABLE REPORT(
 NAME VARCHAR(20)  PRIMARY KEY,
 BAN CHAR(1),
 KOR INT CHECK(KOR BETWEEN 0 AND 100),
 ENG INT CHECK(ENG BETWEEN 0 AND 100),
 MATH INT CHECK(MATH BETWEEN 0 AND 100)
);

SELECT * FROM REPORT;

-- 샘플레코드
INSERT INTO REPORT VALUES('희정', 1 , 80, 70,90);
INSERT INTO REPORT VALUES('효리', 1 , 90, 50,90);

INSERT INTO REPORT VALUES('나영', 2 , 100, 65,85);
INSERT INTO REPORT VALUES('재석', 2 , 80, 70, 95);
INSERT INTO REPORT VALUES('희선', 2 , 85, 45,80);

INSERT INTO REPORT VALUES('승기', 3 , 50, 70,70);
INSERT INTO REPORT VALUES('중기', 3 , 90, 75,80);
INSERT INTO REPORT VALUES('혜교', 3 , 70, 90,95);
INSERT INTO REPORT VALUES('미나', 3 , NULL, 80,80);


SELECT * FROM REPORT;

-- 개인별 국어, 영어, 수학의 합계,평균을 검색해보자.


--  국어총점, 국어평균을 검색해보자.


-- 일반컬럼과 집계함수를 SELECT절에 함께 쓸수 있을까?? -> 안된다!!!



-- 국어점수의 최대, 최소, 전체학생수를 검색해보자.

  
-- 수학점수 최대, 최소, 학생수 



-- 국어점수의 총점, 평균, NULL을 0으로 변경해서 평균 검색해보자 
-- AVG()함수는 NULL을 제외한 레코드수로 평균을 구한다. 


-- 일반컬럼과 집계함수를 함께 검색하려면 GROUP BY 절을 이용한다.
-- 반별 국어 최대, 최소 총점 평균 인원수 - GROUP BY절에 나온 컬럼은 SELECT절에 집계함수와 함게 사용가능


-- KOR의 점수가 70이상인 학생들의 반별 국어 최대, 최소 총점 평균 인원수

 
-- KOR의 평균이 80 이상인 학생들의 반별 국어 최대, 최소 총점 평균 인원수 


/*
  중요!!
 SELECT   5)
 FROM     1)
 WHERE    2)
 GROUP BY 3)
 HAVING   4)
 ORDER BY 6)

*/


/*
 ROLLUP - 부분합과 총합을 구하는 함수
               GROUP BY 문과 ROLLUP함수 조합
               
			ex) GROUP BY 열이름 with ROLLUP
*/
SELECT BAN , SUM(KOR) 총점
FROM REPORT
GROUP BY BAN;

SELECT BAN , SUM(KOR) 총점
FROM REPORT
GROUP BY ROLLUP(BAN); -- 전체소계


SELECT BAN , SUM(KOR) 총점
FROM REPORT
GROUP BY  BAN  WITH ROLLUP; -- 전체소계


SELECT DEPTNO, SUM(SAL), COUNT(SAL)
FROM EMP
GROUP BY DEPTNO;

-- 부서별
SELECT DEPTNO, SUM(SAL), COUNT(SAL)
FROM EMP
GROUP BY ROLLUP(DEPTNO);

-- 직무별
SELECT JOB, SUM(SAL), COUNT(SAL)
FROM EMP
GROUP BY ROLLUP(JOB);


SELECT BAN , SUM(KOR) 총점
FROM REPORT
WHERE KOR >=70
GROUP BY rollup(BAN); -- 소계 + 총계



CREATE TABLE MONTHLY_SALES( -- 월별매출
  GOODS_ID VARCHAR(5), -- 상품아이디
  MONTH VARCHAR(10), -- 월
  COMPANY VARCHAR(20), -- 회사
  SALES_AMOUNT int -- 매출금액
);


INSERT INTO MONTHLY_SALES VALUES('P01','2023-01', '롯데', 15000);
INSERT INTO MONTHLY_SALES VALUES('P01','2023-02', '롯데', 25000);

INSERT INTO MONTHLY_SALES VALUES('P02','2023-01', '삼성', 8000);
INSERT INTO MONTHLY_SALES VALUES('P02','2023-02', '삼성', 12000);


INSERT INTO MONTHLY_SALES VALUES('P03','2023-01', 'LG', 8500);
INSERT INTO MONTHLY_SALES VALUES('P03','2023-02', 'LG', 13000);

SELECT * FROM MONTHLY_SALES;

-- 상품아이디별  SALES_AMOUNT의 총합 
SELECT GOODS_ID , SUM(SALES_AMOUNT)
FROM MONTHLY_SALES
GROUP BY GOODS_ID; 


SELECT GOODS_ID , SUM(SALES_AMOUNT)
FROM MONTHLY_SALES
GROUP BY ROLLUP(GOODS_ID); -- 총계 함께 출력 


-- 월별 SALES_AMOUNT의 총합 
SELECT MONTH , SUM(SALES_AMOUNT)
FROM MONTHLY_SALES
GROUP BY MONTH;

SELECT MONTH , SUM(SALES_AMOUNT)
FROM MONTHLY_SALES
GROUP BY ROLLUP(MONTH);


SELECT GOODS_iD, MONTH , SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY GOODS_iD,MONTH;


SELECT GOODS_iD, MONTH , SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY ROLLUP(GOODS_iD,MONTH); -- ROLLUP 첫번째 나온 컬럼을 기준으로 소계, 전체 (인수의 순서가 중요)

SELECT MONTH , GOODS_iD  , SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY ROLLUP(MONTH , GOODS_iD);


SELECT JOB, SUM(SAL), MAX(SAL), MIN(SAL), AVG(SAL) , COUNT(SAL)
FROM EMP
GROUP BY JOB 
ORDER BY JOB;

SELECT JOB, SUM(SAL), MAX(SAL), MIN(SAL), AVG(SAL) , COUNT(SAL)
FROM EMP
GROUP BY ROLLUP(JOB) 
ORDER BY JOB;



SELECT JOB,DEPTNO, SUM(SAL), MAX(SAL), MIN(SAL), AVG(SAL) , COUNT(SAL)
FROM EMP
GROUP BY JOB, DEPTNO 
ORDER BY JOB;

SELECT DEPTNO,JOB, SUM(SAL), MAX(SAL), MIN(SAL), AVG(SAL) , COUNT(SAL)
FROM EMP
GROUP BY DEPTNO , JOB 
ORDER BY JOB;

SELECT DEPTNO,JOB, SUM(SAL), MAX(SAL), MIN(SAL), AVG(SAL) , COUNT(SAL)
FROM EMP
GROUP BY ROLLUP(DEPTNO , JOB) 
ORDER BY JOB;


-- 순위
SELECT row_number()  OVER(ORDER BY SAL) , EMPNO, ENAME, JOB, SAL  FROM EMP;
SELECT RANK()  OVER(ORDER BY SAL) , EMPNO, ENAME, JOB, SAL  FROM EMP;
SELECT dense_rank()  OVER(ORDER BY SAL) , EMPNO, ENAME, JOB, SAL  FROM EMP;


SELECT DEPTNO , JOB , COUNT(*), MAX(SAL) 
FROM EMP
group by DEPTNO , JOB;

SELECT ROW_NUMBER() OVER(  ORDER BY DEPTNO) , DEPTNO , JOB , COUNT(*), MAX(SAL) 
FROM EMP
group by DEPTNO , JOB;

SELECT ROW_NUMBER() OVER( partition by DEPTNO ORDER BY JOB DESC) , DEPTNO , JOB , COUNT(*), MAX(SAL) 
FROM EMP
group by DEPTNO , JOB;


-- MySQL에서 ROW_NUMBER()를 사용하여 특정 범위의 레코드 조회
SELECT row_number()  OVER(ORDER BY SAL)  as no , EMPNO, ENAME, JOB, SAL  
FROM EMP
where no <= 3; -- 에러 

-- 서브쿼리 이용


-- limit 이용











  

