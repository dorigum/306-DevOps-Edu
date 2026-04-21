import { useState } from 'react';
import TodoItem from './TodoItem';
import './List.css';

// List 컴포넌트: 검색 필터링과 TodoItem 목록을 렌더링하는 역할
function List({ todos, onUpdate, onDelete }) {
  const [search, setSearch] = useState('');

  // 검색어에 따라 필터링된 Todo 목록 생성
  const filteredTodos = todos.filter((todo) =>
    todo.content.toLowerCase().includes(search.toLowerCase())
  );

  // 완료된 항목의 개수 계산
  const doneCount = todos.filter((todo) => todo.isDone).length;

  return (
    <div className="list">
      <div className="list-header">
        <h2>Todo List</h2>
      </div>

      {/* 검색창 */}
      <input
        type="text"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        placeholder="검색..."
        className="list-search"
      />

      {/* 통계 정보 */}
      <div className="list-stats">
        <span>완료: {doneCount} / 전체: {todos.length}</span>
      </div>

      {/* 필터링된 목록 출력 */}
      <div className="list-items">
        {filteredTodos.length === 0 ? (
          <p className="list-empty">할 일이 없습니다.</p>
        ) : (
          filteredTodos.map((todo) => (
            <TodoItem
              key={todo.id}
              todo={todo}
              onUpdate={onUpdate}
              onDelete={onDelete}
            />
          ))
        )}
      </div>
    </div>
  );
}

export default List;