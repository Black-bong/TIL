# REST API

## REST API 조건

### Self-descriptive message

- 메시지 스스로 메시지에 대한 설명이 가능해야 한다.
- 서버가 변해서 메시지가 변해도 클라이언트는 그 메시지를 보고 해석이 가능하다.
- 확장 가능한 커뮤니케이션

### HATEOAS

- 링크를 통해 애플리케이션의 상태 변화가 가능해야 한다.
- 링크 정보를 동적으로 바꿀 수 있다.

### Self-descriptive message 해결 방법

1. 미디어 타입을 정의하고 해당 타입의 리소스를 리턴할 때 `Content-Type` 으로 사용한다.
2. `profile` 링크 헤더를 추가한다.

### HATEOAS 해결 방법

- 데이터에 링크 제공

# REFERENCE

[그런 REST API로 괜찮은가](https://www.youtube.com/watch?v=RP_f5dMoHFc)

[스프링 기반 REST API 개발](https://www.inflearn.com/course/spring_rest-api/dashboard)