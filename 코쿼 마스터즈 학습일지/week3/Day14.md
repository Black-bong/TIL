# 14일차 학습일지

## 함수형 프로그래밍

### 함수형 프로그래밍이란?
- 자료 처리를 수학적 함수의 계산으로 표현
- 상태와 가변 데이터 대신 불변 데이터를 프로그래밍 패러다임으로 사용한다.
  - 순수 함수(Pure Function)
  - 불변(Immutable) 타입
  - 일급 함수와 고차 함수
  - 자동 메모리 관리
  - 타입 시스템(타입 추론)

### 함수형 프로그래밍 요소
- 고계 함수
- 일급 함수
- 커링과 부분 적용
- 재귀와 꼬리 재귀 최적화
- 멱등성
- 순수 함수와 참조 투명성
- 불변성과 영속적 자료구조
- 메모리제이션

### 순수 함수
- 부작용(side-effect)이 없는 함수
- 변경 불가한 데이터(immutable)
- 참조 투명성(referential transparency)
- 느긋한 계산(Lazy)
- 인자값에만 의존하여, 같은 값이 들어오면 항상 같은 값을 반환한다.


### 함수형 접근방식과 명령형 접근방식의 차이
|특성|함수형 접근방식|명령형 접근방식|
|---|-----------|-----------|
|프로그래머 관심 사항|원하는 정보가 무엇이며, 어떤 변환이 필요한가|작업을 수행하는 로직, 상태 변경 추적|
|상태(값) 변경|없음(값을 복사)|상태값 참고가 중요함|
|실행 절차|중요도 낮음|중요도 높음|
|제어 흐름|재귀를 비롯한 함수 호출|반복문, 조건문, 함수 호출|
|구현 단위|일급 객체와 데이터 콜랙션으로 함수 사용|구조체 또는 클래스|


### 함수형 프로그래을 사용하는 이유
- 코드의 간결화
- 

## 모나드(MONAD)
- 두 자연변환을 연산으로 갖는 모노이달 카테고리
  - e.g. Optional<Optional<T>> -> Optional<T>, Stream<Stream<T>> -> Stream<T>
  - flatMap(Function<? super T, ? extends Stream<? extedns R>> mapper)
- Join(Flat) + Map = Bind(FlatMap) 연사을 이용하면 함수의 합성이 용이해진다.

### 함자(Functor)
- 한 범주의 대상과 사상을 다른 범주로 대응하는 함수
- 대상과 사상을 매핑한 것
- Stream.Optional의 map이 바로 함자 연산이다.
- 함자의 문제점: 함자가 중첩되면 정상적인 합성이 불가능해진다.

### 모노이드(Monoid)
- 항등원을 갖고 결합법칙을 따르는 이항연산을 갖춘 집합
  - e.g. 자연수(0 이상)의 덧셈과 곱셈, 리스트 Concat

## 자바의 함수형 프로그래밍

### Lambda
```java
int i = 0;
list.forEach(element -> {
    System.out.println(element + i++);
});
```
- Variable used in lambda expression should be final or effectivety final 오류 발생
- 익명 함수 내부의 변수는 불변하거나 실질적으로 불현해야한다.
```java
int[] j = {0};
list.forEach(element -> {
    System.out.println(element + i[0]++);
});
```
- 배열을 사용해 회피 가능
- 개인적인 생각인데 그냥 꼼수 같은건가?

```java
byte[] letters = { 'W', 'O', 'O', 'W', 'A' };

IntStream.range(0, 100).forEach(x -> {
    System.out.write(letters);
})
```
- Unhandled exception: java.io.IOException 오류 발생
```java
byte[] letters = { 'W', 'O', 'O', 'W', 'A' };

IntStream.range(0, 100).forEach(x -> {
    try {
        System.out.write(letters);
    } catch (IOException e) {
        ...
    }
})
```
- 기존에 오류를 해결하던 방식
- 익명 함수는 Checked Exception을 던질 수 없어, 반드시 내부에서 Try-Catch문을 사용 할 수 밖에 없다.
```java
@FuntionalInterface
interface Subroutine {
    void execute() throws Exception;
}

public static void execute(Subroutine subroutine) {
    try {
        subroutine.execute();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

IntStream.range(0, 100).forEach(x -> {
    Try.execute(() -> System.out.write(letters));
})
```
- Exception을 던지는 Functional Interface를 직접 정의하여 회피 가능

### Optional
- Optional은 값이 존재하거나 존재하지 않음을 나타내는데, 이를 Exception 대신에 활용할 수 있다.
- Exception의 종류를 알아야 할 경우 Eiter(Result)타입을 쓸 수 있으나 자바에는 없다.
```java
public TestUser(String name, int age, String address) {
    this.name = validateIfEmpty(name).orElseThrow(IllegalArgumentException::new);
    this.age = validateIfAdult(age).orElseThrow(IllegalArgumentException::new);
    this.address = validateIfEmpty(address).orElseThrow(IllegalArgumentException::new);
}

public static Optional<String> validateIfEmpty(String input) {
    return Optional.ofNullable(input).filter(x -> x.length() > 0);
}

public static Optional<Integer> validateIfAdult(int age) {
    return Optional.of(age).filter(x -> x > MIN_AGE_OF_ADULT);
}
```
- 일반적으로 생성자에서 Optional을 통해 null check 및 추가적인 validation을 하여 Exception을 발생 시킬 수 있다.
```java
public static Optional<TestUser> of(String name, int age, String address) {
    return validateIfEmpty(name).flatMap(
        validName -> validateIfAdult(age).flatMap(
            validAge -> validateIfEmpty(address).map(
                validAddress -> new TestUser(validName, validAge, validAddress)
            )
        )
    );
}

private TestUser(String name, int age, String address) {
    this.name = nema;
    this.age = age;
    this.address = address;
}
```
- Optional을 사용해서 Exception을 발생시키지 않고 팩토리 메서드를 통해 Optional 타입으로 반환할 수도 있다.
- 반환 타입이 일정하므로 예외 처리 없이 일관성 있게 코드를 짤 수 있다.
- 단, 모든 예외 처리를 Optional로 대신하는건 좋지 않다.
  - 0으로 나눌때의 ArithmeticException을 발생시키지 않기 위해 Optional을 사용하여 반환 할 경우 if문으로 0을 거르는 것 이상의 비용이 발생
----

# 추가 학습 사항
- 자바 팩토리 패턴
  - 팩토리 메서드 패턴
  - 추상 팩토리 팩턴
- 자바 빌더 패턴
- Optional 복습
- 자바 싱글톤 패턴 복습

----

# Reference
<a href = "https://www.youtube.com/watch?v=ii5hnSCE6No">[10분 테코톡] 🍩도넛의 함수형 프로그래밍</a>
<a href = "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html#flatMap(java.util.function.Function)">Stream</a>