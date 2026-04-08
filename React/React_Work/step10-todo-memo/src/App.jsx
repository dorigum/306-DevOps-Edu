import "./App.css";
import Header from "./components/Header";
import Editor from "./components/Editor";
import List from "./components/List";
import { useReducer } from "react";
import { useRef } from "react";
import {
  TodoDispatchContext,
  TodoStateContext,
} from "./components/TodoContext";

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

function App() {
  const [todos, dispatch] = useReducer(reducer, mockData);
  // id의 값은 내부적으로 값을 유지하기 위한 용도이므로 Ref 사용
  const idRef = useRef(3);

  // --------------------------------------------------------------------------------
  // onCreate, onUpdate, onDelete 함수를 Reducer 이용해서 수정하기

  // 추가하기
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

  // 수정하기
  const onUpdate = (targetId) => {
    // TodoItem에서 호출할 때 전달한 id
    // todo state의 값들 중에서 targetId와 일치하는 todoitem의 isDone 변경
    dispatch({ type: "UPDATE", targetId });
  };

  // 삭제하기
  const onDelete = (targetId) => {
    dispatch({ type: "DELETE", targetId: targetId });
  };

  // --------------------------------------------------------------------------------
  return (
    <div className="App">
      <Header />
      {/* <TodoContext.Provider value={{todos, onCreate, onUpdate, onDelete}}> */}
      <Editor onCreate={onCreate} />
      <List todos={todos} onUpdate={onUpdate} onDelete={onDelete} />
      <Editor />
      <List />
    </div>
  );
}

export default App;
