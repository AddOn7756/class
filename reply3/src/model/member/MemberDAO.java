package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt = null;
	ResultSet rs;

	public boolean insert(MemberVO vo) {
		conn = JNDI.getConnection();
		String sql = "insert into member values (?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemid());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPasswd());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MemberDAO insert에서 발생");
			e.printStackTrace();
			return false;
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;

	}

	public boolean login(MemberVO vo) {
		conn = JNDI.getConnection();
		String sql = "select memid, passwd from member where memid=?";
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemid());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if (rs.getString("passwd").equals(vo.getPasswd())) {
					result = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("MemberDAO login에서 발생");
			e.printStackTrace();
			return false;
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;
	}

	
	public ArrayList<MemberVO> selectAll(){
		ArrayList<MemberVO> datas = new ArrayList<MemberVO>();
		conn = JNDI.getConnection();
		String sql = "select * from member order by day desc";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO data = new MemberVO();
				data.setDay(rs.getDate("day"));
				data.setMemid(rs.getString("memid"));
				data.setName(rs.getString("name"));
				data.setPasswd(rs.getString("passwd"));
				datas.add(data);
			}

		} catch (SQLException e) {
			System.out.println("MemberDAO selectAll에서 발생");
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
	}

}
