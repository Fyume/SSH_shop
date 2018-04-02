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
    
    <title>My JSP 'MyFavour.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<div class="MyFav">
		<!-- 分页用 -->
		<c:set var="AllNum" value="${sessionScope.myFav_size }"></c:set>
		<div class="User_title">收藏夹</div><br>
		<div class="arrow_div" style="top:179px;"></div>
		<div class="dropdown">
			<ul class="nav nav-tabs">
				<li class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenu1">
					<a>书本</a>
				</li>
				<ul id="typelist" class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
					<li class="dropdown-header">分类</li>
					<li onclick="getMyFav(0)"><a>全部书本</a></li>
					<li onclick="getMyFav(1)"><a>网络小说</a></li>
					<li onclick="getMyFav(2)"><a>文学作品</a></li>
					<li onclick="getMyFav(3)"><a>社会科学</a></li>
				</ul>
				<li onclick="getMyFav(4)"><a>用户作品</a></li>
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
	<div style="width:99%;height:30px;border-top:1px #c0c0c0 solid;text-align:center;">
		<div>页码:</div>
		<c:forEach items="${Alllist }" begin="0" end="${AllNum/8 }" varStatus="num">
			<a href="${pageContext.request.contextPath }/pages/user/User.jsp?list=${requestScope.list}&&page=${num.count}">${num.count }</a>
		</c:forEach>
	</div>
  </body>
</html>
