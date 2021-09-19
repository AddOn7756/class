package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

public class BoardDAO {

	// 게시글 새로 작성 
	// 입력 값 : (B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) 
	// DEFAULT 자동 입력값(B_DATE, B_HIT, RE_CNT)
	static String sql_INSERT = "INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) "
			+ "VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),?,?,?,?,?,?)";
	// 게시글 삭제
	static String sql_DELETE = "DELETE FROM BOARD WHERE B_ID=?";
	// 게시글 업데이트 
	// 변경가능 정보 : (카테고리, 타이틀, 컨텐츠(게시글 내용), 프로그래밍 언어) 
	// 변경불가 : (B_ID, USER_NUM, 작성자,작성일, 댓글수, 조회수)
	static String sql_UPDATE = "UPDATE BOARD SET B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_NUM=?";
	// 유저 ID 값 게시글 전체출력
	//static String sql_SELECT_ALL_USER = "SELECT * FROM BOARD (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE USER_NUM=? AND ROWNUM <=?"; //더보기식 페이징
	// 숫자식 페이징
	static String sql_SELECT_ALL_USER = "SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD.* FROM BOARD WHERE ROWNUM<=? ORDER BY B_DATE DESC) WHERE ?<=RNUM";
	
	// 게시글 전체출력
	static String sql_SELECT_ALL = "SELECT * FROM (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE ROWNUM <=?";
	// 게시글 하나 출력
	static String sql_SELECT_ONE = "SELECT * FROM BOARD_REPLY WHERE B_ID=?";
//=====================================================================================
// selectAll BoardVO가 들어있는 datas 반환
	
	// userNum 값 없을 때 0넣어주세요
	public ArrayList<BoardVO> getDBList(int userNum, int pageNum) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>();
		try {
			// 로그인 아이디가 있다면 (내 글보기)
			if (userNum > 0) {
				pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
				pstmt.setInt(1, userNum);
				pstmt.setInt(2, (pageNum*10)+10); 	// max 번호
				pstmt.setInt(3, pageNum*10); 		// start 번호
			}
			// 전체 출력
			else {
				pstmt = conn.prepareStatement(sql_SELECT_ALL);
				pstmt.setInt(2, (pageNum*10)+10); 	// max 번호
				pstmt.setInt(1, pageNum*10); 		// start 번호
			}

			ResultSet brs = pstmt.executeQuery();
			while(brs.next()) {
				BoardVO bvo = new BoardVO();

				bvo.setbId(brs.getInt("B_ID"));				// 게시글 번호
				bvo.setUserNum(brs.getInt("USER_NUM")); 	// 작성 유저 번호
				bvo.setbCtgr(brs.getString("B_CTGR")); 		// 게시글 카테고리
				bvo.setbTitle(brs.getString("B_TITLE"));	// 게시글 제목
				bvo.setbContent(brs.getString("B_CONTENT"));// 게시글 내용
				bvo.setbWriter(brs.getString("B_WRITER")); 	// 글쓴이
				bvo.setbDate(brs.getDate("B_DATE")); 		// 작성일
				bvo.setbHit(brs.getInt("B_HIT")); 			// 조회수
				bvo.setbLang(brs.getString("B_LANG")); 		// 프로그래밍 언어
				bvo.setReCnt(brs.getInt("RE_CNT")); 		// 댓글 수 
				
				datas.add(bvo);
			}
			brs.close();
		} catch(SQLException e) {
			System.out.println("boardDAO getDBList에서 발생");
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
			System.out.println("BoardDAO getDBData에서 발생");
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
			// ? 입력 값 : (USER_NUM, B_CTGR, B-TITLE, B_CONTENT, B_WRITER, B_LANG) 
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setInt(1, vo.getUserNum()); 		// 유저넘버
			pstmt.setString(2, vo.getbCtgr());		// 카테고리
			pstmt.setString(3, vo.getbTitle());		// 타이틀
			pstmt.setString(4, vo.getbContent());	// 내용
			pstmt.setString(5, vo.getbWriter());	// 글쓴이
			pstmt.setString(6, vo.getbLang());		// 프로그래밍 언어
			pstmt.executeUpdate();
			res=true;
		} catch(SQLException e) {
			System.out.println("BoardDAO insert에서 발생");
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
			// 변경가능 정보 : (카테고리, 타이틀, 컨텐츠(게시글 내용), 프로그래밍 언어) 
			// 변경불가 : (B_ID, USER_NUM, 작성자,작성일, 댓글수, 조회수)
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
			System.out.println("BoardDAO update에서 발생");
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
			pstmt.setInt(1, vo.getbId());	// 아이디 값으로 삭제
			pstmt.executeUpdate();
			res = true;
		} catch (SQLException e) {
			System.out.println("BoardDAO delete에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}


}
