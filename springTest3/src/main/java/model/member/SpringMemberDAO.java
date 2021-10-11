package model.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class MemberRowMapper implements RowMapper<MemberVO> {

	/*CREATE TABLE MEMBER2(
			ID VARCHAR(15) PRIMARY KEY,
			PASSWORD VARCHAR(15),
			NAME VARCHAR(15),
			ROLE VARCHAR(15)

		);*/
	
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data=new MemberVO();
		data.setId(rs.getString("id"));
		data.setPassword(rs.getString("password"));
		data.setName(rs.getString("name"));
		data.setRole(rs.getString("role"));
		return data;
	}
}

@Repository
public class SpringMemberDAO {

	private final String insertSQL="insert into member2 (id,password,name,role) values((select nvl(max(id),0)+1 from member2),?,?,?)";
	private final String updateSQL="update member2 set password=?, name=?, role=? where id=?";
	private final String deleteSQL="delete member2 where id=?";
	private final String getMemberSQL="select * from member2 where id=? and password=?";
	private final String getMemberListSQL="select * from member2 order by id desc";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertMember(MemberVO vo) {
		System.out.println("jdbcTemplate insert");
		jdbcTemplate.update(insertSQL,vo.getPassword(),vo.getName(),vo.getId());
	}
	
	public void updateMember(MemberVO vo) {
		System.out.println("jdbcTemplate update");
		jdbcTemplate.update(updateSQL,vo.getPassword(),vo.getName(),vo.getRole(),vo.getId());
	}
	
	public void deleteMember(MemberVO vo) {
		System.out.println("jdbcTemplate delete");
		jdbcTemplate.update(deleteSQL,vo.getId());
	}
	
	public List<MemberVO> getMemberList(MemberVO vo) {
		System.out.println("jdbcTemplate getMemberList");
		return jdbcTemplate.query(getMemberListSQL,new MemberRowMapper());
	}
	
	public MemberVO getMember(MemberVO vo) {
		System.out.println("jdbcTemplate getMember");
		Object[] args= { vo.getId(), vo.getPassword() };
		return jdbcTemplate.queryForObject(getMemberSQL,args,new MemberRowMapper());
	}
	
}
// 1. Support 상속
// 2. <bean> 주입☆