import React from "react";
import "./Item.css";

export const Item = ({ imgName, text, price, no, seq }) => {
  console.log(no);
  console.log(seq);
  console.log("----------");

  return (
    <div className="box">
      <img src={imgName} alt="수박" />
      <h5>{text}</h5>
      <span>{price}원</span>
    </div>
  );
};

/*
const Item + {{img}}
*/
export default Item;
