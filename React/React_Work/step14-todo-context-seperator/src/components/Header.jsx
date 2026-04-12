import React, { memo } from "react";
import "./Header.css";

const Header = () => {
  console.log("Header call")
  return (
    <div className="Header">
      <h3>오늘의 Plan🌼📝</h3>
      <h1>{new Date().toLocaleString()}</h1>
    </div>
  );
};

// React.memo() 적용
export default memo(Header);
