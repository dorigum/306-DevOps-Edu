<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Cookie정보 조회하자 </h2>
<%
   //java코딩
   Cookie [] cookies = request.getCookies();
   if(cookies==null){
	   out.print("쿠키정보가 없습니다. - JSessionID이제 저장되었어요~");
   }else{
	   out.print("쿠키개수 = " + cookies.length +"<hr>");
	   
	   for(Cookie co : cookies){
		   String name = co.getName();
		   String value = co.getValue();
		   out.print(name +" = " + value +"<br>");
	   }
   }
%>
<hr>
<a href="../cookieTest.jsp">cookieTest.jsp이동</a>

</body>
</html>