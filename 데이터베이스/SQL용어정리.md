# SQL 용어 정리

1. 모델링의 특징 3가지
    - 추상화
    - 단순화
    - 명확화
2. 속성이 가질 수 있는 범위를 뜻하는 용어
    - 도메인
3. DML, DCL, DDL, TCL 용어
    - DML - INSERT, UPDATE, DELETE, RENAME
    - DDL - CREATE, ALTER, DROP, TRUNCATE
    - DCL - REVOKE, GRANT
    - TCL - COMMIT, ROLLBACK
4. SELETE 중복제거 키워드
    - DISTINCT
5. 트랜잭션의 특징
    - 원자성
    - 고립성
    - 일관성
    - 지속성
6. 뷰의 특징
    - 편리성
    - 독립성
    - 보안성
7. 순위 1 2 3 3 4 5
    - DENSE_RANK
8. 교집합을 구하는 집단 연산자
    - INTERSECT
9. SELECT NULLIF('A','A') FROM DUAL; 의 값
    - NULL
10. SELECT문의 논리적인 수행 순서
    - FROM - WHERE - GROUP BY - HAVING - SELECT - ORDER BY
11. 부모 삭제 시 같이 삭제되는 옵션
    - CASCADE
12. UNION과 UNION ALL의 차이점
    - UNION은 중복을 제거한다.
13. 데이터를 빠르게 조회하기 위해 미리 계산된 값이 저장되는 속성
    - 파생속성
14. 계층 쿼리에서 형제 노드 간의 정렬을 지정하는 구문
    - ORDER SIBLINGS BY
15. 의미상 더 이상 분리되지 않는 최소의 데이터 단위
    - 속성(ATTRIBUTE)
16. SELECT절에 오는 서브쿼리
    - 스칼라 서브쿼리
17. 엔터티 일반속성 간에 서로 종속되지 않는다.
    - 제 3정규형
18. 관계의 표기법에 표현되는 3가지 개념
    - 관계명
    - 관계차수
    - 관계선택사양
19. SELETE COALESCE(NULLIF('A','A'),'B') FROM DUAL;
    - B
20. SUMMER 테이블 이름을 FALL로 바꾸려면
    - RENAME SUMMER TO FALL;
21. 다양한 권한을 그룹으로 묶어 관리할 수 있는 것
    -  ROLE
22. 권한부여 명령어
    - GRANT
23. 카티션 곱을 생성하는 JOIN
    - CROSS JOIN
24. SELECT ABS(-3.8) FROM DUAL;
    - 3.8
25. 그룹함수 3가지
    - ROLLUP
    - CUBE
    - GROUPING SETS
26. SELECT FLOOR(12.5) FROM DUAL;
    - 12
27. INSERT와 UPDATE 동시 실행 하는 것
    - MERGE INTO
28. INSERT, UPDATE, DELETE를 잘못 사용했을때 되돌리는 명령어
    - ROLLBACK
29. 차집합을 구하는 집합 연산자
    - MINUS
30. GROUP BY ROLLUP(A, B, C);
    - GROUP BY(A, B, C) + GROUP BY(A, B) + GROUP BY(A) + 총 합계
31. GROUP BY ROLLUP(A, (B, C));
    - GROUP BY(A, B, C) + GROUP BY(A) + 총 합계
32. RANK() OVER
    - 1 2 3 3 3 6 7 8
33. DENS_RANK() OVER
    - 1 2 3 3 3 4 5 6
34. ROW_NUMBER() OVER
    - 1 2 3 4 5 6 7 8
35. CASE WHEN과 DECODE (if ~ else 구문)
    ```SQL
     CASE 컬럼명 WHEN '데이터1' THEN '변경할 데이터1' 
               WHEN '데이터2' THEN '변경할 데이터2' 
               ELSE '디폴트 데이터'
               END
    ```
    ```SQL
     CASE WHEN 컬럼명 = 데이터1 THEN '변경할 데이터1' 
          WHEN 컬럼명 = 데이터2 THEN '변경할 데이터2' 
          ELSE '디폴트 데이터'
          END
    ```      
    ```SQL
     DECODE(컬럼명, 데이터1, 변경할 데이터1, 데이터2, 변경할 데이터2, 디폴트 데이터)
    ```
36. GROUP BY CUBE(A)
    - GROUP BY(A) + 총 합계 → GROUP ROLLUP(A)와 동일
37. GROUP BY CUBE(A, B)
    - GROUP BY(A, B) + GROUP BY(A) + GROUP BY(B) + 총 합계
    - ROLLUP과 CUBE의 차이점은 () 안에 컬럼 순서가 ROLLUP은 결과에 영향이 있지만, CUBE는 영향이 없다.
38. NVL
    - NVL(컬럼명1, 컬럼명2) → 컬럼명1의 데이터값이 NULL이면 컬럼명2의 데이터 값으로 출력
39. NULLIF
    - NULLIF(컬럼명, 데이터) → 오른쪽에 있는 값과 왼쪽에 있는 값이 같으면 NULL 다르면 왼쪽값 출력
40. COALESCE
    - COALESCE(데이터1, 데이터2, 데이터3) → COALESCE가 포함하고 있는 데이터(데이터1,2,3) 중 인자값이 NULL이 아닌 최초의 값 출력
