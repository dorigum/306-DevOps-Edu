import React from "react";
import Comment from "./Comment";

function CommentList() {
  const comments = [
    {
      name: "도연",
      comment: "오류 해결해서 기분 좋아요!",
    },

    {
      name: "리액트봇",
      comment: "축하드려요! 이제 시작입니다.",
    },

    {
      name: "이가현",
      comment: "저도 리액트 배워보고 싶어요!",
    },
  ];
  // map 함수를 사용했을 때, key를 설정하지 않으면
  // Each child in a list should have a unique "key" prop. 오류 발생

  // 리액트에서key는 컴포넌트 배열을 렌더링했을 때 어떤 원소에 변동이 있었는지 알아내려고 사용한다.
  // 유동적인 데이터를 다룰 때는 key가 없다면 Virtual DOM을 비교하는 과정에서 리스트를 순차적으로 비교하면서 변화를 감지한다.
  // 하지만 key가 있다면 이 값을 이용하여 어떤 변화가 일어났는지 더욱 빠르게 알아낼 수 있다. 

  // ★★★★★key값은 유일해야 한다!!!!!!
  // 따라서, 데이터가 가진 고유 값을 key 값으로 설정해야 한다.
  // ※단, map의 index로 key를 설정하면 안된다!!!

  return (
    <div>
      {/* 수정 전 코드*/}
      {/* {comments.map((com, index) => (
        <Comment name={com.name} text={com.comment} />
      ))} */}
      {/* --------------------------------------------------- */}

      {/* 수정 후 코드 */}
      {comments.map((com, index) => {
        return <Comment name={com.name} text={com.comment} key={com.name} />;
      })}
    </div>
  );
}

export default CommentList;
