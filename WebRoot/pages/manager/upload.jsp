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

<title>管理员上传书本</title>

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
	src="${pageContext.request.contextPath}/js/manager/edit.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/upload.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/upload.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/manager/edit.css">
</head>
<body>
	<div class="header">
			<div class="header_logo"></div>
			<div class="header_index">
				<a target="_top"
					href="${pageContext.request.contextPath}/pages/index.jsp">首页</a>
			</div>
			<div class="h_user">
				<a href="${pageContext.request.contextPath}/managerAction_getUser">管理用户</a>
			</div>
			<div class="h_book">
				<a href="${pageContext.request.contextPath}/managerAction_getBook">管理书本</a>
			</div>
			<div class="header_user">
				<div class="user_img" onmouseover="infoon()" onmouseout="infooff()">
					<span class="glyphicon glyphicon-user"></span> <span
						style="color:red;font-weight:400">${sessionScope.user.username }</span>
				</div>
				<c:if test="${!empty sessionScope.user }">
					<div class="user_upload">
						<a
							href="${pageContext.request.contextPath}/pages/manager/upload.jsp">
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
					onmouseover="classUl${num.count}on()"
					onmouseout="classUl${num.count}off()">
					<a
						href="${pageContext.request.contextPath}/bookAction_selectB?type=${type.type}">${type.type }</a>
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
	<div class="upload_form2">
		<form action="${pageContext.request.contextPath }/bookAction_upload_U"
			method="post" enctype="multipart/form-data" onsubmit="return check()">
			<div class="upload_title">
				<div class="title_font">标题/作品名 ：</div>
				<div class="title_input">
					<input id="upload_title" name="upload_title" type="text" maxlength="15"
						placeholder="输入标题/书名(1-20个字符、数字)" onblur="checktitle()">
				</div>
				<div id="title_F" class="upload_flag_default"></div>
				<div id="title_warning" class="title_warning"></div>
			</div>
			<img id="upload_img" alt="预览图"
				src="${pageContext.request.contextPath }/images/background/bookimg-default.jpg">
			<div class="img_button">
				<input type="button" value="上传封面" onclick="uploadi()" style="width:100px;margin-right:-18px;">
				<br>
				<div style="margin-left:5px;font-size:12px;color:red;">(仅限jpg,jpeg)</div>
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
			
			<div style="margin-top:20px;">
				<div class="upload_font">出版日期:</div>
				<div style="margin-left:5px;width:310px;height:25px;">
				<input type="text" id="year" name="year" class="date" style="width:40px;">年
				<input type="text" id="month" name="month" class="date" style="width:30px;">月
				<input type="text" id="day" name="day" class="date" style="width:30px;">日
				<div style="font-size:12px;margin-left:190px;margin-top:-20px;color:blue">例如1990;1;2</div>
				</div>
			</div>
			<div>
				<div class="upload_font">ISBN:</div>
				<div style="margin-left:5px;width:210px;height:25px;">
				<input type="text" name="ISBN">
				</div>
			</div>
			<br>
			<div>
			    <div class="upload_font" style="text-align:center">类别:</div>
				<select id="type" name="type" class="upload_font">
					<option value="网络小说" selected>网络小说</option>
					<option value="文学作品">文学作品</option>
					<option value="社会科学">社会科学</option>
				</select>
			</div>
			<div>
				<div class="upload_font">作者:</div>
				<input type="text" id="author" name="author" style="width:80px;">
			</div>
			<div class="upload_button2">
				<input type="submit" value="提交">
			</div>
			<div style="width:120px;height:30px;margin-left:-100px;margin-top:115px;">
				<a href="${pageContext.request.contextPath }/managerAction_getBook" style="color:blue;">修改上传书本信息</a>
			</div>
		</form>
	</div>
	</c:if>
</body>
</html>
