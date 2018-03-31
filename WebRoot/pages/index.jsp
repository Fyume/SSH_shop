<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>Index.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body onload="start(${empty sessionScope.typelist },${empty sessionScope.user })">
	<div class="header">
		<a href="${pageContext.request.contextPath}/bookAction_getData">
			<div class="header_logo">
				<img width=100% height=100% alt="" src="${pageContext.request.contextPath}/images/flag/2018-03-31_164359.png">
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
			<div class="h_r_user btn btn-default" onmouseover="infoon()" onmouseout="infooff()" onclick="login('${empty sessionScope.user}','${pageContext.request.contextPath}')">
				<span class="glyphicon glyphicon-user"></span> <span
					style="color:red;font-weight:400">${sessionScope.user.username }</span>
			</div>
			<div class="h_r_user btn btn-default">消息</div>
			<a href="${pageContext.request.contextPath}/userAction_getMyFavBy?type=0">
				<div class="h_r_user btn btn-default">收藏夹</div>
			</a>
			<div id="updateFlag"></div>
			<a href="${pageContext.request.contextPath}/pages/user/upload.jsp">
				<div class="user_upload" style="margin-top:-5px;">
					<span id="upload_flag" class="glyphicon glyphicon-arrow-up">上传</span>
				</div>
			</a>
		</div>
	</div>
	<div class="index_center">
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
	<div id="user_info" class="user_info" onmouseover="infoon()" onmouseout="infooff()">
		<c:choose>
			<c:when test="${empty sessionScope.user }">
				<div class="list_login">
					<a href="${pageContext.request.contextPath}/pages/user/login.jsp">前往登录</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="info_img">
					<c:choose>
						<c:when test="${empty sessionScope.user.image }">
							<img id="user_img" alt="头像" style="border-radius:100%;" src="${pageContext.request.contextPath }/images/flag/user_img(default).png">
						</c:when>
						<c:otherwise>
							<img id="user_img" alt="头像" style="border-radius:100%;" src="${pageContext.request.contextPath }/images/user/userImg/${sessionScope.user.image }">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="list_half">ID：<span style="color:#0080c0;">${sessionScope.user.uid }</span></div>
				<div class="list_half">用户名:<span style="color:red;">${sessionScope.user.username }</span></div>
				<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
					<div class="list_all">
						<span style="font-weight:550;font-size:15px;">个人中心</span>
					</div>
				</a>
				<!-- 未实现暂时用User.jsp过渡 -->
				<a href="${ pageContext.request.contextPath}/pages/user/User.jsp">
					<div class="list_btn">
						设置
					</div>
				</a>
				<div class="list_btn" onclick="logout()">
					退出
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="bottom">
		<div class="totalBook">
			<c:choose>
				<c:when test="${sessionScope.classfy=='用户作品'}">
					<div class="kindOfBook">
						<div class="book_top_type">
							<div class="book_top_left">用户作品</div>
						</div>
						<c:if test="${empty param.page }">
								<c:set var="begin" value="0"></c:set>
								<c:set var="end" value="7"></c:set>
						</c:if>
						<c:if test="${!empty param.page }">
								<c:set var="begin" value="${(param.page-1)*8 }"></c:set>
								<c:set var="end" value="${param.page*8-1 }"></c:set>
						</c:if>
						<c:forEach items="${sessionScope.worklist }" var="work" begin="${begin }" end="${end }">
							<div class="book_border">
								<div class="book_title">
									<a
										href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
										${work.wname } </a>
								</div>
								<div class="book_img">
									<a href="${pageContext.request.contextPath}/workAction_readWork?wid=${work.wid}">
										<img width=100% height=100% src="${pageContext.request.contextPath}/images/user/workImg/${work.image }" alt="${work.wname }">
									</a>
								</div>
								<div class="book_description">
									<span class="desc_font">作者</span>
									<br>
									<span class="desc_value">${work.user.uid }</span>
									<br>
									<span class="desc_font">概述</span>
									<br>
									<div class="desc_value">${work.description }</div>
									<br>
									<span class="desc_font">分类</span>
									<br>
									<span class="desc_value" style="font-size:13px;font-weight: 600;color:#ff8000;">用户作品</span>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<c:if test="${sessionScope.classfy=='全部分类'|| sessionScope.classfy=='网络小说'}">
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
												<c:set var="end" value="7"></c:set>
										</c:if>
										<c:if test="${!empty param.page }">
												<c:set var="begin" value="${(param.page-1)*8 }"></c:set>
												<c:set var="end" value="${param.page*8-1 }"></c:set>
										</c:if>
									</c:otherwise>
								</c:choose>
							</div>
							<c:forEach items="${sessionScope.booklist }" var="book" begin="${begin }" end="${end }">
								<c:if test="${book.type eq '网络小说' }">
									<c:choose>
										<c:when test="${sessionScope.classfy=='全部分类' }">
											<c:if test="${type1 != 8 }">
												<c:set var="type1" value="${type1+1}"></c:set>
												<div class="book_border">
													<div class="book_title">
														<a
															href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
															${book.bname } </a>
													</div>
													<div class="book_img">
														<a href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
															<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
														</a>
													</div>
													<div class="book_description">
														<span class="desc_font">作者</span>
														<br>
														<span class="desc_value">${book.author }</span>
														<br>
														<span class="desc_font">概述</span>
														<br>
														<div class="desc_value">${book.description }</div>
														<br>
														<span class="desc_font">分类</span>
														<br>
														<span class="desc_value" style="font-size:13px;font-weight: 600;color:#008000;">${book.type }</span>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:otherwise>
											<div class="book_border">
												<div class="book_title">
													<a
														href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														${book.bname } </a>
												</div>
												<div class="book_img">
													<a href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
													</a>
												</div>
												<div class="book_description">
													<span class="desc_font">作者</span>
													<br>
													<span class="desc_value">${book.author }</span>
													<br>
													<span class="desc_font">概述</span>
													<br>
													<div class="desc_value">${book.description }</div>
													<br>
													<span class="desc_font">分类</span>
													<br>
													<span class="desc_value" style="font-size:13px;font-weight: 600;color:#008000;">${book.type }</span>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${sessionScope.classfy=='全部分类'|| sessionScope.classfy=='文学作品'}">
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
												<c:set var="end" value="7"></c:set>
										</c:if>
										<c:if test="${!empty param.page }">
												<c:set var="begin" value="${(param.page-1)*8 }"></c:set>
												<c:set var="end" value="${param.page*8-1 }"></c:set>
										</c:if>
									</c:otherwise>
								</c:choose>
							</div>
							<c:forEach items="${sessionScope.booklist }" var="book" begin="${begin }" end="${end }">
								<c:if test="${book.type eq '文学作品' }">
									<c:choose>
										<c:when test="${sessionScope.classfy=='全部分类' }">
											<c:if test="${type2 != 8 }">
												<c:set var="type2" value="${type2+1}"></c:set>
												<div class="book_border">
													<div class="book_title">
														<a
															href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
															${book.bname } </a>
													</div>
													<div class="book_img">
														<a href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
															<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
														</a>
													</div>
													<div class="book_description">
														<span class="desc_font">作者</span>
														<br>
														<span class="desc_value">${book.author }</span>
														<br>
														<span class="desc_font">概述</span>
														<br>
														<div class="desc_value">${book.description }</div>
														<br>
														<span class="desc_font">分类</span>
														<br>
														<span class="desc_value" style="font-size:13px;font-weight: 600;color:#008000;">${book.type }</span>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:otherwise>
											<div class="book_border">
												<div class="book_title">
													<a
														href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														${book.bname } </a>
												</div>
												<div class="book_img">
													<a href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
													</a>
												</div>
												<div class="book_description">
													<span class="desc_font">作者</span>
													<br>
													<span class="desc_value">${book.author }</span>
													<br>
													<span class="desc_font">概述</span>
													<br>
													<div class="desc_value">${book.description }</div>
													<br>
													<span class="desc_font">分类</span>
													<br>
													<span class="desc_value" style="font-size:13px;font-weight: 600;color:#008000;">${book.type }</span>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${sessionScope.classfy=='全部分类'|| sessionScope.classfy=='社会科学'}">
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
												<c:set var="end" value="7"></c:set>
										</c:if>
										<c:if test="${!empty param.page }">
												<c:set var="begin" value="${(param.page-1)*8 }"></c:set>
												<c:set var="end" value="${param.page*8-1 }"></c:set>
										</c:if>
									</c:otherwise>
								</c:choose>
							</div>
							<c:forEach items="${sessionScope.booklist }" var="book" begin="${begin }" end="${end }">
								<c:if test="${book.type eq '社会科学' }">
								<c:choose>
									<c:when test="${sessionScope.classfy=='全部分类' }">
										<c:if test="${type3 != 8 }">
											<c:set var="type3" value="${type3+1}"></c:set>
											<div class="book_border">
												<div class="book_title">
													<a
														href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														${book.bname } </a>
												</div>
												<div class="book_img">
													<a href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
													</a>
												</div>
												<div class="book_description">
													<span class="desc_font">作者</span>
													<br>
													<span class="desc_value">${book.author }</span>
													<br>
													<span class="desc_font">概述</span>
													<br>
													<div class="desc_value">${book.description }</div>
													<br>
													<span class="desc_font">分类</span>
													<br>
													<span class="desc_value" style="font-size:13px;font-weight: 600;color:#008000;">${book.type }</span>
												</div>
											</div>
										</c:if>
									</c:when>
									<c:otherwise>
										<div class="book_border">
												<div class="book_title">
													<a
														href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														${book.bname } </a>
												</div>
												<div class="book_img">
													<a href="${pageContext.request.contextPath}/bookAction_readBook?bid=${book.bid}">
														<img width=100% height=100% src="${pageContext.request.contextPath}/images/bookImg/${book.image }" alt="${book.bname }">
													</a>
												</div>
												<div class="book_description">
													<span class="desc_font">作者</span>
													<br>
													<span class="desc_value">${book.author }</span>
													<br>
													<span class="desc_font">概述</span>
													<br>
													<div class="desc_value">${book.description }</div>
													<br>
													<span class="desc_font">分类</span>
													<br>
													<span class="desc_value" style="font-size:13px;font-weight: 600;color:#008000;">${book.type }</span>
												</div>
											</div>
									</c:otherwise>
								</c:choose>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
				</c:otherwise>
			</c:choose>
			<span id="page_num" style="display:none;">${param.page }</span>
			<c:if test="${sessionScope.classfy!='全部分类' }">
				<div class="index_page_div">
					<c:if test="${!empty sessionScope.worklist }">
						<c:forEach items="${sessionScope.worklist }" begin="0" end="${sessionScope.listSize/8 }" varStatus="num">
							<a id="page_a${param.page }" href="${pageContext.request.contextPath}/pages/index.jsp?page=${num.count}">${num.count }</a>
						</c:forEach>
					</c:if>
					<c:if test="${!empty sessionScope.booklist }">
						<c:forEach items="${sessionScope.booklist }" begin="0" end="${sessionScope.listSize/8 }" varStatus="num">
							<a id="page_a${param.page }" href="${pageContext.request.contextPath}/pages/index.jsp?page=${num.count}">${num.count }</a>
						</c:forEach>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<!-- 用下载下来的bootstrap.min.css没有图标 不知道为什么 可能是需要其他的文件支持 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/index.js"></script>
</body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/index.css">
</html>