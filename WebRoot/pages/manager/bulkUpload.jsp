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
    
    <title>批量上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/user/upload.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/manager/edit.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/manager/bulkUpload.css">
  </head>
  <body>
   	<%-- <c:choose>
		<c:when test="${empty sessionScope.user.u_permission}">
			<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
				style="margin-left:600px;">前往登录</a>
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose> --%>
		<div class="header">
			<div class="header_logo"></div>
			<a href="${pageContext.request.contextPath}/pages/index.jsp">
				<div class="header_index">
					首页
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/managerAction_getUser">
				<div class="h_user">
					管理用户
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/managerAction_getBook">
				<div class="h_book">
					管理书本
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/managerAction_getRecord">
				<div class="h_record">
					操作历史
				</div>
			</a>
			<div class="header_user">
				<div class="user_img" onmouseover="infoon()" onmouseout="infooff()"
					onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
					<span class="glyphicon glyphicon-user"></span> <span
						style="color:red;font-weight:400">${sessionScope.user.username }</span>
				</div>
				<a href="${pageContext.request.contextPath}/pages/manager/upload.jsp">
					<div class="user_upload">
						<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
					</div>
				</a>
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
					<div class="info_img">
						<c:choose>
							<c:when test="${empty sessionScope.user.image }">
								<img id="user_img" alt="头像" style="border-radius:100%;" src="${pageContext.request.contextPath }/images/flag/user_img(default).png">
							</c:when>
							<c:otherwise>
								<img id="user_img" alt="头像" style="border-radius:100%;" src="${pageContext.request.contextPath }/images/user/userImg/${sessionScope.user.image }">
							</c:otherwise>
						</c:choose>
					</div>
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
		<div id="bu_bottom" class="bu_bottom">
			<form id="bulkForm" action="${pageContext.request.contextPath }/bookAction_test" enctype="multipart/form-data" method="post">
				<input id="image" type="file" name="image" multiple="multiple" accept="image/*">
				<div class="btn btn-default" onclick="bulkImage()">批量上传图片</div>
				<input type="submit" class="btn btn-default pull-left" value="提交">
				<div style="width:100%;height:1px;"></div>
			</form>
		</div>
  </body>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manager/bulkUpload.js"></script>
</html>
<!--
<input type="button" value="Create!!!" onclick="createDiv()">
<div class="book_div">
				 <div class="div_font">
					<div class="div_font_1">
						封面
					</div>
					<div class="div_font_1">
						书名
					</div>
					<div class="div_font_1">
						作者
					</div>
					<div class="div_font_1">
						类型
					</div>
					<div class="div_font_1" style="width:16%;">
						ISBN
					</div>
					<div class="div_font_1" style="width:20%;">
						出版时间
					</div>
					<div class="div_font_1" style="width:20%;">
						简述
					</div>
				</div> -->
				<!-- <div class="div_value">
					<div class="div_value_1">
						<img alt="" src="11">
					</div>
					<div class="div_value_1">
						<input type="text" name="blist[0].bname">
					</div>
					<div class="div_value_1">
						<input type="text" name="blist[0].author">
					</div>
					<div class="div_value_1">
						<select id="type" name="blist[0].type">
							<option value="网络小说" selected>网络小说</option>
							<option value="文学作品">文学作品</option>
							<option value="社会科学">社会科学</option>
						</select>
					</div>
					<div class="div_value_1" style="width:16%;">
						<input type="text" name="blist[0].ISBN">
					</div>
					<div id="publish" class="div_value_1" style="width:20%;">
						<input type="text" name="ylist[0]" placeholder="1990">年
						<input type="text" name="mlist[0]" placeholder="1">月
						<input type="text" name="dlist[0]" placeholder="1">日
					</div>
					<div class="div_value_1" style="width:20%;">
						<textarea name="blist[0].description" placeholder="书本概述"></textarea>
					</div>
				</div> 
			</div>-->