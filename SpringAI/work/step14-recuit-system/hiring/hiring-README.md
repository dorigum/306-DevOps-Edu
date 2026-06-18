# Hiring Service (8084)

## 프로젝트 개요
Hiring Service는 채용 담당자 관점의 AI 채용 평가 서비스입니다.
지원자가 특정 채용 공고에 지원하면 AI가 지원자와 채용 공고를 분석하여 적합도를 평가합니다.

## 주요 기능
- 입사지원 접수
- AI 적합도 평가
- 평가 결과 저장

## 기술 스택
- Spring Boot
- Spring AI
- Spring Events
- @Async
- JPA

## 프로젝트 구조
- JobApplicationController
- JobApplicationService
- JobApplicationEvaluationListener
- HiringAdvisorClient
- JobClient

## API
- POST /api/job-applications
- GET /api/job-applications?candidateId=
- GET /api/job-applications?jobId=

## Entity
JobApplication

## DB
- JOB_APPLICATION

## AI 동작 방식
JobApplicationSubmittedEvent 발행
↓
@TransactionalEventListener
↓
@Async 비동기 평가
↓
AI 결과 저장

## 호출 흐름
Hiring → Job
Hiring → AI
결과 저장


## 역할
포트 8084에서 동작하며 채용 담당자를 위한 AI 평가 서비스를 제공합니다.

## 중요 사항
- Job Service(8082) 연동
- Spring Event 기반 비동기 처리
- publishEvent() → @TransactionalEventListener 흐름 사용
- AI가 지원자 적합도 평가 후 DB 저장
- JOB_APPLICATION 데이터 관리
