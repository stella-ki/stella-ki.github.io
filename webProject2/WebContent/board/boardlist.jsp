<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.board.*" %>
 <%@ page import="java.util.*" %>
  <%@ page import="com.user.UserDTO" %>
<%@ include file = "../util/ajax.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
</head>
<script type="text/javascript">
</script>
<link href="./css/table.css" rel="stylesheet" type="text/css">
<body>
<% 
	List<BoardItemDTO> list = (List<BoardItemDTO>)request.getAttribute("boardDTOList");
	UserDTO dto = (UserDTO)session.getAttribute("userDTO");
%>

<%	if(dto!=null &&
		dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
	%>
<form action="./test.boardInfo" method = "get" >
<% } %>
<table border="1" cellspacing="0" cellpadding="0" class="list_table" width="500">
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
		if((dto.getIsBoardAdmin() != UserDTO.BOARDADMIN && item.getDelete_date()==null)||
				dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
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
	<%	if(dto!=null &&
			dto.getIsBoardAdmin() == UserDTO.BOARDADMIN &&
				item.getIs_can_delete()!=0 && item.getDelete_date()==null){
				%>
				<input type="checkbox" name = "delete_<%=item.getItem_num() %>" id = "delete_<%=item.getItem_num() %>">
				<%	
		}
	} %>	
	</td>
	</tr>
<% 
}
%>	
</table>

<%	if(dto!=null &&
		dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
	%>
<input type="hidden" name = "action" id = "action" value="DELETEBOARDITEM">
<input type="hidden" name = "item_num" id = "item_num"<%--  value="<%=item.getItem_num() %>" --%>>
<input type = "submit" value = "삭제">
</form>

<form action="../board/write.jsp" method = "get" >
<input type="hidden" name = "name" id = "name" value="<%=dto.getName()%>">
<input type = "submit" value = "글쓰기">
</form>

<% } %> 


</body>
</html>