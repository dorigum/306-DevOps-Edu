import "./App.css";
import Header from "./components/Header";
import Editor from "./components/Editor";
import List from "./components/List";
import { useState } from "react";
import { useRef } from "react";

// 렌더링이 될 때 다시 실행되지 않아도 되기 때문에, 함수 밖에 선언한다.
const mockData = [
  { id: 0, isDone: false, content: "React Study", date: new Date().getTime() },
  { id: 1, isDone: false, content: "친구 만나기", date: new Date().getTime() },
  { id: 2, isDone: false, content: "낮잠자기", date: new Date().getTime() },
];

function App() {
  const [todos, setTodos] = useState(mockData);
  const idRef = useRef(3); // 재렌더링이 되어도 내부적으로 값을 유지할 수 있도록 Ref 사용

  // 추가하기
  const onCreate = (content) => {
    const newTodo = {
      id: idRef.current++,
      isDone: false,
      content: content,
      date: new Date().getTime(),
    };

    setTodos([newTodo, ...todos]);
  };

  // 수정하기
  // 위 코드를 삼항연산자로 변경!!!!!!
  const onUpdate = (targetId) => {
    setTodos(
      todos.map((todo) =>
        todo.id === targetId ? { ...todo, isDone: !todo.isDone } : todo,
      ),
    );
  };

  // 삭제하기
  const onDelete = (targetId) => {
    // 인수: todos 배열에서 targetId와 일치하는 id를 갖는 요소만 삭제한 새로운 배열
    setTodos(todos.filter((todo) => todo.id !== targetId));
  };

  return (
    <div className="App">
      <Header />
      <Editor onCreate={onCreate} />
      <List todos={todos} onUpdate={onUpdate} onDelete={onDelete} />
    </div>
  );
}

export default App;
