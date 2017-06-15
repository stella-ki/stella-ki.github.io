<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정</title>
</head>

<body>
<%
	String item_numstr = request.getParameter("item_num");
	int item_num = Integer.parseInt(item_numstr);
	BoardItemDAO dao = new BoardItemDAO();
	BoardItemDTO dto = dao.getBoardItem(item_num);
	request.setAttribute("boardItem", dto);
	System.out.println(dto);
%>
<form action="./test.boardInfo" >

<jsp:include page="./boardItemTable.jsp">
	<jsp:param value="modify" name="action"/>
</jsp:include>

<input type = "hidden" name = "action" id = "action" value = "MODIFYBOARDITEM"> 
<input type = "submit" value = "저장">

</form>

<input type="button" value="취소" onClick="javascript:self.location='../user/welcome.jsp';">

</body>
</html>