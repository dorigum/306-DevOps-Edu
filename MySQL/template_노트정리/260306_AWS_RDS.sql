SELECT @@time_zone, NOW();

use AWS_RDS_DB;

CREATE TABLE test (
    id INT AUTO_INCREMENT,
    content VARCHAR(255) DEFAULT NULL,
    reg_date DATETIME DEFAULT NOW(),
    PRIMARY KEY (id)
);

insert into test(content) values ('RDS 세팅 성공!');
-- delete  from test;
select * from test;