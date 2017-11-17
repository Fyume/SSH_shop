<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/index.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user/index.css">
	
</head>
<body>
	<div class="header">
		<div class="header_index">图标</div>
		<div class="header_classify" onmouseover="classify()"onmouseout="classify()">分类</div>
		<div class="header_user">
			<div>头像</div>
			<div>用户名${sessionScope.user.username }</div>
			<div>注销</div>
		</div>
	</div>
	<div class="classify_st" id="classify_st"onmouseover="classify()"onmouseout="classify()">
	<ul>
	<li>1</li>
	<li>1</li>
	<li>1</li>
	<li>1</li>
	<li>1</li>
	<li>1</li>
	<li>1</li>
	<li>1</li>
	<li>1</li>
	</ul>
	</div>
</body>
</html>
