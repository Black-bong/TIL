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
8. [Cube클래스](#Cube클래스)
9. [RubiksCubeController클래스](#RubiksCubeController클래스)

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
- 사용자로부터 값을 입력 받는 역활

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
