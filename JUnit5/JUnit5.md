# JUnit5 공부

## JUnit5란?
- 자바 개발자가 가장 많이 사용하고 있는 테스팅 프레임워크
- 자바 8 이상에서 사용가능

### JUnit5 모듈
- Platform: 테스트를 실행해주는 런처 제공, TestEngine API 제공
- Jupiter: TestEngine API의 구현체로 JUnit5를 제공
- Vintage: JUnit4와 3을 지원하는 TestEngine 구현체

### JUnit5 설정
- 스프링 부트 2.0부터는 프로젝트 생성시 자동으로 dependency에 의존성이 추가 되어 있다.
- 스프링 부트 프로젝트가 아니면 dependency에 의존성을 추가해주면 사용이 가능하다.

### 단축키
- 인텔리제이 기준 cmd+shift+t 를 누르면 쉽게 test클래스를 만들수 있다.

```java
class StudyTest {
	@Test
	void create() {
		Study study = new Study();
		assertNotNull(study);
	}
}
```

- 초간단 테스트 예제
- JUnit5부터 메서드 앞에 public 접근제어자를 생략할 수 있다.

## Junit5 Basic Annotation

### @Test
- Test메서드를 지정

### @BeforeAll, @AfterAll
- @BeforeAll: 모든 test가 실행되기전에 딱 한번만 호출
	- static void MethodName() 형식으로 만들어야한다.
- @AfterAll: 모든 test가 실행된 이후 딱 한번만 호출
	- static void MethodName() 형식으로 만들어야한다.

### @BeforeEach, @AfterEach
- @BeforeEach: 모든 test를 실행할때 각각의 test가 실행하기 전에 호출
	- void MethodName() 형식으로 만든다.
- @AfterEach: 모든 test를 실행할때 각각의 test가 실행된 후에 호출
	- void MethodName() 형식으로 만든다.

### @Disable
- @Disable: 해당 test를 실행하지 않는다.

### @DisplayName
- @DisplayName: 클래스나 메서드에 test results창에 해당 test의 Method, Class명이 아니라 지정한 name이 출력되도록 만든다.
- 메서드명에서 사용이 불가능한 이모지, 특수문자를 사용할 수 있다.

### @DisplayNameGeneration
- @DisplayNameGeneration: 클래스나 메서드에 사용이 가능하며, Method, Class의 이름을 짓는 전략을 설정할 수 있다.
	- ex: DisplayNameGenerator.ReplaceUnderscores.class: Method, Class 이름에 들어있는 언더바를 공백으로 변경한다.

## Assertion

### assertEquals(기대값, 테스트 값, 메시지)
```java
assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 DRAFT 상태여야 한다.);
// assertj 사용
assertThat(study.getStatus()).as("스터디를 처음 만들면 DRAFT 상태여야 한다.").isEqualTo(StudyStatus.DRAFT);
```
- 테스트 실패 시 메시지를 출력해줌으로, 나중에 테스트가 깨지거나 했을 경우 어떤 문제가 있는지 파악하기 쉽다.

