import React from "react";
import { useState } from "react";
import "./List.css";
import TodoItem from "./TodoItem";

function List({ todos, onUpdate, onDelete }) {
  console.log(todos);

  const [search, setSearch] = useState("");

  // 검색어를 입력했을 때, 검색어를 포함한 todo 정보 조회
  const getFilterData = () => {
    if (search === "") return todos;
    else
      return todos.filter((todo) =>
        todo.content.toLowerCase().includes(search.toLowerCase()),
      );
  };

  // 컴포넌트가 리렌더링 될 때마다 호출
  const filteredTodos = getFilterData();

  return (
    <div className="List">
      <h4>Todo List🌱</h4>

      <input
        type="text"
        placeholder="검색어를 입력해주세요."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <div className="todos_wrapper">
        {filteredTodos.map((todo) => (
          <TodoItem
            key={todo.id}
            {...todo}
            onDelete={onDelete}
            onUpdate={onUpdate}
          />
        ))}
      </div>
    </div>
  );
}

export default List;
