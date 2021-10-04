package model.member;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import model.board.BoardVO;

public class MemberClient {

	public static void main(String[] args) {

		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");

		MemberService ms= (MemberService)factory.getBean("memberService");
		
		MemberVO vo = new MemberVO();
		vo.setPassword("1234");
		vo.setName("hong");
		vo.setRole("°ü¸®ÀÚ");
		ms.insert(vo);
		
		List<MemberVO> datas = ms.getMemberList(vo);
		for (MemberVO data : datas) {
			System.out.println(data);
		}
		factory.close();
		
	}

}
