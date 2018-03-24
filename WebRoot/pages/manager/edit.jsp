<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!-- 本来打算用来转时间的 谁知道只有String转Date -->
<%@ taglib uri="/mytags" prefix="data" %><!-- 根据网上的 建了自定义标签处理long类型的时间戳 好强啊 --><!-- 越写越多方法 -->
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
			<a href="${pageContext.request.contextPath}/pages/index.jsp">
				<div class="header_index">
					首页
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/managerAction_getUser">
				<div class="h_user">
					管理用户
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/managerAction_getBook">
				<div class="h_book">
					管理书本
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/managerAction_getRecord">
				<div class="h_record">
					操作历史
				</div>
			</a>
			<div class="header_user">
				<div class="user_img" onmouseover="infoon()" onmouseout="infooff()"
					onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
					<span class="glyphicon glyphicon-user"></span> <span
						style="color:red;font-weight:400">${sessionScope.user.username }</span>
				</div>
				<a href="${pageContext.request.contextPath}/pages/manager/upload.jsp">
					<div class="user_upload">
						<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
					</div>
				</a>
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
					<div class="list_half">ID：<span style="color:#0080c0;">${sessionScope.user.uid }</span></div>
					<div class="list_half">用户名:<span style="color:red;">${sessionScope.user.username }</span></div>
					<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
						<div class="list_all">
							<span style="font-weight:550;font-size:15px;">个人中心</span>
						</div>
					</a>
					<!-- 未实现暂时用User.jsp过渡 -->
					<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
						<div class="list_btn">
							设置
						</div>
					</a>
					<a href="${pageContext.request.contextPath}/userAction_logOut">
						<div class="list_btn">
							退出
						</div>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${empty sessionScope.managerType }">
			<div class="notice">
				<div class="not_div">
					<a href="${pageContext.request.contextPath}/managerAction_getUser">
						<div class="not_div_font">
							管理用户
						</div>
					</a>
					<div id="arrow">
						<img src="${pageContext.request.contextPath}/images/flag/arrow.png" width=100% height=100%>
					</div>
				</div>
				<div class="not_div" style="margin-left:1px;">
					<a href="${pageContext.request.contextPath}/managerAction_getBook">
						<div class="not_div_font">
							管理书本
						</div>
					</a>
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
							<input id="slt_id" type="text" placeholder="用户ID" onchange="return checkUid()">
							<input id="slt_name" type="text" placeholder="用户名" onchange="return checkUname()">
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
						<span id="delete_U" style="display:none;">${requestScope.delete_U }</span>
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
												href="${ pageContext.request.contextPath}/managerAction_delete_U?uid=${user.uid}" onclick="return del_U(${user.uid})">删除</a>
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
												href="${pageContext.request.contextPath}/managerAction_delete_U?uid=${user.uid}" onclick="return del_U(${user.uid})">删除</a>
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
											<data:date2 num="${num.count }" value="${book.publish*1000*60*60}"></data:date2>
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
										<div class="cont2-button">
											<input type="button" value="修改"
												onclick="alter_B('${num.count }',${book.bid })">
										</div>
										<div class="cont2-button">
											<a
												href="${ pageContext.request.contextPath}/managerAction_delete_B?bid=${book.bid}" onclick="return del_B(${book.bid})">删除</a>
										</div>
										<div class="cont2-button">
											<input type="button" style="width:78px;" value="更 新"
												onclick="uploadDiv(${num.count })">
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
											<data:date2 num="${num.count }" value="${book.publish*1000*60*60}"></data:date2>
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
										<div class="cont2-button">
											<input type="button" value="修改"
												onclick="alter_B('${num.count }',${book.bid })">
										</div>
										<div class="cont2-button">
											<a
												href="${ pageContext.request.contextPath}/managerAction_delete_B?bid=${book.bid}" onclick="return del_B(${book.bid})">删除</a>
										</div>
										<div class="cont2-button">
											<input type="button" style="width:78px;" value="更 新"
												onclick="uploadDiv(${num.count })">
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
			
			<!-- 历史记录 -->
			<c:if test="${sessionScope.managerType=='record' }">
				<div id="recordTable" class="recordTable">
					<div id="T-header" class="T-header">总操作记录</div>
					<div class="T-font">
						<!-- <div class="select_div" onclick="u_select()">
							筛选<span id="slt_flag" class="glyphicon glyphicon-chevron-down" style="margin-left:2px;"></span>
						</div> -->
						
					</div>
					<div class="T-center">
						<c:choose>
							<c:when test="${!empty param.page }">
								<input id="page" type="text" value="${param.page }"
									style="display:none">
								<c:forEach items="${sessionScope.record }" var="operate"
									begin="${(param.page-1)*10 }" end="${param.page*10-1 }"
									varStatus="num">
									<div id="T-content3${num.count }" class="T-content3">
										<span id="oid${num.count }" style="font-weight:700;">${operate.oid }</span> | 
										<span id="time${num.count }" style="color:red;"><data:date value="${operate.time*1000 }"></data:date></span>
										用户<span id="managerID${num.count }" class="record_id">${operate.user.uid }</span>
										对
										<span id="entity1${num.count }" class="record_entity">${operate.entity }</span><span id="chevron1${num.count }" class="glyphicon glyphicon-chevron-down" style="cursor:pointer;" onclick="entity(1,${num.count})"></span>
										<span id="value_after${num.count }" style="display:none;">${operate.value_after}</span>
										实体进行了
										<c:if test="${operate.type_flag eq 1}">
											<span id="operate${num.count }">插入操作</span>
										</c:if>
										<c:if test="${operate.type_flag eq 2}">
											<span id="operate${num.count }">删除操作</span>
											删除前-->
											<span id="entity2${num.count }" class="record_entity">${operate.entity }</span><span id="chevron2${num.count }" class="glyphicon glyphicon-chevron-down" style="cursor:pointer;" onclick="entity(2,${num.count})"></span>
											<span id="value_before${num.count }" style="display:none;">${operate.value_before}</span>
										</c:if>
										<c:if test="${operate.type_flag eq 3}">
											<span id="operate${num.count }">修改操作 </span>
											修改前-->
											<span id="entity2${num.count }" class="record_entity">${operate.entity }</span><span id="chevron2${num.count }" class="glyphicon glyphicon-chevron-down" style="cursor:pointer;" onclick="entity(2,${num.count})"></span>
											<span id="value_before${num.count }" style="display:none;">${operate.value_before}</span>
										</c:if>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<input id="page" type="text" value="1" style="display:none">
								<c:forEach items="${sessionScope.record }" var="operate"
									begin="0" end="9" varStatus="num">
									<div id="T-content3${num.count }" class="T-content3">
										<span id="oid${num.count }" style="font-weight:700;">${operate.oid }</span> | 
										<span id="time${num.count }" style="color:red;"><data:date value="${operate.time*1000 }"></data:date></span>
										用户<span id="managerID${num.count }" class="record_id">${operate.user.uid }</span>
										对
										<span id="entity1${num.count }" class="record_entity">${operate.entity }</span><span id="chevron1${num.count }" class="glyphicon glyphicon-chevron-down" style="cursor:pointer;" onclick="entity(1,${num.count})"></span>
										<span id="value_after${num.count }" style="display:none;">${operate.value_after}</span>
										实体进行了
										<c:if test="${operate.type_flag eq 1}">
											<span id="operate${num.count }">插入操作</span>
										</c:if>
										<c:if test="${operate.type_flag eq 2}">
											<span id="operate${num.count }">删除操作</span>
											删除前-->
											<span id="entity2${num.count }" class="record_entity">${operate.entity }</span><span id="chevron2${num.count }" class="glyphicon glyphicon-chevron-down" style="cursor:pointer;" onclick="entity(2,${num.count})"></span>
											<span id="value_before${num.count }" style="display:none;">${operate.value_before}</span>
										</c:if>
										<c:if test="${operate.type_flag eq 3}">
											<span id="operate${num.count }">修改操作 </span>
											修改前-->
											<span id="entity2${num.count }" class="record_entity">${operate.entity }</span><span id="chevron2${num.count }" class="glyphicon glyphicon-chevron-down" style="cursor:pointer;" onclick="entity(2,${num.count})"></span>
											<span id="value_before${num.count }" style="display:none;">${operate.value_before}</span>
										</c:if>
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
		</div>
		<div id="B-I" class="Bimg" style="display:none;">
			<img id="B-img" src="" alt="书本封面大图"
				onclick="disapper('${ pageContext.request.contextPath}')">
			<span class="Img_tips">点击图片关闭大图</span>
			<div class="ImgForm_div">
				<form id="ImageForm" style="display:none;">
					<input id="image" name="image" type="file" accept="image/*" onchange="alt_btn()">
				</form>
				<input type="button" value="修改封面"
					onclick="checkForm()">
				<input id="alt_btn" type="button" style="display:none" value="确认修改"
					onclick="update_Img()">
			</div>
		</div>
		<!-- Δ=80px -->
		<div id="book_update_div" class="book_update_div">
			<form action="${ pageContext.request.contextPath}/bookAction_update">
				书本名字:
				<br>
				《<span id="bookName" style="color:red"></span>》
				<input type="text" id="b_u_bid" name="book.bid" style="display:none;">
				<input type="button" style="width:85px;font-size:12px;" value="上传更新内容" onclick="uploadFile()">
				<input type="file" id="upload" name="upload" style="display:none;" accept="text/plain,application/msword" onchange="checkfile()">
				<input id="b_u_sub" type="submit" value="确认上传" disabled="disabled" style="opacity:0.5;">
				<br>
				${requestScope.uploadResult }
			</form>
		</div>
	</c:if>
</body>
</html>
