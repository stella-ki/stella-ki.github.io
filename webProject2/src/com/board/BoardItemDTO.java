package com.board;

public class BoardItemDTO {
	/*
	 * item_num int(10) UNSIGNED not null AUTO_INCREMENT,
	create_date varchar(30) not null,
	delete_date varchar(30) not null,
	last_Update_Date varchar(30) not null,
	auther varchar(50) not null,
	title varchar(100) not null,
	content varchar(1000) not null,
	is_can_delete int(10) not null,basic_board
	*/
	
	private int item_num;
	private String create_date;
	private String delete_date;
	private String last_Update_Date;
	private String auther;
	private String title;
	private String content;
	private int is_can_delete;
	
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(String delete_date) {
		this.delete_date = delete_date;
	}
	public String getLast_Update_Date() {
		return last_Update_Date;
	}
	public void setLast_Update_Date(String last_Update_Date) {
		this.last_Update_Date = last_Update_Date;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIs_can_delete() {
		return is_can_delete;
	}
	public void setIs_can_delete(int is_can_delete) {
		this.is_can_delete = is_can_delete;
	}
	@Override
	public String toString() {
		return "BoardItemDTO [item_num=" + item_num + ", create_date=" + create_date + ", delete_date=" + delete_date
				+ ", last_Update_Date=" + last_Update_Date + ", auther=" + auther + ", title=" + title + ", content="
				+ content + ", is_can_delete=" + is_can_delete + "]";
	}
	
	
	
}
