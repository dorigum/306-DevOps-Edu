import React from "react";
import { useState } from "react";
import Ex01_Count from "./Ex01_Count";
import Ex02_Form from "./Ex02_Form";

const Ex04_ConditionRendering = () => {
  const [isLogin, setIsLogin] = useState(false);

  return (
    <div>
      <h3>ConditionRendering Test</h3>
      <button onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? "Logout" : "Login"}
        {/* Login이면 Logout로, Logout이면 Login로 */}
      </button>
      <hr />
    </div>
  );
};

export default Ex04_ConditionRendering;
