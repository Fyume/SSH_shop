<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="myTags"%>
<c:if test="${sessionScope.classfy=='用户作品'}">
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
		<c:forEach items="${sessionScope.worklist }" var="work"
			begin="${begin }" end="${end }">
			<div class="book_border">
				<a title="${work.wname }"
					href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
					<div class="book_img">
						<img width=100% height=100%
							src="${pageContext.request.contextPath}/images/user/workImg/${work.image }"
							alt="${work.wname }">
					</div>
				</a>
				<div class="book_title">
					<a title="${work.wname }"
						href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
						${work.wname } </a>
				</div>
				<div class="book_publish">
					<myTags:date type="1" value="${work.uploadtime*1000 }"></myTags:date>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>
<c:if test="${sessionScope.classfy=='网络小说'}">
	<div class="kindOfBook">
		<div class="book_top_type">
			<div class="book_top_left">网络小说</div>
			<c:set var="type1" value="0"></c:set>
			<c:if test="${empty param.page }">
				<c:set var="begin" value="0"></c:set>
				<c:set var="end" value="13"></c:set>
			</c:if>
			<c:if test="${!empty param.page }">
				<c:set var="begin" value="${(param.page-1)*14 }"></c:set>
				<c:set var="end" value="${param.page*14-1 }"></c:set>
			</c:if>
		</div>
		<c:forEach items="${sessionScope.booklist }" var="book"
			begin="${begin }" end="${end }">
			<c:if test="${book.type eq '网络小说' }">
				<div class="book_border">
					<a title="${book.bname }"
						href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
						<div class="book_img">
							<img width=100% height=100%
								src="${pageContext.request.contextPath}/images/bookImg/${book.image }"
								alt="${book.bname }">
						</div>
					</a>
					<div class="book_title">
						<a title="${book.bname }"
							href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
							${book.bname } </a>
					</div>
					<div class="book_publish">
						<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
</c:if>

<c:if test="${sessionScope.classfy=='文学作品'}">
	<div class="kindOfBook">
		<div class="book_top_type">
			<div class="book_top_left">文学作品</div>
			<c:set var="type2" value="0"></c:set>
			<c:if test="${empty param.page }">
				<c:set var="begin" value="0"></c:set>
				<c:set var="end" value="13"></c:set>
			</c:if>
			<c:if test="${!empty param.page }">
				<c:set var="begin" value="${(param.page-1)*14 }"></c:set>
				<c:set var="end" value="${param.page*14-1 }"></c:set>
			</c:if>
		</div>
		<c:forEach items="${sessionScope.booklist }" var="book"
			begin="${begin }" end="${end }">
			<c:if test="${book.type eq '文学作品' }">
				<div class="book_border">
					<a title="${book.bname }"
						href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
						<div class="book_img">
							<img width=100% height=100%
								src="${pageContext.request.contextPath}/images/bookImg/${book.image }"
								alt="${book.bname }">
						</div>
					</a>
					<div class="book_title">
						<a title="${book.bname }"
							href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
							${book.bname } </a>
					</div>
					<div class="book_publish">
						<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
</c:if>

<c:if test="${sessionScope.classfy=='社会科学'}">
	<div class="kindOfBook">
		<div class="book_top_type">
			<div class="book_top_left">社会科学</div>
			<c:set var="type3" value="0"></c:set>
			<c:if test="${empty param.page }">
				<c:set var="begin" value="0"></c:set>
				<c:set var="end" value="13"></c:set>
			</c:if>
			<c:if test="${!empty param.page }">
				<c:set var="begin" value="${(param.page-1)*14 }"></c:set>
				<c:set var="end" value="${param.page*14-1 }"></c:set>
			</c:if>
		</div>
		<c:forEach items="${sessionScope.booklist }" var="book"
			begin="${begin }" end="${end }">
			<c:if test="${book.type eq '社会科学' }">
				<div class="book_border">
					<a title="${book.bname }"
						href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
						<div class="book_img">
							<img width=100% height=100%
								src="${pageContext.request.contextPath}/images/bookImg/${book.image }"
								alt="${book.bname }">
						</div>
					</a>
					<div class="book_title">
						<a title="${book.bname }"
							href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
							${book.bname } </a>
					</div>
					<div class="book_publish">
						<myTags:date type="1" value="${book.publish*1000*60*60 }"></myTags:date>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
</c:if>
<div class="index_page_div">
	<c:if test="${!empty sessionScope.worklist }">
		<c:forEach items="${sessionScope.worklist }" begin="0"
			end="${sessionScope.listSize/14 }" varStatus="num">
			<a id="page_a${param.page }"
				href="${pageContext.request.contextPath}/pages/index.jsp?page=${num.count}">${num.count }</a>
		</c:forEach>
	</c:if>
	<c:if test="${!empty sessionScope.booklist }">
		<c:forEach items="${sessionScope.booklist }" begin="0"
			end="${sessionScope.listSize/14 }" varStatus="num">
			<a id="page_a${param.page }"
				href="${pageContext.request.contextPath}/pages/index.jsp?page=${num.count}">${num.count }</a>
		</c:forEach>
	</c:if>
</div>
