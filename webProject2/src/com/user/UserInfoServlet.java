package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.user.implement.UserService;

public class UserInfoServlet extends HttpServlet{
	private UserService us = new UserServiceImpl();
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resq) throws IOException, ServletException{
		String actionStr = req.getParameter("action");
		RequestDispatcher disPatcher = req.getRequestDispatcher("../dispatch/error.jsp");
		
		try {
			if(actionStr.equals("MODIFYINFO")){
				HttpSession session = req.getSession();
				UserDTO dto = (UserDTO)session.getAttribute("userDTO");
				String nameStr = req.getParameter("name");
				String ageStr = req.getParameter("age");
				String pwdStr = req.getParameter("pwd");
				dto.setAge(Integer.valueOf(ageStr));
				dto.setName(nameStr);
				dto.setPw(pwdStr);
				boolean isModify = us.modifyInfo(dto);
				
				if(isModify){	
					req.setAttribute("error_message", "회원 정보 수정 성공");
					req.setAttribute("go_page", "../user/welcome.jsp");
					req.setAttribute("url_pattern", "");
					//RequestDispatcher disPatcher = req.getRequestDispatcher("../user/register.jsp");
					disPatcher.forward(req, resq);				
				}else{
					throw new Exception("회원 정보 수정 실패");
				}
				doProcess(resq, "");
			}else if(actionStr.equals("REMOVEACCOUNT")){
				String idStr = req.getParameter("id");
				boolean isRemove = us.removeAccount(idStr);
				
				if(isRemove){
					HttpSession session = req.getSession();
					session.invalidate();				
					req.setAttribute("error_message", "회원탈퇴 성공");
					req.setAttribute("go_page", "../index.jsp");
					req.setAttribute("url_pattern", "");
					//RequestDispatcher disPatcher = req.getRequestDispatcher("../user/register.jsp");
					disPatcher.forward(req, resq);				
				}else{
					throw new Exception("회원탈퇴 실패");
				}
			}else if(actionStr.equals("MANAGEUSERS")){
				List<String> idlist = us.getUserIDLists();
				UserDTO user = new UserDTO();
				Map<String, Boolean> result = new HashMap<>();
				for (String id : idlist) {
					String nameStr = req.getParameter(id + "_name");
					String pwdStr = req.getParameter(id + "_pw");
					String ageStr = req.getParameter(id + "_age");
					String[] adStr = req.getParameterValues(id + "_ad");
					String[] badStr = req.getParameterValues(id + "_bad");
					user.setId(id);
					if(adStr!=null){
						user.setIsAdmin(1);
					}else{
						user.setIsAdmin(0);
					}
					if(badStr!=null){
						user.setIsBoardAdmin(1);
					}else{
						user.setIsBoardAdmin(0);
					}
					user.setAge(Integer.valueOf(ageStr));
					user.setName(nameStr);
					user.setPw(pwdStr);
					result.put(id, us.modifyInfo(user));
				}
				
				req.setAttribute("error_message", "회원 관리 끝");
				req.setAttribute("go_page", "../user/welcome.jsp");
				req.setAttribute("url_pattern", "");
				//RequestDispatcher disPatcher = req.getRequestDispatcher("../user/register.jsp");
				disPatcher.forward(req, resq);	
			
			}
			else{
				throw new Exception("");
			}
		} catch (Exception e) {
			req.setAttribute("error_message", "잘못된 접근 : " + e.getMessage());
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