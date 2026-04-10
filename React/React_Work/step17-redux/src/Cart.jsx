import { shallowEqual, useDispatch, useSelector } from "react-redux";
import { sortByName, addCount } from "./redux/store.js";

const Cart = () => {
  // let state = useSelector((state) => state);
  let state = useSelector((state) => {
    return state.cart;
  }, shallowEqual);

  console.log(state);
  // console.log(state.count);
  // console.log(state.cart[0].name);

  // dispatch는 store.js로 요청을 보내주는 함수
  let dispatch = useDispatch();

  return (
    <div>
      <h5>장바구니</h5>
      <table style={{ border: "1px solid red" }}>
        <thead>
          <tr>
            <th>id</th>
            <th>상품명</th>
            <th>수량</th>
            <th>변경하기</th>
          </tr>
        </thead>
        <tbody>
          {state.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.count}</td>
              <td>
                <button onClick={() => dispatch(addCount(item.id))}> + </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <br />
      <button onClick={() => dispatch(sortByName())}>이름순정렬</button>{" "}
    </div>
  );
};
export default Cart;
