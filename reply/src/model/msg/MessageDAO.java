package model.msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

public class MessageDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public ArrayList<MsgSet> selectAll(String memid){
		ArrayList<MsgSet> datas = new ArrayList<MsgSet>();
		conn = JNDI.getConnection();
		String sql;
		
		try {
			if(memid == null || (memid.equals(""))) {
				sql="select * from message order by day";
				pstmt = conn.prepareStatement(sql);
				
			}
			else {
				sql="select * from message where memid=? order by day desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memid);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MsgSet ms = new MsgSet();
				MessageVO m = new MessageVO();
				ArrayList<ReplyVO> rlist = new ArrayList<ReplyVO>();
				
				m.setMid(rs.getInt("mid"));
				m.setMsg(rs.getNString("msg"));
				m.setFavcount(rs.getInt("favcount"));
				m.setMemid(rs.getString("memid"));
				m.setDay(rs.getDate("day"));
				
				String rsql = "select * from reply where mid=? order by day desc";
				pstmt = conn.prepareStatement(rsql);
				pstmt.setInt(1, rs.getInt("mid"));
				ResultSet rrs = pstmt.executeQuery();
				int rcnt = 0;
				while(rrs.next()) {
					ReplyVO r = new ReplyVO();
					r.setDay(rrs.getDate("day"));
					r.setMid(rrs.getInt("mid"));
					r.setRid(rrs.getInt("rid"));
					r.setMemid(rrs.getString("memid"));
					rlist.add(r);
					rcnt++;
				}
				m.setReplycount(rcnt);
				
				ms.setM(m);
				ms.setRlist(rlist);
				datas.add(ms);
				rrs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
		
	}

	public boolean insert(MessageVO vo) {
		conn = JNDI.getConnection();
		
		String sql="insert into message values((select nvl(max(mid),0)+1 from message), ?,?, 0, 0, sysdate)";
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemid());
			pstmt.setString(2,vo.getMsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
	}
	
	public boolean delete(MessageVO vo) {
		conn = JNDI.getConnection();
		String sql="delete from message where mid=?";
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
	}
	
	public void update(MessageVO vo) {
		conn = JNDI.getConnection();
		String sql="update message set favcount= favcount+1 where mid=?";
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
	}
	
	
}
