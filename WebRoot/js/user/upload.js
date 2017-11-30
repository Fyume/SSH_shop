/**
 * 
 */
$(document).ready(function() {
	$("#image").change(function() {
		if (!checkimg()) {
			alert("图片格式不对啊！");
		} else {
			var path = getObjectURL(this.files[0]);
			$("#upload_img").attr("src",path);
		}
	});
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

function uploadf() {
	$("#upload").click();
}
function uploadi() {
	$("#image").click();
}
function check() {
	if (!checkdoc()) {
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