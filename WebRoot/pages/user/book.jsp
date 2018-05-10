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

<c:if test="${!empty sessionScope.book}">
	<title>《${sessionScope.book.bname }》信息页面</title>
</c:if>
<c:if test="${!empty sessionScope.work}">
	<title>《${sessionScope.work.wname }》信息页面</title>
</c:if>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- 用下载下来的bootstrap.min.css没有图标 不知道为什么 可能是需要其他的文件支持 -->

<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/book.css">
</head>
<body onload="checkUser(${empty sessionScope.user})">
	<div class="header">
		<a href="${pageContext.request.contextPath}/bookAction_getData">
			<div class="header_logo">
				<img width=100% height=100% title="在线阅读网站" src="${pageContext.request.contextPath}/images/flag/2018-03-31_164359.png">
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
	<div id="index_center" class="index_center" style="background-color: #c8e3b3;">
		
	</div>
	<div id="b_bottom" class="b_bottom">
		<input id="empty_book" type="text" value="${empty sessionScope.book }"
			style="display:none;"> <input id="empty_work" type="text"
			value="${empty sessionScope.work }" style="display:none;">
		<div class="book_total">
			<br>
			<c:if test="${!empty sessionScope.work }">
				<!-- 展示work -->
				<div class="tt_img">
					<img id="book_img" width=100% height=100%
						title="${sessionScope.work.wname }"
						alt="work:${sessionScope.work.wid }"
						src="${pageContext.request.contextPath}/images/user/workImg${sessionScope.work.image }">
				</div>
				<div class="tt_right">
					<span class="r_bname">《${sessionScope.work.wname }》 </span> <br>
					<span class="r_font">类型：</span> <span class="r_value">用户作品</span> <br>
					<br> <span class="r_font">作者：</span> <span class="r_value">${sessionScope.work.user.uid }</span>
					<br> <br> <span class="r_font">更新日期：</span> <input
						id="publish_t" type="text"
						value="${sessionScope.uploadRecord.time}"> <span
						id="publish" class="r_value"></span> <br> <br> <span
						class="r_font">上传人员：</span> <span class="r_value" id="managerID">${sessionScope.uploadRecord.user.username }</span>
					<br> <br>
					<c:choose>
						<c:when test="${empty sessionScope.history}">
							<input id="read_btn" type="button" value="进入阅读" onclick="read(1)">
						</c:when>
						<c:otherwise>
							<input id="read_btn" type="button" class="book_btn2" value="继续阅读"
								onclick="read(${sessionScope.history.pageNum})">
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${empty sessionScope.user }">
							<a href="${pageContext.request.contextPath}/pages/user/login.jsp">
								<button>
									<span id="font_favour" class="glyphicon glyphicon-star-empty"
										style="color:#ecec00;">添加收藏</span>
								</button>
							</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty sessionScope.favour }">
									<button id="fav_btn" onclick="addFavour()">
										<span id="font_favour" class="glyphicon glyphicon-star-empty"
											style="color:#ecec00;">添加收藏</span>
									</button>
								</c:when>
								<c:otherwise>
									<button id="fav_btn" onclick="cancFavour()">
										<span id="font_favour" class="glyphicon glyphicon-star"
											style="color:#ecec00;">取消收藏</span>
									</button>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
			<c:if test="${!empty sessionScope.book }">
				<!-- 展示book -->
				<div class="tt_img">
					<img width=100% height=100% title="${sessionScope.book.bname }"
						alt="book:${sessionScope.book.bid }"
						src="${pageContext.request.contextPath}/images/bookImg${sessionScope.book.image }">
				</div>
				<div class="tt_right">
					<span class="r_bname">《${sessionScope.book.bname }》 </span> <br>
					<span class="r_font">类型：</span> <span class="r_value">${sessionScope.book.type }</span>
					<br> <br> <span class="r_font">作者：</span> <span
						class="r_value">${sessionScope.book.author }</span> <br> <br>
					<span class="r_font">更新日期：</span> <input id="publish_t" type="text"
						value="${sessionScope.uploadRecord.time}"> <span
						id="publish" class="r_value"></span> <br> <br> <span
						class="r_font">上传人员：</span> <span class="r_value" id="managerID">${sessionScope.uploadRecord.user.username }</span>
					<br> <br>
					<c:choose>
						<c:when test="${empty sessionScope.history}">
							<input id="read_btn" type="button" value="进入阅读" onclick="read(1)">
						</c:when>
						<c:otherwise>
							<input id="read_btn" type="button" class="book_btn2" value="继续阅读"
								onclick="read(${sessionScope.history.pageNum})">
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${empty sessionScope.user }">
							<a href="${pageContext.request.contextPath}/pages/user/login.jsp">
								<button>
									<span id="font_favour" class="glyphicon glyphicon-star-empty"
										style="color:#ecec00;">添加收藏</span>
								</button>
							</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty sessionScope.favour }">
									<button id="fav_btn" onclick="addFavour()">
										<span id="font_favour" class="glyphicon glyphicon-star-empty"
											style="color:#ecec00;">添加收藏</span>
									</button>
								</c:when>
								<c:otherwise>
									<button id="fav_btn" onclick="cancFavour()">
										<span id="font_favour" class="glyphicon glyphicon-star"
											style="color:#ecec00;">取消收藏</span>
									</button>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
			<div class="tt_chapter">
				<div class="bt_title">
					<div class="bt_t_font">页数选择</div>
				</div>
				<div class="bt_part">
					<c:choose>
						<c:when test="${!empty sessionScope.work}">
							<input id="btn_getHistAndFav" type="button"
								onclick="getHistAndFav('${empty sessionScope.user }','wid;${sessionScope.work.wid }',)">
							<input id="mess" type="text"
								value="wid;${sessionScope.work.wid }">
							<div id="ccc"></div>
						</c:when>
						<c:otherwise>
							<input id="btn_getHistAndFav" type="button"
								onclick="getHistAndFav('${empty sessionScope.user }','bid;${sessionScope.book.bid }')">
							<input id="mess" type="text"
								value="bid;${sessionScope.book.bid }">
							<div id="ccc"></div>
						</c:otherwise>
					</c:choose>
					<input id="emptyUser" type="text"
						value="${empty sessionScope.user }"> <input id="docCount"
						type="text" value="${sessionScope.doc_count }">
					<div class="chapter_bottom">
						<div class="aaa">
							<div class="bbb" onclick="pageDec('${param.cptPage}')">
								<span class="glyphicon glyphicon-chevron-left"></span>
							</div>
							<div class="bbb" style="margin-left:20%;"
								onclick="pageAdd('${param.cptPage}')">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tt_comment">
				<div class="bt_title">
					<div class="bt_t_font">评论区</div>
				</div>

				<!-- 我的评论 -->

				<form id="MyReviewsF" action="${pageContext.request.contextPath}/userAction_Review" method="post">
					<div class="comment_text">
						<c:choose>
							<c:when test="${!empty sessionScope.user }">
								<div class="con_top_img" style="float:left;">
									<img alt=""
										src="${pageContext.request.contextPath}/images/user/userImg/${sessionScope.user.image}">
								</div>
								<div class="con_top_font" style="float:left;">${sessionScope.user.username }
									评论：</div>
								<div class="text_mycomment">
									<input type="button" onclick="submitReviews('MyReviewsF')" class="btn btn-info pull-right" value="提交">
									<textarea name="rfb.content" cols="20" rows="3"
										placeholder="来说几句吧。。。。"></textarea>
								</div>
							</c:when>
							<c:otherwise>
								<div class="con_top_img" style="float:left;">
									<img alt=""
										src="${pageContext.request.contextPath}/images/user/userImg/aaa.jpg">
								</div>
								<div class="con_top_font" style="float:left;">XXXX 评论：</div>、
								<div class="text_mycomment">
									<input type="submit" class="btn btn-info pull-right" value="提交">
									<textarea name="rfb.content" cols="20" rows="3" placeholder="来说几句吧。。。。"></textarea>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</form>

				<!-- 显示评论 -->
				<div class="reviews">
					<div class="reviews_title">相关评论</div>
					<div class="line_btn" id="line_btn" onclick="getReviews()">点击展开评论</div>
					<div id="Allreviews">
						
					</div>
					<!-- 分页 -->
					<div></div>
				</div>
			</div>

			<div style="width:100%;height:100px;">
				<!-- 留白 -->
				<span id="r_close" style="display:none;">${param.close }</span>
			</div>
		</div>
	</div>
	<input id="USER" type="text" value="${empty sessionScope.user}" class="hidden">
	<div id="con_bottom_reviews" class="con_bottom_reviews">
		<form id="reviewForm" action="${pageContext.request.contextPath}/userAction_ReviewForR">
			<input id="font_id" name="font_id" type="text" value="">
			<div class="text_mycomment">
				<input type="button" class="btn btn-info btn-sm pull-right"
					value="提交" onclick="submitReviews('reviewForm')">
				<textarea name="rfr.content" cols="20" rows="3"
					placeholder="来说几句吧。。。。"></textarea>
			</div>
		</form>
		<input id="reviewsRs" type="text" value="${requestScope.reviewsRs }">
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
<script src="${pageContext.request.contextPath}/js/user/book.js"></script>
</html>
