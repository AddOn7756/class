<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<String> datas=new ArrayList();
	datas.add("apple");
	datas.add("사과");
	pageContext.setAttribute("datas", datas);
%>

<ol>
<c:forEach var="v" items="${datas}">
	<li>${v}</li>
</c:forEach>
</ol>

<hr>

<form>
	<input type="number" name="num" value="0">
	<input type="submit" value="확인">
</form>
<hr>

<c:if test="${param.num%2==0}">
	짝수
</c:if>
<c:if test="${param.num%2==1}">
	홀수
</c:if>

</body>
</html>