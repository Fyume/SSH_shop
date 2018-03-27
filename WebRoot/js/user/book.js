$(document).ready(function(){
	if($("#empty_book").val()=="true"&&$("#empty_work").val()=="true"){
		window.location.href="/SSH_test/pages/index.jsp";
	}
	DateFormat();
	$("#btn_getHistAndFav").click();
});
function DateFormat() {
	var time = new Date($("#publish_t").val() * 1000);
	$("#publish").html((time.getYear()+1900)+"-"+(time.getMonth()+1)+"-"+time.getDate());
	/*alert(time.getYear()+"年"+time.getMonth()+"月"+time.getDate()+"日"+time.getHours()+"时"+time.getMinutes()+"分");*/
}
function getHistAndFav(user,msg){
	window.user_r = user;//方便收藏等功能的后续操作
	window.msg_r = msg;
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
			if(data.h_page==0||data.h_page==null){
				$("#read_btn").val("进入阅读");
				$("#read_btn").removeAttr("class");
				$("#read_btn").attr('onclick','read(1)');
			}else{
				$("#read_btn").val("继续阅读");
				$("#read_btn").attr("class","book_btn2");
				$("#read_btn").attr('onclick','read('+data.h_page+')');
			}
			if(data.f_flag==0||data.f_flag==null){
				$("#font_favour").html("添加收藏");
				$("#font_favour").attr("class","glyphicon glyphicon-star-empty");
				$("#fav_btn").attr('onclick','addFavour()');
			}else{
				$("#font_favour").html("取消收藏");
				$("#font_favour").attr("class","glyphicon glyphicon-star");
				$("#fav_btn").attr('onclick','cancFavour()');
			}
			$("#managerID").html(data.managerID);
			$("#publish_t").val(data.time);
			DateFormat();
		},
	});
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
/***********评论区**********/
function checklogin(str){
	if(str==true){
		if(confirm("没登录呢,是否前往登录？")){
			window.location.href='/SSH_test/pages/user/login.jsp';
			return false;//有用？
		}
		return false;
	}else{
		return true;
	}
}
//reviews_content外rfr_div里
//是不是做复杂了。。。
function openReviews($this,id){//打开回复入口
	var n = id.lastIndexOf("t");
	var str_a = id.substr(0, n+1);
	var str_b = id.substr(n+1,id.length);
	if(str_a=='reviews_content'){//若是外围点击
		$(".content_bottom2").each(function(){//则关闭内部回复
			var id = $(this).attr("id");
			var n = id.lastIndexOf("r");
			var str_a = id.substr(0, n+1);
			var str_b = id.substr(n+1,id.length);
			closeReviews(this,"rfr_div"+str_b);
		});
		$("#font-id").val("rfb:"+str_b);
		$("#con_bottom_reviews").css("display","block");
		$("#con_bottom_reviews").appendTo($("#"+id));
	}else{
		$(".content_bottom").each(function(){//否则关闭外部回复
			var id = $(this).attr("id");
			var n = id.lastIndexOf("r");
			var str_a = id.substr(0, n+1);
			var str_b = id.substr(n+1,id.length);
			closeReviews(this,"reviews_content"+str_b);
		});
		$("#font-id").val("rfr:"+str_b);
		$("#con_bottom_reviews").css("display","block");
		$("#con_bottom_reviews").appendTo($("#"+id));
	}
	$($this).html("收起");
	$($this).attr("onclick","closeReviews(this,'"+id+"')");
}
function closeReviews($this,id){//关闭回复入口
	$($this).attr("onclick","openReviews(this,'"+id+"')");
	$($this).html("回复");
	var n = id.lastIndexOf("t");
	var str_a = id.substr(0, n+1);
	var str_b = id.substr(n+1,id.length);
	if(str_a=='reviews_content'){//若是外围点击
		$("#con_bottom_reviews").css("display","none");
	}else{
		$("#con_bottom_reviews").css("display","none");
	}
	
}
function getReviews($this){
	$($this).html("loading.....");
	$.ajax({
		url : '/SSH_test/userAction_getReviews',
		type : "POST",
		async : false,
		cache : false,
		success : function(){
			$("#Allreviews").css("display","block");
			$($this).css("display","none");
		},
	});
}