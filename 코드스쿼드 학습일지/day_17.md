# 17일차 학습일지

## 학습
- 인터페이스 추가 학습
- 람다 심화
- 스트림 심화
    - 내일 보충..

## 인터페이스 추가 학습
> 남궁성님의 자바의 정석을 보고 학습 및 정리한 내용입니다.
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

## 람다심화
> 남궁성님의 자바의 정석을 보고 학습 및 정리한 내용입니다.
### java.util.function 패키지

- 자주 사용되는 다양한 함수형 인터페이스를 제공

### 패키지 종류
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
    | BiConsumer<T, U> | T, U → void accept(T t, U u) | 두 개의 매개변수만 있고, 반환값이 없다 |
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
### 컬렉션 프레임웍과 함수형 인터페이스
- 함수형 인터페이스를 사용하는 컬렉션 프레임웍의 메서드
    | 함수형 인터페이스 | 메서드 | 설명 |
    | --- | --- | --- |
    | Collection | boolean removeIf(Predicate<E> filter) | 조건에 맞는 요소를 삭제 |
    | List | void replaceAll(UnaryOperator<E> operator) | 모든 요소를 변환하여 대체 |
    | Iterable | void forEach(Consumer<T> action) | 모든 요소에 작업 action을 수행 |
    | Map | V compute(K key, BiFunction<K ,V ,V> f) | 지정된 키의 값에 작업 f를 수행 |
    | Map | V computeifAbsent(K key, Finction<k, V> f) | 키가 없으면, 작업 f 수행 후 추가 |
    | Map | V computeIfPersent(K key, BiFunction<K, V, V> f) | 지정된 키가 있을때, 작업 f 수행 |
    | Map | V merge(K key, V value, BiFunction<V, V, V> f) | 모든 요소에 병합작업 f를 수행 |
    | Map | void forEach(BiConsumer<K, V> action) | 모든 요소에 작업 action을 수행 |
    | Map | void replaceAll(BiFunction<K, V, V> f) | 모든 요소에 치환작업 f를 수행 |
    - 사용 예
    ```java
    list.forEach(i -> System.out.println(i + ","));
    list.removeIf(x -> x % 2 == 0 || x % 3 == 0);
    list.replaceAll(i -> i * 10);
    map.forEach((k, v) -> System.out.println("k:" + k + "," + "v:" + v);
    ```
    
## 논리곱, 논리합
- 문제 풀다 헤깔림 다시 정리
    
    | A | B | A and B | A or B | !A | A ^ B(XOR) |
    | --- | --- | --- | --- | --- | --- |
    | TRUE | TRUE | TRUE | TRUE | FALSE | FALSE |
    | TRUE | FALSE | FALSE | TRUE | FALSE | TRUE |
    | FALSE | TRUE | FALSE | TRUE | TRUE | TRUE |
    | FALSE | FALSE | FALSE | FALSE | TURE | FALSE |

## 메서드, 생성자참조
    
### 메서드 참조(클래스이름::메서드이름)
- 하나의 메서드만 호출하는 람다식은 메서드 참조로 더 간단히 할 수 있다.
    | 종류 | 람다 | 메서드 참조 |
    | --- | --- | --- |
    | static 메서드 참조 | (x) → ClassName.method(x) | ClassName::method |
    | 인스턴스메서드 참조 | (obj, x) → obj.method(x) | ClassName::method |
    | 특정 객체 인스턴스메서드 참조 | (x) → obj.method(x) | obj::method |
- static메서드 참조
    ```java
    Integer method(String s) {
        return Integer.parseInt(s);
    }
    // 위에 코드 축약
    Function<String, Integer> f = Integer::parseInt; // 메서드 참조
    ```
### 생성자의 메서드 참조
- 생성자와 메서드 참조
    ```java
    Supplier<MyClass> s = () -> new MyClass();
    // 위에 코드 축약
    Supplier<MyClass> s = MyClass::new;
    // 매개변수가 있는 객체 생성
    Function<Integer, MyClass> s = MyClass::new;
    ```
- 배열과 메서드 참조
    ```java
    Function<Integer, int[]> f2 = int[]::new;
    ```
