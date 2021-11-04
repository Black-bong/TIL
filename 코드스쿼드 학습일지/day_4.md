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

## Call by Value, Call by Reference

## 함수, 객체, 메모리 콜스택

## 재귀

## 클래스와 객체

## 자료구조의 
