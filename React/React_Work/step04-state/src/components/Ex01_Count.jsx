import React from "react";
import { useState } from "react";

let i = 0;
function Ex01_Count() {
  // useState의 리턴은 배열이 [초기값, 변경 함수]
  const [no, setNo] = useState(0);

  console.log(no);
  console.log(setNo);

  const minusClick = function () {
    i--;
    // console.log(i);

    // state 값 변경
    setNo(no - 1); // state의 값이 변경되면 리랜더링된다.
  };

  const plusClick = function () {
    i++;
    // console.log(i);
    setNo(no + 1);
  };

  return (
    <div>
      <h2>숫자 증가, 감소</h2>
      <button onClick={minusClick}>빼기</button>
      <span>
        no = {no} / i = {i}
      </span>
      <button onClick={plusClick}>더하기</button>
    </div>
  );
}

export default Ex01_Count;
