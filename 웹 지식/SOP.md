# SOP(Same Origin Policy)

## SOP란?

> The **same-origin policy** is a critical security mechanism that restricts how a document or script loaded by one origin can interact with a resource from another origin. [mdn SOP](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy)

- SOP는 하나의 `Origin`으로 부터 로드된 `Document`또는 `Script`가 다른 `Origin의 리소스`와 상호작용 할 수 있는 방법을 제한하는 중요한 보안 메커니즘입니다.

### Origin

> Web content's **origin** is defined by the *scheme* (protocol), *hostname* (domain), and *port* of the URL used to access it. Two objects have the same origin only when the scheme, hostname, and port all match.[mdn Origin](https://developer.mozilla.org/en-US/docs/Glossary/Origin)

- 웹 콘텐츠의 `Origin`은 엑세스에 사용되는 `URL`의 `스키마`, `호스트 이름` 그리고 `포트`에 의해 `정의`되며, 두개의 `Origin`은 `스키마`, `호스트 이름`, `포트`가 `모두 일치`하는 경우에 만 두 개의 `Origin`이 같다고 할 수 있다.

```html
https://www.naver.com:433

https -> Scheme(protocl)
www.naver.com -> Hostname(domain)
433 -> Port
```

### Same Origin Ex

| URI                                           | Same |
| :-------------------------------------------- | ---- |
| `https://www.geombong.com/path/sop.html`      | O    |
| `https://www.geombong.com:433/path/sop2.html` | O    |
| `http://www.geombong.com/path/page.html`      | X    |
| `http://www.geombong.com:8423/path/page.html` | X    |

- https의 기본 포트가 433이기 때문에 1번 2번 URL은 same-origin을 갖는다.
- http의 기본 포트가 80, 3번 URI은 기본 포트가 생략되어 있는 형태로 4번 URL와 포트 번호가 다르기 때문에 same-origin을 갖지 않는다.

### SOP의 적용

- `http://localhost:3000`에서`http://localhost:8080/api/users`로 요청을 보낼때 Origin이 같지 않기 때문에 요청으로 부터 응답되어 오는 Json 데이터를 읽을 수가 없다.
- iframe을 사용할 때도 Origin이 다르다면 iframe의 내부와 외부의 접근이 매우 제한적이게 된다.
- 웹 자체의 데이터 베이스인 로컬 스토리지와 세션 스토리지가 있는데, 이 데이터 베이스는 Origin마다 생성이되고, 생성된 데이터 베이스에는 same-origin을 갖는 document나 script만 접근이 가능하다.

## Cross-Origin 통신

`Cross-Origin`간의 통신이 필요할 경우

- JSONP
	- 예전에 사용하던 방식 우회하여 SOP를 해결(꼼수)
	- 요즘에는 사용해서도 안되고 사용하지도 않지만, 레거시 시스템에서 종종 보일때도 있다고 한다.

- CORS 사용
	- 허용할 Origin 만을 `Access-Control-Allow-Origin`에 추가
	- 모든 Origin을 대상으로 열어두면 안된다, 반드시 허용할 Origin 만을 추가하여 사용

# REFERENCE

[mdn web docs](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy)