import React from "react";

function Header(props) {
  return (
    <header>
      {/* App.jsx에서 보낸 "Trip" 출력 */}
      <h1>{props.title}</h1>
    </header>
  );
}

export default Header;
