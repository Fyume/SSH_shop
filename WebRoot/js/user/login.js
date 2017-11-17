/**
 * 
 */
function changeimg(){
/*	alert($("#vCode")[0].src);
 * 
*/	var imgpath = $("#vCode")[0].src;
	$("#vCode").attr('src',imgpath + "?" + new Date().getTime());
}
function checkform(){
	var flag=true;
	$("#form input[type='text']").each(function() {
		if(this.value.match(/\S+/) == null){
			flag=false;
			alert(this.name+"不能为空");
			this.focue();
			return false;
		}
	});
	return flag;
}