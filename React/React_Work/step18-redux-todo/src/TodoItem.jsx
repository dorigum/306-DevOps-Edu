import React from "react";
import "./TodoItem.css";
import { useDispatch } from "react-redux";
import { deleteTodo, updateTodo } from "./redux/store";

const TodoItem = ({ id, content, isDone, date }) => {
  const dispatch = useDispatch();

  return (
    <div className="TodoItem">
      <input
        type="checkbox"
        checked={isDone}
        onChange={() =>
          dispatch(updateTodo({ id, content, isDone: !isDone, date }))
        }
      />
      <div className="content">{content}</div>
      <div className="date">{new Date(date).toLocaleDateString()}</div>
      <button onClick={() => dispatch(deleteTodo(id))}>삭제</button>
    </div>
  );
};

export default TodoItem;
