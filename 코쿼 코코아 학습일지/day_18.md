# 18일차 학습일지

## 코딩

### SimpleRPG 구현하기
- 예전부터 기회가되면 다시한번 해보고싶었던 SimpleRPG를 이번에 다시 해보게 되었다.
- 과연 1주차의 나와 지금의 나는 달라진게 있을까?

### 게임시작
- 맵에 함정과 몬스터를 랜덤으로 생성하게끔 만들어야하는데 여기서 시간이 엄청 오래 걸렸다.
- 플레이어 위치와 몬스터 위치 함정 위치를 다 고려해서, 중복되지 않게 생성해야해서 어려웠다..
    ```java
    @Override
    public void startLocate(int x, int y) {
        this.locationX = (int) (Math.random() * 5);
        this.locationY = (int) (Math.random() * 5);
        if (locationX == x && locationY == y) {
            startLocate(x, y);
        }
    }
    ```
    - 몬스터의 위치 설정하는 메소드
    - 함정의 위치를 받아서 중복되면 다시 위치를 설정하게끔 만들었다.
    - 원래는 SameLocationException을 만들어서 처리해볼까도 생각해봤는데 쉽지않아서 그냥 편한 길을 선택했다.
    ```java
    @Override
    public void startLocate(int x, int y) {
        this.locationX = (int) (Math.random() * 5);
        this.locationY = (int) (Math.random() * 5);
        if ((x == locationX && y == locationY) || (locationX == 2 && locationY == 2)) {
            startLocate(x, y);
        }
    }
    ```
    - 함정의 위치를 설정하는 메소드
    - 플레이어와 몬스터의 위치를 받아서 중복되어 생성되지 않도록 만들었다.
    - 뭔가 마음에 안드는 코드
### 게임플레이
- 몬스터의 위치에 플레이어가 도달하면 몬스터 처치 후 점수 획득
- 몬스터의 위치로 이동 중 함정위치에 플레이어가 위치하면 게임오버
- 맵의 범위를 벗어나거나 WASD외의 값을 입력하면 예외처리
    ```java
    private void inputKey(Input input) {
        int[] keyIndex;
        try {
            String inputKey = input.inputString();
            keyIndex = GameKey.gameKeyList(inputKey);
            moveToPlayer(keyIndex);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 값을 입력했습니다.");
            System.out.println("다시 입력해주세요.");
            printInputBar();
            inputKey(input);
        }
    }
    ```
    - 입력예외처리 메소드
    - enum으로 키를 만들고 입력된 값과 비교해서 다른값이 입력되면 예외발생
    ```java
    private void moveToPlayer(int[] index) {
        try {
            gameMap[playerLocation[0]][playerLocation[1]] = "* ";
            gameMap[playerLocation[0] + index[0]][playerLocation[1] + index[1]] = "P ";
            playerLocation[0] += index[0];
            playerLocation[1] += index[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("맵의 범위를 벗어났습니다.");
            playingGame();
        }
        if (playerLocation[0] == monsterLocation[0] && playerLocation[1] == monsterLocation[1]) {
            gameScore += 100;
            Score score = new Score(gameScore);
            gameWinner(score.getGameScore());
            startGame();
        }
        if (playerLocation[0] == trapLocation[0] && playerLocation[1] == trapLocation[1]) {
            gameOver(gameScore);
        }
        printMap();
        playingGame();
    }
    ```
    - 플레이어 이동 메소드
    - 맵의 범위를 벗어나면 예외 발생
    - 몬스터의 위치에 도달하면 점수 획득
    - 함정의 위치에 도달하면 게임 오버
    - 사실 너무 마음에 안드는 코드 좀 더 간결하게 정리 할 수 있는 방법을 생각해보자
### 실행화면
![CodeSquad_COCOA_JAVA – Input java  week4](https://user-images.githubusercontent.com/78953393/143290109-de405bf2-eda0-42c4-b543-05f538cf9e8d.png)
- 몬스터 처치 및 점수 획득 출력 화면
    
![CodeSquad_COCOA_JAVA – Inpu1t java  week4](https://user-images.githubusercontent.com/78953393/143290054-d7060ef8-1349-43a2-b773-179a20c21f2f.png)
- 함정으로 인한 게임오버 및 최종점수 출력

### 미션후기
- 사실 코드짜는것보다 클래스 나누고 설계하는데 시간이 더 많이 걸린거 같다.
- MVC패턴으로 설계해보려고 매번 시도해보지만 쉽지 않은거 같다.
- 변수, 메소드, 클래스 등 이름을 명명하는게 어렵다. 영어 실력이 부족해서 더 그렇게 느끼는것 같다.
- 그래도 이번주는 학습을 위주로 진행했었는데, 오랜만에 코딩을 하루종일 해서 그런가 더 재미있었다.
- 객체의 개념과 LifeCycle에 대해 다시 한번 공부해봐야겠다.
- 2주차에 가계부를 구헌할때와 크게 달라진점이 없는것같아, 조금 아쉬웠다. 
- <a href=https://github.com/Black-bong/CodeSquad_COCOA_JAVA/tree/master/week4/src/simplerpg>SimpleRPG코드</a>
