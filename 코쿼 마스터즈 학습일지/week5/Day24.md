# 24일 차 학습일지

# HTTP

## HTTP란?

-   웹상에서 클라이언트와 서버 간 통신을 위한 프로토
-   응용 계층에 속한다.
-   HTTP0.9 ~ 2까지는 TCP를 사용
-   HTTP3은 UDP를 사용

### HTTP0.9

```
GET /title.html

<HTML>
Hello HTTP
</HTML>
```

-   HTML만 전송이 가능
-   굉장히 단순하다.

### HTTP1.0(확장성 만들기)

```
GET /title.html HTTP/1.0/
User-Agent: NCSA_Mosaic/2.0 (Windows 3.1)

200 OK
Date: Tue, 15 Nov 1994 08:12:31 GMT
Server: CERN/3.0 libwww/2.17
Content-Type: text/html
<HTML>
A page with an image
    <IMG SRC="/myimage.gif">
</HTML>
```

-   버전 정보가 명시되기 시작
-   브라우저의 요청에 대한 성공과 실패를 알 수 있고, 그 결과에 대한 동작을 할 수 있다.
-   메타데이터 전송을 허용하고 프로토콜을 유연하고 확장 가능하도록 변경되었다.
-   새로운 HTTP 헤더의 도움으로 HTML 파일 외에 다른 문서들을 전송하는 기능이 추가
-   Connection 하나당 응답 하나와 요청 하나만 처리 가능
-   하나씩밖에 처리가 불가능해서 새로운 요청마다 새로운 연결로 성능이 저하

### HTTP1.1(표준 프로토콜)

-   Persistent Connection 추가
    -   지정한 timeout동안 Connection을 닫지 않는 방식
-   Pipelining 추가
    -   하나의 Connection에서 응답을 기다리지 않고 순차적인 여러 요청을 연속적으로 보내, 순서에 맞춰 응답을 받는 방식으로 지연 시간을 줄이는 방법
-   Head Of Line Blocking이 발생할 수 있다.
    -   패킷 라인이 첫 번째 패킷에 의해 보류될 때 발생하는 성능 제한 현상.
-   Header 구조의 중복
    -   매번 요청을 할 때 Header에 중복된 내용이 있어도, 중복 내용까지 Header에 등록하여 요청

### HTTP2

-   가장 많이 사용하는 HTTP버전
-   기존 HTTP버전의 성능 향상을 목적으로 개발/사용

![바이터리 프레이밍](https://developers.google.com/web/fundamentals/performance/http2/images/binary_framing_layer01.svg?hl=ko)

-   응용계층 내의 바이너리 프레이밍 계층 새로 만들어서 사용
    -   전송 속도 Up, 오류 발생 가능성 Down
-   리소스 간 우선순위를 설정 가능
-   Header 구조의 중복과 Head Of Line Blocking 문제 해결

### HTTP 메시지 구조

-   start-line(시작 라인)
-   header(헤더)
-   empty line(공백)
-   message body

### HTTP 요청 메시지

```
GET /search?q=hello&hl=ko HTTP/1.1 // start-line
Host: www.google.com     // header
            // empty line
{message body}
```

-   요청 메시지도 body 본문을 가질 수 있다.

### HTTP 응답 메시지

```
HTTP/1.1 200 OK // start-line
Content-Type: text/html;charset=UTF-8 // header
Content-Length: 3423
            // empty line
<html>            // message body
    <body>
     ... 
    </body>
</html>
```

-   첫 줄에 HTTP버전과 연결 상태를 나타낸다.

### start-line(요청 메시지)

-   request-line으로도 불린다.
-   request-line: method SP(공백) request-target SP HTTP-version CRLF(엔터)
-   method: GET(조회)
    -   GET, POST, PUT, DELETE...
    -   서버가 수행해야 할 동작을 지정
-   request-target: /search? q=hello&hl=ko(요청 대상)
    -   절대 경로로 시작하는 경로
-   HTTP Vsesion: HTTP1.1

### start-line(응답 메시지)

-   status-line으로도 불린다.
-   status-line: HTTP-version SP status-code SP reason-phrase CRLF
-   HTTP 상태 코드: 요청의 성공, 실패를 나타낸다.
    -   200: 성공
    -   400: 클라이언트 요청 오류
    -   500: 서버 내부 오류

### HTTP 헤더

-   header-field: field-name ":" OWS(띄어쓰기 허용) field-value OWS
-   field-name은 대소문자 구분을 하지 않는다.
-   HTTP 전송에 필요한 모든 부가정보가 들어있다.

### HTTP 메시지 바디

-   실제 전송할 데이터가 들어가 있다.

### HTTP 헤더 표현

-   Content-Type: 표현 데이터의 형식
    -   미디어 타입, 문자 인코딩
-   Content-Encoding: 표현 데이터의 압출 방식
    -   표현 데이터를 압출하기 위해 사용
-   Content-Language: 표현 데이터의 자연 언어
    -   표현 데이터의 자연 언어를 표현
-   Content-Length: 표현 데이터 길이
    -   표현 데이터의 길이
    -   바이트 단위
-   표현 헤더는 전송, 응답 둘 다 사용

## TCP/IP 그리고 UDP

### TCP/IP란?

-   인터넷에서 컴퓨터들이 서로 정보를 주고받는데 쓰이는 프로토콜의 집합

### 인터넷 프로토콜 계층

-   4개의 계층으로 되어있다.
-   Application Layer(응용 계층): HTTP, FTP
    -   특정 서비스를 제공하기 위해 애플리케이션끼리 정보를 주고받을 때 사용
-   Transport Layer(전송 계층): TCP, UDP
    -   송신된 데이터를 수신 측 애플리케이션에 전달하기 위해 사용
-   Internet Layer(인터넷 계층): IP
    -   수신측 까지 데이터를 전달하기 위해 사용
-   Network Access Layer(네트워크 접근 계층): 이더넷, Wi-Fi
    -   네트워크에 직접 연결된 기기 간 데이터 전송을 할 수 있도록 사용(MAC 주소를 활용)

### TCP/IP 패킷 정보

-   IP 패킷: 출발지 IP, 목적지 IP 등이 담겨있다.
-   TCP세그먼트: 출발지 PORT, 목적지 PORT, 전송 제어, 순서, 검증 정보 등이 담겨있다.

## TCP

### TCP 특징

-   연결 지향
    -   클라이언트와 서버를 연결(가상연결) 한 후에 데이터를 전송
-   데이터 전달 보증
    -   서버에서 데이터를 전송받으면, 그에 관한 응답을 클라이언트로 전송
-   순서 보장
    -   잘못된 순서로 패킷이 서버로 들어올 경우 클라이언트로 패킷 재전송 요청을 보낸다.
-   신뢰가 가능한 프로토콜
-   흐름, 혼잡 제어 및 오류를 감지할 수 있다.

### 3 way-handshake

1.  SYN(클라이언트 -> 서버)
2.  SYN + ACK(서버 -> 클라이언트)
3.  ACK + Packet(클라이언트 -> 서버)

-   클라이언트와 서버가 가상연결을 하는 과정

### 4 way-handshake

1.  FIN(클라이언트 -> 서버)
2.  ACK(서버 -> 클라이언트)
3.  FIN(클라이언트 -> 서버)
4.  ACK(서버 -> 클라이언트)

-   클라이언트와 서버가 가상연결을 끊는 과정
-   3.  FIN과정이 일어나기 전, 혹시 모를 잉여 패킷을 서버에서 전송하기 위해 잠시 대기를 한다.(TIME-WAIT상태)

### 흐름 제어

### 혼잡 제어

### 오류 감지

### TCP의 단점

-   조금의 패킷 손실만 있어도 재전송을 요청
-   매번 연결할 때마다 3 way-handshakeing을 하기 때문에 시간적인 손실 발생
-   즉 최적화에 한계가 있다.

## UDP

### UDP란?

-   TCP보다 신뢰성이 떨어지지만 전송 속도가 일반적으로 빠른 프로토콜
-   흐름 제어, 혼잡 제어, 순차 전송 x
-   오로지 PORT정보만 가지고 있다.(Header가 TCP에 비해 간략)
-   체크섬을 통한 오류 감지만 한다.
-   데이터 전송을 최적화해야 할 때 사용
-   애플리케이션에서 추가 작업 필요

## PORT

### PORT란?

-   각각의 프로세스에서 필요한 패킷을 보내기 위해 프로세스를 식별하는 논리 단위

### PORT 번호

-   0 ~ 65535: 할당 가능
-   0 ~ 1023: 잘 알려진 포트, 사용하지 않는 것이 좋다.
    -   FTP - 20, 21
    -   TELNET - 23
    -   HTTP - 80
    -   HTTP - 443

## DNS

### DNS란?

-   도메인 네임 시스템
-   호스트의 도메인 이름을 호스트의 네트워크 주소(IP)로 바꾸거나 그 반대의 변환을 수행할 수 있는 시스템

### DNS사용

-   IP는 기억되기 어려우며, 변경될 가능성도 있다.
-   DNS를 사용해서 도메인과 IP를 서버에 등록시켜 놓으면 도메인을 사용해서 해당 IP주소로 접근이 가능

---

# REFERENCE

[\[10분 테코톡\] 👨‍🏫르윈의 TCP UDP](https://www.youtube.com/watch?v=ikDVGYp5dhg)

[모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC/dashboard)

[HTTP의 진화](https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/Evolution_of_HTTP)

[HTTP/2 소개](https://developers.google.com/web/fundamentals/performance/http2?hl=ko)