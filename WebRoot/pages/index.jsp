<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="myTags"%>
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

<title>Index.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body onload="start(${empty sessionScope.user })">
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
	<div id="index_center" class="index_center">
		
	</div>
	
	<div class="bottom">
		<div id="totalBook" class="totalBook">
		</div>
		<span id="page_num" style="display:none;">${param.page }</span>
	</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<!-- 用下载下来的bootstrap.min.css没有图标 不知道为什么 可能是需要其他的文件支持 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
</body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">
</html>