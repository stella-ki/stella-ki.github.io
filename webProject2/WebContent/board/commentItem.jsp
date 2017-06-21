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
<title>BoardItem</title>
</head>
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
	
	CommentDAO cdao = new CommentDAO();
	List<CommentDTO> cdtos = null;
			
	if(action.equals("show") || action.equals("modify")){
		item_num = dto.getItem_num();
		create_date = dto.getCreate_date();
		last_Update_Date = dto.getLast_Update_Date();
		delete_date = (dto.getDelete_date()==null)?"-":dto.getDelete_date();
		auther = dto.getAuther();
		title = dto.getTitle();
		content = dto.getContent();
		is_can_delete = dto.getIs_can_delete();
		
		if(action.equals("show")){
			cdtos = cdao.getComments(item_num);
		}
		
	}else if(action.equals("create")){
		create_date = "170615";
		last_Update_Date = "170615";
		auther = request.getParameter("name");
		
	}else{
		
	}
	
	
%>
<body>
<br>
<table border="1" cellspacing="0" cellpadding="0" class="list_table" width="500">
<%
if(action.equals("show") && cdtos.size() != 0){
	for(CommentDTO temp : cdtos){
%>
<tr>
	<td>이름</td><td><%=temp.getAuthor() %></td>
	<td>작성일</td>
	<td colspan="2"><%=temp.getCreate_date() %></td>
	<td><input type = "submit" value = "수정"></td>
	</tr>
	<tr>
	<td colspan="6"><%=temp.getContent() %></td>
</tr>
<%
	}
}
%>
</table>

<br>
<form action="./cmt.commentInfo">
<input type = "hidden" name = "action" id = "action" value = "SAVEBAORDITEM" >
<input type = "hidden" name = "auther" id = "auther" value = "<%=auther %>" >
<input type = "hidden" name = "board_item_num" id = board_item_num value = "<%=item_num %>" >
<table border="1" cellspacing="0" cellpadding="0" class="list_table" width="500">
<tr>
	<td>이름</td>
	<td colspan="4"><%=userdto.getName() %></td>
	<td><input type = "submit" value = "저장"></td>
	</tr>
	<tr>
	<td colspan="6">
	<textarea name = "content" id = "content" cols=65 rows=3></textarea>
	</td>
</tr>
</table>
</form>
</body>
</html>