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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/login.js"></script>
</head>

<body>
<div style="float:right;width:50px;height:50px;font-size:15px;font-weight: 900;"><a href="${pageContext.request.contextPath}/pages/index.jsp">前往首页</a></div>
	<div class="login_form">
		<form id="form"
			action="${pageContext.request.contextPath }/userAction_login"
			method="post" onsubmit="return checkform()">
			<div class="login_table">
				<label class="table_label">
					<div class="table_label_font">用户ID:</div>
					<div class="table_label_input">
						<input id="uid" type="text" name="用户ID" maxlength="8" placeholder="输入用户ID">
						<a href="${pageContext.request.contextPath }/pages/user/register.jsp">注册用户</a>
					</div>
					<div id="u_warn" class="warning">${requestScope.uidpass_flag }</div>
				</label>
				<label class="table_label">
					<div class="table_label_font">密 码 :</div>
					<div class="table_label_input">
						<input id="password" type="password" name="密码" maxlength="16">
						<a href="${pageContext.request.contextPath }/pages/user/forget.jsp">忘记密码</a>
					</div>
				</label> 
				<label class="table_label">
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
					
				</label> <input id="login_table_submit" class="login_table_submit"
					type="submit" value="登  录">
					<a href="${pageContext.request.contextPath }/pages/user/activate.jsp" style="float:left;margin-left:200px;margin-top:7px;">帐号未激活？去激活</a>
			</div>
		</form>
	</div>
</body>
</html>
