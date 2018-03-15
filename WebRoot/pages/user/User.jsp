<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户信息界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-dropdown.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/User.js"></script>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/User.css">
</head>
<!-- 你这个时候才开始有想用bootstrap的意思啊喂魂淡 -->
<body onload="getData(${empty sessionScope.user})">
	<c:choose>
		<c:when test="${!empty sessionScope.user}">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">SVN</a></li>
				<li><a href="#">iOS</a></li>
				<li><a href="#">VB.Net</a></li>
				<li class="dropdown">
					<a class="" data-toggle="dropdown" href="#">
						Java <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">Swing</a></li>
						<li><a href="#">jMeter</a></li>
						<li><a href="#">EJB</a></li>
						<li class="divider"></li>
						<li><a href="#">分离的链接</a></li>
					</ul>
				</li>
				<li><a href="#">PHP</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
				style="margin-left:500px;">前往登录</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
