<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加用户</title>
	</head>
	<body>
		<form action="${ctx}/app/register" method="post">
			真实姓名<input type="text" name="realName" id="realName"/>
			登录名称<input type="text" name="loginname" id="loginname"/>
			登录密码<input type="password" name="password" id="password"/>
			<input type="submit" value="注册">
		</form>
	</body>
</html>