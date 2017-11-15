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
	src="${pageContext.request.contextPath }/js/user/activate.js"></script>
</head>

<body onload="change()">
	<div class="act_form">
		<c:if test="${param.method==1||empty param.method}">
				<div class="act_resend">
					<form
						action="${pageContext.request.contextPath}/user/userAction_resendEmail">
						<label class="form_label">
							<div class="form_label_font">用户ID:</div>
							<div class="form_label_input">
								<input id="uid" name="uid" type="text">
							</div>
							<div>
								<a
									href="${pageContext.request.contextPath}/pages/user/activate.jsp?method=2">邮箱换了？点这里</a>
							</div>
						</label>
					</form>
				</div>
		</c:if>
			<c:if test="${param.method==2}">
				<div class="act_changeEmail">
					<form
						action="${pageContext.request.contextPath}/user/userAction_updateEmail">
<div>修改邮箱</div>
<a href="${pageContext.request.contextPath}/pages/user/activate.jsp?method=1">返回</a>
					</form>
				</div>
			</c:if>
	</div>
</body>
</html>
