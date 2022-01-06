# Linux(리눅스)와 Unix

## 시작하기 전에.
- 함께 공부하는 동료가 야공만 만화를 추천해줘서 봤는데 재미있다. 간단하게 한번 보고 시작하는것도 좋겠다.
> <a href = https://www.ddanzi.com/ddanziNews/200047221>[과학]야밤의 공대생 만화26: 리눅스를 만든 자, 리누스 토르발스</a>

### 실습 운영체제
- Linux기반으로 되어있는 `Ununtu`를 사용해서 실습을 할 예정이다.
- 다운로드 과정은 여러 블로그를 보고 따라했습니다.
  - 참고로 M1 맥북이라 고생을 좀 했는데 이 블로그(<a href = https://webnautes.tistory.com/1580>Macbook M1에 Ubuntu ARM64 설치하는 방법</a>)를 보고 따라했더니 문제없이 설치가 끝났습니다.
- `Ubuntu Desktop`을 설치하자
    ```bash
    sudo apt install
    sudo apt update
    sudo apt install tasksel
    sudo tasksel install ubuntu-desktop
    ```
- 설치가 끝나면 `sudo tasksel install ubuntu-desktop`으로 설치가 제대로 됬는지 확인
- `sudo reboot`으로 리붓하면 끝!
- 우분투 설치 후 터미널 실행 화면

    <img src="https://user-images.githubusercontent.com/78953393/148365671-1c4e9372-7410-49c8-8329-d9c49bc6791b.png" height="60%" width="40%">

## 운영체제
- 운영체제란?
  - 기기에서 프로그램이 실행될 수 있도록 도와주는 역할한다.
- 운영체제의 종류로는 윈도우, macOS, 안드로이드, IOS, Linux, ubuntu, RedHat, ChromeOS 등등 수많은 운영체제가 있다.
- 위의 많은 운영체제의 시작점이 `UNIX`다.
- 오늘은 `Linux`와 `UNIX`에 대해 공부 할 예정이다.

## UNIX

### UNIX란?
> 유닉스(영어: Unix)는 교육 및 연구 기관에서 즐겨 사용되는 범용 다중 사용자 방식의 대화식, 시분할처리 시스템용 운영 체제이다. 1970년대 초반 벨 연구소 직원인 켄 톰슨, 데니스 리치 등이 소형 컴퓨터용으로 처음 개발하였다. 오늘날의 유닉스 시스템은 여러 회사들과 비영리 단체들이 이 커널로 활용하여 다양한 운영체제를 개발하고 있다.(위키백과)
- 유닉스 및 유닉스계열 구조도
  
<img src="https://user-images.githubusercontent.com/78953393/148364894-ee11047f-ccd1-4281-bb29-12d9a29e4499.png" height="60%" width="40%">
(이미지 출처: 위키백과)

- 다양한 운영체제가 Unix에서 파생되어 만들어진걸 볼 수 있다.
- 친숙한 운영체제도 보이고, 완전 처음들어 보는 운영체제도 보인다.(신기)

## Linux

### Linux란?
> 리눅스(Linux)는 1991년 9월 17일 리누스 토르발스가 처음 출시한 운영 체제 커널인 리눅스 커널에 기반을 둔 오픈 소스 유닉스 계열 운영 체제 계열이다.(위키백과)
- 오픈 소스의 형태로 이루어져있다.
- 리눅스 시스템은 임베디드 시스템에서 사실상 모든 슈퍼컴퓨터에 이르기까지 컴퓨팅 전반에 사용되며, 대중적인 LAMP 애플리케이션 스택 등 서버 설치본에 자리잡았다.

## Ubuntu원격 접속
- 시간을 한국 표준시(KST)로 변경하자.
  - 명령어: `sudo timedatectl set-timezone Asia/Seoul`
    - sudo: `sudo (superuser do)`현재 계정에서 `root`권한을 이용하여 명령어를 실행할 때 사용
    - timedatectl: 현재 설정되어 있는 `date` 확인
    - set-timezone Asia/Seoul: 설정되어있는 `date`를 `KST`(한국표준시)로 변경
- 시간 변경 화면

    <img src="https://user-images.githubusercontent.com/78953393/148365673-a1626148-7868-4ea3-9d5d-05834468bb65.png" height="60%" width="40%">

- 모르는 명령어가 있으면 앞에`man`을 쓰고 뒤에 알고싶은 명령어를 입력하면 해당 명령어의 메뉴얼을 볼 수 있다.(물론 영어로..)
  - 예: `man sudo`

### ssh로 ubuntu 연결하기
1. `ubuntu`와 `mac` 터미널에 `ssh`를 설치해준다.
   - 명령어: `sudo apt-get install ssh`

    <img src="https://user-images.githubusercontent.com/78953393/148366531-2a409965-5b2b-41d0-b7e1-76fd4b8e64cd.png" height="60%" width="40%">

1. `ubuntu`에서 `ssh`실행
   - 명령어: `sudo service start`

    <img src="https://user-images.githubusercontent.com/78953393/148367061-ee09b2ff-c387-4c31-b630-0560de7ceabe.png">

1. `ifconfig`를 사용해서 `ip`주소 확인
   - 오류: `ifconfig`명령어를 사용하기 위해서는 `net-tools`을 먼저 설치해야한다.
   - net-tools: 네트워크툴 패키지로 `ifconfig`나 `netstat`명령어를 사용하기 위해 필요하다.
   - 명령어: `sudo apt install net-tools`
   - 명령어: `ifconfig`

    <img src="https://user-images.githubusercontent.com/78953393/148366273-e1c5d787-ce58-4e3a-8782-8ac5f57bf87d.png">

1. 맥 터미널에서 `ssh`접속

    <img src="https://user-images.githubusercontent.com/78953393/148367111-16637ab9-b9bb-4a1a-bdc6-1d5136576bb4.png" height="60%" width="40%">

   - 명령어: `ssh {username}@{ipaddress}`
   - 오류: `ssh: connect to host xxxxx port 22: Operation timed out`
   - 검색해보니 방화벽 또는 포트 22에 문제가 있어 접속이 안되는거라고 한다.(쉬운게 없다..)  

1. `ubuntu`쪽 방화벽 확인 (출처: <a href = "https://mia-dahae.tistory.com/62">우분투 방화벽 관련</a>)
   - 명령어
    ```bash
    sudo ufw status # Status: inactive(비활성화)
    sudo ufw allow ssh  # ssh 서버를 허락한다.
    sudo ufw default deny incoming # incoming 은 deny 하고 outgoing 은 allow한다.
    sudo ufw default allow outgoing
    sudo ufw enable            # ufw 를 enable 시킨다.
    sudo ufw status verbose   # ufw status 를 체크 
    ```
   - 오류: 똑같다.. 왜 안되는걸까 
2. `ip`문제??
   - 처음 우분투 설치할때 ip가 10.0.2로 시작하는 ip로 지정되어있었다.
     - 이게 공유기 때문에 이런 ip가 잡히는거라는데 재설치하니 ip가 192로 시작하는걸로 바뀌었다.
   - 바뀐 아이피로 접속하니 잘된다.. 설정은 처음 그대로 바뀐게 없는데 재설치하니 ip가 변경되었다.
   - 뭐가 문제인지 모르겠다ㅠㅠ(5시간동안... 이것만 했는데)

### 현재 사용자 계정 변경
- `su`: 현재 계정을 `로그아웃 하지 않고 다른 계정으로 전환`하는 명령어
    ```bash
    su # root 사용자로 변경한다.
    su {username} # 사용자를 `{username}`으로 변경한다.
    ```

### 리눅스 파일 사용 권한
- 실습을 위해 새로운 파일을 만들어보자
    ```bash
    geombong@geombong:~/backup$ echo hello ubuntu >> hello.txt
    geombong@geombong:~/backup$ ls
    hello.txt
    ```
- `echo {text} >> {filename.type}`: 파일 생성 명령어
    ```bash
    geombong@geombong:~/backup$ ls -l
    total 4
    -rw-rw-r-- 1 geombong geombong 13 Jan  6 18:01 hello.txt 
    # {파일 권한 내용} 숫자 {파일 소유자 이름} {파일 소유 그룹 이름}
    ```
- 파일에 대한 권한

  <img src="https://user-images.githubusercontent.com/78953393/148366914-a52aa02a-f16a-4d0c-a8a3-191bb064f7fe.png">

  - `ls -l`: 파일의 사용권한을 볼 수 있다.
  - `-rw-rw-r--`: 맨 앞에 `-`를 빼고 뒤에 3개씩 끊어서 권한을 나타낸다.
    - `rw-`: 파일 소유자의 권한으로 r(읽기)와 w(쓰기)가 권한이 있다.
    - `rw-`: 파일 소유그룹의 권한으로 r(읽기)와 w(쓰기)가 권한이 있다.
    - `r--`: 그 외 사용자 권한으로 r(읽기)만 가능하다.
    - 그외 `x` 도 있는데 x(실행)는 파일을 실행할 수 있는 권한을 표시한다.
- 파일의 사용 권한을 변경해보자
    ```bash
    geombong@geombong:~/backup$ chmod g+x hello.txt # 파일 소유 그룹에 x(실행)의 권한을 부여
    geombong@geombong:~/backup$ ls -l               # 파일의 권한 확인
    total 4
    -rw-rwxr-- 1 geombong geombong 13 Jan  6 18:01 hello.txt # 확인 결과 그룹의 권한이 `rw`에서 `rwx`로 변경된걸 볼 수 있다.
    ```
    - 권한을 변경(chmod)하고 변경된 권한을 확인(ls -l)
    ```bash
    chmod g+x {filename} # g(그룹)+(추가)x(실행) 파일을 소유한 그룹에 대해 실행 권한을 추가
    chmod a=r {filename} # a(모든사용자)=(지정)r(읽기) 모든 사용자에게 파일을 읽을 수 있는 권한 지정
    chmod go+x {filename} # g(그룹)o(그외 사용자)-(제거)w(쓰기) 그룹 외에 사용자에대해 쓰기 권한 제거
    chmod g-w {filename} # g(그룹)-(제거)w(쓰기) 파일을 소유한 그룹에 대해 쓰기 권한을 제거
    ```
    - `chmod`: 파일의 모드를 변경하는 명령어
- 실습을 위한 새로운 디렉토리 생성
    ```bash
    geombong@geombong:~/backup$ mkdir test
    geombong@geombong:~/backup$ ls
    hello.txt  test
    ```
    - `mkdir {directoryname}`
- 디렉토리에 대한 권한
    ```bash
    geombong@geombong:~/backup$ ls -l
    total 8
    -rw-rwxr-- 1 geombong geombong   13 Jan  6 18:01 hello.txt
    drwxrwxr-x 2 geombong geombong 4096 Jan  6 18:24 test
    # {디렉토리 권한 내용} 숫자 {디렉토리 소유자 이름} {디렉토리 소유그룹 이름}
    ```
  - `r`: 디렉토리에 있는 파일 및 디렉토리 리스트 읽기 권한
  - `w`: 디렉토리에 파일 추가, 이름 변경, 삭제 권한
  - `x`: 디렉토리에 접근 권한
- 8진수 형식으로 파일 모드 변경 방법
  - r(읽기): 4
  - w(쓰기): 2
  - x(실행): 1
  - -(권한없음): 0
    ```bash
    --- # 0 (0 + 0 + 0) 권한이 없는 상태
    r-- # 4 (4 + 0 + 0) 읽기 권한
    -w- # 2 (0 + 2 + 0) 쓰기 권한
    rw- # 6 (4 + 2 + 1) 읽기, 쓰기 권한
    r-x # 5 (4 + 0 + 1) 읽기, 실행 권한
    -wx # 3 (0 + 2 + 1) 쓰기, 실행 권한
    rwx # 7 (4 + 2 + 1) 읽기, 쓰기, 실행 권한
    ```
- 디렉토리의 변환을 변경시켜 보자
    ```bash
    drwxrwxr-x 3 geombong geombong 4096 Jan  6 18:24 backup # 변경 전
    ```
    - 775권한으로 설정되어 있는 상태다.
    ```bash
    geombong@geombong:~$ chmod 764 backup
    drwxrw-r-- 3 geombong geombong 4096 Jan  6 18:24 backup
    ```
    - 763권한으로 변경된걸 확인 할 수 있다.

    <img src="https://user-images.githubusercontent.com/78953393/148367351-ee73333f-b9ff-49aa-8892-90a324863045.png">
  
# 회고

## 오늘 한일
- 리눅스 공부

## 아쉬운점
- M1맥북.. 의 한계인가 내가 설정을 잘못한건가 우분투 설치부터 실행까지 너무 많은 시간을 잡아먹었다.(아무것도 없는 보라색 화면이 이렇게 무서울줄이야..)

## 좋았던점
- 주변에서 꼭 리눅스는 써봐야한다고 종종 듣긴했지만, AWS배울때 잠깐 써본것 말곤 없었는데 이렇게 하루를 잡고 리눅스를 공부 할 수 있어서 좋았다.

## 내일 할일
- 쉘 스크립트 미션이 남아 내일은 그 미션을 진행할거 같다.