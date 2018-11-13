package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Template {
	public static Connection getConnection() {
		
		//가장 첫번째 드라이버 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. 드라이버 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. 드라이버 연결 실패");
			e.printStackTrace();
		}
		
		//DB 연결에 필요한것. (url,id,pw)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "hr";
		
		Connection con = null;
		
		try {
			//실제로 연결해주는 구문
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("02. DB 계정 연결");
			
			//내가 원할때만 Commit 하게 해준다.(자동 저장 X)
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			System.out.println("02. DB 계정 연결 실패");
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean isConnection(Connection con) {
			boolean valid = true;
			
			try {
				//Connection 이 연결되지 않았다면
				if(con==null || con.isClosed()) {
					valid = false;
				}//Connection 이 연결되어있다면,
			} catch (SQLException e) {
				valid = true;
				e.printStackTrace();
			}
			
		return valid;
	}
	
	public static void close(Connection con) {
		if(isConnection(con)) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Connection을 닫기 전에 저장하기위한 메소드
	public static void commit(Connection con) {
		if(isConnection(con)) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//Connection을 닫기 전에 롤백하기위한 메소드
	public static void rollback(Connection con) {
		if(isConnection(con)) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//public static void close(Statement stmt, Connection co)
}

