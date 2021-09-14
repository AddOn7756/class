<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<ol>
		<li><a href="control.jsp?action=main">전체목록보기</a></li>
	</ol>

	<hr>

	<h2>전체목록</h2>
	<c:forEach var="v" items="${datas}">
		<c:set var="m" value="${v.m}" />
		<h3>[${m.memid}] ${m.msg} &gt;&gt; [좋아요 ${m.favcount} | 댓글
			${m.replycount} | ${m.day}]</h3>
		<ol>
			<c:forEach var="r" items="${v.rlist}">
				<li>${r.memid}>> ${r.rmsg} [${r.day}]</li>
			</c:forEach>
		</ol>
	</c:forEach>
<hr>

<a href="control.jsp?action=main&mcnt=${mcnt+1}&selUser=${selUser1}">더보기&gt;&gt;</a>

<hr>

<a href="insertUser.jsp">회원가입</a> <br>
<form action="control.jsp" method="post">
<c:choose>
	<c:when test="${empty selUser}">
		<input type="hidden" name="mcnt" value="${mcnt}">
		<input type="hidden" name="action" value="login">
		<input type="text" name="memid">
		<input type="password" name="passwd">
		<input type="submit" value="로그인">	
	</c:when>
	<c:otherwise>
		${selUser}님, 환영합니다!
		<input type="hidden" name="action" value="logout">
		<input type="submit" value="로그아웃">
		<a href="control.jsp?action=main&selUser=${selUser}">내글목록보기</a>
	</c:otherwise>
</c:choose>
</form>







