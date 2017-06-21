package com.board;

public class CommentDTO {
	
	/*
	 * 
	 * create table board_comment(
	item_num int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	create_date DATE not null,
	delete_date DATE not null,
	last_update_date DATE not null,
	author varchar(50) not null,
	content varchar(1000) not null,
	board_item_num int(10) UNSIGNED not null,
	PRIMARY KEY(item_num),
	FOREIGN key(board_item_num) REFERENCES basic_board(item_num)
	 * */
	
	int item_num;
	String create_date;
	String delete_date;
	String last_update_date;
	String author;
	String content;
	int board_item_num;
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
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBoard_item_num() {
		return board_item_num;
	}
	public void setBoard_item_num(int board_item_num) {
		this.board_item_num = board_item_num;
	}
	@Override
	public String toString() {
		return "CommentDTO [item_num=" + item_num + ", create_date=" + create_date + ", delete_date=" + delete_date
				+ ", last_update_date=" + last_update_date + ", author=" + author + ", content=" + content
				+ ", board_item_num=" + board_item_num + "]";
	}
	
	
}
