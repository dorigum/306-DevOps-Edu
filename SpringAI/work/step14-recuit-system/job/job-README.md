# Job Service (8082)

## 프로젝트 개요
Job Service는 채용 공고(Job)와 요구 기술(Job Skill)을 관리하는 서비스입니다.
채용 공고 데이터를 제공하며 AI 추천 및 적합도 분석의 기준 정보를 제공합니다.

## 주요 기능
- 채용 공고 관리
- 직무 요구 기술 관리
- 공고 상세 조회
- REST API 제공

## 기술 스택
- Spring Boot
- Spring Data JPA
- H2 Database

## 프로젝트 구조
- JobController
- JobService
- JobRepository
- Job
- JobSkill

## Controller API
- GET /api/jobs/{id}
- GET /api/jobs?ids=1,2
- GET /api/jobs?skills=java,spring

## Entity 관계도
Job (1) ─── (N) JobSkill

## DB
- JOB
- JOB_SKILL

## 역할
포트 8082에서 동작하며 Career Service와 Hiring Service에 채용 공고 데이터를 제공합니다.

## 서비스 호출 흐름
Career → Job
Hiring → Job

## Role
Career 서비스의 추천 대상 데이터 제공
Hiring 서비스의 평가 대상 공고 정보 제공

## 중요 사항
- 채용 공고 및 요구 기술 관리
- AI 기반 추천 및 매칭의 기준 데이터
- Candidate 데이터와 결합하여 적합도 분석 수행
