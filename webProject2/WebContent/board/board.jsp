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

<table border="1" cellspacing="0" cellpadding="0">
	<tr>
	<td></td>
	<td>생성날짜</td>
	<td>삭제날짜</td>
	<td>수정날짜</td>
	<td>작성자</td>
	<td>제목</td>
	<td>삭제여부</td>
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
	<td>
	<%=(item.getIs_can_delete()==0?"삭제불가":"삭제가능") %>
	</td>
	</tr>
<% 
}
%>	
</table>
<%	if(dto!=null &&
		dto.getIsAdmin() == UserDTO.ADMIN){
	%>
<form action="../board/write.jsp" method = "get" >
<input type="hidden" value = "name" id = "name" value="<%=dto.getName()%>">
<input type = "submit" value = "글쓰기">
</form>
<% } %>


</body>
</html>