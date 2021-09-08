package model.coffee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JNDI;

public class CoffeeDAO {

	// 상품전체 보기
	private String sql_SELECT_ALL = "SELECT * FROM COFFEE WHERE NAME LIKE ? ORDER BY NUM DESC";
	// 상품하나 보기
	private String sql_SELECT_ONE = "SELECT * FROM COFFEE WHERE NUM=?";
	// 상품삭제
	private String sql_DELETE = "DELETE FROM COFFEE WHERE NUM=? ";
	// 상품추가
	private String sql_INSERT = "INSERT INTO COFFEE VALUES((SELECT NVL(MAX(NUM), 0)+1 FROM COFFEE),?,?)";
	// 상품수정
	private String sql_UPDATE = "UPDATE COFFEE SET NAME=?, PRICE=? WHERE NUM=?";
	
//==================================================================================	
	public ArrayList<CoffeeVO> selectAll(String coffeeName) {
		Connection conn=JNDI.connect();
		PreparedStatement pstmt=null;
		ArrayList<CoffeeVO> datas = new ArrayList();
		CoffeeVO data=null;
		try {
			pstmt=conn.prepareStatement(sql_SELECT_ALL);
			pstmt.setString(1, "%"+coffeeName+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				data=new CoffeeVO();
				data.setNum(rs.getInt("num"));
				data.setName(rs.getString("name"));
				data.setPrice(rs.getInt("price"));
				datas.add(data);
				// System.out.println(datas);
			}
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CoffeeDAO selectAll에서 발생");
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
	}
//==================================================================================	
	public CoffeeVO selectOne(CoffeeVO vo) {
		Connection conn=JNDI.connect();
		PreparedStatement pstmt=null;
		CoffeeVO data=null;
		try {
			pstmt=conn.prepareStatement(sql_SELECT_ONE);
			pstmt.setInt(1, vo.getNum());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data=new CoffeeVO();
				data.setNum(rs.getInt("num"));
				data.setName(rs.getString("name"));
				data.setPrice(rs.getInt("price"));
			}
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CoffeeDAO selectOne에서 발생");
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}
//==================================================================================
	public boolean insert(CoffeeVO vo) {
		Connection conn=JNDI.connect();
		PreparedStatement pstmt=null;
		boolean res=false;
		try {
			pstmt=conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.executeUpdate();
			res=true;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CoffeeDAO insert에서 발생");
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
//==================================================================================		
	public boolean delete(CoffeeVO vo) {
		Connection conn=JNDI.connect();
		PreparedStatement pstmt=null;
		boolean res=false;
		try {
			pstmt=conn.prepareStatement(sql_DELETE);
			pstmt.setInt(1, vo.getNum());
			pstmt.executeUpdate();
			res=true;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CoffeeDAO delete에서 발생");
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
//==================================================================================	
	public boolean update(CoffeeVO vo) {
		Connection conn=JNDI.connect();
		PreparedStatement pstmt=null;
		boolean res=false;
		try {
			pstmt=conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getNum());
			pstmt.executeUpdate();
			res=true;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CoffeeDAO update에서 발생");
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
		

}
