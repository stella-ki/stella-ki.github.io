<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.board.*" %>
 <%@ page import="java.util.*" %>
  <%@ page import="com.user.UserDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
</head>
<link href="./css/table.css" rel="stylesheet" type="text/css">
<body>

<% 
	BoardItemDAO dao = new BoardItemDAO();
	List<BoardItemDTO> list = dao.getBoardItemList();
	UserDTO dto = (UserDTO)session.getAttribute("userDTO");
%>

<table border="1" cellspacing="0" cellpadding="0" class="list_table">
	<tr>
	<td></td>
	<td>생성날짜</td>
	<td>삭제날짜</td>
	<td>수정날짜</td>
	<td>작성자</td>
	<td>제목</td>
<%	if(dto!=null &&
		dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
	%>	
<!-- 	<td></td> -->
	<td>삭제</td>
<% } %>
	</tr>
<% 
	for(BoardItemDTO item : list){
%>
	<tr>
	<td>
	<%=item.getItem_num() %>
	</td>
	<td>
	<%=item.getCreate_date() %>
	</td>
	<td>
	<%=(item.getDelete_date()==null?"-":item.getDelete_date()) %>
	</td>
	<td>
	<%=item.getLast_Update_Date() %>
	</td>
	<td>
	<%=item.getAuther() %>
	</td>
	<td>
	<a href="../board/boardItem.jsp?item_num=<%=item.getItem_num()%>" target="_self"><%=item.getTitle() %></a>
	</td>
	
<%	if(dto!=null &&
		dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
	%>	
<%-- 	<td>
	<%=(item.getIs_can_delete()==0?"삭제불가":"삭제가능") %>
	</td> --%>
	<td>
	<%
		if(item.getIs_can_delete()!=0){
			%>
			<input type="checkbox" name = "delete_<%=item.getItem_num() %>" id = "delete_<%=item.getItem_num() %>">
			<%
		}
	%>
	</td>
<% } %>	
	</tr>
<% 
}
%>	
</table>
<%	if(dto!=null &&
		dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
	%>
<input type="hidden" name = "action" id = "action" value="delete">
<input type="hidden" name = "item_num" id = "item_num"<%--  value="<%=item.getItem_num() %>" --%>>
<input type = "submit" value = "삭제">

<form action="../board/write.jsp" method = "get" >
<input type="hidden" name = "name" id = "name" value="<%=dto.getName()%>">
<input type = "submit" value = "글쓰기">
</form>

<% } %>


</body>
</html>