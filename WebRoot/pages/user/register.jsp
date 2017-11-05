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
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
</head>

<body>
	${sessionScope.uid_check}
	<div class="register_body">
		<div class="register_title">用户注册</div>
		<div class="register_main">
			<form action="/useraction" method="post">
				<div class="register_dtable">
					<div class="register_table">
						<div class="register_table_left">
							<ul>
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
							<ul>
								<li><input type="text" id="uid" name="uid">
								<div class="div_flag">*</div>
								<c:if test="${sessionScope.uid_check}">用户id已存在</c:if>
								</li>
								<li><input type="text" id="username" name="username">
								<div class="div_flag">*</div></li>
								<li><input type="text" id="name" name="name">
								<div class="div_flag">*</div></li>
								<li><input type="password" id="password" name="password">
								<div class="div_flag">*</div></li>
								<li><input type="password" id="r_password"
									name="r_password">
								<div class="div_flag">*</div></li>
								<li><input type="text" id="address" name="address"></li>
								<li><input type="text" id="IDCN" name="IDCN">
								<div class="div_flag">*</div></li>
								<li><input type="text" id="telnum" name="telnum">
								<div class="div_flag">*</div></li>
								<li><input type="text" id="email" name="email">
								<div class="div_flag">*</div></li>
							</ul>
						</div>
						<div class="register_table_right">
							<li></li>
						</div>
					</div>
					<input class="register_table_submit" type="button" value="提交"
						onclick="checkuid()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>

