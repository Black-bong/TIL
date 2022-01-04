# 19일차 학습일지
## 학습
### 스트림 심화학습
### 스트림이란?
- 다양한 데이터 소스를 표준화된 방법으로 다루기 위한 것
- Strem<T> Collection.stream()
- 스트림이 제공하는 기능 - 중간 연산과 최종연산

### 스트림만들기

1. 스트림 만들기
2. 중간연산
    - 연산결과가 스트림인 연산
    - 0 ~ 여러번 가능하다.
3. 최종연산
    - 연산결과가 스트림이 아닌 연산
    - 스트림의 요소를 소모한다.
    - 1번만 가능하며, 최종연산 진행 후 결과가 나타난다.
4. 사용예
    ```java
    stream.distinct().limit(5).sorted().forEach(System.out::println)
        //중간연산    중간연산    중간연산     최종연산
    ```
### 스트림의 특징
- 스트림은 데이터 소스로부터 데이터를 읽기만할 뿐 변경하지 않는다.
    ```java
    List<Integer> list = Arrays.asList(3, 1, 5, 4, 2);
    // list를 정렬해서 새로운 List에 저장
    List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
    ```
- 스트림은 Iterator처럼 일회용이다.(필요하면 다시 스트림을 생성해야 함)
    ```java
    strStream.forEach(System.out::println); // 모든 요소를 화면에 출력(최종연산)
    int numOfStr = strStream.count();       // 에러, 스트림은 이미 닫혔음.
    ```
- 최종 연산 전까지 중간연산이 수행되지 않는다. - 지연된 연산
    ```java
    IntStream intStream = new Random().ints(1, 46); // 1 ~ 45범위의 무한 스트림
    intStream.distinct().limit(6).sorted() // 중간 연산
        .forEach(i -> System.out.println(i + ",")); // 최종 연산
    ```
- 스트림은 작업을 내부 반복으로 처리한다.
    ```java
    for (String str : strList)
    	System.out.println(str);
    
    //위에 코드 축약
    stream.forEach(System.out::println);
    
    // 스트림의 내부
    void forEach(Consumer<? super T> action) {
    	Object.requireNonNull(action); // 매개변수의 널 체크
    	for (T t : src)    // 내부 반복(for문을 메서드 안으로 넣음)
    		action.accept(t);
    }
    ```
    - 성능 비효율적이지만, 코드가 간결해진다.
- 스트림의 작업을 병렬로 처리 - 병렬스트림
    ```java
    Stream<String> strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
    int sum = strStream.parallel() // 병렬스트림으로 전환(속성만 변경)
              .mapToInt(s -> s.length()).sum(); // 모든 문자열 길이의 합
    ```
- 기본형 스트림
    - intStream, LongStream, DoubleStream
    - 오토박싱 & 언박싱의 비효율이 제거됨 (Stream<Integer>대신 IntStream사용)
    - 숫자와 관련된 유용한 메서드를 Stream<T>보다 더 많이 제공

### 스트림 만들기
- 컬렉션
- Collection인터페이스의 stream()으로 컬렉션을 스트림으로 변환
    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    Stream<Integer> intStream = list.stream(); // list를 스트림으로 변환
    
    intStream.forEach(System.out::print); // 12345
    ```
- 배열
- 객체 배열로부터 스트림 생성하기
    ```java
    Stream<T> Stream.of(T... values) // 가변인자
    Stream<T> Stream.of(T[])
    Stream<T> Arrays.stream(T[])
    Stream<T> Arrays.stream(T[] array, int startInclusive, int endExclusive) // 배열의 일부만 스트림으로 만듬 0,3이면 인덱스 0,1,2
    ```  
- 임의의 수
- 난수를 요소로 갖는 스트림 생성하기
    ```java
    IntStreamintStream = new Random().ints(); // 무한 스트림
    intStream.limit(5).forEach(System.out::println); // 5개의 요소만 출력한다.
    IntStream intStream = new Random().ints(5); // 크기가 5인 난수 스트림을 반환
    ```
    - ints(): Integer.MIN_VALUE ≤ ints() ≤ Integer.MAX_VALUE
    - longs(): Long.MIN_VALUE ≤ longs() ≤ Long.MAX_VALUE
    - double(): 0.0 ≤ doubles() ≤ 1.0
- 지정된 범위의 난수를 요소로 갖는 스트림을 생성하는 메서드
    ```java
    IntStream ints(int begin, int end) // 무한 스트림 (5, 10)이면 5부터 9까지의 숫자 중 랜덤으로 요소를 갖는다.
    IntStream ints(long streamSize, int begin, int end) // 유한 스트림
    ```
- 특정 범위의 정수를 요소로 갖는 스트림 생성하기
    ```java
    IntStream IntStream.range(int begin, int end) // (1, 5) 1, 2, 3, 4
    IntStream IntStream.rangeClosed(int begin, int end) // (1, 5) 1, 2, 3, 4, 5
    ```
- 람다식을 소스로 하는 스트림 생성하기
    ```java
    // 이전 요소에 종속적, 무한스트림
    static <T> Stream<T> interate(T seed, UnaryOperator<T> f)
    static <T> Stream<T> interate(0, n -> n + 2); // 0, 2, 4, 6 ...
    // 이전 요소에 독립적, 무한스트림
    static <T> Stream<T> generate(Supplier<T> s)
    Stream<Double> randomStream = Stream.generate(Math::random);
    Stream<Integer> oneStream = Stream.generate(() -> 1); // 계속 1 출력
    ```
    - iterate()는 이전 요소를 seed로 해서 다음 요소를 계산한다.
    - generate()는 seed를 사용하지 않는다.

### 스트림의 연산
- 중간연산
    | 중간 연산 | 셜명 |
    | --- | --- |
    | Stream<T> distinct() | 중복을 제거 |
    | Stream<T> filter(Predicate<T> predicate) | 조건에 안 맞는 요소 제외 |
    | Stream<T> limit(long maxSize) | 스트림의 일부를 잘라낸다. |
    | Stream<T> skip(long n) | 스트림의 일부를 건너뛴다. |
    | Stream<T> peek(Consumer<T> action) | 스트림의 요소에 작업수행 |
    | Stream<T> sorted() | 스트림의 요소를 정렬한다. |
    | Stream<T> sorted(Comparator<T> comparator) | 스트림의 요소를 정렬한다. |
    | map(Function<T, R> mapper) | 스트림의 요소를 변환한다. |
    | mapToDouble(ToDoubleFunction<T> mapper) | 스트림의 요소를 변환한다. |
    | mapToInt(ToIntFunction<T> mapper) | 스트림의 요소를 변환한다. |
    | mapToLong(ToLongFunction<T> mapper) | 스트림의 요소를 변환한다. |
    | flatMap(Function<T, Stream<R>> mapper) | 스트림의 요소를 변환한다. |
    | flatMapToDouble(Function<T, DoubleStream> m) | 스트림의 요소를 변환한다. |
    | flatMapToInt(Function<T, IntStream> m) | 스트림의 요소를 변환한다. |
    | flatMapToLong(Function<T, LongStram> m) | 스트림의 요소를 변환한다. |
- 최종연산
    | void forEach(Consumer<? super T> action | 각 요소에 지정된 작업 수행 |
    | --- | --- |
    | void forEachOrdered(Consumer<? super T> action | 각 요소에 지정된 작업 수행, 순서 유지, 병렬스크림 |
    | long count() | 스트림 요소의 개수 반환 |
    | Optional<T> max(Comparator<? super T> comparator | 스트림의 최대값 반환 |
    | Optional<T> min(Comparator<? super T> comparator | 스트림의 최소값 반환 |
    | Optional<T> findAny() | 스트림의 요소 중 아무거나 하나 반환 filter() 와 함께 사용된다.(병렬) |
    | Optional<T> findFirst() | 스트림의 요소 중 첫번째 요소를 반환 filter()와 함께 사용된다.(직렬) |
    | boolean allMatch(Predicate<T> p) | 주어진 조건을 모든 요소가 만족시키는지 확인 |
    | boolean anyMatch(Predicate<T> p) | 주어진 조건을 만족시키는 요소가 하나라도 있는지 확인 |
    | boolean noneMatch(Predicate<T> p) | 주어진 조건을 모든 요소가 만족하지 않는지 확인 |
    | Object[] toArray() | 스트림의 모든 요소를 배열로 반환 |
    | A[] toArray(IntFunction<A[]> generator) | 스트림의 모든 요소를 배열로 반환 |
    | Optionbal<T> reduce(BinaryOperator<T> accumulator) | 스트림의 요소를 하나씩 줄여가면서 계산한다. |
    | T reduce(T identity, BinaryOperator<T> accumulator) | 스트림의 요소를 하나씩 줄여가면서 계산한다. |
    | U reduce(U identity, BiFunction<U, T, U> accumlator, BinaryOperator<U> combiner) | 스트림의 요소를 하나씩 줄여가면서 계산한다. |
    | R collect(Collector<T, A, R> collector) | 스트림의 요소를 수집한다. 주로 요소를 그릅화 하거나 분할한 결과를 컬렉션에 담아 반환하는데 사용된다. |
    | R collect(Supplier<R> supplier, BiConsumer<R, T> accumulator, BiConsumer<R, R> combiner | 스트림의 요소를 수집한다. 주로 요소를 그릅화 하거나 분할한 결과를 컬렉션에 담아 반환하는데 사용된다. |
