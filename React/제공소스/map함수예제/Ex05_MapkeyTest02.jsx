import React, { useState } from 'react';

const Ex05_MapkeyTest02 = () => {
    console.log("MapkeyTest02 call..... - rendering....")
    //useState에 의해 관리되는 변수의 상태가 변경되면 화면을 다시 그린다.
    // - rerendering된다. --> 컴포넌트 = 함수가 다시 호출된다..(함수안에 있는 모든 변수는초기화!!!)

    //inputValue는 text박스에 값이 변경되면 setInputValue를 이용해서 값을 수정한다. 
    const [inputValue, setInputValue] = useState('');

    const [list, setList] = useState([
        {id:1, value:"짬뽕"},
        {id:2, value:"짜짱"},
    ]);

    const inputValueChange = (e)=>{
          setInputValue( e.target.value)
    }
    const addList = ()=>{
        setList((list)=>[
             {id: list.length + 1, value: inputValue}, 
             ...list
             ]);
        setInputValue("");
    }
    return (
        <>
          <input type='text' onChange={inputValueChange} value={inputValue}/>  
          <button onClick={addList}>추가</button>
          <ul>
            {
                list.map((menu,i)=>{
                    return(   
                    <li key={menu.id}>{menu.value}</li>
                    )
            })
            }
          </ul>
        </>
    );
};

export default Ex05_MapkeyTest02;