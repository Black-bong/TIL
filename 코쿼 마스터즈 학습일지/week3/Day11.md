# 11일차 학습일지

# JVM 이해하기

## 자바, JVM, JDK 그리고 JRE

![JDK](https://user-images.githubusercontent.com/78953393/149734672-ebaff535-8de7-40f9-bda1-fdb260aea379.png)

### JVM(Java Virtual Machine)
- 자바 가상 머신으로 자바 바이트 코드(.class파일)를 OS에 특화된 코드로 변환하여 실행하는 역활을 한다.
- 그 외 class를 읽고, 메모리에 올리고 실행하는 등등의 일을 진행
- 특정 플랫폼에 종속적이다.
- 정해신 표준이 있고, JVM 밴더마다 다양한 구현체가 존재한다.

### JRE(Java Runtime Environment)
- 자바 애플리케이션을 실행할 수 있도록 구성된 배포판
- JVM과 자바의 핵심 라이브러리가 함께 포함되어 있다.
- 개발 관련 도구는 포함하지 않는다.(JDK가 제공)
- JRE만 있어도 자바를 실행 할 순 있으나, 컴파일은 불가능하다.
- JRE만 따로 다운받아 JRE만 따로 사용하는 일은 거의 없다.

### JDK(Java Development Kit)
- JRE + 개발에 필요한 툴을 포함하고 있다.
- 오라클은 자바 11부터는 JDK만 제공, 따로 JRE를 제공하진 않는다.

### 자바
- 프로그래밍 언어
- JDK에 들어있는 자바 컴파일러(javac)를 사용하여 바이트코드(.class 파일)로 컴파일 할 수 있다.
- 자바를 JVM, JDK, JRE를 다 포함시켜 이야기하는 것은 좋지 않다. 각각 저게 어떤것이고 무엇이 다른지 구분할 수 있어야한다.
  - 예로 자바 유로화 이슈에 관해 자바 자체가 유료화로 되는 것이 아니라 Oracle JDK11을 상용으로 사용할 때 유료인것.
  - 이렇게 문맥적으로 어떻게 사용하느냐에 따라 그 뜻이 크게 다를수 있기 때문에 구분할 수 있어야한다.

## JVM의 구조

![JVM](https://user-images.githubusercontent.com/78953393/149739256-7a67df86-6c5e-4344-9aff-83a2ac167c5d.png)

### 클래스 로더 시스템
- .class에서 바이트코드를 읽고 메모리에 저장
- 로딩: 클래스를 읽어오는 과정
- 링크: 래퍼런스를 연결하는 과정
- 초기화: static값을 초기화 및 변수에 할당하는 과정

### 메모리
- 메소드(Method) 영역: 클래스 수준의 정보(클래스 이름, 부모 클래스 이룸, 메소드, 변수)를 저장, 여기에 저장된 자원을 공유하는 자원이다.
  - 다른 영역에서 저장된 값을 참조할 수 있다.
- 힙(Heap) 영역: 만들어진 객체가 저장되는 영역, 메소드 영역과 마찬가지로 저장된 자원을 공유할 수 있다.
- 스택(Stack) 영역: 쓰레드 마다 런타임 스택을 만들고, 그 안에 메소드 호출을 스택 프레임이라 부르는 블럭이 쌓는다(콜 스택). 쓰레드가 종료되면 런타임 스택은 사라진다.
- PC(Program Counter) 레지스터(PC Registers): 쓰레드 마다 쓰레드 안에 현재 실행할 스택 프레임을 가리키는 포인터가 생성된다.
- 네이티브 메서드 스택: 쓰레드 마다 생성, 네이티브 메소드를 호출할때 사용하는 별도의 메서드 스택
  - 네이티브 메서드: 메소드에 네이티브란 키워드가 붙어 있고, 구현을 자바가 아닌 C,C++로 구현한 메서드
  - 예로 `public static native Thread currentThread();` 메서드가 있다.
  - 이런 API들을 자바 네이티브 메서드 인터페이스(JNI)라고 부른다.

### 실행 엔진
- 인터프리터: 바이트 코드를 한줄 씩 실행, 반복되는 코드가 있어도 계속 한줄 씩 모두 실행하여 비효율적이다. 그걸 효율적으로 바꾸기 위해 JIT를 사용
- JIT(Just In Time) 컴파일러: 인터프리터의 효율을 높이기 위해, 인터프리터가 반복되는 코드를 발견하면 JIT컴파일러로 반복되는 코드를 모드 네이티브 코드로 바꿔둔다. 그 다음 인터프리터는 네이티브 코드로 컴파일된 코드를 사용한다.
- GC(Garbage Collector): 더이상 참조되지 않는 객체를 모아서 정리한다. 실행 엔진의 핵심.
  - 이해가 필수, 필요에 따라 커스텀해서 사용해야 할 경우도 있다.
  - 필요에 따라 GC의 종류를 선택해서 적절하게 사용해야한다.(성능을 위해)

### JNI(Java Native Interface)
- 자바 애플리케이션에서 C, C++, 어셈블리로 작성된 함수를 사용할 수 있는 방법을 제공
- `Native`키워드를 사용한 메서드 호출

### 네이티브 메소드 라이브러리
- C, C++로 작성 된 라이브러리

## 클래스 로더

### 클래스 로더
- 로딩, 링크, 초기화순으로 진행된다.
- 로딩
  - 클래스 로더가 .class 파일을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들고 `메서드 영역`에 저장
  - 메서드 영역에 저장하는 데이터
    - FQCN(Fully Qualified Class Name): 클래스가 속한 패키지명을 모두 포함한 이름(정규화된 이름)
    - 클래스(Class) | 인터페이스(Interface) | 이늄(Enum)
    - 메소드와 변수
  - 로딩이 끝나면 해당 클래스 타입의 Class객체를 생성하여 `힙 영역`에 저장
- 링크
  - Verify, Prepare, Resolve(optional) 세 단계로 나눠져 있다.
  - Verify: .class파일 형식이 유효한지 체크한다.
  - Preparation: 클래스 변수와 기본값에 필요한 메모리를 준비하는 과정
  - Resolve: 심볼릭 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체한다.
- 초기화
  - static 변수의 값을 할당한다.

## GC(Garbage Collector)
- 동적으로 할당한 메모리의 영역 중 사용하지 않는 영역을 탐지하여 해지하는 기능
  - Stack: 정적으로 할당한 메모리 영역
  - Heap: 동적으로 할당한 메모리 영역

### Stop-The-World
- `GC`를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것이다.
- `Stop-The-World`가 발생하면 `GC`를 실행하는 쓰레드를 제외한 나머지 쓰레는 모두 작업을 멈춘다.
- `GC`작업을 완료한 이후 중단한 작업이 다시 시작된다.

### GC의 과정(Mark And Sweep)
1. GC가 Stack의 모든 변수를 스캔하면서 각각 어떤 객체를 참고하고 있는지 찾아서 마킹한다.
2. Reachable Object가 참조하고 있는 객체도 찾아서 마킹한다.
3. 마킹되지 않은 객체를 Haep에서 삭제한다.
- 참조중인 객체를 찾아서 모두 마킹하고, 마킹되지 않은 객체를 삭제하는 방식
- 1번 2번 과정이 Mark, 3번 과정이 Sweep

### GC는 언제 일어날까?
- 힙 영역 안의 세부 영역
  - New Generation
    - Eden
    - Servival0
    - Servival1
  - Old Generation
- `Eden` 영역의 메모리가 모두 사용했을때 `GC가` 발생, 이걸 `MinorGC`라고 한다.
- `Eden` 영역에서 `Reachable` 객체는 `Servival0` 영역으로 이동된다.
- `UnReachable` 객체는 메모리에서 해제된다.
- 이때 `Mark And Sweep`는 `Eden`영역에서만 이뤄진다.
- 위의 과정이 `Servival0`영역이 모두 사용될때 까지 반복적으로 진행
- `Servival0`영역이 모두 사용되면 `Servival0`영역에서 `Mark And Sweep`이 일어나고, 살아남은 객체 즉 `Reachable`객체는 Servival1영역으로 이동한다.
- `Servival`영역 간의 객체 이동이 발생하면 해당 객체의 `Age`값이 증가
- 이후 Eden 영역이 또 모두 사용되게 되면, 다시한번 `GC`가 발생하고 이번에 살아남은 객체는 기존에 객체가 보관되어 있는 영역 즉`Servival1`영역으로 이동하게된다.
- `Servival0`, `Servival1`영역 둘 중 하나는 항상 비워져 있는 상태로 유지된다.
- 다시 `Servival1` 영역이 모두 사용되면 `GC`가 발생하고 살아남은 객체는 `Servival0` 영역으로 이동하며, `Age` 값이 증가한다.
- 위의 과정이 반복되면서 살아남은 객체의 `Age`값이 증가하다가 특정 `Age`값을 넘어서면 `Old Generation` 영역으로 이동하게 된다.
- `New Generation`에서 `Old Generation`영역으로 이동되는 이 과정을 `Promotion`이라고 한다.
- `Old Generation` 영역이 모두 사용되게 되면 GC가 발생하며, 이걸 `MajorGC`라고 한다.
- 위의 과정이 계속 반복되면서 `GC`가 메모리를 관리한다.

## GC의 종류

### Serial GC
- GC를 처리하는 쓰레드가 1개이다.
- CPU코어가 1개만 있을때 사용하는 방식
- Mark-Compact Collection 알고리즘 사용
  - Mark-Compact Collection 알고리즘: `Mark And Sweep`이후 군대군대 흩어져있는 데이터를 한곳으로 몰아서 메모리 파편화를 방지하는 알고리즘

### Parallel GC
- `GC`를 처리하는 쓰레드가 여러 개 이다.
- `Serial GC`보다 빠르게 객체를 처리할 수 있다.
- 메모리가 충분하고, CPU코어의 개수가 많을때 사용하면 좋다.

### Concurrent Mark And Sweep GC(CMS GC)
- Stop-The-World로 인해 발생되는 애플리케이션 중단 시간을 줄이기 위해 만들어진 `GC`
- 응답, 속도가 중요한 애플리케이션(웹 애플리케이션 등등)에서 사용
- 단점으로는 다른 `GC`의 방식보다 CPU와 메모리를 많이 사용한다.

### G1 GC
- 각 영역을 `Region`영역으로 나눈다.
- `GC`가 일어날때, 전체영역을 탐색하지 않는다.

----
# Reference
- <a href = "https://www.youtube.com/watch?v=vZRmCbl871I&t=254s">[10분 테코톡] 👌던의 JVM의 Garbage Collector</a>
- <a href = "https://d2.naver.com/helloworld/1329">Java Garbage Collection</a>
- <a href = "https://www.inflearn.com/course/the-java-code-manipulation/dashboard">더 자바, 코드를 조작하는 다양한 방법</a>