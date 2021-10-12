package com.kim.app.common;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import model.board.BoardService;
import model.board.BoardVO;
import model.member.MemberService;
import model.member.MemberVO;

public class Client {
	
	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService bs=(BoardService)factory.getBean("boardService");
		MemberService ms = (MemberService)factory.getBean("memberService");
		
		BoardVO bvo=new BoardVO();
		MemberVO mvo = new MemberVO();
		
		bvo.setContent("1");
		bvo.setTitle("제목2");
		bvo.setWriter("관리자");
		/*bs.insertBoard(bvo);*/
		
		mvo.setId("hong");
		mvo.setName("길동");
		mvo.setPassword("12345");
		mvo.setRole("USER");
		/*ms.insertMember(mvo);*/
		
		
		MemberVO mdata = ms.getMember(mvo);
		
		if(mdata != null) {
			System.out.println("로그인 성공");
		}
		else {
			System.out.println("로그인 실패");
		}
		
		
		List<BoardVO> datas = bs.getBoardList(bvo);
		
		for(BoardVO data : datas) {
			System.out.println(data);
		}
		
		factory.close();
	}
	
}
