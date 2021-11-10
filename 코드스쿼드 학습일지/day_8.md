# 8일차 학습일지

## 가계부 추가 요구사항

  - 자동 저장 기능을 추가한다. 프로그램 종료 후 다시 시작해도 데이터가 보존되도록 구현해 보자.
       - 자바 파일 입출력 하고 있는데 한번도 안해봐서 어렵다.
       - 값을 받아서 저장 되는 것 까지만 구현.

  - 소비 유형을 추가한다. (현금 / 카드)
       - 소비 유형 추가 완료
       - 이걸 enum으로 구현해서 하던데 어떻게 하는거지?
       - 변수를 하나 더 생성하고, 각각 생성자를 생성할때마다 매개변수를 추가 했더니 너무 번거롭다.
       - 변수 이름도 좀 다듬어 봐야겠다.
       - 지금이야 작은 프로젝트지만 코드가 많아지면 하나씩 다 찾아봐야하니..
          ```java
          private Long dataID;
          private final String date;
          private final String briefs;
          private final String consumptionType; // 소비 유형 추가
          private final BigDecimal earnings;
          private final BigDecimal expenses;
          private BigDecimal balanceTempValue;
          private final BigDecimal balance;
          
          MoneyBookData data = new MoneyBookData(date, briefs, income, expenses, consumptionType); // 객체 생성
          ```
  
  - 검색 기능을 구현한다. 
  - 적요, 날짜, 금액, 수입, 지출, 소비 유형별로 검색을 하고 결과를 표시할 수 있어야 한다.
       - 검색 기능 구현 완료.
       - 이런 반복적인 코드들을 좀 더 보기 좋게 만들어 보고 싶은데 너무 욕심인가?
          ```java
          public boolean isSameDate(String d) {
              return this.date.substring(0, 3).equals(d);
          }
          public boolean isSameBriefs(String b) {
              return this.briefs.equals(b);
          }
          public boolean isSameEarnings(String ea) {
              return this.earnings.equals(ea);
          }
          public boolean isSameExpenses(String ex) {
              return this.expenses.equals(ex);
          }
          public boolean isSameConsumptionType(String c) {
              return this.consumptionType.equals(c);
          }
          public boolean isSameBalance(String bl) {
              return this.balance.equals(bl);
          }
          ```
  
  - 정렬해서 보여주기 기능을 추가한다. 날짜 또는 금액의 오름차순 또는 내림차순으로 정렬해서 화면에 출력할 수 있어야 한다.
       - 아직 이건 시작하지 못했다, 주말에 해야할게 너무나도 많당.
