import React from 'react'
import { useState } from 'react'

function Ex03_Form() {
    const [profile, setProfile] = useState({name: "도연", age: 0, email: "koo@gmail.com", addr: ""});
    const inputUpdate = (e) => {
        console.log(e.target.name)
        console.log(e.target.value)

        const {name, value} = e.target;

        setProfile({...profile, [name] : value})
        // console.log(profile)

        // const obj = {a: 10, b: 20, ["f"]: "도연"} // a값이 덮어써짐
        // console.log(obj)
    }

  return (
    <>
        <h1>폼 입력 값</h1>
        이름: <input type="text" name="name" value={profile.name} onChange={inputUpdate}/><br/>
        나이: <input type="text" name="age" value={profile.age} onChange={inputUpdate}/><br/>
        이메일: <input type="text" name="email" value={profile.email} onChange={inputUpdate}/><br/>
        주소: <input type="text" name="addr" value={profile.addr} onChange={inputUpdate}/><br/>
        
        <hr/>
        <h3>입력 정보</h3>
        이름: {profile.name} / 나이: {profile.age} / 이메일: {profile.email} / 주소: {profile.addr}
    </>
  )
}

export default Ex03_Form