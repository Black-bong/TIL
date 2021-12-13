# HTML class와 id
### class, id의 차이점

- 같은 클래스 이름을 여러 요소가 가질 수 있지만, 같은 아이디를 여러 요소가 공유할 수는 없다.
- 한 요소가 여러 클래스를 가질 수 있지만, 한 요소는 하나의 아이디만 가질 수 있다.(단, 한 요소가 클래스도 여러 개 갖고 아이디도 하나 가질 수 있다.)

### class

- big-blue-text 라는 클래스 명을 사용
- .을 사용해 클래스 명이라는 것을 표시

```html
<p class="big-blue-text">First</p>
<p>Second</p>
<p class="big-blue-text">Third</p>
```

```css
.big-blue-text {
  color: blue;
  font-size: 48px;
}
```

### id

- favorite-text 라는 id 명을 사용
- #을 사용해 id 명이라는 것을 표시

```html
<p id="favorite-text">First</p>
<p>Second</p>
<p>Third</p>
```

```css
#favorite-text {
  color: blue;
  font-size: 48px;
}
```
