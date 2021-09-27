<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${empty selUser}">
	<form action="login.do" method="post">
		<input type="hidden" name="selUser1" value="${selUser1}">
		<input type="hidden" name="mcnt" value="${mcnt}">
		<input type="text" name="memid">
		<input type="password" name="passwd">
		<input type="submit" value="로그인">
	</form>
	</c:when>
	<c:otherwise>
		${selUser}님, 환영합니다!
		<a href="logout.do">로그아웃</a>
		<a href="main.do?mcnt=${mcnt}&selUser=${selUser}">내글목록보기</a>
	</c:otherwise>
</c:choose>