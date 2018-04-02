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
<body onload="start(${empty sessionScope.user})">
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
					<select id="select_select" name="flag" style="display:none;"> 
						<option value="bname" checked>书名</option>
						<option value="wname">作品名</option>
						<option value="author">作者</option>
					</select>
					<ul class="nav nav-tabs" style="width:280px;border-bottom:1px #c0c0c0 solid;background-color:#5bc0de;padding-top:-5px;height:45px;border-radius:30px;">
						<li><input type="text" id="select_message" placeholder="输入作品名/书名(1-20个字符、数字)" style="height:40px;width:170px;margin-top:-8px;margin-left:20px;"></li>
						<ul class="nav nav-stacked" style="width:60px;height:39px;margin-top:-7px;margin-left:-10px;" onmouseover="showFlag()" onmouseout="hideFlag()">
							<li id="ls_1" onclick="exchangeFlag(1)">书名<span class="glyphicon glyphicon-play" style="font-size:5px;transform:rotate(90deg);"></span>
							</li>
							<li id="ls_2" onclick="exchangeFlag(2)">作品名</li>
							<li id="ls_3" onclick="exchangeFlag(3)">作者</li>
						</ul>
						<li>
							<div class="fs_input2" onclick="selectmess()">
								<span class="glyphicon glyphicon-search" style="font-size:25px;margin-top:10px;margin-left:0;"></span>
							</div>
						</li>
					</ul>
				</div>
				<c:if test="${sessionScope.user.u_permission }">
					<a href="${pageContext.request.contextPath}/pages/manager/edit.jsp">
						<div class="managerPage">
							前往管理员界面
						</div>
					</a>
				</c:if>
				<div id="header_right" class="header_right">
				</div>
			</div>
			<div id="index_center" class="index_center" style="background-color:#efefef;">
				
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
