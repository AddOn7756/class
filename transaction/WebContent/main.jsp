<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.check{
	font-size:30px;
	color:red;
}
</style>
</head>
<body>

<table border="1">
	<tr>
		<th>이름</th>
		<th>잔액</th>
	</tr>
	<c:forEach var="v" items="${datas}">
		<tr>
			<td>${v.name}</td>
			<td>${v.money }</td>
		</tr>
	</c:forEach>
</table>

<form method="post" action="control.jsp">
<p>보내는 사람</p>
<select name="send">
	<c:forEach var="v1" items="${datas}">
	<option>${v1.name}</option>
	</c:forEach>
</select>
<hr>
<p>받는 사람</p>
<select name="receive">
	<c:forEach var="v2" items="${datas}">
	<option>${v2.name}</option>
	</c:forEach>
</select>
<hr>
<p>송금</p>
	<input type="hidden" name="action" value="toss">
	<input type="hidden" name="check" value="">
	<input type="text" name="money">
	<input type="submit" value="계좌이체">
</form>

<p class="check">${check}</p>

</body>
</html>