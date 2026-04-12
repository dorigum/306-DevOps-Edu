import React from "react";
import { useState } from "react";
import "./List.css";
import TodoItem from "./TodoItem";
import { useMemo } from "react";
import { use } from "react";
import { TodoContext } from "../App";

const List = () => {
  const { todos } = use(TodoContext);
  const [search, setSearch] = useState("");

  const getFilterData = () => {
    if (search === "") return todos;

    return todos.filter((todo) =>
      todo.content.toLowerCase().includes(search.toLowerCase()),
    );
  };

  // 교안자료: 08_TodoList_Project_CRUD(p22)
  // ❌코드 문제점: getAnalyzedData() 함수가 불필요하게 리렌더링이 될 때마다 같은 작업을 반복
  // 👉useMemo(), memo() 를 사용해 최적화
  // ----------------------------------------------------------------
  // 컴포넌트가 리렌더링 될 때마다 호출
  const filteredTodos = getFilterData();

  const { totalCount, doneCount, notDoneCount } = useMemo(() => {
    console.log("useMemo Call");

    const totalCount = todos.length;
    const doneCount = todos.filter((todo) => todo.isDone).length;
    const notDoneCount = totalCount - doneCount;

    return { totalCount, doneCount, notDoneCount };
  }, [todos]);

  return (
    <div className="List">
      <h4>Todo List🌱</h4>
      <div>
        <div>total: {totalCount}</div>
        <div>doneCount: {doneCount}</div>
        <div>notDoneCount: {notDoneCount}</div>
      </div>

      <input
        type="text"
        placeholder="검색어를 입력해주세요."
        value={search}
        onChange={onChangeSearch}
      />

      <div className="todos_wrapper">
        {filteredTodos.map((todo) => {
          return <TodoItem key={todo.id} {...todo} />;
        })}
      </div>
    </div>
  );
};

export default List;
