import { useState } from 'react';
import Header from './components/Header';
import Editor from './components/Editor';
import List from './components/List';
import './App.css';

// App 컴포넌트: 전체 TodoList의 상태와 비즈니스 로직을 관리하는 최상위 부모 컴포넌트
function App() {
  // 초기 데이터 (더미 데이터)
  const initialTodos = [
    {
      id: 1,
      content: '리액트 공부하기',
      isDone: false,
      createdDate: new Date('2026-04-19').getTime(),
    },
    {
      id: 2,
      content: '할 일 목록 프로젝트 완성하기',
      isDone: false,
      createdDate: new Date('2026-04-20').getTime(),
    },
    {
      id: 3,
      content: '코드 리뷰하기',
      isDone: true,
      createdDate: new Date('2026-04-18').getTime(),
    },
  ];

  // todos 상태 관리
  const [todos, setTodos] = useState(initialTodos);
  // 다음 생성될 아이템의 ID를 관리하는 상태
  const [nextId, setNextId] = useState(4);

  // 새로운 Todo를 생성하는 함수
  const onCreate = (content) => {
    const newTodo = {
      id: nextId,
      content: content,
      isDone: false,
      createdDate: new Date().getTime(),
    };
    setTodos([...todos, newTodo]);
    setNextId(nextId + 1);
  };

  // Todo의 완료 상태(isDone)를 토글하는 함수
  const onUpdate = (id) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, isDone: !todo.isDone } : todo
      )
    );
  };

  // Todo를 삭제하는 함수
  const onDelete = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  return (
    <div className="app">
      <div className="app-container">
        <Header />
        <Editor onCreate={onCreate} />
        <List todos={todos} onUpdate={onUpdate} onDelete={onDelete} />
      </div>
    </div>
  );
}

export default App;