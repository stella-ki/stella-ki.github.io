<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

function doSearch(){
	xmlHttpObj = getHttpXmlObj(); 
	var searchKeyword = document.getElementById("searchKeyword").value;
	var combobox = document.getElementById("combobox");
    var selectedValue = combobox.options[combobox.selectedIndex].value;
   	//통신할 jsp명입니다.
	var url = "./test.boardInfo";
   	var method = "get";
   	var params = "?action=BOARDITEMSEARCH&searchKeyword=" + encodeURIComponent(searchKeyword) + "&selectedValue=" + encodeURIComponent(selectedValue);
   	var sync = true;
   	xmlHttpObj.onreadystatechange=function(){
   		if (xmlHttpObj.readyState==4 && xmlHttpObj.status==200){
   			var result = decodeURIComponent(xmlHttpObj.responseText);
   			document.getElementById("resultDiv").innerHTML=result;    
        }
    }
    //open으로 위의 기술한 jsp명과 통신을 연결합니다. 
  	xmlHttpObj.open(method, url+params, sync);
   	//xmlHttpObj.open(method, url, sync);
   	/* xmlHttpObj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   	xmlHttpObj.setRequestHeader("Content-length", params.length);
   	xmlHttpObj.setRequestHeader("Connection", "close"); */

   	//실제로 값을 보냅니다.
   	xmlHttpObj.send(params);
}
</script>
<link href="./css/table.css" rel="stylesheet" type="text/css">
<body>

<% 
	BoardItemDAO dao = new BoardItemDAO();
	List<BoardItemDTO> list = dao.getBoardItemList();
	request.setAttribute("boardDTOList", list);
%>
<table cellspacing="0" cellpadding="0" class="list_table" width="500">
<tr>
<td>검색</td>
<td><input type = "text" name = "searchKeyword" id = "searchKeyword" ></td>
<td><select name="combobox"  id="combobox" >
  <option value="제목">제목</option>
  <option value="내용">내용</option>
  <option value="작성자">작성자</option>
 </select></td>
 <td><input type="button" value="검색" onclick="doSearch();"/></td>
</tr>
</table>
<div id="resultDiv">
<jsp:include page="boardlist.jsp"/>
</div>

</body>
</html>