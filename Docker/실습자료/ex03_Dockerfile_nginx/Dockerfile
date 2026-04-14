# nginx 공식 이미지를 기반으로 사용
FROM nginx 

# 작업 디렉토리를 nginx의 기본 html 루트로 설정
WORKDIR /usr/share/nginx/html 

# images라는 서브 디렉토리를 생성 (/usr/share/nginx/html/images 디렉토리 생성)
RUN mkdir images 

# 로컬의 docker.png 파일을 컨테이너 내부 images 폴더로 복사
COPY docker.png images/docker.png 

# 로컬의 index.html 파일을 컨테이너의 작업 디렉토리(/usr/share/nginx/html)로 복사
COPY index.html . 

# 컨테이너의 80번 포트를 외부에 공개 (기본 HTTP 포트)
EXPOSE 80  

# nginx를 포그라운드(백그라운드 아님)로 실행하여 컨테이너가 계속 유지되도록 함
 ENTRYPOINT ["nginx", "-g", "daemon off;"]  
