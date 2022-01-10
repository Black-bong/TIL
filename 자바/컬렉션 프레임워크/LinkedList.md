# LinkedList

## 메모리
- LinkedList를 이해하기 전에 우선 메모리가 무엇인지 알아보자.
- STORAGE(HDD/SSD): 비휘발성, 싸다, 용량이 크다, 느리다.
- MEMORY(DRAM): 휘발성, 비싸다, 용양이 작다, 데이터의 저장, 꺼냄이 빠르다.
- CPU: 3개중 데이터의 처리속도가 제일 빠르다.
- MEMORY는 CPU와 STORAGE의 데이터 처리 속도가 달라 이를 조율해주기 위한 중간 매개체로 사용한다.
- 자료구조의 미션은 메모리를 효율적으로 사용하는것

## ArrayList와 LinkedList의 차이
- 메모리를 사용하는 방식이 다르다.
- ArrayList: 각각의 요소가 선형적으로 붙어있다.
  - ArrayList에서 요소는 말 그대로 요소(Element)라는 이름을 사용
- LinkedList: 각각의 요소가 비선형적으로 연결되어있다.
  - LinkedList에서 요소는 노드(Node) 또는 버택스(Vertex)라는 이름을 사용

### LinkedList의 구조
- 각 노드들이 연결되어 있는 형태의 구조
- 하나의 노드에는 두개의 변수(data, link)가 있다.
  - data: 실제값이 저장되어 있다.
  - link: 다음 노드가 어떤것인지 저장되어 있다.
- HEAD: 첫번째 노드가 무엇인지에 대한 정보를 저장하고 있다.
- LinkedList는 HEAD를 통해 첫번째 노드를 찾고, 그 후 link에 저장되어 있는 다음 노드 값을 활용해 사용하게 된다.

### LinkedList의 동작
- LinkedList 예시: 5 -> 6 -> 21 -> 23 -> 15
```java
Node newNode = new Node(input);
temp.next = head;
head = temp;
```
- Firstadd(85) 실행: 85 -> 5 -> 6 -> 21 -> 23 -> 15
```java
Node temp1 = head;
while (--k != 0) {
    temp1 = temp1.next;         
}
Node temp2 = temp1.next;
Node newNode = new Node(input);
temp1.next = newNode;
newNode.next = temp2
```
- add(2, 23) 실행: 85 -> 5 -> 23 -> 6 -> 21 -> 23 -> 15
- 우리가 저장할 위치의 앞, 뒤에 있는 요소의 참조값을 찾아서 temp1, temp2에 저장해야한다.
```java
Node temp1 = head;
while (--k != 0) {
    temp1 = temp1.next;
}
Node deleted = temp1.next;
temp1.next = deleted.next
delete deleted;
```
- remove(2) 실행: 85 -> 5 -> 6 -> 21 -> 23 -> 15
- LinkedList는 중간에 데이터를 추가, 삭제하게 되면 이전, 이후 요소의 참조값만 변경하면 되기 때문에 빠르다.
- ArrayList는 중간에 데이터를 추가, 삭제하게 되면 해당 요소의 뒤에 있는 모든 값이 이동해야하기 때문에 느리다.
```java
Node temp1 = head;
while (--k != 0) {
    temp1 = temp1.next;
}
return temp1.next;
```
- get(2) 실행: 85 -> 5 -> 6 -> 21 -> 23 -> 15
- LinkedList: link를 통해 순차적으로 접근해야하기 떄문에 느리다.
- ArrayList: 주소를 통해 직접 접근하기 때문에 빠르다.

|종류|추가/삭제|인덱스 조회|
|---|---|---|
|ArrayList|느리다|빠르다|
|LinkedList|빠르다|느리다|

- 자료구조를 사용함에 있어 장점과 단점을 서로 다르게 가지고 있기 때문에(Trade-off) 경쟁관계에 있는 구현이 존재한다.
- 사용하는 용도에 맞게 장점은 최대한 살리고, 단점은 최소화 하여 사용해야한다.