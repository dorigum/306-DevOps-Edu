import React, { memo } from "react";
import "./TodoItem.css";
import { use } from "react";
import { TodoDispatchContext } from "../components/TodoContext";

const TodoItem = ({ id, isDone, content, date }) => {
  const { onUpdate, onDelete } = use(TodoDispatchContext);

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