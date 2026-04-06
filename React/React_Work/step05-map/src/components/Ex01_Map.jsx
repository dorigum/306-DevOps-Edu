import React from "react";

function Ex01_Map() {
  const items = ["짬뽕", "짜장", "탕수육", "짬짜면", "깐풍기"];
  
  return (
    <>
      <ul>
        {
            items.map((item, i) => <li key={i}> {item} / {i}</li>)
        }
      </ul>
    </>
  );
}

export default Ex01_Map;
