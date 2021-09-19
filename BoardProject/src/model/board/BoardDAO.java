package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

public class BoardDAO {

	// �Խñ� ���� �ۼ� 
	// �Է� �� : (B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) 
	// DEFAULT �ڵ� �Է°�(B_DATE, B_HIT, RE_CNT)
	static String sql_INSERT = "INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) "
			+ "VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),?,?,?,?,?,?)";
	// �Խñ� ����
	static String sql_DELETE = "DELETE FROM BOARD WHERE B_ID=?";
	// �Խñ� ������Ʈ 
	// ���氡�� ���� : (ī�װ�, Ÿ��Ʋ, ������(�Խñ� ����), ���α׷��� ���) 
	// ����Ұ� : (B_ID, USER_NUM, �ۼ���,�ۼ���, ��ۼ�, ��ȸ��)
	static String sql_UPDATE = "UPDATE BOARD SET B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_NUM=?";
	// ���� ID �� �Խñ� ��ü���
	//static String sql_SELECT_ALL_USER = "SELECT * FROM BOARD (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE USER_NUM=? AND ROWNUM <=?"; //������� ����¡
	// ���ڽ� ����¡
	static String sql_SELECT_ALL_USER = "SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD.* FROM BOARD WHERE ROWNUM<=? ORDER BY B_DATE DESC) WHERE ?<=RNUM";
	
	// �Խñ� ��ü���
	static String sql_SELECT_ALL = "SELECT * FROM (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE ROWNUM <=?";
	// �Խñ� �ϳ� ���
	static String sql_SELECT_ONE = "SELECT * FROM BOARD_REPLY WHERE B_ID=?";
//=====================================================================================
// selectAll BoardVO�� ����ִ� datas ��ȯ
	
	// userNum �� ���� �� 0�־��ּ���
	public ArrayList<BoardVO> getDBList(int userNum, int pageNum) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>();
		try {
			// �α��� ���̵� �ִٸ� (�� �ۺ���)
			if (userNum > 0) {
				pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
				pstmt.setInt(1, userNum);
				pstmt.setInt(2, (pageNum*10)+10); 	// max ��ȣ
				pstmt.setInt(3, pageNum*10); 		// start ��ȣ
			}
			// ��ü ���
			else {
				pstmt = conn.prepareStatement(sql_SELECT_ALL);
				pstmt.setInt(2, (pageNum*10)+10); 	// max ��ȣ
				pstmt.setInt(1, pageNum*10); 		// start ��ȣ
			}

			ResultSet brs = pstmt.executeQuery();
			while(brs.next()) {
				BoardVO bvo = new BoardVO();

				bvo.setbId(brs.getInt("B_ID"));				// �Խñ� ��ȣ
				bvo.setUserNum(brs.getInt("USER_NUM")); 	// �ۼ� ���� ��ȣ
				bvo.setbCtgr(brs.getString("B_CTGR")); 		// �Խñ� ī�װ�
				bvo.setbTitle(brs.getString("B_TITLE"));	// �Խñ� ����
				bvo.setbContent(brs.getString("B_CONTENT"));// �Խñ� ����
				bvo.setbWriter(brs.getString("B_WRITER")); 	// �۾���
				bvo.setbDate(brs.getDate("B_DATE")); 		// �ۼ���
				bvo.setbHit(brs.getInt("B_HIT")); 			// ��ȸ��
				bvo.setbLang(brs.getString("B_LANG")); 		// ���α׷��� ���
				bvo.setReCnt(brs.getInt("RE_CNT")); 		// ��� �� 
				
				datas.add(bvo);
			}
			brs.close();
		} catch(SQLException e) {
			System.out.println("boardDAO getDBList���� �߻�");
			e.printStackTrace();
		}
		return datas;
	}
//=====================================================================================
	public boolean getDBData(int userNum, int bid) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			if (userNum > 0) {
				pstmt = conn.prepareStatement(sql_SELECT_ONE);
				pstmt.setInt(1, userNum);
				pstmt.setInt(2, bid);
			}
			else {
				pstmt = conn.prepareStatement(sql_SELECT_ALL);
				pstmt.setInt(1, bid);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO getDBData���� �߻�");
			e.printStackTrace();
		}
		return res;
	}
//=====================================================================================
	public boolean insert(BoardVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			// (SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),?,?,?,?,?,?)
			// ? �Է� �� : (USER_NUM, B_CTGR, B-TITLE, B_CONTENT, B_WRITER, B_LANG) 
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setInt(1, vo.getUserNum()); 		// �����ѹ�
			pstmt.setString(2, vo.getbCtgr());		// ī�װ�
			pstmt.setString(3, vo.getbTitle());		// Ÿ��Ʋ
			pstmt.setString(4, vo.getbContent());	// ����
			pstmt.setString(5, vo.getbWriter());	// �۾���
			pstmt.setString(6, vo.getbLang());		// ���α׷��� ���
			pstmt.executeUpdate();
			res=true;
		} catch(SQLException e) {
			System.out.println("BoardDAO insert���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
//=====================================================================================
	public boolean update(BoardVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt=null;
		boolean res = false;
		try {
			// ���氡�� ���� : (ī�װ�, Ÿ��Ʋ, ������(�Խñ� ����), ���α׷��� ���) 
			// ����Ұ� : (B_ID, USER_NUM, �ۼ���,�ۼ���, ��ۼ�, ��ȸ��)
			// B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_NUM=?
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getbCtgr());
			pstmt.setString(2, vo.getbTitle());
			pstmt.setString(3, vo.getbContent());
			pstmt.setString(4, vo.getbLang());
			pstmt.setInt(5, vo.getbId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("BoardDAO update���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
//=====================================================================================
	public boolean delete(BoardVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			pstmt = conn.prepareStatement(sql_DELETE);
			pstmt.setInt(1, vo.getbId());	// ���̵� ������ ����
			pstmt.executeUpdate();
			res = true;
		} catch (SQLException e) {
			System.out.println("BoardDAO delete���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}


}
