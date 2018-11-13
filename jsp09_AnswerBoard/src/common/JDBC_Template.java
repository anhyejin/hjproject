package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Template {
	public static Connection getConnection() {
		
		//���� ù��° ����̹� ����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. ����̹� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("01. ����̹� ���� ����");
			e.printStackTrace();
		}
		
		//DB ���ῡ �ʿ��Ѱ�. (url,id,pw)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "hr";
		
		Connection con = null;
		
		try {
			//������ �������ִ� ����
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("02. DB ���� ����");
			
			//���� ���Ҷ��� Commit �ϰ� ���ش�.(�ڵ� ���� X)
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			System.out.println("02. DB ���� ���� ����");
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean isConnection(Connection con) {
			boolean valid = true;
			
			try {
				//Connection �� ������� �ʾҴٸ�
				if(con==null || con.isClosed()) {
					valid = false;
				}//Connection �� ����Ǿ��ִٸ�,
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
	
	//Connection�� �ݱ� ���� �����ϱ����� �޼ҵ�
	public static void commit(Connection con) {
		if(isConnection(con)) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//Connection�� �ݱ� ���� �ѹ��ϱ����� �޼ҵ�
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

