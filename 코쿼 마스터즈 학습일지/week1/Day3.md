# 1주차 알고리즘 문제

## 필수문제
- [X] Bjo1009문제(분산처리) <a href="https://www.acmicpc.net/problem/1009">문제링크</a>
  - 코드: <a href = "https://github.com/geombong/codesquad_masters_2022BE/blob/master/week1/src/algorithm/Bjo1009.java">코드</a>
  - 풀이: 처음에는 간단하게 제곱으로 접근했다가, 자료형의 범위 초과로 문제해결이 어렵다는걸 파악
  ```java
  public static int solve(int a, int b) {
    int result = 1;
    for (int i = 0; i < b; i++) {
        result = (result * a) % 10;
    }
    return result;
  }
  ```
  - 해결: 필요한 값은 데이터값 전부가 아니라 1의 자리값만 필요하기 때문에 거듭제곱되는 동안 1의 자리만 구해서 반환하는 식으로 해결
  - 또 다른 풀이
  - 코드: <a href = "https://github.com/geombong/codesquad_masters_2022BE/blob/master/week1/src/algorithm/Bjo1009_2.java">코드</a>
  - 풀이: Math.pow를 활용할 순 없을까?
  ```java
  public static long customPow(long a, long b) {
    long exponent = b % 4;
    if (exponent == 0) {
        exponent = 4;
    }
    return (long) Math.pow(a, exponent);
  }
  ```
  - 해결: 거듭제곱의 1의자리는 4개의 수가 반복되는걸 확인하여 나머지 연산자를 사용해 해결
  - 두 코드의 차이
    - 처음 풀이한 코드 결과
    - ![채점 현황](https://user-images.githubusercontent.com/78953393/148186739-dc70bc0c-f570-4bd3-90ea-ccce719b21a6.png)
    - 두번째(Math.pow 사용) 풀이한 코드 결과
    - ![채점 현황2](https://user-images.githubusercontent.com/78953393/148186750-43a98fcc-a4cf-4484-a918-0a6d6c98d5b7.png)
    - 속도 차이가 엄청나다.
- [X] Bjo1076문제(저항) <a href=https://www.acmicpc.net/problem/1076>문제링크</a>
  - 코드: <a href = "https://github.com/geombong/codesquad_masters_2022BE/blob/master/week1/src/algorithm/Bjo1076.java">코드</a>
  - 풀이: enum을 사용해 값을 지정하고, 색깔이 문자로 입력되면 해당 값과 곱을 반환해서 처리
  ```java
  public enum Resistance {
    BLACK("black", 0, 1),
    BROWN("brown", 1, 10),
    RED("red", 2, 100),
    ORANGE("orange", 3, 1000),
    YELLOW("yellow", 4, 10000),
    GREEN("green", 5, 100000),
    BLUE("blue", 6, 1000000),
    VIOLET("violet", 7, 10000000),
    GREY("grey", 8, 100000000),
    WHITE("white", 9, 1000000000);
  ...
  ```
  - 해결: 각각의 필요한 값을 받아와서 합, 곱을 진행해서 해결
## 선택문제
- [ ] Bjo1052문제(물병) <a href="https://www.acmicpc.net/problem/1052">문제링크</a>
  - 코드: 
  - 풀이:
- [ ] Bjo10757문제(큰 수 A+B) <a href="https://www.acmicpc.net/problem/10757">문제링크</a>
  - 코드: 
  - 풀이: