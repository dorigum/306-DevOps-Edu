docker-compose.yml을 사용하면 WAR 파일을 컨테이너에 자동으로 복사 할수 없기 때문에, ROOT.war를 복사하려면 
Dockerfile을 이용한다.

#db-init/init.sql파일 준비
use web_basic;

CREATE TABLE IF NOT EXISTS todo_list(
   id int primary key auto_increment,
   done char(1) default 0,
   content varchar(100),
   reg_date datetime default now()
);

insert into todo_list(content) value('web ajax 공부하기');
insert into todo_list(content) value('친구랑 수다하기');
insert into todo_list(content) value('부모님께 전화 드리기');

------------------------------------------------
#Dockerfile
FROM tomcat:10
COPY ROOT.war /usr/local/tomcat/webapps/

-----------------------------------------------------------
#docker-compose.yml
version: '3.8'

services:
  mysql:
    image: mysql:8.4
    container_name: my_sql
    environment:
      MYSQL_ROOT_PASSWORD: "1234"
      MYSQL_DATABASE: "web_basic"
      TZ: "Asia/Seoul"
    volumes:
      - mysql_vol:/var/lib/mysql
      - ./db-init:/docker-entrypoint-initdb.d  # ← SQL 스크립트 자동 실행 경로
      - /etc/localtime:/etc/localtime:ro
    ports:
      - "3306:3306"
    networks:
      - web_net

  tomcat:
    build: .
    container_name: my_tomcat
    ports:
      - "80:8080"
    networks:
      - web_net
    depends_on:
      - mysql

volumes:
  mysql_vol:
  mytomcat_vol:

networks:
  web_net:


-------------------------------------------
cmd창에서 
#yaml파일에 정의 된 서비스 실행하기전에 이미지빌드(--build),  컨테이너 생성과 실행(up) , 백그라운드실행(-d)
docker-compose up --build -d

# 컨테이너 와 네트워크를 종료하고 삭제한다.
docker-compose down 


 컨테이너 와 네트워를 종료하고 삭제,  볼률 항목 삭제(-v) 한다.
docker-compose down -v

