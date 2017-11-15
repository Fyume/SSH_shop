<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'loading.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/loading.js"></script>

<style type="text/css">
div {
	float: left;
}

.div_main {
	width: 700px;
	height: 70px;
	margin-left: 300px;
	margin-top: 200px;
	border: 3px #008080 solid;
	/* 	shadow */
}

.div_main_top {
	width: 550px;
	height: 25px;
	margin-left: 118px;
	margin-top: 3px;
	border-bottom: 1px #c0c0c0 solid;
}

.div_main_buttom {
	width: 638px;
	height: 25px;
	margin-left: 118px;
	margin-top: 5px;
}

#num {
	color: red;
}
</style>
</head>

<body onload="show()">
	<div class="div_main">
		<div class="div_main_top">
			<div class="div_functionname">${requestScope.functionname },</div>
			<div id="num"></div>
			<div>秒后自动跳转</div>
		</div>
		<div class="div_main_buttom">
			点击直接跳转<a href="<%=basePath %>${requestScope.gohere }">gooooooooooooooooo</a>
		</div>
	</div>

	<input type="hidden" id="path"
		value="<%=basePath %>${requestScope.gohere }">
</body>
</html>
