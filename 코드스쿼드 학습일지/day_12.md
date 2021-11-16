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
### 리터널이란?
### 오토박싱 언박싱?
### Map이란?
  - hashMap과 hashTable의 차이점은?
  - entrySet과 keySet의 차이점은?

### 프로세스와 쓰레드에 대해 가볍게 공부해보기.
