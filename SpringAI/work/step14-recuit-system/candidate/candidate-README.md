# Candidate Service

## 프로젝트 개요
Candidate Service는 채용 희망자의 기본 프로필과 경력 정보를 관리하는 마이크로서비스입니다.
AI 채용 시스템에서 지원자(Candidate)의 정보를 제공하는 핵심 데이터 서비스 역할을 수행합니다.

## 주요 기능
- 지원자 정보 관리(Candidate)
- 경력 정보 관리(Work Experience)
- 지원자 상세 프로필 조회
- REST API 기반 데이터 제공

## 기술 스택
- Spring Boot
- Spring Data JPA
- H2 Database
- REST API

## 프로젝트 구조
- CandidateController : REST API 제공
- CandidateService : 비즈니스 로직 처리
- CandidateRepository : 데이터 접근 계층
- Candidate : 지원자 엔티티
- WorkExperience : 경력 엔티티

## Controller API
- GET /api/candidates/{candidateId}

## Entity 관계도
Candidate (1) ─── (N) WorkExperience

## DB
- CANDIDATE
- WORK_EXPERIENCE

## 역할
포트 8081에서 동작하며 Career Service와 Hiring Service가 지원자 정보를 조회할 수 있도록 제공합니다.

## 서비스 호출 흐름
Career → Candidate
Hiring → Candidate

## 중요 사항
- Candidate와 WorkExperience 데이터를 관리
- AI 추천 및 평가를 위한 원천 데이터 제공
- 독립 DB를 사용하는 MSA 구조
