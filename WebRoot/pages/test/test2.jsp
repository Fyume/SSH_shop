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
	alert("test2:"+window.ppp);
</script>
 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
		$("#file").on("change",function(){
			var file = this.files;
			for(var i=0;i<file.length;i++){
				alert(file[i].name);
			}
		});
	}
);
</script>
</head>

<body>
	<%-- <a href="${pageContext.request.contextPath }/pages/test/test.jsp">test</a> --%>
	<form action="${pageContext.request.contextPath }/bookAction_test" enctype="multipart/form-data" method="post">
		<input id="file" type="file" name="test" multiple="multiple">
		<input type="submit" value="111">
	</form>
</body>
</html>
