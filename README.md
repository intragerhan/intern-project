### JWT기반으로 인증 후 로그인하여 2번의 설문조사를 한 뒤, 각 페이지에서 입력한 데이터를 DB에 저장하는 작업을 구현한 것입니다.

럽디(주)에서 학교 연계 겨울방학 인턴활동으로 6주간 Express.js로 서버 개발하는 과제를 했었고, JWT 기반의 인가 인증/Sequelize 연동을 통해 개발했습니다. 설문조사와 앰배서더 각각의 신청 서버 API를 개발하는 작업이었습니다.

이 과제를 Spring으로 마무리하고자 만든 프로젝트 저장소입니다.

Edu(교육 부분 설문조사) 주제는 보안상 다른 주제로 변경했습니다. -> 관리자가 회원 관리를 할 수 있도록 구현

----

개발환경
=========
- IntelliJ IDEA
- JDK 11
- Spring Boot 2.6.11
  - Boot starter 2.6.11
    - Security (JWT)
    - Validation
    - Data JPA
- MySQL 8.0.30

## ERD
![앰배서더](https://user-images.githubusercontent.com/77195486/192688389-c54d4133-5315-40f3-9c58-1afcd43c124f.png)


## Swagger페이지
![Swagger페이지](https://user-images.githubusercontent.com/77195486/192661299-f447b1a5-ad6b-4b91-8bd8-20f317579a6d.png)

## 로그인 성공 시 토큰 발급
![로그인 성공 시 토큰 발급](https://user-images.githubusercontent.com/77195486/192661377-d38c8888-aad5-4862-bbdb-38b62435b6a0.PNG)

## 앰배서더 신청서 등록 (Bearer가 아닌 커스텀 방식의 토큰 헤더)
![신청서 등록](https://user-images.githubusercontent.com/77195486/192661435-e42c6f16-88aa-4eea-84a8-8365f97eea21.png)

### 개발 기간
학교연계 인턴 - 21.12.29 ~ 22.02.12 (6주)

리뉴얼 - 22.09.01 ~ 22.09.24 (약 3주)
