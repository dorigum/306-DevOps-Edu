import React from "react";
import "./Item.css";

export const Item = (props) => {
  return (
    <div className="box">
      <img src={props.imgName} alt="수박" />
      <h5>{props.text}</h5>
      <span>{props.price}원</span>
    </div>
  );
};

export default Item;
