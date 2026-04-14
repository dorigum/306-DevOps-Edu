
use web_basic;

CREATE TABLE IF NOT EXISTS  Electronics(
	model_num varchar(15) primary key, 
	model_name varchar(20) not null,
	price int,
	description varchar(100), 
	password varchar(20) not null,
	writeday datetime  not null, 
	readnum int, 
	fname varchar(50), 
        fsize int 
);


insert into Electronics values('NT900X4D-A68','samsung',1300000,'Windows 8','1111',now(),0,null,0); 
insert into Electronics values('SHV-E250S','Galaxy Note II',1000000,'Wi-Fi bluetooth 4.0','1111',now(),0,null,0);
insert into Electronics values('NT900X4D-A99S','samsung',1700000,'Windows 8','1111',now(),0,null,0);


CREATE TABLE IF NOT EXISTS  replies(
  reply_num int primary key auto_increment,
  reply_content varchar(100) not null,
  reply_regdate datetime,
  parent_model_num varchar(15)  ,
  foreign key(parent_model_num)  references Electronics(model_num)
);

insert into replies(reply_content,reply_regdate , parent_model_num) values( 'NT900X4D-A68 first reply', now() , 'NT900X4D-A68');
insert into replies(reply_content,reply_regdate , parent_model_num)  values( 'NT900X4D-A68 second reply', now() , 'NT900X4D-A68');
insert into replies(reply_content,reply_regdate , parent_model_num)  values( 'NT900X4D-A68 third reply', now() , 'NT900X4D-A68');

insert into replies(reply_content,reply_regdate , parent_model_num) values('NT900X4D-A99S first reply', now() , 'NT900X4D-A99S');
insert into replies(reply_content,reply_regdate , parent_model_num) values( 'NT900X4D-A99S second reply', now() , 'NT900X4D-A99S');


CREATE TABLE IF NOT EXISTS  users(
   user_id varchar(10) primary key,	
   pwd varchar(10), 
   name varchar(10)
);

insert into users values('jang', '1234', 'heejung');
insert into users values('lee', '1234', 'Lee GaHyun');