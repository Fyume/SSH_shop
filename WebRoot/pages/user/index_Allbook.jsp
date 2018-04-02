<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="myTags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index_book.jsp' starting page</title>
    
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
	<div class="kindOfBook">
		<div class="book_top_type">
			<div class="book_top_left">网络小说</div>
			<c:set var="type1" value="0"></c:set>
			<c:choose>
				<c:when test="${sessionScope.classfy=='全部分类'}">
					<div class="book_top_right">
						<a href="${pageContext.request.contextPath}/bookAction_selectB?flag=type&message=网络小说">
							更多>>
						</a>
					</div>
					<c:set var="begin" value="0"></c:set>
					<c:set var="end" value="${sessionScope.listSize }"></c:set>
				</c:when>
				<c:otherwise>
					<c:if test="${empty param.page }">
							<c:set var="begin" value="0"></c:set>
							<c:set var="end" value="13"></c:set>
					</c:if>
					<c:if test="${!empty param.page }">
							<c:set var="begin" value="${(param.page-1)*14 }"></c:set>
							<c:set var="end" value="${param.page*14-1 }"></c:set>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
		<c:forEach items="${sessionScope.booklist }" var="book" begin="${begin }" end="${end }">
			<c:if test="${book.type eq '网络小说' }">
				<c:choose>
					<c:when test="${sessionScope.classfy=='全部分类' }">
						<c:if test="${type1 != 14 }">
							<c:set var="type1" value="${type1+1}"></c:set>
							<div class="book_border">
								<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
									<div class="book_img">
										<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
									</div>
								</a>
								<div class="book_title">
									<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
										${book.bname } </a>
								</div>
								<div class="book_publish">
									<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
								</div>
							</div>
						</c:if>
					</c:when>
					<c:otherwise>
						<div class="book_border">
							<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
								<div class="book_img">
									<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
								</div>
							</a>
							<div class="book_title">
								<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
									${book.bname } </a>
							</div>
							<div class="book_publish">
								<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
	</div>
	<div class="kindOfBook">
		<div class="book_top_type">
			<div class="book_top_left">文学作品</div>
			<c:set var="type2" value="0"></c:set>
			<c:choose>
				<c:when test="${sessionScope.classfy=='全部分类'}">
					<div class="book_top_right">
						<a href="${pageContext.request.contextPath}/bookAction_selectB?flag=type&message=文学作品">
							更多>>
						</a>
					</div>
					<c:set var="begin" value="0"></c:set>
					<c:set var="end" value="${sessionScope.listSize }"></c:set>
				</c:when>
				<c:otherwise>
					<c:if test="${empty param.page }">
							<c:set var="begin" value="0"></c:set>
							<c:set var="end" value="13"></c:set>
					</c:if>
					<c:if test="${!empty param.page }">
							<c:set var="begin" value="${(param.page-1)*14 }"></c:set>
							<c:set var="end" value="${param.page*14-1 }"></c:set>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
		<c:forEach items="${sessionScope.booklist }" var="book" begin="${begin }" end="${end }">
			<c:if test="${book.type eq '文学作品' }">
				<c:choose>
					<c:when test="${sessionScope.classfy=='全部分类' }">
						<c:if test="${type2 != 14 }">
							<c:set var="type2" value="${type2+1}"></c:set>
							<div class="book_border">
								<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
									<div class="book_img">
										<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
									</div>
								</a>
								<div class="book_title">
									<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
										${book.bname } </a>
								</div>
								<div class="book_publish">
									<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
								</div>
							</div>
						</c:if>
					</c:when>
					<c:otherwise>
						<div class="book_border">
							<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
								<div class="book_img">
									<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
								</div>
							</a>
							<div class="book_title">
								<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
									${book.bname } </a>
							</div>
							<div class="book_publish">
								<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
	</div>

	<div class="kindOfBook">
		<div class="book_top_type">
			<div class="book_top_left">社会科学</div>
			<c:set var="type3" value="0"></c:set>
			<c:choose>
				<c:when test="${sessionScope.classfy=='全部分类'}">
					<div class="book_top_right">
						<a href="${pageContext.request.contextPath}/bookAction_selectB?flag=type&message=社会科学">
							更多>>
						</a>
					</div>
					<c:set var="begin" value="0"></c:set>
					<c:set var="end" value="${sessionScope.listSize }"></c:set>
				</c:when>
				<c:otherwise>
					<c:if test="${empty param.page }">
							<c:set var="begin" value="0"></c:set>
							<c:set var="end" value="13"></c:set>
					</c:if>
					<c:if test="${!empty param.page }">
							<c:set var="begin" value="${(param.page-1)*14 }"></c:set>
							<c:set var="end" value="${param.page*14-1 }"></c:set>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
		<c:forEach items="${sessionScope.booklist }" var="book" begin="${begin }" end="${end }">
			<c:if test="${book.type eq '社会科学' }">
			<c:choose>
				<c:when test="${sessionScope.classfy=='全部分类' }">
					<c:if test="${type3 != 14 }">
						<c:set var="type3" value="${type3+1}"></c:set>
						<div class="book_border">
							<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
								<div class="book_img">
									<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
								</div>
							</a>
							<div class="book_title">
								<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
									${book.bname } </a>
							</div>
							<div class="book_publish">
								<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
							</div>
						</div>
					</c:if>
				</c:when>
				<c:otherwise>
					<div class="book_border">
						<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
							<div class="book_img">
								<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
							</div>
						</a>
						<div class="book_title">
							<a title="${book.bname }" href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
								${book.bname } </a>
						</div>
						<div class="book_publish">
							<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			</c:if>
		</c:forEach>
	</div>
	<div class="kindOfBook">
		<div class="book_top_type">
			<div class="book_top_left">用户作品</div>
		</div>
		<c:if test="${empty param.page }">
				<c:set var="begin" value="0"></c:set>
				<c:set var="end" value="13"></c:set>
		</c:if>
		<c:if test="${!empty param.page }">
				<c:set var="begin" value="${(param.page-1)*14 }"></c:set>
				<c:set var="end" value="${param.page*14-1 }"></c:set>
		</c:if>
		<c:forEach items="${sessionScope.worklist }" var="work" begin="${begin }" end="${end }">
			<div class="book_border">
				<a title="${work.wname }" href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
					<div class="book_img">
						<img width=100% height=100% src="${pageContext.request.contextPath}/images/user/workImg/${work.image }" alt="${work.wname }">
					</div>
				</a>
				<div class="book_title">
					<a title="${work.wname }" href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
						${work.wname } </a>
				</div>
				<div class="book_publish">
					<myTags:date type="1" value="${work.uploadtime*1000 }"></myTags:date>
				</div>
			</div>
		</c:forEach>
	</div>
  </body>
</html>
