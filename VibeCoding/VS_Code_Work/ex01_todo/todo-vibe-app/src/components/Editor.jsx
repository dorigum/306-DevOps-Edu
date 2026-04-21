import { useState } from 'react';
import './Editor.css';

// Editor 컴포넌트: 새로운 할 일을 입력받고 추가하는 역할
function Editor({ onCreate }) {
  const [input, setInput] = useState('');

  // 추가 버튼 클릭 이벤트 핸들러
  const handleAddClick = () => {
    if (input.trim() === '') {
      alert('할 일을 입력해주세요.');
      return;
    }
    onCreate(input); // 부모로부터 받은 onCreate 함수 호출
    setInput('');    // 입력창 초기화
  };

  // 엔터 키 입력 이벤트 핸들러
  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleAddClick();
    }
  };

  return (
    <div className="editor">
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyDown={handleKeyDown}
        placeholder="새로운 할 일을 입력하세요"
        className="editor-input"
      />
      <button onClick={handleAddClick} className="editor-btn">
        추가
      </button>
    </div>
  );
}

export default Editor;