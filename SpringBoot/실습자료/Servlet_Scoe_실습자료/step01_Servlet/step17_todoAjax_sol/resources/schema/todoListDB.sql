create database web_basic;
use web_basic;

create table todo_list(
   id int primary key auto_increment,
   done char(1) default 0,
   content varchar(100),
   reg_date datetime default now()
);

insert into todo_list(content) value('web ajax 공부하기');
insert into todo_list(content) value('친구랑 수다하기');
insert into todo_list(content) value('부모님께 전화 드리기');

select id,done,content, reg_date from todo_list;

commit;





