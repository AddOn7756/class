<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.msg.*,model.member.*"%>
<jsp:useBean id="mDAO" class="model.msg.MessageDAO" />
<jsp:useBean id="rDAO" class="model.msg.ReplyDAO" />
<jsp:useBean id="uDAO" class="model.member.MemberDAO" />
<jsp:useBean id="mVO" class="model.msg.MessageVO" />
<jsp:setProperty property="*" name="mVO" />
<jsp:useBean id="rVO" class="model.msg.ReplyVO" />
<jsp:setProperty property="*" name="rVO" />
<jsp:useBean id="uVO" class="model.member.MemberVO" />
<jsp:setProperty property="*" name="uVO" />

<%
	String action = request.getParameter("action");
	String url = "control.jsp?action=main";
	String mcntt = request.getParameter("mcnt");
	
	// mcnt = 초기에 게시글 보여지는 개수
	int mcnt= 2;
	if(mcntt !=null){
		mcnt=Integer.parseInt(mcntt);
	}
	url = url + "&mcnt=" +mcnt;
	String selUser = request.getParameter("selUser");
	if(selUser !=null){
		url= url+ "&selUser=" +selUser;
	}
	
	// action값 비교 시작
	if(action.equals("main")){
		ArrayList<MsgSet> datas = mDAO.selectAll(selUser);
		ArrayList<MemberVO> newUsers = uDAO.selectAll();
		
		request.setAttribute("datas", datas);
		request.setAttribute("newUsers", newUsers);
		request.setAttribute("selUser", selUser);
		request.setAttribute("mcnt", mcnt);
		
		pageContext.forward("main.jsp");
	}
	else if(action.equals("login")){
		// System.out.println("control login에서 uVO : "+uVO);
		if(uDAO.login(uVO)){
			session.setAttribute("selUser", uVO.getMemid());
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			out.println("<script>alert('아이디 또는 비밀번호가 맞지 않음');history.go(-1)</script>");
		}
		
	}
	else if (action.equals("logout")){
		session.invalidate();
		response.sendRedirect("control.jsp?action=main");
	}
	
	else if (action.equals("sign")){
		if(uDAO.insert(uVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			System.out.println("control sign에서 uVO : "+uVO);
		}
	}
	
	
	
	
	
	
	
%>