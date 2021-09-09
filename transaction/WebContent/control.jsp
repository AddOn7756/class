<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.bank.* " %>
<% request.setCharacterEncoding("UTF-8"); %>    
<jsp:useBean id="abankDAO" class="model.bank.ABankDAO"/>
<jsp:useBean id="abankVO" class="model.bank.ABankVO"/>
<jsp:setProperty property="*" name="abankVO" />

<% 
	String action = request.getParameter("action");
	
	if (action.equals("main")) {
		ArrayList<ABankVO> datas = abankDAO.selectAll();
		System.out.println(datas);
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}
	else if (action.equals("toss")){
		int money = Integer.parseInt(request.getParameter("money"));
		String send = (String)request.getParameter("send");
		String receive = (String)request.getParameter("receive");
		
		if(abankDAO.trans(send, receive, money)){
			String check = "송금완료";
			session.setAttribute("check", check);
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			String check = "송금실패";
			session.setAttribute("check", check);
			response.sendRedirect("control.jsp?action=main");
		}
		
	}
	else {
		System.out.println("파라미터를 확인하라");
	}
	

%>