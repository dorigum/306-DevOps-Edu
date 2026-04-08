import React from "react";
import { Link, useNavigate } from "react-router-dom";

function Header() {
  const nav = useNavigate();
  const btn1 = function () {
    // 기능(유효성 체크)
    // 성공하면 페이지 전환, 실패하면 메시지 출력

    // 이동
    nav("/"); // HOME 이동
  };

  const btn2 = function () {
    nav("/user/50");
  };

  const btn3 = function () {
    nav("/admin");
  };

  return (
    <div style={{ border: "2px solid lightpink" }}>
      <h1>Header 입니다.</h1>
      <Link to="/">HOME</Link> &nbsp;&nbsp;&nbsp;
      <Link to="/user/doyeon">USER</Link> &nbsp;&nbsp;&nbsp;
      <Link to="/admin">ADMIN</Link> &nbsp;&nbsp;&nbsp;
      <hr />
      {/* a 태그는 사용하지 않음!!!! */}
      <a href="/">HOME</a> &nbsp;&nbsp;&nbsp;
      <a href="/user/koo">USER</a> &nbsp;&nbsp;&nbsp;
      <a href="/admin">ADMIN</a> &nbsp;&nbsp;&nbsp;
      <hr />
      <button onClick={btn1}>클릭1</button>
      <button onClick={btn2}>클릭2</button>
      <button onClick={btn3}>클릭3</button>
    </div>
  );
}

export default Header;
