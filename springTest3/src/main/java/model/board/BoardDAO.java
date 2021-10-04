package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.common.JDBC;

@Repository("boardDAO")
public class BoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private final String insertSQL="INSERT INTO BOARD2(ID, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(SELECT MAX(ID),0)+1 FROM BOARD),?,?,?)";
	private final String updateSQL="UPDATE BOARD2 SET TITLE=?, CONTENT=? WHERE ID=?";
	private final String deleteSQL="DELETE FROM BOARD2 WHERE ID=?";
	private final String getBoardSQL="SELECT * FROM BOARD2 WHERE ID=? ";
	private final String getBoardListSQL="SELECT * FROM BOARD2";
	
	
	public boolean insertBoard(BoardVO vo) 	{
		conn = JDBC.getConnection();
		boolean res = false;

		try {
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("BoardDAO insert");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return res;
	} 

	public boolean updateBoard(BoardVO vo) {
		conn = JDBC.getConnection();
		boolean res = false;
		try {
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("BoardDAO update");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return res;
	}

	public boolean deleteBoard(BoardVO vo) {
		conn = JDBC.getConnection();
		boolean res = false;
		try {
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, vo.getId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("BoardDAO delete");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return res;
	}
	public List<BoardVO> getBoardList(BoardVO vo) {
		List<BoardVO> datas = new ArrayList<BoardVO>();
		conn = JDBC.getConnection();
		try {
			pstmt = conn.prepareStatement(getBoardListSQL);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				BoardVO data = new BoardVO();

				data.setId(rs.getInt("ID"));
				data.setTitle(rs.getString("TITLE"));
				data.setContent(rs.getString("CONTENT"));
				data.setWriter(rs.getString("WRITER"));
				data.setWdate(rs.getDate("WDATE"));

				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO getBoardList");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return datas;
	}
	public BoardVO getBoard(BoardVO vo) {
		conn = JDBC.getConnection();
		BoardVO data = new BoardVO();
		try {
			pstmt = conn.prepareStatement(getBoardSQL);
			pstmt.setInt(1, vo.getId());
			rs=pstmt.executeQuery();

			if(rs.next()) {
				data.setId(rs.getInt("ID"));
				data.setTitle(rs.getString("TITLE"));
				data.setContent(rs.getString("CONTENT"));
				data.setWriter(rs.getString("WRITER"));
				data.setWdate(rs.getDate("WDATE"));

			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO getBoardList");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return data;
	}

}
