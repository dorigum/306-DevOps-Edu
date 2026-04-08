import React, { memo } from "react";
import "./TodoItem.css";
import { use } from "react";
import { TodoContext } from "../App";
import { TodoDispatchContext } from "../components/TodoContext";

// const TodoItem = ({ id, isDone, content, date, onUpdate, onDelete }) => {
const TodoItem = ({ id, isDone, content, date }) => {
  const { onUpdate, onDelete } = use(TodoContext);

  // console.log(id + "-> 렌더링")
  const onChangeCheckbox = () => {
    // 수정하기(checkbox 상태 변경)
    onUpdate(id);
  };

  // 삭제를 클릭했을 때
  const onClickDeleteButton = () => {
    onDelete(id);
  };

  return (
    <div className="TodoItem">
      <input type="checkbox" checked={isDone} onChange={onChangeCheckbox} />
      <div className="content">{content}</div>
      <div className="date">{new Date(date).toLocaleString()}</div>
      <button onClick={onClickDeleteButton}>삭제</button>
    </div>
  );
};

export default memo(TodoItem);
