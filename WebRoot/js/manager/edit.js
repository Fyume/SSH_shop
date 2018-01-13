/**
 * 
 */

function infoon(){
	$("#user_info").css("display","inline");
}
function infooff(){
	$("#user_info").css("display","none");
}
function alter_U(num){
	$.ajax({
		url : 'http://localhost:8080/SSH_test/managerAction_alter_U',
		type : "POST",
		timeout : 1000,
		data :{json:JSON.stringify(getJsonData1(num))},
		async:false,//取消异步请求
		dataType : "json",
		cache : false,
		success : function messageSuccess(data) {// 返回时的方法
			alert($("#u_permission"+num).val());
		},
	})
}
function alter_B(num){
	$.ajax({
		url : 'http://localhost:8080/SSH_test/managerAction_alter_B',
		type : "POST",
		timeout : 1000,
		data :{json:JSON.stringify(getJsonData2(num))},
		async:false,//取消异步请求
		dataType : "json",
		cache : false,
		success : function messageSuccess(data) {// 返回时的方法
			alert($("#u_permission"+num).val());
		},
	})
}
function getJsonData1(num){
	var json={
			uid:$("#uid"+num).val(),
			username:$("#username"+num).val(),
			u_permission:$("#u_permission"+num).val()
	}
	alert(JSON.stringify(json));
	return json;
}
function getJsonData2(num){
	var json={
			bid:$("#bid"+num).val(),
			bname:$("#bname"+num).val(),
			ISBN:$("#ISBN"+num).val(),
			publish:$("#publish"+num).val(),
			description:$("#description"+num).val(),
			type:$("#type"+num).val()
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
function page2(count){
	var page = $("#page").val();
	$("#a_"+page).css("color","red");
	$("#a_"+page).css("font-weight","700");
	for (var int = 0; int < count; int++) {
		if(int!=page){
			$("#a_"+int).css("color","black");
			$("#a_"+page).css("font-weight","0");
		}
	}
}