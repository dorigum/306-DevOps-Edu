import React, { useEffect } from "react";
import { useState, useRef } from "react";
import "./Editor.css";

const Editor = ({ onCreate }) => {
  const [content, setContent] = useState("");
  const contentRef = useRef();

  // 마운트 되었을 때 커서 놓기
  useEffect(() => {
    contentRef.current.focus();
  }, []);

  // 추가 버튼 클릭
  const onSubmit = () => {
    if (content === "") {
      contentRef.current.focus();
      return;
    }

    // 추가 기능 함수 호출(App.jsx 파일에 있는 함수가 호출된다!)
    onCreate(content); // 부모 쪽 함수 호출(입력한 내용을 전달)
    setContent(""); // 내용 비우기
    contentRef.current.focus();
  };

  // 엔터를 입력했을 때 onSubmit 호출
  const onkeydown = (e) => {
    if (e.keyCode === 13) onSubmit();
  };

  return (
    <div className="Editor">
      <input
        type="text"
        placeholder="새로운 todo"
        value={content}
        onChange={(e) => setContent(e.target.value)}
        ref={contentRef}
        onKeyDown={onKeyDown}
      />
      <button onClick={onSubmit}>추가</button>
    </div>
  );
};

export default Editor;
