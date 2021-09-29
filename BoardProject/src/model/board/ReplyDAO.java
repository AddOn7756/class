package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;
import model.users.UsersVO;

public class ReplyDAO {

	// 댓글 새로 작성 
	// 입력 값 : (R_ID, USER_NUM, B_ID, R_CONTENT, R_WRITER, PARENT_ID) 
	// DEFAULT 자동 입력값(R_DATE, DELETE_AT)
	static String sql_INSERT = "INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) "
			+ "VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),?,?,?,?,?)";
	// 댓글 삭제
	static String sql_DELETE = "UPDATE BOARD_REPLY SET DELETE_AT='Y' WHERE R_ID =?";
	// 댓글 업데이트 
	static String sql_UPDATE = "UPDATE BOARD_REPLY SET R_CONTENT=? WHERE R_ID=?";
	// 유저 ID 값 댓글 전체출력
	static String sql_SELECT_ALL_USER = "SELECT R_ID, B_ID, USER_NUM, "
			+ "CASE WHEN DELETE_AT='Y' THEN 'unknown' ELSE R_WRITER END AS R_WRITER, "
			+ "CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END AS R_CONTENT , "
			+ "R_DATE, DELETE_AT, PARENT_ID FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM ("
			+ "SELECT * FROM BOARD_REPLY WHERE USER_NUM=? AND B_ID=? AND PARENT_ID=0 ORDER BY R_DATE DESC"
			+ ") BOARD_REPLY WHERE ROWNUM<=? "
			+ ") WHERE RNUM > ? ORDER BY R_DATE DESC";

	// 댓글 전체출력
	static String sql_SELECT_ALL = "SELECT R_ID, B_ID, USER_NUM, "
			+ "CASE WHEN DELETE_AT='Y' THEN 'unknown' ELSE R_WRITER END AS R_WRITER, "
			+ "CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END AS R_CONTENT , "
			+ "R_DATE, DELETE_AT, PARENT_ID FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM ("
			+ "SELECT * FROM BOARD_REPLY WHERE B_ID=? AND PARENT_ID=0 ORDER BY R_DATE DESC"
			+ ") BOARD_REPLY WHERE ROWNUM<=? "
			+ ") WHERE RNUM > ? ORDER BY R_DATE DESC";

	// 댓글 하나 출력
	static String sql_SELECT_ONE = "SELECT R_ID, B_ID, USER_NUM, "
			+ "CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END AS R_WRITER, "
			+ "CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END AS R_CONTENT, "
			+ "R_DATE, DELETE_AT, PARENT_ID FROM BOARD_REPLY WHERE R_ID=?";
	
	//========================================================================================
	// selectAll 댓글 객체 + 대댓글 List가 들어있는 datas 반환

	   static int pageSize = 10; // 페이지 10개씩 출력 출력갯수 바꾸실때 여기 바꾸시면 됩니다.

	   // userNum 값 없을 때 0넣어주세요
	   public ArrayList<ReplySet> getDBList(UsersVO uvo, ReplyVO vo, int pageNum) {

	      Connection conn = JNDI.getConnection();
	      PreparedStatement pstmt = null;
	      ArrayList<ReplySet> datas = new ArrayList<ReplySet>();
	      ReplyVO rvo = null;
	      int cnt = 0;

	      System.out.println(uvo);
	      System.out.println(vo);
	      System.out.println(pageNum);

	      try {
	         // 로그인 아이디가 있다면 (내 글보기)
	         if(uvo.getUserNum() > 0) {
	            //SELECT * FROM (SELECT * FROM BOARD_REPLY ORDER BY B_DATE DESC) 
	            // WHERE USER_NUM=? AND PARENT_ID=0 AND ROWNUM > ? AND ROWNUM <=?";
	            pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
	            pstmt.setInt(1, uvo.getUserNum());
	            pstmt.setInt(2, vo.getbId());
	            pstmt.setInt(3, (pageNum-1)*pageSize+pageSize);
	            pstmt.setInt(4, (pageNum-1)*pageSize);
	            // System.out.println("userNum > 0 통과");
	         }
	         // 전체 출력
	         else {
	            //"SELECT * FROM (SELECT * FROM BOARD_REPLY ORDER BY B_DATE DESC) "
	            //      + "WHERE ROWNUM>? AND ROWNUM <=?";
	            pstmt = conn.prepareStatement(sql_SELECT_ALL);
	            pstmt.setInt(1, vo.getbId());
	            pstmt.setInt(2, (pageNum-1)*pageSize+pageSize);
	            pstmt.setInt(3, (pageNum-1)*pageSize);
	            // System.out.println("전체출력 통과");
	         }
	         System.out.println("start : "+(pageNum-1)*pageSize);
	         System.out.println("last : "+(pageNum-1)*pageSize+pageSize);

	         ResultSet rs = pstmt.executeQuery();
	         // 댓글 객체

	         while(rs.next()) {
	            // System.out.println("replyDAO.getDBList while안에서 출력");
	            rvo = new ReplyVO();

	            rvo.setrId(rs.getInt("R_ID"));            // 댓글 PK
	            rvo.setbId(rs.getInt("B_ID"));            // 게시글 번호
	            rvo.setUserNum(rs.getInt("USER_NUM"));      // 유저 번호
	            rvo.setrWriter(rs.getString("R_WRITER"));   // 작성자
	            rvo.setrContent(rs.getString("R_CONTENT"));   // 댓글 내용
	            rvo.setrDate(rs.getDate("R_DATE"));         // 작성일
	            rvo.setDeleteAt(rs.getString("DELETE_AT"));   // 삭제여부
	            rvo.setParentId(rs.getInt("PARENT_ID"));   // 댓글 대댓글 구분
	            // rs.close();

	            // System.out.println("댓글 1객체 : "+rvo);

	            String rrsql = "SELECT * FROM BOARD_REPLY WHERE PARENT_ID=? ORDER BY R_DATE DESC";
	            pstmt = conn.prepareStatement(rrsql);
	            pstmt.setInt(1, rvo.getrId()); // 대댓글의 parent는 댓글의 ID
	            ResultSet rrs = pstmt.executeQuery();

	            ArrayList<ReplyVO> rrlist = new ArrayList<ReplyVO>();

	            while(rrs.next()) {

	               ReplyVO rrvo = new ReplyVO();
	               //rrlist = new ArrayList<ReplyVO>();

	               rrvo.setrId(rrs.getInt("R_ID"));
	               rrvo.setbId(rrs.getInt("B_ID"));
	               rrvo.setUserNum(rrs.getInt("USER_NUM"));
	               rrvo.setrContent(rrs.getString("R_CONTENT"));
	               rrvo.setrDate(rrs.getDate("R_DATE"));
	               rrvo.setDeleteAt(rrs.getString("DELETE_AT"));
	               rrvo.setrWriter(rrs.getString("R_WRITER"));
	               rrvo.setParentId(rrs.getInt("PARENT_ID"));

	               // System.out.println("대댓글 writer 확인 : "+rrvo.getrWriter());
	               rrlist.add(rrvo);

	               // System.out.println("대댓글 list"+rrlist);
	            }
	            rrs.close();

	            ReplySet replySet = new ReplySet();
	            replySet.setRvo(rvo); // 댓글 1
	            replySet.setRrlist(rrlist); // 댓글에 대한 대댓글
	            datas.add(replySet); // 댓글1 + 대댓글 N개

	            // System.out.println("replySet : "+replySet);
	         }
	         rs.close();

	         String sql;
	     	
	     	if(uvo.getUserNum() > 0) {
	     		// 내가 쓴 글 전체 개수 출력
	     		sql = "SELECT COUNT(*) FROM BOARD_REPLY WHERE USER_NUM=?";
	     		pstmt.setInt(1, uvo.getUserNum());
	     	}
	     	else {
	     		// 카테고리별 전체 개수 출력
	     		sql = "SELECT COUNT(*) FROM BOARD_REPLY";
	     	}

	     	ReplySet replySet = new ReplySet();
	     	
	     	ResultSet crs = pstmt.executeQuery();
	     	if(crs.next()) {
	     		cnt=crs.getInt(1);
	     	}
	     	crs.close();
	     	
	     	replySet.setReplyCnt(cnt);
	     	datas.add(replySet);
	         
	      }  catch(SQLException e) {
	         System.out.println("ReplyDAO getDBList에서 발생");
	         e.printStackTrace();
	      } finally {
	         JNDI.disconnect(pstmt, conn);
	      }

	      /*for(ReplySet v : datas) {
	         System.out.println("최종 : "+v);
	      }*/
	      return datas;
	   }
	
	
	
	//========================================================================================
	@SuppressWarnings("resource")
	public ReplySet getDBData(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		ReplySet data = new ReplySet();
		ReplyVO rvo = null;
		try {
			// "SELECT * FROM BOARD_REPLY WHERE R_ID=?";
			pstmt = conn.prepareStatement(sql_SELECT_ONE);
			pstmt.setInt(1, vo.getrId());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				rvo = new ReplyVO();

				rvo.setrId(rs.getInt("R_ID"));				// 댓글 PK
				rvo.setbId(rs.getInt("B_ID"));				// 게시글 번호
				rvo.setUserNum(rs.getInt("USER_NUM"));		// 유저 번호
				rvo.setrContent(rs.getString("R_CONTENT"));	// 댓글 내용
				rvo.setrDate(rs.getDate("R_DATE"));			// 작성일
				rvo.setDeleteAt(rs.getString("DELETE_AT"));	// 삭제여부
				rvo.setrWriter(rs.getString("R_WRITER"));	// 작성자
				rvo.setParentId(rs.getInt("PARENT_ID"));	// 댓글 대댓글 구분

				rs.close();
				
				ArrayList<ReplyVO> rrlist = new ArrayList<ReplyVO>();
				
				if(rvo.getParentId() > 0) {
					String sql = "SELECT * FROM BOARD_REPLY WHERE PARENT_ID=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rvo.getrId()); // 대댓글의 parent는 댓글의 ID
					ResultSet rrs = pstmt.executeQuery();
					// 대댓글
					
					while (rrs.next()) {
						ReplyVO rrvo = new ReplyVO();

						rrvo.setrId(rrs.getInt("R_ID"));				// 댓글 PK
						rrvo.setbId(rrs.getInt("B_ID"));				// 게시글 번호
						rrvo.setUserNum(rrs.getInt("USER_NUM"));		// 유저 번호
						rrvo.setrContent(rrs.getString("R_CONTENT"));	// 댓글 내용
						rrvo.setrDate(rrs.getDate("R_DATE"));			// 작성일
						rrvo.setDeleteAt(rrs.getString("DELETE_AT"));	// 삭제여부
						rrvo.setrWriter(rrs.getString("R_WRITER"));		// 작성자
						rrvo.setParentId(rrs.getInt("PARENT_ID"));		// 댓글 대댓글 구분	
						rrlist.add(rrvo);
					}
					rrs.close();
				}
				data.setRvo(rvo);
				data.setRrlist(rrlist);
			}
		} catch (SQLException e) {
			System.out.println("ReplySet getDBDate에서 출력");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}
	//========================================================================================	
	@SuppressWarnings("resource")
	public boolean insert(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		// 트렌젝션 확인
		boolean check = false;
		try {
			// (SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),?,?,?,?,?)
			// ? 입력값 : (B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID)
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setInt(1, vo.getbId());			// 보드 ID
			pstmt.setInt(2, vo.getUserNum());		// 유저넘버
			pstmt.setString(3, vo.getrContent());	// 댓글내용
			pstmt.setString(4, vo.getrWriter());	// 작성자
			pstmt.setInt(5, vo.getParentId());		// 댓글 대댓글 구분 부모 값
			pstmt.executeUpdate();

			String sql = "UPDATE BOARD SET RE_CNT = RE_CNT+1 WHERE B_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getbId());
			pstmt.executeUpdate();
			check = true;

			if (check) {
				conn.commit();
				res= true;
			}
			else {
				conn.rollback();
			}

		} catch (SQLException e) {
			System.out.println("replyDAO insert에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//========================================================================================
	@SuppressWarnings("resource")
	public boolean delete(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		String sql;
		int cnt=0; // 대댓글 개수 확인

		// 커밋확인용
		boolean check = false;

		try {
			// 오토 커밋 해제
			conn.setAutoCommit(false);
			// 댓글이라면
			if (vo.getParentId() == 0) {

				sql ="SELECT COUNT(*) FROM BOARD_REPLY WHERE PARENT_ID=?";
				// sql = "SELECT * FROM BOARD_REPLY WHERE PARENT_ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getrId());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					cnt=rs.getInt(1);
				}
				System.out.println("댓글일때 일 때 대댓글 갯수 : "+cnt);
				
				// 만약에 댓글에 대댓글이 없으면 해당 댓글 삭제
				if (cnt == 0) {
					sql = "DELETE FROM BOARD_REPLY WHERE R_ID=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, vo.getrId());
					pstmt.executeUpdate();
					System.out.println("대댓글 없을때 댓글삭제 통과");
				}
				// 대댓글이 있으면 DELETE_AT Y로 수정
				else {
					pstmt = conn.prepareStatement(sql_DELETE);
					pstmt.setInt(1, vo.getrId());
					pstmt.executeUpdate();
					System.out.println("대댓글 있을때 업데이트 통과");
				}
			}
			// 대댓글 이라면
			else {
				sql = "DELETE FROM BOARD_REPLY WHERE R_ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getrId());
				pstmt.executeUpdate();
				System.out.println("대댓글 일때 삭제 통과");
			}
			
			// 댓글 수 1 제거
			sql = "UPDATE BOARD SET RE_CNT = RE_CNT-1 WHERE B_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getbId());
			pstmt.executeUpdate();
			System.out.println("댓글수 차감완료");
			
			check = true;
			
			if (check) {
				conn.commit();
				res= true;
				System.out.println("커밋완료");
			}
			else {
				conn.rollback();
				System.out.println("롤백");
			}

		} catch (SQLException e) {
			System.out.println("ReplyDAO delete에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//========================================================================================	
	public boolean update(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getrContent());
			pstmt.setInt(2, vo.getrId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("ReplyDAO update에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}

}
