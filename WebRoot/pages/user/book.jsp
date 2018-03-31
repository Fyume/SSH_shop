<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/mytags" prefix="myTags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
				<img width=100% height=100% alt="" src="${pageContext.request.contextPath}/images/flag/2018-03-31_164359.png">
			</div>
		</a>
		<div class="header_center">
			<fieldset>
				<select id="select_select">
					<option value="1">书名</option>
					<option value="2">作品名</option>
					<option value="3">作者</option>
				</select>
				<input class="fs_input1" id="select_message" type="text" name="select_message" placeholder="输入作品名/书名(1-20个字符、数字)"onblur="check_selecttext()">
				<input class="fs_input2 btn btn-info" type="button" value="搜索" onclick="selectmess()">
			</fieldset>
		</div>
		<c:if test="${sessionScope.user.u_permission }">
			<a href="${pageContext.request.contextPath}/pages/manager/edit.jsp">
				<div class="managerPage">
					前往管理员界面
				</div>
			</a>
		</c:if>
		<div id="header_right" class="header_right">
			<div class="h_r_user btn btn-default" onmouseover="infoon()" onmouseout="infooff()" onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
				<span class="glyphicon glyphicon-user"></span> <span
					style="color:red;font-weight:400">${sessionScope.user.username }</span>
			</div>
			<div class="h_r_user btn btn-default">消息</div>
			<div id="updateFlag"></div>
			<a href="${pageContext.request.contextPath}/userAction_getMyFavBy?type=0">
				<div class="h_r_user btn btn-default">收藏夹</div>
			</a>
			<a href="${pageContext.request.contextPath}/pages/user/upload.jsp">
				<div class="user_upload" style="margin-top:-5px;">
					<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
				</div>
			</a>
		</div>
	</div>
	<div class="index_center" style="background-color:#c8e3b3;">
		<div id="index_title" class="index_title">
			<div class="class_title" style="border-left:0;">
				<a href="${pageContext.request.contextPath}/bookAction_getData">
					首页
				</a>
			</div>
			<c:forEach items="${sessionScope.typelist }" var="type"
				varStatus="num">
				<div>
					<div class="class_title" onmouseover="classUlon(${num.count})"
						onmouseout="classUloff(${num.count})">
						<a
							href="${pageContext.request.contextPath}/bookAction_selectB?flag=type&message=${type.type}">${type.type }</a>
					</div>
	
				</div>
				<%-- <div id="class_ul${num.count}" class="class_ul${num.count}"
					onmouseover="classUlon(${num.count})"
					onmouseout="classUloff(${num.count})">
			<!-- 数据库里面用";"分开 -->
					<c:set value="${fn:split(type.type_flag,';') }" var="type_flag"></c:set>
					<c:forEach items="${type_flag }" var="flag" begin="0" end="5">
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/bookAction_selectB?flag=type_flag&message=${type.type}">${flag }</a></li>
						</ul>
					</c:forEach>
					<c:forEach items="${type_flag }" var="flag" begin="6" end="12">
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/bookAction_selectB?flag=type_flag&message=${type.type}">${flag }</a></li>
						</ul>
					</c:forEach>
				</div> --%>
			</c:forEach>
			<div class="class_title">
				<a href="${pageContext.request.contextPath}/workAction_getData">
					用户作品
				</a>
			</div>
			<div class="class_title">
				<a href="${pageContext.request.contextPath}/userAction_random">
					随便看看
				</a>
			</div>
		</div>
	</div>
	<div class="b_bottom">
		<input id="empty_book" type="text" value="${empty sessionScope.book }"
			style="display:none;"> <input id="empty_work" type="text"
			value="${empty sessionScope.work }" style="display:none;">
		<div class="book_total">
			<br>
			<c:if test="${!empty sessionScope.work }">
				<!-- 展示work -->
				<div class="tt_img">
					<img id="book_img" width=100% height=100% title="${sessionScope.work.wname }"
						alt="work:${sessionScope.work.wid }"
						src="${pageContext.request.contextPath}/images/user/workImg${sessionScope.work.image }">
				</div>
				<div class="tt_right">
					<span class="r_bname">《${sessionScope.work.wname }》 </span> <br>
					<span class="r_font">类型：</span> <span class="r_value">用户作品</span> <br>
					<br> <span class="r_font">作者：</span> <span class="r_value">${sessionScope.work.user.uid }</span>
					<br>
					<br> <span class="r_font">更新日期：</span> <input id="publish_t"
						type="text" value="${sessionScope.uploadRecord.time}"> <span
						id="publish" class="r_value"></span> <br>
					<br> <span class="r_font">上传人员：</span> <span class="r_value"
						id="managerID">${sessionScope.uploadRecord.user.uid }</span> <br>
					<br>
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
					<br>
					<br> <span class="r_font">作者：</span> <span class="r_value">${sessionScope.book.author }</span>
					<br>
					<br> <span class="r_font">更新日期：</span> <input id="publish_t"
						type="text" value="${sessionScope.uploadRecord.time}"> <span
						id="publish" class="r_value"></span> <br>
					<br> <span class="r_font">上传人员：</span> <span class="r_value"
						id="managerID">${sessionScope.uploadRecord.user.uid }</span> <br>
					<br>
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
					<div class="bt_t_font">
						章节选择
					</div>
				</div>
				<div class="bt_part">
					<c:choose>
						<c:when test="${empty sessionScope.book}">
							<input id="btn_getHistAndFav" type="button" onclick="getHistAndFav('${empty sessionScope.user }','wid;${sessionScope.work.wid }',)" style="display:none;">
							<c:forEach items="${sessionScope.content }" var="line" begin="0" end="${sessionScope.doc_count/100}" varStatus="num">
								<div id="page${num.count }" class="part_div" onclick="history('${empty sessionScope.user }','wid;${sessionScope.work.wid }',${num.count})">
									${num.count }
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<input id="btn_getHistAndFav" type="button" onclick="getHistAndFav('${empty sessionScope.user }','bid;${sessionScope.book.bid }')" style="display:none;">
							<c:forEach items="${sessionScope.content }" var="line" begin="0" end="${sessionScope.doc_count/100}" varStatus="num">
								<div id="page${num.count }" class="part_div" onclick="history('${empty sessionScope.user }','bid;${sessionScope.book.bid }',${num.count})">
									${num.count }
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="tt_comment">
				<div class="bt_title">
					<div class="bt_t_font">
						评论区
					</div>
				</div>
				
				<!-- 我的评论 -->
				
				<form action="${pageContext.request.contextPath}/userAction_Review" method="post" onsubmit="return checklogin(${empty sessionScope.user})">
					<div class="comment_text">
						<c:choose>
							<c:when test="${!empty sessionScope.user }">
								<div class="con_top_img" style="float:left;">
									<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${sessionScope.user.image}">
								</div>
								<div class="con_top_font" style="float:left;">${sessionScope.user.username } 评论：</div>
							</c:when>
							<c:otherwise>
								<div class="con_top_img" style="float:left;">
									<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/aaa.jpg">
								</div>
								<div class="con_top_font" style="float:left;">XXXX 评论：</div>
							</c:otherwise>
						</c:choose>
						
						<div class="text_mycomment">
							<input type="submit" class="btn btn-info pull-right" value="提交">
							<textarea name="rfb.content" cols="20" rows="3" placeholder="来说几句吧。。。。"></textarea>
						</div>
					</div>
				</form>
				
				<!-- 显示评论 -->
				<div class="reviews">
					<div class="reviews_title">相关评论</div>
					<div class="line_btn" id="line_btn" onclick="getReviews()">点击展开评论</div>
						<div id="Allreviews" style="display:none;">
							<c:if test="${empty sessionScope.rfb_set }">
							<span>还没有评论呢，赶紧发表你的感想吧~~</span>
							</c:if>
							<c:if test="${!empty sessionScope.rfb_set }">
								<c:forEach items="${sessionScope.rfb_set }" var="rfb" begin="0" end="7" varStatus="num">
									<div id="reviews_content${num.count }" class="reviews_content">
										<div class="content_top">
											<div class="con_top_img">
												<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${rfb.user.image}">
											</div>
											<div class="con_top_font">${rfb.user.username}</div>
											<div class="con_top_font2">的评论</div>
											<div class="con_top_font" style="float:right;"><myTags:date value="${rfb.time*1000}"></myTags:date></div>
										</div>
										<div class="content_center">
											${rfb.content }
										</div>
											<c:forEach items="${rfb.rfr }" var="rfr" begin="0" end="3" varStatus="nnum">
												<div id="rfr_div${nnum.count }" class="rfr_div">
													<div class="content_top">
														<div class="con_top_img">
															<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${rfr.user1.image}">
														</div>
														<div class="con_top_font">${rfr.user1.username }</div>
														<div class="con_top_font2">回复</div>
														<div class="con_top_img">
															<img alt="" src="${pageContext.request.contextPath}/images/user/userImg/${rfr.user2.image} ">
														</div>
														<div class="con_top_font">${rfr.user2.username }</div>
														<div class="con_top_font" style="float:right;"><myTags:date value="${rfr.time*1000}"></myTags:date></div>
													</div>
													<div class="content_center">
														${rfr.content }
													</div>
													<div class="content_bottom2" id="inner${nnum.count }" onclick="openReviews(this,'rfr_div${nnum.count}')">回复</div>
												</div>
											</c:forEach>
										<div class="content_bottom" id="outer${num.count }" onclick="openReviews(this,'reviews_content${num.count}')">回复</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
					<!-- 分页 -->
					<div>
					
					</div>
				</div>
			</div>
			
			<div style="width:100%;height:100px;">
				<!-- 留白 -->
				<span id="r_close" style="display:none;">${param.close }</span>
			</div>
		</div>
	</div>
	<div id="con_bottom_reviews" class="con_bottom_reviews">
		<form action="${pageContext.request.contextPath}/userAction_ReviewForR" method="post" onsubmit="return checklogin(${empty sessionScope.user})">
			<input id="font_id" name="font_id" type="text" value="">
			<div class="text_mycomment">
				<input type="submit" class="btn btn-info btn-sm pull-right" value="提交">
				<textarea name="rfr.content" cols="20" rows="3" placeholder="来说几句吧。。。。"></textarea>
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
