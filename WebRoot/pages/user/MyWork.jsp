<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="mytags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyWork.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
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
	<div style="width:99%;height:30px;border-top:1px #c0c0c0 solid;text-align:center;">
		<div>页码:</div>
		<c:forEach items="${Alllist }" begin="0" end="${AllNum/8 }" varStatus="num">
			<a href="${pageContext.request.contextPath }/pages/user/User.jsp?list=${requestScope.list}&&page=${num.count}">${num.count }</a>
		</c:forEach>
	</div>
  </body>
</html>
