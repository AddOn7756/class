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
	int mcnt= 1;
	if(mcntt !=null){
		mcnt=Integer.parseInt(mcntt);
	}
	url = url+"&mcnt="+mcnt;
	String selUser1 = request.getParameter("selUser");
	if(selUser1 !=null){
		url= url+ "&selUser=" +selUser1;
	}
	
	// action값 비교 시작
	if(action.equals("main")){
		// System.out.println("control main : "+selUser1);
		ArrayList<MsgSet> datas = mDAO.selectAll(selUser1, mcnt);
		ArrayList<MemberVO> newUsers = uDAO.selectAll();
		
		request.setAttribute("datas", datas);
		// System.out.println(datas);
		request.setAttribute("newUsers", newUsers);
		request.setAttribute("selUser1", selUser1);
		request.setAttribute("mcnt", mcnt);
		pageContext.forward("main.jsp");
	}
	else if(action.equals("login")){
		// System.out.println("control login에서 uVO : "+uVO);
		if(uDAO.login(uVO)){
			session.setAttribute("selUser", uVO.getMemid());
			response.sendRedirect(url);
		}
		else {
			out.println("<script>alert('아이디 or 비밀번호가 맞지 않음');history.go(-1)</script>");
		}
		
	}
	else if (action.equals("logout")){
		// System.out.println("control loout에서 uVO : "+uVO);
		session.invalidate();
		response.sendRedirect("control.jsp?action=main");
	}
	
	else if (action.equals("sign")){
		if(uDAO.insert(uVO)){
			out.println("<script>alert('축하합니다');window.close()</script>");
		}
		else {
			throw new Exception("control sign에서 uVO : "+uVO);
		}
	}
	
	else if (action.equals("newmsg")){
		if(mDAO.insert(mVO)){
			response.sendRedirect(url);
		}
		else {
			throw new Exception("control newmsg에서 발생");
		}
	}
	else if (action.equals("newrmsg")){
		if (rDAO.insert(rVO)){
			response.sendRedirect(url);
		}
		else {
			throw new Exception("control newmsg에서 발생");
		}
	}
	else if (action.equals("mdelete")){
		if (mDAO.delete(mVO)){
			response.sendRedirect(url);
		}
		else {
			throw new Exception("control newmsg에서 발생");
		}
	}
	else if (action.equals("rdelete")){
		if (rDAO.delete(rVO)){
			response.sendRedirect(url);
		}
		else {
			throw new Exception("control newmsg에서 발생");
		}
	}
	else if(action.equals("updatemsg")){
		mDAO.update(mVO);
		response.sendRedirect(url);
	}
	
	
	
%>