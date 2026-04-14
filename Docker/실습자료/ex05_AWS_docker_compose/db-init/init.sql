
use web_basic;

CREATE TABLE IF NOT EXISTS todo_list(
   id int primary key auto_increment,
   done char(1) default 0,
   content varchar(100),
   reg_date datetime default now()
);

insert into todo_list(content) value('I will study for Docker');
insert into todo_list(content) value('I will hang out with my friends');


