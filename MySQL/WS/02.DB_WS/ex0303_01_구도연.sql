USE ex0303;

-- 1-1번
create table users (
	user_seq int primary key, -- 1-2번
    name varchar(10),
    email varchar(30) not null unique, -- 1-3번
    phone varchar(13),
    is_sleep char(1) default 'N' -- 1-4번
);

desc users;

drop table users;

-- 1-2번
alter table users add primary key(user_seq);

-- 1-3번
alter table users add unique(email);
-- alter table users modify unique(email);

-- 1-4번
-- alter table users modify is_sleep;

-- 2번
insert into users(user_seq, name, email, phone) values (111, "홍길동", "hong@gildong.com", "010-1111-1111");
insert into users(user_seq, name, email, phone) values (222, "이길동", "lee@gildong.com", "010-2222-2222");
insert into users(user_seq, name, email, phone) values (333, "삼길동", "sam@gildong.com", "010-3333-3333");

-- 3번
select * from users;

delete from users;

-- 4번
update users set is_sleep = 'Y' where user_seq = 222;

-- 5번
insert into users(user_seq, name, email, phone) values (222, '이길동2', 'lee2@gildong.com', '010-2222-2222');
-- Error Code: 1062. Duplicate entry '222' for key 'users.PRIMARY'

-- 6번
insert into users(user_seq, name, email, phone) values (2222, '이길동2', 'lee@gildong.com', '010-2222-2222');
-- Error Code: 1062. Duplicate entry 'lee@gildong.com' for key 'users.email'

-- 7번
insert into users(user_seq, name, phone) values (2222, '이길동2', '010-2222-2222');
-- Error Code: 1364. Field 'email' doesn't have a default value

-- 8번
insert into users(user_seq, name, phone) values (2222, '이길동2', 'lee2@gildong.com', '010-2222-2222');

-- 9번
select * from users where user_seq = 2222;
delete from users where user_seq = 2222;
select * from users;

-- 10번
drop table users;