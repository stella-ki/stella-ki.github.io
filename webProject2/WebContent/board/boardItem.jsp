<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.user.UserDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BoardItem</title>
</head>

<body>
<%
	UserDTO userdto = (UserDTO)session.getAttribute("userDTO");
	String item_numstr = request.getParameter("item_num");
	int item_num = Integer.parseInt(item_numstr);
	BoardItemDAO dao = new BoardItemDAO();
	BoardItemDTO dto = dao.getBoardItem(item_num);
	request.setAttribute("boardItem", dto);
%>

<jsp:include page="./boardItemTable.jsp">
	<jsp:param value="show" name="action"/>
</jsp:include>

<input type="button" value="목록으로 가기" onClick="javascript:self.location='../user/welcome.jsp';">

<form action="./modify.jsp" >
<% if(userdto.getName().equals(dto.getAuther()) || userdto.getIsBoardAdmin()  == UserDTO.BOARDADMIN){ %>
	<input type = "hidden" name = "action" id = "action" value = "MODIFYBOARDITEM">
	<input type = "hidden" name = "item_num" id = "item_num" value = "<%=item_num%>">
	<input type = "submit" value = "수정">
<% } %>
</form>
<!-- <form action="" >
<input type = "hidden" name = "" id = "" value = ""> 
<input type = "submit" value = "저장">
</form> -->
</body>
</html>