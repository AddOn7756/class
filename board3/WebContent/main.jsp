<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.board.*, model.member.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<jsp:useBean id="datas" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<hr>
	<table border="1">
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="v" items="${datas}">
            <tr>
               <td><a href="control.jsp?action=edit&bornum=${v.bornum}">${v.bornum}</a></td>
               <td>${v.title}</td>
               <td>${v.writer}</td>
            </tr>
         </c:forEach>
	</table>
<hr>
<!-- 로그인하기 -->
<%
	if(session.getAttribute("member")==null){
%>
<form method="post" action="login.jsp" name="loginForm">
<input type="hidden" name="action" value="login">
<input type="submit" value="로그인">
</form>
<% 
	}
	else {
%>
<form method="post" action="control.jsp" name="logoutForm">
<input type="hidden" name="action" value="logout">
<input type="submit" value="로그아웃">
</form>
<hr>
<form action="upload.jsp" method="post" name="uploadForm">
<input type="submit" value="작성하기">
</form>
<hr>
<form action="mypage.jsp" method="post" name="mypageForm">
<input type="submit" value="마이페이지">
</form>
<% 
	}
%>
<!-- 검색하기 -->
<hr>
<form method="post" action="control.jsp" name="serchForm">
<input type="hidden" name="action" value="search">
<input type="text" name="content">
<input type="submit" value="검색">
</form>


</body>
</html>