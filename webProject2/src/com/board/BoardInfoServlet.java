package com.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.UserDTO;

public class BoardInfoServlet  extends HttpServlet{
	
	public static final String SAVEBAORDITEM = "SAVEBAORDITEM";
	public static final String MODIFYBOARDITEM = "MODIFYBOARDITEM";
	public static final String DELETEBOARDITEM = "DELETEBOARDITEM";
	public static final String BOARDITEMSEARCH = "BOARDITEMSEARCH";
	
	
	
	
	private BoardItemDAO dao = new BoardItemDAO();
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resq) throws IOException, ServletException{
		RequestDispatcher disPatcher = req.getRequestDispatcher("../dispatch/error.jsp");
		
		String action = req.getParameter("action");
		if(action.equals(SAVEBAORDITEM)){
			String auther = req.getParameter("auther");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String[] is_can_deletestr = req.getParameterValues("is_can_delete");
			int is_can_delete = 0;
			if(is_can_deletestr!=null){
				is_can_delete = 1;
			}
			BoardItemDTO boardItemDTO = new BoardItemDTO();
			boardItemDTO.setAuther(auther);
			boardItemDTO.setTitle(title);
			boardItemDTO.setContent(content);
			boardItemDTO.setIs_can_delete(is_can_delete);
			
			boolean result = dao.insertBoardItem(boardItemDTO);
			if(result){	
				req.setAttribute("error_message", "글쓰기 성공");
				req.setAttribute("go_page", "../user/welcome.jsp");
				req.setAttribute("url_pattern", "");
				disPatcher.forward(req, resq);				
			}else{
				req.setAttribute("error_message", "글쓰기 실패");
				req.setAttribute("go_page", "../user/welcome.jsp");
				req.setAttribute("url_pattern", "");
			}
			System.out.println(boardItemDTO);
		}else if(action.equals(MODIFYBOARDITEM)){
			int item_num = Integer.valueOf(req.getParameter("item_num"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String[] is_can_deletestr = req.getParameterValues("is_can_delete");
			int is_can_delete = 0;
			if(is_can_deletestr!=null){
				is_can_delete = 1;
			}
			BoardItemDTO boardItemDTO = new BoardItemDTO();
			boardItemDTO.setItem_num(item_num);			
			boardItemDTO.setTitle(title);
			boardItemDTO.setContent(content);
			boardItemDTO.setIs_can_delete(is_can_delete);
			
			boolean result = dao.modifyBoardItem(boardItemDTO);
			if(result){	
				req.setAttribute("error_message", "글수정 성공");
				req.setAttribute("go_page", "../user/welcome.jsp");
				req.setAttribute("url_pattern", "");
				disPatcher.forward(req, resq);				
			}else{
				req.setAttribute("error_message", "글수정 실패");
				req.setAttribute("go_page", "../user/welcome.jsp");
				req.setAttribute("url_pattern", "");
			}
			System.out.println(boardItemDTO);
			
			
		}else if(action.equals(DELETEBOARDITEM)){
			Map map = req.getParameterMap();
			Iterator<String> key = map.keySet().iterator();
			
			while(key.hasNext()){
				String id = key.next();
				String num = "";
				if(id.startsWith("delete_")){
					num = id.substring("delete_".length());
					dao.deleteBoardItem(Integer.parseInt(num));
				}
			}
			
			req.setAttribute("error_message", "글삭제");
			req.setAttribute("go_page", "../user/welcome.jsp");
			req.setAttribute("url_pattern", "");
			disPatcher.forward(req, resq);	
		}else if(action.equals(BOARDITEMSEARCH)){
			String searchKeyword = req.getParameter("searchKeyword");
			String selectedValue = req.getParameter("selectedValue");
			BoardItemDTO boardItemDTO = new BoardItemDTO();
			if(selectedValue.equals("제목")){
				boardItemDTO.setTitle(searchKeyword);
			}else if(selectedValue.equals("내용")){
				boardItemDTO.setContent(searchKeyword);
			}else if(selectedValue.equals("작성자")){
				boardItemDTO.setAuther(searchKeyword);
			}
			List<BoardItemDTO> result = dao.searchBoardItem(boardItemDTO);
			HttpSession session = req.getSession();
			UserDTO dto = (UserDTO)session.getAttribute("userDTO");
			
			String resultStr = "";
			
			if(dto!=null &&
				dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){			
				resultStr +="<form action=\"./test.boardInfo\" method = \"get\" >";
			} 
			resultStr +="<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class=\"list_table\" width=\"500\">";
			resultStr +="<tr><td></td><td>생성날짜</td><td>삭제날짜</td><td>수정날짜</td><td>작성자</td><td>제목</td>";
			if(dto!=null &&
				dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){			
				resultStr +="<td>삭제</td>";
			} 
			resultStr +="</tr>";
		 
			for(BoardItemDTO item : result){
				if((dto.getIsBoardAdmin() != UserDTO.BOARDADMIN && item.getDelete_date()==null)||
						dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
		
					resultStr +="<tr><td>";
					resultStr += item.getItem_num();
					resultStr +="</td><td>";
					resultStr += item.getCreate_date();
					resultStr +="</td><td>";
					if(item.getDelete_date()==null){
						resultStr +="-";
					}else{
						resultStr +=item.getDelete_date();
					}
					resultStr +="</td><td>";
					resultStr += item.getLast_Update_Date();
					resultStr +="</td><td>";
					resultStr += item.getAuther();
					resultStr +="</td><td><a href=\"../board/boardItem.jsp?item_num="+item.getItem_num()+"\" target=\"_self\">"+item.getTitle()+" </a></td><td>";
					if(dto!=null &&
						dto.getIsBoardAdmin() == UserDTO.BOARDADMIN &&
							item.getIs_can_delete()!=0 && item.getDelete_date()==null){							
						resultStr +="<input type=\"checkbox\" name = \"delete_="+item.getItem_num()+" \" id = \"delete_="+item.getItem_num()+" \">";
							
					}
				} 	
				resultStr +="</td></tr>";			 
			}				
			resultStr +="</table>";

			if(dto!=null &&
				dto.getIsBoardAdmin() == UserDTO.BOARDADMIN){
			
				resultStr +="<input type=\"hidden\" name = \"action\" id = \"action\" value=\"DELETEBOARDITEM\">";
				resultStr +="<input type=\"hidden\" name = \"item_num\" id = \"item_num\">";
				resultStr +="<input type = \"submit\" value = \"삭제\">";
				resultStr +="</form>";

				resultStr +="<form action=\"../board/write.jsp\" method = \"get\" >";
				resultStr +="<input type=\"hidden\" name = \"name\" id = \"name\" value=\"="+dto.getName()+"\">";
				resultStr +="<input type = \"submit\" value = \"글쓰기\">";
				resultStr +="</form>";	
			}  
				
			resq.setContentType("text/html; charset = UTF-8");
			PrintWriter out = resq.getWriter();
			out.println(resultStr);
		}else{
			
		}
		
	}
	
	public void dePost(HttpServletRequest req, HttpServletResponse reqs) throws IOException{
		
	}

}
