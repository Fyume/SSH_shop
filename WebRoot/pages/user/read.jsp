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

<title>My JSP 'read.jsp' starting page</title>

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
	src="${pageContext.request.contextPath}/js/manager/edit.js"></script>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/read.css">
</head>

<body onload="page2(${sessionScope.doc_count})">
	<div>
		<c:choose>
			<c:when test="${empty param.page }">
				<c:forEach items="${sessionScope.content }" var="line" begin="0"
					end="49">
					<h4>${line }</h4>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach items="${sessionScope.content }" var="line"
					begin="${(param.page-1)*100 }" end="${param.page*100-1 }">
					<h4>${line }</h4>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<c:forEach items="${sessionScope.content }" var="line" begin="0"
			end="${sessionScope.doc_count/100}" varStatus="num">
			<div style="margin:5px;float:left;width:15px;height:10px;font-size:10px;">
			<a href="${pageContext.request.contextPath}/pages/user/read.jsp?page=${num.count }">${num.count }</a></div>
		</c:forEach>
		<input type="text" value="1" id="page" style="display:none">
		<div style="border:1px red solid;text-align:center;width:100px;height:20px;margin-left:50px;margin-top:70px;">
			<a href="${pageContext.request.contextPath}/pages/index.jsp">返回主页</a>
		</div>
		<div style="border:1px red solid;text-align:center;width:100px;height:20px;margin-left:50px;margin-top:70px;">
			<a href="${pageContext.request.contextPath}/uesrAction_addF?bid=${sessionScope.book.bid }&page=${param.page }">添加收藏</a>
		</div>
	</div>
</body>
</html>
