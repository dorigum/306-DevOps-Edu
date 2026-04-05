import Article from "./components/Article";
import Header from "./components/Header";
import Nav from "./components/Nav";
import Trip from "./components/Trip";
import "./App.css";

function App() {
  return (
    <>
      <Header title="Best Top 3 Trip" />
      <Nav />
      <Article title="태어난 김에 세계일주" body="이번 여름에 바다가 있는 테마 여행을 시작합니다. 올 여름 최고의 찬스!!" />
      <Trip /> {/* 사진 컴포넌트 추가 */}
    </>
  );
}

export default App;
