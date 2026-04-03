import React from "react";
import { useState } from "react";

const Ex04_ConditionRendering = () => {
  const [isLogin, setIsLogin] = useState(false);

  return (
    <div>
      <h3>ConditionRendering Test</h3>
      <button onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? "Logout" : "Login"}
        {/* Login이면 Logout로, Logout이면 Login로 */}
      </button>
    </div>
  );
};

export default Ex04_ConditionRendering;
