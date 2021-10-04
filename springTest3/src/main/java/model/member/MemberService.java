package model.member;

import java.util.List;

public interface MemberService {

	boolean insert(MemberVO vo);
	boolean update(MemberVO vo);
	boolean delete(MemberVO vo);
	List<MemberVO> getMemberList(MemberVO vo);
	MemberVO getMember(MemberVO vo);
	
}
