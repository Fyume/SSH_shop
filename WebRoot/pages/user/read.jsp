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

<title>
	<c:choose>
		<c:when test="${empty sessionScope.book}">《${sessionScope.work.wname }》</c:when>
		<c:otherwise>《${sessionScope.book.bname }》</c:otherwise>
	</c:choose>
</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/read.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/read.css">
</head>

<body>
	<div id="content_div" class="content_div">
		<input id="content" type="text" value="${empty sessionScope.content }" style="display:none;">
		<c:choose>
			<c:when test="${empty param.page }">
				<div class="cont_top">
					第<span class="top_pageNum"> 1 </span>页
				</div>
				<input id="page" type="text" value="1" style="display:none">
				<br>
				<br>
				<c:forEach items="${sessionScope.content }" var="line" begin="0"
					end="49">
					<c:if test="${line != '　　' }">
						<h4>${line }</h4>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="cont_top">
					第<span class="top_pageNum"> ${param.page } </span>页
				</div>
				<input id="page" type="text" value="${param.page }"
					style="display:none">
				<br>
				<br>
				<c:forEach items="${sessionScope.content }" var="line"
					begin="${(param.page-1)*100 }" end="${param.page*100-1 }">
					<c:if test="${line != '　　' }">
						<h4>${line }</h4>
					</c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<div class="cont_tips">鼠标双击页面进入下一页 右键返回</div>
		<div class="hide_page">
			<c:choose>
			<c:when test="${empty sessionScope.page }">
				<c:if test="${empty param.page }">
					<input type="text" value="1" id="page" style="display:none">
				</c:if>
				<c:if test="${!empty param.page }">
					<input type="text" value="${param.page }" id="page"
						style="display:none">
				</c:if>
			</c:when>
			<c:otherwise>
				<input type="text" value="${sessionScope.page }" id="page"
					style="display:none">
			</c:otherwise>
		</c:choose>
		</div>
	</div>
	<c:choose>
		<c:when test="${!empty sessionScope.book }">
			<div id="cont_showMenu" class="cont_showMenu" onclick="showMenu('${empty sessionScope.user }','bid;${sessionScope.book.bid }','${sessionScope.doc_count}',${param.page })">
				<input id="Menu_page" type="text" value="1" style="display:none;">
				<span id="show_Flag" class="glyphicon glyphicon-chevron-left"></span>
			</div>
		</c:when>
		<c:otherwise>
			<div id="cont_showMenu" class="cont_showMenu" onclick="showMenu('${empty sessionScope.user }','wid;${sessionScope.work.wid }','${sessionScope.doc_count}',${param.page })">
				<input id="Menu_page" type="text" value="1" style="display:none;">
				<span id="show_Flag" class="glyphicon glyphicon-chevron-left"></span>
			</div>
		</c:otherwise>
	</c:choose>
	<div id="cont_menu" class="cont_menu">
		<div class="menu_top">
			<div class="function_div">
				<c:choose>
					<c:when test="${empty sessionScope.user }">
						<a href="${pageContext.request.contextPath}/pages/user/login.jsp">
							<span id="font_favour" class="glyphicon glyphicon-star-empty" style="cursor:pointer;color:#ff962d;">添加收藏</span>
						</a>
					</c:when>
					<c:otherwise>
						<c:choose>
						<c:when test="${empty sessionScope.favour }">
							<span id="font_favour" onclick="addFavour()" class="glyphicon glyphicon-star-empty" style="cursor:pointer;color:#ff962d;">添加收藏</span>
						</c:when>
						<c:otherwise>
							<span id="font_favour" onclick="cancFavour()" class="glyphicon glyphicon-star" style="cursor:pointer;color:#ff962d;">取消收藏</span>
						</c:otherwise>
					</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="function_div">
				<a href="${pageContext.request.contextPath}/pages/user/book.jsp">返回章节选择</a>
			</div>
		</div>
		<div class="menu_center">
			<span>页数选择：</span>
			<br>
			<div id="menu_page" class="menu_page">
				<!-- 放页码 -->
			</div>
		</div>
		<div class="menu_bottom">
			<input id="menu_dec" class="menu_bottom_btn1" type="button" value="上一页" onclick="decpage()">
			<input id="menu_add" class="menu_bottom_btn1" type="button" value="下一页" onclick="addpage()">
		</div>
	</div>
</body>
</html>
