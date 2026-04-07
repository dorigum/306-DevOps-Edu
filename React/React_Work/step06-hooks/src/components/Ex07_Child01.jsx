import Ex07_Child02 from "./Ex07_Child02";
import { useRef } from "react";
import { GlobalContext } from "./Ex07_Context";
import { use } from "react";

// props 전달하는 방법
// const Ex07_Child01 = (list, setList) => {
//   const idRef = useRef(4);

//   return (
//     <div style={{ border: "1px blue solid", padding: "10px" }}>
//       <h3>Child01입니다.</h3>
//       <button>눌러보쉴?</button>
//       <button onClick={() => setList(...list, { id: idRef.current++, name: "도연" })}>
//         추가
//       </button>

//       <hr />
//       <Ex07_Child02 />
//     </div>
//   );
// };

// --------------------------------------------------------------------------------
const Ex07_Child01 = () => {
  // Context 영역에 있는 상태 정보 가져오기
  const { setList, btnClick01, list } = use(GlobalContext);
  const idRef = useRef(4);

  return (
    <div style={{ border: "1px blue solid", padding: "10px" }}>
      <h3>Child01입니다.</h3>
      <button onClick={btnClick01}>눌러보세요</button>
      <button
        onClick={() =>
          setList([...list, { id: idRef.current++, name: "도연" }])
        }
      >
        추가
      </button>

      <hr />
      <Ex07_Child02 />
    </div>
  );
};

export default Ex07_Child01;
