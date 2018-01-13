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

<title>My JSP 'User.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/User.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/User.css">
</head>

<body onload="getData(${empty sessionScope.user})">
	<c:choose>
		<c:when test="${empty param.row }">
			<input type="text" id="row" value="1" style="display:none">
		</c:when>
		<c:otherwise>
			<input type="text" id="row" value="${param.row }" style="display:none">
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${!empty sessionScope.user}">
			<div>
				<div class="user_left">
					<div class="select_div"
						style="margin-top:0px;padding-top:10px;background-color:red;">
						<a style="color:white;font-weight:500;font-size:20px;"
							href="${pageContext.request.contextPath}/pages/index.jsp">
							返回主页 </a>
					</div>
					<div id="row1" class="select_div">
						<a
							href="${pageContext.request.contextPath}/pages/user/User.jsp?row=1">基本信息</a>
					</div>
					<div id="row2" class="select_div">
						<a
							href="${pageContext.request.contextPath}/pages/user/User.jsp?row=2">作品信息</a>
					</div>
				</div>
				<div class="user_right">
					<div>
						<div>UID：</div>
						<div>${sessionScope.user.uid }</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
				style="margin-left:500px;">前往登录</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
