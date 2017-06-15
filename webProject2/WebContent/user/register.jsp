<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file = "../util/ajax.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
</head>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<%!
String idStr = "";
String pwStr = "";
String nameStr = "";
String ageStr = "";
%>
<%
	String actionStr = request.getParameter("action");
	if(actionStr!=null){
		idStr = request.getParameter("id");
		pwStr = request.getParameter("pwd");
		nameStr = request.getParameter("name");
		ageStr = request.getParameter("age");
	}
%> 
<script>
function doCheck(){
	xmlHttpObj = getHttpXmlObj(); 
	var userid = document.getElementById("id").value;	
	if(userid == ""){
		alert("userid를 입력하세용");
		return;
	}
	
   	var url = "./welcome.register";
   	var method = "get";
   	var params = "?action=CHECK&id=" + encodeURIComponent(userid);
   	var sync = true;
   	
   	xmlHttpObj.onreadystatechange=function(){   		
   		if (xmlHttpObj.readyState==4 && xmlHttpObj.status==200){   		
   			var result = decodeURIComponent(xmlHttpObj.responseText);
   			if(result == "false"){
   				alert(userid + "는 사용할 수 있습니다."); 
   			}else{
   				alert(userid + "는 사용할 수 없습니다."); 
   			}   			
        }
    }
   	
   	xmlHttpObj.open(method, url+params, sync);
   	xmlHttpObj.send(params);	
}
</script>
<body>
<form action="./welcome.register" method = "get">
아이디 : <input type = "text" name = "id" id = "id" value="<%=idStr%>"><input type = "button" value="중복체크" onclick="doCheck();"><br/>
이름 : <input type = "text" name = "name" id = "name" value="<%=nameStr%>"><br/>
비밀번호 : <input type = "text" name = "pwd" id = "pwd" value="<%=pwStr%>"><br/>
나이 : <input type = "text" name = "age" id = "age" value="<%=ageStr%>"><br/>
<input type="hidden" name = "action" id = "action" value="REGISTER">
<input type="submit" value="회원가입"/>
</form>


</body>
</html>