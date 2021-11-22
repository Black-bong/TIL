# 16일차 학습일지

## GUI 프로그래밍 학습하기

## 추가학습
- super
- 람다와 스트림
- for-each, map, filter, reduce
- stream foreach, enhanced foreach
- 자바의 인터페이스

## Super
> 남궁성님 자바의 정석을 참고하여 공부하고, 정리한 내용입니다.
### 참조변수 super
- this -> iv(인스턴스변수), lv(지역변수) 구별 시 사용 , super -> 조상, 자기자신 멤버 구별 시 사용
- 객체 자신을 가리키는 참조변수, 인스턴스 메서드내에만 존재
- 조상의 멤버를 자신의 멤버와 구별할 때 사용
    ```java
    class Ex7_2 {
        public static void main(String args[]) {
            Child c = new Child();
            c.method();
        }
    }
    
    class Parent {
        int x = 10; /* super.x */
    }
    
    class Child extends Parent {
        int x = 20; // this.x
    
        void method() {
            System.out.println("x=" + x);
            System.out.println("this.x=" + this.x);
            System.out.println("super.x=" + super.x);
        }
    }
    ```
    - 결과창
    ```java
    x = 20
    this.x = 20
    super.x = 10
    ```
- 자기자신의 x가 없을 경우 this.x출력 시 조상의 x 값을 가져온다
    - 상속받은 값을 경우 조상의 것도 되지만 자기 자신의 것도 되기 떄문.
    
### super() - 조상의 생성자
- 조상의 생성자를 호출할 때 사용 (참조변수 super와 전혀 다른 개념)
- 조상의 멤버는 조상의 생성자를 호출해서 초기화
- 자식의 생성자는 자식이 선언한 것만 초기화 해야한다.
    ```java
    class Point {
        int x, y;
    
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class Point3D extends Point {
        int z;
    
        Point3D(int x, int y, int z) {
            super(x, y); // 조상클래스의 생성자 Point(int x, int y)를 호출
            this.z = z; // 자신의 멤버를 초기화
        }
    }
    ```
- 생성자의 첫 줄에 반드시 생성자를 호출해야한다.
- 그렇지 않으면 컴파일러가 생성자의 첫 줄에 super();를 자동 삽입한다.
    ```java
    class Point extends Object {
        int x;
        int y;
    
        Point() {
            this(0, 0);
        }
        Point(int x, int y) {
            //super();
            this.x = x;
            this.y = y;
        }
    }
    ```
- 클래스 생성 시 기본 생성자는 필수로 만들어줘야 한다.
    ```java
    class Point {
        int x;
        int y;
    
        Point() {
        }
    
        Point int x, int y) {
            this();
            this.x = x;
            this.y = y;
        }
    }
    
    class Point3D extends Point {
        int z;
    
        Point3D(int x, int y, int z) {
            super(x, y);
            // super(); 컴파일러가 자동으로 super() 삽입
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    
    class PointTest {
        public static void main(String[] args) {
            // 포인트 클래스에 기본생성자가 없고, 생성자의 첫줄에 생성자 호출이 없어 오류 발생 둘중 하나를 해결해야 정상적으로 작동된다.
            Point3D p3 = new Point3D(1, 2, 3);
        }
    }
    
    class Ex7_2 {
        public static void main(String args[]) {
            Child c = new Child();
            c.method();
        }
    }
    
    class Parent {
        int x = 10; /* super.x */
    }
    
    class Child extends Parent {
        int x = 20; // this.x
    
        void method() {
            System.out.println("x=" + x);
            System.out.println("this.x=" + this.x);
            System.out.println("super.x=" + super.x);
        }
    }
    ```
## 람다식
>패스트캠퍼스 JAVA 인강을 보고 정리한 내용입니다.

### 람다식이란?
- 자바에서 함수형 프로그래밍을 구현하는 방식
- 클래스를 생성하지 않고 함수의 호출만으로 기능을 수행
- 자바 8부터 지원되는 기능
- 순수 함수를 구현하고 호출
- 매개 변수만을 사용하도록 만든 함수로 외부 자료에 부수적인 영향(side effect)가 발생하지 않도록 함
- 입력 받은 자료를 기반으로 수행되고 외부에 영향을 미치지 않으므로 병렬처리 등이 가능
- 안정적인 확장성 있는 프로그래밍 방식

### 람다식 문법
- 매개 변수가 하나인 경우 괄호 생략가능(두 개인경우는 괄호를 생략할 수 없다.)
    - str → {System.out.println(str);}
- 중괄호 안의 구현부가 한 문장인 경우 중괄호 생략
    - str → System.out.println(str);
- 중괄호 안의 구현부가 한 문장이라도 return문은 중괄호를 생략할 수 없다.
    - str → return str.length(); //오류 발생
- 중괄호 안의 구현부가 반환문 하나라면 return과 중괄호를 모두 생략할 수 있다.
    - (x, y) → x + y     //두 값을 더하여 반환
    - str → str.length() //문자열 길이 반환
```java
interface PrintString {
    void showString(String str);
}

public class TestLambda {
    public static void main(String[] args) {

        PrintString lambdaStr = System.out::println;
        lambdaStr.showString("Test");

        showMyString(lambdaStr);

        PrintString test = returnString();
        test.showString("Test3");
    }

    private static void showMyString(PrintString p) {
        p.showString("Test2");
    }

    public static PrintString returnString() {
        return s -> System.out.println(s + "!!!");
    }
}
```
- 함수의 구현부가 변수처럼 사용된다.
