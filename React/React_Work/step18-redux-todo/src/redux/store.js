import { configureStore, createSlice } from "@reduxjs/toolkit";

const todoSlice = createSlice({
  name: "todo",
  // 1. 여기에 초기 데이터 3개를 넣어둡니다!
  initialState: [
    {
      id: 1,
      isDone: false,
      content: "React 공부하기 ⚛️",
      date: new Date().getTime(),
    },

    {
      id: 2,
      isDone: true,
      content: "맛있는 점심 먹기 🍕",
      date: new Date().getTime(),
    },

    {
      id: 3,
      isDone: false,
      content: "Redux 마스터하기 🚀",
      date: new Date().getTime(),
    },
  ],

  reducers: {
    // 2. 새로운 Todo 추가 (전달받은 payload를 상태 배열에 추가)
    addTodo(state, action) {
      state.push(action.payload);
    },

    deleteTodo(state, action) {
      // id가 일치하지 않는 것들만 남기기
      return state.filter((item) => item.id !== action.payload);
    },

    updateTodo(state, action) {
      // id가 일치하는 항목을 찾아 업데이트
      let index = state.findIndex((item) => item.id === action.payload.id);
      if (index !== -1) {
        state[index] = action.payload;
      }
    },
  },
});

export const { addTodo, deleteTodo, updateTodo } = todoSlice.actions;

export default configureStore({
  reducer: {
    todo: todoSlice.reducer,
  },
});
