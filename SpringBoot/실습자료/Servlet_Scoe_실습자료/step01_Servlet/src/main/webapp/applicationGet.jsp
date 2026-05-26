<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> ServletContext = application 정보 확인하기 </h3>
메시지 :<%=application.getAttribute("message") %> <br>
아이디 :<%=application.getAttribute("id") %>

<hr>
<h3> HttpSession = session  정보 확인하기 </h3>
아이디 :<%=session.getAttribute("id") %> <br>
취미 :<%=session.getAttribute("hobbies") %> <br>

<h3> HttpServletRequest = request  정보 확인하기 </h3>
info : <%=request.getAttribute("info") %>
</body>
</html>





