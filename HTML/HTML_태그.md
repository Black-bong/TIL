# HTML태그
### `<title>` 태그

- 페이지의 제목을 작성할때 나타내는 태그

```html
<title>My First Website</title>
```

### `<h1>` ~ `<h6>` 태그

- 페이지의 머리말을 작성할때 사용하는 태그
- 각 머리말의 크기는 나중에 마음대로 설정할 수 있지만, 따로 설정해주지 않으면 **`<h1>`**부터 순서대로 작아진다.

```html
<h1>머리말 1</h1>
<h2>머리말 2</h2>
<h3>머리말 3</h3>
<h4>머리말 4</h4>
<h5>머리말 5</h5>
<h6>머리말 6</h6>
```

### `<p>` 태그

- 보통 문단은 **`<p>`**(paragraph) 태그 안에 넣는다.
- 직접 설정할 수도 있지만 **`<p>`** 태그 위, 아래에는 기본적으로 여백이 조금씩 있다.

```html
<p>HTML 기초 공부 기초만 후다닥</p>
```

### **`<b>` 태그**

- 텍스트에 bold효과를 줄때 사용

```html
Hello <b>World</b>!
```

### **`<i>` 태그**

- 텍스트 기울이기

```html
Hello <i>World</i>!
```

### **`<strong>` 태그**

- `<b>` 태그와 같이 글자를 굵게 만들어주지만 보이지 않는 설명을 붙여 시각 장애인, 학습/인지 장애인, 노인, 다문화 가정의 웹 접근성을 지원해주는 목적으로 사용된다.

```html
Hello <strong>World</strong>!
```

### **`<em>` 태그**

- `<i>` 태그와 동일하나, `<strong>` 태그와 마찬가지로 웹 접근성을 지원해주는 목적으로 사용된다.

```html
Hello <em>World</em>!
```

### `<style>` 태그

- 사이트에 CSS 스타일을 입혀주기 위해서 **`<style>` 사용한다.**

```html
<!-- 여기에 html 코드 -->

<style>
/* 여기에 CSS 코드 */
</style>
```

- `<style>` 태그 안에서 사용할수 있는 기본적인 속성들
- 텍스트 크기조절 px단위를 가장 많이 사용한다.
    - font-size: 00px;
- 텍스트를 왼쪽, 가운데, 또는 오른쪽으로 정렬할 수 있습니다.
    - text-align: left;
    - text-align: right;
    - text-align: center;
- 텍스트 색 입히기
    - color: lime;
- 요소사이에 여백을 넣을때 사용
    - margin-bottom: 80px; <!— 아래쪽만 여백 —>
    - margin: 80px 80px 80px 80px; <!— 상,하,좌,우 여백 —>
    - margin: 100px 50px; <!— 상,하: 100px, 좌,우: 50px 여백 —>

### `<div>` 태그

- 같이 유형을 묶을때 사용 특별한 기능은 없지만 묶음으로 유형을 관리할때 좀 더 쉽게 관리 할 수 있게 해준다.

### 외부 css 파일 + `<link>` 태그

- href: 주소
- rel: 관계

```css
/* css */
h1 {
  color: green;
  text-align: center;
}

p {
  font-size: 18px;
}
```

```html
<!-- HTML -->
<link href="css/styles.css" rel="stylesheet">

<h1>Hello World!</h1>
<p>Hello HTML</p>
```
