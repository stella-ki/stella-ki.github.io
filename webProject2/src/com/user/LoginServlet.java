package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.user.implement.UserService;

public class LoginServlet extends HttpServlet{
	
	private UserService us = new UserServiceImpl();
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resq) throws IOException, ServletException{		
		RequestDispatcher disPatcher = req.getRequestDispatcher("../dispatch/error.jsp");
		
		try {
			String action = req.getParameter("action");
			if(action.equals("LOGIN")){
				String idStr = req.getParameter("id");
				String pwStr = req.getParameter("pwd");
				UserDTO reDTO = us.login(idStr, pwStr);	
				boolean isLogin = (reDTO != null);	
				String result = idStr;
				if(isLogin){
					result = "success";
					HttpSession session = req.getSession();
					session.setAttribute("userDTO", reDTO);		
					//resq.sendRedirect(req.getContextPath() + "/user/welcome.jsp?id"+idStr);
				}else{
					result += "님 로그인에 실패 하셨습니다.";
					throw new Exception(result);
				}
				doProcess(resq, result);	
			}else if(action.equals("LOGOUT")){
				HttpSession session = req.getSession();
				if(session.getAttribute("userDTO") != null){
					session.invalidate();
					doProcess(resq, "로그아웃이 성공적으로 이루어 졌습니다.");
					resq.sendRedirect(req.getContextPath() + "/index.jsp");
				}else{
					//throw new Exception("");
				}
			}
		} catch (Exception e) {
			req.setAttribute("error_message", e.getMessage());
			req.setAttribute("go_page", "../index.jsp");
			req.setAttribute("url_pattern", "");
			disPatcher.forward(req, resq);
		}
		
		
	}
	
	public void dePost(HttpServletRequest req, HttpServletResponse reqs) throws IOException{
		
	}

	
	public void doProcess(HttpServletResponse resq, String writeStr) throws IOException {
		resq.setContentType("text/html; charset = UTF-8");
		PrintWriter out = resq.getWriter();
		out.print(writeStr);
		
	}
}