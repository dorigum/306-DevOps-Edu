import React, { useEffect } from "react";
import { useRef } from "react";

function Ex04_Ref() {
  console.log("Ex04_Ref call");

  const inputRef = useRef();
  // console.log(inputRef); // {current: undefined}
  useEffect(() => {
    console.log(inputRef); // {current: input}

    inputRef.current.focus(); // 커서 놓기
  });

  // 클릭 이벤트
  const clickCheck = () => {
    alert(`${inputRef.current.value}님 클릭했네요.`);
    inputRef.current.value = ""; // .current 꼭 추가하기!!!★★★
    inputRef.current.focus();
  };

  return (
    <>
      <h3>useRef로 DOM 접근하기</h3>
      <input type="text" ref={inputRef} />
      <button onClick={clickCheck}>클릭하세요</button>
    </>
  );
}

export default Ex04_Ref;
