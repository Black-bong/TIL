> 본 게시물은 인프런에서 김영한님의 `모든 개발자를 위한 HTTP 웹 기본 지식` 강의를 듣고 정리한 내용입니다.
>>다시 한번 좋은 강의를 만들어 주신 김영한님에게 감사의 말씀 드립니다.

# 모든 개발자를 위한 HTTP 웹 기본 지식

## IP(인터넷 프로토콜)

### IP 프로토콜
  - IP 프로토콜의 특징
    - 지정 IP Address에 데이터 전달
    - Packet 이라는 통신 단위로 전달
    - 출발 IP Address와 도착 IP Address 필요
  
  - IP 프로토콜의 한계
    - 비연결성 -> 패킷을 받을 대상 서버의 상태를 알 수 없다.
    - 비신뢰성 -> 패킷 소실의 위험이 있다.

### TCP 프로토콜
  - TCP 프로토콜의 특징
    - IP 프로토콜만 가지고 정보를 전달 했을때의 문제점을 해결하기 위해 사용한다.
    - 출발지 PORT, 목적지 PORT, 전송제어, 순서, 검증 정보등을 가지고 있다.
    - 연결지향적 -> TCP 3 way handshake (가상연결)
        1. SYN (C -> S)
            - Client에서 Server로 접속 요청
        2. SYN + ACK (S -> C)
            - Server에서 Client로 접속 요청 및 요청 수락
        3. ACK (C -> S)
            - Client에서 Server로 요청 수락 및 데이터 전송
     - 데이터 전달 보증 (데이터 누락을 알 수 있다.)
        - Client에서 보낸 정보를 Server가 받을 경우 전송 상태를 반환해준다.
     - 데이터 전송 순서 보장
        - Client에서 전송한 정보가 잘못된 순서로 도착할 경우 Server에서 전송이 안된 정보부터 재 전송 요청을 한다.

### UDP 프로토콜
  - UDP 프로토콜의 특징
    - IP 프로토콜과 거의 같다.
    - PORT와 체크섬 정보만 추가
      - PORT : 하나의 IP에서 여러 Application이 실행될때 전송 되는 패킷의 종류를 구별할 수 있게 해준다.
      - 체크섬 : 중복 검사의 한 형태로 송신된 자료의 무결성을 보호하는 방법 중 하나이다.

### DNS
  - DNS란?
    - 사람이 읽을 수 있는 도메인 이름(ex:`wwww.naver.com`)을 IP주소로 변환 혹은 그 반대로 변환해 주는 것
    - Client에서 도메인 명 입력 -> DNS 서버에서 해당 도메인의 IP 주소 반환 -> Client 해당 IP주소 서버 접속

### URI
  - URI란?
    - Uniform: 리소스를 식별하는 통일된 방식
    - Resource: 자원, URI로 식별할 수 있는 모든 것
    - Identifier: 다른 항목과 구분하는데 필요한 정보
  - URL과 URN
    - URL: 리소스가 있는 위치를 지정
    - URN: 리소스에 이름을 부여
  - URL 전체 문법
    - schema://[userinfo@]host[:prot][/path][?query][#fagment]
    - `https://www.google.com:433/search?q=hello&hi=ko`
  - URL Schema
    - 주로 프로토콜 사용
    - 프로토콜: 어떤 방식으로 자원에 접근할 것인가 하는 약속(규칙)
      - http, https, ftf 등등
  - URL Path
    - 리소스 경로
    - 계층적 구조를 가지고 있다.
    - /home/file.jpg
    - /members
    - /members/100, /items/iphone13
  - URL Query
    - key=value 의 형태
    - ?로 시작해서 & 추가 가능
    - query parameter, query string 등으로 불린다.
