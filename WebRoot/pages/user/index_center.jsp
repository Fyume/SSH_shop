<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="index_title" class="index_title">
	<div class="class_title" style="border-left:0;">
		<a href="${pageContext.request.contextPath}/bookAction_getData">
			首页 </a>
	</div>
	<input id="typelist" style="display:none;" type="text" value="${empty sessionScope.typelist }">
	<c:forEach items="${sessionScope.typelist }" var="type" varStatus="num">
		<div>
			<div class="class_title"
				onclick="centerLoad('${pageContext.request.contextPath}/bookAction_selectB?flag=type&message=${type.type}')">
				${type.type }</div>
		</div>
	</c:forEach>
	<div class="class_title"
		onclick="centerLoad('${pageContext.request.contextPath}/workAction_getData')">
		用户作品</div>
	<div class="class_title">
		<a href="${pageContext.request.contextPath}/userAction_random">
			随便看看 </a>
	</div>
</div>
