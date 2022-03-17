# 자바의 Concurrent프로그래밍

## 멀티 스레드와 멀티 프로세싱

### 멀티 스레드와 멀티 프로세싱
- 스레드와 프로세싱은 앞에 공부를 하고 정리했기 때문에 여기서는 따로 정리는 안하고 복습 하는 시간을 가지는 것이 좋을거 같다.

## Executors

### Executors란?
- 고수준의 Concurrency 프로그래밍
- 스레드를 만들고 관리하는 작업을 애플리케이션에서 분리
- 그런 기능을 Executorts에게 위임

### Executors의 역할
- 스레드 만들기: 애플리케이션이 사용할 스레드 풀을 만들어 관리
- 스레드 관리: 스레드 생명 주기를 관리
- 작업 처리 및 실행: 스레드로 실행할 작업을 제공할 수 있는 API을 제공
- 주로 ExecutorService와 ScheduledExecutorService인터페이스를 사용한다.

### Executors의 사용
```java
public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Systme.out.println("Thread " + Thread.currentThread().getName());
        });
    }
    executorService.shutdown();
}
```
- submit()을 사용해서 실행
- ExecutorService는 submit()을 통해 작업을 실행한후, 다음 작업이 들어올때까지 무한 대기 상태에 빠지게된다.
- 명시적으로 shutdown()을 해줘야한다.
  - shutdown: 현재 진행중인 작업을 모두 마친 후 종료된다.

```java
public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("Hello"));
        executorService.submit(getRunnable("Java"));
        executorService.submit(getRunnable("World"));

        executorService.shutdown();
    }
    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
```
- 여러개의 스레드를 생성하여, 멀티 스레드로 작업하도록 만들 수 있다.
- 스레드 풀 안에 2개의 스레드가 형성되어 3개의 작업을 2개의 스레드가 번갈아가며 실행하게 된다.
```java
public class App {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
```
- ScheduledExecutorService의 사용
- 원하는 시간(delay) 이후에 스레드가 작업을 시작하도록 만들 수 있다.
```java
ececutorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS)
 // 1: initialDelay, 2: period
```
- scheduleAtFixedRate()를 사용하면, initialDelay(작업 시작 전 대기 시간)와 period(작업 시작 후 반복 작업 대기 시간)를 사용할 수 있다.
- 이떄는 shutdown()이 있으면 원하는 결과를 얻을 수 없으니, shutdown을 사용하지 말아야한다.

### 그외 Fork/Join
- 멀티 프로세서 기반에 애플리케이션 개발에 유용한 프레임워크
- 자바의 정석과 이자바 책에 잘 나와있다. 이따 보면서 정리해보자.

## Callable과 Future
- Runnable과 비슷하나, Runnable의 반환 타입은 void인 반면 Callable의 반환 타입은 V(제네릭)타입이기 때문에 값을 리턴할 수 있다.
- 별도의 스레드에서 작업을 진행하고 그 결과값을 가져오고 싶은 경우에 사용
- 리턴 값을 받아오기 위해 필요한 것이 Future다.

### Callable
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            TimeUnit.SECONDS.sleep(2);
            return "Hello";
        };

        Future<String> future = executorService.submit(hello);
        System.out.println("start");

        System.out.println(future.get()); 

        System.out.println("end");
        executorService.shutdown();
    }
}
```
- Callable의 사용 예제
- Runnable과는 다르게 작업의 결과물을 반환 받아서 사용할 수 있다.
- 단, future.get()(Blocking Call)으로 반환값을 얻을때 Callable로 실행하고 있는 작업이 종료되고 값을 반환될때까지 다른 스레드는 대기 상태가 된다.
  - 여기서는 main스레드가 대기 상태가 되며, 2초 후에 값이 반환되면 나머지 작업을 다시 진행하게 된다.
- 무작정 Callable을 사용한다고 해서 애플리케이션이 다 빨라지는건 아니다.

### isDone()
- 작업의 상태를 확인하는 메서드
- 작업이 종료되었으면 true, 아니면 false를 반환하게 된다.

### cancel()
- 작업을 취소시키는 메서드
- cancle()되는 순간 future.get()을 사용할 수 없다. 사용하게 되면 예외발생

### invokeAll(), invokeAny()
- invokeAll(): 동시에 실행된 작업이 모두 종료될때까지 다른 작업이 종료되어도 기다렸다가 값을 한번에 반환
  - 동시에 실행된 작업중 가장 긴 시간의 작업 시간만큼 시간 소요
- invokeAny(): 동시에 실행된 작업중 가장 빠르게 종료된 작업의 결과값을 반환
  - 동시에 실행된 작업중 가장 짧은 시간의 작업 시간만큼 시간 소요


## CompletableFuture

### CompletableFuture란?
- 자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 인터페이스
  - Future로도 어느정도 가능하긴 했으나, 어려운점이 많았다.
- Future로 하기 힘든 작업
  - Future를 외부에서 완료 시킬수 없다.
  - get()을 사용하지 않고는 작업이 끝났을때 콜백을 실행할 수 없다.(가장 큰 단점)
  - 여러 Future를 조합할 수 없다.
  - 예외 처리용 API를 제공하지 않는다.

### runAsync(), supplyAsync()
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
        });
        future.get();
    }
}
```
- runAsync(): 리턴값이 없는 경우 사용
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future.get());
    }
}
```
- supplyAsync(): 리턴값이 있는 경우 사용
### 콜백 사용하기
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply(s -> {
            System.out.println("name: " + Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future.get());
    }
}
```
- thenApply(): 리턴값이 있을 경우 사용
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept(s -> {
            System.out.println("name: " + Thread.currentThread().getName());
            System.out.println(s.toUpperCase()); 
        });
        future.get();
    }
}
```
- thenAccept(): 리턴값이 없을 경우 사용
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println("name: " + Thread.currentThread().getName());
        });
        future.get();
    }
}
```
- thenRun(): Runnable이 뒤에 온다고 생각하면 쉽다.
- CompletableFuture의 결과물도 사용이 불가능하다.
- get을 호출하기 전에 CompletableFuture의 결과물을 가지고 작업을 진행할 수 있게된다.
- ForkJoinPool.commonPool을 사용하여 별도의 스레드 생성 없이 사용이 가능하다.
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRun(() -> {
            System.out.println("name: " + Thread.currentThread().getName());
        });
        future.get();
    }
}
```
- 원한다면 스레드를 만들어서 사용하는것도 가능하다.
- 실행 결과로 스레드의 이름이 바뀌는걸로 확인이 가능하다.

### 조합하여 사용하기
```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> future = hello.thenCompose(App::getWorld);
        System.out.println(future.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
```
- thenCompose(): 두개의 future간에 의존성이 있는 경우 사용
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture future = hello.thenCombine(world, (h, w) -> {
            return h + " " + w;
        });

        System.out.println(future.get());
    }
}
```
- thenCombine(): 두개의 future간에 의존성이 없고, 두개의 future의 값을 활용해 하나의 결과값을 나타낼때 사용
```java
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(hello, world)
                .thenAccept(System.out::println);
        System.out.println(future.get());
    }
}
```
- allOf(): 두개 이상의 future를 활용하는 경우 사용
  - allOf()로 넘긴 future의 작업이 모두 종료되었을때 추가적인 콜백을 실행할 수 있다.
  - 문제는 allOf()로 넘긴 future의 결과값의 타입이 모두 다를수도 있고, 그 중에 오류가 발생한 future가 있을수도 있다.
  - 그래서 그냥 allOf()로 넘기고 get으로 결과값을 확인해 보면 null값이 출력되게 된다.
### 예외처리하기

# Reference
[더 자바, JAVA 8](https://www.inflearn.com/course/the-java-java8/dashboard)
