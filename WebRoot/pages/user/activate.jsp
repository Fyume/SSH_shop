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

<title>激活页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/user/activate.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/user/activate.js"></script>
</head>

<body onload="change()">
	<div class="act_form">
		<div class="act_step">
			<div class="step2" id="step1">
				<div class="stepinner">第一步</div>
			</div>
			<div class="step" id="step2">
				<div class="stepinner">第二步</div>
			</div>
			<div class="step" id="step3">
				<div class="stepinner">第三步</div>
			</div>
		</div>
		<c:if test="${param.method==1||empty param.method}">
			<div class="title">重发激活邮件</div>
			<div class="act_resend" id="act_resend1">
				<form id="form1"
					action="${pageContext.request.contextPath}/userAction_resendEmail"
					method="post">
					<label class="form_label">
						<div class="form_label_font">用户ID:</div>
						<div class="form_label_input">
							<input id="uid1" name="uid1" type="text">
						</div>
						<div>
							<input type="button" value="下一步" class="button" onclick="step2()">
						</div>
						<div>
							<a
								href="${pageContext.request.contextPath}/pages/user/activate.jsp?method=2">邮箱换了？点这里</a>
						</div>
					</label>
				</form>
			</div>
			<div class="act_resend" id="act_resend2">
				<label class="form_label">
					<div>
						<input type="button" class="button" value="重发激活邮件"
							onclick="step3()"style="margin-left:40px;">
					</div>
					<div>
						<input type="button" class="button" value="返回" onclick="step1()">
					</div>
				</label>
			</div>
			<div class="act_resend" id="act_resend3">
				<label class="form_label">
					<div>激活邮件已重新发送,有效时间10分钟,请注意查收</div>
				</label>
			</div>
		</c:if>
		<c:if test="${param.method==2}">
			<div class="title">修改邮箱</div>
			<div class="act_changeE">
				<form id="form2"
					action="${pageContext.request.contextPath}/userAction_updateEmail">
					<label class="form_label" id="form_label1">
						<div class="form_label_font">用户ID:</div>
						<div class="form_label_input">
							<input id="uid2" name="uid2" type="text">
						</div>
						<div>
							<input type="button" value="下一步" class="button"
								onclick="c_step2()">
						</div>
						<div>
							<a
								href="${pageContext.request.contextPath}/pages/user/activate.jsp?method=1">前往重新激活</a>
						</div>
					</label> <label class="form_label" id="form_label2">
						<div class="form_label_font">新邮箱:</div>
						<div class="form_label_input">
							<input id="email" name="email" type="text" onchange="AjaxE()">
						</div>
						<div>
							<input type="button" value="发送激活邮件" class="button"
								onclick="c_step3()">
						</div>
						<div>
							<a
								href="${pageContext.request.contextPath}/pages/user/activate.jsp?method=1">前往重新激活</a>
						</div>
						<div id="Ewarnning" class="div_flag"></div>
					</label> <label class="form_label" id="form_label3">
						<div>激活邮件已发送至新邮箱,有效时间10分钟,请注意查收</div>
					</label>
				</form>
			</div>
		</c:if>
	</div>
	<div class="login_a">
		<a href="${pageContext.request.contextPath }/pages/user/login.jsp">
			前往登录</a>
	</div>
	<input id="contextPath" type="hidden"
		value="${pageContext.request.contextPath}">
</body>
</html>