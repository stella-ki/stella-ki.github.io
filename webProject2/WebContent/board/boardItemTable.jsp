<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.user.UserDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	BoardItemDTO dto = (BoardItemDTO)request.getAttribute("boardItem");		
	int item_num;
	String create_date = "";
	String delete_date = "-";
	String last_Update_Date = "";
	String auther = "";
	String title = "";
	String content = "";
	int is_can_delete;
	
	if(dto != null){
		create_date = dto.getCreate_date();
		last_Update_Date = dto.getLast_Update_Date();
		delete_date = (dto.getDelete_date()==null)?"-":dto.getDelete_date();
		auther = dto.getAuther();
		title = dto.getTitle();
		content = dto.getContent();
	}else{
		create_date = "170615";
		last_Update_Date = "170615";
		auther = request.getParameter("id");
		
	}
%>

<table border="1" cellspacing="0" cellpadding="0">
<tr>
	<td>작성일</td>
	<td><%=create_date %></td>
	<td>마지막 수정일</td>
	<td><%=last_Update_Date%></td>
	<td>삭제일</td>
	<td><%=delete_date%></td>
</tr>
<tr>
	<td>작성자</td>
	<td><%=auther %></td>
	<td>제목</td>
	<td colspan="3"><input type = "text" value = "<%= title%>" <%=(dto != null)?"readonly=\"readonly\"":"" %> > </td>
</tr>
<tr>
	<td colspan="6"><input type = "text" value = "<%=content %>" <%=(dto != null)?"readonly=\"readonly\"":"" %>></td>
</tr>
</table>
</body>
</html>