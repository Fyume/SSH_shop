<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<c:when test="${empty sessionScope.book}">${sessionScope.work.wname }</c:when>
		<c:otherwise>${sessionScope.book.bname }</c:otherwise>
	</c:choose>
</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/read.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/read.css">
<script type="text/javascript">
	function history(path,ID,pageNum){//放到js文件的话会无效，可能是页面元素加载的问题？
		var before = "";
		var str=new Array(2);
		str=ID.split(";");
		/* alert(path+","+str[0]+","+str[1]+","+pageNum); */
		var json={
			s1:str[0],
			s2:str[1],
			s3:pageNum,
		};
		JSON.stringify(json);
		$.ajax({
			url : path + '/userAction_history',
			type : "POST",
			data : {
				json : json
			},
			dataType : "json",
			timeout : 1000,
			cache : false,
			success:function ok(){
				alert("ok");
			}
		})
	}
</script>
</head>

<body onload="page2(${sessionScope.doc_count})">
	<div class="content_div">
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
					<h4>${line }</h4>
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
					<h4>${line }</h4>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${empty sessionScope.book}">
				<c:forEach items="${sessionScope.content }" var="line" begin="0" end="${sessionScope.doc_count/100}" varStatus="num">
					<div class="page_div">
					<a id="a_${num.count }"
						href="${pageContext.request.contextPath}/pages/user/read.jsp?page=${num.count }" onclick="history('${pageContext.request.contextPath}','wid;${sessionScope.work.wid }',${num.count})">
						${num.count } </a>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach items="${sessionScope.content }" var="line" begin="0" end="${sessionScope.doc_count/100}" varStatus="num">
					<div class="page_div">
					<a id="a_${num.count }"
						href="${pageContext.request.contextPath}/pages/user/read.jsp?page=${num.count }" onclick="history('${pageContext.request.contextPath}','bid;${sessionScope.book.bid }',${num.count})">
						${num.count } </a>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
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

		<div class="function_div">
			<a href="${pageContext.request.contextPath}/pages/index.jsp">返回主页</a>
		</div>
		<div class="function_div">
		<c:choose>
			<c:when test="${empty sessionScope.work }">
			<a
				href="${pageContext.request.contextPath}/uesrAction_addF?bid=${sessionScope.book.bid }&page=${num.count }">添加收藏</a>
			</c:when>
			<c:otherwise>
			<a
				href="${pageContext.request.contextPath}/uesrAction_addF?wid=${sessionScope.work.wid }&page=${num.count }">添加收藏</a>
			</c:otherwise>
		</c:choose>
			
		</div>
	</div>
</body>
</html>
