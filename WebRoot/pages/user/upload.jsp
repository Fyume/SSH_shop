<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>用户上传作品</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/upload.css">
</head>
<body onload="start(${empty sessionScope.typelist },${empty sessionScope.user})">
	<c:choose>
		<c:when test="${empty sessionScope.user }">
			<a href="${pageContext.request.contextPath}/pages/user/login.jsp"
				style="margin-left:600px;">前往登录</a>
		</c:when>
		<c:otherwise>
			<div class="header">
				<a href="${pageContext.request.contextPath}/bookAction_getData">
					<div class="header_logo">
						<img width=100% height=100% alt="" src="${pageContext.request.contextPath}/images/flag/2018-03-31_164359.png">
					</div>
				</a>
				<div class="header_center">
					<fieldset>
						<select id="select_select">
							<option value="1">书名</option>
							<option value="2">作品名</option>
							<option value="3">作者</option>
						</select>
						<input class="fs_input1" id="select_message" type="text" name="select_message" placeholder="输入作品名/书名(1-20个字符、数字)"onblur="check_selecttext()">
						<input class="fs_input2 btn btn-info" type="button" value="搜索" onclick="selectmess()">
					</fieldset>
				</div>
				<c:if test="${sessionScope.user.u_permission }">
					<a href="${pageContext.request.contextPath}/pages/manager/edit.jsp">
						<div class="managerPage">
							前往管理员界面
						</div>
					</a>
				</c:if>
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
			</div>
			<div class="index_center" style="background-color:#b7b7b7;">
				<div id="index_title" class="index_title">
					<div class="class_title" style="border-left:0;">
						<a href="${pageContext.request.contextPath}/bookAction_getData">
							首页
						</a>
					</div>
					<c:forEach items="${sessionScope.typelist }" var="type"
						varStatus="num">
						<div>
							<div class="class_title" onmouseover="classUlon(${num.count})"
								onmouseout="classUloff(${num.count})">
								<a
									href="${pageContext.request.contextPath}/bookAction_selectB?flag=type&message=${type.type}">${type.type }</a>
							</div>
			
						</div>
						<%-- <div id="class_ul${num.count}" class="class_ul${num.count}"
							onmouseover="classUlon(${num.count})"
							onmouseout="classUloff(${num.count})">
					<!-- 数据库里面用";"分开 -->
							<c:set value="${fn:split(type.type_flag,';') }" var="type_flag"></c:set>
							<c:forEach items="${type_flag }" var="flag" begin="0" end="5">
								<ul>
									<li><a
										href="${pageContext.request.contextPath}/bookAction_selectB?flag=type_flag&message=${type.type}">${flag }</a></li>
								</ul>
							</c:forEach>
							<c:forEach items="${type_flag }" var="flag" begin="6" end="12">
								<ul>
									<li><a
										href="${pageContext.request.contextPath}/bookAction_selectB?flag=type_flag&message=${type.type}">${flag }</a></li>
								</ul>
							</c:forEach>
						</div> --%>
					</c:forEach>
					<div class="class_title">
						<a href="${pageContext.request.contextPath}/workAction_getData">
							用户作品
						</a>
					</div>
					<div class="class_title">
						<a href="${pageContext.request.contextPath}/userAction_random">
							随便看看
						</a>
					</div>
				</div>
			</div>
			<div class="upload_bottom">
				<input id="uploadResult" type="text"
					value="${requestScope.uploadResult}" style="display:none">
				<div class="upload_form">
					<form
						action="${pageContext.request.contextPath }/workAction_upload"
						method="post" enctype="multipart/form-data"
						onsubmit="return check()">
						<div class="upload_title">
							<div class="title_font">标题/作品名 ：</div>
							<div class="title_input">
								<input id="upload_title" name="work.wname" type="text"
									maxlength="15" placeholder="输入标题/作品名(1-20个字符、数字)"
									onblur="checktitle()">
							</div>
							<div id="title_F" class="upload_flag_default"></div>
							<div id="title_warning" class="title_warning"></div>
						</div>
						<img id="upload_img" alt="预览图"
							src="${pageContext.request.contextPath }/images/background/bookimg-default.jpg">
						<div class="img_button">
							<input type="button" value="上传封面" onclick="uploadi()"> <br>
							<div style="font-size:12px;color:red;">(仅限jpg,jpeg)</div>
						</div>
						<input type="file" id="image" name="image" accept="image/*"
							style="display:none;">
						<div class="file_button">
							<input type="button" value="上传作品"
								style="width:200px;height:30px;cursor: pointer;"
								onclick="uploadf()"> <br>
							<div
								style="font-size:12px;color:red;margin-left:30px;cursor: default;">(仅限doc,docx,txt)</div>
							<div id="file_F"
								style="margin-top:-40px;margin-left:203px;cursor: default;"
								class="upload_flag_default"></div>
						</div>
						<input type="file"
							accept="application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain"
							id="upload" name="upload" style="display:none;"
							onchange="checkF()"> <br>
						<div class="upload_desc">
							<div class="desc_font">书本概述:</div>
							<div style="margin-top:5px;">
								<textarea name="work.description" class="desc_content"
									placeholder="输入概述"></textarea>
							</div>
						</div>
						<div class="upload_button">
							<input type="submit" value="提交">
						</div>
						<div
							style="width:120px;height:30px;margin-left:-100px;margin-top:230px;">
							<a
								href="${pageContext.request.contextPath }/pages/user/editWork.jsp"
								style="color:blue;">修改上传作品信息</a>
						</div>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/upload.js"></script>
</body>
</html>
