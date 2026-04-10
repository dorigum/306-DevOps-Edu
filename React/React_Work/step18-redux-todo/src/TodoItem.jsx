import React from "react";
import "./TodoItem.css";
import { shallowEqual, useDispatch, useSelector } from "react-redux";
import { deleteTodo, updateTodo } from "./redux/store.js";

// step17-Cart 역할: 삭제 버튼/체크박스(수정)
// dispatch(deleteTodo()), dispatch(updateTodo()) 실행
const TodoItem = () => {
  let state = useSelector((state) => {
    return state.todo;
  }, shallowEqual); // todo list 뿌리기

  // console.log(state);

  // dispatch: store.js로 요청을 보내주는 함수
  let dispatch = useDispatch();

  return (
    <div className="TodoItem">
      {/* step17-Cart 역할: useSelect를 사용해서 Todo 배열을 map으로 뿌려주기 */}
      {state.map((item) => (
        <div key={item.id}>
          <input
            type="checkbox"
            checked={item.isDone}
            onChange={() => dispatch(updateTodo(item))}
          />
          <div className="content">{item.content}</div>
          <div className="date">{new Date(item.date).toLocaleString()}</div>
          <button onClick={() => dispatch(deleteTodo(item.id))}>삭제</button>
        </div>
      ))}
      )
    </div>
  );
};

export default TodoItem;
