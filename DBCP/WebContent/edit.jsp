<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.coffee.* " %>
<jsp:useBean id="coffeeVO" class="model.coffee.CoffeeVO" scope="request" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정/삭제 화면</title>
<script type="text/javascript">
	function del(){
		result=confirm("정말로 삭제하시겠습니까?");
		if(result==true){
			document.editForm.action.value="delete";
			document.editForm.submit();
		}
		else{
			return;
		}
	}
</script>
</head>
<body>

<hr>
<form action="control.jsp" method="post" name="editForm">
<input type="hidden" name="action" value="update">
<input type="hidden" name="num" value="${data.num }">
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
		<td colspan='2'><input type="submit" value="메뉴 변경">
		<input type="button" value="메뉴삭제" onClick="del()"></td>
	</tr>
</table>
</form>
<hr>
<a href="control.jsp?action=main">뒤로가기</a>
</body>
</html>