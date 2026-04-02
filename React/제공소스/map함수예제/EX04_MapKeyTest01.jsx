import React, { useState } from 'react';

const Ex04_MapKeyTest01 = () => {

   // const menu = ["짬뽕","짜장"];
   const [list , setList] = useState( ["짬뽕","짜장"] );
   const [inputValue, setInputValue ] = useState("")

   //추가
    const addList = ()=>{
       // console.log("inputValue" + inputValue);

        // text박스의 값을 조회
        setList((preList)=>[inputValue, ...preList]);

        //추가후에 text박스 지우기
        setInputValue("");
    }

    const inputChangeValue = (e)=>{
        setInputValue( e.target.value )
    }

    return (
        <div>
            <input type='text'  onChange={inputChangeValue} value={inputValue}/>
            <button onClick={addList}>추가</button>
            <ul>
               {
                list.map((menu, index)=>{
                    return (<li key={menu}>{menu}</li>)
                })
               }
                
            </ul>
        </div>
    );
};

export default Ex04_MapKeyTest01;