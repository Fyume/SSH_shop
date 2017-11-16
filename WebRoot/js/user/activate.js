/* 重发*/
function step1() {
	$("#step1").attr("class", "step2");
	$("#step2").attr("class", "step");
	$("#step3").attr("class", "step");
	$("#act_resend2").css('display', 'none');
	$("#act_resend1").css('display', 'inline');
}
function step2() {
	if (checkuid("uid1")) {
		$("#step1").attr("class", "step");
		$("#step2").attr("class", "step2");
		$("#step3").attr("class", "step");
		$("#act_resend1").css('display', 'none');
		$("#act_resend2").css('display', 'inline');
	}
}
function step3() {
	$("#step1").attr("class", "step");
	$("#step2").attr("class", "step");
	$("#step3").attr("class", "step2");
	$("#act_resend2").css('display', 'none');
	$("#act_resend3").css('display', 'inline');
	$("#form1").submit();
}
/* 修改邮箱 */
function c_step2() {
	if (checkuid("uid2")) {
		$("#step1").attr("class", "step");
		$("#step2").attr("class", "step2");
		$("#step3").attr("class", "step");
		$("#form_label1").css('display', 'none');
		$("#form_label2").css('display', 'inline');
	}
}
function c_step3() {
		AjaxE();
		if ($("#Ewarnning").text() == "") {
			$("#step1").attr("class", "step");
			$("#step2").attr("class", "step");
			$("#step3").attr("class", "step2");
			$("#form_label2").css('display', 'none');
			$("#form_label3").css('display', 'inline');
			$("#form2").submit();
		}
	}
/* 验证用户ID */
function checkuid(str1) {
	var str = /[a-zA-Z0-9_]{1,16}/;
	if (str.test($("#" + str1).val()) == false) {
		alert("用户ID有误");
		return false;
	}
	return true;
}
/* 验证邮箱格式 */
function checkE(str1) {
	var str = /\w+[@]{1}\w+[.]\w+/;
	if (str.test($("#" + str1).val()) == false) {
		alert("邮箱填写有误");
		return false;
	}
	return true;
}
/* ajax检测邮箱是否已绑定 */
function AjaxE() {
	var path = $("#contextPath").val();
	if(checkE("email")){
	$.ajax({
		url : path + '/userAction_checkE',
		type : "POST",
		async:false,//取消异步请求
		data : {
			email : $("#email").val()
		},
		dataType : "json",
		timeout : 1000,
		cache : false,
		/*
		 * beforeSend : function sendBefore() {// 发送之前执行的方法 alert("发送中..."); },
		 */
		success : function messageSuccess(data) {// 返回时的方法
			if (data.check_E != undefined) {
				$("#Ewarnning").html(data.check_E);
				$("#email").focus();
			} else {
				$("#Ewarnning").html("");
			}
		}

	});
	}
}