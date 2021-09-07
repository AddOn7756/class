<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.member.*, model.board.*, java.util.*" errorPage="error.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String content=request.getParameter("content");
%>

<jsp:useBean id="memberDAO" class="model.member.MemberDAO" />
<jsp:useBean id="boardDAO" class="model.board.BoardDAO" />
<jsp:useBean id="memberVO" class="model.member.MemberVO" />
<jsp:useBean id="boardVO" class="model.board.BoardVO" scope="session"/>
<jsp:setProperty property="*" name="memberVO" />
<jsp:setProperty property="*" name="boardVO" />

<%

	String action = request.getParameter("action");
	// System.out.println(action);
	
	if (action.equals("main")){
		ArrayList<BoardVO> datas = boardDAO.selectAll("");
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	} 
	
	else if (action.equals("login")){
		memberVO = memberDAO.getMember(memberVO);
		System.out.println("컨트롤러 로그인에서 멤버 VO출력 : "+memberVO);
		if (memberVO!=null){
			session.setAttribute("member", memberVO);
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			out.println("<script>alert('로그인 실패!');history.go(-1)</script>");
		}
	}
	
	else if (action.equals("logout")){
		session.invalidate();
		pageContext.forward("index.jsp");
	}
	
	else if (action.equals("sign")){
		if(memberDAO.insert(memberVO)){
			response.sendRedirect("control.jsp?action=main");
		}
	}
	else if (action.equals("edit")){
		System.out.println(request.getParameter("bornum"));
		//System.out.println("이거임"+boardVO);
		BoardVO data = boardDAO.selectOne(boardVO);
		// System.out.println("찐임"+data);
		request.setAttribute("data", data);
		pageContext.forward("edit.jsp");
	}
	
	else if (action.equals("delete")){
		if(boardDAO.delete(boardVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else{
			throw new Exception("DB 삭제 오류 발생!");
		}
	}
	else if (action.equals("insert")){
		if(boardDAO.insert(boardVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else{
			throw new Exception("DB 추가 오류 발생!");
		}
	}
	
	else if (action.equals("search")){
		ArrayList<BoardVO> datas = boardDAO.selectAll(content);
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");	
	}
	else if (action.equals("update")){
		if(boardDAO.update(boardVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else{
			throw new Exception("DB 업데이트 오류 발생!");
		}
	}

	else if(action.equals("change")){
		/* MemberVO member = (MemberVO)session.getAttribute("memberVO"); */
		System.out.println(memberVO);
		if(memberDAO.update(memberVO)){
			pageContext.forward("control.jsp?action=main");
			System.out.println(memberVO);
		}
		else{
			throw new Exception("change 오류 발생!");
		}
	}
	
	else if (action.equals("delete2")){
		/* MemberVO member = (MemberVO)session.getAttribute("memberVO"); */
		if(memberDAO.delete(memberVO)){
			session.invalidate();
			pageContext.forward("control.jsp?action=main");
		}
		else{
			throw new Exception("유저 딜리트 오류 발생!");
		}
	}
	
%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨트롤러</title>
</head>
<body>

</body>
</html>