<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h2>AjaxTest</h2>
	<button id="btn">클릭</button>
	<button id="btn2">클릭</button>
	<button id="btn3">클릭</button>

	<script type="text/javascript">
  document.getElementById("btn").addEventListener("click", function () {
	  fetch("${pageContext.request.contextPath}/ajax.do", {
	    method: "POST",
	    headers: {
	      "Content-Type": "application/x-www-form-urlencoded"
	    },
	    body: new URLSearchParams({
	      email: "jang",
	      pwd: "1234"
	    })
	  })
	    .then(response => {
	      if (!response.ok) {
	        throw new Error("HTTP 상태 코드: " + response.status);
	      }
	      return response.json();
	    })
	    .then(result => {
	      console.log(result);
	    })
	    .catch(error => {
	      alert("문제 발생: " + error.message);
	    });
	});
  
  
  // -------------------------------------------------
  document.getElementById("btn2").addEventListener("click", function () {
	  const jsonData = {
	    id: "jang",
	    name: "희정",
	    age: 20,
	    addr: "서울"
	  };
	
	  fetch("${pageContext.request.contextPath}/ajax2.do", {
	    method: "POST",
	    headers: {
	      "Content-Type": "application/json"
	    },
	    body: JSON.stringify(jsonData) // {"id": "jang", }
	  })
	    .then(response => {
	      console.log("status:", response.status);
	      if (!response.ok) {
	        throw new Error("HTTP 오류: " + response.status);
	      }
	      return response.text(); // 서버 응답이 text일 경우
	    })
	    .then(result => {
	      console.log("결과:", result);
	    })
	    .catch(error => {
	      console.log("문제 발생:", error.message);
	    });
	});

  // -------------------------------------------------
  // JSON 형태로 데이터 보내기
  document.getElementById("btn3").addEventListener("click", function () {
	  const jsonData = {
	    id: "jang",
	    name: "희정",
	    age: 25,
	    addr: "서울"
	  };

	  fetch("${pageContext.request.contextPath}/ajax3.do", {
	    method: "POST",
	    headers: {
	      "Content-Type": "application/json;charset=UTF-8"
	    },
	    body: JSON.stringify(jsonData)
	  })
	    .then(async response => {
	      if (!response.ok) {
	        // 응답이 에러일 때도 JSON을 받을 수 있으므로 JSON 파싱 시도
	        const errorData = await response.json();
	        console.log("문제 발생: ", errorData);
	        console.log(errorData.msg);
	        console.log(errorData.status);
	        throw new Error(`HTTP ${response.status}`);
	      }
	      return response.json(); // 정상 응답일 경우
	    })
	    .then(result => {
	      console.log(result);
	      result.forEach((item, index) => {
	        console.log(item);
	      });
	    })
	    .catch(error => {
	      console.log("에러 메시지:", error.message);
	    });
	});
</script>

</body>
</html>