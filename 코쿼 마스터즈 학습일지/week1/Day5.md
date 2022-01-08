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

### 조건문 한줄에 작성하기
- if 구문
```bash
if [ 조건 ]; then 명령문; fi

if [ -z $1 ]; then echo "Insert arguments"; fi
```
- `if`와 `[` 조건 `]` 사이에는 반드시 공백이 있어야한다.
- `[]`에서 `&&`, `||`, `<>` 연산자들이 에러가 나는 경우에는 `[[]]` 를 사용하면 정상 작동하는 경우가 종종있다.

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
- 파일 이 존재하면 `file exist`출력된다.

### 압축 명령 tar
- 주요 옵션
```bash
x   # 묶을을 해제
c   # 파일을 묶음
v   # 묶음/해제 과정을 화면에 표시
z   # gunzip을 사용
f   # 파일 이름을 지정
```
- 압축시 자주 사용하는 옵션
```bash
tar -cvzf [압축된 파일 이름] [압출할 파일이나 디렉토리명]
```
- 압출을 풀때 자주 사용하는 옵션
```bash
tar -xvzf [압축을 해제할 압축 아카이브 이름]
```

### 파일검색 명령(find)
- 파일검색
```bash
find . -type f -name '파일명검색어' -exec bash -c "명령어1; 명령어2; 명령어3" \;
```
- `-type f`: 파일 타입을 지정해서 검색
- `.`: 현재 디렉토리
- `파일명검색어`: 주로 정규표현식을 많이 사용한다.
- `-exec`: 프로세스 대체 여기서는 조건을 가지고 찾은 파일들을 대상으로 다음 명령어를 실행하라의 의미
- `bash -c "명령어1; 명령어2; 명령어3"`: 쉘 명령어를 실행

### 논리연산
```bash
조건1 -a 조건2  # AND
조건1 -o 조건2  # OR
조건1 && 조건2  # AND
조건1 || 조건2  # OR
!조건          # NOT
true          # 항상 참
false         # 항상 거짓
```

### 쉘 스크립트 해석하기
- 명령 해석
```bash
#!/bin/bash

ping -c 1 192.168.0.1 1> /dev/null

if [ $? == 0 ]
then
    echo "게이트웨이 핑 성공!"
else
    echo "게이트웨이 핑 실패!"
fi
```
  - `ping -c 1 192.168.0.1 1> /dev/null`
    - `0`: 표준입력, `1`: 표준출력, `2`: 표준에러
  - `1> /dev/null`: 표준 출력 내용은 버려라
  - `>`: 리다이렉션
  - `/dev/null`: `/dev`뒤에 붙은 파일에 보내라는 뜻 여기서는 `/null`로 보내라는건데 이게 출력하지 말라는 뜻을 가지고 있다.
  - `-c 1`: 한번만 체크

## 반복문

### for 반복문 문법
- 기본 `for`구문
```bash
#!/bin/bash

for 변수 in 변수값1 변수값2 ...
do
    명령문
done
```

### 간단예제
- 현재 디렉토리에 있는 파일과 디렉토리를 출력
```bash
#!/bin/bash

for database in $(ls)
do
    echo $database
done
```
- 명령어 `ls`와 유사하게 작동

### while 반복문 문법
```bash
#!/bin/bash

while [ 조건문 ]
do
    명령문
done
```

### 간단예제
- 현재 디렉토리에 있는 파일과 디렉토리를 출력
```bash
#!/bin/bash

lists=$(ls)                 # lists변수에 $(ls)의 결과값을 전부 넣는다.
num=${#lists[@]}            # num변수에 lists변수의 총 개수가 저장된다.
index=0                     # index변수를 만들어 0값을 저장
while [ $num -ge 0 ]        # num변수가 0보다 크거나 같으면 실행
do
    echo ${lists[$index]}   # index변수 값에 있는 lists변수의 값을 출력
    index=`expr $index + 1` # index번수의 값에 1을 더해서 저장
    num=`expr $num -1`      # num변수의 값에 -1을 빼서 저장
done
```

## 실제사용 예제

### 1. 백업하기
- 백업하기
```bash
#!/bin/bash

if [ -z $1 ] || [ -z $2 ]
then
    echo usage: $0 sourcedir targetdir
else
    SRCDIR=$1
    DSTDIR=$2
    BACKUPFILE=backup_$(date +%y%m%d%H%M%S).zip
    if [ -d $DSTDIR ]
    then
        tar -cvzf $DSTDIR/$BACKUPFILE $SRCDIR
    else
        mkdir $DSTDIR
        tar -cvzf $DSTDIR/$BACKUPFILE $SRCDIR
    fi
fi
```

### 2. 로그파일 정리하기
```bash
#!/bin/bash

LOGDIR=/var/log
GZIPDAY=1
DELDAY=2
cd $LOGDIR
echo "cd $LOGDIR"

sudo find . -type f -name '*log.?' -mtime +$GZIPDAY -exec bash -c "gzip {}" \; 2> /dev/null
sudo find . -type f -name '*.gz' -mtime +$DELDAY -exec bash -c "rm -f {}" \; 2> /dev/null
```
- `-mtime +숫자`: `-mtime`은 파일의 기록시간, +1을 할 경우 `현재~(1+숫자)일`이상 경과한 파일을 찾아준다.

## 실습

### step.1
- 특정 타입의 파일이 있는지 확인해 보자.
```bash
#!/bin/bash

if [ -e *.cs ]
then
    echo "YES"
else
    echo "NO
fi
```

### step.2
- 특정 타입의 파일이 다양한 디렉토리중에 어떤 디렉토리에 있는지 확인하고 결과를 출력해보자.
```bash
#!/bin/bash

dirname=${PWD##*/}

if [ -e *.cs ]
then
    echo "YES"
else
    echo $dirname
fi
```
- 일단 현재 디렉토리에 파일이 없을 경우 디렉토리 이름 출력
- 이걸 어떻게 하위 디렉토리에도 적용 시킬지 생각해보자.
```bash
#!/bin/bash

dirlists=$(ls -l | grep ^d)
count=${#dirlists[@]}
echo $count
```
- 난 디렉토리 개수를 구하고 싶은건데 왜 자꾸 1만 출력되는지 모르겠다...
```bash
#!/bin/bash
dirlists=$(ls -d day*)

for i in $dirlists
do
    echo $i
done
```
- 그냥 해당 이름으로 끝나는 디렉토리를 전부 찾는 방향으로 변경
- 생각을 달리하니 진행이 빨라졌다.
```bash
#!/bin/bash

check_backup_dir() {
    if ! [ -d backup ]
    then
        mkdir backup
    fi
}

is_empty_file() {
    if [ -e cs.* ]
    then
        echo "YES"
    else
        echo $i is empty
    fi
}

dir_list=$(ls -d day*)

check_backup_dir

for i in $dirlists
do
    cd $i
    is_empty_file
    cd..
done
```
- 우선 백업파일을 저장할 디렉토리가 있는지 확인하고 없으면 생성, 있으면 다음단계로
- 디렉토리안에 `.cs`파일이 있으면, "YES"출력 없으면 `$i(디렉토리이름) is empty` 출력

### step.3
- "YES"출력 부분을 백업 디렉토리에 기존 파일을 복사해서 저장하는 로직으로 변경해보자.
```bash
is_empty_file() {
    if [ -e cs.* ]
    then
        echo cp *.cs ../backup
    else
        echo $i is empty
    fi
}
```

### step.4
- 백업된 파일을 압축하자
```bash
file_backup() {
    today=$(date "+%Y%m%d")
    backup_file_name="backup_$today"

    zip $backup_file_name.zip backup/*
}
```

### step.5
- 백업된 파일이 일정 시간이 지나면 삭제되도록 만들자.
```bash
backup_file_del() {
    del_day=30

    sudo find . -type f -name '*.cs' -mtime +$del_day -exec bash -c "rm -f {}" \; 2> /dev/null
}
```
- 지정 일수에 따라 파일 삭제
### step.6
- 파일을 원격pc로 전송하자!
```bash
transfer_file() {
    scp $backup_file_name.zip geombong@192.xxx.xx.x:~/backup
}
```
- 원격pc에 있는 `backup`디렉토리에 파일을 전송
- 함수를 쓰면서 생각하지만 함수안에서 선언되어있는 변수를 다른 함수에도 별도의 조치 없이 사용할 수 있는게 신기하다.

```bash
#!/bin/bash

check_backup_dir() {
	if ! [ -d backup ]
	then
		mkdir backup
	fi
}

is_empty_file() {
	if [ -e *.cs ]
	then
		cp *.cs ../backup
	else
		echo $i is empty
	fi
}

file_backup() {
	today=$(date "+%Y%m%d")
	backup_file_name="backup_$today"
	
	zip $backup_file_name.zip backup/*
}

backup_file_del() {
	del_day=30
	
	sudo find . -type f -name '*.cs' -mtime +$del_day  -exec bash -c "rm -f {}" \; 2> /dev/null
}

transfer_file() {
	scp $backup_file_name.zip geombong@192.xxx.xx.x:~/backup
}

dir_lists=$(ls -d day*)

check_backup_dir

for i in $dir_lists
do
	cd $i
	is_empty_file
	cd ..
done

file_backup
backup_file_del
transfer_file
```
- 드디어 완성된 전체 코드!! 와 12시간넘게 걸렸네.. ㅠㅠ