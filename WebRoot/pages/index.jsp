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
		<div class="header_logo"></div>
		<div class="header_index"><a href="${pageContext.request.contextPath}/pages/index.jsp">首页</a></div>
		<div class="header_classify" onmouseover="classifyon()" onmouseout="classifyoff()">分类</div>
		<div class="header_random" >随机</div>
		<div class="header_user">
			<div class="user_img" onmouseover="infoon()" onmouseout="infooff()">头像</div>
			<div class="user_message">消息</div>
			<div class="user_favorite">收藏夹</div>
			<div class="user_logout"><a href="${pageContext.request.contextPath}/pages/user/upload.jsp">上传</a></div>
		</div>
	</div>
	<div id="user_info" class="user_info" onmouseover="infoon()" onmouseout="infooff()">
	<div class="list_all">ID：。。。。</div>
	<div class="list_all">用户名${sessionScope.user.username }</div>
	<div class="list_all">个人中心</div>
	<div class="list_half">设置</div>
	<div class="list_half">退出</div>
	</div>
	<div class="classify_st" id="classify_st"  onmouseover="classifyon()" onmouseout="classifyoff()">
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
