package model.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JNDI;

public class ABankDAO {

	public ArrayList<ABankVO> selectAll() {
		Connection conn = JNDI.connect();
		PreparedStatement pstmt = null;
		ArrayList<ABankVO> datas = new ArrayList();
		try {
			String sql = "SELECT * FROM BANK1";
			pstmt=conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ABankVO data = new ABankVO();
				data.setNum(rs.getInt("num"));
				data.setName(rs.getString("name"));
				data.setMoney(rs.getInt("money"));
				datas.add(data);
			}
			rs.close();
		} catch(Exception e) {
			System.out.println("ABankDAO selectOne에서 발생");
			e.printStackTrace();
		} finally{
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	public ABankVO selectOne(int num) {
		Connection conn = JNDI.connect();
		PreparedStatement pstmt = null;
		ABankVO data = new ABankVO();
		try {
			String sql = "SELECT * FROM BANK1 WHERE NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setName(rs.getString("name"));
				data.setMoney(rs.getInt("money"));
			}
			rs.close();
		} catch(Exception e) {
			System.out.println("ABankDAO selectOne에서 발생");
			e.printStackTrace();
		} finally{
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}
	
	
	@SuppressWarnings("resource")
	public boolean trans(String send, String receive, int money) {
		Connection conn = JNDI.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			conn.setAutoCommit(false);
			String sql1 = "UPDATE BANK1 SET MONEY=MONEY-? WHERE NAME=? ";
			String sql2 = "UPDATE BANK1 SET MONEY=MONEY+? WHERE NAME=? ";
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, money);
			pstmt.setString(2, send);
			pstmt.executeUpdate();
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, money);
			pstmt.setString(2, receive);
			pstmt.executeUpdate();
			
			String sql = "SELECT MONEY FROM BANK1 WHERE NAME=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, send);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt(1)<0) {
				conn.rollback();
			}
			else {
				res = true;
				conn.commit();
			}
			conn.setAutoCommit(true);
		} catch(Exception e) {
			System.out.println("ABankDAO trans 에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	
	
	
	
}
