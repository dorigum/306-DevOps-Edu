// 여기는 자바스크립영 영역입니다. 

/*여러줄 또는 부분 주석입니다.*/

//콘솔에 출력
console.log("어디에 출력되나요?");

//브라우져에 출력
document.write("<h1>난 어디에 나오니?</h1>")

//함수작성
function test(th){ //th는 this전달받는다.
//alert("되니?");// 메시지 창
//h2에 css 적용

// element를 찾는 방법 - id로 찾기
    //document.getElementById("h2").style.backgroundColor="red";
    //document.getElementById("h2").style.color="white";

    th.style.backgroundColor="red";
    th.style.color="white";
}

