<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/mytags" prefix="mytags"%>
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

<title>用户信息界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-dropdown.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/User.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/User.css">
</head>
<!-- 你这个时候才开始有想用bootstrap的意思啊喂魂淡 -->
<body>
	<c:choose>
		<c:when test="${!empty sessionScope.user}">
			<div class="header">
				<a href="${pageContext.request.contextPath}/pages/index.jsp">
					<div class="header_index">首页</div>
				</a> 
				<!-- <div class="header_random">随机</div> -->
		
				<c:if test="${sessionScope.user.u_permission }">
					<a href="${pageContext.request.contextPath}/pages/manager/edit.jsp">
						<div class="managerPage">前往管理员界面</div>
					</a>
				</c:if>
				<!-- 未实现 -->
				<div class="header_select">
					<div class="select_text">
						<input id="select_message" type="text" name="select_message"
							placeholder="输入作品名/书名(1-20个字符、数字)" onblur="check_selecttext()">
					</div>
					<div class="select_select">
						<select id="select_select">
							<option value="1">书名</option>
							<option value="2">作品名</option>
							<option value="3">作者</option>
						</select>
					</div>
					<div class="select_button">
						<input type="button" value="搜索" onclick="selectmess()"
							style="background-color:#80ffff;">
					</div>
				</div>
				<div class="header_user">
					<div class="user_img" onmouseover="infoon()" onmouseout="infooff()"
						onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
						<span class="glyphicon glyphicon-user"></span> <span
							style="color:red;font-weight:400">${sessionScope.user.username }</span>
					</div>
					<c:if test="${!empty sessionScope.user }">
						<div class="user_message">消息</div>
						<div class="user_favour">收藏夹</div>
						<a href="${pageContext.request.contextPath}/pages/user/upload.jsp">
							<div class="user_upload">
								<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
							</div>
						</a>
					</c:if>
				</div>
			</div>
			<!-- 不放到这个位置好像会影响div的onmouserover事件 应该是video标签的问题？ 还是加载顺序的问题？不是很清楚 -->
			<div class="second">
				<div class="sec_logo"></div>
				<div class="sec_font">在线阅读网站</div>
				<div class="sec_video">
					<video width="100%" height="100%" loop="loop" autoplay="autoplay"
						style="object-fit:fill;"> <source
						src="${pageContext.request.contextPath}/images/video/sec(2).mp4"
						type="video/mp4"></video>
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
						<div class="list_half">
							ID：<span style="color:#0080c0;">${sessionScope.user.uid }</span>
						</div>
						<div class="list_half">
							用户名:<span style="color:red;">${sessionScope.user.username }</span>
						</div>
						<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
							<div class="list_all">
								<span style="font-weight:550;font-size:15px;">个人中心</span>
							</div>
						</a>
						<!-- 未实现暂时用User.jsp过渡 -->
						<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
							<div class="list_btn">设置</div>
						</a>
						<a href="${pageContext.request.contextPath}/userAction_logOut">
							<div class="list_btn">退出</div>
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="User_bottom">
				<span id="listNum" title="${param.list }"></span>
				<div class="bottom_list">
					<ul class="nav nav-tabs-stacked">
						<li class="active">
							<a href="${pageContext.request.contextPath}/pages/user/User.jsp?list=1">
								基本资料
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/pages/user/User.jsp?list=2">
								作品
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/pages/user/User.jsp?list=3">
								收藏夹
							</a>
						</li>
						<!-- <li class="dropdown"><a class="" data-toggle="dropdown"
							href="#"> Java <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="#">Swing</a></li>
								<li><a href="#">jMeter</a></li>
								<li><a href="#">EJB</a></li>
								<li class="divider"></li>
								<li><a href="#">分离的链接</a></li>
							</ul></li>
						<li><a href="#">PHP</a></li> -->
					</ul>
				</div>
				<div class="User_table">
					<c:if test="${empty param.list||param.list==1 }">
						<form action="${pageContext.request.contextPath }/userAction_update" method="post" enctype="multipart/form-data"
								onsubmit="return checkForm()">
							<div style="width:10%;height:20%;">
								<c:choose>
									<c:when test="${empty sessionScope.user.image }">
										<img id="User_img" alt="头像" src="${pageContext.request.contextPath }/images/flag/user_img(default).png">
									</c:when>
									<c:otherwise>
										<img id="User_img" alt="头像" src="${pageContext.request.contextPath }/images/user/userImg/${sessionScope.user.image }">
									</c:otherwise>
								</c:choose>
								<div class="file_button">
									<input type="button" value="修改头像" onclick="uploadi()"> <br>
									<div style="font-size:12px;color:red;">(仅限jpg,jpeg)</div>
									<input type="file" id="image" name="image" accept="image/*"
									style="display:none;">
								</div>
							</div>
							<div id="User_AllInfo" class="User_AllInfo">
									<span class="User_font">用户ID：</span>
									<input type="text" name="user.uid" style="display:none;" value="${sessionScope.user.uid }">
									<span class="User_value">${sessionScope.user.uid }</span>
									<br><br>
									<span class="User_font">用户名：</span>
									<span class="User_value"><input type="text" disabled="disabled" name="user.username" value="${sessionScope.user.username }"></span>
									<br><br>
									<span class="User_font">姓名：</span>
									<span class="User_value"><input type="text" disabled="disabled" name="user.name" value="${sessionScope.user.name }"></span>
									<br><br>
									<span class="User_font">地址：</span>
									<span class="User_value"><input type="text" disabled="disabled" name="user.address" value="${sessionScope.user.address }"></span>
									<br><br>
									<span class="User_font">身份证：</span>
									<span class="User_value">
										<input type="text" name="hide" disabled="disabled" value="<mytags:hide value='${sessionScope.user.IDCN }'></mytags:hide>">
										<input type="text" name="user.IDCN" class="edit_text" value="${sessionScope.user.IDCN }">
									</span>
									<br><br>
									<span class="User_font">手机号：</span>
									<span class="User_value">
										<input type="text" name="hide" disabled="disabled" name="user.telnum" value="<mytags:hide value='${sessionScope.user.telnum }'></mytags:hide>">
										<input type="text" name="user.telnum" class="edit_text" value="${sessionScope.user.telnum }" onchange="checktelnum()">
									</span>
									<br><br>
									<span class="User_font">邮箱：</span>
									<span class="User_value"><mytags:hide value="${sessionScope.user.email }"></mytags:hide></span>
									<a style="float:right;margin-right:30%;padding-top:5px;"href="${pageContext.request.contextPath }/pages/user/activate.jsp">修改邮箱</a>
									<br><br>
									<span class="User_font">激活时间：</span>
									<span class="User_value"><mytags:date value="${sessionScope.user.activateTime }"></mytags:date></span>
									<br><br>
							</div>
							<input class="User_btn" id="edit_btn" type="button" value="进入编辑" onclick="edit()">
							<br><br>
							<input class="User_btn" id="submit_btn" type="submit" value="提交" disabled="disabled" style="opacity:0.5;">
						</form>
					</c:if>
					<!-- 作品 -->
					<c:if test="${param.list==2 }">
					
					</c:if>
					<!-- 收藏夹 -->
					<c:if test="${param.list==3 }">
					
					</c:if>
					<div style="float:left;width:130px;height:190px;border-right:2px green solid;">
						<div style="float:left;width:90%;height:75%;border:1px red solid;">
							<img alt="wid" src="${pageContext.request.contextPath }/images/background/bookimg-default.jpg">
						</div>
						<div style="margin-top:-150px;margin-left:80%;width:20px;height:20px;border:1px black solid;border-radius:20px;text-align:center;cursor:pointer;">
							<span class="glyphicon glyphicon-remove"></span>
						</div>
						<div style="float:left;width:90%;height:13%;text-align:center;">title</div>
						<div style="float:left;width:90%;height:13%;text-align:center;font-size:12px;color:#c0c0c0;">2018-03-23 21:24:00</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
				style="margin-left:500px;">前往登录</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
