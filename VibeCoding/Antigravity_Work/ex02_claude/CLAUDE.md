# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 개발 명령어

### 프론트엔드 (루트 디렉토리)
```bash
npm run dev      # Vite 개발 서버 실행 (localhost:5173)
npm run build    # 프로덕션 빌드 → dist/
npm run preview  # 빌드 결과 미리보기
```

### 백엔드 (backend/ 디렉토리)
```bash
cd backend
npm run dev      # node --watch로 자동 재시작
npm start        # 일반 실행
```

## 아키텍처

### 전체 구조
- **로컬 개발**: Vite dev server(5173) + 로컬 Express(8080) + EC2 MySQL
- **프로덕션**: EC2 Nginx → React dist(정적) + Express(8080, PM2) + MySQL(localhost)

### API 프록시
`vite.config.js`에서 `/boards` 요청을 `http://localhost:8080`으로 프록시.
프론트엔드 `boardApi.js`는 `/boards` 경로를 사용하며 baseURL 없이 동작.

### 백엔드 레이어 구조
```
routes/boardRoutes.js       → URL 매핑
controllers/boardController.js → 요청/응답 처리 + console.error 로깅
services/boardService.js    → MySQL 쿼리 (테이블명: boards)
db/connection.js            → mysql2/promise 커넥션 풀
```

### 프론트엔드 구조
- `pages/` — 라우트별 페이지 (BoardListPage, BoardDetailPage, BoardFormPage)
- `components/` — Layout(공통 헤더/푸터), Toast(알림)
- `hooks/useToast.js` — 토스트 상태 관리
- `api/boardApi.js` — CRUD 5개 함수, `axiosInstance` 기반

### 라우팅
| 경로 | 컴포넌트 |
|------|----------|
| `/` | BoardListPage (목록 + 검색) |
| `/boards/:id` | BoardDetailPage |
| `/create` | BoardFormPage (신규) |
| `/edit/:id` | BoardFormPage (수정) |

## 환경 변수

### backend/.env
```
DB_HOST=     # 로컬 개발 시 EC2 IP/도메인, EC2 실행 시 localhost
DB_PORT=3306
DB_USER=
DB_PASSWORD=
DB_NAME=
PORT=8080
```

### .env (프론트엔드 루트)
```
VITE_API_URL=   # 로컬 개발 시 비워두면 Vite proxy 사용
                # EC2 배포 시 /api 또는 백엔드 URL
```

## 로컬 개발 시 MySQL 접속 조건
백엔드를 로컬에서 실행할 경우 EC2 보안 그룹에 MySQL 인바운드 규칙(포트 3306, 소스: 내 IP)이 필요.
EC2에서 백엔드를 직접 실행하면 `DB_HOST=localhost`로 규칙 불필요.

## 디자인 시스템
`src/index.css`에 CSS 변수로 정의된 Vibe Coding 테마 사용.
주요 변수: `--bg-color`, `--glass-bg`, `--accent-color`, `--glass-border`, `--transition-smooth`.
카드 스타일은 `.glass-card` 클래스, 버튼은 `.btn .btn-primary/.btn-secondary/.btn-danger` 조합.
