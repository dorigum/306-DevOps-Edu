# Spring AI Prompt Templates 분석 문서

## 프로젝트 개요

이 프로젝트는 Spring AI의 Prompt Template 기능을 활용하여 **AI 기반 커리어 컨설턴트 서비스**를 구현하기 위한 프롬프트 모음입니다.

사용자의 프로필(Candidate)과 채용 공고(Job)를 입력받아 다음 기능을 제공합니다.

1. 채용공고 적합도 평가 (evaluate-jobs)
2. 여러 채용공고 비교 분석 (compare-jobs)
3. 맞춤형 이력서 생성 (generate-resume)

---

## 전체 구조

```text
prompt-templates
├─ compare-jobs
├─ evaluate-jobs
└─ generate-resume
```

각 폴더는 하나의 AI Use Case(업무)를 의미합니다.

각 업무는 다음 두 개의 Prompt로 구성됩니다.

- system.txt : AI의 역할(Role)과 행동 규칙 정의
- user.txt : 사용자 입력 데이터와 실제 요청 정의

---

## 1. compare-jobs

### 목적

여러 채용공고 중 어떤 직무가 가장 적합한지 비교 분석합니다.

### 입력

- Candidate
- Jobs (여러 개)

### AI 역할

시니어 전략적 커리어 컨설턴트

### 주요 분석 항목

- 기술 적합성
  - EXCELLENT
  - GOOD
  - BAD

- 기술 통찰력
- 근무지 적합성
- 연봉 분석
- 최종 추천

### 결과

가장 적합한 채용공고 1개를 추천하고 이유를 설명합니다.

### 활용 예시

- 이직 추천 서비스
- 채용 플랫폼
- AI 커리어 코치

---

## 2. evaluate-jobs

### 목적

후보자와 채용공고 간 적합도를 점수화합니다.

### 입력

- Candidate
- Jobs

### AI 역할

시니어 전략 커리어 컨설턴트

### 주요 평가 기준

#### 기술 스택

가장 높은 가중치

예)
- Java
- Spring Boot
- AWS
- Docker

#### 경력 수준

예)

- Junior
- Senior
- Lead

#### 근무 조건

- 지역
- 원격근무 여부

### 출력

- 적합도 점수 (0~100)
- 적합 사유

### 활용 예시

- 채용 추천 시스템
- AI Job Matching
- 취업 플랫폼

---

## 3. generate-resume

### 목적

특정 채용공고에 최적화된 맞춤형 이력서를 생성합니다.

### 입력

- Candidate
- Target Job

### AI 역할

기술 분야 경력 전환 전문 이력서 작성자

### 특징

#### 허위 정보 금지

없는 경험 작성 금지

#### 성과 중심

책임보다 성과를 강조

예)

❌ 프로젝트 개발 수행

✅ API 응답속도 30% 개선

#### ATS 최적화

채용공고의 기술 키워드를 활용

### 생성 구조

1. 전문가 요약
2. 기술 스킬
3. 경력
4. 추가 정보

### 출력 형식

Markdown

### 활용 예시

- AI Resume Builder
- 커리어 컨설팅
- 자기소개서 지원 서비스

---

## Spring AI 연동 방식

예시 코드

```java
this.chatClient.prompt()
    .system(systemPrompt)
    .user(userPrompt)
    .call();
```

### system.txt

AI의 역할 정의

예)

- 커리어 컨설턴트
- 이력서 전문가

### user.txt

실제 데이터 전달

예)

```text
Candidate:
{candidate}

Jobs:
{jobs}
```

---

## 서비스 아키텍처

```text
사용자
   │
   ▼
Spring Boot
   │
   ▼
Spring AI
   │
   ├─ evaluate-jobs
   ├─ compare-jobs
   └─ generate-resume
   │
   ▼
OpenAI
   │
   ▼
분석 결과 반환
```

---

## 결론

이 Prompt Template 세트는 단순 챗봇이 아니라

"AI 기반 취업 · 이직 · 커리어 컨설팅 플랫폼"

구현을 목적으로 설계된 프롬프트 집합입니다.

핵심 기능은 다음 세 가지입니다.

- Job Matching
- Job Comparison
- Resume Generation

즉,

"내 경력에 맞는 채용공고 찾기 → 여러 공고 비교 → 맞춤형 이력서 생성"

까지 하나의 흐름으로 지원하는 AI Career Assistant 프로젝트입니다.
