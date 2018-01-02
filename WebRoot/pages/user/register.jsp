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

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/register.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/register.js"></script>
</head>

<body>
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}">
	<div class="register_body">
		<div class="register_title">用户注册</div>
		<div class="register_main">
			<form id="form" action="${pageContext.request.contextPath}/userAction_register" method="post" onsubmit="return checkform()">
				<div class="register_dtable">
					<div class="register_table">
						<div class="register_table_left">
							<ul class="u_left">
								<li>用 户 ID:</li>
								<li>用 户 名：</li>
								<li>姓 名：</li>
								<li>密 码：</li>
								<li>确认密码：</li>
								<li>地 址：</li>
								<li>身 份 证：</li>
								<li>电 话：</li>
								<li>邮 箱：</li>
							</ul>
						</div>
						<div class="register_table_center">
							<ul class="u_center">
								<li><input type="text" id="uid" name="用户ID" maxlength="8" placeholder="只能由字母数字字符下划线组成，最长8位"onchange="checkuid()">
								<div class="div_flag" id="uidwarnning">*</div>
								</li>
								<li><input type="text" id="username" name="用户名">
								<div class="div_flag">*</div>
								</li>
								<li><input type="text" id="name" name="姓名">
								<div class="div_flag">*</div>
								</li>
								<li><input type="password" id="password" name="密码" maxlength="16" placeholder="最长16位">
								<div class="div_flag">*</div>
								</li>
								<li><input type="password" id="r_password" maxlength="16" placeholder="再次输入密码"
									name="二次密码" onchange="return checkpw()">
								<div class="div_flag">*</div>
								</li>
								<li><input type="text" id="address" name="地址"></li>
								<li><input type="text" id="IDCN" name="身份证号码">
								<div class="div_flag">*</div>
								</li>
								<li><input type="text" id="telnum" name="电话" onchange="checktelnum()"></li>
								<li><input type="text" id="email" name="邮箱">
								<div class="div_flag">*</div>
								</li>
							</ul>
						</div>
					</div>
					<input id="register_table_submit" class="register_table_submit" type="submit" value="提交" 
						>
						<a href="${pageContext.request.contextPath}/pages/user/login.jsp" style="float:left;font-size:1px;margin-left:200px;">已有账号,前往登录</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

