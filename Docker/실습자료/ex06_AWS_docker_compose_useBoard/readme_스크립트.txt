
# AWS EC2 unbuntu 생성

#MobaXterm 다운로드 설치

# ec2생성할 때 만든 mykey.pem 을 이용해서 ec로 mobaxterm을 이용해서 접속한다.
----------------------------------------------------------------
#최신 패키지를 설치하기 위해 패키지 목록을 업데이트 
sudo apt update

#Docker를 설치하려면 몇 가지 필수 패키지를 먼저 설치해야 한다.
sudo apt install apt-transport-https ca-certificates curl software-properties-common

#Docker의 공식 GPG 키를 추가
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo tee /etc/apt/trusted.gpg.d/docker.asc

#Docker의 공식 저장소를 APT 소스에 추가
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

#새로운 Docker 저장소가 추가되었으므로, 패키지 목록을 다시 업데이트
sudo apt update

#Docker를 설치
sudo apt install docker-ce

#Docker가 자동으로 시작되지만, 확인을 위해 서비스를 시작할 수 있다.
sudo systemctl start docker

#Docker 서비스가 정상적으로 실행되고 있는지 확인
sudo systemctl status docker

#Docker 설치가 완료되었는지 확인하려면 버전을 확인
docker --version
---------------------------------------------------------------
#현재 사용자로 root 권한을 부여받은 후, root 사용자로 전환한다
sudo su

# 폴더생성 
mkdir docker-test
mkdir -p ./upload     -p의 의미는 디렉토리가 이미 있으면 그냥 넘어간다

# mobaxteram 에서 SFTP를 이용하여 드래그 앤 드롭으로 로컬pc의 파일을  aws의 docker-test폴더에    복사한다. 

#만약 권한 문제가 발생하면
sudo chown ubuntu:ubuntu /home/ubuntu/docker-test


#docker-test폴더로 이동한다.
cd docker-test

# Docker Compose V2부터는 docker-compose 대신 docker compose
 docker compose version

#docker-compose실행하기
docker compose up --build -d

#docker-compose 삭제
docker compose down -v





