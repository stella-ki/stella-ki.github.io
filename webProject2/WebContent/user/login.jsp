<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../util/ajax.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
<script>
function doLogin(){
	xmlHttpObj = getHttpXmlObj(); 
	var userid = document.getElementById("id").value;
	var userpwd = document.getElementById("pwd").value;
   	//통신할 jsp명입니다.
   	var url = "./userLogin.login";
   	var method = "get";
   	var params = "?action=LOGIN&id=" + encodeURIComponent(userid) + "&pwd=" + encodeURIComponent(userpwd);
   	var sync = true;
   	
   	xmlHttpObj.onreadystatechange=function(){
   		if (xmlHttpObj.readyState==4 && xmlHttpObj.status==200){
   			var result = decodeURIComponent(xmlHttpObj.responseText);
   			if(result == "success"){
       			location.href = "../user/welcome.jsp"
       		}else{
       			document.getElementById("resultDiv").innerHTML=result;
       		}
       		
        }
    }
    //open으로 위의 기술한 jsp명과 통신을 연결합니다. 
   	xmlHttpObj.open(method, url+params, sync);
   	//xmlHttpObj.open(method, url, sync);
   	/* xmlHttpObj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   	xmlHttpObj.setRequestHeader("Content-length", params.length);
   	xmlHttpObj.setRequestHeader("Connection", "close"); */

   	//실제로 값을 보냅니다.
//   	alert(params);
   	xmlHttpObj.send(params);
}
</script>
<table border="1" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="2"> 로그인 </td> 
	</tr>
	<tr>
		<td> ID : </td>
		<td><input type="text" name="id" id="id"/></td>
	</tr>
	<tr>
		<td> PassWord : </td>
		<td><input type="password" name="pwd" id="pwd"/></td>
	</tr>
	<tr>
		<td><input type="button" value="Login" onclick="doLogin();"/></td>
		<td>
		<form action="../user/register.jsp">
		<input type="submit" value = "register">
		</form>
		
		</td>
	</tr>
</table>
<div id="resultDiv"></div>
</body>
</html>