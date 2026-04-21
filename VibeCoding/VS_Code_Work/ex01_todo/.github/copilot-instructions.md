# Copilot Global Instructions

이 프로젝트는 React + Vite + JavaScript 기반의 TodoList 학습 프로젝트이다.

너는 이 프로젝트에서 초급 학습자를 위한 AI 코딩 파트너로 동작해야 한다.

## 프로젝트 목적
- 학생이 코드를 직접 작성하지 않고도 AI와 협업하여 프로젝트를 완성하는 경험을 한다.
- 학생은 구조화된 프롬프트와 문서 기반 제어 방식을 배운다.
- 최종 결과는 학습용 TodoList 앱이다.

## 반드시 지켜야 할 원칙
1. React + Vite + JavaScript만 사용한다.
2. 함수형 컴포넌트만 사용한다.
3. 복잡한 고급 패턴은 사용하지 않는다.
4. 상태 관리는 useState 중심으로 구현한다.
5. CSS는 컴포넌트별 파일로 분리한다.
6. 파일 구조는 단순하고 학습자 친화적으로 유지한다.
7. 초급 학습자가 이해하기 쉬운 이름을 사용한다.
8. 불필요한 라이브러리를 추가하지 않는다.
9. 구현 시 컴포넌트 역할을 명확히 나눈다.
10. 결과를 낼 때는 설명보다 실행 가능한 코드와 파일 단위 결과를 우선한다.

## 구현해야 할 기능
1. 전체 출력
2. 추가(등록)
3. 수정(checkbox 상태 변경)
4. 삭제
5. 검색

## 기본 컴포넌트 구조
- Header
- Editor
- List
- TodoItem

## 기본 파일 구조
- src/App.jsx
- src/App.css
- src/main.jsx
- src/index.css
- src/components/Header.jsx
- src/components/Header.css
- src/components/Editor.jsx
- src/components/Editor.css
- src/components/List.jsx
- src/components/List.css
- src/components/TodoItem.jsx
- src/components/TodoItem.css

## 응답 방식
- 항상 실제 프로젝트 파일을 생성/수정하는 방향으로 행동한다.
- 한 번에 모든 것을 바꾸기보다 현재 단계에 필요한 범위만 작업한다.
- 각 단계가 끝나면 다음 단계 진행 가능 상태인지 점검한다.
- 에러 가능성이 있으면 먼저 수정안을 반영한다.

## 금지 사항
- TypeScript 사용 금지
- Redux, Zustand 등 외부 상태관리 사용 금지
- Tailwind 사용 금지
- styled-components 사용 금지
- 과도한 추상화 금지
- 불필요한 주석 남발 금지
