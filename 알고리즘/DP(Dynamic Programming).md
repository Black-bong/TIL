## Fibonacci Numbers

```java
// 리커전 방식의 계산
int fib(int n) {
  if (n == 1 || n == 2) {
    return 1;
  }
  fib(n - 2) + fib(n - 1);
}
```

- 리커전 방식의 계산은 많은 계산이 중복으로 처리되기 때문에 비효율적이다.

```java
int fib(int n) {
  if (n == 1 || n == 2) {
    return 1;
  }
  else if (f[n] > -1 ) { // 배열 f가 -1로 초기화 되어 있다고 가정
    return f[n]; // 즉 이미 계산된 값이라는 의미
  }
  else {
    f[n] = fib(n - 2) + fib(n - 1); // 중간 계산 결과를 caching
    return f[n];
  }
}
```

- Memoization 방식 계산
- 중간 계산값을 배열(cache)에 저장(caching)하는 방식
- 리커전 방식에서는 계산된 값을 버리지만, Memoization 방식은 계산된 값을 저장하고 필요할때 사용
- 중복된 계산이 사라지게 된다.

```java
int fib(int n) {
  f[1] = f[2] = 1;
  for (int i = 3; i <= n; i++) {
    f[n] = f[n - 1] + f[n - 2];
  }
  return f[n];
}
```

- bottom-up 방식
- 처음부터 순차적으로 계산하는 방식
- 좁은 의미에서의 순환식을 bottom-up방식으로 계산하는 기술이 DP이다.

## 이항 계수

```java
int binomial(int n, int k) {
  if (n == k || k == 0) {
    return 1;
  }
  else {
    return binomial(n - 1, k) + binomial(n - 1, k - 1);
  }
}
```

- 이항 계수를 리커전 방식으로 계산
- 많은 계산이 중복된다.

```java
int binomial(int n, int k) {
  if (n == k || k == 0) {
    return 1;
  }
  else if (binom[n][k] > -1) { // 배열이 -1로 초기화 되어 있다고 가정
    return binom[n][k];
  }
  else {
    binom[n][k] = binomial(n - 1, k) + binomial(n - 1, k - 1);
    return binom[n][k];
  }
}
```

- Memoization 방식 계산

```java
int binomial(int n, int k) {
  for (int i = 0; i <= n; i++) {
    for (int j = 0; j <= k && j <= i; j++) {
      if (k == 0 || n == k) {
        binom[i][j] = 1;
      }
      else {
        binom[i][j] = binom[i - 1][j - 1] + binom[i - 1][j];
      }
    }
  }
  return binom[n][k];
}
```

- bottom-up 방식
- Dynamic Programming

### Memoization vs Dynamic Programming

- 순환식의 값을 계산하는 기법이다.
- 둘 다 동적계획법의 일종으로 보기도 한다.
- Memoization은 top-down 방식이며, 실제로 필요한 subproblem만을 푼다.
- Dynamic Programming은 bottom-up 방식이며, recursion에 수반되는 overhead가 없다.
