<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<title>My JSP 'edit.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manager/edit.js"></script>
<!-- 用下载下来的bootstrap.min.css没有图标 不知道为什么 可能是需要其他的文件支持 -->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/manager/edit.css">
</head>

<body onload="page()">
	<c:if test="${empty sessionScope.user }">
		<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
			style="margin-left:500px;">前往登录</a>
	</c:if>
	<c:if test="${sessionScope.user.u_permission }">
		<div class="header">
			<div class="header_logo"></div>
			<div class="header_index">
				<a target="_top"
					href="${pageContext.request.contextPath}/pages/index.jsp">首页</a>
			</div>
			<div class="h_user">
				<a href="${pageContext.request.contextPath}/managerAction_getUser">管理用户</a>
			</div>
			<div class="h_book">
				<a href="${pageContext.request.contextPath}/managerAction_getBook">管理书本</a>
			</div>
			<div class="header_user">
				<div class="user_img" onmouseover="infoon()" onmouseout="infooff()">
					<span class="glyphicon glyphicon-user"></span> <span
						style="color:red;font-weight:400">${sessionScope.user.username }</span>
				</div>
				<c:if test="${!empty sessionScope.user }">
					<div class="user_upload">
						<a
							href="${pageContext.request.contextPath}/pages/manager/upload.jsp">
							<span class="glyphicon glyphicon-arrow-up">上传</span>
						</a>
					</div>
				</c:if>
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
				<div class="list_all">ID：${sessionScope.user.uid }</div>
				<div class="list_all">用户名:${sessionScope.user.username }</div>
				<div class="list_all">
					<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">个人中心</a>
				</div>
				<!-- 未实现暂时用User.jsp过渡 -->
				<div class="list_half">
					<a
						href="${ pageContext.request.contextPath}/pages/user/User.jsp">设置</a>
				</div>
				<div class="list_half">
					<a href="${pageContext.request.contextPath}/userAction_logOut">退出</a>
				</div>
			</c:otherwise>
			</c:choose>
		</div>
		<div class="manager_content">
			<c:if test="${sessionScope.managerType=='user' }">
				<table align="center">
					<tr>
						<th colspan=6>用户管理</th>
					</tr>
					<tr>
						<td>用户ID</td>
						<td>用户名</td>
						<td>用户状态</td>
						<td>用户权限</td>
						<td>修改</td>
						<td>删除</td>
					</tr>
					<c:if test="${!empty param.page }">
						<input id="page" type="text" value="${param.page }"
							style="display:none">
						<c:forEach items="${sessionScope.userlist }" var="user"
							begin="${(param.page-1)*10 }" end="${param.page*10-1 }"
							varStatus="num">
							<tr>
								<td><input id="uid${num.count }" name="uid" type="text"
									value="${user.uid }" style="display:none">${user.uid }</td>
								<td><input id="username${num.count }" name="username"
									type="text" value="${user.username }"></td>
								<td><c:choose>
										<c:when test="${user.u_status }">
										已激活
									</c:when>
										<c:otherwise>
										未激活
									</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${user.u_permission }">
											<select id="u_permission${num.count }" name="u_permission">
												<option value="1" selected>管理员</option>
												<option value="0">普通用户</option>
											</select>
										</c:when>
										<c:otherwise>
											<select id="u_permission${num.count }" name="u_permission">
												<option value="1">管理员</option>
												<option value="0" selected>普通用户</option>
											</select>
										</c:otherwise>
									</c:choose></td>
								<td><input type="button" value="修改"
									onclick="alter(${num.count })"></td>
								<td><a
									href="${ pageContext.request.contextPath}/managerAction_delete?uid=${user.uid}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty param.page }">
						<input id="page" type="text" value="1" style="display:none">
						<c:forEach items="${sessionScope.userlist }" var="user" begin="0"
							end="9" varStatus="num">
							<tr>
								<td><input id="uid${num.count }" name="uid" type="text"
									value="${user.uid }" style="display:none">${user.uid }</td>
								<td><input id="username${num.count }" name="username"
									type="text" value="${user.username }"></td>
								<td><c:choose>
										<c:when test="${user.u_status }">
										已激活
									</c:when>
										<c:otherwise>
										未激活
									</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${user.u_permission }">
											<select id="u_permission${num.count }" name="u_permission">
												<option value="1" selected>管理员</option>
												<option value="0">普通用户</option>
											</select>
										</c:when>
										<c:otherwise>
											<select id="u_permission${num.count }" name="u_permission">
												<option value="1">管理员</option>
												<option value="0" selected>普通用户</option>
											</select>
										</c:otherwise>
									</c:choose></td>
								<td><input type="button" value="修改"
									onclick="alter_U(${num.count })"></td>
								<td><a
									href="${ pageContext.request.contextPath}/managerAction_delete_U?uid=${user.uid}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td colspan=6 style="text-align:left;">页码: <c:forEach
								items="${sessionScope.userlist }" var="user" varStatus="num"
								begin="0" end="${sessionScope.count/10 }">
								<a id="a_${num.count }"
									href="${ pageContext.request.contextPath}/pages/manager/edit.jsp?page=${num.count}"
									style="margin-left:10px;"> ${num.count } </a>
							</c:forEach>
						</td>
					</tr>
				</table>
			</c:if>
			<!-- 书本 -->
			<c:if test="${sessionScope.managerType=='book' }">
				<table align="center">
					<tr>
						<th colspan=10>书本管理</th>
					</tr>
					<tr>
						<td>书本ID</td>
						<td>书名</td>
						<td>ISBN</td>
						<td>出版时间</td>
						<td>描述</td>
						<td>作者</td>
						<!-- 未实现 -->
						<td>类型</td>
						<td>图片</td>
						<td colspan=2>操作</td>
					</tr>
					<c:if test="${!empty param.page }">
						<input id="page" type="text" value="${param.page }"
							style="display:none">
						<c:forEach items="${sessionScope.booklist }" var="book"
							begin="${(param.page-1)*10 }" end="${param.page*10-1 }"
							varStatus="num">
							<tr>
								<td><input id="bid${num.count }" name="bid" type="text"
									value="${book.bid }" style="display:none">${book.bid }</td>
								<td><input id="bname${num.count }" name="bname" type="text"
									value="${book.bname }"></td>
								<td><input id="ISBN${num.count }" name="ISBN" type="text"
									value="${book.ISBN }"></td>
								<td><input type="text" id="publish${num.count }"
									name="publish" value="${book.publish }"></td>
								<td><input id="description${num.count }" name="description"
									type="text" value="${book.description }"></td>
								<td><input id="author${num.count }" name="author"
									type="text" value="${book.author }"></td>
								<td><input id="type${num.count }" name="type" type="text"
									value="${book.type }"></td>
								<td><img
									style="width:130px;height:163px;background-size: cover;-moz-background-size: cover;"
									id="image${num.count }" name="image"
									src="${ pageContext.request.contextPath}/images/bookImg/${book.image }"></td>
								<td><input type="button" value="修改"
									onclick="alter(${num.count })"></td>
								<td><a
									href="${ pageContext.request.contextPath}/managerAction_delete_B?uid=${book.bid}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty param.page }">
						<input id="page" type="text" value="1" style="display:none">
						<c:forEach items="${sessionScope.booklist }" var="book" begin="0"
							end="9" varStatus="num">
							<tr>
								<td><input id="bid${num.count }" name="bid" type="text"
									value="${book.bid }" style="display:none">${book.bid }</td>
								<td><input id="bname${num.count }" name="bname" type="text"
									value="${book.bname }"></td>
								<td><input id="ISBN${num.count }" name="ISBN" type="text"
									value="${book.ISBN }"></td>
								<td><input type="text" id="publish${num.count }"
									name="publish" value="${book.publish }"></td>
								<td><input id="description${num.count }" name="description"
									type="text" value="${book.description }"></td>
								<td><input id="author${num.count }" name="author"
									type="text" value="${book.author }"></td>
								<td><input id="type${num.count }" name="type" type="text"
									value="${book.type }"></td>
								<td><img
									style="width:130px;height:163px;background-size: cover;-moz-background-size: cover;"
									id="image${num.count }" name="image"
									src="${ pageContext.request.contextPath}/images/bookImg/${book.image }"></td>
								<td><input type="button" value="修改"
									onclick="alter(${num.count })"></td>
								<td><a
									href="${ pageContext.request.contextPath}/managerAction_delete_B?uid=${book.bid}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td colspan=10 style="text-align:left;">页码: <c:forEach
								items="${sessionScope.booklist }" var="book" varStatus="num"
								begin="0" end="${sessionScope.count/10 }">
								<a id="a_${num.count }"
									href="${ pageContext.request.contextPath}/pages/manager/edit.jsp?page=${num.count}"
									style="margin-left:10px;">${num.count }</a>
							</c:forEach>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</c:if>
</body>
</html>
