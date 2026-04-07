import React, { use } from "react";
import { GlobalContext } from "./Ex07_Context";
const Ex07_Child03 = () => {
  const { btnClick02 } = use(GlobalContext);

  return (
    <div style={{ border: "1px orange solid", padding: "10px" }}>
      <h3>Child 03입니다.</h3>
      {/* <button onClick={() => btnClick02()}>클릭!</button> */}
      <button onClick={btnClick02}>클릭!</button>
    </div>
  );
};

export default Ex07_Child03;
