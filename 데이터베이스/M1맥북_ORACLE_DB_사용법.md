# M1 맥북에서 오라클 DB 사용하기

M1칩을 사용하는 맥북은 docker를 사용해도 오라클 데이터베이스를 사용할 방법이 없어 찾아보던중 오라클 클라우드를 사용하면 사용이 가능하다는 소식을 듣고 추후 다시 세팅해야 할 경우를 대비해 정리

-----

1. 무료 오라클 데이터 베이스 생성
    - https://www.oracle.com/kr/cloud/free/ 링크에서 가입 후 
    - 용도에 맞게 ATP와 ADW를 선택하여 데이터 베이스 생성
    - ATP 설정
        - 구획, 표시 이름(클라우드에 표시되는 이름), 데이터베이스 이름(DB 접속 시 필요한 이름)을 입력
        - 작업 로드 유형 선택 (필요한 유형 선택)
          - 트랜잭션 처리
        - 배치 유형 선택 (필요한 유형 선택)
          - 공유 인프라 선택
    - 데이터베이스 구성
        - `필수` 항상 무료 체크
        - 버전 선택 (19c 만 선택 가능)
    - 관리자 인증서 생성
        - 사용자 이름
        - 비밀번호 설정 (대,소문자 / 숫자 / 기호 포함)
    - 네트워크 액세스 선택
        - 엑세스 유형 선택
            - 모든 곳에서 보안 액세스 선택
        - 라이센스 유형 선택
            - 라이센스 포함됨 선택
    - 자율운영 데이터베이스 생성 버튼 클릭
        - 처음 생성 시 시간이 조금 걸릴 수 있으나, 좀 만 기다리면 생성이 완료됨.

2. 전자 지갑 다운로드
    - DB접속 버튼 클릭
    - 전자 지갑 유형 선택
        - 인스턴스 전자 지갑 선택
    - 전자 지갑 다운로드

----

오라클 클라우드 DB서버를 생성 완료 이제 Developer를 활용해서 서버 연결이 되는지 확인해보자.(DBeaver를 사용)

1. OJDBC 설치 (19c버전)
    - https://www.oracle.com/kr/database/technologies/appdev/jdbc-downloads.html

2. 원하는 Developer 설치
    - DBeaver 설치(homebrow 사용)
      ```
      brew install --cask dbeaver-community
      ```

3. Developer 접속 후 오라클 DB 생성
    - Username과 Password 입력
    - Custom 탭에 JDBC URL Template을 아래와 같이 입력합니다.
      ```
      jdbc:oracle:thin:@{ATP에서설정한이름}?TNS_ADMIN=/전자지갑경로
      ```
    - ATP에서 설정한 이름은 설장한이름_high 를 넣어주면 된다. 혹시 까먹었다면 전자지갑 폴더안에 `tnsnames.ora` 파일을 열어 확인 가능하다. 
    - 폴더 경로 복사는 `command + option + c` 이다.
    - Edit Driver Settings 클릭
    - Libraries안에 있는 오라클 드라이버를 전부 삭제 후 아까 설치 받은 OJDBC를 넣어준다.
    - 마지막으로 Test Connection 버튼 클릭 후 정상적으로 연결되는지 확인

-----
# REFERENCE
  - https://ltw2.tistory.com/19
