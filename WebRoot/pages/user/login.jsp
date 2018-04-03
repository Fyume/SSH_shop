<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body onload="checkUser(${empty sessionScope.user})">
	<a class="a_login" href="${pageContext.request.contextPath}/pages/index.jsp">前往首页</a>
	<div class="webName">在 线 阅 读</div>
	<div id="login_form" class="login_form">
		<form id="form"
			action="${pageContext.request.contextPath }/userAction_login"
			method="post" onsubmit="return checkform()">
			<div class="login_table">
				<div class="table_label">
					<div class="table_label_font">帐 号 :</div>
					<div class="table_label_input">
						<input id="uid" type="text" name="user.uid" maxlength="8" placeholder="输入用户ID">
						<a href="${pageContext.request.contextPath }/pages/user/register.jsp">注册用户</a>
					</div>
					
					<div id="u_warn" class="warning">${requestScope.uidpass_flag }</div>
				</div>
				<div class="table_label">
					<div class="table_label_font">密 码 :</div>
					<div class="table_label_input">
						<input id="password" type="password" name="user.password" maxlength="16">
						<a href="${pageContext.request.contextPath }/pages/user/forget.jsp">忘记密码</a>
					</div>
				</div>
				<div class="table_label">
					<div class="table_label_font">验证码:</div>
					<div class="table_label_input">
						<input type="text" id="vCode_text" name="验证码">
					</div>
					<div>
						<!-- 发现li标签会限制img的大小，使得交互的大小不等于图片的大小，故单独拉出来-->
						<img id="vCode" alt="验证码"
							src="<%=basePath %>uutils_checkCode" onclick="changeimg()">
					</div>
					<div id="vC_warn" class="warning">${requestScope.vCode_flag}</div>
					
				</div>
				<input id="login_table_submit" class="login_table_submit" type="submit" value="登  录">
				<a href="${pageContext.request.contextPath }/pages/user/activate.jsp" style="float:left;margin-left:200px;margin-top:7px;">帐号未激活？去激活</a>
				<input type="checkbox" name="checkbox" value="1" checked="checked"><span style="font-size:14px;">记住我</span>
				<input type="text" name="www" value="login" style="display:none;">
			</div>
		</form>
	</div>
	<c:if test="${!empty sessionScope.user}">
		<div id="login_form2" class="login_form2">
			<div class="img_div">
				<c:choose>
					<c:when test="${empty sessionScope.user.image}">
						<img src="${pageContext.request.contextPath}/images/flag/user_img(default).png">
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/images/user/userImg${sessionScope.user.image}">
					</c:otherwise>
				</c:choose>
				<div class="userName">${sessionScope.user.username }</div>
				<div class="btn btn-default" onclick="logout()">切换账号<span class="glyphicon glyphicon-sort" style="transform:rotate(90deg)"></span></div>
			</div>
		</div>
	</c:if>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/login.js"></script>
</body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/user/login.css">
</html>
