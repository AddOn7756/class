package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.common.JDBC;

public class MemberDAO {

	// 회원 가입
	private String sql_INSERT ="INSERT INTO MEMBER VALUES ((SELECT NVL(MAX(MEMNUM), 0)+1 FROM MEMBER),?,?,?)";
	// 회원 탈퇴
	private String sql_DELETE = "DELETE FROM MEMBER WHERE MEMNUM=? ";
	// 회원정보 변경
	private String sql_UPDATE = "UPDATE MEMBER SET NAME=?, MID=?, MPW=? WHERE MEMNUM=?";
	// 맴버
	private String sql_SELECT = "SELECT * FROM MEMBER WHERE MID=? AND MPW=?";

// ========================================================================
	public boolean insert(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res=false;
		try {
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getMpw());
			pstmt.executeUpdate();
			res = true;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO insert에서 출력");
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
// ========================================================================	
	public boolean delete(MemberVO vo) {
		Connection conn=JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			pstmt=conn.prepareStatement(sql_DELETE);
			pstmt.setInt(1, vo.getMemnum());
			pstmt.executeUpdate();
			res = true;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO delete에서 출력");
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
// ========================================================================	
	public boolean update(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		System.out.println(vo);
		try {
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getMpw());
			pstmt.setInt(4, vo.getMemnum());
			pstmt.executeUpdate();
			res = true;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO update에서 출력");
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
// ========================================================================	
	public MemberVO getMember(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		MemberVO data = null;
		
		try {
			pstmt = conn.prepareStatement(sql_SELECT);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberVO();
				data.setMemnum(rs.getInt("memnum"));
				data.setName(rs.getString("name"));
				data.setMid(rs.getString("mid"));
				data.setMpw(rs.getString("mpw"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO getMember에서 출력");
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return data;
	}
	
	
}
