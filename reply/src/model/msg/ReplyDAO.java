package model.msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.common.JNDI;

public class ReplyDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	public boolean insert(ReplyVO vo) {
		conn = JNDI.getConnection();
		
		String sql ="insert into reply values ((select nvl(max(rid),0)+1 from reply), ?,?,sysdate,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMid());
			pstmt.setString(2, vo.getMemid());
			pstmt.setString(3, vo.getRmsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
		
	}
	
	public boolean delete(ReplyVO vo) {
		conn = JNDI.getConnection();
		String sql = "delete from reply where rid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getRid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
	
	}
	
	
	
	
	
	
}
