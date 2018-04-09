<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="mytags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户信息界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/User.css">
</head>
<!-- 你这个时候才开始有想用bootstrap的意思啊喂魂淡 -->
<body onload="checkUser(${empty sessionScope.user})">
	<c:choose>
		<c:when test="${!empty sessionScope.user}">
			<div id="header_right" class="header_right">
			</div>
			<!-- 不放到这个位置好像会影响div的onmouserover事件 应该是video标签的问题？ 还是加载顺序的问题？不是很清楚 -->
			<div class="second">
				<a href="${pageContext.request.contextPath}/bookAction_getData" title="在线阅读网站">
					<div class="sec_logo"></div>
					<div class="sec_font">在线阅读网站</div>
				</a>
				<div class="sec_video">
					<video width="100%" height="100%" loop="loop" autoplay="autoplay" style="object-fit:fill;">
						<source src="${pageContext.request.contextPath}/images/video/sec(2).mp4"  type="video/mp4">
					</video>
				</div>
			</div>
			<div class="User_bottom">
				<div class="bottom_list">
					<ul class="nav nav-tabs-stacked">
						<li class="active">
							<a href="${pageContext.request.contextPath}/pages/user/User.jsp">
								基本资料
							</a>
						</li>
						<li onclick="getMyWork()">
							<a>
								我的作品
							</a>
						</li>
						<li onclick="getMyFav(0)">
							<a>
								收藏夹
							</a>
						</li>
						<li class="dropdown">
							<li class="dropdown-toggle" id="CommentMenu1" data-toggle="dropdown" >
								<a>我的评论<span class="caret"></span></a>
							</li>
							<ul class="dropdown-menu" aria-labelledy="CommentMenu1" style="margin-top:-345px;">
								<li onclick="loadMyReviews(0)"><a>我的书评</a></li>
								<li onclick="loadMyReviews(1)"><a>我评论的评论</a></li>
								<li onclick="loadMyReviews(2)"><a>被回复的</a>
								<c:if test="${!empty sessionScope.updateFlag2 }">
									<div id="flag2" style="width:10px;height:10px;background-color:red;border-radius:10px;margin-top:-30px;margin-left:80px;"></div>
								</c:if></li>
							</ul>
						</li>
					</ul>
				</div>
				<div id="User_table" class="User_table">
					<c:choose>
						<c:when test="${(requestScope.list==1&&empty param.list)||(empty param.list&&empty requestScope.list)}">
							
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty param.page }" >
									<c:set var="begin" value="${(param.page-1)*8}" scope="session"></c:set>
									<c:set var="end" value="${(param.page*8)-1}" scope="session"></c:set>	
								</c:when>
								<c:otherwise>
									<c:set var="begin" value="0" scope="session"></c:set>
									<c:set var="end" value="7" scope="session"></c:set>	
								</c:otherwise>
							</c:choose>
							<c:set var="AllNum" value="0" scope="session"></c:set>
							<!-- 作品 -->
							
							<!-- 收藏夹 -->
							
							<!-- 评论消息 -->
							
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div id="work_edit_div" class="work_edit_div">
				<div class="Work_form">
					<div class="Form_close" onclick="edit_divOff()"><span class="glyphicon glyphicon-remove" style="font-size:20px;"></span></div>
					<div class="Form_img">
						<img width=100% height=100% id="Work_img" alt="作品封面" src="">
						<input type="button" value="修改封面" onclick="workImg()">
					</div>
					<form action="${pageContext.request.contextPath}/workAction_update" method="post" enctype="multipart/form-data" onsubmit="return checkForm2()">
						<input type="file" id="workimage" name="image" accept="image/*" onclick="event.cancelBubble = true">
						<ul>
							<li>
								<input id="Work_wid_t" class="hidden" type="text" name="work.wid">
							</li>
							<li>
								<span class="Form_font">作品名：</span>
								<input id="Work_wname" type="text" name="work.wname" placeholder="作品名">
							</li>
							<li>
								<span class="Form_font">描述：</span>
							</li>
							<li>
								<textarea id="Work_description" type="text" name="work.decription" placeholder="作品描述"></textarea>
							</li>
						</ul>
						<input type="submit" value="提交修改">
					</form>
				</div>
			</div>
			<c:if test="${!empty sessionScope.updateFlag }">
				<div id="flag1" class="user_Update_Flag"></div>
			</c:if>
			<c:if test="${!empty sessionScope.updateFlag2 }">
				<div id="flag2" class="user_Update_Flag" style="top:245px;"></div>
			</c:if>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
				style="margin-left:500px;">前往登录</a>
		</c:otherwise>
	</c:choose>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-dropdown.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/User.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
</body>
</html>