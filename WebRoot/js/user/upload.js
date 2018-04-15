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
		url = window.webkitURL.createObjectURL(file);//blob:
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

/**********用户部分*********/
//文件格式检查
function check() {
	if (!checktitle()||!checkF()){
		return false;
	}else if(!checkdoc()) {
		alert("文档文件格式有误，请上传txt,doc,docx类型的文件！");
		return false;
	} else {
		alert("loading...");
		return true;
	}

}
/*************用户＆管理员上传作品部分****************/
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
// 检查标题格式
function checktitle(){
	var title = /^[a-zA-Z0-9\u4e00-\u9fa5 ]{1,20}$/;
	if(title.test($("#upload_title").val())==false){
		$("#title_F").attr('class',"upload_flag_false");
		$("#title_warning").html("标题输入有误") ;
		return false;
	}else{
		$("#title_warning").html("");
		$("#title_F").attr('class',"upload_flag_true");
		return true;
	}
}
// 检查上传文件是否为空
function checkF(){
	var txt = /.txt$/;
	var doc = /.doc$/;
	var docx = /.docx$/;
	if(!txt.test($("#upload").val())&&!doc.test($("#upload").val())&&!docx.test($("#upload").val())){
		$("#file_F").attr('class',"upload_flag_false");
		return false;
	}else{
		$("#file_F").attr('class',"upload_flag_true");
		return true;
	}
}

/*************管理员相较用户上传书本多出来(不同)的部分****************/
function check2() {
	if (!checktitle()||!checkF()||!checkdate()){
		return false;
	}else if(!checkdoc()) {
		alert("文档文件格式有误，请上传txt,doc,docx类型的文件！");
		return false;
	} else {
		alert("loading...");
		return true;
	}
}
function checkdate(){
	var year = /^\d{4}$/;
	var month = /^\d{1,2}$/;
	var day = /^\d{1,2}$/;
	var ISBN = /^[0-9A-Za-z]*$/;
	if(year.test($("#year").val())==false||month.test($("#month").val())==false||day.test($("#day").val())==false){
		alert("日期输入错误");
		return false;
	}else if(ISBN.test($("#ISBN").val())==false){
		alert("ISBN错误");
		return false;
	}else{
		return true;
	}
}