<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<title>用户上传作品</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/upload.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/upload.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
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
		<div class="header_user">
			<div class="user_img" onmouseover="infoon()" onmouseout="infooff()">
				<span class="glyphicon glyphicon-user"></span>
				 <span style="color:red;font-weight:400">${sessionScope.user.username }</span>
			</div>
			<c:if test="${!empty sessionScope.user }">
				<div class="user_message">消息</div>
				<div class="user_favorite">收藏夹</div>
				<div class="user_logout">
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
			用户作品
		</div>
	</div>
	<c:if test="${!empty sessionScope.user}">
	<input id="uploadResult" type="text" value="${requestScope.uploadResult}" style="display:none">
	<div class="upload_form">
		<form action="${pageContext.request.contextPath }/workAction_upload_U"
			method="post" enctype="multipart/form-data" onsubmit="return check()">
			<div class="upload_title">
				<div class="title_font">标题/作品名 ：</div>
				<div class="title_input">
					<input id="upload_title" name="upload_title" type="text" maxlength="15"
						placeholder="输入标题/作品名" onblur="checktitle()">
				</div>
				<div id="title_F" class="upload_flag_default"></div>
				<div id="title_warning" class="title_warning"></div>
			</div>
			<img id="upload_img" alt="预览图"
				src="${pageContext.request.contextPath }/images/background/bookimg-default.jpg">
			<div class="img_button">
				<input type="button" value="上传封面" onclick="uploadi()">
				<br>
				<div style="font-size:12px;color:red;">(仅限jpg,jpeg)</div>
			</div>
			<input type="file" id="image" name="image"
				accept="image/*" style="display:none;">
			<div class="file_button">
				<input type="button" value="上传作品" style="width:200px;height:30px;cursor: pointer;"
					onclick="uploadf()"> <br>
				<div style="font-size:12px;color:red;margin-left:30px;cursor: default;">(仅限doc,docx,txt)</div>
				<div id="file_F" style="margin-top:-40px;margin-left:203px;cursor: default;" class="upload_flag_default"></div>
			</div>
			<input type="file"
				accept="application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain"
				id="upload" name="upload" style="display:none;"onchange="checkF()"> 
				<br>
			<div class="upload_desc">
				<div class="desc_font">书本概述:</div>
				<div style="margin-top:5px;">
					<textarea name="description" class="desc_content" placeholder="输入概述"></textarea>
				</div>
			</div>
			<div class="upload_button">
				<input type="submit" value="提交">
			</div>
			<div style="width:120px;height:30px;margin-left:-100px;margin-top:230px;">
				<a href="${pageContext.request.contextPath }/pages/user/editWork.jsp" style="color:blue;">修改上传作品信息</a>
			</div>
		</form>
	</div>
	</c:if>
</body>
</html>
