$(document).ready(function(){
	$("#btn_getHistory").click();
	if($("#book").val()=="true"){
		window.location.href="/SSH_test/pages/index.jsp";
	}
});
function getHistory(user,msg){
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = parseInt(msg.substr(n+1, msg.length));
		var json = {
				font : font,
				id : id,
		}
		$.ajax({
			url : '/SSH_test/userAction_getHistory',
			type : "POST",
			dataType : 'json',
			data : {
				json : JSON.stringify(json)
			},
			async : false,
			cache : false,
			success : function(pageNum){
				if(pageNum!=0){
					$("#read_btn").val("继续阅读");
					$("#read_btn").attr("class","book_btn2");
					$("#read_btn").attr('onclick','read('+pageNum+')');
				}else{
					$("#read_btn").val("进入阅读");
					$("#read_btn").removeAttr("class");
					$("#read_btn").attr('onclick','read(1)');
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