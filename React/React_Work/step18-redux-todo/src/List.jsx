import React, { useState, useMemo } from "react";
import { useSelector } from "react-redux"; // useSelector 임포트 추가
import "./List.css";
import TodoItem from "./TodoItem";

const List = () => {
  const [search, setSearch] = useState("");
  // Redux store에서 todo 데이터 가져오기
  const todos = useSelector((state) => state.todo);

  // 검색어 입력 시 호출되는 함수
  const onChangeSearch = (e) => {
    setSearch(e.target.value);
  };

  // 검색어에 따라 필터링된 Todo 리스트 반환
  const getFilterData = () => {
    if (search === "") return todos;

    return todos.filter((todo) =>
      todo.content.toLowerCase().includes(search.toLowerCase()),
    );
  };

  const filteredTodos = getFilterData();
  // 통계 데이터 계산 (todos가 변경될 때만 재계산)
  const { totalCount, doneCount, notDoneCount } = useMemo(() => {
    const totalCount = todos.length;
    const doneCount = todos.filter((todo) => todo.isDone).length;
    const notDoneCount = totalCount - doneCount;

    return { totalCount, doneCount, notDoneCount };
  }, [todos]);

  return (
    <div className="List">
      <h4>Todo List🌱</h4>
      <div className="stats">
        <div>total: {totalCount}</div>
        <div>done: {doneCount}</div>
        <div>notDone: {notDoneCount}</div>
      </div>

      <input
        type="text"
        placeholder="검색어를 입력해주세요."
        value={search}
        onChange={onChangeSearch} // 검색어 상태 업데이트로 수정
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
