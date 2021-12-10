# Optional

- T 타입 객체의 래퍼글래스(Optional<T>)
    
    ```java
    public final class Optional<T> {
        private final T value; // T 타입의 참조변수, value에는 모든종류의 객체 저장 가능!(null도 저장가능)
        ...
    }
    ```
    
    Optional로 null을 다루는 이유
    
    - null을 직접 다루는것은 위험하다.(예외가 발생할 수 있기때문에) → 간접적으로 null 다루기
    - null체크 if문 필수 → 코드가 지저분해진다.
- Optional<T>객체를 생성하는 다양한 방법
    
    ```java
    String str = "abc";
    Optional<String> optVal = Optional.of(str);
    Optional<String> optVal = Optioanl.of("abc");
    Optional<String> optVal = Optional.of(null); // NullPorinterException 발생
    Optional<String> optVal = Optional.ofNullable(null); // -> null을 저장할 수 있는 Optional
    ```
    
- null대신 빈 Optional<T>객체를 사용하자
    - Optional<String> optVal = null; → Null로 초기화, 바람직하지 않다.
    - Optional<String> optVal = Optional.<String>empty(); → 빈 객체로 초기화 <String>은 생략 가능
- Optional<T> 객체의 값 가져오기
    
    ```java
    Optional<String> optVal = Optional.of("abc");
    String str1 = optVal.get(); // optVal에 저장된 값을 반환, null이면 예외 발생
    String str2 = optVal.orElse(""); // optVal에 저장된 값이 null일 때는, ""를 반환
    String str3 = optVal.orElseGet(String::new); // 람다식 사용가능 () -> new String()
    String str4 = optVal.orElseThrow(NullPointerException::new); // null이면 예외 발생
    ```
    
- isPresent() - Optional객체의 값이 null이면 false, 아니면 true를 반환
    
    ```java
    if(Optional.ofNullable(str).isPresent()) { // if(str != null)
    	System.out.println(str)
    }
    // 위에 코드 간결화
    Optional.ofNullable(str).ifPresent(System.out::println); // null이 아닐때만 작업을 수행
    ```
    
- Optional 예제
    
    ```java
    1 import java.util.Optional;
    2
    3 class Ex14_0 {
    4     public static void main(String[] args) {
    5         int[] arr = new int[0];
    6         System.out.println("arr.length = " + arr.length);
    7
    8         // Optional<String> opt = null; 오류는 발생하지 않지만 바람직하진 않다.
    9         Optional<String> opt = Optional.empty();
    10         System.out.println("opt=" + opt);
    11
    12         String str = "";
    13         str = opt.orElse("EMPTY"); // Optional에 저장된 값이 null이면 EMPTY 반환
    14         str = opt.orElseGet(String::new);
    15         System.out.println("str=" + str);
    16     }
    17 }
    ```
    
- OptionalInt, OptionalLong, OptionalDouble
    
    ```java
    public final class OptionalInt() {
    	...
    	private final boolean isPresent; // 값이 저장되어 있으면 true
    	private final int value; // int타입의 변수
    ```
    
    - 기본형 값을 감싸는 래퍼클래스
        - Optinal<T>를 사용해도 되지만 성능을 위해 사용한다.
- Optinal 값 가져오기
    
    
    | Optional클래스 | 값을 반환하는 메서드 |
    | --- | --- |
    | Optional<T> | T get() |
    | OptionalInt | int getAsInt() |
    | OptionalLong | long getAsLong() |
    | OptionalDouble | double getAsDouble() |
- 빈 Optional객체와의 비교
    - OptionalInt opt = OptionalInt.of(0) // OptionalInt에 0을 저장
    - OptionalInt opt2 = OptionalInt.empty() // OptionalInt에 0을 저장
- 아무값도 저장안해도 0이 저장되있고, 0으로 저장을해도 0이 저장되어있는데 이 두개를 어떻게 구별할 수 있을까? → isPresent 변수를 사용
    - System.out.println(opt.ispresent()); // true
    - System.out.println(opt2.isPresent()); // false
    - System.out.println(opt.equals(opt2)); // false → value값과 isPresent값이 모두 동일해야 true

Optioanl예제

```java
1 import java.util.*;
2
3 class Ex14_8 {
4     public static void main(String[] args) {
5         Optional<String> optStr = Optional.of("abcde");
6         Optional<Integer> optInt = optStr.map(String::length);
7
8         System.out.println("optStr=" + optStr.get());
9         System.out.println("optInt=" + optInt.get());
10
11         int result1 = Optional.of("123").filter(x -> x.length() > 0).map(Integer::parseInt).get();
12         int result2 = Optional.of("").filter(x -> x.length() > 0).map(Integer::parseInt).orElse(-1);
13
14         System.out.println("result1=" + result1);
15         System.out.println("result2=" + result2);
16
17         Optional.of("456").map(Integer::parseInt).ifPresent(x -> System.out.printf("result3 = %d%n", x));
18
19         OptionalInt optInt1 = OptionalInt.of(0); // 0을 저장
20         OptionalInt optInt2 = OptionalInt.empty(); // 빈 객체를 생성
21
22         System.out.println(optInt1.isPresent()); // true
23         System.out.println(optInt2.isPresent()); // false
24
25         System.out.println(optInt1.getAsInt()); // 0
26        //  System.out.println(optInt2.getAsInt()); NoSuchElementException
27         System.out.println("optInt1=" + optInt1);
28         System.out.println("optInt2=" + optInt2);
29         System.out.println("optInt1.equals(optInt2)?" + optInt1.equals(optInt));
30     }
31 }
```
