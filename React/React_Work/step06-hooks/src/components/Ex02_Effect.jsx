import React from 'react'
import { useState } from 'react'
import Ex02_Timer from './Ex02_Timer'

function Ex02_Effect() {
    const [state, setState] = useState(false);
  return (
    <div>
        <h3>Effect - 자원 정리하기</h3>
        {state && <Ex02_Timer/>}
        <button onClick={() => setState(!state)}>Toggle Button</button>
    </div>
  )
}

export default Ex02_Effect