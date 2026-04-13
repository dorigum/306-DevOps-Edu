import React from "react";
import { useState } from "react";
import "./List.css";
import TodoItem from "./TodoItem";
import { useMemo } from "react";
import { TodoStateContext } from "../components/TodoContext";
import { useContext } from "react";

const List = () => {
  const [search, setSearch] = useState("");
  const todos = useContext(TodoStateContext);

  const getFilterData = () => {
    if (search === "") return todos;

    return todos.filter((todo) =>
      todo.content.toLowerCase().includes(search.toLowerCase()),
    );
  };

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
        onChange={(e) => setSearch(e.target.value)}
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
