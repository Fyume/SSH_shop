/**
 * 
 */
function history(user,sg,num){
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = msg.substr(n+1, msg.length);
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