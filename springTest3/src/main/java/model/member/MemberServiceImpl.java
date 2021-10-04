package model.member;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	private MemberDAO memberDAO;
	
	@Override
	public boolean insert(MemberVO vo) {
		return memberDAO.insert(vo);
	}

	@Override
	public boolean update(MemberVO vo) {
		return memberDAO.update(vo);
	}

	@Override
	public boolean delete(MemberVO vo) {
		return memberDAO.delete(vo);
	}

	@Override
	public List<MemberVO> getMemberList(MemberVO vo) {
		return memberDAO.getMemberList(vo);
	}

	@Override
	public MemberVO getMember(MemberVO vo) {
		return memberDAO.getMember(vo);
	}

	
	
}
