$(document).ready(function(){
	if($("#book").val()=="true"){
		window.location.href="/SSH_test/pages/index.jsp";
	}
	DateFormat();
	$("#btn_getHistAndFav").click();
});
function DateFormat() {
	var time = new Date($("#publish_t").val() * 1000 * 60 * 60);
	$("#publish").html(time.getYear()+"-"+time.getMonth()+"-"+time.getDate());
	/*alert(time.getYear()+"年"+time.getMonth()+"月"+time.getDate()+"日"+time.getHours()+"时"+time.getMinutes()+"分");*/
}
function getHistAndFav(user,msg){
	window.user_r = user;//方便收藏等功能的后续操作
	window.msg_r = msg;
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = parseInt(msg.substr(n+1, msg.length));
		var json = {
				font : font,
				id : id,
		}
		$.ajax({
			url : '/SSH_test/userAction_getHistAndFav',
			type : "POST",
			dataType : 'json',
			data : {
				json : JSON.stringify(json)
			},
			async : false,
			cache : false,
			success : function(data){
				if(data.h_page!=0){
					$("#read_btn").val("继续阅读");
					$("#read_btn").attr("class","book_btn2");
					$("#read_btn").attr('onclick','read('+data.h_page+')');
				}else{
					$("#read_btn").val("进入阅读");
					$("#read_btn").removeAttr("class");
					$("#read_btn").attr('onclick','read(1)');
				}
				if(data.f_flag!=0){
					$("#font_favour").html("取消收藏");
					$("#font_favour").attr("class","glyphicon glyphicon-star");
					$("#fav_btn").attr('onclick','cancFavour()');
				}else{
					$("#font_favour").html("添加收藏");
					$("#font_favour").attr("class","glyphicon glyphicon-star-empty");
					$("#fav_btn").attr('onclick','addFavour()');
				}
			},
		});
	}
}
function history(user,msg,num){
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = parseInt(msg.substr(n+1, msg.length));
		var json = {
				font : font,
				id : id,
				page : num
		}
		$.ajax({
			url : '/SSH_test/userAction_history',
			type : "POST",
			dataType : 'json',
			data : {
				json : JSON.stringify(json)
			},
			async : false,
			cache : false,
			success : function(){
				window.location.href='/SSH_test/pages/user/read.jsp?page='+num;
			},
		});
	}else{
		window.location.href='/SSH_test/pages/user/read.jsp?page='+num;
	}
}
function read(pageNum){
	$("#page"+pageNum).click();
}
//添加收藏
function addFavour(){
	var user = window.user_r;
	var msg = window.msg_r;
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = parseInt(msg.substr(n+1, msg.length));
		var json = {
				font : font,
				id : id,
				where : "read"
		}
		$.ajax({
			url : '/SSH_test/userAction_addF',
			type : "POST",
			dataType : 'json',
			data : {
				json : JSON.stringify(json)
			},
			async : false,
			cache : false,
			success : function(){
				$("#font_favour").html("取消收藏");
				$("#font_favour").attr("class","glyphicon glyphicon-star");
				$("#fav_btn").attr('onclick','cancFavour()');
			},
		});
	}else{//虽然有c:choose控制 万一session过期
		if(confirm("还没登录呢 要前往登录吗？")){
			window.location.href='/SSH_test/pages/user/login.jsp';
		}
	}
}
//取消收藏
function cancFavour(){
	var user = window.user_r;
	var msg = window.msg_r;
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = parseInt(msg.substr(n+1, msg.length));
		var json = {
				font : font,
				id : id,
				where : "book"
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
				$("#font_favour").html("添加收藏");
				$("#font_favour").attr("class","glyphicon glyphicon-star-empty");
				$("#fav_btn").attr('onclick','addFavour()');
			},
		});
	}else{//虽然有c:choose控制 万一session过期
		if(confirm("还没登录呢 要前往登录吗？")){
			window.location.href='/SSH_test/pages/user/login.jsp';
		}
	}
}