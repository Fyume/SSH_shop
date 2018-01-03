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
function classifyon(){
	$("#classify_st").css("display","inline");
}
function classifyoff(){
	$("#classify_st").css("display","none");
}

function classUl1on(){
	$("#class_ul1").css("display","inline");
}
function classUl1off(){
	$("#class_ul1").css("display","none");
}

function classUl2on(){
	$("#class_ul2").css("display","inline");
}
function classUl2off(){
	$("#class_ul2").css("display","none");
}

function classUl3on(){
	$("#class_ul3").css("display","inline");
}
function classUl3off(){
	$("#class_ul3").css("display","none");
}

function infoon(){
	$("#user_info").css("display","inline");
}
function infooff(){
	$("#user_info").css("display","none");
}