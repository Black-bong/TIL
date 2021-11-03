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

**Scanner와 BufferdReader()의 차이**

Scanner란?
  - java.util 패키지 안에 존재하는 클래스이다.
  - 기본적인 데이터 타입들을 Scanner의 메소드를 사용하여 입력받을 수 있다.
  - Scanner scan = new Scanner(System.in) 객체 선언을 통해 사용할 수 있다.
  
  `System.in은 자바 표준입력 스트림이다. 추후 공부해야 할 내용중 하나이다.`
  
  - Scanner의 주요 메소드로는 next(), nextLine(), next+자료형()`int, Short등등`이 있으며 리턴하고자 하는 자료형에 따라 사용하면 된다.
  - 추가적으로 Scanner 사용 후 close()를 통해 사용 종료를 해주는 것이 좋으며, boolean hasNext()도 있는데 입력값이 들어 올때까지 대기하다 입력값이 들어오면 true를 반환한다.
  - 추가적으로 next()와 nextLine()은 주로 문자열을 nextLine()은 콘솔 창에서 입력을 받을때 한 줄에 있으면 모두 문자열로 인식하여 리턴하지만 next()의 경우 개행 문자가 발생 되는 시점 까지만 리턴해준다.

Scanner의 정의
```java
import java.util.Scanner;

public class ScannerEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String scan = scanner.next();
        scanner.close();
        
        System.out.println("scan = " + scan);
    }
}
```
  - java.util패키지 안에 있는 Scanner클래스를 import한다.
  - 객체를 생성한다.
  - 입력값을 받은 후 종료한다.

-----

hello world를 입력 했을 때 next()의 결과

```
hello world
scan = hello
```

hello world를 입력 했을 때 nextLine()의 결과

```
hello world
scan = hello world
```

BufferdReader()란?

  - 자바의 java.io패키지 안에 존재하는 문자 단위 입력을 위한 스트림 클래스의 종류 중 하나다.
  - 문자 단위 입력을 위한 최상위 클래스 Reader클래스를 상속 받고 있다.
  - 문자 단위 입력을 받기 위한 보조 스트림이다.

입출력 스트림이란?
  
  - 자바에서 모든 입출력(I/O)는 스트림을 통해 이루어진다.
  - 데이터를 운반하는 연결통로라고 생각하면 된다.
  - 스트림은 단방향 통신만 가능하기 때문에 하나의 스트림으로 동시에 입출력을 처리하는것은 불가능하다.
  - 큐와 같은 FIFO(First In First Out) 구조로 되어있다.
  - 자바에는 다양한 입출력 스트림이 존재하며, 바이트 단위와 문자 단위로 나눠져 있다.
  - 문자/바이트 단위로 스트림의 종류를 나누고 그 안에서 데이터를 직접읽는 기반스트림과 기반스트림의 기능을 강화해주는 보조 스트림으로 나눠진다.

`자바 입출력 스트림에 대해서는 나중에 좀 더 알아보고 우선 여기서는 BufferdReader에 대해서 알아보자`

BufferdReader의 정의

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderEx {
    public static void main(String[] args) throws IOException {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
        // System.in으로 입력 받을때 아무런 값이 안들어오면 오류가 발생하기 때문에 예외 처리를 필수적으로 해줘야한다.

        String readLineEx = buf.readLine();

        buf.close();

        System.out.println("readLineEx = " + readLineEx);
    }
}
```
  - java.io패키지 안에 있는 BufferedReader와 InPutStreamReader 그리고 IOException을 import해줘야 한다.
  - 맨 처음 System.io에서 입력 받은 바이트 단위의 데이터를 InputStreamReader를 통해 문자로 읽을 수 있도록 만들 후 BufferdReader로 한 문장으로 읽을 수 있도록 만들면 된다.
  - BufferedReader에는 read()와 readLine() 이라는 메소드가 있다.
  - Scanner와 마찬가지로 입력을 받은 후 close를 이용해 종료를 해줘야 한다.

## Call By Value와 Call By Reference
