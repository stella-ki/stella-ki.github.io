<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.user.*" %>
  <%@ page import="com.user.implement.*" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<body>
<%
UserService us = new UserServiceImpl();
List<UserDTO> userlist = us.getUserLists();
%>
<form action="./userInfo.info" >
<input type = "hidden" name = "action" id = "action" value = "MANAGEUSERS"> 
<table border="1" cellspacing="0" cellpadding="0"  class="list_table">
	<tr>
	<td></td>
	<td>이름</td>
	<td>ID</td>
	<td>Password</td>
	<td>age</td>
	<td>Admin</td>
	<td>board Admin</td>
	<td>삭제</td>
	</tr>
<% 
	for(UserDTO user : userlist){
%>
	<tr>
	<td>
	<input size="1" type = "text" accesskey="" readonly="readonly" value = "<%=user.getUser_num() %>"> </td>
	<td>
	<input size="10" type = "text" name = "<%=user.getId()%>_name" id = "<%=user.getId()%>_name" value = "<%=user.getName()%>"> </td>
	<td>
	<input size="7" type = "text"  readonly="readonly" value = "<%=user.getId()%>"> </td>
	<td>
	<input size="7" type = "text" name = "<%=user.getId()%>_pw" id = "<%=user.getId()%>_pw" value = "<%=user.getPw()%>"> </td>
	<td>
	<input size="7" type = "text" name = "<%=user.getId()%>_age" id = "<%=user.getId()%>_age" value = "<%=user.getAge()%>"> </td>
	<td>
	<input size="7" type = "checkbox"  name = "<%=user.getId()%>_ad" id = "<%=user.getId()%>_ad" <%=user.getIsAdmin() == UserDTO.ADMIN?"checked=\"checked\"":"" %> > </td>
	<td>
	<input size="7" type = "checkbox" name = "<%=user.getId()%>_bad" id = "<%=user.getId()%>_bad" <%=user.getIsBoardAdmin() == UserDTO.ADMIN?"checked=\"checked\"":"" %> > </td>
	</tr>
<% 
}
%>	
</table>

<input type = "submit" value = "저장">
</form>

<form action="./welcome.jsp">
<input type = "submit" value = "정보 페이지로 이동">
</form>

</body>
</html>