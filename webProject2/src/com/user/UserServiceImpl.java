package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.user.implement.UserService;

public class UserServiceImpl implements UserService{
	
	public List<String> getUserIDLists(){
		//String result = "";
		List<String> userlist = new ArrayList<String>();
		try {
			Connection con = DBConn.getCon();			
			String sql = "select user_id from user_info";
			PreparedStatement prestmt = con.prepareStatement(sql);
			
			ResultSet rs = prestmt.executeQuery();
			
			while (rs.next()) {		
				userlist.add(rs.getString(1));
			}
						
			DBConn.closeCon();
			
			return userlist;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<UserDTO> getUserLists(){
		//String result = "";
		List<UserDTO> userlist = new ArrayList<UserDTO>();
		UserDTO user;
		try {
			Connection con = DBConn.getCon();			
			String sql = "select user_num, user_name, user_id, user_pwd, age, admin, board_admin from user_info";
			PreparedStatement prestmt = con.prepareStatement(sql);
			
			ResultSet rs = prestmt.executeQuery();
			
			while (rs.next()) {		
				user = new UserDTO();
				user.setUser_num(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setId(rs.getString(3));
				user.setPw(rs.getString(4));
				user.setAge(rs.getInt(5));				
				user.setIsAdmin(rs.getInt(6));
				user.setIsBoardAdmin(rs.getInt(7));
				userlist.add(user);
			}
						
			DBConn.closeCon();
			
			return userlist;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public UserDTO login(String userID, String userPWD){
		//String result = "";
		UserDTO user = new UserDTO();
		try {
			Connection con = DBConn.getCon();			
			String sql = "select user_num, user_name, user_id, user_pwd, age, admin, board_admin from user_info where user_id = ? and user_pwd = ?";
			PreparedStatement prestmt = con.prepareStatement(sql);
			
			prestmt.setString(1, userID);
			prestmt.setString(2, userPWD);
			
			ResultSet rs = prestmt.executeQuery();
			
			while (rs.next()) {
				user.setId(userID);
				user.setPw(userPWD);
				user.setUser_num(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAge(rs.getInt(5));				
				user.setIsAdmin(rs.getInt(6));
				user.setIsBoardAdmin(rs.getInt(7));
				return user;
			}				
			DBConn.closeCon();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
		
	public boolean signup(UserDTO user){
			try {
			Connection con = DBConn.getCon();			
			String sql = "insert into user_info(user_name, user_id, user_pwd, age, admin, board_admin) values (?,?,?,?,?,?);";
			PreparedStatement prestmt = con.prepareStatement(sql);
			
			prestmt.setString(1, user.getName());
			prestmt.setString(2, user.getId());
			prestmt.setString(3, user.getPw());
			prestmt.setInt(4, user.getAge());
			prestmt.setInt(5, 0);
			prestmt.setInt(6, 0);
			
			int result = prestmt.executeUpdate();
			DBConn.closeCon();
			if(result == 1){
				return true;				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean logout(HttpSession session){
		if(session.getAttribute("userDTO") != null){
			session.invalidate();
			return true;
		}else{
			return false;
		}
		
	}
	

	public boolean checkDuplicate(String idStr){
		try {
			Connection con = DBConn.getCon();			
			String sql = "select * from user_info where user_id = ?";
			PreparedStatement prestmt = con.prepareStatement(sql);
			
			prestmt.setString(1, idStr);
			
			ResultSet rs = prestmt.executeQuery();
			
			while (rs.next()) {
				return true;
			}				
			DBConn.closeCon();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean modifyInfo(UserDTO dto){
		try {
			Connection con = DBConn.getCon();			
			String sql 
			= "update user_info set user_name=?, age=?, user_pwd=?, admin=?, board_admin=? where user_id=?;";
			PreparedStatement prestmt = con.prepareStatement(sql);
			System.out.println(dto.toString());
			prestmt.setString(1, dto.getName());
			prestmt.setInt(2, dto.getAge());
			prestmt.setString(3, dto.getPw());
			prestmt.setInt(4, dto.getIsAdmin());
			prestmt.setInt(5, dto.getIsBoardAdmin());
			prestmt.setString(6, dto.getId());

			
			int result = prestmt.executeUpdate();
						
			DBConn.closeCon();
			
			if(result == 1){
				return true;	
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean removeAccount(String idStr){
		try {
			Connection con = DBConn.getCon();			
			String sql = "delete from user_info where user_id=?";
			PreparedStatement prestmt = con.prepareStatement(sql);
			
			prestmt.setString(1, idStr);
			
			int result = prestmt.executeUpdate();
						
			DBConn.closeCon();
			
			if(result == 1){
				return true;	
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
