# 4일차 학습일지

## 깃 관리

### .gitignore란?
   - 프로젝트 작업 시 로컬 환경의 정보나 빌드 정보 등 원격 저장소에 저장되지 말아야할 파일들이 원격 저장소로 실수로 업로드 되지 않도록 관리해주는 파일
   - 정의한 정보들에 해당하는 파일들에 대해 git track하지 않도록 설정하는 역활을 한다.

### .gitignore 파일 만들기
   1. 프로젝트 안에서 항상 최상위 Directory에 존재해야한다.
   2. 예시
   ```
   ### IntelliJ IDEA ###
  .idea
  *.iws
  *.iml
  *.ipr
  out/
  !**/src/main/**/out/
  !**/src/test/**/out/
  ```
### .gitignore 적용하기
  1. .gitignore파일을 생성 후 저장소에 함께 push하게 되면 바로 적용된다.
  2. 기존 프로젝트에 적용이 되지 않을 경우 directory이름이나 .gitignore파일 위치를 다시 확인해 보자.

## 자바의 타입

### primitive type란?
   > 원시 자료형은 컴퓨터 과학에서 프로그래밍 언어가 제공하는 자료형 중 하나다. 원시형은 또한 내장형이나 기본형으로도 불린다.

### java언어의 primitive type

   |키워드|크기|설명|
   |-----|----|----|
   |boolean|1bit|`ture`혹은 `false`로 표현되는 논리형|
   |char|2byte|문자형|
   |byte|1byte|-128~127의 범위를 가지는 정수형|
   |short|2byte|-32768~32767의 범위를 가지는 정수형|
   |int|4byte|-2147483648~2147483647의 범위를 가지는 정수형|
   |long|8byte|int보다 더 넓은 범위를 가지는 정수형|
   |float|4byte|실수형|
   |double|8byte|float보다 두배의 정홛성을 가지는 실수형|
   
   - 예전에는 컴퓨터 메모리 성능이 안좋아서(작아서) 크기가 작은 자료형을 사용했지만, 현대에는 컴퓨터 성능도 좋아지고 정확도가 중요하여 크기가 큰 자료형을 주로 사용한다.

## Call by Value, Call by Reference

   - java언어에서는 따지고 보면 Call by Reference도 참조를 할때 주소의 `값`을 참조하는것이기 때문에 곧 Call by Value라고 할 수 있다.
   - java에서는 Call by Reference가 없다고 봐야한다.

### call by Value란?
   > 값을 호출하는 것을 의미합니다. 전달받은 값을 복사하여 처리합니다. 즉 전달받은 값을 변경하여도 원본은 변경되지 않습니다.

### call by Reference란?
   > 참조에 의한 호출을 의미합니다. 전달받은 값을 직접 참조합니다. 즉 전달받은 값을 변경할 경우 원본도 같이 변경이 됩니다.
   
## 자바의 메모리 영역
   - 자바의 메모리 영역은 상당히 복잡하다.
   - 크게 class(method)영역, 힙영역, 스택영역으로 나뉜다.

### Class 영역이란?
   - 바이트 코드가 저장되는 영역, static또한 이곳에 저장된다.

### 힙 영역
   - new 로 생성된 모든것은 힙 영역에 생성된다.
   - 모든 객체는 힙 영역에서 만들어진다.

### 스택 영역
   - 지역변수는 스택 영역에 저장된다.
   - 매개변수 또한 스택 영역에 저장된다.
   - 메소드 호출 시 스택이 생성된다.

## 재귀 함수

### 재귀 함수란?
   - 어떤 함수에서 자신을 다시 호출하여 작업을 수행하는 방식의 함수를 의미한다.

### 재귀함수 예제
```java
public class RecursionFunctionEx {
    public static void main(String[] args) {
        int n = 5;
        System.out.println("1부터 " + n + "까지의 합계: " + add(n));
    }

    private static int add(int n) {
        if (n == 0) {
            return 0;
        }
        return n += add(n - 1);
    }
}
```
```
1부터 5까지의 합계: 15
```

## 클래스와 객체


## 자료구조의 기초

### java언어에서 가장 많이 사용하는 자료구조는?
   - List
----------

# Reference
- https://deveric.tistory.com/92 call by reference
