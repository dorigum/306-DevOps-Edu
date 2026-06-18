# Career Web Service (8083)

## 프로젝트 개요
Career Service는 채용 희망자 관점의 AI 커리어 어드바이저 서비스입니다.
Candidate Service와 Job Service의 데이터를 수집하여 AI를 활용한 채용 추천 및 이력서 생성을 수행합니다.

## 주요 기능
- 내 프로필 기반 채용 공고 추천
- 추천 공고 비교 분석
- 지원 회사 맞춤형 이력서 생성

## 기술 스택
- Spring Boot
- Spring AI
- OpenAI / Gemini / Ollama
- REST Client

## 프로젝트 구조
- CareerAdvisorController
- CareerAdvisorService
- CandidateClient
- JobClient
- Prompt Template
- DTO 모델

## Directory Structure
```text
src/main/java/com/jadecross/career
├── controller
├── service
├── client
├── dto
└── config

src/main/resources/prompt-templates
├── evaluate-jobs
├── compare-jobs
└── generate-resume
```

## API
- GET /api/career-advisor/find-jobs
- GET /api/career-advisor/compare-jobs
- GET /api/career-advisor/generate-resume

## AI Prompt 구조
prompt-templates/
- evaluate-jobs
- compare-jobs
- generate-resume
  각 기능별 system.txt / user.txt 사용

## Service Flow
User
→ Career Service
→ Candidate Service
→ Job Service
→ LLM

## DB
별도 DB 없음

## Static Pages
- index.html
- career-advisor-candidate.html
- career-advisor-recruiter.html


## 역할
포트 8083에서 동작하는 사용자 진입점(Web Front API)입니다.


## 중요 사항
- Candidate Service(8081) 연동
- Job Service(8082) 연동
- AI Prompt 기반 추천/비교/이력서 생성
- OpenAI, Gemini, Ollama 프로필 전환 가능


## 실행
8083 포트
application-openai.properties
application-google.properties
application-ollama.properties 지원