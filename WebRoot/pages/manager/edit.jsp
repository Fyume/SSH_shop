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

<title>管理页面</title>

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

<body onload="page('${requestScope.DBRs}')">
	<c:if test="${empty sessionScope.user }">
		<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
			style="margin-left:600px;">前往登录</a>
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
				<div class="user_img" onmouseover="infoon()" onmouseout="infooff()"
					onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
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
						<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">设置</a>
					</div>
					<div class="list_half">
						<a href="${pageContext.request.contextPath}/userAction_logOut">退出</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${empty sessionScope.managerType }">
			<div class="notice">
				<div class="not_div">
					<div class="not_div_font">
						<a href="${pageContext.request.contextPath}/managerAction_getUser">管理用户</a>
					</div>
					<div id="arrow">
						<img src="${pageContext.request.contextPath}/images/flag/arrow.png" width=100% height=100%>
					</div>
				</div>
				<div class="not_div" style="margin-left:0px;">
					<div class="not_div_font">
						<a href="${pageContext.request.contextPath}/managerAction_getBook">管理书本</a>
					</div>
					<div id="arrow">
						<img src="${pageContext.request.contextPath}/images/flag/arrow.png" width=100% height=100%>
					</div>
				</div>
			</div>
		</c:if>
		<div class="manager_content">
			<!-- 用户 -->
			<c:if test="${sessionScope.managerType=='user' }">
				<div id="userTable" class="userTable">
					<div id="T-header" class="T-header">用 户 管 理</div>
					<div class="T-font">
						<div class="select_div" onclick="u_select()">
							筛选<span id="slt_flag" class="glyphicon glyphicon-chevron-down" style="margin-left:2px;"></span>
						</div>
						<div class="font2-font">用户ID</div>
						<div id="u_select" class="font2_select1">
							<input id="slt_id" type="text" placeholder="筛选条件" onchange="return checkUid()">
							<input id="slt_name" type="text" placeholder="筛选条件" onchange="return checkUname()">
							<select id="slt_status" >
								<option value="0">未激活</option>
								<option value="1">已激活</option>
								<option value="2" checked>所有状态</option>
							</select>
							<select id="slt_permission" >
								<option value="0">普通用户</option>
								<option value="1">管理员</option>
								<option value="2" checked>所有权限</option>
							</select>
							<div class="f2s_button" onclick="select_u()">
								<span
									class="glyphicon glyphicon-search"
									style="margin-left:20px;font-size:20px;color:black;"> 筛选</span>
							</div>
						</div>
						<div class="font2-font">用户名</div>
						<div class="font2-font">用户状态</div>
						<div class="font2-font">用户权限</div>
						<div class="font2-font2">操作</div>
					</div>
					<div class="T-center">
						<c:choose>
							<c:when test="${!empty param.page }">
								<input id="page" type="text" value="${param.page }"
									style="display:none">
								<c:forEach items="${sessionScope.userlist }" var="user"
									begin="${(param.page-1)*10 }" end="${param.page*10-1 }"
									varStatus="num">
									<div id="T-content2${num.count }" class="T-content2">
										<div class="cont2-cont">
											<input id="uid${num.count }" name="uid" type="text"
												value="${user.uid }" style="display:none">${user.uid }
										</div>
										<div class="cont2-cont">
											<input id="username${num.count }" name="username" type="text"
												value="${user.username }">
										</div>
										<div class="cont2-cont">
											<input id="u_status${num.count }" name="u_status" type="text"
												value="${user.u_status }" style="display:none">
											<c:choose>
												<c:when test="${user.u_status }">
												已激活
												</c:when>
												<c:otherwise>
												未激活
												</c:otherwise>
											</c:choose>
										</div>
										<div class="cont2-cont">
											<c:choose>
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
											</c:choose>
										</div>
										<div class="cont2-cont2">
											<input type="button" value="修改"
												onclick="alter_U('${num.count }','${user.uid }')">
										</div>
										<div class="cont2-cont2">
											<a
												href="${ pageContext.request.contextPath}/managerAction_delete?uid=${user.uid}">删除</a>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<input id="page" type="text" value="1" style="display:none">
								<c:forEach items="${sessionScope.userlist }" var="user"
									begin="0" end="9" varStatus="num">
									<div id="T-content2${num.count }" class="T-content2">
										<div class="cont2-cont">
											<input id="uid${num.count }" name="uid" type="text"
												value="${user.uid }" style="display:none">${user.uid }
										</div>
										<div class="cont2-cont">
											<input id="username${num.count }" name="username" type="text"
												value="${user.username }">
										</div>
										<div class="cont2-cont">
											<input id="u_status${num.count }" name="u_status" type="text"
												value="${user.u_status }" style="display:none">
											<c:choose>
												<c:when test="${user.u_status }">
												已激活
												</c:when>
												<c:otherwise>
												未激活
												</c:otherwise>
											</c:choose>
										</div>
										<div class="cont2-cont">
											<c:choose>
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
											</c:choose>
										</div>
										<div class="cont2-cont2">
											<input type="button" value="修改"
												onclick="alter_U('${num.count }','${user.uid }')">
										</div>
										<div class="cont2-cont2">
											<a
												href="${ pageContext.request.contextPath}/managerAction_delete?uid=${user.uid}">删除</a>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="pageNum"
						style="width:100%;height:8%;border:1px red solid;padding-top:5px;">
						<span
							style="font-size:15px;font-weight: 700;float:left;margin-left:5px;">页码</span>
						<c:forEach items="${sessionScope.userlist }" var="user"
							varStatus="num" begin="0" end="${(sessionScope.count-1)/10 }">
							<a id="a_${num.count }"
								href="${ pageContext.request.contextPath}/pages/manager/edit.jsp?page=${num.count}"
								style="margin-left:10px;"> ${num.count } </a>
						</c:forEach>
					</div>
				</div>
			</c:if>

			<!-- 书本 -->
			<c:if test="${sessionScope.managerType=='book' }">
				<div id="bookTable" class="bookTable"
					onmouseover="showPublish('${param.page }')">
					<div id="T-header" class="T-header">图 书 管 理</div>
					<div id="T-font" class="T-font">
						<div class="select_div" onclick="b_select()">
							筛选<span id="slt_flag2" class="glyphicon glyphicon-chevron-down" style="margin-left:2px;"></span>
						</div>
						<div class="font-font">书本ID</div>
						<div class="font-font">书名</div>
						<div class="font-font">ISBN</div>
						<div class="font-font2">出版时间</div>
						<div class="font-font">描述</div>
						<div class="font-font">作者</div>
						<div class="font-font">类型</div>
						<div class="font-font3">图片</div>
						<div class="font-font2">操作</div>
						<div id="b_select" class="font2_select2">
							<input id="slt_bid" type="text" placeholder="书本ID" onchange="return checkBid()">
							<input id="slt_bname" type="text" placeholder="书名" onchange="return checkBname()">
							<input id="slt_ISBN" type="text" placeholder="ISBN" onchange="return checkISBN()">
							<input id="slt_author" type="text" placeholder="作者" style="margin-left:310px;">
							<select id="slt_type">
								<option value="网络小说">网络小说</option>
								<option value="文学作品">文学作品</option>
								<option value="社会科学">社会科学</option>
								<option value="" checked>所有类型</option>
							</select>
							日期: 
							<input id="slt_year1" type="text" placeholder="1999" onchange="return checkYear(1)">年
							<input id="slt_month1" type="text" placeholder="1" style="width:6.5%" onchange="return checkMonth(1)">月
							<input id="slt_date1" type="text" placeholder="1" style="width:6.5%" onchange="return checkDate(1)">日
							至
							<input id="slt_year2" type="text" placeholder="1999" onchange="return checkYear(2)">年
							<input id="slt_month2" type="text" placeholder="1" style="width:6.5%" onchange="return checkMonth(2)">月
							<input id="slt_date2" type="text" placeholder="1" style="width:6.5%" onchange="return checkDate(2)">日
							<div class="f2s_button" onclick="select_b()">
								<span
									class="glyphicon glyphicon-search"
									style="margin-left:20px;font-size:20px;color:black;"> 筛选</span>
							</div>
						</div>
					</div>
					<div class="T-center">
						<c:choose>
							<c:when test="${!empty param.page }">
								<input id="page" type="text" value="${param.page }"
									style="display:none">
								<c:forEach items="${sessionScope.booklist }" var="book"
									begin="${(param.page-1)*5 }" end="${param.page*5-1 }"
									varStatus="num">
									<div id="T-content${num.count }" class="T-content">
										<div class="cont-cont">
											<input id="bid${num.count }" name="bid" type="text"
												value="${book.bid }" style="display:none">${book.bid }
										</div>
										<div class="cont-cont">
											<input id="bname${num.count }" name="bname" type="text"
												value="${book.bname }">
										</div>
										<div class="cont-cont">
											<input id="ISBN${num.count }" name="ISBN" type="text"
												value="${book.ISBN }">
										</div>
										<div class="cont-cont3">
											<input id="publish${num.count }" type="text"
												value="${book.publish }" style="display:none;"> <input
												id="year${num.count }" type="text"
												onchange="return checkPublish('${num.count }');">年 <input
												id="month${num.count }" type="text"
												onchange="return checkPublish('${num.count }');">月 <input
												id="date${num.count }" type="text"
												onchange="return checkPublish('${num.count }');">日
										</div>
										<div class="cont-cont" style="padding-top:0px;">
											<textarea
												style="width:90%;height:100%;border:1px #3366FF solid;"
												id="description${num.count }" name="description">${book.description }</textarea>
										</div>
										<div class="cont-cont">
											<input id="author${num.count }" name="author" type="text"
												value="${book.author }">
										</div>
										<div class="cont-cont">
											<select size='1' id="type${num.count }">
												<option value="${book.type}" checked>${book.type }</option>
												<c:forEach items="${sessionScope.typelist }" var="type">
													<c:if test="${book.type ne type.type }">
														<option value="${type.type }" checked>${type.type }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										<div class="cont-cont2">
											<img id="image${num.count }" class="cont-img" title="${book.bname }" alt="书本封面小图"
												src="${ pageContext.request.contextPath}/images/bookImg${book.image }"
												onclick="alter_Img('image${num.count }','${book.bid }')">
										</div>
										<div class="cont-cont">
											<input type="button" value="修改"
												onclick="alter_B('${num.count }','${book.bid }')">
										</div>
										<div class="cont-cont">
											<a
												href="${ pageContext.request.contextPath}/managerAction_delete_B?uid=${book.bid}">删除</a>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<input id="page" type="text" value="1" style="display:none">
								<c:forEach items="${sessionScope.booklist }" var="book"
									begin="0" end="4" varStatus="num">
									<div id="T-content${num.count }" class="T-content">
										<div class="cont-cont">
											<input id="bid${num.count }" name="bid" type="text"
												value="${book.bid }" style="display:none">${book.bid }
										</div>
										<div class="cont-cont">
											<input id="bname${num.count }" name="bname" type="text"
												value="${book.bname }">
										</div>
										<div class="cont-cont">
											<input id="ISBN${num.count }" name="ISBN" type="text"
												value="${book.ISBN }">
										</div>
										<div class="cont-cont3">
											<input id="publish${num.count }" type="text"
												value="${book.publish }" style="display:none;"> <input
												id="year${num.count }" type="text"
												onchange="return checkPublish('${num.count }');">年 <input
												id="month${num.count }" type="text"
												onchange="return checkPublish('${num.count }');">月 <input
												id="date${num.count }" type="text"
												onchange="return checkPublish('${num.count }');">日
										</div>
										<div class="cont-cont" style="padding-top:0px;">
											<textarea
												style="width:90%;height:100%;border:1px #3366FF solid;"
												id="description${num.count }" name="description">${book.description }</textarea>
										</div>
										<div class="cont-cont">
											<input id="author${num.count }" name="author" type="text"
												value="${book.author }">
										</div>
										<div class="cont-cont">
											<select size='1' id="type${num.count }">
												<option value="${book.type}" checked>${book.type }</option>
												<c:forEach items="${sessionScope.typelist }" var="type">
													<c:if test="${book.type ne type.type }">
														<option value="${type.type }" checked>${type.type }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										<div class="cont-cont2">
											<img id="image${num.count }" class="cont-img" title="${book.bname }" alt="书本封面小图"
												src="${ pageContext.request.contextPath}/images/bookImg${book.image }"
												onclick="alter_Img('image${num.count }','${book.bid }')">
										</div>
										<div class="cont-cont">
											<input type="button" value="修改"
												onclick="alter_B('${num.count }','${book.bid }')">
										</div>
										<div class="cont-cont">
											<a
												href="${ pageContext.request.contextPath}/managerAction_delete_B?uid=${book.bid}">删除</a>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="pageNum"
						style="width:100%;height:8%;border:1px red solid;padding-top:5px;">
						<span
							style="font-size:15px;font-weight: 700;float:left;margin-left:5px;">页码</span>
						<c:forEach items="${sessionScope.booklist }" var="book"
							varStatus="num" begin="0" end="${(sessionScope.count-1)/5 }">
							<a id="a_${num.count }"
								href="${ pageContext.request.contextPath}/pages/manager/edit.jsp?page=${num.count}"
								style="margin-left:10px;">${num.count }</a>
						</c:forEach>
					</div>
				</div>
			</c:if>
		</div>
		<div id="B-I" class="Bimg" style="display:none;">
			<img id="B-img"
				src="${ pageContext.request.contextPath}/images/bookImg/5031868592.jpg"
				alt="书本封面大图"
				onclick="disapper('${ pageContext.request.contextPath}')">
			<span style="width:10%;height:10%;margin-top:-45%;color:white;font-size:15px;font-weight:700;">点击图片回到回到原来页面</span>
			<div style="position:absolute;width:200px;height:30px;left:43%;top:0;">
				<form id="ImageForm" style="display:none;">
					<input id="image" name="image" type="file" accept="image/*" onchange="alt_btn()">
				</form>
				<input type="button" style="width:48%;height:100%;" value="修改封面"
					onclick="checkForm()">
				<input id="alt_btn" type="button" style="width:48%;height:100%;display:none" value="确认修改"
					onclick="update_Img()">
			</div>
		</div>
	</c:if>
</body>
</html>
