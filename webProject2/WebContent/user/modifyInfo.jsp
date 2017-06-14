<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.user.UserDTO" %>
 <%@ include file = "../util/ajax.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원정보수정</title>
</head>
<%
	UserDTO dto = (UserDTO)session.getAttribute("userDTO");
	if(dto!=null){
%>
<body>
<form action="./user.info" method = "get">
아이디 : <input type = "text" name = "id" id = "id" value="<%=dto.getId()%>" readonly="readonly"><br/>
이름 : <input type = "text" name = "name" id = "name" value="<%=dto.getName()%>"><br/>
비밀번호 : <input type = "text" name = "pwd" id = "pwd" value="<%=dto.getPw()%>"><br/>
나이 : <input type = "text" name = "age" id = "age" value="<%=dto.getAge()%>"><br/>
<input type="hidden" name = "action" id = "action" value="MODIFYINFO">
<input type="submit" value="저장"/>
</form>


<%
	}
%>
</body>
</html>