/**
 * 
 */
//图片预览
$(document).ready(function() {
	$("#image").change(function() {
		if (!checkimg()) {
			alert("图片格式不对啊！");
		} else {
			var path = getObjectURL(this.files[0]);
			$("#upload_img").attr("src",path);
		}
	});
	var result = $("#uploadResult").val();
	if(result!=null&&result!="[object HTMLInputElement]"&&result!=""){
		alert(result);
	}
});
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
//响应隐藏的文件上传文件按钮
function uploadf() {
	$("#upload").click();
}
function uploadi() {
	$("#image").click();
}
//文件格式检查
function check() {
	if (!checktitle()||!checkF()){
		return false;
	}else if(!checkdoc()) {
		alert("文档文件格式有误，请上传txt,doc,docx类型的文件！");
		return false;
	} else if (!checkimg()) {
		alert("图片文件格式有误，请上传jpg,jpeg类型的文件！");
		return false;
	} else {
		alert("loading...");
		return true;
	}

}
// 检查上传文件格式（后缀）
function checkdoc() {
	var path = $("#upload").val();
	var str = /[.](txt|doc|docx)$/;
	var res = str.test(path);
	return res;
}
// 检查上传图片的格式
function checkimg() {
	var path = $("#image").val();
	var str = /[.](jpg|jpeg)$/;
	var res = str.test(path);
	return res;
}
function checktitle(){
	var title = /\S+/;
	if(title.test($("#upload_title").val())==false){
		$("#title_F").attr('class',"upload_flag_false");
		$("#title_warning").html("标题不能为空") ;
		return false;
	}else{
		$("#title_warning").html("");
		$("#title_F").attr('class',"upload_flag_true");
		return true;
	}
}
function checkF(){
	if($("#upload").val()==""){
		$("#file_F").attr('class',"upload_flag_false");
		alert("上传的文件不能为空");
		return false;
	}else{
		$("#file_F").attr('class',"upload_flag_true");
		return true;
	}
}