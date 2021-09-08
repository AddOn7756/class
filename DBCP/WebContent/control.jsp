<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.coffee.*,java.util.* "%>
<% 
	request.setCharacterEncoding("UTF-8"); 
	String coffeeName = request.getParameter("coffeeName");
%>
<jsp:useBean id="coffeeDAO" class="model.coffee.CoffeeDAO" />
<jsp:useBean id="coffeeVO" class="model.coffee.CoffeeVO" />
<jsp:setProperty property="*" name="coffeeVO"/>

<% 
	String action = request.getParameter("action");
	
	if (action.equals("main")){
		ArrayList<CoffeeVO> datas = coffeeDAO.selectAll("");
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}
	else if (action.equals("edit")){
		CoffeeVO data = coffeeDAO.selectOne(coffeeVO);
		request.setAttribute("data", data);
		pageContext.forward("edit.jsp");
	}
	else if(action.equals("update")){
		if(coffeeDAO.update(coffeeVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else{
			throw new Exception("control.jsp 업데이트 오류 발생!");
		}
	}
	else if(action.equals("delete")){
		if(coffeeDAO.delete(coffeeVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			throw new Exception("control.jsp 딜리트 오류 발생!");
		}
	}
	else if(action.equals("insert")){
		if(coffeeDAO.insert(coffeeVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			throw new Exception("control.jsp 인서트 오류 발생!");
		}
	}
	else {
		System.out.println("파라미터 값 확인");
	}
%>







<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>