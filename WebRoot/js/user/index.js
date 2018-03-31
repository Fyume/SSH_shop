/**
 * 
 */
$(document).ready(function(){
	$(window).scroll(function(){
		if($(window).scrollTop()>82){
			//显示绝对位置div
			$("#index_title").css("position","fixed");
			$("#index_title").css("top","0");
			$("#header_right div").css("border","0");
			$("#header_right div").css("border-bottom","1px #c0c0c0 dotted");
			$("#header_right").css("right","15px");
			$("#header_right").css("width","80px");
			$("#header_right").css("height","140px");
			$("#user_info").css("right","95px");
			$("#user_info").css("top","0");
			$("#updateFlag").css("position","fixed");
			$("#updateFlag").css("right","24px");
			$("#updateFlag").css("top","77px");
		}else{
			$("#index_title").css("position","relative");
			$("#index_title").css("top","0");
			$("#index_title").css("left","0");
			$("#header_right div").css("border","0");
			$("#header_right div").css("border-right","1px #c0c0c0 dotted");
			$("#header_right").css("right","0");
			$("#header_right").css("width","325px");
			$("#header_right").css("height","45px");
			$("#user_info").css("right","200px");
			$("#user_info").css("top","43px");
			$("#updateFlag").css("position","absolute");
			$("#updateFlag").css("right","168px");
			$("#updateFlag").css("top","5px");
		}
	});
});
function start(msg,user) {
	if(user == true){
		var page = $("#page_num").html();
		$("#page_a"+page).css("color","red");
		$.ajax({
			url : '/SSH_test/userAction_login',
			type : "POST",
			timeout : 1000,
			cache : false,
			async : false,// 取消异步请求
			success : function(data) {
				if(data=="111"){
					$.cookie('user',null,{expires: -1,path: '/'});
					window.location.reload();
				}
			},
		});
	}else{
		$.ajax({
			url:'/SSH_test/userAction_updateFlag',
			type : "POST",
			timeout : 1000,
			cache : false,
			success : function(data) {
				if(data=="updateFlag"){
					$("#updateFlag").css("display","block");
				}else{
					$("#updateFlag").css("display","none");
				}
			},
		});
	}
	if (msg == true) {
		$.ajax({
			url : 'http://localhost:8080/SSH_test/bookAction_getData',
			type : "POST",
			timeout : 1000,
			cache : false,
			async : false,// 取消异步请求
			success : function() {
				window.location.reload();
			},
		});
	}
}
function checkUser(user) {
	if(user == true){
		var page = $("#page_num").html();
		$("#page_a"+page).css("color","red");
		$.ajax({
			url : '/SSH_test/userAction_login',
			type : "POST",
			timeout : 1000,
			cache : false,
			async : false,// 取消异步请求
			success : function(data) {
				if(data=="111"){
					$.cookie('user',null,{expires: -1,path: '/'});
					window.location.reload();
				}
			},
		});
	}else{
		$.ajax({
			url:'/SSH_test/userAction_updateFlag',
			type : "POST",
			timeout : 1000,
			cache : false,
			success : function(data) {
				if(data=="updateFlag"){
					$("#updateFlag").css("display","block");
				}else{
					$("#updateFlag").css("display","none");
				}
			},
		});
	}
}
function logout(){
	$.ajax({
		url : '/SSH_test/userAction_logOut',
		type : "POST",
		timeout : 1000,
		cache : false,
		async : false,// 取消异步请求
		success : function(data) {
			if(data!=""){
				$.cookie('user',null,{expires: -1,path: '/'});
				window.location.reload();
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
		$.ajax({
				url : 'http://localhost:8080/SSH_test/bookAction_selectB?flag=bname&message='
						+ message,
				type : "POST",
				async : false,// 取消异步请求
				timeout : 1000,
				cache : false,
				success : function reloadJSP() {
					window.location.href="http://localhost:8080/SSH_test";
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
					window.location.href="http://localhost:8080/SSH_test";
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
					window.location.href="http://localhost:8080/SSH_test";
				}
			})
	}
}