<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="mid" %>
<%@ attribute name="memid" %>

<a href="updatemsg.do?mid=${mid}&mcnt=${mcnt}">♥</a>
<c:if test="${selUser == memid}">
	<a href="mdelete.do?mid=${mid}&mcnt=${mcnt}">삭제</a>
</c:if>