# 2일차 학습일지
## 조건문
## 반복문
## 함수와 메서드

**함수**는 무엇일까?

  - 하나의 기능을 수행하는 일련의 코드
  - 함수는 호출하여 사용하고 기능이 수행된 후 값을 반환 할 수 있다.
  - 함수로 구현된 기능은 여러곳에서 호출되어 사용될 수 있다.

함수의 정의 방법
```java
int add(int a, int b) {
  int result;
  result = a + b;
  return result;
}
```

함수는 함수이름(add), 매개변수(int a, int b), 반환값(return result), 몸체로 구성 되어 있다.

`함수의 성격에 따라 매개변수와 반환값이 없을 수도 있다.`

이제 함수에 대해 알아 봤으니 메소드에 대해 알아보자.

--------

**메소드**는 무엇일까?

  - 함수와 동일 하다 볼 수 있으나, 선언된 위치에 따라 함수와 메소드를 나눌수 있다.
  - 메소드는 Class내에 선언되어 있는 함수를 메소드라고 부른다.

메소드의 정의 방법
```java
public Class MethodEx {
  
  public static int add(int a, int b) {
    int result;
    result = a + b;
    return result;
  }
  
  public static void main(String[] args) {
    ...
  }
}
```

메소드 또한 함수와 마찬가지로 구성되어 있으며, 선언 위치만 Class내부에 선언되어 있다는 차이점이 있다.

`JAVA언어는 Class 단위로 구성되어 있기 떄문에 JAVA에서의 함수는 전부 메소드라 볼 수 있다.`

## 파일 읽기 기초
## Call By Value와 Call By Reference
