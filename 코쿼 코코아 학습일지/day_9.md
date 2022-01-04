# 9일차 학습일지
### 커스텀 예외처리
- checkedException
    - 컴파일 시점에 명시적으로 예외상황을 처리해야하는 예외기법.
    - 컴파일 시점에서 예외처리 하도록 유도를 하기 때문에 실행 단계 전에서 예외발생에 따른 문제를 미리 처리할 수 있다.
    - 예외발생시 트랜잭션을 roll-back 시키지 않는다.
        - 트랜젝션이란? 
        - `데이터베이스의 상태를 변환시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위 또는 한꺼번에 모두 수행되어야 할 일련의 연산들을 의미한다.`
    - 필수적으로 예외처리를 해줘야 한다. (ex: IOException)
    - CheckedExceptiondms RuntimeException을 상속 받지 않는다.(uncheck인지 check인지 구분하는 중요 포인트)
    ```java
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public String next() throws IOException { // 컴파일러가 무조건 Exeption 처리를 하도록 만든다.
    	if (!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
    	return st.nextToken();
    }
    ```
    - 위에 예시처럼 BufferdReader로 콘솔에서 입력을 받을때 입력이 없을때의 예외를 컴파일 단계에서 처리하도록 유도한다.
    - IOException 없이 실행할 경우 `unreported exception java.io.IOException; must be caught or declared to be thrown` 오류 문구가 발생하면서 실행되지 않는다.
- UncheckedException
    - 런타임 시점에서 발생되는 예외 상황
    - 컴파일 시점에서 예외처리에 따른 문제를 인식할 수 없어 실행 단계에서 문제 발생 시 프로그램 종료로 이어질 수 있다.
    - 개발자는 이런 예외 상황을 미리 생각해보고 체크해서 예외발생에 따른 프로그램 종료를 발생 시켜서는 안된다.
    - 예외발생시 트랜젝션을 roll-back한다.
    - checkedException과 다르게 반드시 예외처리를 해야한다는 강제성은 없다.(ex: NullPointerException)
    - Unchecked Exception은 RuntimeException을 상속받고 있다.  
    ```java
    // 콘솔에서 값을 입력받을때 받고자하는 값이 아닌 다른 값이 입력되어 들어올때 예외처리
    while (true) {
                try {
                    value = input.integer();
                    validateIndexRange(value);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("으악1");
                    logger.info(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("으악2");
                    logger.info(e.getMessage());
                } catch (Exception e) {
                    System.out.println("으악3");
                    logger.info(e.getMessage());
                }
            }
            return value;
    ```
    - 위 예제처럼 미리 발생할 수 있는 예외 상황에 따라 예측하고, 예외발생 시 프로그램 종료가 아닌 다른 방향으로 예외 처리를 해야한다.
    - 예외 처리에는 예외복구, 예외처리회피, 예외 전환이 있다.
        - try-catch-finally를 사용한 예외복구 방법
            - 예외가 발생해도 프로그램 종료 없이 정상적으로 진행되는 방법.
            ```java
            try {
            	// 예외가 발생할 가능성이 있는 로직
            	return; // 예외발생 없이 작업 성공 시 리턴
            }
            catch {
            	// 로그 출력. 정해진 시간만큼 대기
            }
            finally {
            	// 리소스 반납 및 정리 작업
            }
            ```
        - throws를 사용한 예외처리 회피 방법
            - 예외가 발생하면 throws를 통해 호출한쪽으로 예외를 던지고 처리를 회피하는 방법.
            - 매우 간단해보이지만 의도가 명확할때만 사용해야한다.
            ```java
            public void add() throws SQLException (
            	....
            }
            ```
        - 예외전환 방법
            - 호출한 쪽에서 예외를 받아서 처리할 때 좀더 명확하게 인지할수 있도록 돕기위한 방법.
            
            ```java
            catch(SQLException e) {
            	...
            	throw DuplicateUserIdException();
            }
            ```
