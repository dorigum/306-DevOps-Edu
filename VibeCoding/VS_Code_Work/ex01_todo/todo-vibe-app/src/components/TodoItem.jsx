import './TodoItem.css';

// TodoItem 컴포넌트: 개별 할 일 항목을 표시하고 수정/삭제를 처리하는 역할
function TodoItem({ todo, onUpdate, onDelete }) {
  // 타임스탬프를 읽기 쉬운 날짜 형식으로 변환
  const formatDate = (timestamp) => {
    return new Date(timestamp).toLocaleDateString('ko-KR');
  };

  return (
    <div className="todo-item">
      {/* 완료 체크박스 */}
      <input
        type="checkbox"
        checked={todo.isDone}
        onChange={() => onUpdate(todo.id)}
        className="todo-checkbox"
      />
      {/* 할 일 내용 (완료 시 취소선 적용) */}
      <span className={`todo-content ${todo.isDone ? 'done' : ''}`}>
        {todo.content}
      </span>
      {/* 생성 날짜 */}
      <span className="todo-date">{formatDate(todo.createdDate)}</span>
      {/* 삭제 버튼 */}
      <button
        onClick={() => onDelete(todo.id)}
        className="todo-delete-btn"
      >
        삭제
      </button>
    </div>
  );
}

export default TodoItem;