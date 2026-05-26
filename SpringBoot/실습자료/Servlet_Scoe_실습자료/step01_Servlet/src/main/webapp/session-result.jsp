<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>session정보 조회하기 </h3>
<h4>
아이디 :<%=session.getAttribute("id") %> <br>
취미 :<%=session.getAttribute("hobbies") %> <br>
</h4>
</body>
</html>