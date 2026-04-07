import React from "react";
import "./Ex03_Product.css";
import Item from "./Item";
import a from "../assets/a.png";
import b from "../assets/b.jpg";
import c from "../assets/c.png";
import d from "../assets/d.jpg";

export const Ex03_Product = () => {
  const items = [
    { id: 1, imgName: a, text: "당도선별 11brix", price: "25,000" },
    { id: 2, imgName: b, text: "국내산 프리미엄", price: "35,000" },
    { id: 3, imgName: c, text: "13brix 100% 국내산", price: "28,000" },
    { id: 4, imgName: d, text: "고당도 참박수박", price: "20,000" },
    { id: 5, imgName: b, text: "당도선별 11brix", price: "15,000" },
  ];

  console.log(items);

  let no = 10;
  const test = {
    seq: [{ good: 1 }, { good: 2 }, { good: 3 }],
    no, // no: no의 축약형
  };

  return (
    <div id="product">
      <h3>오늘의 상품</h3>
      <p>새로운 상품을 만나보세요!</p>

      {items.map((item) => (item) => item.text + " = " + item.price + <br />)}

      <hr />
      {items.map((item, i) => (
        <h6 key={i}>
          {item.text} / {item.price}
        </h6>
      ))}

      {items.map((item) => (
        <Item key={item.id} {...item} {...test} />
      ))}
    </div>
  );
};

export default Ex03_Product;
