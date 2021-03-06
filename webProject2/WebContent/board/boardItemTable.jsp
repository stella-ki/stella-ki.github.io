<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.user.UserDTO" %>
<%@ page import="com.board.*" %>
<%@ include file = "../util/ajax.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BoardItemTable</title>
</head>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<body>
<%
	UserDTO userdto = (UserDTO)session.getAttribute("userDTO");
	String action = request.getParameter("action");
	BoardItemDTO dto = (BoardItemDTO)request.getAttribute("boardItem");		
	int item_num = 0;
	String create_date = "";
	String delete_date = "-";
	String last_Update_Date = "";
	String auther = "";
	String title = "";
	String content = "";
	int is_can_delete = 0;
				
	if(action.equals("show") || action.equals("modify")){
		item_num = dto.getItem_num();
		create_date = dto.getCreate_date();
		last_Update_Date = dto.getLast_Update_Date();
		delete_date = (dto.getDelete_date()==null)?"-":dto.getDelete_date();
		auther = dto.getAuther();
		title = dto.getTitle();
		content = dto.getContent();
		is_can_delete = dto.getIs_can_delete();
		
	}else if(action.equals("create")){
		create_date = "170615";
		last_Update_Date = "170615";
		auther = request.getParameter("name");
		
	}else{
		
	}
	
	
%>
<input type = "hidden" name = "auther" id = "auther" value = "<%=auther %>" >
<input type = "hidden" name = "item_num" id = "item_num" value = "<%=item_num %>" >

<table border="1" cellspacing="0" cellpadding="0" class="list_table" width="500">
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
	<td>삭제 가능 여부</td>
	<td><input type = "checkbox" name = "is_can_delete" id = "is_can_delete" <%=is_can_delete == 1?"checked=\"checked\"":"" %>  
	 <%=(action.equals("show"))?"onclick=\"f_checkBox(this.checked,'is_can_delete')\"":"" %>> </td>
	 <td colspan="2">
	 
	 <%
	 if(action.equals("show") || action.equals("modify")){		 
	 %> 
	  <input type = "submit" value = "삭제">
	 <%	
	 }else if(action.equals("create")){
	 %> 
	  <input type = "submit" value = "수정">
	 <%		
	 }
	 %>
	
	 
	 
	 </td>
</tr>
<tr>
	<td>제목</td>
	<td colspan="5"><input type = "text" name = "title" id = "title" value = "<%= title%>" <%=(action.equals("show"))?"readonly=\"readonly\"":"" %> > </td>
</tr>
<tr>
	<td colspan="6">
	<textarea name = "content" id = "content" cols=65 rows=10 <%=(action.equals("show"))?"readonly=\"readonly\"":"" %>><%=content %></textarea>
	
</tr>

</table>

</body>
</html>