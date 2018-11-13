package com.answer.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.dto;

import common.JDBC_Template;

public class dao extends JDBC_Template{

		public List<dto> selectAll(){
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<dto> list_res = new ArrayList<dto>();

			String sql = " SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSQ ";

			try {
				pstm = con.prepareStatement(sql);
				System.out.println("03. QUERY �غ� : " + sql);
				
				rs = pstm.executeQuery();
				System.out.println("04. QUERY ���� �� ��� ����");

				while (rs.next()) {
					dto dto = new dto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getDate(9));

					list_res.add(dto);
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

			return list_res;
		}	
		public dto selectOne(int boardno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;

			dto dto = null;

			String sql = " SELECT * FROM ANSWERBOARD WHERE BOARDNO = ? ";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);
				
				pstm.setInt(1, boardno);

				System.out.println("04. QUERY ���� �� ��� ����");
				rs = pstm.executeQuery();
				while (rs.next()) {
					dto = new dto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getDate(9));
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
		public int insert(dto dto) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int insert_res = 0;

			String sql = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSQ.NEXTVAL,GROUPNOSQ.NEXTVAL,1,0,?,?,?,'N',SYSDATE) ";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);

				pstm.setString(1, dto.getTitle());
				pstm.setString(2, dto.getContent());
				pstm.setString(3, dto.getWriter());
				

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
		public int update(dto dto) {
			
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int update_res = 0;

			String sql = " UPDATE ANSWERBOARD SET TITLE = ?, CONTENT = ? WHERE BOARDNO = ?";
			

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);

				pstm.setString(1, dto.getTitle());
				pstm.setString(2, dto.getContent());
				pstm.setInt(3, dto.getBoardno());

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
		public int delete(int boardno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int delete_res = 0;

			String sql = " UPDATE ANSWERBOARD SET ISDEL= 'Y' WHERE BOARDNO=? ";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);

				pstm.setInt(1, boardno);

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
		public int updateAnswer(int groupno,int groupsq) {
			
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int updateAnswer_res = 0;

			String sql = " UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ+1 WHERE GROUPNO = ? AND GROUPSQ > ? ";
/*							" DELETE FROM ANSWERBOARD WHERE BOARDNO = ?  AND TITLETAB = 0"
			//�θ���� �׷� ��ȣ�� �θ���� ������ ã�Ƽ� ��������
			String sql2 = " UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ+1 WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ? ) AND GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO=? ) ";
*/
			try {
				pstm = con.prepareStatement(sql);

				pstm.setInt(1, groupno);
				pstm.setInt(2, groupsq);
				System.out.println("03. QUERY �غ� : " + sql);

				updateAnswer_res = pstm.executeUpdate();
				System.out.println("04. QUERY ���� �� ����");
				
				if(updateAnswer_res>0) {
					commit(con);
				}
			} catch (SQLException e) {
				System.out.println("03/04 �ܰ� ����");
				e.printStackTrace();
			} finally {
				System.out.println("05. DB ���� ����(����)\n");
				close(pstm);
				close(con);
			}
			return updateAnswer_res;
		}
		public int insertAnswer(dto dto) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int insert_res = 0;

			String sql = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSQ.NEXTVAL,?,?,?,?,?,?,'N',SYSDATE) ";

			try {
				pstm = con.prepareStatement(sql);

				pstm.setInt(1, dto.getGroupno());
				pstm.setInt(2, dto.getGroupsq()+1);
				pstm.setInt(3, dto.getTitletab()+1);
				pstm.setString(4, dto.getTitle());
				pstm.setString(5, dto.getContent());
				pstm.setString(6, dto.getWriter());
				
				System.out.println("03. QUERY �غ� : " + sql);
				
				insert_res = pstm.executeUpdate();
				System.out.println("04. QUERY ���� �� ����");
				
				if(insert_res>0) {
					commit(con);
				}
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
		public int deleteAnswer(int g_no) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int delete_res = 0;

			String sql = " DELETE FROM ANSWERBOARD WHERE GROUPNO = ?";

			try {
				System.out.println("03. QUERY �غ� : " + sql);
				pstm = con.prepareStatement(sql);

				pstm.setInt(1, g_no);

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
