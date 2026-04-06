import "./App.css";
import Comment from "./components/Comment";
import CommentList from "./components/CommentList";
import Rendering_Count from "./components/Rendering_Count";

function App() {
  return (
    <>
      {/* 1. 댓글 컴포넌트 만들기(map() 함수 사용) */}
      {/* <CommentList /> */}

      {/* 2. 교안자료 06_React Hooks 종류 */}
      {/* 무한 루프에 빠지는 이유???? */}

      {/* 3. useRef로 변경하기 */}
      <Rendering_Count />
    </>
  );
}

export default App;
