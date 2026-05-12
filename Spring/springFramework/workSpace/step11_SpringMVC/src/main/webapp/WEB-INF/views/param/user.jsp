<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>UserDTO의 정보</h3>
	아이디: ${userDTO.id}<br><!-- userDTO.getId() 메소드 호출 -->
	이름: ${userDTO.name}<br>
	나이: ${userDTO.age}<br>
	주소: ${userDTO.addr}<br>

	<hr>
	<h3>UserDTO의 정보 - @ModelAttribute("dto")</h3>
	아이디: ${userDTO.id}<br><!-- userDTO.getId() 메소드 호출 -->
	이름: ${userDTO.name}<br>
	나이: ${userDTO.age}<br>
	주소: ${userDTO.addr}	<br>
	msg: ${msg}<br>
	hobbys: ${hobbysList}
</body>
</html>