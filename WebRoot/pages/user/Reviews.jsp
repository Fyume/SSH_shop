<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="mytags"%>
<div class="User_title">我的评论</div><br>
<div class="arrow_div" style="top:248px;"></div>
<div id="MyReviews" class="MyReviews">
	<c:forEach var="rfr" items="${sessionScope.MyRfrList }" varStatus="num">
		<div>你回复${rfr.user2.uid }说:"${rfr.content }"</div>
	</c:forEach>
</div>

