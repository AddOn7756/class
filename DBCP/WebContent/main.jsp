<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.coffee.*, java.util.*,java.sql.*,javax.sql.*,javax.naming.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="datas" class="java.util.ArrayList" scope="request" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h2>안녕하세요~! 애돈커피입니다.</h2>
<hr>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>가격</th>
		</tr>
		<c:forEach var="v" items="${datas}">
            <tr>
               <td><a href="control.jsp?action=edit&num=${v.num}">${v.num}</a></td>
               <td>${v.name}</td>
               <td>${v.price}</td>
            </tr>
         </c:forEach>
	</table>
<hr>
<form method="post" action="insert.jsp" name="insertForm">
<input type="submit" value="메뉴 추가">
</form>
<hr>

</body>
</html>