# 4일차 학습일지

## 깃 관리

### .gitignore란?
   - 프로젝트 작업 시 로컬 환경의 정보나 빌드 정보 등 원격 저장소에 저장되지 말아야할 파일들이 원격 저장소로 실수로 업로드 되지 않도록 관리해주는 파일
   - 정의한 정보들에 해당하는 파일들에 대해 git track하지 않도록 설정하는 역활을 한다.

### .gitignore 파일 만들기
   1. 프로젝트 안에서 항상 최상위 Directory에 존재해야한다.
   2. 예시
   ```
   ### IntelliJ IDEA ###
  .idea
  *.iws
  *.iml
  *.ipr
  out/
  !**/src/main/**/out/
  !**/src/test/**/out/
  ```
### .gitignore 적용하기
  1. .gitignore파일을 생성 후 저장소에 함께 push하게 되면 바로 적용된다.
  2. 기존 프로젝트에 적용이 되지 않을 경우 directory이름이나 .gitignore파일 위치를 다시 확인해 보자.

## 자바의 타입

### primitive type란?
   > 원시 자료형은 컴퓨터 과학에서 프로그래밍 언어가 제공하는 자료형 중 하나다. 원시형은 또한 내장형이나 기본형으로도 불린다.

### java언어의 primitive type

   |키워드|크기|설명|
   |-----|----|----|
   |boolean|1bit|`ture`혹은 `false`로 표현되는 논리형|
   |char|2byte|문자형|
   |byte|1byte|-128~127의 범위를 가지는 정수형|
   |short|2byte|-32768~32767의 범위를 가지는 정수형|
   |int|4byte|-2147483648~2147483647의 범위를 가지는 정수형|
   |long|8byte|int보다 더 넓은 범위를 가지는 정수형|
   |float|4byte|실수형|
   |double|8byte|float보다 두배의 정홛성을 가지는 실수형|
   
   - 예전에는 컴퓨터 메모리 성능이 안좋아서(작아서)
## Call by Value, Call by Reference

## 함수, 객체, 메모리 콜스택

## 재귀

## 클래스와 객체

## 자료구조의 
