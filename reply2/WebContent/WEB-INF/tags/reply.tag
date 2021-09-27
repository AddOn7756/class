<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="rid" %>
<%@ attribute name="memid" %>

<c:if test="${selUser == memid}">
	<a href="rdelete.do?rid=${rid}&mcnt=${mcnt}">삭제</a>
</c:if>