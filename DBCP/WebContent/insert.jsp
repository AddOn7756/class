<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>메뉴 추가</h2>
<p>추가할 메뉴 정보 입력</p>
<hr>
<form action="control.jsp" method="post" name="editForm">
<input type="hidden" name="action" value="insert">
<table border="1">
	<tr>
		<td>메뉴</td>
		<td><input type="text" name="name" value="${data.name }"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="price" value="${data.price }"></td>
	</tr>
	<tr>
		<td colspan='2' align="right"><input type="submit" value="추가하기"></td>
	</tr>
</table>
</form>
</body>
</html>