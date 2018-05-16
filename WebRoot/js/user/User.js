/**
 * 
 */
$(document).ready(function() {
	var list = $("#listNum").attr('title');
	var path = window.location.href;
	var q = path.indexOf("?");
	var r = path.indexOf("&");
	if(q==-1){
		getMyInfo();
	}else if(r==-1){
		var eq = path.indexOf("=");
		var param = path.substring(q+1, eq);
		if(param=='func'){
			var value = path.substring(eq+1, path.length);
			if(value==0){//消息
				loadMyReviews(2);
			}else if(value==1){//收藏夹
				getMyFav(0);
			}else{
				getMyInfo();
			}
		}
	}
});
function uploadi() {
	$("#image").click();
}
function loadPs(){
	$("#User_table").load("/SSH_test/pages/user/updatePassword.jsp");
}
// 检查上传图片的格式
function checkimg(path) {
	var str = /[.](jpg|jpeg)$/;
	var res = str.test(path);
	return res;
}
function bottomLoad_U(value){
	$("#User_table").load(value);break;
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
/************修改密码*************/
function u_checkPs(){
	var oldPs = $("#ps1").val();
	var newPs = $("#ps2").val();
	var newPs_c = $("#ps3").val();
	var flag = true;
	for(var i=1;i<4;i++){
		if($("#ps"+i).val().match(/\S+/) == null){
			$("#U_flag"+i).html("不能为空");
			flag = false;
			return false;
		}else{
			$("#U_flag"+i).html("");
		}//还没写完的啊！！！！！！！！！！！！1
	}
	if(flag){
		if (newPs != newPs_c ) {
			$("#U_flag3").html("两次密码不一样");
			flag = false;
			return false;
		}else{
			$("#U_flag3").html("");
			return true;
		}
	}
	return flag;
}
function submitPs(){
	if(u_checkPs()){
		$.ajax({
			url:'/SSH_test/userAction_updatePs',
			type:'POST',
			data:$("#PsForm").serialize(),
			dataType:'json',
			async:false,
			cache:false,
			success:function(){
				alert("修改成功");
			},
		});
	}
}
function checkpw() {
	var r_ps = $("#r_password").val();
	var ps = $("#RG_password").val();
	if(ps.match(/\S+/) == null){
		$("#pswarnning").html("密码不能为空");
		return false;
	}else{
		$("#pswarnning").html("");
		if (r_ps != ps ) {
			$("#rpswarnning").html("两次密码不一样");
			return false;
		}else{
			
			$("#rpswarnning").html("");
			return true;
		}
	}
}
/**********作品部分*************/
function coveron(num){
	$("#img_cover"+num).css("display","block");
}
function coveroff(num){
	$("#img_cover"+num).css("display","none");
}
function deleteWork(wid){
	if(confirm("确定删除该作品？")){
		$.ajax({
			url:'/SSH_test/workAction_delete',
			type:'post',
			data:{
				wid:wid
			},
			async : false,
			cache : false,
			success : function(){
				$("#User_table").load("/SSH_test/pages/user/MyWork.jsp");
			},
		});
	}
}
function edit_divOn(num){
	$("#work_edit_div").css("display","block");
	var wid = $("#work_img"+num).attr("alt");
	var src = $("#work_img"+num).attr("src");
	var wname = $("#work_wname"+num).html();
	var description = $("#work_img"+num).attr("title");
	$("#Work_wid_t").val(wid);
	$("#Work_wid").html(wid);
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
function workFile(){
	$("#workfile").click();
}
/*function checkForm2(){
	
}*/
/*************收藏夹**************/
function cancFav(user,msg){
	if(user=="false"){
		if(confirm("确定取消收藏？")){
			var n = msg.indexOf(";");
			var font = msg.substring(0, n);
			var id = parseInt(msg.substr(n+1, msg.length));
			var json = {
					font : font,
					id : id,
					where : "user"
			}
			$.ajax({
				url : '/SSH_test/userAction_delF',
				type : "POST",
				dataType : 'json',
				data : {
					json : JSON.stringify(json)
				},
				async : false,
				cache : false,
				success : function(){
					if(font=="wid"){
						$("#work_"+id).remove();
					}else if(font=="bid"){
						$("#book_"+id).remove();
					}
				},
			});
		}
	}else{//虽然有c:choose控制 万一session过期
		if(confirm("还没登录呢 要前往登录吗？")){
			window.location.replace='/SSH_test/pages/user/login.jsp';
		}
	}
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
function loadMyReviews(num){
	var url = "/SSH_test/userAction_getMyBookReviews";
	var jsp = "/SSH_test/pages/user/ReviewsForB.jsp";
	switch(num){
		case 1:
			url = "/SSH_test/userAction_getMyReviews";
			jsp = "/SSH_test/pages/user/ReviewsForR.jsp";
			break;
		case 2:
			url = "/SSH_test/userAction_getReviewsAboutMe";
			jsp = "/SSH_test/pages/user/ReviewsAboutMe.jsp";
			break;
		default:break;
	}
	$.ajax({
		url : url,
		type : "POST",
		timeout : 1000,
		cache : false,
		success : function() {
			$("#flag2").css("display","none");
			$("#User_table").load(jsp);
		},
	});
}
function gogogo(url){
	window.location.replace(url);
}