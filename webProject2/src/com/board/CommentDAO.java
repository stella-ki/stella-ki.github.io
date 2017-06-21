package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.DBConn;

public class CommentDAO {
	
	public boolean insertComment(CommentDTO cmtDTO){
		try {
			Connection con = DBConn.getCon();
			String sql = "insert into board_comment("
											+ "create_date, "
											+ "last_Update_Date, "
											+ "author, "
											+ "content, "
											+ "board_item_num) values (NOW(),NOW(),?,?,?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1, cmtDTO.getAuthor());
			preparedStatement.setString(2, cmtDTO.getContent());
			preparedStatement.setInt(3, cmtDTO.getBoard_item_num());
			
			int result = preparedStatement.executeUpdate();
			
			DBConn.closeCon();
			
			if(result == 1){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}
	

	public List<CommentDTO> getComments(int board_item_num){
		List<CommentDTO> result = new ArrayList<>();
		try {
			Connection con = DBConn.getCon();
			String sql = "select create_date, author, content from board_comment where board_item_num = ?";
			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setInt(1, board_item_num);
			
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()){
				CommentDTO cdto = new CommentDTO();
				cdto.setCreate_date(rs.getString(1));
				cdto.setAuthor(rs.getString(2));
				cdto.setContent(rs.getString(3));
				result.add(cdto);
			}
			DBConn.closeCon();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
}
