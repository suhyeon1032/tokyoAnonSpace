# tokyoAnonSpace
신한 GYC 7기 Tokyo반 チーム◦ｾﾖﾝ 프로젝트

## 🟡 프로젝트 선정 이유 및 배경

이 프로젝트는 웹 서비스에서 가장 기본이 되는 **게시판 기능**을 Spring Boot 기반으로 직접 구현하며  
백엔드·프론트엔드 전반의 개발 흐름을 익히기 위해 선정하였습니다.

### 주요 목적
- Spring Boot 3.x 기반 MVC 구조 학습
- JPA를 활용한 ORM 데이터 처리 경험
- Oracle DB 연동 및 실제 서비스와 유사한 환경 구성
- 게시판 CRUD 기능 전반 구현
- REST API 설계 및 적용
- Thymeleaf 기반 서버 사이드 렌더링 화면 구성
- Bootstrap 5를 통한 UI/UX 개선

### 기능 선정 배경
- 공지 게시판 · 자유 게시판 기본 구조 구성
- 댓글, 추천 기능 등 커뮤니티 요소 구현
- 익명 작성 및 비밀번호 기반 삭제 처리 적용
- 캘린더 기능을 통해 날짜 확인 기능 제공

본 프로젝트를 통해 **웹 애플리케이션의 전체 흐름을 이해하고**,  
실제 서비스와 유사한 구조를 설계·구현하는 데 중점을 두었습니다.

### 프로젝트 구조
📂 Project Structure
```tokyoAnonSpace/
├── api/ # DTO 요청/응답 관련 클래스
│ ├── FreeRequest.java
│ ├── NoticeRequest.java
│ └── NoticeResponse.java
│
├── controller/ # 웹 요청을 처리하는 컨트롤러 계층
│ ├── CalendarController.java # 캘린더 API 컨트롤러
│ ├── CalendarPageController.java # 캘린더 페이지 렌더링
│ ├── FreeController.java # 자유게시판 CRUD 처리
│ ├── FreeCommentController.java # 자유게시판 댓글 처리
│ ├── NoticeController.java # 공지게시판 CRUD 처리
│ ├── NoticeCommentController.java # 공지게시판 댓글 처리
│ └── MainController.java # 메인 페이지 매핑
│
├── dto/ # 데이터 전달용 객체 (Data Transfer Object)
│ └── CalendarDto.java
│
├── entity/ # 데이터베이스와 매핑되는 JPA 엔티티
│ ├── Calendar.java
│ ├── Free.java
│ ├── FreeComment.java
│ ├── Notice.java
│ └── NoticeComment.java
│
├── repository/ # DB 접근 계층 (Spring Data JPA Repository)
│ ├── CalendarRepository.java
│ ├── FreeRepository.java
│ ├── FreeCommentRepository.java
│ ├── NoticeRepository.java
│ └── NoticeCommentRepository.java
│
├── service/ # 비즈니스 로직 계층
│ ├── CalendarService.java
│ ├── FreeService.java
│ ├── FreeCommentService.java
│ ├── NoticeService.java
│ ├── NoticeCommentService.java
│ └── TokyoAnonSpaceApplication.java # Spring Boot 실행 메인 클래스
│
├── resources/ # 정적 리소스 및 설정 파일
│ ├── static/ # 정적 파일 (HTML, JS, CSS)
│ │ └── calendar.html
│ ├── templates/ # Thymeleaf 템플릿 (선택 사용)
│ └── application.properties # DB 및 서버 설정
│
├── test/ # 단위 및 통합 테스트 코드
│
├── .gitignore # Git 버전관리 제외 파일 목록
├── .gitattributes # Git 속성 관리 설정
├── build.gradle # Gradle 빌드 설정 파일
└── gradlew # Gradle 실행 스크립트
```
### 📅 CALENDAR

| 컬럼명     | 타입        | 제약조건                 | 설명             |
|-----------|-------------|--------------------------|------------------|
| id        | BIGINT      | PK, AUTO_INCREMENT       | 일정 고유 ID     |
| CAL_DATE  | DATE        | NOT NULL                 | 날짜             |
| content   | VARCHAR/CLOB| NOT NULL                 | 일정 내용        |

### 🗣 FREE (자유 게시판)

| 컬럼명      | 타입            | 제약조건                     | 설명                   |
|-------------|------------------|------------------------------|------------------------|
| id          | BIGINT           | PK, AUTO_INCREMENT           | 게시글 ID              |
| nickname    | VARCHAR(50)      | NOT NULL                     | 작성자 닉네임          |
| password    | VARCHAR          | NOT NULL                     | 삭제/수정용 비밀번호   |
| title       | VARCHAR(200)     | NOT NULL                     | 게시글 제목            |
| content     | CLOB             | NOT NULL                     | 게시글 내용            |
| created_at  | TIMESTAMP        | DEFAULT now(), NOT NULL      | 작성일                 |
| updated_at  | TIMESTAMP        | DEFAULT now()                | 수정일                 |
| likes       | INT              | DEFAULT 0                    | 추천 수                |
| dislikes    | INT              | DEFAULT 0                    | 비추천 수              |

### 💬 FREE_COMMENT (자유 게시판 댓글)

| 컬럼명      | 타입            | 제약조건                       | 설명                       |
|-------------|------------------|--------------------------------|----------------------------|
| id          | BIGINT           | PK, AUTO_INCREMENT             | 댓글 ID                    |
| board_id    | BIGINT           | FK (FREE.id)                   | 연결된 게시글              |
| nickname    | VARCHAR(50)      | NOT NULL                       | 댓글 작성자                |
| password    | VARCHAR          | NOT NULL                       | 댓글 삭제용 비밀번호       |
| content     | CLOB             | NOT NULL                       | 댓글 내용                  |

### 📢 NOTICE (공지 게시판)

| 컬럼명        | 타입            | 제약조건                     | 설명                     |
|---------------|------------------|------------------------------|--------------------------|
| id            | BIGINT           | PK, AUTO_INCREMENT           | 공지글 ID                |
| nickname      | VARCHAR(50)      | NOT NULL                     | 작성자 닉네임            |
| password      | VARCHAR          | NOT NULL                     | 비밀번호(삭제/수정용)    |
| title         | VARCHAR(200)     | NOT NULL                     | 공지 제목                |
| content       | CLOB             | NOT NULL                     | 공지 내용                |
| created_at    | TIMESTAMP        | DEFAULT now(), NOT NULL      | 작성일                   |
| updated_at    | TIMESTAMP        | DEFAULT now()                | 수정일                   |

