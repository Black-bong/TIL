# 타임리프

## 타임리프의 기본기능

### 텍스트(text,utext)

```html
<ul>
    <li>th:text 사용 <span th:text="${data}"></span></li>
    <li>컨텐츠 안에서 직접 출력하기 = [[${data}]]</li>
</ul>
```

- th:text="${data}": model에서 넘어오는 data 값을 텍스트 그대로 출력

```html
<ul>
    <li>th:text = <span th:text="${data}"></span></li>
    <li>th:utext = <span th:utext="${data}"></span></li>
</ul>

<h1><span th:inline="none">[[...]] vs [(...)]</span></h1>
<ul>
    <li><span th:inline="none">[[...]] = </span>[[${data}]]</li>
    <li><span th:inline="none">[(...)] = </span>[(${data})]</li>
</ul>
```

- utext: 태그를 escape처리 하지 않고 출력
- ex): `<b>`hello`</b>` world! -> hello 만 `<b>`태그로 인해 볼드 처리되고 그 결과가 출력

### 변수(SpringEL)

```html
<ul>Object
    <li>${user.username} = <span th:text="${user.username}"></span></li>
    <li>${user['username']} = <span th:text="${user['username']}"></span></li>
    <li>${user.getUsername()} = <span th:text="${user.getUsername()}"></span></li>
</ul>
<ul>List
    <li>${users[0].username} = <span th:text="${users[0].username}"></span></li>
    <li>${users[0]['username']} = <span th:text="${users[0]['username']}"></span></li>
    <li>${users[0].getUsername()} = <span th:text="${users[0].getUsername()}"></span></li>
</ul>
<ul>Map
    <li>${userMap['userA'].username} = <span th:text="${userMap['userA'].username}"></span></li>
    <li>${userMap['userA']['username']} = <span th:text="${userMap['userA']['username']}"></span></li>
    <li>${userMap['userA'].getUsername()} = <span th:text="${userMap['userA'].getUsername()}"></span></li>
</ul>
```

- model에서 넘어오는 User객체의 username을 출력
- list와 map도 사용이 가능하다.
- 각각 3가지의 출력 방법이 있는데, 첫번째 출력 방법을 가장 많이 사용한다.
- getter가 없어도 값을 가져올수 있다.(신기)

### 지역변수

```html
<div th:with="first=${users[0]}">
    <p>처음 사람 이름은 <span th:text="${first.username}"></span></p>
</div>
```

- th:with를 통해 값을 지정하면, 지역변수로 사용이 가능하다.

# REFERENCE
[스프링 MVC 2편 - 백엔드 웹 개발 활용 기술](https://www.inflearn.com/course/스프링-mvc-2/dashboard)
