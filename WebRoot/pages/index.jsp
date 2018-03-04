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

<title>Index.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
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
<body onload="start(${empty sessionScope.typelist })">
	<div class="header">
		<div class="header_index">
			<a target="_top"
				href="${pageContext.request.contextPath}/pages/index.jsp">首页</a>
		</div>
		<div class="header_classify" onmouseover="classifyon()"
			onmouseout="classifyoff()">
			<a target="_top" href="${pageContext.request.contextPath}/pages/index.jsp">
				全部分类
			</a>
		</div>
		<div class="header_work">
			<a href="${pageContext.request.contextPath}/workAction_getData">
				用户作品
			</a>
		</div>
			
		<!-- <div class="header_random">随机</div> -->
		
		<c:if test="${sessionScope.user.u_permission }">
			<div class="managerPage">
				<a href="${pageContext.request.contextPath}/pages/manager/edit.jsp">前往管理员界面</a>
			</div>
		</c:if>
		<!-- 未实现 -->
		<div class="header_select">
			<div class="select_text">
				<input id="select_message" type="text" name="select_message" placeholder="输入作品名/书名(1-20个字符、数字)"
					onblur="check_selecttext()">
			</div>
			<div class="select_select">
				<select id="select_select">
					<option value="1">书名</option>
					<option value="2">作品名</option>
					<option value="3">作者</option>
				</select>
			</div>
			<div class="select_button">
				<input type="button" value="搜索" onclick="selectmess()"
					style="background-color:#80ffff;">
			</div>
		</div>
		<div class="header_user">
			<div class="user_img" onmouseover="infoon()" onmouseout="infooff()" onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
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
	<div class="second">
		<div class="sec_logo"></div>
		<div class="sec_font">在线阅读网站</div>
		<div class="sec_video">
			<video width="100%" height="100%" loop="loop" autoplay="autoplay" poster="xx.png" style="object-fit:fill;">
				<source src="${pageContext.request.contextPath}/images/video/sec(2).mp4"  type="video/mp4">
			</video>
		</div>
	</div>
	<div id="user_info" class="user_info" onmouseover="infoon()" onmouseout="infooff()">
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
					<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">个人中心</a>
				</div>
				<!-- 未实现暂时用User.jsp过渡 -->
				<div class="list_half">
					<a
						href="${ pageContext.request.contextPath}/pages/user/User.jsp">设置</a>
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
					onmouseover="classUlon(${num.count})"
					onmouseout="classUloff(${num.count})">
					<a
						href="${pageContext.request.contextPath}/bookAction_selectB?flag=type&message=${type.type}">${type.type }</a>
				</div>

			</div>
			<div id="class_ul${num.count}" class="class_ul${num.count}"
				onmouseover="classUlon(${num.count})"
				onmouseout="classUloff(${num.count})">
		<!-- 数据库里面用";"分开 -->
				<c:set value="${fn:split(type.type_flag,';') }" var="type_flag"></c:set>
				<c:forEach items="${type_flag }" var="flag" begin="0" end="5">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/bookAction_selectB?flag=type_flag&message=${type.type}">${flag }</a></li>
					</ul>
				</c:forEach>
				<c:forEach items="${type_flag }" var="flag" begin="6" end="12">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/bookAction_selectB?flag=type_flag&message=${type.type}">${flag }</a></li>
					</ul>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<div class="bottom">
		<div class="totalBook">
			<c:choose>
				<c:when test="${sessionScope.classfy=='用户作品'}">
					<c:forEach items="${sessionScope.worklist }" var="work">
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${sessionScope.booklist }" var="book">
						<div class="book_border">
							<div class="book_title">
								<a
									href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
									${book.bname } </a>
							</div>
							<div class="book_img">
								<a href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
									<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
								</a>
							</div>
							<div class="book_description">
								<span class="desc_font">作者</span>
								<br>
								<span class="desc_value">${book.author }</span>
								<br>
								<span class="desc_font">概述</span>
								<br>
								<div class="desc_value">${book.description }</div>
								<br>
								<span class="desc_font">分类</span>
								<br>
								<span class="desc_value" style="font-size:13px;font-weight: 600;color:#008000;">${book.type }</span>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>