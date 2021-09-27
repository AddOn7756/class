<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
</style>

</head>
<body>

<div class="content">

	<ol>
		<li><a href="main.do">로고</a></li>
		<li><a href="#" onclick="window.open('insertUser.jsp', '_blank', 'width=600 height=600')">회원가입</a></li>
	</ol>

	<hr>

	<h2>전체목록</h2>
	<c:forEach var="v" items="${datas}">
		<c:set var="m" value="${v.m}" />
		<h3>[${m.memid}] ${m.msg} &gt;&gt; [좋아요 ${m.favcount} | 댓글
			${m.replycount} | ${m.day}] <mytag:msg mid="${m.mid}" memid="${m.memid}" /></h3>
		<ol>
		<!-- 댓글 삭제-->
			<c:forEach var="r" items="${v.rlist}">
				<li>${r.memid} >> ${r.rmsg} [${r.day}]
				<mytag:reply rid="${r.rid}" memid="${r.memid}" />
				</li>
			</c:forEach>
		</ol>
		<!-- 댓글 작성 -->
			<form method="post" action="newrmsg.do">
				<input type="hidden" name="mcnt" value="${mcnt}">
				<input type="hidden" name="mid" value="${m.mid }">
				<input type="hidden" name="memid" value="${selUser }">
				<mytag:insert type="rmsg"/>
			</form>
			
	</c:forEach>
	
<hr>

<a href="main.do?mcnt=${mcnt+1}&selUser=${selUser1}">더보기&gt;&gt;</a>

<hr>
	<form method="post" action="newmsg.do">
		<input type="hidden" name="memid" value="${selUser}">
		<input type="hidden" name="mcnt" value="${mcnt}">
		<mytag:insert type="msg" />
	</form>

<hr>

<!-- 로그인 폼 -->
<mytag:login />

<hr>

<h4>신규 회원 목록</h4>

<ol>
	<c:forEach var="u" items="${newUsers}">
		<li><a href="main.do?mcnt=${mcnt}&selUser=${u.memid}">${u.name}</a>님 가입</li>
	</c:forEach>
</ol>

</div>

<div class="ad">
	<ul>
		<li><a href="https://www.naver.com/"><img alt="네이버_광고" src="img/naver.png" style="width:200px;height:200px;"></a></li>
		<li><a href="https://www.youtube.com/watch?v=dIVSn0T_kCI"><img alt="대한민국_소개" src="img/youtube.png" style="width:200px;height:200px;"></a></li>
		<li><a href="https://www.op.gg/"><img alt="opgg_광고" src="img/op.jpg" style="width:200px;height:200px;"></a></li>
	</ul>
</div>
