### JWT기반으로 인증 후 로그인하여 2번의 설문조사를 한 뒤, 각 페이지에서 입력한 데이터를 DB에 저장하는 작업을 구현한 것입니다.

(주)럽디에서 6주간 근무하면서 Node.js 기반의 Express.js로 과제 프로젝트를 시작했지만, 
JWT 기반의 로그인/Sequelize 연동 따로만 개발하고 마무리하지 못했었습니다.

이 과제를 Spring Boot로 마무리하고자 만든 프로젝트 저장소입니다.

Edu(교육 부분 설문조사) 주제는 보안상 다른 주제로 변경했습니다. -> 관리자가 회원 관리를 할 수 있도록 구현

----

개발환경
=========
- IntelliJ IDEA
- JDK 11
- Spring Boot 2.6.11
- Spring Security (JWT)
- MySQL 8.0.30

## Swagger페이지
![Swagger페이지](https://user-images.githubusercontent.com/77195486/192661299-f447b1a5-ad6b-4b91-8bd8-20f317579a6d.png)

## 로그인 성공 시 토큰 발급
![로그인 성공 시 토큰 발급](https://user-images.githubusercontent.com/77195486/192661377-d38c8888-aad5-4862-bbdb-38b62435b6a0.PNG)

## 앰배서더 신청서 등록 (Bearer가 아닌 커스텀 방식의 토큰 헤더)
![신청서 등록](https://user-images.githubusercontent.com/77195486/192661435-e42c6f16-88aa-4eea-84a8-8365f97eea21.png)

### 개발 기간
22.09.01 ~ 22.09.24 (약 3주)
