import "./App.css";
import Header from "./components/Header";
import Editor from "./Editor";
import List from "./List";
import { useDispatch, useSelector } from "react-redux";
import { addTodo } from "./redux/store";
import TodoItem from "./TodoItem";

function App() {
  // store에 있는 count 가져오기(조회)
  const search = useSelector((state) => {
    return state.count.search;
  });

  const dispatch = useDispatch();

  return (
    <>
      <div className="App">
        <Header />
        <Editor onCreate={() => dispatch(addTodo())} />
        {/* <button onClick = {() => dispatch(addTodo())}>추가</button> */}
        <span> {search} </span>
        <List />
        <TodoItem />
      </div>
    </>
  );
}

export default App;
