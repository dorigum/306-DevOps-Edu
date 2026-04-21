# Prompt 03 - Implement Core Features

`.github` 폴더의 모든 지침을 참고한 뒤 아래 기능을 구현해줘.

## 구현할 기능
1. 전체 출력
2. 추가(등록)
3. 수정(checkbox 상태 변경)
4. 삭제
5. 검색

## 세부 요구사항
- App.jsx에서 todos 상태를 useState로 관리한다.
- 초기 더미 데이터 3개를 만든다.
- onCreate, onUpdate, onDelete 함수를 구현한다.
- Editor는 입력 후 추가 가능해야 한다.
- 빈 문자열은 추가되지 않아야 한다.
- Enter 키로도 추가 가능해야 한다.
- List는 검색 input을 통해 content 기준 필터링해야 한다.
- TodoItem은 checkbox 토글과 삭제 버튼이 동작해야 한다.
- createdDate는 보기 좋은 날짜로 출력한다.
- 완료 항목은 취소선 처리한다.

## 중요
- 초급 학습자 기준에서 읽기 쉬운 구조로 구현한다.
- 불필요한 라이브러리는 추가하지 않는다.
- 실제 실행 가능한 코드로 각 파일을 완성한다.
