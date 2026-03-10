create database ex0305;

use ex0305;

-- 1) 성별이 여자인 학생의 정보 검색
select  * from student where substring(수_주민등록번호, 8, 1) = '2';
select  * from student where substring(수_주민등록번호, 8, 1) in ('2', '4');

select  * from student where 수_주민등록번호 like '%-2%';
select  * from student where 수_주민등록번호 like '______-2______%';


-- 2) 생년월일이 1973년인 학생의 정보
select * from student where substring(수_주민등록번호, 1, 2) = '73';
select * from student where substring(수_주민등록번호, 1, 2) like '73%';
select * from student where concat('19', substring(수_주민등록번호, 1, 2)) = '1973';


-- 3) 성이 '홍' 이 아닌 강사의 정보
select * from teacher where substring(강사이름, 1, 1) != '홍';
select * from teacher where 강사이름 not like '홍';


-- 4) 강남구에 거주하는 강사의 정보
 select * from teacher where 주소 like '%강남구%';
 
 -- 5) Java를 강의하는 강사 정보 검색(서브쿼리 이용)
 select * from teacher;
 select * from subject;
 
	-- 1) Java 과목에 해당하는 수강 코드를 검색한다.
    select 수강코드 from subject where upper(과목) = upper('jAvA');
    
    -- 2) 검색된 수강 코드를 조건으로 사용한다.
    select * from teacher where 수강코드 =
    (select 수강코드 from subject where upper(과목) = upper('jAvA'));
    
    
-- 6) 주소가 NULL이 아닌 강사의 정보
select * from teacher where 주소 != null; -- X
select * from teacher where 주소 is not null;


-- 7) SQL 수업을 수강하고 있는 학생의 정보를 검색하는 뷰를 작성
-- 가상 테이블 : 보안, 복잡한 쿼리를 단순화
	-- 1) SQL 과목의 수강 코드를 구한다.
    -- 2) 수강 테이블에서 1)에 있는 수강코드를 사용하고 있는 수강생번호를 구한다.
    -- 3) 학생 테이블에서 2)에 있는 수강생번호와 같은 학생의 정보를 검색한다.
    
select * from student;
select * from sugang;
select * from subject;

	-- 서브 쿼리를 이용한 7번 문제
    -- 1) subject에서 SQL의 수강코드를 검색
    -- 2)
  --  select 수강코드 from subject where upper(과목) = upper
    
-- 3) student 테이블에서 2)에 있는 수강생번호를...

    -- 뷰로 만든다.
    create or replace view v_stu
    as
    select * from student
		where 수강생번호 = (select 수강생번호 from sugang
        where 수강코드 = (select 수강코드 from subject where 과목 = 'sql'));
    
    -- 뷰 검색
     select * from v_stu;
     
     
     -- 8) 강사가 담당하는 과목과 그 과목이 어느 강의실에서 진행되며,
     -- 총 수용 인원이 몇 명인지 검색(JOIN을 이용해 뷰 작성)
     
		select * from student;
		select * from room;
		select * from teacher;
		select * from sugangtb;

		-- 조인
        select * from teacher inner join subject using(수강코드)
					inner join sugangtb using(강사번호)
					inner join room using(강의실번호);
                    
		-- using 사용
        create or replace view v_teacher
        as
        select sugangtb.강사번호, 강사이름, 수강코드, 과목, 강의실번호, 수용인원
        from teacher inner join subject using(수강코드)
				inner join sugangtb using(수강코드)
				inner join room using(강의실번호);
                
		select * from v_teacher where 강사번호 = 2;
		select * from v_teacher where 수용인원 >= 30;
        
        -- 9) 학생이름, 주민번호, 성별 출력
        -- case end
        select 수_이름, 수_주민등록번호,
        case
			when substring(수_주민등록번호, 8, 1) in ('1', '3') then '남자'
			when substring(수_주민등록번호, 8, 1) in ('2', '4') then '여자'
		end as 성별
        from student;
        
        -- 10) teacher 강사이름을 기준으로 올림차순 정렬, 
        select row_number() over (order by  강사이름) as no,
        강사이름, 연락처, 주소 from teacher;
        
        select row_number() over(order by 강사이름) as no, 강사이름, 연락처, 주소
        from (select row_number() over(order by 강사이름) as no, 강사이름, 연락처, 주소
				 from teacher) as sub
		 where no < 3; -- O
	  -- where no < 3; -- 별칭은 조건절에서 사용 불가 -> 서브쿼리 사용