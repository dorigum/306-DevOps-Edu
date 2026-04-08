import React from "react";
import { useReducer } from "react";

const reducer = (state, action) => {
  console.log(state, action);

  switch (action.type) {
    case "up":
      return state + action.data;
    case "down":
      return state - action.data;
    default:
      return state;
  }
};

function Ex01_Count() {
  const [state, dispatch] = useReducer(reducer, 100);
  console.log(state);
  console.log(dispatch);

  const minusClick = function () {
    dispatch({
      type: "down",
      data: 1,
      info: "OK",
    }); // reducer 함수가 호출된다.
  };

  const plusClick = function () {
    dispatch({
      type: "up",
      data: 1,
      info: "Success",
    });
  };

  return (
    <div>
      <h2>숫자 증가, 감소</h2>
      <button onClick={minusClick}>빼기</button>
      <span>state = {state}</span>
      <button onClick={plusClick}>더하기</button>
    </div>
  );
}

export default Ex01_Count;
