create table bank (
   account varchar(10) primary key, 
   balance int 
);

insert into bank values('A01',1000);
insert into bank values('A02',500);
 
select * from bank;
commit;

DELETE FROM bank;

-- 계좌 이체
-- 1. 인출한다.
update bank set balance = balance-인출금액 where account = 출금계좌번호;

-- 2. 인출한 금액만큼 입금한다.
update bank set balance = balance+인출금액 where account =입금계좌번호;

select balance from bank where account = 'A02';
select balance from bank where account = 'A01';