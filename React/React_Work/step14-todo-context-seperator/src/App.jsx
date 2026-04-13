import "./App.css";
import Header from "./components/Header";
import Editor from "./components/Editor";
import List from "./components/List";
import { useReducer, useMemo, useRef } from "react";

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
// export const TodoStateContext = createContext();
// export const TodoDispatchContext = createContext();

function App() {
  // const [todos, setTodos] = useState(mockData);
  const [todos, dispatch] = useReducer(reducer, mockData);

  const idRef = useRef(3);

  const onCreate = (content) => {
    dispatch({
      type: "CREATE",
      data: {
        id: idRef.current++,
        isDone: false,
        content: content,
        date: new Date().getTime(),
      },
    });
  };

  // 수정하기(useCallback() 최적화 적용)
  const onUpdate = (targetId) => {
    // TodoItem에서 호출할 때 전달한 id
    // todo state의 값들 중에서 targetId와 일치하는 todoitem의 isDone 변경
    dispatch({ type: "UPDATE", targetId });
  };

  // 삭제하기(useCallback() 최적화 적용)
  const onDelete = (targetId) => {
    dispatch({ type: "DELETE", targetId: targetId });
  };

  // 💡 Dispatch 함수들을 묶어서 최적화(컴포넌트 리렌더링 방지)
  const memoizedDispatch = useMemo(
    () => ({ onCreate, onDelete, onUpdate }),
    [],
  );

  return (
    <div className="App">
      <Header />
      <TodoStateContext value={todos}>
        <TodoDispatchContext value={memoizedDispatch}>
          <Editor />
          <List />
        </TodoDispatchContext>
      </TodoStateContext>
    </div>
  );
}

export default App;