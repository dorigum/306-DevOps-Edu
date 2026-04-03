import React from "react";
import "./Ex03_ButtonImg.css";

function Ex03_ButtonImg({ imgSrc, text, btnCk }) {
//   console.log(obj);

  return (
    <div className="divBtn">
      <img src={imgSrc} />
      <button onClick={btnCk}>{text}</button>
    </div>
  );
}

export default Ex03_ButtonImg;
