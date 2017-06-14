package com.user.implement;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.user.UserDTO;

public interface UserService {
	
	public UserDTO login(String userID, String userPWD);
	public boolean signup(UserDTO user);
	public boolean logout(HttpSession sesion);
	public boolean modifyInfo(UserDTO dto);
	public boolean checkDuplicate(String idStr);
	public boolean removeAccount(String idStr);
	public List<UserDTO> getUserLists();
	public List<String> getUserIDLists();
}
