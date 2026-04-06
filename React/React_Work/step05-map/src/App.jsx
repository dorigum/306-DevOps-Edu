import "./App.css";
import Ex01_Map from "./components/Ex01_Map";
import Ex02_Map from "./components/Ex02_Map";
import Ex03_Product from "./components/Ex03_Product";
import Ex04_MapKeyTest01 from "./components/Ex04_MapKeyTest01";
import Ex05_MapkeyTest02 from "./components/Ex05_MapkeyTest02";

function App() {
  return (
    <>
      <h1>Map 함수 연습하기</h1>
      {/* 1. 기본 값 배열 Map 사용하기 */}
      {/* <Ex01_Map /> */}

      {/* 2. Object 배열 Map 사용하기 */}
      {/* <Ex02_Map/> */}

      {/* 3. Item 배열 Map 사용하기 */}
      {/* <Ex03_Product/> */}

      {/* Form 요소 Map 사용하기 */}
      {/* <Ex04_MapKeyTest01/> */}
      <Ex05_MapkeyTest02/>
    </>
  );
}

export default App;
