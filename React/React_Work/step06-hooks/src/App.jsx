import "./App.css";
import Ex01_Effect from "./components/Ex01_Effect";
import Ex02_Effect from "./components/Ex02_Effect";
import Ex03_Memo from "./components/Ex03_Memo";
import Ex04_Ref from "./components/Ex04_Ref";
import Ex05_Ref from "./components/Ex05_Ref";
import Ex06_Ref from "./components/Ex06_Ref";

function App() {
  return (
    <>
      <h1>Hooks 연습하기</h1>
      {/* 1. useEffect 연습하기 */}
      {/* <Ex01_Effect /> */}

      {/* 2. useEffect 자원 정리(return) 연습하기 */}
      {/* <Ex02_Effect/> */}

      {/* 3. useMemo 연습하기 */}
      {/* <Ex03_Memo /> */}

      {/* 4. useRef DOM 접근 TEST */}
      {/* <Ex04_Ref /> */}

      {/* 5. useState vs useRef 차이 */}
      {/* <Ex05_Ref/> */}

      {/* 6. useRef vs let 차이 */}
      <Ex06_Ref/>
      <h3>--------------Ex06_Ref 위 아래 두 번 등록--------------</h3>
      <Ex06_Ref/>
    </>
  );
}

export default App;
