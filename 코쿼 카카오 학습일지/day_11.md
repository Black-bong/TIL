# 11일차 학습일지

## 셸 만들기

### 요구사항
  - 간단한 터미널 명령어를 실행할 수 있는 셸을 구현해보자.

### 진행상황
  - 으앙 도저히 어떻게 해야하는건지 모르겠어서 일단 보류..
  - 동료 찬스를 써서 공부해봐야겠다.

## 한글 시계 구현하기

### 요구사항
  - 한글로 나타나는 시계 구현하기.

### 진행상황
  - 처음에 보고 쉬울줄 알았으나, 생각보다 시간을 많이 소요했다.
  - 가장 큰 고민은 12가지의 시간과 59가지의 분을 어떻게 구별해서 나타내야할지 막막했었다.
  - 12시와 24시에는 각각 정오와 자정이 출력되어 나타나도록 만들었다.
  ```java
  public enum HourHangul {
    HAN("한", 1, 0, 0),
    DOO("두", 2, 0, 1),
    SAE("세", 3, 0, 2),
    NE("네", 4, 0, 3),
    DA("다", 5, 0, 4),
    DA_SUT("섯", 5, 0, 5),
    YEO("여", 6, 1, 0),
    YEO_SUT("섯", 6, 1, 1),
    Il("일", 7, 1, 2),
    GOP("곱", 7, 1, 3),
    YEO_DEOL("여", 8, 1, 4),
    DEOL("덟", 8, 1, 5),
    A("아", 9, 2, 0),
    HOB("홉", 9, 2, 1),
    YEOL("열", 10, 2, 2),
    YEOL_HAN("한", 11, 2, 3),
    YEOL_DO("두", 12, 2, 4);
  ```
  - 열거형으로 시간 데이터를 만든 후 속성값 한글, 시간, 행인덱스, 열인덱스 4개를 만들어서 사용
  ```java
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\033[1;31m";
  
  private void createHour(String[][] clock, int hourIndex, HourHangul[] hourHangul) {
        clock[2][5] = ANSI_RED + "시" + ANSI_RESET;
        for (HourHangul hangul : hourHangul) {
            if (hourIndex == hangul.getHour()) {
                clock[hangul.getFirstIndex()][hangul.getLastIndex()] = ANSI_RED + hangul.getHangul() + ANSI_RESET;
            }
        }
    }
  ```
  - 시간을 만드는 메소드를 활용하여, 열거형의 시간 속성값과 실시간으로 받아오는 실제 시간의 시간 값을 비교하여 같은 값에 대한 시간 출력
  - ANSI_RED와 ANSI_RESET이라는 상수 타입의 값을 할용해 시간을 나타내는 글씨와 일반 글씨의 차이점을 두어 가시성을 높힘.
  ```java
  public enum MinuteHangul {
    Il("일", 1, 4, 1),
    YI_SIP("이", 20, 3, 1),
    YI("이", 2, 4, 2),
    SAM_SIP("삼", 30, 3, 2),
    SAM("삼", 3, 4, 3),
    SA_SIP("사", 40, 3, 3),
    SA("사", 4, 4, 4),
    OH_SIP("오", 50, 3, 4),
    OH("오", 5, 5, 1),
    YUK("육", 6, 4, 5),
    CHIL("칠", 7, 5, 2),
    PAL("팔", 8, 5, 3),
    GU("구", 9, 5, 4),
    SIP("십", 10, 3, 5);
  ```
  - 분 값도 시간의 값과 동일하게 열거형으로 데이터를 만든 후 나머지 연산자를 활용해 값을 추출하여 비교, 출력하였다.
 ### 실행결과
 ![CodeSquad_COCOA_JAVA – Clock java  week3 !](https://user-images.githubusercontent.com/78953393/141749043-ea2ec8c9-1b64-45e7-ab3f-db826558554f.png)
