/**
 * 别忘了加正则判断
 */
$(document).ready(function(){
	var rs = $("#rs").html();
	if(rs!=''){
		var list = eval(rs);
		$(list).each(function(index) {
			var str = list[index];
			alert(str);
		});
	}
	$("#image").on("change",function(){
		var file = this.files;
		window.fileSize = file.length;
		if(file.length==0){
			$("#file_btn").css("cursor","default");
			$("#file_btn").css("opacity","0.5");
			$("#file_btn").removeAttr("onclick");
			$("input[type='submit']").css("opacity","0.5");
			$("input[type='submit']").css("cursor","default");
			$("input[type='submit']").attr("disabled","disabled");
		}else{
			$("input[type='submit']").css("opacity","1");
			$("input[type='submit']").css("cursor","pointer");
			$("input[type='submit']").removeAttr("disabled");
			$("#file_btn").css("cursor","pointer");
			$("#file_btn").css("opacity","1");
			$("#file_btn").attr("onclick","bulkFile()");
		}
		for(var i=0;i<file.length;i++){
			if (!checkBulkImg(file[i].name)) {
				alert("图片仅限jpg！");
			}else{
				var path = getObjectURL(file[i]);
				createDiv(i,path);
			}
		}
	});
	$("#file").on("change",function(){
		var file = this.files;
		for(var i=0;i<file.length;i++){
			var fileName = file[i].name;
			var n = fileName.indexOf(".");
			alert(fileName);
			var m = fileName.indexOf(";");
			var name = fileName.substr(m+1,n);//这个substr有毒
			$("#Bname"+i).val(name);
			if (!checkFile(file[i].name)) {
				alert("文本仅限doc,docx,txt！");
			}else{
				var div = $("<div class='div_value_1' id='File"+i+"'>"+fileName+"</div>");
				$("#File"+i).remove();
				div.appendTo($("#div_value"+i));//文件
			}
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
function bulkImage(){
	$("#image").click();
	$("#bu_totalBook").html("");//清空
	$("#image").val("");
}
function bulkFile(){
	$("#file").click();
}
function text_author(){
	$("#text_author").removeClass("hidden");
	$("#text_author").focus();
	$("#div_author").css("display","none");
}
function div_author(){
	$("#text_author").addClass("hidden");
	$("#div_author").css("display","block");
}
//批量修改分类
function changeType(num){
	var size = window.fileSize;
	var str ="网络小说";
	switch(num){
		case 1:str = "文学作品";break;
		case 2:str = "社会科学";break;
		default:break;
	}
	for(var i=0;i<size;i++){
		$("#type"+i).val(str);
	}
}
//批量修改作者
function changeAuthor(){
	var str = $("#text_author").val();
	var size = window.fileSize;
	for(var i=0;i<size;i++){
		$("#Author"+i).val(str);
	}
}
//检查图片格式
function checkBulkImg(filename){
	var str = /[.](jpg|jpeg)$/;
	var res = str.test(filename);
	return res;
}
//检查文档格式
function checkFile(filename){
	var str = /[.](txt|doc|docx)$/;
	var res = str.test(filename);
	return res;
}
function checkBulkForm(){
	var size = window.fileSize;
	var res = true;
	for(var i=0;i<size;i++){
		if(checkBname(i)==false||checkDate(i)==false||checkISBN(i)==false||checkAuthor(i)==false){
			res = false;
			break;//跳出循环
		}
	}
	return res;
}
//检查书名
function checkBname(num){
	var title = /^[a-zA-Z0-9\u4e00-\u9fa5- ()]{1,20}$/;
	var res = title.test($("#Bname"+num).val());
	if(res==false){
		alert("第"+(num+1)+"本书的书名有误！");
		$("#Bname"+num).focus();
	}
	return res;
}
//检查日期
function checkDate(num){
	var year = /^\d{4}$/;
	var month = /^\d{1,2}$/;
	var day = /^\d{1,2}$/;
	var res1 = year.test($("#Year"+num).val());
	var res2 = month.test($("#Month"+num).val());
	var res3 = day.test($("#Day"+num).val());
	if(res1==false||res2==false||res3==false){
		alert("第"+(num+1)+"个日期有误！！");
		$("#Year"+num).focus();
		return false;
	}
	return true;
}
function checkISBN(num){
	var ISBN = /^[0-9A-Za-z]*$/;
	var res = ISBN.test($("#ISBN"+num).val());
	if(res==false){
		alert("第"+(num+1)+"本书的ISBN有误");
		$("#ISBN"+num).focus();
	}
	return res;
}
function checkAuthor(num){
	var author = /^[a-zA-Z0-9\u4e00-\u9fa5 ]{1,8}$/;
	var res = author.test($("#Author"+num).val());
	if(res==false){
		alert("第"+(num+1)+"本书的作者名有误");
		$("#Author"+num).focus();
	}
	return res;
}
function createDiv(num,src){//记得num存window（真累）
	var str = ['封面','书名','作者','类型','ISBN','出版时间','简述','文件'];
	//外围div
	var book_div = $("<div></div>");
	book_div.attr("id","book_div"+num);
	book_div.attr("class","book_div");
	book_div.appendTo($("#bu_totalBook"));
	//上下行div
	var div_font = $("<div></div>");
	div_font.attr("id","div_font"+num);
	div_font.attr("class","div_font");
	div_font.appendTo($("#book_div"+num));
	var div_value = $("<div></div>");
	div_value.attr("id","div_value"+num);
	div_value.attr("class","div_value");
	div_value.appendTo($("#book_div"+num));
	
	//上div内div
	for(var i=0;i<str.length;i++){
		var div_font_1 = $("<div></div>");
		div_font_1.attr("class","div_font_1");
		div_font_1.html(str[i]);
		if(i==4){
			div_font_1.css("width","14%");
		}
		if(i==5||i==6){
			div_font_1.css("width","18%");
		}
		div_font_1.appendTo($("#div_font"+num));
	}
	//下div内div
	for(var i=0;i<str.length;i++){//真累
		switch(i){
			case 0:
				var div = $("<div class='div_value_1'><img alt='' src='"+src+"'></div>");
				div.appendTo($("#div_value"+num));//图片
				break;
			case 1:
				var div =$("<div class='div_value_1'><input id='Bname"+num+"' type='text' name='blist["+num+"].bname'></div>");
				div.appendTo($("#div_value"+num));//书名
				break;
			case 2:
				var div =$("<div class='div_value_1'><input id='Author"+num+"' type='text' name='blist["+num+"].author'></div>");
				div.appendTo($("#div_value"+num));//作者
				break;
			case 3:
				var div =$("<div class='div_value_1'><select id='type"+num+"' name='blist["+num+"].type'><option value='网络小说' selected>网络小说</option><option value='文学作品'>文学作品</option><option value='社会科学'>社会科学</option></select></div>");
				div.appendTo($("#div_value"+num));//分类
				break;
			case 4:
				var div =$("<div class='div_value_1' style='width:14%;'><input id='ISBN"+num+"' type='text' name='blist["+num+"].ISBN'></div>");
				div.appendTo($("#div_value"+num));//ISBN
				break;
			case 5:
				var div =$("<div id='publish' class='div_value_1' style='width:18%;'><input type='text' id='Year"+num+"' name='ylist["+num+"]' placeholder='1990'>年<input id='Month"+num+"' type='text' name='mlist["+num+"]' placeholder='1'>月<input id='Day"+num+"' type='text' name='dlist["+num+"]' placeholder='1'>日</div>");
				div.appendTo($("#div_value"+num));//出版时间
				break;
			case 6:
				var div = $("<div class='div_value_1' style='width:18%;'><textarea name='blist["+num+"].description' placeholder='书本概述'></textarea></div>");
				div.appendTo($("#div_value"+num));//概述
				break;
			default:break;
		}
	}
}