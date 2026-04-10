import React, { useRef } from "react";
import { useDispatch } from "react-redux";
import { addItem } from "./redux/store";

const Order = () => {
  // dispatch는  store.js로 요청을 보내주는 함수
  let dispatch = useDispatch();
  let idRef = useRef(4);

  return (
    <div>
      <h4>주문하기 페이지</h4>
      <button
        onClick={() => {
          dispatch(addItem({ id: idRef.current++, name: "오렌지", count: 15 }));
        }}
      >
        오렌지 주문하기
      </button>
    </div>
  );
};

export default Order;
