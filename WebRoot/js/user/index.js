/**
 * 
 */
$(document).ready(function(){
	loadIndex();
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
			$("#updateFlag2").css("position","fixed");
			$("#updateFlag2").css("right","24px");
			$("#updateFlag2").css("top","42px");
		}else{
			$("#index_title").css("position","inherit");
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
			$("#updateFlag").css("right","88px");
			$("#updateFlag").css("top","5px");
			$("#updateFlag2").css("position","absolute");
			$("#updateFlag2").css("right","168px");
			$("#updateFlag2").css("top","5px");
		}
	});
});
function start(user) {
	if(user == true){//没登陆
		if($.cookie('user')!='null'){//但有cookie
			$.ajax({
				url : '/SSH_test/userAction_login',
				type : "POST",
				timeout : 1000,
				cache : false,
				success : function(data) {
					$("#header_right").load("/SSH_test/pages/user/index_header_right.jsp");
				},
			});
		}
	}
}
function loadIndex(){
	$.ajax({
		url : '/SSH_test/bookAction_getData',
		type : "POST",
		timeout : 1000,
		cache : false,
		success : function() {
			$("#header_right").load("/SSH_test/pages/user/index_header_right.jsp");
			$("#index_center").load("/SSH_test/pages/user/index_center.jsp");
			$("#totalBook").load("/SSH_test/pages/user/index_Allbook.jsp");
		},
	});
	$.ajax({
		url:'/SSH_test/userAction_updateFlag',
		type : "POST",
		timeout : 1000,
		cache : false,
		success : function(data) {
			var j = JSON.parse(data);
			if(j[0]==1){//收藏通知
				$("#updateFlag").css("display","block");
			}else{
				$("#updateFlag").css("display","none");
			}
			if(j[1]==1){//评论通知
				$("#updateFlag2").css("display","block");
			}else{
				$("#updateFlag2").css("display","none");
			}
		},
	});
}
function checkUser(user) {
	start(user);
	if(user==false){
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
function centerLoad(path){
	$.ajax({
		url : path,
		type : "POST",
		timeout : 1000,
		cache : false,
		async : false,// 取消异步请求
		success : function() {
			var path = window.location.href;
			/*debugger*/
			var q = path.indexOf("?");
			var m = path.lastIndexOf("/")+1;
			if(q==-1){
				q = path.length;
			}
			path = path.substring(m,q) ;
			var div = "";
			if(path=="index.jsp"||path=="bookAction_getData"){
				div="totalBook";
			}else if(path=="workAction_readWork"||path=="bookAction_readBook"){
				div="b_bottom";
				$("#index_center").css("background-color","#efefef");
				$("#"+div).attr("class","totalBook");
			}else if(path=="upload.jsp"){
				div="upload_bottom";
				$("#"+div).attr("class","totalBook");
			}
			$("#"+div).load("/SSH_test/pages/user/index_AkindOfBook.jsp");
		},
	});
}
function bottomLoad(value){
	$("#totalBook").load(value);
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
				$.cookie('user',null,{ path: "/"});
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
function classUlon(num) {
	$("#class_ul"+num).css("display", "inline");
}
function classUloff(num) {
	$("#class_ul"+num).css("display", "none");
}
function infoon() {
	$("#user_info").css("z-index","999");
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
function exchangeFlag(num){
	var ls_1s = $("#ls_1").html();
	var ls_1 = ls_1s.substr(0,ls_1s.indexOf("<"));//选中的值
	var str = ls_1s.substr(ls_1s.indexOf("<"),ls_1s.length);//图标
	var ls_2 = $("#ls_2").html();
	var ls_3 = $("#ls_3").html();
	switch(num){
		case 1:break;
		case 2:
			changeSelect(ls_2);
			$("#ls_1").html(ls_2+str);
			$("#ls_2").html(ls_1);
			break;
		case 3:
			changeSelect(ls_3);
			$("#ls_1").html(ls_3+str);
			$("#ls_3").html(ls_1);
			break;
		default:break;
	}
}
function changeSelect(str){
	if(str=='书名'){
		$("#select_select").val("bname");
	}else if(str=='作品名'){
		$("#select_select").val("wname");
	}else if(str=='作者'){
		$("#select_select").val("author");
	}
}
function showFlag(){
	$("#ls_2").css("display","block");
	$("#ls_3").css("display","block");
	$("#ls_2").css("position","fixed");
	$("#ls_2").css("z-index","999");
	$("#ls_3").css("position","fixed");
	$("#ls_3").css("top","90px");
	$("#ls_3").css("z-index","999");
}
function hideFlag(){
	$("#ls_2").css("display","none");
	$("#ls_3").css("display","none");
}
function selectmess() {
	var flag = $("#select_select").val();
	var flag;
	var message = $("#select_message").val();
	if(flag=='wname'){
		$.ajax({
			url : '/SSH_test/workAction_selectW?flag='+flag+'&message='
					+ message,
			type : "POST",
			async : false,// 取消异步请求
			timeout : 1000,
			cache : false,
			success : function() {
				$("#totalBook").load("/SSH_test/pages/user/index_Allbook.jsp");
			},
		});
	}else{
		$.ajax({
			url : '/SSH_test/bookAction_selectB?flag='+flag+'&message='
					+ message,
			type : "POST",
			async : false,// 取消异步请求
			timeout : 1000,
			cache : false,
			success : function() {
				$("#totalBook").load("/SSH_test/pages/user/index_Allbook.jsp");
			},
	});
	}
}
