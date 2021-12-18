# 열거형(enum)

---
>유튜브에서 남궁성님의 자바의 정석 기초편 강의를 보고 요약, 정리한 글입니다.
>
> `강의 내용외에 직접 검색하고, 찾아본 내용도 함께 작성될 수 있습니다.`
>> 출처:
>> <a href= "https://www.youtube.com/watch?v=ODHC-n4mpMY&list=PLW2UjW795-f6xWA2_MUhEVgPauhGl3xIp&index=142">강의링크1</a>
>> <a href= "https://www.youtube.com/watch?v=R0WrMaKoLTE&list=PLW2UjW795-f6xWA2_MUhEVgPauhGl3xIp&index=143">강의링크2</a>
---

### enum이란?

- 서로 관려된 상수들을 같이 묶어 놓은 것

```java
class Card {
  // 카드 무늬
  static final int CLOVER = 0;
  static final int HEART = 1;
  static final int DIAMOND = 2;
  static final int SPADE = 3;
  // 카드 숫자
  static final int TWO = 0;
  static final int THREE = 1;
  static final int FOUR = 2;

  final int kind;
  final int num;
}
```

- if (Card.CLOVER == CARD.TWO)의 결과가 true지만 false이어야 의미상 맞다.

```java
class Card {
  enum Kind { CLOVER, HEART, DIAMOND, SPADE }
  enum Value { TWO, THREE, FOUR }

  final Kind kind;
  final Value value;
}
```

- 위에 코드를 열거형으로 변환
- 자바의 열거형은 타입과 값을 모두 체크
- 여기서 if (Card.CLOVER == CARD.TWO)는 타입이 달라 비교할 수 없으므로 컴파일 에러 발생

## 열거형의 정의와 사용

### 열거형의 정의

- 열겨형을 정의하는 방법: enum 열거형이름 { 상수명1, 상수명2, ...}

```java
enum Direction { EAST, SOUTH, WEST, NORTH } // 각각 0, 1, 2, 3 이라는 값을 갖게된다.
```

- 각각 0, 1, 2, 3 이라는 값이 자동으로 주어지게 된다.

### 열거형의 사용

```java
class Unit {
  int x, y;
  Direction dir;

  void init() {
    dir = Direction.EAST;
  }
}
```

- 열거형 인스턴스 변수를 선언
- 유닛의 방향을 EAST로 초기화

### 열거형 상수의 비교

```java
if (dir == Direction.EAST {
  x++;
} else if (dir > Direction.WEST) {
  ...
} else if (dir.compareTo(Direction.WEST) > 0 {
  ...
}
```

- ==와 compareTo() 사용가능
- 열거형 상수에는 비교연산자를 사용할 수 없다.
- `compareTo()` 0(같다), 음수(오른쪽이 크다), 양수(왼쪽이 크다) 반환

## 열거형의 조상

### java.lang.Enum

- 모든 열거형은 Enum의 자손이며, 아래의 메서드를 상속받는다.

| 메소드 | 설명 |
| --- | --- |
| Class<E> getDeclaringClass() | 열거형의 Class객체를 반환 |
| String name() | 열거형 상수의 이름을 문자열로 반환 |
| int ordinal() | 열거형 상수가 정의된 순서를 반환(0부터 시작) |
| T valueOf(Class<T> enum Type, String name | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환 |
- values(), valueOf()는 컴파일러가 자동으로 추가

## 예제

```java
 1 enum Direction {EAST, SOUTH, WEST, NORTH };
 2
 3 class Ex12_5 {
 4     public static void main(String[] args) {
 5         Direction d1 = Direction.EAST;
 6         Direction d2 = Direction.valueOf("WAST");
 7         Direction d3 = Enum.valueOf(Direction.class, "EAST");
 8
 9         System.out.println("d1=" + d1);
10         System.out.println("d2=" + d2);
11         System.out.println("d3=" + d3);
12
13         System.out.println("d1==d2 ? " + (d1==d2));
14         System.out.println("d1==d3 ? " + (d1==d3));
15         System.out.println("d1.equals(d3) ? " + d1.equals(d3));
16         System.out.println("d1.compareTo(d3) ? " + (d1.compareTo(d3)));
17         System.out.println("d1.compareTo(d2) ? " + (d1.compareTo(d2)));
18
19         switch(d1) {
20             case EAST:
21                 System.out.println("EAST");
22                 break;
23             case SOUTH:
24                 System.out.println("SOUTH");
25                 break;
26             case WEST:
27                 System.out.println("WEST");
28                 break;
29             case NORTH:
30                 System.out.println("NORTH");
31                 break;
32         }
33
34         Direction[] dArr = Direction.values();
35
36         for (Direction d : dArr) {
37             System.out.printf("%s=%d%n", d.name(), d.ordinal());
38         }
39     }
40 }
```

## 열거형에 멤버 추가하기

```java
enum Direction { EAST(1), SOUTH(5), WEST(-1), NORTH(10) }
```

- 불연속적인 열거형 상수의 경우, 원하는 값을 괄호 ()안에 적는다.

```java
enum Direction {
  EAST(1),
  SOUTH(5),
  WEST(-1),
  NORTH(10);

  private final int value;
  Direction(int value) { this.value = value; }

  public int getValue() { return value; }
}
```

- 괄호를 사용하려면, 인스턴스 변수와 생성자를 새로 추가해 줘야 한다.

```java
Direction d = new Direction(1); // 에러
```

- 열거형의 생성자는 묵시적으로 `private` 이므로, 외부에서 객체생성이 불가능하다.

## 예제

```java
 1 enum Direction2 {
 2     EAST(1, ">"),
 3     SOUTH(2, "V"),
 4     WEST(3, "<"),
 5     NORTH(4, "^");
 6
 7     private static final Direction2[] DIR_ARR = Direction2.values();
 8     private final int value;
 9     private final String symbol;
10
11     Direction2(int value, String symbol) {
12         this.value = value;
13         this.symbol = symbol;
14     }
15
16     public int getValue() { return value; }
17     public String getSymbol() { return symbol; }
18
19     public static Direction2 of(int dir) {
20         if (dir < 1 || dir > 4) {
21             throw new IllegalArgumentException("Invalid value : " + dir);
22         }
23         return DIR_ARR[dir - 1];
24     }
25
26     public Direction2 rotate(int num) {
27         num = num % 4;
28
29         if (num < 0) {
30             num += 4;
31         }
32
33         return DIR_ARR[(value - 1 + num) % 4];
34     }
35
36     @Override
37     public String toString() {
38         return name() + getSymbol();
39     }
40 }
41
42 class Ex12_6 {
43     public static void main(String[] args) {
44         for (Direction2 d : Direction2.values()) {
45             System.out.printf("%s=%d%n", d.name(), d.getValue());
46         }
47
48         Direction2 d1 = Direction2.EAST;
49         Direction2 d2 = Direction2.of(1);
50
51         System.out.printf("d1=%s, %d%n", d1.name(), d1.getValue());
52         System.out.printf("d2=%s, %d%n", d2.name(), d2.getValue());
53         System.out.println(Direction2.EAST.rotate(1));
54         System.out.println(Direction2.EAST.rotate(2));
55         System.out.println(Direction2.EAST.rotate(-1));
56         System.out.println(Direction2.EAST.rotate(-2));
57     }
58 }
```
