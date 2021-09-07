<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.member.* " %>
<jsp:useBean id="boardVO" class="model.board.BoardVO" scope="request" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%-- <jsp:useBean id="memberVO" class="model.member.MemberVO" scope="session" /> --%>
<%-- <%
	MemberVO member = (MemberVO)session.getAttribute("member");
	boolean flag = false;
	if(member.getMid()!=null){
		flag = member.getMid().equals(boardVO.getWriter());
	}
/* 	System.out.println("edit.jsp boolean에서 멤버 아이디 출력 "+member.getMid());
	System.out.println("deit.jsp boolean에서 작성자 출력 "+boardVO.getWriter());
	System.out.println("deit.jsp boolean에서 작성자 출력 "+boardVO.getBornum());
	System.out.println("deit.jsp boolean에서 작성자 출력 "+boardVO.getTitle());
	System.out.println("deit.jsp boolean에서 작성자 출력 "+boardVO.getContent()); */
%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정/삭제 화면</title>
<script type="text/javascript">
	function del(){
		result=confirm("정말로 삭제하시겠습니까?");
		if(result==true){
			document.form1.action.value="delete";
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
<input type="hidden" name="action" value="update">
<input type="hidden" name="bornum" value="${data.bornum }">
<table border="1">
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer" value="${data.writer }"></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" name="title" value="${data.title }"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><input type="text" name="content" value="${data.content }"></td>
	</tr>
	<tr>
	
<%-- 	<c:if test="${not empty member }">
		<td colspan='2'><input type="submit" value="내용 변경하기">
		<input type="button" value="글 삭제하기" onClick="del()"></td>
	</c:if> --%>
	
	<c:if test="${member.mid == data.writer }">
		<td colspan='2'><input type="submit" value="내용 변경하기">
		<input type="button" value="글 삭제하기" onClick="del()"></td>
	</c:if>
	
<%-- 		<%
		if(flag){
		%>
		<td colspan='2'><input type="submit" value="내용 변경하기">
		<input type="button" value="글 삭제하기" onClick="del()"></td>
		<%
		}
		%>	 --%>
	</tr>
	
</table>
</form>
<a href="control.jsp?action=main">뒤로가기</a>
</body>
</html>