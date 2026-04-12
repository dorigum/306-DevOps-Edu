import React, { memo } from "react";
import "./TodoItem.css";
import { use } from "react";
import { TodoContext } from "../App";
import { useContext } from "react";

const TodoItem = ({ id, isDone, content, date }) => {
  const { onUpdate, onDelete } = use(TodoContext);
  console.log("TodoItem call")

  const onChangeCheckbox = () => {
    // 수정하기(checkbox 상태 변경)
    onUpdate(id);
  };

  return (
    <div className="TodoItem">
      <input type="checkbox" checked={isDone} onChange={onChangeCheckbox} />
      <div className="content">{content}</div>
      <div className="date">{new Date(date).toLocaleString()}</div>
      <button onClick={()=>{onDelete(id)}}>삭제</button>
    </div>
  );
};

export default memo(TodoItem);
