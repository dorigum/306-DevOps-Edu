import "./App.css";

function App() {
  // JavaScript 코드 작성
  const message = "궁서체 썼음";

  const student = {
    stno: 10,
    name: "도연",
    addr: "오리",
    age: 20
  };

  console.log(student);

  // CSS 적용
  const sccStyle = {
    color: "lightgray",
    backgroundColor: "lightblue",
    border: "double 5px lightgreen"
  }

  const fun1 = function() {
    console.log("클릭1 버튼 눌렀어요.");
  }

  // 컴포넌트 내부의 return 전에 JSX 문법 사용 가능
  const name = <h5>춘식이🐈</h5>
  const arr = [1, 2, 3, 4, 5];

  return (
    <>
      <h1 style={sccStyle}>JSX 문법 공부하기</h1>
      <h3 id="b">메시지: {message} </h3>
      {/* {student} 는 jsx 문법에서 속성 없이 출력할 수 없음! */}
      <h3 className="test">학생 정보: {student.name} / {student.addr} </h3>
      <h3 className="test">true: {true} / {null} / {undefined} </h3>
      <h4 style={{ color: "lightblue", backgroundColor: "lightyellow" }}>CSS 적용하기</h4>
      <a href='#'>Click 1</a><br/>
      <a href='#'>Click 2</a>
      <hr/>

      <button onClick={fun1}>클릭 1</button>
      <button onClick={() => {
        console.log("클릭2 버튼 눌렀어요.");
      }}>클릭 2</button>
      {name}
    <hr/>
    {student.age > 18 ?
    <h3 style={{color: "blue"}}>{student.name}님은 성인입니다.</h3> :
    <h3 style={{color: "red"}}>{student.name}님은 미성년자입니다.</h3>
}

    {/* 나이가 18이상이면 "모든 서비스를 이용할 수 있어요" */}
    {student.age > 18 && <h4>모든 서비스를 이용할 수 있어요👍</h4>}
    <h5>{arr}</h5>
    </>
  );
}

export default App;
