# 코드스쿼드 5주차
## 개인 프로젝트

## 루빅스큐브 구현하기
> 참고 사이트 https://rubiks-cube-solver.com/

<details>
<summary>문제</summary>
<div markdown="1">

## 문제 설명
- 큐브는 W, B, G, Y, O, R의 6가지 색깔을 가지고 있다.
- 입력: 각 조작법을 한 줄로 입력받는다.
- 출력: 큐브의 6면을 펼친 상태로 출력한다.
- Q를 입력받으면 프로그램을 종료하고, 조작 받은 명령의 갯수를 출력시킨다.

## 큐브의 초기 상태
```
              B B B
              B B B
              B B B

W W W     O O O     G G G     Y Y Y
W W W     O O O     G G G     Y Y Y
W W W     O O O     G G G     Y Y Y

              R R R
              R R R
              R R R
```
## 프로그램 예시
```
(초기 상태 출력)

CUBE> FRR'U2R

F
(큐브상태)

R
(큐브상태)

...

R
(큐브상태)

CUBE> Q
경과시간: 00:31 //추가 구현 항목
조작갯수: 6
이용해주셔서 감사합니다. 뚜뚜뚜.
```

## 추가 구현 기능
- 프로그램 종료 시 경과 시간 출력
- 큐브의 무작위 섞기 기능
- 모든 면을 맞추면 축하 메시지와 함께 프로그램을 자동 종료
  
## 3단계 코딩 요구사항
- 가능한 한 커밋을 자주 하고 구현의 의미가 명확하게 전달되도록 커밋 메시지를 작성할 것
- 함수나 메소드는 한 번에 한 가지 일을 하고 가능하면 20줄이 넘지 않도록 구현한다.
- 함수나 메소드의 들여쓰기를 가능하면 적게(3단계까지만) 할 수 있도록 노력해 본다.
```
function main() {
    for() { // 들여쓰기 1단계
        if() { // 들여쓰기 2단계
            return; // 들여쓰기 3단계
        }
    }
}
```
</div>
</details>

## 목차
1. [PrintScreen클래스](#PrintScreen클래스)
3. [Input클래스](#Input클래스)
4. [Commends클래스](#Commends클래스)
5. [CubeRepository클래스](#CubeRepository클래스)
6. [RubiksCube클래스](#RubiksCube클래스)
7. [CubeTimer클래스](#CubeTimer클래스)
8. [RubiksCubeController클래스](#RubiksCubeController클래스)

### PrintScreen클래스
- 화면 출력을 담당

|메소드명|기능|
|------|----|
|[startScreen](#startScreen메소드)|큐브 초기상태 출력|
|[endScreen](#endScreen메소드)|큐브 종료 안내 출력|
|[inputBar](#inputBar메소드)|사용자 입력 칸 출력|

### endScreen메소드
```java
public void endScreen(int count, int time) {
    int seconds = time % 60;
    int minute = time / 60;
    System.out.printf("경과시간: %02d:%02d%n", minute, seconds);
    System.out.println("조작갯수: " + count);
    System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
}
```
- 루빅스 큐브 종료 시 조작갯수, 경과시간, 종료안내문 출력

### Input클래스
- 사용자로부터 값을 입력 받는 역할

|메소드명|기능|
|------|----|
|[inputString](#inputString메소드)|사용자의 명령어를 입력받는 기능|
|[splitString](#splitString메소드)|명령어에 특수문자가 있을 시 일반 문자와 특수문자를 나누는 기능|
|[isInteger](#isInteger메소드)|명령어에 숫자가 있을 시 문자와 숫자를 나누는 기능|

### inputString메소드
```java
public void inputString(List<String> commandList) throws IOException {
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    String commands = buf.readLine().toUpperCase();
    splitString(commands, commandList);
}
```
- 사용자로부터 입력을 받아 대문자로 변환해준다.
### splitString메소드
```java
private void splitString(String commands, List<String> commandList) {
    for (int i = 0; i < commands.length(); i++) {
        int lastIndex = commandList.size() - 1;
        String command = Character.toString(commands.charAt(i));
        if (command.equals("'")) {
            commandList.set(lastIndex, commandList.get(lastIndex) + "'");
            continue;
        }
        isInteger(command, commandList);
    }
}
```
- B' 등 특수문자가 결합된 명령어를 만들어준다.
### isInteger메소드
```java
private void isInteger(String command, List<String> commandList) {
    int lastIndex = commandList.size() - 1;
    try {
        for (int j = 0; j < Integer.parseInt(command) - 1; j++) {
            commandList.add(commandList.get(lastIndex));
        }
    } catch (NumberFormatException e) {
        commandList.add(command);
    }
}
```
- U2등 명령어 앞에 숫자가 붙어있을 경우 명령어를 숫자만큼 반복 실행되도록 만들어 준다.
 
### Commends클래스
- 명령어를 저장하는 enum타입의 클래스
```java
public enum Commends {
    MOVING_TO_RIGHT_TOP_LINE("U'", 0),
    MOVING_TO_LEFT_TOP_LINE("U", 1),
    MOVING_TO_DOWN_RIGHT_LINE("R'", 2),
    MOVING_TO_UP_RIGHT_LINE("R", 3),
    MOVING_TO_UP_LEFT_LINE("L'", 4),
    MOVING_TO_DOWN_LEFT_LINE("L", 5),
    MOVING_TO_RIGHT_BOTTOM_LINE("D", 6),
    MOVING_TO_LEFT_BOTTOM_LINE("D'", 7),
    MOVING_TO_RIGHT_FRONT("F", 8),
    MOVING_TO_LEFT_FRONT("F'", 9),
    MOVING_TO_RIGHT_BACK("B'", 10),
    MOVING_TO_LEFT_BACK("B", 11);
```
|메소드명|기능|
|------|----|
|[isSameCommend](#isSameCommend메소드)|사용자로부터 입력받은 값이 명령어와 일치하는지 확인하는 기능|
|[transferCommendID](#transferCommendID메소드)|입력값이 명령어와 같을 경우 명령어의 ID값을 반환해주는 기능|

### isSameCommend메소드
```java
public boolean isSameCommend(String commend) {
    return this.commend.equals(commend);
}
```
- 입력받은 값이 명령어와 같은지 확인하여 true,flase을 반환해준다.
### transferCommendID메소드
```java
public static int transferCommendID(String commend) {
    for (var c : Commends.values()) {
        if (c.isSameCommend(commend)) {
            return c.commendID;
        }
    }
    throw new IllegalArgumentException();
}
```
- 입력 받은 값이 명령어와 일치하면 명령어 ID값을 반환해준다.

### CubeRepository클래스
- 큐브의 상태를 변경하고,저장하는 클래스
- 구현 진행중 입니다.. 아직 다 못했습니다 ㅠㅠ

|메소드명|기능|
|------|----|
|[save](#save메소드)|큐브 객체를 받아 저장하는 기능|
|[printCube](#printCube메소드)|큐브의 현재 상태를 출력해주는 기능|
|[clockWise](#clockWise메소드)|큐브를 시계방향으로 회전 시키는 기능|
|[inverted](#inverted메소드)|큐브를 반시계방향으로 회전 시키는 기능|

### save메소드
```java
public void save(Cube cube) {
    cube.setId(sequence);
    cubeList.put(sequence, cube);
    sequence++;
}
```
- 큐브 생성 시 생성된느 순서에 따라 ID값을 부여하고, Map에 저장하는 기능을 수행한다.
### printCube메소드
```java
public void printCube() {
    StringBuilder sb = new StringBuilder();
    int count = 0;
    topAndBottom(sb, count);
    for (int c = 0; c < 3; c++) {
        center(sb, c);
    }
    count = 5;
    topAndBottom(sb, count);
    System.out.println(sb);
}
```
- 큐브의 현재 상태를 출력해주는 기능을 수행한다.
### clockWise메소드
```java
public String[][] clockWise(String[][] cube) {
    String[][] tempArr = new String[3][3];
    for (int i = 0; i < tempArr.length; i++) {
        for (int j = 0; j < tempArr[i].length; j++) {
            tempArr[j][2 - i] = cube[i][j];
        }
    }
    return tempArr;
}
```
- 큐브를 받아서 시계방향으로 돌린 후 저장하여 반환해주는 기능을 수행한다.
### inverted메소드
```java
public String[][] inverted(String[][] cube) {
    String[][] tempArr = new String[3][3];
    for (int i = 0; i < 3; i++) {
        tempArr = clockWise(cube);
    }
    return tempArr;
}
```
- 반 시계뱡향으로 돌리는 기능을 수행한다.
- 시계방향으로 3번돌리면 반 시계방향으로 돌린것과 같기때문에 그걸 활용하여 반 시계반향으로 돌린다.

### RubiksCube클래스
- 큐브를 생성하고, 입력받은 명령어에 따른 행동을 저장소에서 꺼내와 실행하는 역할

|메소드명|기능|
|------|----|
|[createCube](#createCube메소드)|큐브 객체를 받아 저장하는 기능|
|[leftClockWise](#leftClockWise메소드)|L명령어에 따른 행동을 저장소에서 꺼내와 실행하는 기능|

### createCube메소드
```java
protected void createCube() {
    Cube top = new Cube("⬜", 3, 3);
    cubeRepository.save(top);
    Cube left = new Cube("\uD83D\uDFE7", 3, 3);
    cubeRepository.save(left);
    Cube front = new Cube("\uD83D\uDFE9", 3, 3);
    cubeRepository.save(front);
    Cube right = new Cube("\uD83D\uDFE5", 3, 3);
    cubeRepository.save(right);
    Cube back = new Cube("\uD83D\uDFE6", 3, 3);
    cubeRepository.save(back);
    Cube bottom = new Cube("\uD83D\uDFE8", 3, 3);
    cubeRepository.save(bottom);
}
```
- 큐브를 생성자를 통해 생성하고, 저장한다.
### leftClockWise메소드
```java
public void leftClockWise() {
    System.out.println("L");
    cubeRepository.left();
    printCube();
}
```
- 입력된 명령에 따라 기능을 수행한다.

### CubeTimer클래스
- 루빅스 큐브를 플레이한 시간을 측정하는 역할

|메소드명|기능|
|------|----|
|[timeCheck](#timeCheck메소드)|타이머를 생성하는 기능|
|[playTime](#playTime메소드)|playTime을 측정하는 기능|
|[stopTimer](#stopTimer메소드)|Timer를 멈추는 기능|

### timeCheck메소드
```java
public void timeCheck() {
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            playTime(timer);
        }
    };
    timer.schedule(timerTask, 0, 1000);
}
```
- TimerTask를 활용하여 메인 스레드와는 별개로 작동되는 스레드를 만들어 시간 측정
- 1초마다 playTime메소드를 실행
### playTime메소드
```java
private void playTime(Timer timer) {
    if (timerFlag) {
        time++;
    }
    if (!timerFlag) {
        timer.cancel();
    }
}
```
- timerFlag가 true인 동안 1초마다 time을 1씩 증가 시켜 시간 측정
### stopTimer메소드
```java
public int stopTimer() {
    timerFlag = false;
    return time;
}
```
- timer를 멈추고 측정된 time값을 반환해준다.

### RubiksCubeController클래스
- 입력 받은 명령어를 처리하여 RubiksCube클래스에 있는 큐브를 움직이는 메소드를 호출하는 역할

|메소드명|기능|
|------|----|
|[saveCommend](#saveCommend메소드)|특정 값이 입력될 때 까지 명령을 계속 받고 명령어 리스트에 저장하는 기능|
|[readCommend](#readCommend메소드)|저장된 명령어를 읽는 기능|
|[createCommendController](#createCommendController메소드)|명령에 따른 기능을 호출할 수 있도록 컨트롤러를 생성하는 기능|

### saveCommend메소드
```java
private void saveCommend() throws IOException {
    Input input = new Input();
    List<String> commandList = new LinkedList<>();
    Map<Integer, Runnable> controllerList = new HashMap<>();
    createCommendController(controllerList);
    while (inputFlag) {
        clearCommandList(commandList);
        inputBar();
        input.inputString(commandList);
        readCommend(commandList, controllerList);
    }
}
```
- 명령어 처리에 필요한 객체를 생성하고, 명령어를 입력받아 저장한다.

### readCommend메소드
```java
private void readCommend(List<String> commandList, Map<Integer, Runnable> controllerList) {
    for (String s : commandList) {
        commandCount++;
        if (s.equals("Q")) {
            inputFlag = false;
            endScreen(commandCount, cubeTimer.stopTimer());
            break;
        }
        controllerList.get(Commends.transferCommendID(s)).run();
    }
}
```
- 저장된 명령어를 읽어 명령어 ID를 반환받아 해당 명령을 수행하는 메소드를 호출해준다.

### createCommendController메소드
```java
private void createCommendController(Map<Integer, Runnable> controllerList) {
    controllerList.put(0, () -> upInverted()); // U'
    controllerList.put(1, () -> upClockWise()); // U
    controllerList.put(2, () -> rightInverted()); // R'
    controllerList.put(3, () -> rightClockWise()); // R
    controllerList.put(4, () -> leftInverted()); // L'
    controllerList.put(5, () -> leftClockWise()); // L
    controllerList.put(6, () -> downInverted()); // D
    controllerList.put(7, () -> downClockWise()); // D'
    controllerList.put(8, () -> frontClockWise()); // F
    controllerList.put(9, () -> frontInverted()); // F'
    controllerList.put(10, () -> bottomInverted()); // B'
    controllerList.put(11, () -> bottomClockWise()); // B
}
```
- if~else 또는 switch구문의 사용을 피하기위해 Runnable을 사용
