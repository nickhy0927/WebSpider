<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>
<html>
	<body>
		<h2>Hello World!</h2>
		<a href="${ctx}/platform/news/list.html">
			新闻列表
		</a>
	</body>
</html>
