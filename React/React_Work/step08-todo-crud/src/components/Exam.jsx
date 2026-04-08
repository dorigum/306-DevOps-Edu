import React from "react";
import { useReducer } from "react";

const reducer = (state, action) => {
  // 현재 상태 값, action
  console.log(state, action);

  switch (action.type) {
    case "UP":
      return state + action.data;
    case "DOWN":
      return state - action.data;
    default:
      return state;
  }
};

// reducer: 상태를 어떻게 업데이트할 지 정의하는 함수
// dispatch: 액션을 전달하여 상태를 업데이트하는 함수
const Exam = () => {
  const [state, dispatch] = useReducer(reducer, 0);

  const onClickPlus = () => {
    dispatch({ type: "UP", data: 1 });
  };
  const onClickMinus = () => {
    dispatch({ type: "DOWN", data: 1 });
  };

  return (
    <div>
      <h1>{state}</h1>
      <button onClick={onClickPlus}>+</button>
      <button onClick={onClickMinus}>-</button>
    </div>
  );
};

export default Exam;
