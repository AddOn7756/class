package model.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.oracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kim","1234");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn,PreparedStatement pstmt) {
		
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
