# @MappedSuperclass

- 공통 매핑 정보가 필요 할 때 사용

## 공통 정보를 가지는 추상 클래스 생성
```java
@MappedSuperclass
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;
}
```

## 사용
- 기존 객체가 상속 받는 것 처럼 상속 받아 사용하면 된다.

## 주의점
- 상속 관계 매핑은 아니다.
- 엔티티도 아니며, 그렇기 때문에 테이블과 매핑되지 않는다.
- 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공한다.
- 조회, 검색이 불가능하다.
- 직접 생성해서 사용할 일이 없기 때문에 추상 클래스로 생성해서 사용하는 것이 좋다.

## 정리
- 테이블과 관계 없고, 단순히 엔티티가 공통으로 사용하는 매핑 정보를 모으는 역할
- 주로 등록, 수정 일자와 등록, 수정한 사람 같은 전체 엔티티에서 공통으로 적용하는 정보를 모을 때 사용한다.