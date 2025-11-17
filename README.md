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

