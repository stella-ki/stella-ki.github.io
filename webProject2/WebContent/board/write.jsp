<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
</head>

<body>
<%
	String name = request.getParameter("name");
	System.out.println("name: " + name);
%>
<form action="./test.boardInfo" >
<jsp:include page="./boardItemTable.jsp">
<jsp:param value="create" name="action"/>
<jsp:param value="<%=name %>" name="name"/>
</jsp:include>


<input type = "hidden" name = "action" id = "action" value = "SAVEBAORDITEM"> 
<input type = "submit" value = "저장">
</form>
<input type="button" value="취소" onClick="javascript:self.location='../user/welcome.jsp';">

</body>
</html>