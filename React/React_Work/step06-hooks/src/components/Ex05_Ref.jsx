import React from "react";
import { useState } from "react";
import { useRef } from "react";

function Ex05_Ref() {
  console.log("Ex05_Ref call");

  // 값이 변경될 때마다 rerendering 된다.
  const [count, setCount] = useState(0); // count

  // 값이 변경되어도 rerendering 되지 않는다.
  const countRef = useRef(0); // countRef.current

  return (
    <div>
      <h2>useState vs useRef 차이</h2>
      <p>
        useState: {count}
        <button onClick={() => setCount(count + 1)}>state UP</button>
      </p>

      <hr />
      <p>
        useRef: {countRef.current}
        <button onClick={() => (countRef.current = countRef.current + 1)}>
          ref UP
        </button>
      </p>
    </div>
  );
}

export default Ex05_Ref;
