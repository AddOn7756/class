package model.msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.common.JNDI;

public class ReplyDAO {
	
	public boolean insert(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
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
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
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
