<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script type="text/javascript">
  $(function(){
	 // alert("되니?");
	  
	  $("#btn").click(function(){
		  $.ajax({
      		url:"${pageContext.request.contextPath}/ajax.do",
      		type:"post",
      		data: {email:"jang" , pwd : "1234" } ,
      		dataType:"json", //text |xml | html | json
      		success: function(result){
      			console.log(result);
      		},
      		error : function(jqXHR, textStatus, errorThrown){
      			alert("문제 발생 : " + jqXHR.status)
      			
      		}
      		
      	}); 
	  });//btnEnd
	  
	  
     $("#btn2").click(function(){
		  let jsonData ={id:"jang" , name : "희정",   age:20 , addr:"서울" }
		  
		  $.ajax({
    		url:"${pageContext.request.contextPath}/ajax2.do",
    		type:"post",
    		data:jsonData ,
    		dataType:"text", //text |xml | html | json
    		success: function(result, textStatus, xhr){//data , 
    			console.log(result);
    			console.log(textStatus);
    			console.log(xhr);
    			console.log(xhr.status);
    			
    		},
    		error : function(jqXHR, textStatus, errorThrown){
    			console.log("문제 발생 : " + jqXHR.status)
    			
    		}
    		
    	}); 
	  });//btnEnd
	  
	  
	  /**
	    JSON형태로 데이터 보내기 
	  */
	  $("#btn3").click(function(){
		  
		  let jsonData ={id:"jang" , name : "희정",   age:12 , addr:"서울" }
		  
		  $.ajax({
      		url:"${pageContext.request.contextPath}/ajax3.do",
      		type:"post",
      		contentType:"application/json;charset=UTF-8",
      		data:JSON.stringify(jsonData) ,
      		dataType:"json", //text |xml | html | json
      		success: function(result){
      			 console.log(result);
      			 
      			$.each(result, function(index, item){
      				console.log(item);
      			})
      			
      		},
      		error : function(jqXHR, textStatus, errorThrown){
      			//alert("문제 발생 : " + jqXHR.status)
      			console.log(jqXHR)
      			console.log(jqXHR.responseJSON.msg)
      			console.log(jqXHR.responseJSON.status)
      			
      			
      			//console.log(jqXHR.responseText) //미성년자....
      			//console.log(jqXHR.status)//600
      			console.log(textStatus)
      			console.log(errorThrown)
      		}
      		
      	}); 
	  });//btnEnd
	  
	  
	  
  })//readyEnd

</script>
</head>
<body>
<h2>AjaxTest</h2>
<button id="btn">클릭</button>
<button id="btn2">클릭</button>
<button id="btn3">클릭</button>

</body>
</html>



