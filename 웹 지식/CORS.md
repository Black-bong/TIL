# CORS(Cross Origin Resource Sharing)

## CORS란?

- SOP(Same Origin Policy)로 인해 막혀 있는 다른 Origin간의 리소스 상호작용을 가능하게 해주는 매커니즘
- JSONP등의 우회 방식을 막고, 합법적으로 어떤 기준을 충족시키면 리소스 공유가 되도록 만들어졌다.
- 처음 CORS를 만나면 CORS 때문에 안된다고 생각하지만, 그게 아니라 CORS을 통해 리소스 상호작용을 가능하게끔 해달라고 알려주는 것

## Simple request

- GET 또는 POST 등 일정 조건의 요청들에 사용

### 일반적인 CORS

- CORS가 발생할 경우 RequestHeader에 Origin 정보가 추가로 담겨져서 보내진다.
- ResponseHeader에는 Access-Control-Allow-Origin 정보가 담겨 보내진다.
- Origin 정보와 Access-Control-Allow-Origin이 같으면 안전한 요청으로 브라우저가 간주하고, 응답 데이터를 받아 오게 된다.
- 두개의 정보가 다를 경우 응답 데이터를 받아오지 않고, 오류 메시지를 출력하게 된다.

### 토큰 등 사용자 식별 정보가 담긴 CORS

- 요청 옵션에 credentials 항목을 true로 세팅해야 한다.
- 요청을 받는 쪽에서는 아무 출처를 허용하는 와일드카드(*)이 아니라 보내는 쪽의 출처(Origin)을 정확하게 명시하고, Access-Control-Allow-Credentials 항목을 true로 맞춰줘야 한다.

## Preflight 요청

- 본 요청을 보내기 전에 Preflight 요청을 먼저 보내서 본 요청이 안전한지 확인하고, 안전해야만 본격적인 요청이 가능하다.

# REFERENCE

[mdn cors](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)