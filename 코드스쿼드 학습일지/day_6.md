# 6일차 학습일지

## 미션 풀이

### 가계부 구현하기

  - 필수 요구사항
      1. 간단한 가계부를 구현한다.
      2. 키보드를 통해 데이터 입력을 받고 화면에 내용을 출력한다.
      3. 사용자 등록: 사용자 이름 및 비밀번호를 입력받는다.
      4. 데이터 입력: 날짜, 적요, 수입, 지출을 입력받는다.
      5. 데이터 삭제: 특정 순번의 데이터를 삭제한다.
      6. 데이터 수정: 특정 순번의 데이터를 수정할 수 있다.
      7. 화면에 출력: 해당 월의 지출내역을 순번, 적요, 수입, 지출, 잔액으로 화면에 출력한다.
  
  - 구현
      - Member와 Data 클래스를 생성
      - MemberRepository와 DataRepository라는 저장소를 만들어서 생성된 Member와 Data를 저장
      - Member 클래스
          - id, username, password 변수 생성
      - Data 클래스
          - id, date, briefs, income, expenses, cash 변수 생성
      - 각 클래스의 id, cash 값을 제외한 값들은 사용자로부터 콘솔창의 입력을 통해 값을 받는다.
      - id값은 객체가 생성되어 저장소에 저장될때마다 1씩 증가
      - 사용자로부터 입력 받은 수입과 지출을 계산해서 보유중인 자산 표시
      ```java
      private static final DataRepository instance = new DataRepository();
      private static final Map<Long, Data> dataList = new HashMap<>();
      private static long sequence = 0L;
      private static double cash = 0.0;
    
      public void dataSave(Data data) {
        data.setDataID(++sequence);
        cash += (data.getIncome() - data.getExpenses());
        data.setCash(cash);
        dataList.put(sequence, data);
      }
      ```
      - BufferedReader처럼 자주 새롭게 생성되는 객체는 싱글 패턴을 사용
      ```java
      public class BufferedInput {

        private static BufferedReader instance = new BufferedReader(new InputStreamReader(System.in));

        public static BufferedReader getInstance() {
          if (instance == null) {
              instance = new BufferedReader(new InputStreamReader(System.in));
          }
          return instance;
        }
      }
    ```
    > 생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 객체를 리턴한다. 이와 같은 디자인 유형을 싱글턴 패턴이라고 한다.
      - 사용자 등록없이 로그인 시도, Data 저장값 입력 시 잘못된 입력 예외 처리
    ```java
    System.out.println("날짜, 적요, 수입, 지출을 띄어쓰기로 구분하여 입력하시오.(종료:exit)");
    String date;

    System.out.print(">> ");
    StringTokenizer str = new StringTokenizer(bufferedInput.readLine(), " ");

    try {
        date = str.nextToken();
        if (date.equals("exit")) {
            dataMenu();
        }
        String briefs = str.nextToken();
        double income = Double.parseDouble(str.nextToken());
        double expenses = Double.parseDouble(str.nextToken());
        Data data = new Data(date, briefs, income, expenses);
        dataRepository.dataSave(data);
        createData();

    } catch (NoSuchElementException e) {
        System.out.println("입력값 오류 다시 입력해주세요.");
        LOG.info(e.toString());
        createData();
    }
    ```
      - 데이터 저장, 재산 호출, 데이터 삭제 test 코드 작성
    ```java
    @Test
    @DisplayName("데이터 저장 확인")
    void saveData() {
        Data data = new Data("11월02일","핸드폰요금",10000.0,0.0);
        dataRepository.dataSave(data);
        Assertions.assertEquals(data.getBriefs(),"핸드폰요금");
    }
    @Test
    @DisplayName("재산 호출")
    void callCash() {
        Data data1 = new Data("11월01일", "월급", 1000000.0, 0.0);
        dataRepository.dataSave(data1);
        Data data2 = new Data("11월02일", "핸드폰요금", 0.0, 100000.0);
        dataRepository.dataSave(data2);
        Data data3 = new Data("11월03일", "보너스", 120000.0, 0.0);
        dataRepository.dataSave(data3);
        Assertions.assertEquals(data3.getCash(), 1020000.0);
    }
    @Test
    @DisplayName("데이터 삭제 확인")
    void deleteData() {
        Data data = new Data("11월01일", "월급", 1000000.0, 0.0);
        dataRepository.dataSave(data);
        dataRepository.removeData(1L);
        Assertions.assertNull(dataRepository.findByIdData(1L));
    }
    ```
  ## 개선사항

  - 데이터 삭제 및 수정에 따른 재산 잔액의 변동이 없다.
  - txt파일로 데이터를 만들어 좀 더 대량의 데이터가 입력되고 조회 될때 문제가 없는지 확인 필요
  - 좀 더 간결하고 좀 더 보기 쉽게 코드를 작성 수 없을까 고민해보기
  - 인터페이스나 추상화를 사용해볼순 없을까?

  -----
  
  ## 실행 화면
      
  ### 1.첫 화면
  ```
  ========가계부========
  ========Menu========
  1. 사용자 등록
  2. 로그인
  3. 종료
  >> 
  ```
  ### 2.사용자 등록 화면
  ```
  1. 사용자 등록
  2. 로그인
  3. 종료
  >> 1
  사용자 이름과 비밀번호를 띄어쓰기로 구분하여 입력하세요.
  >> blackbong 1234
  ```
  ### 3.메뉴 화면
  ```
  ========Menu========
  1. 데이터 등록
  2. 데이터 확인
  3. 데이터 수정
  4. 데이터 삭제
  5. 메인 메뉴
  >> 
  ```
### 4. 가계부 작성
  ```
  날짜, 적요, 수입, 지출을 띄어쓰기로 구분하여 입력하시오.(종료:exit)
  >> 10월10일 월급 1500000 0
  날짜, 적요, 수입, 지출을 띄어쓰기로 구분하여 입력하시오.(종료:exit)
  >> 
  ```
### 5. 가계부 조회
  ```
  >> 2
  조회 할 월을 입력하세요.
  >> 10월
  1 날짜:10월10일 적요:월급 수입:1500000.0원 지출:0.0원 잔액:1500000.0원
  2 날짜:10월10일 적요:핸드폰값 수입:0.0원 지출:50000.0원 잔액:1450000.0원
  3 날짜:10월10일 적요:보너스 수입:150000.0원 지출:0.0원 잔액:1600000.0원
  4 날짜:10월10일 적요:외식 수입:0.0원 지출:30000.0원 잔액:1570000.0원
  5 날짜:10월11일 적요:기름값 수입:0.0원 지출:50000.0원 잔액:1520000.0원
  6 날짜:10월12일 적요:조카용돈 수입:0.0원 지출:30000.0원 잔액:1490000.0원
  7 날짜:10월13일 적요:모임 수입:0.0원 지출:50000.0원 잔액:1440000.0원
  ```
