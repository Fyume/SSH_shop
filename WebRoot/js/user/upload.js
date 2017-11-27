/**
 * 
 */
function uploadf(){
	$("#upload").click();
}
function uploadi(){
	$("#image").click();
}
function check(){
	var path1 = $("#upload").val();
	var path2 = $("#image").val();
	var str1 = /[.](txt|doc|docx)$/;
	var str2 = /[.](jpg|jpeg)$/;
	if(!str1.test(path1)){
		alert("文档文件格式有误，请上传txt,doc,docx类型的文件！");
		return false;
	}else if(!str2.test(path2)){
		alert("图片文件格式有误，请上传jpg,jpeg类型的文件！");
		return false;
	}else{
		return true;
	}
	
}