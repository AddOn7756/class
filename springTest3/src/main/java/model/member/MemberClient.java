package model.member;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MemberClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");

		MemberService ms=(MemberService)factory.getBean("memberService");

		MemberVO vo=new MemberVO();
		vo.setId("admin");
		vo.setPassword("1234");
		MemberVO data=ms.getMember(vo);

		if(data!=null) {
			System.out.println("로그인 성공");
		}
		else {
			System.out.println("로그인 실패");
		}

		factory.close();
	}
}
