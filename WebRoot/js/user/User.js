/**
 * 
 */
$(document).ready(function() {
	var list = $("#listNum").attr('title');
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
	getMyInfo();
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
function uploadi() {
	$("#image").click();
}
// 检查上传图片的格式
function checkimg(path) {
	var str = /[.](jpg|jpeg)$/;
	var res = str.test(path);
	return res;
}
/*
 * function getData(user){ var row = $("#row").val(); if(!user){
 *  } }
 */
// 总觉得做得麻烦了
function edit() {
	$.each($("#User_AllInfo input[type='text']"), function() {
		if ($(this).attr("name") == "hide") {// 被屏蔽的text 隐藏text
			$(this).css("display", "none");
		} else if ($(this).attr("class") == "edit_text") {// 没被屏蔽的text 显示text
			$(this).css("display", "inline");
		}
		$(this).removeAttr("disabled");
		$(this).css("border", "1px #c0c0c0 solid");
		$(this).css("box-shadow", "1px 1px 1px #8080ff");
	});
	$("#edit_btn").attr("value", "放弃编辑");
	$("#edit_btn").attr("onclick", "closeEdit()");
	$("#submit_btn").css("opacity", "1");
	$("#submit_btn").removeAttr("disabled");
	$("#image_btn").css("opacity", "1");
	$("#image_btn").removeAttr("disabled");
}
function closeEdit() {
	$.each($("#User_AllInfo input[type='text']"), function() {
		if ($(this).attr("name") == "hide") {// 被屏蔽的text 隐藏text
			$(this).css("display", "inline");
		} else if ($(this).attr("class") == "edit_text") {// 没被屏蔽的text 显示text
			$(this).css("display", "none");
		}
		$(this).attr("disabled", "disabled");
		$(this).css("border", "0");
		$(this).css("box-shadow", "0");
	});
	$("#edit_btn").attr("value", "进入编辑");
	$("#edit_btn").attr("onclick", "edit()");
	$("#submit_btn").css("opacity", "0.5");
	$("#submit_btn").attr("disabled", "disabled");
	$("#image_btn").css("opacity", "0.5");
	$("#image_btn").attr("disabled", "disabled");
}

function checkform() {// 检查表单必填数据
	var flag = true;
	$("#form input[type='text']").each(function() {
		if (this.name != "user.address" && this.name != "user.telnum") {
			if (this.value.match(/\S+/) == null) {// 若匹配不到非空白字符
				alert(this.name + "不能为空");
				this.focus();
				flag = false;
				return false;// 跳出循环而不是返回值
			}
		}
	});
	if (flag == true) {
		if (checkname() == false || checkICDN() == false) {
			return false;
		}
	}
	return flag;
}
function checkname() {
	var str = /^[\u4E00-\u9FA5]{2,4}$/;// 2个到4个中文
	if ($("#name").val() != "") {
		if (str.test($("#name").val()) == false) {
			alert("姓名填写有误");
			$("#name").focus();
			return false;
		}
	}
	return true;
}
function checkICDN() {
	// 18位身份证号码的校验
	// 查询过前6位地址码是110000到659001
	// 6位地址码+8位年月日+3位顺序码+1位数字校验码
	var str = /^[1-6][0-9]{5}(18|19|20)\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
	if ($("#IDCN").val() != "") {
		if (str.test($("#IDCN").val()) == false) {
			alert("身份证填写有误");
			$("#IDCN").focus();
			return false;
		}
	}
	return true;
}
function checktelnum() {
	var str = /^1[3|4|5|8][0-9]\d{8}/;
	if ($("#telnum").val() != "") {
		if (str.test($("#telnum").val()) == false) {
			alert("电话填写有误！");
			$("#telnum").focus();
			return false;
		}
	}
	return true;
}
/**********作品部分*************/
function coveron(num){
	$("#img_cover"+num).css("display","block");
}
function coveroff(num){
	$("#img_cover"+num).css("display","none");
}
function edit_divOn(num){
	$("#work_edit_div").css("display","block");
	var wid = $("#work_img"+num).attr("alt");
	var src = $("#work_img"+num).attr("src");
	var wname = $("#work_wname"+num).html();
	var description = $("#work_img"+num).attr("title");
	$("#Work_wid").val(wid);
	$("#Work_img").attr("src",src);
	$("#Work_wname").val(wname);
	$("#Work_description").val(description);
}
function edit_divOff(){
	$("#work_edit_div").css("display","none");
}
function workImg(){
	$("#workimage").click();
}
function checkForm2(){
	
}
/**********重新用load优化el******/
function getMyInfo(){
	$("#User_table").load("/SSH_test/pages/user/MyInfo.jsp");
}
function getMyWork(){
	$.ajax({
		url : '/SSH_test/workAction_getMyWork',
		type : "POST",
		timeout : 1000,
		cache : false,
		success : function() {
			$("#User_table").load("/SSH_test/pages/user/MyWork.jsp");
		},
	});
}
function getMyFav(num){
	$.ajax({
		url : '/SSH_test/userAction_getMyFavBy?type='+num,
		type : "POST",
		timeout : 1000,
		cache : false,
		success : function() {
			$("#User_table").load("/SSH_test/pages/user/MyFavour.jsp");
		},
	});
}
/************评论*********/
// href="${pageContext.request.contextPath}/userAction_getMyReviews"
function loadMyReviews(){
	$.ajax({
		url : '/SSH_test/userAction_getMyReviews',
		type : "POST",
		timeout : 1000,
		cache : false,
		success : function() {
			$("#User_table").load("/SSH_test/pages/user/Reviews.jsp");
		},
	});
}
function gogogo(url){
	window.location.replace(url);
}