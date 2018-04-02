<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- <script type="text/javascript">
	window.ppp = 1;
	alert("test1:"+window.ppp);
</script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#header_right").click(function(){
		$("#header_right").load("/SSH_test/pages/test/test2.jsp");
	});
	$("#aaa").click(function(){
		$("#aaa").load("/SSH_test/pages/test/test3.jsp");
	});
});
</script>
</head>

<body>
	<%-- <a href="${pageContext.request.contextPath }/pages/test/test2.jsp">test2</a>
	<a href="${pageContext.request.contextPath }/userAction_test">测试一对多中一中配的外键集合实体</a>
	<c:forEach items="${sessionScope.test.work }" var="work" >
		${work.wid }<br>${work.wname }<br>${work.uploadtime }
	</c:forEach>  --%>
	<div id="header_right" style="width:300px;height:500px;border:1px red solid;">
		
	</div>
</body>
</html>
