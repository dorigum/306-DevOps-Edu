import React from "react";
import Ex03_ButtonImg from "./Ex03_ButtonImg";
import mail from "../assets/images/mail.png";
import location from "../assets/images/location.png";
import search from "../assets/images/search.png";
import emotion1 from "../assets/images/emotion1.png";

function Ex03_ButtonTest() {
  const btnClick = (e) => {
    console.log(e.target);
    console.log(e.target.innerText + " 반응 살피기(?)");
  };

  const obj =  {
    no: 1, name: "koo", age: 20, addr: "서울"
  }

  // assets 폴더 안에 있는 이미지는 import해서 사용
  return (
    <div style={{ display: "flex", gap: "30px" }}>
      <Ex03_ButtonImg imgSrc={mail} text="메일" btnCk={btnClick} {...obj} />
      <Ex03_ButtonImg imgSrc={location} text="위치" btnCk={btnClick} obj={obj} />
      <Ex03_ButtonImg imgSrc={search} text="검색" btnCk={btnClick} />
    <hr/>

    <img src="emotion1.png" /><br/>
    <img src={emotion1} /><br/>
    <img src="../assets/images/emotion1.png" /><br/>
    </div>
  );
}

export default Ex03_ButtonTest;
