<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body onload="start(${empty sessionScope.typelist })">
	<div class="header">
		<a href="${pageContext.request.contextPath}/pages/index.jsp">
			<div class="header_index">
				首页
			</div>
		</a>
		<a href="${pageContext.request.contextPath}/pages/index.jsp">
			<div class="header_classify" onmouseover="classifyon()"
				onmouseout="classifyoff()">
					全部分类
			</div>
		</a>
		<a href="${pageContext.request.contextPath}/workAction_getData">
			<div class="header_work">
				用户作品
			</div>
		</a>
			
		<!-- <div class="header_random">随机</div> -->
		
		<c:if test="${sessionScope.user.u_permission }">
			<a href="${pageContext.request.contextPath}/pages/manager/edit.jsp">
				<div class="managerPage">
					前往管理员界面
				</div>
			</a>
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
				<div class="user_favour">收藏夹</div>
				<a href="${pageContext.request.contextPath}/pages/user/upload.jsp">
					<div class="user_upload">
						<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
					</div>
				</a>
			</c:if>
		</div>
	</div>
	<!-- 不放到这个位置好像会影响div的onmouserover事件 应该是video标签的问题？ 还是加载顺序的问题？不是很清楚 -->
	<div class="second">
		<div class="sec_logo"></div>
		<div class="sec_font">在线阅读网站</div>
		<div class="sec_video">
			<video width="100%" height="100%" loop="loop" autoplay="autoplay" style="object-fit:fill;">
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
				<div class="info_img"><img alt="头像" style="border-radius:100%;width:100%;height:100%;" src="${pageContext.request.contextPath }/images/flag/user_img(default).png"> </div>
				<div class="list_half">ID：<span style="color:#0080c0;">${sessionScope.user.uid }</span></div>
				<div class="list_half">用户名:<span style="color:red;">${sessionScope.user.username }</span></div>
				<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
					<div class="list_all">
						<span style="font-weight:550;font-size:15px;">个人中心</span>
					</div>
				</a>
				<!-- 未实现暂时用User.jsp过渡 -->
				<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
					<div class="list_btn">
						设置
					</div>
				</a>
				<a href="${pageContext.request.contextPath}/userAction_logOut">
					<div class="list_btn">
						退出
					</div>
				</a>
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
						
						<!-- 还没加上去 -->
						
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${sessionScope.booklist }" var="book" begin="0" end="19">
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
					
					<!-- 准备搞个分页 -->
					<div style="width:"></div>
					<c:if test="${fn:length(sessionScope.booklist)>20}">
					</c:if>
					
					
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>