<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    //쿠키에 저장된 아이디 있다면 id의 value에 기억된 id 놓기
    String savedId="";
     Cookie cookies []  = request.getCookies();
      if(cookies!=null){
    	  for(Cookie co : cookies){
          	String name = co.getName();
          	if(name.equals("savedId")){ //new Cookie("savedId" , 값)
          		savedId = co.getValue();
          	    break;
          	}  	
          	
          }
      }
    %>

<h2> 로그인하기  - post방식</h2>

<form method="post" action="login">
  ID : <input type="text" name="userId"  value="<%=savedId%>"/><br/>
  PWD : <input type="password" name="userPwd" /></br/>
  NAME : <input type="text" name="userName" /></br/>
  
  <input type="submit" value="로그인" />
</form>
</body>
</html>