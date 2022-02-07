# 21일차 학습일지

## docker

### docker 설치
- 공식 사이트에 접속해서 사용 OS에 맞게 설치
- 또는 homebrew와 같이 패키지 관리자를 활용해서 설치

## mysql

### mysql server설치
- M1맥북 기준 doker에 mysql server 설치하기
```bash
docker search mysql
```
- 이미지 리스트를 검색
```bash
docker pull --platform linux/amd64 mysql-server:5.7
```
- 위의 명령어를 사용해서 mysql-server 이미지를 받기.
- 5.7버전을 사용할 예정이기 때문에 버전명을 명시해준다.
- M1맥북에서는 사용 platform명의 명시해 줘야한다.(은근 불편한게 많다)
```bash
docker images
```
- 다운 받은 이미지 확인

### mysql컨테이터 생성
```bash
docker run
-d 
-p 3306:3306 
-e MYSQL_ROOT_PASSWORD={password}
--name {containerName}
mysql/mysql-server:5.7 
--character-set-server=utf8mb4 
--collation-server=utf8mb4_unicode_ci
```
- 위의 명령어를 사용해서 실행
- -d: 백그라운드에서 실행
- -p: 포트포워딩 실제 내 서버로 들어온 3306포트는 도커의 3306포트로 보내겠다는 의미
- -e: 그외 옵션 mysql을 사용하기 위해 설정해줘야하는 옵션들
- MYSQL_ROOT_PASSWORD: mysql root비밀번호 설정
- name: 컨테이너이름 설정, 설정하지 않으면 랜덤으로 설정된다.
- mysql/mysql-server:5.7: 실행할 이미지 이름, 버전이 따로 있다면 버전까지 명시해줘야한다.
- character: 한국어 사용을 위한 설정

### 컨테이너 접속
```bash
docker exec -it {containerName} bash
```
- -it: interactive terminal 모드

### mysql 서버 접속
```bash
mysql -u root -p
```
- 명령어 입력 후 root password를 입력하여, root계정으로 접속

### DB사용자 생성 및 권한 설정
```bash
CREATE USER '{userName}' IDENTIFIED BY '{password}';
GRANT ALL PRIVILEGES ON *.* TO '{userName}';
flush privileges;
```
- 유저 생성 후 GRANT로 권한을 설정
- revoke를 사용해 권한을 회수 할 수도 있다.
- sql구문 관련은 검색 또는 찾아서 공부해보자.

### mysql GUI환경 접속 테스트
```bash
Server Host: localhost
port: 3306
username: {userName}
password: {password}
```
- M1맥북에서 mysql warkbanch사용에 제약이 많다.(오류가 엄청 많다)
- 예전부터 사용하고 있던 DBeaver를 활용할 예정 사용하기 쉽고, 좋다.

### mysql 테스트
```bash
show databases;
```
- 데이터 베이스 목록 조회
```bash
create database {dbName}
```
- 사용할 DB생성
- 테이블 생성 및 사용 쿼리문은 상황에 따라 매우 다양하므로, 필요에 따라 검색하거나 공부해서 사용하자.