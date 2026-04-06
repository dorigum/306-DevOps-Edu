import React, { useEffect, useRef, useState } from "react";

const Rendering_Count = () => {
  const [count, setCount] = useState(0);
  // const [renderCount, setRenderCount] = useState(1);
  const countRef = useRef(0);

  // 1. 렌더링 발생: Rendering_Count 컴포넌트가 화면에 그려짐
  // 2. useEffect 실행: 의존성 배열(dependency array)이 없으므로,
  // 렌더링이 끝날 때마다 useEffect 내부의 코드가 실행

  // 3. 상태 업데이트: useEffect 안에서 setRenderCount를 호출하여 상태를 변경
  // 4. 재렌더링 트리거: useEffect 안에서 setRenderCount를 호출하여 상태를 변경
  // 5. 무한 반복: 다시 렌더링이 되었으니 2번(useEffect 실행)으로 돌아가고, 또 상태를 바꾸고, 또 렌더링을 하는 과정이 멈추지 않고 반복
  // -> 무한 루프에 빠지게 됨
  // ★★★★★ [useRef] 사용하기 ★★★★★

  // 컴포넌트가 총 몇 번 렌더링이 되었는지 카운트
  useEffect(() => {
    console.log("렌더링");
    // setRenderCount(renderCount + 1);
    // setCount(count + 1) -> 무한 루프
    // state가 변경되면 컴포넌트 함수가 다시 호출된다 -> useEffect 실행 -> 호출 반복
    countRef.current = countRef.current + 1; // 리렌더링 안된다!

    console.log("countRef.current = " + countRef.current);
  });

  return (
    <div>
      <p>
        Count: {count} / countRef.current = {countRef.current} <br />
        <button
          onClick={() => {
            setCount(count + 1);
          }}
        >
          Up
        </button>
      </p>
    </div>
  );
};

export default Rendering_Count;
