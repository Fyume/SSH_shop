/**
 * 
 */
	$(document).ready(
			$.ajax({
				url : 'http://localhost:8080/SSH_test/bookAction_getData',
				type : "POST",
				timeout : 1000,
				cache : false,
			})
	)
function infoon(){
	$("#user_info").css("display","inline");
}
function infooff(){
	$("#user_info").css("display","none");
}
function alter(num){
	$.ajax({
		url : 'http://localhost:8080/SSH_test/managerAction_alter',
		type : "POST",
		timeout : 1000,
		data :{json:JSON.stringify(getJsonData(num))},
		async:false,//取消异步请求
		dataType : "json",
		cache : false,
		success : function messageSuccess(data) {// 返回时的方法
			alert($("#u_permission"+num).val());
		},
	})
}
function getJsonData(num){
	var json={
			uid:$("#uid"+num).val(),
			username:$("#username"+num).val(),
			u_permission:$("#u_permission"+num).val()
	}
	alert(JSON.stringify(json));
	return json;
}
function page(){
	var page = $("#page").val();
	$("#a_"+page).css("color","red");
	for (var int = 0; int < array.length; int++) {
		if(int!=page){
			$("#a_"+int).css("color","black");
		}
	}
}