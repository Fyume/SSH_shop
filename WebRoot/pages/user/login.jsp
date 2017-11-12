<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/user/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/login.js"></script>
</head>

<body>
	<div class="login_form">
		<form id="form" action="${pageContext.request.contextPath }/userAction_login"
			method="post" onsubmit="return checkform()">
				<div class="login_table">
					<div class="login_table_left">
						<ul>
							<li>帐 号:</li>
							<li>密 码:</li>
							<li>验证码:</li>
						</ul>
					</div>
					<div class="login_table_center">
						<ul>
							<li><input type="text" id="uid" name="用户ID"
								placeholder="用户ID"></li>
								<div id="u_warn" class="warning">${requestScope.uidpass_flag }</div>
							<li><input type="password" id="password" name="密码">
							</li>
							<li><input type="text" id="vCode_text" name="验证码"></li>
							<div id="vC_warn" class="warning">${requestScope.vCode_flag}</div>
						</ul>
						<!-- 发现li标签会限制img的大小，使得交互的大小不等于图片的大小，故单独拉出来-->
						<img id="vCode" alt="验证码" src="<%=basePath %>utilsAction_checkCode" onclick="changeimg()">
					</div>
					<input id="login_table_submit" class="login_table_submit"
						type="submit" value="登  录">
				</div>
		</form>
	</div>
</body>
</html>
