import React from "react";

// function Ex02_Book(props) { // props가 아닌 다른 이름으로도 가능!
//     console.log(props);

//   return (
//     <>
//         <h3>이 책은 {props.bookName} 입니다.</h3>
//         <h4>이 책의 총 페이지 수는 {props.numPage} 페이지로 이루어져 있습니다.</h4>
//     </>
//   )
// }

function Ex02_Book({ numPage, bookName, author }) {
  // console.log(props);
  return (
    <>
      <h3>이 책은 {bookName} 입니다.</h3>
      <h4>이 책의 총 페이지 수는 {numPage} 페이지로 이루어져 있습니다.</h4>
      <h4>작성자: {author.name} / {author.age} / {author.addr}</h4>
    </>
  );
}

export default Ex02_Book;
