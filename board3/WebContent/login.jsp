<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<!-- 나중에 태그로 바꾸자 -->
<form action="control.jsp" method="post" name="loginForm">
	<input type="hidden" name="action" value="login">
		<table border="1">
			<tr>
				<td>ID</td>
				<td><input type="text" name="mid"></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="mpw"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
				<input type="submit" value="로그인"></td>
			</tr>
		</table>
	</form>
	<a href="sign.jsp">회원가입</a>
	<hr>
</body>
</html>