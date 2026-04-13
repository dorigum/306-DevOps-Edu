import React, { useEffect } from "react";
import { useState } from "react";
import "./Editor.css";
import { useRef } from "react";
import { TodoStateContext, TodoDispatchContext } from "./components/TodoContext";
import { use } from "react";

const Editor = () => {
  const [content, setContent] = useState("");
  const contentRef = useRef();

  const { onCreate } = use(TodoDispatchContext); // version 19

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
    onCreate(content);
    setContent("");
    contentRef.current.focus();
  };

  // 엔터를 입력했을 때 onSubmit 호출
  const onKeyDown = (e) => {
    if (e.keyCode === 13) onSubmit();
  };

  return (
    <div className="Editor">
      <input
        type="text"
        placeholder="새로운 Todo"
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
