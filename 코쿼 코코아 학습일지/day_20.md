# 20일차 학습일지
## 학습
- 스트림의 중간연산
- 스트림의 최종연산

## 코딩
- 알고리즘 풀이

### 스트림의 중간연산
- 스트림 자르기(skip(), limit())
    ```java
    Stream<T> skip(long n) // 앞에서부터 n개 건너뛰기
    Stream<T> limit(long maxSize) // maxSize 이후의 요소는 잘라냄
    
    IntStream intStream = intStream.rangeClosed(1, 10); // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    intStream.skip(3).limit(5).forEach(System.out::print); // 4, 5, 6, 7, 8
    ```
- 스트림의 요소걸러내기(filter(), distinct())
    ```java
    Stream<T> filter(Predicate<? super T> predicate) // 조건에 맞는 요소만 남기고 나머지는 제거
    Stream<T> distinct() // 중복제거
    
    IntStream intStream = IntStream.of(1, 2, 2, 3, 3, 3, 4, 5, 5, 6);
    intStream.distinct().forEach(System.out::print); // 123456
    
    IntStream intStream = IntStream.rangeClosed(1, 10); // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    intStream.filter(i -> i % 2 == 0).forEach(System.out::print) // 2, 4, 6, 8, 10
    ```
- 스트림 정렬하기(sorted())
    ```java
    Stream<T> sorted() // 스트림 요소의 기본 정렬로 정렬
    Stream<T> sorted(Comparator<? super T> comparator) // 지정된 Comparator로 정렬
    ```
    | 문자열 스트림 정렬 방법 | 출력결과 |
    | --- | --- |
    | strStream.sorted() | CCaaabccdd // 기본정렬 |
    | strStream.sorted(Comparator.naturalOrder()) | CCaaabccdd // 기본정렬 |
    | strStream.sorted((s1, s2) → s1.compareTo(s2)); | CCaaabccdd // 람다식도 가능 |
    | strStream.sorted(String::compareTo); | CCaaabccdd // 위의 코드와 동일 |
    | strStream.sorted(Comparator.reverseOrder()) | ddccbaaaCC // 기본 정렬의 역순 |
    | strStream.sorted(Comparator.<String>naturalOrder().reversed()) | ddccbaaaCC // 기본 정렬의 역순 |
    | strStream.sorted(String.CASE_INSENSITIVE_ORDER) | aaabCCccdd // 대소문자 구분안함 |
    | strSTream.sorted(String.CASE_INSENSITIVE_ORDER.reversed()) | ddCCccbaaa // 대소문자 구부없이 역순 |
    | strStream.sorted(Comparator.comparing(String::length)) | bddCCccaaa // 길이 순 정렬 |
    | strStream.sorted(Comparator.comparingInt(String::length)) | bddCCccaaa // no 오토박싱 |
    | strStream.sorted(Comparator.comparing(String::length).reversed()) | aaaddCCccb |
- Comparator의 comparing()으로 정렬 기준을 제공
    - comparing(Function<T, U> keyExtractor)
    - comparing(Function<T, U> keyExtractor, Comparator<U> keyComparator)
    ```java
    studentsStream.sorted(Comparator.comparing(Student::getBan)) // 반 별로 정렬
        .forEach(System.out::println);
    ```
- 추가 정렬 기준을 제공할 때는 thenComparing()을 사용
    - thenComparing(Comparator<T> other)
    - thenComparing(Function<T, U> keyExtractor)
    - thenComparing(Function<T, U> keyExtractor, Comparator<U> keyComp)
    ```java
    studentStream.sorted(Comparator.comparing(Student::getBan) // 반별로 정렬
      .thenComparing(Student::getTotalScore) // 총점별로 정렬
      .thenComparing(Student::getName)) // 이름별로 정렬
      .forEach(System.out::println);
    ```
- 스트림의 요소 변환하기(map())
    ```java
    Stream<R> map(Function<? super T, ? extends R> mapper) // Stream<T> -> Stream<R>
    Stream<File> fileStream = Stream.of(new File("Ex1.java"), new File("Ex1"),
    		new File("Ex1.bak"), new File("Ex2.java"), new File("Ex1.txt"));
    Stream<String> filenameStream = fileStream.map(File::getName);
    filenameStream.forEach(System.out::println); // 스트림의 모든 파일의 이름을 출력
    ```
    - Stream<File> → map → Stream<String> 으로 변환
    ```java
    fileStream.map(File::getName) // Stream<File> -> Stream<String>
        .filter(s -> s.indexOf('.') != -1) // 확장자가 없는 것은 제외
        .map(s -> s.substring(s.indexOf('.') + 1)) // Stream<String> -> Stream<String>
        .map(String::toUpperCase) // Stream<String> -> Stream<String>
        .distinct()
        .forEach(System.out::print); // JAVABAKTXT
    ```
    - 파일 스트림에서 파일 확장자(대문자)를 중복없이 뽑아내기
    - indexOf → 특정 문자의 위치 찾기, 특정 문자가 없을 경우 -1을 반환
- 스트림의 요소를 소비하지 않고 엿보기(peek())
    ```java
    Stream<T> peek(Cinsumer<? super T> action) // 중간 연산(스트림을 소비 x)
    void forEach(Consumer<? super T> action) // 최종 연산(스트림을 소비 o)
    fileStream.map(File::getName)
        .filter(s -> s.indexOf('.') != -1)
        .peek(s -> System.out.printf("filename = %s%n", s))
        .map(s -> s.substring(s.indexOf('.') + 1))
        .peek(s -> System.out.printf("extension = %s%n", s))
        .forEach(System.out::println);
    ```
- 스트림의 스트림을 스트림으로 변환(flatMap())
    ```java
    Stream<String> strStrStrm = strArrStrm.flatMap(Arrays::stream); // Arrays.stream(T[])
    ```
    - Stream<String[]> → flatMap → Stream<String>
    - 각각의 문자열 배열이 하나의 문자열 배열로 변환
