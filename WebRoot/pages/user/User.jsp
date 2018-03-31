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
				<div class="h_r_user btn btn-default" onmouseover="infoon()" onmouseout="infooff()" onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
					<span class="glyphicon glyphicon-user"></span> <span
						style="color:red;font-weight:400">${sessionScope.user.username }</span>
				</div>
				<div class="h_r_user btn btn-default">消息</div>
				<div id="updateFlag"></div>
				<a href="${pageContext.request.contextPath}/userAction_getMyFavBy?type=0">
					<div class="h_r_user btn btn-default">收藏夹</div>
				</a>
				<a href="${pageContext.request.contextPath}/pages/user/upload.jsp">
					<div class="user_upload" style="margin-top:-5px;">
						<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
					</div>
				</a>
			</div>
			<!-- 不放到这个位置好像会影响div的onmouserover事件 应该是video标签的问题？ 还是加载顺序的问题？不是很清楚 -->
			<div class="second">
				<a href="${pageContext.request.contextPath}/bookAction_getData">
					<div class="sec_logo"></div>
					<div class="sec_font">在线阅读网站</div>
				</a>
				<div class="sec_video">
					<video width="100%" height="100%" loop="loop" autoplay="autoplay" style="object-fit:fill;">
						<source src="${pageContext.request.contextPath}/images/video/sec(2).mp4"  type="video/mp4">
					</video>
				</div>
			</div>
			<div id="user_info" class="user_info" onmouseover="infoon()" onmouseout="infooff()">
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
						<div class="list_btn" onclick="logout()">
							退出
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="User_bottom">
				<div class="bottom_list">
					<ul class="nav nav-tabs-stacked">
						<li class="active">
							<a href="${pageContext.request.contextPath}/pages/user/User.jsp">
								基本资料
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/workAction_getMyWork">
								我的作品
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/userAction_getMyFavBy?type=0">
								收藏夹
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/userAction_getMyUpdateFlag">
								评论消息
							</a>
						</li>
					</ul>
				</div>
				<div class="User_table">
					<c:choose>
						<c:when test="${(requestScope.list==1&&empty param.list)||(empty param.list&&empty requestScope.list)}">
							<div class="User_title">基本信息</div>
							<div class="arrow_div" style="border-top-left-radius:25px;"></div>
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
										<input id="image_btn" type="button" value="修改头像" onclick="uploadi()" disabled="disabled"> <br>
										<div style="font-size:12px;color:red;">(仅限jpg,jpeg)</div>
										<input type="file" id="image" name="image" accept="image/*">
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
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty param.page }">
									<c:set var="begin" value="${(param.page-1)*8}"></c:set>
									<c:set var="end" value="${(param.page*8)-1}"></c:set>	
								</c:when>
								<c:otherwise>
									<c:set var="begin" value="0"></c:set>
									<c:set var="end" value="7"></c:set>	
								</c:otherwise>
							</c:choose>
							<c:set var="AllNum" value="0"></c:set>
							<!-- 作品 -->
							<c:if test="${(requestScope.list==2&&empty param.list)||param.list==2}">
								<div class="MyFav" style="height:85%;">
									<!-- 分页用 -->
									<c:set var="Alllist" value="${sessionScope.myWork }"></c:set>
									<c:set var="AllNum" value="${sessionScope.myWork_flag }"></c:set>
									<div class="User_title">作品区</div><br>
									<div class="arrow_div" style="top:128px;"></div>
									<c:if test="${empty sessionScope.myWork }">
										<span style="color:#c0c0c0;">您还没有上传过作品呢。点击右上角上传按钮即刻上传吧~</span>
									</c:if>
									<c:forEach items="${sessionScope.myWork }" var="work" begin="${begin }" end="${end }" varStatus="num">
										<div class="work_border">
											<div class="work_img" onmousemove="coveron(${num.count})" onmouseout="coveroff(${num.count})">
												<img id="work_img${num.count }" alt="${work.wid }" title="${work.description }" src="${pageContext.request.contextPath }/images/user/workImg${work.image}">
											</div>
											<div id="img_cover${num.count }" class="work_img_cover" onmousemove="coveron(${num.count})" onmouseout="coveroff(${num.count})">
												<div class="cover_btn"><a href="${pageContext.request.contextPath }/workAction_readWork?wid=${work.wid}">进入阅读</a></div>
												<div class="cover_btn" onclick="edit_divOn(${num.count})">编辑</div>
											</div>
											<div id="work_wname${num.count }" class="work_title">${work.wname }</div>
											<div class="work_time"><mytags:date value="${work.uploadtime*1000 }"></mytags:date></div>
										</div>
									</c:forEach>
								</div>
							</c:if>
							<!-- 收藏夹 -->
							<c:if test="${(requestScope.list==3&&empty param.list)||param.list==3}">
								<div class="MyFav">
									<!-- 分页用 -->
									<c:set var="AllNum" value="${sessionScope.myFav_size }"></c:set>
									<div class="User_title">收藏夹</div><br>
									<div class="arrow_div" style="top:169px;"></div>
									<div class="dropdown">
										<ul class="nav nav-tabs">
											<li class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenu1">
												<a href="${pageContext.request.contextPath }/userAction_getMyFavBy?type=0">书本</a>
											</li>
											<ul id="typelist" class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
												<li class="dropdown-header">分类</li>
												<li><a href="${pageContext.request.contextPath }/userAction_getMyFavBy?type=1">网络小说</a></li>
												<li><a href="${pageContext.request.contextPath }/userAction_getMyFavBy?type=2">文学作品</a></li>
												<li><a href="${pageContext.request.contextPath }/userAction_getMyFavBy?type=3">社会科学</a></li>
											</ul>
											<li><a href="${pageContext.request.contextPath }/userAction_getMyFavBy?type=4">用户作品</a></li>
										</ul>
									</div>
									<div class="MyFav_list">
										<c:if test="${empty sessionScope.myFav_Work &&empty sessionScope.myFav_Book}">
												空空如也~~
										</c:if>
										<c:if test="${!empty sessionScope.myFav_Work }">
											<c:set var="Alllist" value="${sessionScope.myFav_Work }"></c:set>
											<c:forEach items="${sessionScope.myFav_Work }" var="fav" begin="${begin }" end="${end }" varStatus="num">
												<div class="work_border">
													<div class="work_img" onmousemove="coveron(${num.count})" onmouseout="coveroff(${num.count})">
														<img id="work_img${num.count }" alt="${fav.work.wid }" title="${fav.work.description }" src="${pageContext.request.contextPath }/images/user/workImg${fav.work.image}">
													</div>
													<div id="img_cover${num.count }" class="work_img_cover" onmousemove="coveron(${num.count})" onmouseout="coveroff(${num.count})">
														<div class="cover_btn"><a href="${pageContext.request.contextPath }/workAction_readWork?wid=${fav.work.wid}">进入阅读</a></div>
													</div>
													<c:if test="${fav.updateFlag==1}">
														<div style="width:25px;height:25px;margin-left:-15px;margin-top:-10px;">
															<img width=100% height=100% alt="" src="${pageContext.request.contextPath }/images/flag/53ca319f790e8.png">
														</div>
													</c:if>
													<div id="work_wname${num.count }" class="work_title">${fav.work.wname }</div>
													<div class="work_time"><mytags:date value="${fav.work.uploadtime*1000 }"></mytags:date></div>
												</div>
											</c:forEach>
										</c:if>
										<c:if test="${!empty sessionScope.myFav_Book }">
											<!-- 收藏--全部书本 -->
											<!-- 分页用 -->
											<c:set var="Alllist" value="${sessionScope.myFav_Book }"></c:set>
											<c:forEach items="${sessionScope.myFav_Book }" var="fav" begin="${begin }" end="${end }" varStatus="num">
												<div class="work_border">
													<div class="work_img" onmousemove="coveron(${num.count})" onmouseout="coveroff(${num.count})">
														<img id="work_img${num.count }" alt="${fav.book.bid }" title="${fav.book.description }" src="${pageContext.request.contextPath }/images/bookImg${fav.book.image}">
													</div>
													<div id="img_cover${num.count }" class="work_img_cover" onmousemove="coveron(${num.count})" onmouseout="coveroff(${num.count})">
														<div class="cover_btn"><a href="${pageContext.request.contextPath }/bookAction_readBook?bid=${fav.book.bid}">进入阅读</a></div>
													</div>
													<c:if test="${fav.updateFlag==1}">
														<div style="width:25px;height:25px;margin-left:-15px;margin-top:-10px;">
															<img width=100% height=100% alt="" src="${pageContext.request.contextPath }/images/flag/53ca319f790e8.png">
														</div>
													</c:if>
													<div id="work_wname${num.count }" class="work_title">${fav.book.bname }</div>
													<div class="work_time">出版日期： <mytags:date type="1" value="${fav.book.publish*1000*60*60 }"></mytags:date></div>
												</div>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</c:if>
							<div style="width:99%;height:30px;border-top:1px #c0c0c0 solid;text-align:center;">
								<div>页码:</div>
								<c:forEach items="${Alllist }" begin="0" end="${AllNum/8 }" varStatus="num">
									<a href="${pageContext.request.contextPath }/pages/user/User.jsp?list=${requestScope.list}&&page=${num.count}">${num.count }</a>
								</c:forEach>
							</div>
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
								<span class="Form_font">作品ID：</span>
								<input id="Work_wid" type="text" name="work.wid" placeholder="作品ID">
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
				<div class="user_Update_Flag"></div>
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
