package model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.crypto.provider.RSACipher;

import model.common.JNDI;

public class UsersDAO {

	// 회원 전체 출력
	static String sql_SELECT_ALL ="SELECT * FROM USERS";
	// 회원 한명 출력
	static String sql_SELECT_ONE = "SELECT * FROM USERS WHERE USER_NUM=?";
	// 회원 추가
	static String sql_INSERT = "INSERT INTO USERS VALUES((SELECT NVL(MAX(USER_NUM), 0)+1 FROM USERS),?,?,?,?,?,?,?,?,?)";
	// 회원 탈퇴
	static String sql_DELETE = "DELETE FROM USERS WHERE USER_NUM=?";
	// 회원정보 수정 가능 한 것 : 비밀번호, 핸드폰번호, 이메일, 주소, 아이콘 번호
	static String sql_UPDATE = "UPDATE USERS SET USER_PW=?, USER_HP=?, USER_EMAIL=?, USER_ADDR=?, ICON_ID=? WHERE USER_NUM=?";
	
	
	//============================================================================
	public ArrayList<UsersVO> getDBList() {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<UsersVO> datas = new ArrayList<UsersVO>();

		try {
			pstmt = conn.prepareStatement(sql_SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				UsersVO data = new UsersVO();

				data.setUserNum(rs.getInt("USER_NUM"));
				data.setName(rs.getString("USER_NAME"));
				data.setId(rs.getString("USER_ID"));
				data.setPw(rs.getString("USER_PW"));
				data.setHp(rs.getString("USER_HP"));
				data.setGender(rs.getString("USER_GENDER"));
				data.setEmail(rs.getString("USER_EMAIL"));
				data.setAddr(rs.getString("USER_ADDR"));
				data.setBirth(rs.getString("USER_BIRTH"));
				data.setIconId(rs.getString("ICON_ID"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("UsersDAO getDBList에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
	}
	//============================================================================
	public UsersVO getDBData(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		UsersVO data = new UsersVO();

		String sql = "SELECT * FROM USERS WHERE USER_NUM=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserNum());
			ResultSet rs = pstmt.executeQuery();

			data.setUserNum(rs.getInt("USER_NUM"));
			data.setName(rs.getString("USER_NAME"));
			data.setId(rs.getString("USER_ID"));
			data.setPw(rs.getString("USER_PW"));
			data.setHp(rs.getString("USER_HP"));
			data.setGender(rs.getString("USER_GENDER"));
			data.setEmail(rs.getString("USER_EMAIL"));
			data.setAddr(rs.getString("USER_ADDR"));
			data.setBirth(rs.getString("USER_BIRTH"));
			data.setIconId(rs.getString("ICON_ID"));

			rs.close();
		} catch (SQLException e) {
			System.out.println("UsersDAO getDBData에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}
	//============================================================================
	public boolean insert(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		
		try {
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			pstmt.setString(4, vo.getHp());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getAddr());
			pstmt.setString(8, vo.getBirth());
			pstmt.setString(9, vo.getIconId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("UserDAO insert에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//============================================================================
	public boolean delete(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			pstmt = conn.prepareStatement(sql_DELETE);
			pstmt.setInt(1, vo.getUserNum());
			pstmt.executeUpdate();
			res = true;
		} catch (SQLException e) {
			System.out.println("UserDAO insert에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//============================================================================
	public boolean update(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;

		try {
			// 회원정보 수정 가능 한 것 : 비밀번호, 핸드폰번호, 이메일, 주소, 아이콘 번호
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getIconId());
			pstmt.setInt(6, vo.getUserNum());
			pstmt.executeUpdate();
			
			res=true;
		} catch (SQLException e) {
			System.out.println("UserDAO insert에서 발생");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}



}
