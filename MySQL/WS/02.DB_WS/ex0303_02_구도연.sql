-- WS01 users테이블을 생성한 데이터베이스에서 작업한다.
USE ex0303;

select * from users;

insert into users (user_seq, name, email, phone) values (444, '사길동', 'sa@gildong@com', '010-4444-4444');
insert into users (user_seq, name, email, phone) values (555, '오길동', 'o@gildong@com', '010-5555-5555');

CREATE TABLE account
( 
    account_seq   INT PRIMARY KEY,
    account_number VARCHAR(50) not null,
    balance       INT,
    user_seq      INT,
    foreign key(user_seq) references users(user_seq)
);

select * from account;
delete from account;
drop table account;

insert into account (account_seq, account_number, balance, user_seq) values (50, '00500505005005', 3000, 222);
insert into account (account_seq, account_number, balance, user_seq) values (10, '00100101001001', 1000, 111);
insert into account (account_seq, account_number, balance, user_seq) values (30, '00300303003003', 5000, 222);
insert into account (account_seq, account_number, balance, user_seq) values (70, '00700707007007', 7000, 444);
insert into account (account_seq, account_number, balance, user_seq) values (40, '00400404004004', 4000, 222);
insert into account (account_seq, account_number, balance, user_seq) values (60, '00600606006006', 2000, 222);
insert into account (account_seq, account_number) values (80, '00800808008008');
insert into account (account_seq, account_number, balance, user_seq) values (20, '00200202002002', 6000, 111);

SELECT * FROM USERS;
SELECT * FROM ACCOUNT;



-- 1번
select * from account where user_seq = 111;

-- 2번
select * from account where balance < 5000;

-- 3번
select * from account where balance between 5000 and 10000;

-- 4번
select * from account where account_number like '%4%';

-- 5번
select * from users where name like '삼%';

-- 6번
select distinct user_seq from users where user_seq is not null;

-- 7번
select * from account where balance is null;

-- 8번
select * from account where user_seq is not null;

-- 9번
select * from account where user_seq is not null and balance <= 4000;

-- 10번
select * from account order by user_seq;

-- 11번
select * from account order by user_seq, balance desc;

-- 12번
select * from account where balance is null;
select *, ifnull(balance, 0) from account;
select a.*, ifnull(balance, 0) from account a;

-- 13번
select email from users;
select substring_index(email, '@', 1) as email_id from users;
-- select user_seq from account;
select user_seq, email, instr(email, '@') from users;
select user_seq, email, locate('@', email) from users;
select user_seq, email, position('@' in email) from users; -- 표준

-- select user_seq, email, substring(email, 1 position(('@' in email) -1 ) email_id, phone from users;

use ex0303; -- ex0304 과제 내용 추가
select * from users;
select * from account;

-- 14번
select replace(phone, '-', '') as phone_short from users;

-- 15번
select count(user_seq) from account where user_seq = 222;

-- 16번
select sum(balance) as balance_sum from account;

-- 고객별 잔액의 총합 구하기
select user_seq, sum(balance) as 전체계좌잔고, count(user_seq)
from account
where user_seq is not null
group by user_seq;

-- 17번
select min(balance) as balance_min, max(balance) as balance_max from account;

-- 18번
select user_seq , count(*) as user_account_cnt
from account
where user_seq is not null
group by user_seq;

-- 19번
select user_seq, sum(balance) as user_balance_sum
from account
where user_seq is not null
group by user_seq;

-- 20번
select user_seq, sum(balance) as user_balance_sum
from account
where user_seq is not null
group by user_seq
having user_balance_sum <= 10000;