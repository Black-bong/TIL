# @ConfigurationProperties

## 용도
- Spring 에서 Properties 에 등록되어 있는 값을 받아올수 있는 방법 중 하나 이다.

## 사용이유
- @Value 를 통해 yml 파일에 등록되어 있는 값을 가져올때, 여러 불편한 점을 개선해 보고싶어 사용

### @Value 단점
- yml 파일에 등록되어 있는 값을 한곳에서 사용하는 것이 아니라 여러곳에서 사용하다보니, Key 값이 변경되었을 경우 하나하나 @Value 를 사용해서 값을 가져오고 있는 부분을 전부 수정해줘야 하는 불편함이 있었다.
- 단일 값의 주입만 가능하기 때문에 여러 값을 바인딩 하려 하면, 각각의 값을 하나씩 받아와야하는 불편함이 있다.
- 바인딩 값을 Validation 하기 어렵거나 불가능하다.
```java
@Value(value = "${jwt.secretKey}")
private String key;
```
- "${jwt.secretKey}" 문자열로 값을 바인딩하기 때문에 오타가 발생할 여지가 있다.

### @Value 장점
- SpEL 적용

### @ConfigurationProperties 장점
- 여러 값을 받아서 사용이 가능하다.
- Key 값이 변경되면, @ConfigurationProperties 를 선언한 곳에 가서 변경만 해주면 된다.
- 바인딩 값을 Validation 가능하다. 

### @ConfigurationProperties 단점
- SpEL 적용 불가
- 별도의 의존성 추가 필요

## Yml 파일
```yml
oauth2:
  client-id: ...
  client-secret: ...
  redirect-uri: ...
  token-uri: https://github.com/login/oauth/access_token
  user-info-uri: https://api.github.com/user

jwt:
  secretKey: ...
  refreshSecretKey: ...
  accessTokenExpiry : ...
  refreshTokenExpiry : ...
```
- 프로젝트 안에서 사용하는 Yml 파일에 등록되어 있는 값들
- 여기서 Jwt 값을 기존에 @Value 를 사용하여 값을 가져오고 있는데, 여러 불편한 점이 있었다.

## 기존 Provider 클래스
```java
@Component
public class JwtTokenProvider {

    public static final String TOKEN_TYPE = "Bearer";
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final String secretKey;
    private final String refreshSecretKey;

    public JwtTokenProvider(
            @Value("${jwt.accessTokenExpiry}") long accessTokenValidityInMilliseconds,
            @Value("${jwt.refreshTokenExpiry}") long refreshTokenValidityInMilliseconds,
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.refreshSecretKey}") String refreshSecretKey) {
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.secretKey = secretKey;
        this.refreshSecretKey = refreshSecretKey;
    }
    ...
}
```
- 생성자 파라미터에 @Value 어노테이션을 사용하고 있다.
- Provider 클래스 뿐만 아니라 여러 곳에서 @Value 를 사용하고 있는중이다.

## @ConfigurationProperties 적용

### 문제 발생
- 

### 문제 해결

### 해결 방법


---
## REFERENCE
[Spring Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties)