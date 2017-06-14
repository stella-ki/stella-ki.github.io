package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	private static Connection con;
	
	public static Connection getCon() throws SQLException, ClassNotFoundException{
		if(con == null){
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/study_jsp", "root", "1234");
		}
		return con;
	}
	
	public static void closeCon() throws SQLException{
		if(con!=null){
			con.close();
			con = null;
		}
	}
	
	//얘는 tomcat에 떠있는 애들이기 때문에 프로세스가 끝나지 않는다. 프로세스가 일반적으로 끝나는 java에서는 con이 알아서 프로세스 끝나고 없어지겠지만
	//tomcat에 올라와 있는 servlet같은 경우는 서버가 종료되지 않는 이상 계속 존재한다. 그래서 재시작할때 con은 close가 됬지만, null이안되 계속 에러난다.
}
