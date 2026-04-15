FROM tomcat:10-jdk17

# 기본 directory설정(cd /usr/local/tomcat/webapps/ 동일)
WORKDIR /usr/local/tomcat/webapps/

# 기존 /usr/local/tomcat/webapps/ROOT 폴더 제거
RUN rm -rf ./ROOT && apt update && apt install -y git

COPY ./ROOT.war ./ROOT.war

# 컨테이너가 열릴 포트를 설정한다.
EXPOSE 8080
