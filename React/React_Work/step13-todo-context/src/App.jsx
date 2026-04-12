import "./App.css";
import Header from "./components/Header";
import Editor from "./components/Editor";
import List from "./components/List";
import { useReducer, useState, useRef } from "react";
import { useCallback } from "react";
import { createContext } from "react";

const mockData = [
  { id: 0, isDone: false, content: "React Study", date: new Date().getTime() },
  { id: 1, isDone: false, content: "친구 만나기", date: new Date().getTime() },
  { id: 2, isDone: false, content: "낮잠자기", date: new Date().getTime() },
];

const reducer = (state, action) => {
  switch (action.type) {
    case "CREATE":
      return [action.data, ...state];

    case "UPDATE":
      return state.map((todo) =>
        todo.id === action.targetId ? { ...todo, isDone: !todo.isDone } : todo,
      );

    case "DELETE":
      return state.filter((todo) => todo.id !== action.targetId);

    default:
      return state;
  }
};

// ★★★context 사용하기
export const TodoContext = createContext();

function App() {
  // const [todos, setTodos] = useState(mockData);
  const [todos, dispatch] = useReducer(reducer, mockData);

  const idRef = useRef(3);

  // 추가하기(useCallback() 최적화 적용)
  const onCreate = useCallback((content) => {
    dispatch({
      type: "CREATE",
      data: {
        id: idRef.current++,
        isDone: false,
        content: content,
        date: new Date().getTime(),
      },
    });
  }, []);

  // 수정하기(useCallback() 최적화 적용)
  const onUpdate = useCallback((targetId) => {
    dispatch({ type: "UPDATE", targetId });
  }, []);

  // 삭제하기(useCallback() 최적화 적용)
  const onDelete = useCallback((targetId) => {
    dispatch({ type: "DELETE", targetId: targetId });
  }, []);

  return (
    <div className="App">
      <Header />
      <TodoContext value={{ todos, onCreate, onUpdate, onDelete }}>
        <Editor />
        <List />
      </TodoContext>
    </div>
  );
}

export default App;
