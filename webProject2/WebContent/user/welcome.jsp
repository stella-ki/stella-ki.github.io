<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.user.UserDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>welcome</title>
</head>
<body>
<%
	UserDTO dto = (UserDTO)session.getAttribute("userDTO");
	if(dto!=null){
%>

<table border="1" cellspacing="0" cellpadding="0">
	<tr>
		<td>
		<%=dto.getName() %>님 환영합니다.
		</td>
	</tr>
	<tr>
		<td>
		<form action= "./user/logout.login" method="get">
		<input type="hidden" name = "action" id = "action" value = "LOGOUT">
		<input type="submit" value = "logout"> 
		</form>
		</td>
	</tr>
	<tr>
		<td>
		<form action= "../user/modifyInfo.jsp" method="get">
		<input type="submit" value = "회원정보수정"> 
		</form>
		</td>
	</tr>
	<tr>
		<td>
		<form action= "./user.info" method="get">
		<input type="hidden" name = "action" id = "action" value = "REMOVEACCOUNT">
		<input type="hidden" name = "id" id = "id" value = "<%=dto.getId() %>">
		<input type="submit" value = "회원탈퇴"> 
		</form>
		</td>
	</tr>
	
<%
		if(dto.getIsAdmin() == UserDTO.ADMIN){
			%>	
	<tr>
		<td>
			<form action= "./userManage.jsp" method="get">
			<input type="hidden" name = "action" id = "action" value = "MANAGEUSERS">
			<input type="submit" value = "회원관리">
			</form>
		</td>
	</tr>
	<%
		}
%>
</table>
<jsp:include page="../board/board.jsp"></jsp:include>

<%
	}else{
%>
<jsp:forward page="../user/login.jsp"></jsp:forward>
<%
	}
%>

</body>
</html>