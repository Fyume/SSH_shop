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

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
<!-- 用下载下来的bootstrap.min.css没有图标 不知道为什么 可能是需要其他的文件支持 -->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">

</head>
<body>
	<div class="header">
		<div class="header_logo"></div>
		<div class="header_index">
			<a target="_top"
				href="${pageContext.request.contextPath}/pages/index.jsp">首页</a>
		</div>
		<div class="header_classify" onmouseover="classifyon()"
			onmouseout="classifyoff()">分类</div>
		<div class="header_random">随机</div>
		<c:if test="${sessionScope.user.u_permission }">
			<div
				style="border:1px #c0c0c0 solid; width:100px; height:30px; margin-top:10px;margin-left:100px;padding-top:5px;">
				<a href="${pageContext.request.contextPath}/pages/manager/edit.jsp">前往管理员界面</a>
			</div>
		</c:if>
		<div class="header_user">
			<div class="user_img" onmouseover="infoon()" onmouseout="infooff()">
				<span class="glyphicon glyphicon-user"></span> <span
					style="color:red;font-weight:400">${sessionScope.user.username }</span>
			</div>
			<c:if test="${!empty sessionScope.user }">
				<div class="user_message">消息</div>
				<div class="user_favorite">收藏夹</div>
				<div class="user_upload">
					<a href="${pageContext.request.contextPath}/pages/user/upload.jsp">
						<span class="glyphicon glyphicon-arrow-up">上传</span>
					</a>
				</div>
			</c:if>
		</div>
	</div>
	<div id="user_info" class="user_info" onmouseover="infoon()"
		onmouseout="infooff()">
		<c:choose>
			<c:when test="${empty sessionScope.user }">
				<div class="list_login">
					<a href="${pageContext.request.contextPath}/pages/user/login.jsp">前往登录</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="list_all">ID：${sessionScope.user.uid }</div>
				<div class="list_all">用户名:${sessionScope.user.username }</div>
				<div class="list_all">
					<!-- 未实现 -->
					<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">个人中心</a>
				</div>
				<!-- 未实现 -->
				<div class="list_half">
					<a
						href=" ${pageContext.request.contextPath}/pages/user/setting.jsp">设置</a>
				</div>
				<div class="list_half">
					<a href="${pageContext.request.contextPath}/userAction_logOut">退出</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="classify_st" id="classify_st" onmouseover="classifyon()"
		onmouseout="classifyoff()">
		<c:forEach items="${sessionScope.typelist }" var="type"
			varStatus="num">
			<div>

				<div class="class_title" style="border-right:1px #c0c0c0 solid"
					onmouseover="classUl${num.count}on()"
					onmouseout="classUl${num.count}off()">
					<a
						href="${pageContext.request.contextPath}/BookAction_selectB?type=${type.type}">${type.type }</a>
				</div>

			</div>
			<div id="class_ul${num.count}" class="class_ul${num.count}"
				onmouseover="classUl${num.count}on()"
				onmouseout="classUl${num.count}off()">

				<c:set value="${fn:split(type.type_flag,';') }" var="type_flag"></c:set>
				<c:forEach items="${type_flag }" var="flag" begin="0" end="5">
					<ul>
						<li>${flag }</li>
					</ul>
				</c:forEach>
				<c:forEach items="${type_flag }" var="flag" begin="6" end="12">
					<ul>
						<li>${flag }</li>
					</ul>
				</c:forEach>
			</div>
		</c:forEach>
		<div class="class_title"
			style="background-color:red;border-right:1px #c0c0c0 solid">
			<a href="${pageContext.request.contextPath}/workAction_getData">用户作品</a>
		</div>
	</div>
	<c:choose>
		<c:when test="${sessionScope.classfy=='用户作品'}">
			<c:forEach items="${sessionScope.worklist }" var="work">

				<div class="book_border">
					<div>
						<a
							href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
							<img class="book_img"
							src="${pageContext.request.contextPath}/images/user/workImg/${work.image }"
							alt="作品封面">
						</a>
					</div>
					<div class="book_title">
						<a
							href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
							${work.wname } </a>
					</div>
					<div class="book_description">
						<a
							href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
							${work.description } </a>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach items="${sessionScope.booklist }" var="book">
				<div class="book_border">
					<div>
						<a
							href="${pageContext.request.contextPath}/pages/user/bookAction_readBook?bid=${book.bid}">

							<img class="book_img"
							src="${pageContext.request.contextPath}/images/bookImg/${book.image }"
							alt="书本封面">
						</a>
					</div>
					<div class="book_title">
						<a
							href="${pageContext.request.contextPath}/pages/user/bookAction_readBook?bid=${book.bid}">
							${book.bname } </a>
					</div>
					<div class="book_description">
						<a
							href="${pageContext.request.contextPath}/pages/user/bookAction_readBook?bid=${book.bid}">
							${book.description } </a>
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>
