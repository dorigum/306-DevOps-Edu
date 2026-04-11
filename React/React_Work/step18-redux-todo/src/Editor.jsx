import React, { useEffect, useState, useRef } from "react";
import { useDispatch } from "react-redux";
import { addTodo } from "./redux/store";
import "./Editor.css";

const Editor = () => {
  const [content, setContent] = useState("");
  const contentRef = useRef();
  const dispatch = useDispatch();

  // 마운트 되었을 때 커서 놓기
  useEffect(() => {
    contentRef.current.focus();
  }, []);

  // 입력값 변경 시 호출
  const onChangeContent = (e) => {
    setContent(e.target.value);
  };

  // 추가 버튼 클릭 시 호출
  const onSubmit = () => {
    if (content === "") {
      contentRef.current.focus();
      return;
    }

    // 새로운 Todo 객체 생성
    const newTodo = {
      id: Date.now(), // 고유 ID 생성 (간편하게 현재 시간 사용)
      isDone: false,
      content: content,
      date: new Date().getTime(),
    };
    
    // Redux store에 추가 요청
    dispatch(addTodo(newTodo));

    // 입력창 초기화
    setContent("");
  };

  // 엔터 키 입력 시 추가
  const onKeyDown = (e) => {
    if (e.keyCode === 13) {
      onSubmit();
    }
  };

  return (
    <div className="Editor">
      <input
        type="text"
        placeholder="새로운 Todo"
        value={content}
        onChange={onChangeContent} // 글자 입력 시 상태만 업데이트하도록 수정
        ref={contentRef}
        onKeyDown={onKeyDown}
      />
      <button onClick={onSubmit}>추가</button>
    </div>
  );
};

export default Editor;
