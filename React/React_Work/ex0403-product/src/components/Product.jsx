import React from 'react'
import "./Product.css";
import Item from "./Item";
import a from "../assets/a.png";
import b from "../assets/b.jpg";
import c from "../assets/c.png";
import d from "../assets/d.jpg";

export const Product = () => {
  return (
    <div id="product">
        <h3>오늘의 상품</h3>
        <p>새로운 상품을 만나보세요!</p>
        <Item imgName={a} text={"당도선별 11brix"} price={"25,000"}/>
        <Item imgName={b} text={"국내산 프리미엄"} price={"35,000"}/>
        <Item imgName={c} text={"13brix 100% 국내산"} price={"28,000"}/>
        <Item imgName={d} text={"고당도 참박수박"} price={"20,000"}/>
    </div>
  )
}

export default Product