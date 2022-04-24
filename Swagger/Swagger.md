# Swagger!!

## ⁉️  Swagger란?

- 간단한 설정으로 프로젝트에서 지정한 URL들을 HTML화면으로 확인할 수 있게 해주는 프로젝트
- 내가 만든 프로젝트의 URL을 모아 문서화 시켜주는 거라 생각하면 될거 같다.

## 👀 자주 사용되는 Swagger 프로젝트

- SpringFox Swagger2: 적용된 프로젝트의 응답, 요청, 예시 등의 정보를 JSON 쌍으로 만들어 준다.
- SpringFox UI: Swagger2를 통해 만든 JSON 쌍을 상호작용하기 좋은 웹 페이지로 만들어 준다.
- SpringFox Bean Vaildator: Swagger가 문서화를 진행하면서 Bean Validation Annotation에 적용된 내용에 대한 정보를 추가로 저장한다.
- SpringFox Data REST: @Entity와 @Repository를 찾아 엔티티에 대한 사양 정보를 추가로 문서화 한다.

## ⚙️  Swagger설정

### SpringBoot & Gradle

1. 의존성 추가

```hash
compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.5.0'
compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.5.0'
```

- build.gradle에 의존성 추가
- 추가적으로 필요한 의존성은[MVNrepository](https://mvnrepository.com/search?q=springfox)에 접속하여 필요한 의존성 추가 할수 있다.

❗️❗️version 3.x.x 이상 사용 시 Swagger 접속 방법이 변경
- 기존: localhost:8080/swagger-ui.html
- 변경: http://localhost:8080/swagger-ui/index.html

2. 프로젝트에 Swagger 설정 Bean을 등록

```java
:
```

### SpringBoot & Maven

1. 의존성 추가

```bash
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

- pom.xml에 의존성 추가
- 추가적으로 필요한 의존성은[MVNrepository](https://mvnrepository.com/search?q=springfox)에 접속하여 필요한 의존성 추가 할수 있다.
