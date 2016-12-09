<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻列表</title>
<style type="text/css">
	table {
		border: 1px solid #f2f2f2;
	}
	table td{
		height: 30px;
		line-height: 30px;
		padding: 10px;
	}
	.search {
		margin-top:15px;
		width: 100%;
		line-height: 60px;
		height: 60px;
		text-align: center;
	}
	.search input {
		height: 20px;
		line-height: 20px;
		width:70%;
	    display: block;
	    padding: 6px 12px;
	    font-size: 14px;
	    line-height: 1.42857143;
	    color: #555;
	    background-color: #fff;
	    background-image: none;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
	    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	}
</style>
</head>
<body>
	<div class="search">
		<input type="text" name="searchName" />
	</div>
	<table style="width: 100%" cellpadding="0" cellspacing="0" border="1px soild #f2f2f2">
		<tr>
			<td>新闻ID</td>
			<td>新闻标题</td>
			<td>新闻类型</td>
			<td>操作</td>
		<tr>
		<c:forEach items="${mapList}" var="list">
			<tr>
				<td>${list.id}</td>
				<td>${list.title}</td>
				<td>${list.name}</td>
				<td>操作</td>
			<tr>
		</c:forEach>
	</table>
</body>
</html>