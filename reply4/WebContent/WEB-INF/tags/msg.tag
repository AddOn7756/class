<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="mid" %>
<%@ attribute name="memid" %>

<a href="control.jsp?action=updatemsg&mid=${mid}&mcnt=${mcnt}">좋아요</a>
<c:if test="${selUser == memid}">
	<a href="control.jsp?action=mdelete&mid=${mid}&mcnt=${mcnt}">삭제</a>
</c:if>