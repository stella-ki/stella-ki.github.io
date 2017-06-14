package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.DBConn;

public class BoardItemDAO {
	
	public BoardItemDTO getBoardItem(int item_num){

		try {
			Connection con = DBConn.getCon();			
			String sql = "select "
					+ "create_date, "
					+ "delete_date, "
					+ "last_Update_Date, "
					+ "auther, "
					+ "title, "
					+ "content, "
					+ "is_can_delete "
					+ "from basic_board where item_num = ?";
			PreparedStatement prestmt = con.prepareStatement(sql);
		
			prestmt.setInt(1, item_num);
			ResultSet rs = prestmt.executeQuery();
						
			BoardItemDTO dto = new BoardItemDTO();
			while (rs.next()) {	
				
				dto.setCreate_date(rs.getString(1));
				dto.setDelete_date(rs.getString(2));
				dto.setLast_Update_Date(rs.getString(3));
				dto.setAuther(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setIs_can_delete(rs.getInt(7));
				
			}
						
			DBConn.closeCon();
			return dto;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<BoardItemDTO> getBoardItemList(){
		//String result = "";
		List<BoardItemDTO> boardItemlist = new ArrayList<BoardItemDTO>();
		try {
			Connection con = DBConn.getCon();			
			String sql = "select "
					+ "item_num, "
					+ "create_date, "
					+ "delete_date, "
					+ "last_Update_Date, "
					+ "auther, "
					+ "title, "
					+ "content, "
					+ "is_can_delete "
					+ "from basic_board";
			PreparedStatement prestmt = con.prepareStatement(sql);
			
			ResultSet rs = prestmt.executeQuery();
			
			BoardItemDTO dto;
			while (rs.next()) {	
				dto = new BoardItemDTO();
				dto.setItem_num(rs.getInt(1));
				dto.setCreate_date(rs.getString(2));
				dto.setDelete_date(rs.getString(3));
				dto.setLast_Update_Date(rs.getString(4));
				dto.setAuther(rs.getString(5));
				dto.setTitle(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setIs_can_delete(rs.getInt(8));
				boardItemlist.add(dto);
			}
						
			DBConn.closeCon();
			
			return boardItemlist;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
