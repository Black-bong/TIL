# 27일차 학습일지

# 제네릭

## 제네릭이란?

- 클래스나 메서드에서 사용할 내부 데이터 타입을 외부에서 지정하는 기법
- 의도하지 않은 타입의 데이터가 들어오는 것을 막아준다.

```java
public class ItemBox<T> {
	List<T> items = new ArrayList<>();

	public void add (T item) {
		items.add(item);
	}
}
```

- 기본 생김새
- T: 타입 매개변수
- GenericEX<T>: 제네릭 클래스

```java
ItemBox<Potion> potionBox = new ItemBox<Potion>();
```

- ItemBox 인스턴스 생성

```java
public class ItemBox<Potion> {
	List<Potion> items = new ArrayList<Potion>();
	
	public void add (Potion item) {
		items.add(item);
	}
}
```

- ItemBox 인스턴스 생성 후 ItemBox의 타입 매개변수가 변화된다.
- 단 실제 타입이 Potion으로 변화 된것은 아니다.(Type Erasure 참고)

### 제네릭 타입 문자

- 암묵적으로 많이 사용하는 문자, 다른 문자를 사용해도 컴파일하는데 문제는 없다.

|타입|설명|
|---|---|
|\<T\>|Type|
|\<E\>|Element|
|\<K\>|Key|
|\<V\>|Value|
|\<N\>|Number|

### 제네릭을 사용하는 이유?

- 형변환을 하지 않아도 된다.
- 강한 타입 체크가 가능하다.(타입 안정성)
- 컴파일 단계에서 잘못된 자료형 사용을 체크해준다.

## 제네릭 메서드

```java
static <T extends Fruit> Juice makeJuice(FruitBox<T> box) {
	String tmp = "";
	for (var f : box.getList()) {
		tmp = f + " ";
		return new Juice(tmp);
	}
}
```
- 메서드를 호출할때 마다 다른 제네릭 타입을 대입할 수 있다.

```java
public <T> void add(T t) {
	...
}
```

- <> 안에 매개변수의 데이터 타입을 지정
- 타입 파라미터의 범위는 메서드 블록 이내로 한정
- 제네릭 메서드는 제네릭 클래스가 아니더라도 정의할 수 있다.

```java
class Name<T> {
	public <T> void printClassName(T t) {
		System.out.println(t.getClass().getName());
	}
}

public static void main(String[] arg) {
	Name<String> name = new Name<>();
	Class.printClassName(1);
	Class.printClassName(3.14);
}
```

- 제네릭 클래스와 제네릭 메서드의 타입 매개변수가 같다면 제네릭 메서드의 타입 매개변수를 우선한다.

## 제네릭 클래스

### Iterator<E>

- 클래스를 작성할때, Object타입 대신 E와 같은 타입 변수를 사용

```java
public interface Iterator<E> {
	...
	boolean hasNext() { ... };
	E next() { ... };
	void remove() { ... };
	...
}
```
- Iterator 내부 간략하게 표현

```java
Iterator<Student> it = list.iterator();
while (it.hasNext) {
	// Student s = (Student) it.next(); 제네릭이 아니라 Object로 되어 있을때는 형변환 필요
	Student s = it.next();
}
```

- 형변환 없이 사용이 가능

### HashMap<K, V>

- 여러 개의 타입 변수가 필요한 경우 콤마로 구분하여 사용 가능하다.

```java
public class HashMap<K, V> extends AbstractMap<K, V> {
	...
	public V get(Object key) { ... }
	public V put(K key, V value) { ... }
	public V remove(Object key) { ... }
	....
}
```

- HashMap 내부 간략하게 표현

### 타입 매개변수 제한

- T extends Item(상한 경계): Item클래스 혹은 Item클래스를 상속 받는 클래스만 사용 가능
- T super Class(하한 경계): Item클래스 혹은 Item클래스의 상위 클래스만 사용 가능

```java
class ItemBox<T> {
	private List<Potion> potions = new ArrayList<Potion>();

	public void add(T potion) {
		potions.add(potion); // 컴파일 에러 발생
	}
}
```

- T가 Potion 클래스의 하위 클래스가 아닐수도 있기 때문에 컴파일 에러 발생

```java
class ItemBox<T extends Potion> {
	private List<Potion> potions = new ArrayList<Potion>();

	public void add(T potion) {
		potions.add(potion);
	}
}
```

- 상한 경계를 사용해 T는 Potion클래스의 하위 클래스만 올수 있도록 명시해 줬기 때문에 컴파일 에러 해결

### 제네릭 제약

```java
Box<Apple> appleBox = new Box<>();
Box<Grape> grapeBox = new Box<>();
```

- 타입 변수에 대입은 인스턴스 별로 다르게 가능

```java
class Box<T> {
	static T item;
	static int compare(T t1, T t2) { ... } // 에러
	...
}
```

- static 멤버에 타입 변수 사용 불가

```java
class Box<T> {
	T[] itemArr;
		...
	T[] toArray() {
		T[] tmpArr = new T[itemArr.length]; // 에러
	}
}
```
- 배열, 객체를 생성할 때 타입 변수 사용불가, 타입 변수로 배열 선언은 가능

## 와일드 카드

```java
ArrayList<? extends Product> list = new ArrayList<Tv>(); // 상한 경계 와일드 카드
ArrayList<? super Product> list = new ArrayList<Object>(); // 하한 경계 와일드 카드
ArrayList<?> list = new ArrayList<Audio>(); // 비 경계 와일드 카드
```
- 하나의 참조 변수로 대입된 타입이 다른 객체를 참조 할 수 있다.
- 상한 경계 와일드 카드: 자기자신과 자기자신을 상속받은 하위 클래스만 인자가 될 수 있다.
- 하한 경계 와일드 카드: 자기자신과 자기가 상속받은 상위 클래스만 인자가 될 수 있다.
- 비 경계 와일드 카드: ?의 형태로 사용(List<?> ... ), 모든 타입이 인자가 될 수 있다.

```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {
	String tmp = "";
	for (var f : box.getList()) {
		temp = f + " ";
	}
	return tmp;
}
...

System.out.println(Juice.makeJuice(new FruitBox<Fruit>));
System.out.println(Juice.makeJuice(new FruitBox<Apple>));
```

- 메서드의 매개변수에 와일드 카드 사용

## 제네릭 타입의 형변환

- 제네릭 타입과 원시 타입 간의 형변환은 바람직 하지 않다.

```java
Box<Object> objBox = null;
Box box = (Box)objBox;	// 제네릭 -> 원시
objBox = (Box<Object>)box	// 원시 -> 제네릭
```

- 컴파일이 가능은 하지만 경고 발생

## 제네릭 타입의 제거(type erasure)

- 이건 좀 더 공부해봐야 할거 같다. 쉽게 정리가 안된다.

# REFERENCE

[이펙티브자바 5장 제네릭](http://www.yes24.com/Product/Goods/65551284)

[[10분 테코톡] 🌱 시드의 제네릭](https://www.youtube.com/watch?v=Vv0PGUxOzq0)

[자바의 정석 12장 제네릭](https://www.youtube.com/watch?v=QcXLiwZPnJQ&list=PLW2UjW795-f6xWA2_MUhEVgPauhGl3xIp&index=136)
