# 2일차 학습일지
## 조건문

**조건문**

어제 작성한 조건문(알람시계)을 좀 더 보기 좋게 작성해 보자
```java
  static void calculateForTime(int hour, int min) {
      int resultHour = 0;
      int resultMin = 0;

      resultMin = min - MINUS_MIN;

      if (resultMin < 0) {
          hour--;
          if (hour < 0) {
              hour = 23;
          }
          resultMin = ONE_HOUR - Math.abs(resultMin);
      }
      resultHour = hour;
      System.out.println(resultHour + " " + resultMin);
  }
```
시간을 구하는 메소드 안에서 시(hour)와 분(min)을 구하려 했더니 중첩 if문을 사용하면서 보기 안 좋은거 같아

둘을 나눠서 구현하는게 더 깔끔할거 같아서 새로 작성을 해보았다.

----------

시(hour)와 분(min)을 구하는 메소드를 각각 구현한 후 출력하도록 변경해 보았다.

```java
  static void calculateForTime(int hour, int min) {
      min = min - MINUS_MIN;

      if (min < 0) {
          min = calculateForMin(min);
          hour = calculateForHour(hour);
      }
      System.out.println(hour + " " + min);
  }

  static int calculateForHour(int h) {
      h--;

      if (h < 0) {
          return 23;
      }
      return h;
  }

  static int calculateForMin(int m) {

      return ONE_HOUR - Math.abs(m);
  }
```

## 반복문
**for반복문**

  - 어제 작성한 for문을 활용하여 return, break, contiue의 차이점을 알아보자

```java
  static void gugudan(int start, int end) {
      for (int i=start;i<=end;i++) {
          for (int j=1;j<=9;j++ ) {
              if (j == 2) {
                  break; // return, contiue
              }
              System.out.println(i + "*" + j + "=" + i * j);
          }
          System.out.println("------------------");
      }
  }
```

if 조건문 안에 break와 return을 넣어보고 결과를 확인해 보자.

  - break의 결과 안쪽 for문에서 빠져나와 바깥쪽 for문의 조건식(i <= end)으로 이동 후 반복문이 계속 진행된다.
  ```
  1*1=1
  ------------------
  2*1=2
  ------------------
  3*1=3
  ------------------
  ```
  - return의 결과 이중 for문 전체가 종료 되고 결과값이 출력된다.
  ```
  1*1=1
  ```
  - contiue의 결과 j값이 2가 될 경우만 안쪽 for문의 진행을 멈추고 j값이 증감식에 의해 변경될 경우 계속 진행된다.
  ```
  1*1=1
  1*3=3
  1*4=4
  1*5=5
  1*6=6
  1*7=7
  1*8=8
  1*9=9
  ------------------
  ...
  ```
**while반복문**

  - while문을 숙지 하기

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WhileLoopEx {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        int firstNumber = Integer.parseInt(buf.readLine());
        System.out.println(cycle(firstNumber));
    }

    private static int cycle(int number) {
        int cycleCount = 0;
        int temp = number;

        while (true) {
            cycleCount++;
            int value = separationOfNumbers(number);
            if (temp == value) {
                return cycleCount;
            }
            number = value;
        }
    }

    private static int separationOfNumbers(int n) {
        int value = (n / 10) + (n % 10);
        return (n % 10 * 10) + (value % 10);
    }
}
```

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
