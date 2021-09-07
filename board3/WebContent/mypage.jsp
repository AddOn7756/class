<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.member.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script type="text/javascript">
	function del(){
		result=confirm("정말로 삭제하시겠습니까?");
		if(result==true){
			document.form1.action.value="delete2";
			document.form1.submit();
		}
		else{
			return;
		}
	}
</script>
</head>
<body>
<hr>
<form action="control.jsp" method="post" name="form1">
<input type="hidden" name="action" value="change">
<input type="hidden" name="memnum" value="${member.memnum }">
<table border="1">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" value="${member.name }" readonly></td>
	</tr>
	<tr>
		<td>ID</td>
		<td><input type="text" name="mid" value="${member.mid }" readonly></td>
	</tr>
	<tr>
		<td>PW</td>
		<td><input type="text" name="mpw" value="${member.mpw }"></td>
	</tr>
	<tr>
		<td colspan='2'><input type="submit" value="비번 변경">
		<input type="button" value="탈퇴" onClick="del()"></td>

</table>
</form>
<a href="control.jsp?action=main">뒤로가기</a>



</body>
</html>