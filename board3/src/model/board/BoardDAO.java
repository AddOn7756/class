package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;

public class BoardDAO {

	// 전체 출력 또는 검색
	private String sql_SELECT_ALL = "SELECT * FROM BOARD WHERE TITLE LIKE ? ORDER BY BORNUM DESC";
	
	private String sql_SELECT_ONE = "select * from board where bornum=?";
	// 글 삭제
	private String sql_DELETE = "DELETE FROM BOARD WHERE BORNUM=? ";
	// 글 입력
	private String sql_INSERT = "INSERT INTO BOARD VALUES((SELECT NVL(MAX(BORNUM), 0)+1 FROM BOARD),?,?,?)";
	// 글 수정
	private String sql_UPDATE = "UPDATE BOARD SET CONTENT=?, TITLE=?, WRITER=? WHERE BORNUM=?";
	
	public BoardVO selectOne(BoardVO vo){
		Connection conn=JDBC.connect();
		BoardVO data=null;
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(sql_SELECT_ONE);
			pstmt.setInt(1, vo.getBornum());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				data=new BoardVO();
				data.setContent(rs.getString("content"));
				data.setBornum(rs.getInt("bornum"));
				data.setTitle(rs.getString("title"));
				data.setWriter(rs.getString("writer"));
			}
			System.out.println(data);
			rs.close();
		}
		catch(Exception e){
			System.out.println("getDBData()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}
		return data;
	}
	
	public ArrayList<BoardVO> selectAll(String content) {
		Connection conn = JDBC.connect();
		ArrayList<BoardVO> datas = new ArrayList();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql_SELECT_ALL);
			pstmt.setString(1, "%"+content+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO data = new BoardVO();
				data.setBornum(rs.getInt("bornum"));
				// System.out.println("boardDAO select 확인 : "+ data.getBornum());
				data.setTitle(rs.getString("title"));
				// System.out.println("boardDAO select 확인 : "+ data.getTitle());
				data.setContent(rs.getString("content"));
				// System.out.println("boardDAO select 확인 : "+ data.getContent());
				data.setWriter(rs.getString("writer"));
				// System.out.println("boardDAO select 확인 : "+ data.getWriter());
				datas.add(data);
				// System.out.println("boardDAO select 확인 : "+ datas);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("BoardDAO selectAll에서 발생");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return datas;
	}

	public boolean delete(BoardVO vo) {
		Connection conn=JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res=false;
		try {
			pstmt=conn.prepareStatement(sql_DELETE);
			pstmt.setInt(1, vo.getBornum());
			pstmt.executeUpdate();
			res=true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BoardDAO delete에서 발생");
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}

	public boolean insert(BoardVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res= false;
		try {
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getContent());
			// System.out.println("DAO 인서트에서 발생 "+vo.getContent());
			pstmt.setString(2, vo.getTitle());
			// System.out.println("DAO 인서트에서 발생 "+vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			// System.out.println("DAO 인서트에서 발생 "+vo.getWriter());
			pstmt.executeUpdate();
			res=true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BoardDAO insert에서 발생");
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
	
	public boolean update(BoardVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt=null;
		boolean res=false;
		try {
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getBornum());
			pstmt.executeUpdate();
			res=true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BoardDAO update에서 발생");
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
		
	}


}
