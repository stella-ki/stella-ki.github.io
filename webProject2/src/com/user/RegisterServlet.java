package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.user.implement.UserService;

public class RegisterServlet extends HttpServlet{
	private UserService us = new UserServiceImpl();
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resq) throws IOException, ServletException{
		String actionStr = req.getParameter("action");
		RequestDispatcher disPatcher = req.getRequestDispatcher("../dispatch/error.jsp");
		
		try {
			if(actionStr.equals("REGISTER")){
				String idStr = req.getParameter("id");
				String pwStr = req.getParameter("pwd");
				String nameStr = req.getParameter("name");
				String ageStr = req.getParameter("age");
				if(idStr.equals("") || pwStr.equals("") || nameStr.equals("") || ageStr.equals("") ){
					req.setAttribute("error_message", "잘입력해");
					req.setAttribute("go_page", "../user/register.jsp");
					req.setAttribute("url_pattern", "?action=REGISTER&id="+idStr+"&pwd="+pwStr+"&name="+nameStr+"&age="+ageStr);
					//RequestDispatcher disPatcher = req.getRequestDispatcher("../user/register.jsp");
					disPatcher.forward(req, resq);
					return;
				}		
				
				int ageINT = Integer.valueOf(ageStr);
				UserDTO user = new UserDTO(idStr, pwStr, ageINT, nameStr);
				boolean isRegisterSuccess = us.signup(user);
				String result = idStr;
				if(isRegisterSuccess){
					result += "님 반갑습니다. 회원가입에 성공하셨습니다.";
					HttpSession session = req.getSession();
					session.setAttribute("userDTO", user);
					resq.sendRedirect(req.getContextPath() + "../user/welcome.jsp");
				}else{
					result += "님 로그인에 실패 하셨습니다.";
					throw new Exception(result);
				}
				doProcess(resq, result);
			}else if(actionStr.equals("CHECK")){
				String idStr = req.getParameter("id");
				boolean isDup = us.checkDuplicate(idStr);
				doProcess(resq, isDup);
			}
			else{
				throw new Exception("잘못된 접근");
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

	
	public void doProcess(HttpServletResponse resq, Object write) throws IOException {
		resq.setContentType("text/html; charset = UTF-8");
		PrintWriter out = resq.getWriter();
		out.print(write);
		
	}
}