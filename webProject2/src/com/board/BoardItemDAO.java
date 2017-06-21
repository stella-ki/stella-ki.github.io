package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.DBConn;

public class BoardItemDAO {
	
	public boolean deleteBoardItem(int boardItemnum){
		try {
			Connection con = DBConn.getCon();
			String sql = "update basic_board SET delete_date = now() where item_num=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setInt(1, boardItemnum);
			
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
	
	public boolean insertBoardItem(BoardItemDTO boardItemDto){
		try {
			Connection con = DBConn.getCon();
			String sql = "insert into basic_board("
											+ "create_date, "
											+ "last_Update_Date, "
											+ "auther, "
											+ "title, "
											+ "content, "
											+ "is_can_delete) values (NOW(),NOW(),?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1, boardItemDto.getAuther());
			preparedStatement.setString(2, boardItemDto.getTitle());
			preparedStatement.setString(3, boardItemDto.getContent());
			preparedStatement.setInt(4, boardItemDto.getIs_can_delete());
			
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
	
	public BoardItemDTO getBoardItem(int item_num){

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
					+ "from basic_board where item_num = ?";
			PreparedStatement prestmt = con.prepareStatement(sql);
		
			prestmt.setInt(1, item_num);
			ResultSet rs = prestmt.executeQuery();
						
			BoardItemDTO dto = new BoardItemDTO();
			while (rs.next()) {	
				dto.setItem_num(rs.getInt(1));
				dto.setCreate_date(rs.getString(2));
				dto.setDelete_date(rs.getString(3));
				dto.setLast_Update_Date(rs.getString(4));
				dto.setAuther(rs.getString(5));
				dto.setTitle(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setIs_can_delete(rs.getInt(8));
				
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

	public boolean modifyBoardItem(BoardItemDTO boardItemDTO) {
		try {
			Connection con = DBConn.getCon();
			//"update user_info set user_name=?, age=?, user_pwd=?, admin=?, board_admin=? where user_id=?;"
			String sql = "update basic_board set last_Update_Date = NOW(), title=?, content=?, is_can_delete=? where item_num=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1, boardItemDTO.getTitle());
			preparedStatement.setString(2, boardItemDTO.getContent());
			preparedStatement.setInt(3, boardItemDTO.getIs_can_delete());
			preparedStatement.setInt(4, boardItemDTO.getItem_num());
			
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
	

	public List<BoardItemDTO> searchBoardItem(BoardItemDTO dto){
		List<BoardItemDTO> dtos = new ArrayList<BoardItemDTO>();
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
					+ "from basic_board where 1=1";
			if(dto.getTitle()!=null && !dto.getTitle().equals("")){
				sql+=" and title like ?";
			}
			if(dto.getContent()!=null && !dto.getContent().equals("")){
				sql+=" and content like ?";
			}
			System.out.println(sql);
			PreparedStatement prestmt = con.prepareStatement(sql);
		
			int index = 1;
			if(dto.getTitle()!=null && !dto.getTitle().equals("")){
				System.out.println(dto.getTitle());
				prestmt.setString(index, "%" +dto.getTitle() + "%");
				index++;
			}
			if(dto.getContent()!=null && !dto.getContent().equals("")){
				prestmt.setString(index, "%" +dto.getContent()+ "%");
				index++;
			}
			
			ResultSet rs = prestmt.executeQuery();
						
			while (rs.next()) {	
				BoardItemDTO dtoresult = new BoardItemDTO();
				dtoresult.setItem_num(rs.getInt(1));
				dtoresult.setCreate_date(rs.getString(2));
				dtoresult.setDelete_date(rs.getString(3));
				dtoresult.setLast_Update_Date(rs.getString(4));
				dtoresult.setAuther(rs.getString(5));
				dtoresult.setTitle(rs.getString(6));
				dtoresult.setContent(rs.getString(7));
				dtoresult.setIs_can_delete(rs.getInt(8));
				dtos.add(dtoresult);
			}
						
			DBConn.closeCon();
			return dtos;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
