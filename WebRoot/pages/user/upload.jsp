<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户上传作品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/upload.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user/upload.css">
  </head>
  
  <body>
  <div class="upload_form">
   <form
		action="${pageContext.request.contextPath }/workAction_upload_U"
		method="post" enctype="multipart/form-data" onsubmit="return check()">
		<div class="upload_title">
			<div class="title_font">标题/作品名 ：</div>
			<div class="title_input">
				<input id="upload_title" type="text" maxlength="15" placeholder="输入标题/作品名">
			</div>
		</div>
		<div class="upload_img"></div>
		<div class="img_button"><input type="button" value="上传封面" onclick="uploadi()"><br>(仅限jpg,jpeg)</div>
		<input class="upload_file" type="file" id="image" name="image" accept="image/*">
		<div class="upload_div" onclick="uploadf()">上传文本文件<br/>(仅限doc,docx,txt)</div>
		<input class="upload_file" type="file" accept="application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain" id="upload" name="upload" > 
		<br/>
		<input class="upload_content" type="text" placeholder="输入内容">
		<br/>
		<div class="upload_button">
			<input
			type="submit" value="提交" >
		</div>
	</form>
	</div>
  </body>
</html>
