/**
 * 
 */
function start(msg) {
	$.ajax({
		url : 'http://localhost:8080/SSH_test/bookAction_getData',
		type : "POST",
		timeout : 1000,
		cache : false,
		async : false,// 取消异步请求
		success : function() {
			if (msg == true) {
				window.location.reload(true);
			}
		},
	});
}
function login(user, path) {
	if (user == "true") {// 没登陆
		window.location.href = path + "/pages/user/login.jsp";
	} else {
		window.location.href = path + "/pages/user/User.jsp";
	}
}
function classifyon() {
	$("#classify_st").css("display", "inline");
}
function classifyoff() {
	$("#classify_st").css("display", "none");
}

function classUlon(num) {
	$("#class_ul"+num).css("display", "inline");
}
function classUloff(num) {
	$("#class_ul"+num).css("display", "none");
}
function infoon() {
	$("#user_info").css("display", "inline");
}
function infooff() {
	$("#user_info").css("display", "none");
}
function check_selecttext() {
	var input = /^[a-zA-Z0-9\u4e00-\u9fa5 ]{1,20}$/;// 长度为1-20的字符和数字
	if (input.test($("#select_message").val()) == false) {
		$("#select_message").val('');
	}
}
function selectmess() {
	var num = $("#select_select").val();
	var flag;
	var message = $("#select_message").val();
	if (num == 1) {
		$
				.ajax({
					url : 'http://localhost:8080/SSH_test/bookAction_selectB?flag=bname&message='
							+ message,
					type : "POST",
					async : false,// 取消异步请求
					timeout : 1000,
					cache : false,
					success : function reloadJSP() {
						window.location.reload();
					}
				})
	} else if (num == 3) {
		$
				.ajax({
					url : 'http://localhost:8080/SSH_test/bookAction_selectB?flag=author&message='
							+ message,
					type : "POST",
					async : false,// 取消异步请求
					timeout : 1000,
					cache : false,
					success : function reloadJSP() {
						window.location.reload();
					}
				})
	} else if (num == 2) {
		$
				.ajax({
					url : 'http://localhost:8080/SSH_test/workAction_selectW?flag=wname&message='
							+ message,
					type : "POST",
					async : false,// 取消异步请求
					timeout : 1000,
					cache : false,
					success : function reloadJSP() {
						window.location.reload();
					}
				})
	}
}