# 모든 개발자를 위한 HTTP 웹 기본 지식

-----
>본 게시물은 인프런에서 김영한님의 `모든 개발자를 위한 HTTP 웹 기본 지식` 강의를 듣고 정리한 내용입니다.
> 
>`강의 내용외에 직접 검색하고, 찾아본 내용도 함께 작성될 수 있습니다.`
>>출처: https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC/dashboard
-----

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
    - schema://[userinfo@]host[:port][/path][?query][#fagment]
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
**웹 브라우저 요청 흐름**

****HTTP 메시지 전송 순서****
1. 웹 브라우저가 HTTP 메시지 생성
2. SOCKET 라이브러리를 통해 전달
    1. TCP/IP 연결
    2. 데이터 전달
3. TCP/IP 패킷 생성, HTTP 메시지 포함
4. 3 handshake 를 통한 연결 확인 및 패킷 전송
5. 전송 결과에 대한 응답 메시지 반환

## HTTP 기본

**클라이언트 서버 구조**

### **클라이언트 서버 구조**

- Request Response 구조
- 클라이언트는 서버에 요청을 보내고, 응답을 대기
- 서버가 요청에 대한 결과를 만들어서 응답

**Stateful, Stateless**

### **무상태 프로토콜 Stateless**

- 서버가 클라이언트의 상태 보존 x !Stateful
- 무상태는 응답 서버를 쉽게 바꿀 수 있다. -> 무한한 서버 증설 가능
- 스케일 아웃 -> 수평 확장 유리

### **Stateless 한계**

- 어쩔수 없이 상태 유지를 해야하는 경우가 있다
    - 로그인 등
    - 로그인한 사용자의 경우 로그인 했다는 상태를 서버에 유지
- 일반적으로 브라우저 쿠키와 서버 세션등을 사용해서 상태 유지
- 상태 유지는 최소한만 사용
- 데이터를 과도하게 전송한다.

**비 연결성(connectionless)**

### **비 연결성**

- TCP/IP의 경우 연결을 유지하는 모델
    - 요청이 없어도 서버는 연결을 계속 유지, 서버 자원 소비
- HTTP는 비 연결성
    - 요청이 있을때만 연결 유지
    - 최소한의 자원 사용 -> 서버 자원을 매우 효율적으로 사용
    - 빠른속도로 응답(초 단위 이하)

## HTTP 메시지

### **HTTP 메세지 구조**

- start-line 시작 라인
- header 헤더
- empy line 공백라인 (필수)
- message body
    
    ![https://cdn.inflearn.com/public/files/posts/a307ed4a-010f-43e0-96cc-401e39cdd8ae/%E1%84%86%E1%85%A9%E1%84%83%E1%85%B3%E1%86%AB%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%8C%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20HTTP%20%E1%84%8B%E1%85%B0%E1%86%B8%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20%E1%84%8C%E1%85%B5%E1%84%89%E1%85%B5%E1%86%A8%20-%20%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%91%E1%85%B3%E1%84%85%E1%85%A5%E1%86%AB%20%7C%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%20%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%20%F0%9F%94%8A%202021-09-30%2016-05-31.png](https://cdn.inflearn.com/public/files/posts/a307ed4a-010f-43e0-96cc-401e39cdd8ae/%E1%84%86%E1%85%A9%E1%84%83%E1%85%B3%E1%86%AB%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%8C%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20HTTP%20%E1%84%8B%E1%85%B0%E1%86%B8%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20%E1%84%8C%E1%85%B5%E1%84%89%E1%85%B5%E1%86%A8%20-%20%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%91%E1%85%B3%E1%84%85%E1%85%A5%E1%86%AB%20%7C%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%20%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%20%F0%9F%94%8A%202021-09-30%2016-05-31.png)
    

### **시작 라인 요청 메시지**

- start-line = **request-line** / status-line
- request-line = method SP(공백) request-target SP HTTP-version CRLF(엔터)
- HTTP method
    - 종류 : GET, POST, PUT, DELETE...
- request-target
    - 요청 대상
    - absolute-path[?query] (절대경로[?쿼리])
    - 절대경로 ="/" 로 시작

### **시작 라인 응답 메시지**

- start-line = request-line / **status-line**
- status-line = HTTP-version SP status-cod SP reason-phrase CRLF
- HTTP 상태 코드
    - 200: 성공
    - 400: 클라이언트 요청 오류
    - 500: 서버 내부 오류
- 이유 문구: 사람이 이해할 수 있는 짧은 상태 코드

### **HTTP 헤더**

- header-field = field-name ":" OWS field-value OWS
    - OWS -> 띄어쓰기 허용
- field-name은 대소문자 구분을 안한다.
- HTTP 전송에 필요한 모든 부가정보가 들어있다.

**HTTP 메서드**

**HTTP API를 만들어보자**

**HTTP API 만들기**

- API URI 설계
    - 리소스 식별이 가장 중요하다.
    - **회원** 목록 조회 /members
    - **회원** 조회 /members/{id}
    - 리소스와 해당 리소스를 대상으로 하는 행위 분리
        - 리소스: 회원 -- 명사
        - 행위: 조회, 등록, 삭제, 변경 -- 동사

**HTTP 메서드 - GET, POST**

### **HTTP API 만들기**

- HTTP 메서드
    - GET: 리소스 조회
        - 서버에 전달하고 싶은 데이터는 query를 통해 전달
    - POST: 요청데이터 처리, 주로 등록에 사용
        - 메시지 바디를 통해 서버로 요청 데이터 전달
        - 서버는 요청된 데이터 처리
        - 신규 리소스 등록, 프로세스 처리에 사용

**HTTP 메서드 - PUT, PATCH, DELETE**

- PUT: 리소스를 대체, 해당 리소스가 없으면 생성클라이언트가 리소스 위치를 알고 URI 지정POST와의 차이점PATCH: 리소스 부분 변경리소스를 일부 대체 PUT은 완전 대체DELETE: 리소스 삭제

## HTTP 메서드 활용

**클라이언트에서 서버로 데이터 전송**

### **클라이언트에서 서버로 데이터 전송**

- 쿼리 파라미터를 통한 데이터 전송
    - GET
    - 주로 정렬 필터(검색어)
- 메시지 바디를 통한 데이터 전송
    - POST, PUT, PATCH
    - 회원 가입, 상품 주문, 리소스 등록, 리소스 변경

**HTTP API 설계 예시**

### **API 설계 - POST 기반 등록**

- 회원 목록 /members -> GET
- 회원 등록 /members -> POST
- 회원 조회 /members /{id} -> GET
- 회원 수정 /members/{id} - PATCH, PUT, POST
    - PUT -> 기존의 리소스를 삭제하고 붙어 넣는다.
    - PATCH -> 부분적으로 수정 가능하다.
    - 사용 용도에 따라 구분하여 사용하되 대부분 PATCH를 사용
        - 게시판에서 게시글을 수정할때는 PUT을 사용
    - 용도가 애매하면 POST 사용
- 회원 삭제 /members/{id} -> DELETE

### **POST - 신규 자원 등록 특징**

- 클라이언트는 등록될 리소스의 URI를 모른다.
    - 회원등록 /members -> POST
    - POST /members
- 서버가 새로 등록된 리소스 URI를 생성해준다.
    - HTTP/1.1 201 Created Location: /members/100
- 컬렉션(Collection)
    - 서버가 관리하는 리소스 디렉토리
    - 서버가 리소스의 URI를 생성하고 관리
    - 여기서 컬렉션은 /members

### **API 설계 - PUT 기반 등록**

- 파일 목록 /files -> GET
- 파일 조회 /files/{filename} -> GET
- 파일 등록 /files/{filename} -> PUT
- 파일 삭제 /files/{filename} -> DELETE
- 파일 대량 등록 /files -> POST
    - PUT으로 파일을 등록하고 있으므로, /files -> POST 의 의미를 임의로 정할 수 있다.

### **PUT - 신규 자원 등록 특징**

- 클라이언트가 리소스 URI를 알고 있어야 한다.
    - 파일 등록 /files/{filename} -> PUT
    - PUT /files/star.jpg
- 클라이언트가 직접 리소스의 URI를 지정한다.
- 스토어(Store)
    - 클라이언트가 관리하는 리소스 저장소
    - 클라이언트가 리소스의 URI를 알고 관리
    - 여기서 스토어는 /files

### **HTML FORM 사용**

- 순수 HTML FORM은 **GET, POST만 지원**
- GET, POST만 지원하므로 제약이 있음
    - AJAX 같은 기술을 사용해서 해결 가능하나, 여기서는 순수 HTML, HTML FORM 만 이야기
- 회원 목록 /members -> GET
- 회원 등록 폼 /membrs/new -> GET
- 회원 등록 /members/new, members -> POST
- 회원 조회 /members/{id} -> GET
- 회원 수정 폼 /members/{id}/edit -> GET
    - 회원 조회 URI와 회원 수정 폼 URI를 맞추는 것이 좋으나 ( 개인 마다 사용 방식에 차이가 있다)
- 회원 수정 /members/{id}/deit, members/{id} -> POST
    - 회원 수정 폼 URI와 회원 수정 URI를 맞추는 것이 좋으나 ( 개인 마다 사용 방식에 차이가 있다)
- 회원 삭제 /members/{id}/delete -> POST

- 컨트롤 URI
    - GET, POST만 사용이 가능하기 때문에 제약이 있다.
    - 제약을 해결하기 위해 동사로 된 리소스 경로 사용
    - POST의 /new, /edit, /delete가 컨트롤 URI
    - HTTP 메서드로 해결하기 애매한 경우 사용(HTTP API 포함)

## HTTP 상태코드

**HTTP 상태코드 소개**

### **HTTP 상태 코드**

- 요청에 대한 처리 상태를 응답에서 알려주는 기능
    - 1xx (Informationnal): 처리중
        - 거의 사용하지 않으므로 생략
    - 2xx (Successful): 정상처리
    - 3xx (Redirection): 요청완료에 추가적인 행동 필요
    - 4xx (Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없다.
    - 5xx (Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함

**2xx - 성공**

### **2xx (Successful)**

- 200 OK
    - 요청 성공 응답
- 201 Created
    - 요청에 성공하여, 새로운 리소스가 생성됨
- 202 Accepted
    - 요청이 접수 되었으나, 처리가 완료되지 않음
- 204 No Content
    - 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음

**3xx - 리다이렉션1**

### **3xx (Redirection)**

- 요청을 완료하기 위해 유저 에이전트의 추가 조치 필요
    - 300 Multiple Choices
    - 301 Moved Permanently
        - 리소스의 URI가 영구적으로 이동
        - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음
    - 302 Found
        - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음
    - 303 See Other
        - 302와 기능은 같음
        - 리다이렉트 요청 메서드가 GET으로 변경
    - 304 Not Modified
        - 캐시를 목적으로 사용
        - 클라이언트에게 리소스가 수정되지 않았음을 알려준다. 따라서 클라이언트는 로컬 PC에 저장된 캐시를 재사용(캐시로 리다이렉트 한다)
        - 304 응답은 응답에 메시지 바디를 포함하면 안된다.
        - 저건부 GET, HEAD 요청시 사용
    - 307 Temporary Redirect
        - 302와 기능은 같음
        - 리다이렉트시 요청 메서드와 본문 유지
    - 308 Permanent Redirect
        - 301과 기능은 같음
        - 리다이렉트시 요청 메서드와 본문 유지(POST를 사용해서 요청을 할 경우 POST와 본문을 유지)

**3xx - 리다이렉션2**

### **PRG (Post/Redirect/Get)**

- **PRG 미사용**
    - POST로 주문후에 웹 브라우저를 새로고침 하면?
    - 새로고침 다시 요청
    - 중복 주문이 될 수 있다.
- **PRG 사용**
    - POST로 주문후에 새로 고침으로 인한 중복 주문 방지
    - POST로 주문후에 주문 결과 화면을 GET 메서드로 리다이렉트
    - 새로고침해도 결과 화면을 GET으로 조회
    - 중복 주문 대신에 결과 화면만 GET으로 요청
    - URL이 이미 POST -> GET으로 리다이렉트 됨

**4xx - 클라이언트 오류, 5xx - 서버 오류**

### **4xx (Client Error)**

- 클라이언트의 요청에 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- 오류의 원인이 클라이언트에 있음
- 클라이언트가 이미 잘못된 요청, 데이터를 보내고 있기 때문에 똑같은 재시도가 실패함

- 400 Bad Request
    - 요청 구문, 메시지 등등 오류
    - 클라이언트는 요청 내용을 다시 검토하고, 보내야함
    - 예) 요청 파라미터가 잘못되거나, API 스펙이 맞지 않을 때
- 401 Unauthorized
    - 인증 되지 않음
    - 401 오류 발생시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명
    - 참고
        - 인증(Authentication): 본인이 누구인지 확인(로그인)
        - 인가(Authorization): 권한부여(ADMIN 권한처럼 특정 리소스에 접근할 수 있는 권한
        - 오류 메세지가 Unauthorized 이지만 인증 되지 않음 (이름이 아쉬움)
- 403 Forbidden
    - 주로 인증 자격 증명은 있지만, 접근 권한이 불충분한 경우
        - ADMIN등급이 아닌 사용자가 로그인 한 후 ADMIN등급의 리소스에 접근하는 경우
- 404 Not Found
    - 요청 리소스가 서버에 없음
    - 또는 클라이언트가 권한이 부족한 리소스에 접근할 때 해당 리소스를 숨기고 싶을때

**5xx (Server Error)**

- 서버 문제로 오류 발생
- 서버에 문자가 있기 떄문에 재시도 하면 성공할 수도 있다.
- 500 Internal Server Error
    - 서버 내부 문제로 오류 발생
    - 애매하면 500 오류
- 503 Service Unavailable
    - 서버가 일시적인 과부화 또는 예정된 작업으로 잠시 요청을 처리할 수 없음
    - Retry-After 헤더 필드로 얼마뒤에 복구되는지 보낼 수도 있다.

**HTTP 헤더1 - 일반 헤더**

## 콘텐츠 협상

### **협상(Content Negotiation)**

- 클라이언트가 선호하는 표현 요청
    - Accept: 클라이언트가 선호하는 미디어 타입 전달
    - Accept-Charset: 클라이언트가 선호하는 문자 인코딩
    - Accept-Encoding: 클라이언트가 선호하는 압축 인코딩
    - Accept-Language: 클라이언트가 선호하는 자연언어
    - 협상 헤더는 요청시에만 사용
- 협상과 우선순위 1
    - Quality Values(q) 값 사용
    - 0 ~ 1, 클수록 높은 우선순위
    - default 값은 1
    - Accept-Language:ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
        1. ko-KR;q=1 (q생략)
        2. ko;q=0.9
        3. en-US;q=0.8
        4. en;q=0.7
- 협상과 우선순위 2
    - 구체적인 것을 우선한다.
    - Accept: text/*, text/plain, text/plain;format=flowed, */*
        1. text/plain;format=flowed
        2. text/plain
        3. text/*
        4. /*
- 협상과 우선순위 3
    - 구체적인 것을 기준으로 미디어 타입을 맞춘다.
    - Accept: text/*;q=0.9, text/html;q=0.7, text/html;level=1, text/html;level=2;q=0.4, */*;q=0.5
        
        ![https://cdn.inflearn.com/public/files/posts/67a9bb61-6371-4b23-8464-25caa8a56319/%E1%84%86%E1%85%A9%E1%84%83%E1%85%B3%E1%86%AB%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%8C%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20HTTP%20%E1%84%8B%E1%85%B0%E1%86%B8%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20%E1%84%8C%E1%85%B5%E1%84%89%E1%85%B5%E1%86%A8%20-%20%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%91%E1%85%B3%E1%84%85%E1%85%A5%E1%86%AB%20%7C%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%20%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%202021-10-19%2014-05-29.png](https://cdn.inflearn.com/public/files/posts/67a9bb61-6371-4b23-8464-25caa8a56319/%E1%84%86%E1%85%A9%E1%84%83%E1%85%B3%E1%86%AB%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%8C%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20HTTP%20%E1%84%8B%E1%85%B0%E1%86%B8%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20%E1%84%8C%E1%85%B5%E1%84%89%E1%85%B5%E1%86%A8%20-%20%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%91%E1%85%B3%E1%84%85%E1%85%A5%E1%86%AB%20%7C%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%20%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%202021-10-19%2014-05-29.png)
        

## 쿠키

### **쿠키**

- Set-Cookie: 서버에서 클라이언트로 쿠키 전달(응답)
- Cookie: 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달
- 예) set-cookie: sessionid=abcde1234; expores=Sat, 26-Dec-2020 00:00:00 GMT; path=/; domain=.google.com; Secure
- 사용처
    - 사용자 로그인 세션 관리
    - 광고 정보 트래킹
- 쿠키 정보는 항상 서버에 전송됨
    - 네트워크 트래픽 추가 유발
    - 최소한의 정보만 사용(세션 id, 인증 토큰)
    - 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶으면 웹 스토리지(localStorage, sessionStorage) 참고
- 주의
    - 보안에 민감한 데이터는 저장하면 안된다.

### **쿠키 - 생명주기**

- set-Cookie: expires=Sat, 26-Dec-2020 04:39:21 GMT
    - 만료일이 되면 쿠키 삭제
- Set-Cookie: max-age=3600 (3600초)
    - 0이나 음수를 지정하면 쿠키 삭제
- 세션 쿠키: 만료 날짜를 생략하면 브라우저 종료시 까지만 유지
- 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지

### **쿠키 - 도메인**

- 예) domain=example.org
- 명시: 명시한 문서 기준 도메인 + 서브 도메인 포함
    - domain=example.org를 지정해서 쿠키 생성
        - example.org는 물론이고 dev.example.org또 쿠키 접근
- 생략: 현재 문서 기준 도메인만 적용
    - example.org에서 쿠키를 생성하고 domain 지정을 생략
        - example.org 에서만 쿠키 접근
        - dev.example.org는 쿠키 미접근

### **쿠키 - 경로**

- 예) path=/home
    - /home -> 가능
    - /home/level1 -> 가능
    - /home/level1 -> level2 -> 가능
    - /hello -> 불가능
- 이 경로를 포함한 하위 경로 페이지만 쿠키 접근
- 일반적으로 path=/ 루트로 지정

### **쿠키 - 보안**

- Secure
    - 쿠키는 http, https를 구분하지 않고 전송 하나, Secure를 적용하면 https인 경우에만 전송
- HttpOnly
    - XSS 공격 방지
    - 자바스크립트에서 접근 불가(document.cookie)
    - HTTP 전송에만 사용
- SameSite
    - XSRF 공격 방지
    - 요청 도메인과 쿠키에 설정된 도메인이 같은 경우에만 쿠키 전송

**HTTP 헤더2 - 캐시와 조건부 요청**

**캐시 기본 동작**

### **캐시 기본동작**

- 캐시가 없을때
    - 데이터가 변경되지 않아도 계속 네트워크를 통해 데이터를 다운로드 받아야 한다.
    - 브라우저 로딩 속도가 느리다.
- 캐시가 있을때
    - 캐시 사용 가능 시간동안 네트워크를 사용하지 않아도 된다.
    - 브라우저 로딩 속도가 매우 빠르다.
- 캐시 시간 초과
    - 캐시 유효 시간이 초과하면, 서버를 통해 데이터를 다시 조회하고, 캐시를 갱신
- 캐시 동작 원리
    1. 첫번째 접속 시 서버에 요청을 하고 해당 요청에 맞는 데이터를 다운 받은 후 브라우저 캐시 저장소에 해당 데이터를 저장
    2. 두번째(캐시 유효 시간 내) 접속 시 서버 네트워크에 따로 연결 없이 브라우저 캐시 저장소에서 저장되어 있는 데이터를 가져옴
    3. 세번째(캐시 유효 시간 만료) 접속 시 서버 네트워크 연결 다시 데이터를 받아오고 해당 데이터를 브라우저 캐시 저장소에 저장

**검증 헤더와 조건부 요청1**

### **검증 헤더와 조건부 요청**

- 캐시 유효 시간이 초과해도, 서버의 데이터가 갱신되지 않으면 304 Not Modified + 헤더 메타 정보만 응답(Body는 응답 x)
- 클라이언트는 서버가 보낸 응답 헤더 정보로 캐시의 메타 정보를 갱신
- 클라이언트는 캐시에 저장되어 있는 데이터 재활용
- 전체 다운로드 데이터 보다 헤더 정보만 다운로드 받기 때문에 네트워크 사용량 감소

**검증 헤더와 조건부 요청2**

### **검증 헤더와 조건부 요청 2**

- ETag(Entity Tag)
- 캐시용 데이터에 임의의 고유한 버전 이름을 달아둠
- 데이터가 변경되면 이 이름을 바꾸어서 변경함(Hash를 다시 생성)

**캐시와 조건부 요청 헤더**

### **캐시 제어 헤더**

- Cache-Control: 캐시제어
    - Cache-Control: max-age
        - 캐시 유효 시간, 초 단위
    - Cache-Control: no-cache
        - 데이터는 캐시해도 되지만 항상 원(origin) 서버에 검증하고 사용
    - Cache-Control: no-store
        - 데이터에 민감한 정보가 있으므로 저장하면 안됨
            - 메모리에서 사용하고 최대한 빨리 삭제

## 캐시 무효화

### Cache-Control

**캐시 지시어(directives) - 확실한 캐시 무효화**

- Cache-Control: no-cache
    - 데이터는 캐시해도 되지만, 항상 원 서버에 검증하고 사용
- Cache-Control: no-store
    - 데이터에 민감한 정보가 있으므로 저장하면 안됨
- Cache-Control: must-revalidate
    - 캐시 만료후 최초 조회시 원 서버에 검증해야함
    - 원 서버 접근 실패시 반드시 오류가 발생해야함 - 504(Gateway Timeout)
    - must-revalidate는 캐시 유효시간이라면 캐시를 사용함
- Pragma: no-cache
    - HTTP 1.0 하위 호환
