drop table Electronics;

create table Electronics(
	model_num varchar(15) primary key, -- 상품번호
	model_name varchar(20) not null,-- 상품이름
	price int,-- 가격
	description varchar(100), -- 설명
	password varchar(20) not null,-- 비밀번호
	writeday datetime  not null, -- 작성일
	readnum int, -- 조회수
	 fname varchar(50), -- 파일이름
     fsize int -- 파일용량
);


insert into Electronics values('NT900X4D-A68','삼성샌스',1300000,'Windows 8','1111',now(),0,null,0); -- 
insert into Electronics values('SHV-E250S','Galaxy Note II',1000000,'Wi-Fi bluetooth 4.0','1111',now(),0,null,0);
insert into Electronics values('NT900X4D-A99S','삼성샌스',1700000,'Windows 8','1111',now(),0,null,0);

select * from Electronics;

 commit;
 
 /*
 OFFSET으로 특정 구간의 데이터 조회하기  
ORDER BY로 데이터를 정렬한 다음,  상위나 하위가 아닌 특정 구간의 데이터를 조회해야 하는 경우도 있는데 이럴 때는 LIMIT ~ OFFSET을 사용 할 수 있다.
OFFSET에 지정한 행 개수만큼 건너뛰고 LIMIT에 지정한 개수만큼의 상위 데이터를 출력한다.
 
 -- 아래 쿼리는 SAL 열을 기준으로 내림차순 뒤, 3개를 건너 뛰고 4개의데이터를 조회  
SELECT * FROM EMP ORDER BY SAL DESC  LIMIT 4 OFFSET  3;
*/
SELECT * FROM Electronics ORDER BY writeday DESC LIMIT 4 OFFSET 0;


insert into Electronics values('A10','선풍기1',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A11','선풍기2',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A12','선풍3',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A13','선풍기4',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A14','선풍기5',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A15','선풍기6',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A16','선풍기7',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A17','선풍기8',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A18','선풍기9',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A19','선풍기10',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A20','선풍기11',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A21','선풍기12',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A22','선풍기13',250000,'시원해요','1111',now(),0,null,0);
insert into Electronics values('A23','선풍기14',250000,'시원해요','1111',now(),0,null,0);



 -- ------------------------------------------------
 -- 댓글테이블 작성하기 ( 부모글 한개에 여러개의 댓글을 작성할수 있다)
create table replies(
  reply_num int primary key auto_increment,
  reply_content varchar(100) not null,
  reply_regdate datetime,
  parent_model_num varchar(15)  ,
  foreign key(parent_model_num)  references electronics(model_num)
);



-- 샘플레코드 추가
select * from electronics order by writeday desc;
-- ex)  NT900X4D 글에 댓글 3개
insert into replies(reply_content,reply_regdate , parent_model_num) values( 'NT900X4D-A68 첫번째 댓글', now() , 'NT900X4D-A68');
insert into replies(reply_content,reply_regdate , parent_model_num)  values( 'NT900X4D-A68 두번째 댓글', now() , 'NT900X4D-A68');
insert into replies(reply_content,reply_regdate , parent_model_num)  values( 'NT900X4D-A68 세번째 댓글', now() , 'NT900X4D-A68');

-- ex) NT900X4D-A99S 글에 댓글 2개 
insert into replies(reply_content,reply_regdate , parent_model_num) values('NT900X4D-A99S 첫번째 댓글', now() , 'NT900X4D-A99S');
insert into replies(reply_content,reply_regdate , parent_model_num) values( 'NT900X4D-A99S 두번째 댓글', now() , 'NT900X4D-A99S');

commit;

select * from replies;

-- ex) 부모글의 댓글 정보 조회
select * from replies where parent_model_num='NT900X4D-A68'
 

