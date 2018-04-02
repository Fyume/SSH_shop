<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="h_r_user btn btn-default" onmouseover="infoon()" onmouseout="infooff()"
	onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
	<span class="glyphicon glyphicon-user"></span> <span
		style="color:red;font-weight:400">${sessionScope.user.username }</span>
</div>
<div class="h_r_user btn btn-default">消息</div>
<a href="${pageContext.request.contextPath}/userAction_getMyFavBy?type=0">
	<div class="h_r_user btn btn-default">收藏夹</div>
</a>
<div id="updateFlag"></div>
<div id="updateFlag2"></div>
<a href="${pageContext.request.contextPath}/pages/user/upload.jsp">
	<div class="user_upload" style="margin-top:-5px;">
		<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
	</div>
</a>
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
						<img id="user_img" alt="头像" style="border-radius:100%;"
							src="${pageContext.request.contextPath }/images/flag/user_img(default).png">
					</c:when>
					<c:otherwise>
						<img id="user_img" alt="头像" style="border-radius:100%;"
							src="${pageContext.request.contextPath }/images/user/userImg/${sessionScope.user.image }">
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
			<div class="list_btn" onclick="logout()">退出</div>
		</c:otherwise>
	</c:choose>
</div>