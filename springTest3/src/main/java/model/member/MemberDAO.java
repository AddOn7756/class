package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.common.JDBC;

@Repository("memberDAO")
public class MemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private final String insertSQL="INSERT INTO MEMBER2(ID, PASSWORD, NAME, ROLE) VALUES((SELECT NVL(SELECT MAX(ID),0)+1 FROM BOARD),?,?,?)";
	private final String updateSQL="UPDATE MEMBER2 SET PASSWORD=?, ROLE=? WHERE ID=?";
	private final String deleteSQL="DELETE FROM MEMBER2 WHERE ID=?";
	private final String getBoardSQL="SELECT * FROM MEMBER2 WHERE ID=? ";
	private final String getBoardListSQL="SELECT * FROM MEMBER2";

	public boolean insert(MemberVO vo) {
		conn = JDBC.getConnection();
		boolean res = false;
		
		try {
			pstmt =conn.prepareStatement(insertSQL);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getRole());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("MemberDAO insert");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return res;
	}
	
	public boolean update(MemberVO vo) {
		conn = JDBC.getConnection();
		boolean res = false;
		
		try {
			pstmt =conn.prepareStatement(updateSQL);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getRole());
			pstmt.setInt(3, vo.getId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("MemberDAO update");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		
		return res;
	}
	
	public boolean delete(MemberVO vo) {
		conn = JDBC.getConnection();
		boolean res = false;
		
		try {
			pstmt =conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, vo.getId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("MemberDAO delete");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return res;
	}
	
	public List<MemberVO> getMemberList(MemberVO vo) {
		List<MemberVO> datas = new ArrayList<MemberVO>();
		conn = JDBC.getConnection();
		
		try {
			pstmt =conn.prepareStatement(getBoardListSQL);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO data = new MemberVO();
				
				data.setId(rs.getInt("ID"));
				data.setName(rs.getString("NAME"));
				data.setPassword(rs.getString("PASSWORD"));
				data.setRole(rs.getString("ROLE"));
				
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("MemberDAO getBoardList");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return datas;
	}
	
	public MemberVO getMember(MemberVO vo) {
		conn = JDBC.getConnection();
		MemberVO data = new MemberVO();
		
		try {
			pstmt =conn.prepareStatement(getBoardSQL);
			
			pstmt =conn.prepareStatement(getBoardListSQL);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				data.setId(rs.getInt("ID"));
				data.setName(rs.getString("NAME"));
				data.setPassword(rs.getString("PASSWORD"));
				data.setRole(rs.getString("ROLE"));
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("MemberDAO getBoard");
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return data;
	}
	
	
}

