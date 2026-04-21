# Architecture Instructions

## 아키텍처 원칙
- App.jsx가 최상위 컨테이너 역할을 한다.
- todos 상태는 App.jsx에서 관리한다.
- 상태 변경 함수는 App.jsx에서 정의하고 하위 컴포넌트로 props 전달한다.

## 역할 분리
- Header: 날짜와 상단 제목 표시
- Editor: 입력 및 추가
- List: 검색창, 통계, 목록 렌더링
- TodoItem: 개별 Todo 항목 렌더링

## 데이터 구조
각 todo는 아래 구조를 가진다.

```js
{
  id: number,
  content: string,
  isDone: boolean,
  createdDate: number
}
```

## 상태 설계
- todos: 전체 Todo 배열
- 검색 상태는 List 내부에서 관리 가능
- 입력 상태는 Editor 내부에서 관리 가능

## 이벤트 흐름
- 추가: Editor -> App onCreate
- 수정: TodoItem -> App onUpdate
- 삭제: TodoItem -> App onDelete
- 검색: List 내부 입력값으로 필터링

## 구현 원칙
- 데이터 흐름은 단방향으로 유지한다.
- 지나친 분리보다 학습자 이해를 우선한다.
