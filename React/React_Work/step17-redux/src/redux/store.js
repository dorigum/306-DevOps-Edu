import { configureStore, createSlice } from "@reduxjs/toolkit";

// 작은 단위(slice)의 형태로 state 정보를 정의한다.
const countSlice = createSlice({
  name: "count",
  initialState: { no: 100 }, // 초기 값
  reducers: {
    up(state) {
      // 현재 값, action에 payload가 전달된다.
      // console.log("up state = " + state);
      state.no = state.no + 1;
    },

    down(state) {
      // console.log("down state = " + state);
      state.no = state.no - 1;
    },

    incrementByNo(state, action) {
      // console.log("incrementByNo state = " + state);
      console.log(action);
      state.no = state.no + action.payload;
    },
  },
});

// ------------------------------------------------------------------
// Cart 정보
const cartSlice = createSlice({
  name: "cart",
  initialState: [
    { id: 1, name: "수박", count: 3 },
    { id: 2, name: "딸기", count: 5 },
    { id: 3, name: "포도", count: 8 },
  ],

  reducers: {
    addCount(state, action) { // action.payload
      let index = state.findIndex((item) => item.id === action.payload);
      state[index].count++;
    },

    addItem(state, action) {
      state.push(action.payload);
    },

    sortByName(state) {
      state.sort((a, b) => (a.name > b.name ? 1 : -1));
    },
  },
});

// 위의 reducers에 정의된 함수를 외부에서 호출해서 사용할 수 있도록 export
export const { up, down, incrementByNo } = countSlice.actions;
export const { addCount, addItem, sortByName } = cartSlice.actions;

// 여러 slice들을 reducer에 등록하는 작업이 필요
export default configureStore({
  reducer: {
    count: countSlice.reducer,
    cart: cartSlice.reducer,
  },
});
