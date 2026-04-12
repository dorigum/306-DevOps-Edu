import React from "react";
import "./TodoItem.css";

function TodoItem({ id, isDone, content, date, onDelete, onUpdate }) {
  const onChangeCheckbox = () => {
    onUpdate(id);
  };

  return (
    <div className="TodoItem">
      <input type="checkbox" checked={isDone} onChange={onChangeCheckbox} />
      <div className="content">{content}</div>
      <div className="date">{new Date(date).toLocaleString()}</div>
      <button onClick={onClickDeleteButton}>삭제</button>
    </div>
  );
}

export default TodoItem;
