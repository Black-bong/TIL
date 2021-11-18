# 13일차 학습일지

`사실 셸 만들기 하다 하루가 그냥 지나가버렸다.`

## 셸 만들기 정리!
`명렁어 구현만 완료함(cd 빼고..)`

### 미션 소감
  - 지금까지 미션중에 가장 어려웠던거 같았던.. File,Path등등 처음써보는 클래스와 메소드가 많아서 낯설어서 그런지 더 어렵게 느껴졌다.

### 명령어 리스트
  ```java
  public enum CommandList {
    C1("pwd", 0),
    C2("mkdir", 1),
    C3("rm", 2),
    C4("cd", 3),
    C5("ls", 4),
    C6("clock", 5),
    C7("exit", 6),
    C8("cp", 7),
    C9("touch", 8);
  ```
  - 또 다시 enum을 사용했지만, 깔끔하지 못해서 많이 부족하다는걸 다시 느꼇다.
  - 스위치를 사용하지 않고, 바로 입력받은 명령어와 비교해서 실행하는 메소드를 실행시키고 싶었는데 잘 안되 결구 스위치를 사용했다.
  - 좀 더 고민해 봐야할거 같다.

### 명령어 input
  ```java
  List<String> inputValue = new ArrayList<>();
  StringTokenizer str = new StringTokenizer(buf.readLine(), " ");
  String firstInputString = null;
  String secondInputString;
  String inputTrim = null;
  //TODO 코드가 너무 지저분하다
  try {
      firstInputString = str.nextToken();
      inputTrim = str.nextToken();
      secondInputString = str.nextToken();
      inputValue.add(firstInputString);
      inputValue.add(inputTrim);
      inputValue.add(secondInputString);
  } catch (NoSuchElementException e) {
      inputValue.add(firstInputString);
      inputValue.add(inputTrim);
      inputValue.add("");
      return inputValue;
  } catch (Exception e) {
      inputValue.add(firstInputString);
      inputValue.add("");
      inputValue.add("");
      return inputValue;
  }
  ```
  - TODO에도 써있듯이 이건 리펙토링 필수인 코드 코드가 너무 지저분하다.
  - 중간중간 공백이 들어있는 명령어를 받아서 하려다보니 이렇게 되어버렸는데 실력이 부족해서 그런거 같다.
      - mkdir text / cp abc.txt asd.txt / rm abc.txt 등등 
  
### 그외 명령어
  |명령어|느낀점|
  |-----|----|
  |pwd|제일 쉬웠다. 그냥 경로 받아서 출력|
  |mkdir|이거 구현하면서 뭔가 잘못된걸 느꼈다. 위에 코드가 지저분해진 이유|
  |rm|그냥 삭제하면 되서 어렵지 않았다.|
  |cd|와 경로 어떻게 바꾸는거지 왜 처음 경로가 고정되서 안바뀌는거지 아직 구현하지 못했다.|
  |cp|와 부가 명령어가 하나 더 추가되었다.. 망했다|
  |touch|디렉토리 생성이랑 파일 생성은 따로 구현해야하는거였군아.|
  
### <a href="https://github.com/Black-bong/CodeSquad_COCOA_JAVA/tree/cocoa/week3/src/shell">코드링크</a>
