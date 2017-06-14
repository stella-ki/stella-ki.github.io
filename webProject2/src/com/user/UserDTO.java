package com.user;

public class UserDTO {
	public final static int ADMIN = 1;
	public final static int NOT_ADMIN = 0;
	
	public final static int BOARDADMIN = 1;
	public final static int NOT_BOARDADMIN = 0;
	
	//Data Transfer Object
	private String id;
	private String pw;
	private String name;
	private int user_num;
	private int age;
	private int isAdmin;
	private int isBoardAdmin;
			
	public UserDTO() {
		super();
	}
	
	public UserDTO(String id, String pw, int age, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.age = age;
		this.name = name;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIsBoardAdmin() {
		return isBoardAdmin;
	}

	public void setIsBoardAdmin(int isBoardAdmin) {
		this.isBoardAdmin = isBoardAdmin;
	}

	@Override
	public String toString() {
		String result = 
				"아이디 (" + id + ")의 정보는 "
						+ "[이름 :" + name + ", "
								+ "나이 : " + age + ", "
										+ "사용자 번호 : " + user_num + "] 입니다.</br>";
		
		return result;
	}
	
	

}
