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
		success:function refresh(){
				if(msg==true){
					window.location.reload(true);
				}
		},
	});
}
function login(path){
	window.location.href=path+"/pages/user/login.jsp";
}
function classifyon() {
	$("#classify_st").css("display", "inline");
}
function classifyoff() {
	$("#classify_st").css("display", "none");
}

function classUl1on() {
	$("#class_ul1").css("display", "inline");
}
function classUl1off() {
	$("#class_ul1").css("display", "none");
}

function classUl2on() {
	$("#class_ul2").css("display", "inline");
}
function classUl2off() {
	$("#class_ul2").css("display", "none");
}

function classUl3on() {
	$("#class_ul3").css("display", "inline");
}
function classUl3off() {
	$("#class_ul3").css("display", "none");
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
		$.ajax({
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
		$.ajax({
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
		$.ajax({
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