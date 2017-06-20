package com.board;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardInfoServlet  extends HttpServlet{
	
	public static final String SAVEBAORDITEM = "SAVEBAORDITEM";
	public static final String MODIFYBOARDITEM = "MODIFYBOARDITEM";
	public static final String DELETEBOARDITEM = "DELETEBOARDITEM";
	
	
	
	
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
			System.out.println("test?");
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
		}else{
			
		}
		
	}
	
	public void dePost(HttpServletRequest req, HttpServletResponse reqs) throws IOException{
		
	}

}
