import React from "react";
import Ex02_Book from "./Ex02_Book";

function Ex02_Library() {
  const author = { name: "koo", age: 20, addr: "서울" };
  return (
    <>
      <Ex02_Book bookName="Spring" numPage={200} author={author} />
      <Ex02_Book bookName="HTML" numPage={100} author={{name: "do", age: 30, addr: "경기"}} />
      <Ex02_Book numPage={250} author={author}/>
    </>
  );
}

export default Ex02_Library;
