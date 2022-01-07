# 쉘 스크립트

## 쉘 스크립트란?
- 쉘을 사용해서 프로그래밍을 할 수 있다.
- 서버 작업 자동화 및 운영(DevOps)을 위해 기본적으로 익혀둘 필요가 있다.
- 쉘 명령어를 기본으로 하되, 몇 가지 문법이 추가된 형태
- 시스템 프로그래밍에소 꼭 익히는 내용중 하나

## 쉘 스크립트 예
- 서버에 자동으로 작성되는 로그파일 중 오래된 로그파일을 찾고 삭제할때 간단한 쉘 스크립트를 만들어서 주기적으로 실행

## 기본 문법
- 쉘 스크립트는 파일로 작성 후, 파일을 실행
- 파일의 가장 위에 첫 라인은 `#!bin/bash`로 시작
- 쉘 스크립트 파일은 실행 권한`(X)`을 가지고 있어야한다.
- 일반적으로 `파일이름.sh`와 같은 형태로 파일 이름을 작성한다.

## 간단 실습
### thst.sh
```bash
#!/bin/bash
echo 'Hello Bash!'
```
- `echo`: 화면에 출력해주는 쉘 명령어
- 여러 쉘 명령어를 조합해서 프로그래밍이 가능
- 주석: `#`으로 사용

## 변수
- 선언
  - 변수명=데이터
  - 변수명=데이터 사이에 띄어쓰기는 혀용되지 않는다.
- 사용
  - $변수명으로 사용한다.
```bash
#!/bin/bash

mysql_id='root'
mysql_directory='/etc/mysql'

echo $mysql_id
echo $mysql_directory
```
### 변수 실습
- 아이디 관련 정보 변수 만들기
```bash
#!bin/bash

my_name='geombong'
my_age='30'
my_job='student'

echo $my_name $my_age $my_job
```

### 리스트 변수 (배열)
- 선언
  - 변수명=(데이터1 데이터2 데이터3 ...)
- 사용
  - ${변수명[인덱스번호]}
```bash
#!/bin/bash
daemons=("httpd" "mysqld" "vsftpd")
echo ${daemons[1]}  # 배열의 두 번째 인덱스 출력(mysqld)
echo ${daemons[@]}  # 배열의 모든 데이터 출력
echo ${daemons[*]}  # 배열의 모든 데이터 출력
echo ${#daemons[@]} # 배열의 크기 출력

filelist=($(ls))    # 해당 쉘스크립트 실행 디렉토리의 파일 리스트를 배열로 $filelist 변수에 입력
echo ${filelist[*]} # $filelist 모든 데이터 출력
```

### 리스트 변수 실습
- 아이디 관련 정보를 리스트 변수로 만들고, 출력하기
```bash
#!/bin/bash

info=("geombong" 30 "student")
echo $(info[*])
```

### 사전에 정의된 지역 변수
- `$$ `: 쉘의 프로세스 번호
- `$0` : 쉘 스크립트 이름
- `$1 ~ $9` : 명령줄 인수
- `$*` : 모든 명령줄 인수 리스트 
- `$#` : 인수의 개수
- `$?` : 최근에 실행한 명령어의 종료값
  - 0(성공), 1 ~ 125(에러)
  - 126(파일이 실행가능하지 않음)
  - 128 ~ 255(시그널 발생)

### 사전에 정의된 지역 변수 실습
```bash
#!/bin/bash

ehco $$ $0 $1 $* $#
```
- `./shell.sh ls`입력 시 : `4145 ./shell.sh ls ls 1` 출력

## 연산자
- expr: 숫자 계산
- expr를 사용하는 겨우 역작은 따옴표(`)를 사용해야한다.
- 연산자*와 괄로() 앞에 역슬래시()와 같이 사용
- 연산자와 숫자, 변수, 기호 사이에는 space를 넣어야한다.
```bash
#!/bin/bash

num=`expr \( 3 \* 5 \) / 4 + 7`
echo $num
```

### 연산자 실습
- expr명령으로 (10+20) / 8 - 1 계산해보기
```bash
#!/bin/bash

num=`expr \( 10 + 20 \) / 8 - 8`
echo $num
```
- -5 출력

## 조건문

### 조건문 문법
- 기본 if구문
  - 가독성을 위해 탭으로 띄워서 사용하는 것이 좋다.
```bash
if [ 조건 ]
then
    명령문
fi
```

### 조건
- 조건 작성이 다른 프로그래밍 언어와 달리 가독성이 많이 떨어진다.
- 문자비교
```bash
문자1 == 문자2  # 문자1 과 문자 2가 일치
문자1 != 문자2  # 문자1 과 문자 2가 다르다
-z 문자        # 문자가 null이면 참
-n 문자        # 문자가 null이 아니면 참
```
- 수치비교
```bash
값1 -eq 값2 # 값이 같다.(equal)
값1 -nq 값2 # 값이 같지 않다.(not equal)
값1 -lt 값2 # 값1이 값2보다 작다.(less than)
값1 -lq 값2 # 값1이 값2보다 작거나 같다.(less or equal)
값1 -gt 값2 # 값1이 값2보다 크다.(greater than)
값1 -ge 값2 # 값1이 값2보다 크거나 같다.(greater or equal)
```

### 조건문 실습
- 첫번째 실습: 두 인자값을 받아서 두 인자값이 다르면 `different values`를 출력
```bash
#!/bin/bask

if [ $1 != $2]
then
    echo "different values"
    exit
fi
```
- 두번째 실습: 첫번째 인자값이 두번째 인자값보다 크면, 이를 출력하는 코드 작성
```bash
#!/bin/bash

if [ $1 -gt $2]
then
    echo "$1 is higher $2"
elif [ $1 -eq $2 ]
then
    echo "$1 equal $2"
else
    echo "$1 is lower $2"
    exit
fi
```

### 파일검사
```bash
-e 파일명   # 파일이 존재하면 true
-d 파일명   # 파일이 데릭토리면 true
-h 파일명   # 심볼릭 링크파일이면 true
-f 파일명   # 파일이 일반파일이면 true
-r 파일명   # 파일이 읽기 가능이면 true
-s 파일명   # 파일 크기가 0이 아니면 true
-u 파일명   # 파일이 set-user-id가 설정되면 true
-w 파일명   # 파일이 쓰기 가능이면 true
-x 파일명   # 파일이 실행 가능이면 true
```

### 파일검사 예제
- 해당파일이 있는지 없는지를 출력
```bash
#!/bin/bash

if [ -e $1 ]
then
    echo "file exist"
fi
```