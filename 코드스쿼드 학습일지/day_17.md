# 17일차 학습일지

## 학습
- 인터페이스 추가 학습
- 람다 심화
- 스트림 심화

## 인터페이스 추가 학습

### 인터페이스의 장점
- 두 대상 간의 연결, 대화, 소통을 돕는 중간 역할을 한다.
- 선언(설계)과 구현을 분리시킬 수 있게 한다.
- 간접적인 관계의 두 클래스 (인터페이스 사용)
    ```java
    class A {
      public void methodA(I i) {
          i.methodB();
      }
    }
    
    interface I { void methodB(); }
    
    class B implements I {
      public void methodB() {
          System.out.println("methodB()");
      }
    }
    ```
    
    - 코드가 변경이 될때마다 필요한 부분만 변경해서 사용할 수 있어 변경이 쉽다.
- 직접적인 관계의 두 클래스 (인터페이스 미사용)
    ```java
    class A {
      public void methodA(B b) {
          b.methodB();
      }
    }
    
    class B {
      public void methodB() {
          System.out.println("methodB()");
      }
    }
    
    class InterfaceTest {
      public static void main(String args[]) {
          A a = new A();
          a.methodA(new B());
      }
    }
    ```
    - 일반적인 클래스의 사용으로 속도는 빠르나, 변경이 어렵고 하나를 바꾸려면 코드 전체를 바꿔야한다.
- 개발 시간을 단축할 수 있다.
- 표준화가 가능하다.
- 서로 관계없는 클래스들을 관계를 맺어줄 수 있다.

### 인터페이스의 디폴트, static메서드
- 인터페이스에 디폴트 메서드, static메서드 추가 가능(jdk1.8부터)
- 인터페이스에 새로운 메서드(추상 메서드)를 추가하기 어려움.
    - 해결책 → 디폴트 메서드
- 디폴트 메서드가 기존의 메서드와 충돌할 때의 해결책
    - 여러 인터페이스의 디폴트 메서드 간의 충돌
        - 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩해야한다.
    - 디폴트 메서드와 조상 클래스의 메서드 간의 충돌
        - 조상 클래스의 메서드가 상속되고, 디폴트 메서드는 무시된다.

## 람다
## java.util.function 패키지

- 자주 사용되는 다양한 함수형 인터페이스를 제공
- 
## 패키지 종류
- 자주 사용되는 함수형 인터페이스
    | 함수형 인터페이스 | 메서드 | 설명 |
    | --- | --- | --- |
    | java.lang.Runnable | void run() | 매개변수도 없고, 반환값도 없다. |
    | Supplier<T> | T get() | 매개변수는 없고, 반환값만 있다. |
    | Consumer<T> | T → void accept(T t) | Supplier와 반대로 매개변수만 있고 반환값이 없다. |
    | Function<T, R> | T → R apply(T t) → R | 일반적인 함수, 하나의 매개변수를 받아서 결과를 반환 |
    | Predicate<T> | T → boolean test(T t) → boolean | 조건식을 표현하는데 사용됨, 매개변수는 하나 반환타입은 boolean |
- 매개변수가 2개인 함수형 인터페이스
    | 함수형 인터페이스 | 메서드 | 설명 |
    | --- | --- | --- |
    | BiConsumer<T, U> | T, U → void accept(T t, U u) | 구개의 매개변수만 있고, 반환값이 없다 |
    | BiPredicate<T, U> | T, U → boolean test(T t, U u) → B | 조건식을 표현하는데 사용됨 매개변수는 둘, 반환값은 boolean |
    | BiFunction<T, U, R> | T, U → R apply(T t, U u) → R | 두 개의 매개변수를 받아서 하나의 결과를 반환 |
- 매개변수의 타입과 반환타입이 일치하는 함수형 인터페이스
    | 함수형 인터페이스 | 메서드 | 설명 |
    | --- | --- | --- |
    | UnaryOperator<T> (단항 연산자) | T → T apply(T t) → T | Function의 자손, 매개변수의 타입과 반환타입이 같다. |
    | BinaryOperator<T> (이항 연산자) | T, T → T apply(T t, T t) → T | BiFunction의 자손, 매개변수의 타입과 반환타입이 같다. |
    
### 함수형 인터페이스의 결합
- f.andThen(g); → f를 실행 후 g 실행

### Predicate의 결합
- and(), or(), negate()로 두 Predicate를 하나로 결합(defult메서드) 
    ```java
    Predicate<Integer> p = i -> i < 100;
    Predicate<Integer> q = i -> i < 200;
    Predicate<Integer> r = i % 2 == 0;
    
    Predicate<Integer> notp = p.negate(); // i >= 100
    Predicate<Integer> all = notp.and(q).or(r); // 100 <= i && i < 200 || i % 2 == 0
    Predicate<Integer> all2 = notp.and(q.or(r)); // 100 <= i && (i < 200 || i % 2 == 0)
    ```
- isEqual() 사용
    ```java
    Predicate<String> p = Predicate.isEqual(str1); // isEquals()는 static 메서드
    Boolean result = p.test(str2);
    // 위에 코드를 이런식으로 축약 가능
    boolean result = Predicate.isEqual(str1).test(str2);
    ```
