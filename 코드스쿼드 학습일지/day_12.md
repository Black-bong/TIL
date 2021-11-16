# 12일차 학습일지

## 한글시계 추가 요구사항 구현

### 자동갱신 기능 추가
  - 설정한 주기마다 자동으로 시간을 갱신하여, 현재 시간을 실시간으로 보여주는 기능 추가
  - 사용 클래스 또는 메소드
       - 처음에는 Thread.sleep을 사용해 보려 동료분들께 조언을 해본 결과 요즘에는 Thread.sleep을 거의 사용안한다고 조언해주셔서 다른 방법을 찾아봄
       - TimerTask와 Runnable 두개를 추천해주셔서 공부를 해보고, 이번에는 TimerTask를 사용
       - 사용코드
         ```java
         private void timer(String[][] clock, HourHangul[] hourHangul, MinuteHangul[] minuteHangul) {
          Timer timer = new Timer();
          TimerTask timerTask = new TimerTask() {
              @Override
              // TODO 현재 시간의 초를 받아서 분이 바뀌는 시간을 계산한 후 변경 후에 1분마다 갱신되는 기능 추가
              public void run() {
                  String time = LocalTime.now().toString();
                  String hour = time.split(":")[0];
                  String minute = time.split(":")[1];
                  String seconds = time.split(":")[2];
                  int hourValue = Integer.parseInt(hour) % 12;
                  int minuteValue = Integer.parseInt(minute);
                  createHour(clock, hourValue, hourHangul);
                  createMinute(clock, minuteValue, minuteHangul);
                  midnight(clock, hour);
                  printClock(clock);
              }
            };
            timer.schedule(timerTask, 0, 5000);
          }
         ```
       - 주기를 60초로 고정해놓을 경우 실행 당시의 시간에 따른 오차가 발생하기 때문에 이 오차를 계산하는 식을 추가할 예정
       - 문제는 정수형으로 파싱하는 과정에서 LocalTime.now()에서 받아오는 초의 수가 소수점까지 있기때문에 예외발생
       - 위의 예외 상황을 해결할 방안을 찾아보는 중

## 학습 키워드

### 백엔드 개발자의 공부 순서
  1. 자바
  2. 스프링
  3. 데이터베이스
  4. 네트워크(HTTP)
  5. 운영체제(OS)
  6. 자료구조/알고리즘
  7. OOP

### 프로세스란?
  - 메모리에 올린 프로그램 == 실행중인 프로그램
  - 메모리가 클수록 한번에 실행되는 프로세스의 수가 많아진다. == 동시에 실행 할 수 있는 프로그램 즉 프로세스가 많아진다.

### 컴퓨터의 핵심 부품
  1. CPU
  2. RAM
  3. STORAGE

### 래퍼클래스란?
  - 기본형 값을 감싸는 클래스
  - 8개의 기본형을 객체로 다뤄야할 때 사용하는 클래스
    |기본형|래퍼 클래스|
    |----|-----|
    |byte|Byte|
    |short|Short|
    |int|Integer|
    |long|Long|
    |float|Float|
    |double|Double|
    |char|Charater|
    |boolean|Boolean|
### 오토박싱 언박싱?
   - 기본 타입의 데이터를 래퍼 클래스의 인스턴스로 변환하는 과정을 박싱(Boxing)
   - 래퍼 클래스의 인스턴스에 저장된 값을 다시 기본 타입의 데이터로 꺼내는 과정을 언박싱(UnBoxing)
   - 위의 과정을 자동으로 해주는것이 오토박싱 언박싱이다.
   - 예제
      ```java
      Integer num = new Integer(10); // 박싱
      int a = num.intValue();        // 언박싱
      
      System.out.println("a: " + a);
      
      Character chr = new Character('M'); : 오토박싱
      char ch = ch.charValue();           : 오토언박싱
      
      System.out.println("ch: " + ch);
      ```
   - 결과
      ```java
      a: 10
      ch: M
      ```
### var(타입추론)
   - var란?
      > 코드 작성 당시 타입이 정해지지 않았지만, 컴파일러가 그 타입을 유추하는 것이다.
   - 예제
      ```java
      public static void main(String[] args) {
        Map<String, Integer> varTest = new HashMap<>();

        varTest.put("하나", 1);
        varTest.put("둘", 2);
        varTest.put("셋", 3);
        varTest.put("넷", 4);

        for (var v : varTest.values()) {
            System.out.println(v.getClass());
        }
      }
      ```
   - 예제를 살펴보면 우리는 출력하는 값(v)의 타입을 따로 정하지 않았으나, 위에 Map을 만들때 value의 타입을 Integer로 지정을 해줬기 때문에 컴파일러가 타입을 Integer로 추론하여 출력이 되게끔 해준다.
   - 결과
      ```java
      class java.lang.Integer
      class java.lang.Integer
      class java.lang.Integer
      class java.lang.Integer
      ```
   - v의 타입을 출력해본 결과 Integer로 나오는걸 알 수 있다.
### Map이란?
  - hashMap과 hashTable의 차이점은?
  - entrySet과 keySet의 차이점은?

### 프로세스와 쓰레드에 대해 가볍게 공부해보기.
