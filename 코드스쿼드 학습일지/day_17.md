# 17일차 학습일지

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
