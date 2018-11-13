package com.my.dao;

import static common.JDBC_Template.close;
import static common.JDBC_Template.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.my.dto.LoginBoardDto;

import common.JDBC_Template;

public class LoginBoardDao extends JDBC_Template {

	/*
	 * ������ ��� (admin) ȸ�� ��ü ���� (Ż��ȸ�� ����) ȸ����� ����
	 */

	/*
	 * ����� ��� �α��� ȸ�� ���� - > �ߺ� üũ ������ ��ȸ �� ���� ���� ȸ��Ż��
	 */

	public LoginBoardDto Login(String id, String pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		LoginBoardDto res = new LoginBoardDto();

		String sql = " SELECT * FROM MYMEMBER WHERE MYID = ? AND MYPW = ? AND MYENABLED = ? ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, "Y");
			System.out.println("03. query �غ� :" + sql);

			rs = pstm.executeQuery();
			System.out.println("04.query ����");
			while (rs.next()) {
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
			System.out.println("db ����");
		}

		return res;
	}

	public List<LoginBoardDto> selectAll() {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<LoginBoardDto> list = new ArrayList<LoginBoardDto>();

		String sql = " SELECT * FROM MYMEMBER ORDER BY MYNO DESC ";

		try {
			System.out.println("03. QUERY �غ� : " + sql);
			stmt = con.createStatement();

			System.out.println("04. QUERY ���� �� ��� ����");
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				LoginBoardDto dto = new LoginBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));

				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("03/04 �ܰ� ����");
			e.printStackTrace();
		} finally {
			System.out.println("05. DB ���� ����(����)\n");
			close(rs);
			close(stmt);
			close(con);
		}

		return list;
	}

	public List<LoginBoardDto> userlistenabled() {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LoginBoardDto> list = new ArrayList<LoginBoardDto>();

		String sql = " SELECT * FROM MYMEMBER WHERE MYENABLED =? ORDER BY MYNO DESC ";

		try {
			System.out.println("03. QUERY �غ� : " + sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Y");

			System.out.println("04. QUERY ���� �� ��� ����");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LoginBoardDto dto = new LoginBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));

				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("03/04 �ܰ� ����");
			e.printStackTrace();
		} finally {
			System.out.println("05. DB ���� ����(����)\n");
			close(rs);
			close(pstmt);
			close(con);
		}

		return list;
	}
	public LoginBoardDto selectOne(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		LoginBoardDto dto = null;

		String sql = " SELECT * FROM MYMEMBER WHERE MYNO = ? ";

		try {
			System.out.println("03. QUERY �غ� : " + sql);
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, myno);

			System.out.println("04. QUERY ���� �� ��� ����");
			rs = pstm.executeQuery();
			while (rs.next()) {
				dto = new LoginBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("03/04 �ܰ� ����");
			e.printStackTrace();
		} finally {
			System.out.println("05. DB ���� ����(����)\n");
			close(rs);
			close(pstm);
			close(con);
		}
		return dto;
	}

	
//���� �׳� ȥ�� �ѰŴϱ� �Ű� ����
	public int update(LoginBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int update_res = 0;

		String sql = " UPDATE MYMEMBER SET MYADDR = ?, MYPHONE = ?, MYEMAIL=? WHERE MYNO = ?";

		try {
			System.out.println("03. QUERY �غ� : " + sql);
			pstm = con.prepareStatement(sql);

			pstm.setString(1, dto.getMyaddr());
			pstm.setString(2, dto.getMyphone());
			pstm.setString(3, dto.getMyemail());
			pstm.setInt(4, dto.getMyno());

			System.out.println("04. QUERY ���� �� ����");
			update_res = pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("03/04 �ܰ� ����");
			e.printStackTrace();
		} finally {
			System.out.println("05. DB ���� ����(����)\n");
			close(pstm);
			close(con);
		}
		return update_res;
	}
	
	//ȸ���������
		public int updateRole(int myno,String myrole) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int update_res = 0;
			String sql = " UPDATE MYMEMBER SET MYROLE = ? WHERE MYNO = ? ";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);

				pstm.setString(1, myrole);
				
				pstm.setInt(2, myno);

				System.out.println("04. QUERY ���� �� ����");
				update_res = pstm.executeUpdate();
			} catch (SQLException e) {
				System.out.println("03/04 �ܰ� ����");
				e.printStackTrace();
			} finally {
				System.out.println("05. DB ���� ����(����)\n");
				close(pstm);
				close(con);
			}
			return update_res;
		}
		//���̵� �ߺ�üũ
		public String idChk(String id) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			String res = null;

			String sql = " SELECT * FROM MYMEMBER WHERE MYID = ? ";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);
				
				pstm.setString(1, id);

				rs = pstm.executeQuery();
				System.out.println("04. QUERY ���� �� ��� ����");
				
				while (rs.next()) {
					res= rs.getString(2);
				}
			} catch (SQLException e) {
				System.out.println("03/04 �ܰ� ����");
				e.printStackTrace();
			} finally {
				System.out.println("05. DB ���� ����(����)\n");
				close(rs);
				close(pstm);
				close(con);
			}
			return res;
		}
		public int insert(LoginBoardDto dto) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int insert_res = 0;

			String sql = " INSERT INTO MYMEMBER VALUES(MYNOSEQ.NEXTVAL,?,?,?,?,?,?,'Y','USER') ";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);

				pstm.setString(1, dto.getMyid());
				pstm.setString(2, dto.getMypw());
				pstm.setString(3, dto.getMyname());
				pstm.setString(4, dto.getMyaddr());
				pstm.setString(5, dto.getMyphone());
				pstm.setString(6, dto.getMyemail());
				

				System.out.println("04. QUERY ���� �� ����");
				insert_res = pstm.executeUpdate();
			} catch (SQLException e) {
				System.out.println("03/04 �ܰ� ����");
				e.printStackTrace();
			} finally {
				System.out.println("05. DB ���� ����(����)\n");
				close(pstm);
				close(con);
			}
			return insert_res;
		}
		public int delete(int myno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int delete_res = 0;

			String sql = " DELETE FROM MYMEMBER WHERE MYNO = ? ";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);

				pstm.setInt(1, myno);

				System.out.println("04. QUERY ���� �� ����");
				delete_res = pstm.executeUpdate();
			} catch (SQLException e) {
				System.out.println("03/04 �ܰ� ����");
				e.printStackTrace();
			} finally {
				System.out.println("05. DB ���� ����(����)\n");
				close(pstm);
				close(con);
			}
			return delete_res;
		}
}
