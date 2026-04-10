import { configureStore, createSlice } from "@reduxjs/toolkit";

// createSlice(): reducer + action을 한번에 생성
// configureStore(): Redux store를 생성해 여러 slicef를 통합
// dispatch(): action을 실행하는 방법

const filterSlice = createSlice({
  name: "filter",
  initialState: [],
  reducers: {
    searchTodo(state, action) {
      return state.filter((search) => search.content.includes(action.payload));
    },
  },
});

// todo mockData
const todoSlice = createSlice({
  name: "todo",
  initialState: [
    {
      id: 0,
      isDone: false,
      content: "React Study",
      date: new Date().getTime(),
    },
    {
      id: 1,
      isDone: false,
      content: "친구 만나기",
      date: new Date().getTime(),
    },
    { id: 2, isDone: false, content: "낮잠자기", date: new Date().getTime() },
  ],

  reducers: {
    addTodo(state, action) {
      state.push(action.payload);
    },

    deleteTodo(state, action) {
      return state.filter((item) => item.id !== action.payload);
    },

    updateTodo(state, action) {
      let index = state.findIndex((item) => item.id === action.payload.id);
      state[index] = action.payload;
    },
  },
});

// 위 reducers에 정의된 액션 생성자(action creator)를 추출하여 export
export const { addTodo, deleteTodo, updateTodo, searchTodo } =
  todoSlice.actions;

export default configureStore({
  reducer: {
    filter: filterSlice.reducer,
    todo: todoSlice.reducer,
  },
});
