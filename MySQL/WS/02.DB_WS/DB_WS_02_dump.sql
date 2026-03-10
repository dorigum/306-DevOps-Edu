-- WS01 users테이블을 생성한 데이터베이스에서 작업한다.
USE ex0822;

select * from users;

insert into users (user_seq, name, email, phone) values (444, '사길동', 'sa@gildong@com', '010-4444-4444');
insert into users (user_seq, name, email, phone) values (555, '오길동', 'o@gildong@com', '010-5555-5555');

CREATE TABLE account
( 
    account_seq   INT PRIMARY KEY,
    account_number VARCHAR(50) not null,
    balance       INT,
    user_seq      INT ,
    foreign key(user_seq) references users(user_seq)
);

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










