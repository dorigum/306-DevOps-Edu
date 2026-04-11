import "./App.css";
import Header from "./components/Header";
import Editor from "./Editor";
import List from "./List"; // List 컴포넌트 임포트 추가

function App() {
  // Redux를 사용하면 App에서 직접 상태를 관리하거나 dispatch를 할 필요가 줄어듭니다.
  // 상태는 store에서, 액션은 각 컴포넌트(Editor, TodoItem)에서 직접 처리하면 되거든요!

  return (
    <div className="App">
      <Header />
      <Editor />
      <List />
    </div>
  );
}

export default App;
