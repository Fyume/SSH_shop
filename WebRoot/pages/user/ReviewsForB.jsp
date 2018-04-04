<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="mytags"%>
<div class="User_title">我的书评</div><br>
<div class="arrow_div" style="top:248px;"></div>
<div id="MyReviews" class="MyReviews" style="background-color:#c0c0c0;">
	<c:forEach var="rfb" items="${sessionScope.MyRfbSet }" varStatus="num">
	<div style="background-color:#c0c0c0;border-bottom:1px white solid;">
		<div class="Myr_one" style="background-color: #c0c0c0;">
			<img style="width:40px;height:40px;border-radius:40px;" src="${pageContext.request.contextPath }/images/user/userImg/${sessionScope.user.image }">
			你评论<c:choose>
					<c:when test="${empty rfb.book }"><span style="color:green;cursor:pointer;" onclick="gogogo('${pageContext.request.contextPath }/workAction_readWork?wid=${rfb.work.wid}')">《${rfb.work.wname }》</span></c:when>
					<c:otherwise><span style="color:green;cursor:pointer;" onclick="gogogo('${pageContext.request.contextPath }/bookAction_readBook?bid=${rfb.book.bid}')">《${rfb.book.bname }》</span></c:otherwise>
				</c:choose>
			<div style="float:right;background-color:#c0c0c0;height:30px;padding-top:7px;">
				<mytags:date value="${rfb.time*1000 }"></mytags:date>
			</div>
		</div>
		<div class="Myr_two">
			<div style="margin-top:-5px;width:0;height:0;border-width:3px;border-style:solid;border-color:#c0c0c0 #c0c0c0 white white;"></div>
			<div style="margin-top:5px;">${rfb.content }</div><div style="width:10px;;height:100%;border-radius:5px;"></div>
		</div>
		<div style="height:4px;width:100%;background-color:#c0c0c0;"></div>
	</div>
	</c:forEach>
</div>