<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytags" prefix="mytags"%>
<<script type="text/javascript">
$(document).ready(function(){
	$("#image").change(function() {
		if (!checkimg($("#image").val())) {
			alert("图片格式不对啊！");
		} else {
			var path = getObjectURL(this.files[0]);
			$("#User_img").attr("src", path);//浏览器缓存是最骚的 暂时不理
		}
	});
	$("#workimage").change(function() {
		if (!checkimg($("#workimage").val())) {
			alert("图片格式不对啊！");
		} else {
			var path = getObjectURL(this.files[0]);
			$("#Work_img").attr("src", path);//浏览器缓存是最骚的 暂时不理
		}
	});
});
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
</script>
    <div class="User_title">基本信息</div>
		<div class="arrow_div" style="border-top-left-radius:25px;"></div>
		<form action="${pageContext.request.contextPath }/userAction_update" method="post" enctype="multipart/form-data"
				onsubmit="return checkForm()">
			<div style="width:10%;height:20%;">
				<c:choose>
					<c:when test="${empty sessionScope.user.image }">
						<img id="User_img" alt="头像" src="${pageContext.request.contextPath }/images/flag/user_img(default).png">
					</c:when>
					<c:otherwise>
						<img id="User_img" alt="头像" src="${pageContext.request.contextPath }/images/user/userImg/${sessionScope.user.image }">
					</c:otherwise>
				</c:choose>
				<div class="file_button">
					<input id="image_btn" type="button" value="修改头像" onclick="uploadi()" disabled="disabled"> <br>
					<div style="font-size:12px;color:red;">(仅限jpg,jpeg)</div>
					<input type="file" id="image" name="image" accept="image/*">
				</div>
			</div>
			<div id="User_AllInfo" class="User_AllInfo">
				<span class="User_font">用户ID：</span>
				<input type="text" name="user.uid" style="display:none;" value="${sessionScope.user.uid }">
				<span class="User_value">${sessionScope.user.uid }</span>
				<br><br>
				<span class="User_font">用户名：</span>
				<span class="User_value"><input type="text" disabled="disabled" name="user.username" value="${sessionScope.user.username }"></span>
				<br><br>
				<span class="User_font">姓名：</span>
				<span class="User_value"><input type="text" disabled="disabled" name="user.name" value="${sessionScope.user.name }"></span>
				<br><br>
				<span class="User_font">地址：</span>
				<span class="User_value"><input type="text" disabled="disabled" name="user.address" value="${sessionScope.user.address }"></span>
				<br><br>
				<span class="User_font">身份证：</span>
				<span class="User_value">
					<input type="text" name="hide" disabled="disabled" value="<mytags:hide value='${sessionScope.user.IDCN }'></mytags:hide>">
					<input type="text" name="user.IDCN" class="edit_text" value="${sessionScope.user.IDCN }">
				</span>
				<br><br>
				<span class="User_font">手机号：</span>
				<span class="User_value">
					<input type="text" name="hide" disabled="disabled" name="user.telnum" value="<mytags:hide value='${sessionScope.user.telnum }'></mytags:hide>">
					<input type="text" name="user.telnum" class="edit_text" value="${sessionScope.user.telnum }" onchange="checktelnum()">
				</span>
				<br><br>
				<span class="User_font">邮箱：</span>
				<span class="User_value"><mytags:hide value="${sessionScope.user.email }"></mytags:hide></span>
				<a style="float:right;margin-right:30%;padding-top:5px;"href="${pageContext.request.contextPath }/pages/user/activate.jsp">修改邮箱</a>
				<br><br>
				<span class="User_font">激活时间：</span>
				<span class="User_value"><mytags:date value="${sessionScope.user.activateTime }"></mytags:date></span>
				<br><br>
			</div>
			<input class="User_btn" id="edit_btn" type="button" value="进入编辑" onclick="edit()">
			<br><br>
			<input class="User_btn" id="submit_btn" type="submit" value="提交" disabled="disabled" style="opacity:0.5;">
		</form>
