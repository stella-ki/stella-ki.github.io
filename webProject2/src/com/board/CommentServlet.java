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

public class CommentServlet  extends HttpServlet{
	
	public static final String SAVEBAORDITEM = "SAVEBAORDITEM";
	public static final String MODIFYBOARDITEM = "MODIFYBOARDITEM";
	public static final String DELETEBOARDITEM = "DELETEBOARDITEM";
	
	
	private CommentDAO dao = new CommentDAO();
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resq) throws IOException, ServletException{
		RequestDispatcher disPatcher = req.getRequestDispatcher("../dispatch/error.jsp");
		
		String action = req.getParameter("action");
		if(action.equals(SAVEBAORDITEM)){
			String auther = req.getParameter("auther");
			String content = req.getParameter("content");
			String board_item_num = req.getParameter("board_item_num");
			
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.setAuthor(auther);
			commentDTO.setContent(content);
			commentDTO.setBoard_item_num(Integer.valueOf(board_item_num));
			
			boolean result = dao.insertComment(commentDTO);
			if(result){	
				req.setAttribute("error_message", "댓글쓰기 성공");
				req.setAttribute("go_page", "../user/welcome.jsp");
				req.setAttribute("url_pattern", "");
				disPatcher.forward(req, resq);				
			}else{
				req.setAttribute("error_message", "댓글쓰기 실패");
				req.setAttribute("go_page", "../user/welcome.jsp");
				req.setAttribute("url_pattern", "");
			}
			System.out.println(commentDTO);
		}else{
			
		}
		
	}
	
	public void dePost(HttpServletRequest req, HttpServletResponse reqs) throws IOException{
		
	}

}
