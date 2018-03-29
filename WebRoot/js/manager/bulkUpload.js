/**
 * 别忘了加正则判断
 */
$(document).ready(function(){
	$("#image").on("change",function(){
		var file = this.files;
		for(var i=0;i<file.length;i++){
			var path = getObjectURL(file[i]);
			createDiv(i,path);
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
}
function createDiv(num,src){//记得num存window（真累）
	var str = ['封面','书名','作者','类型','ISBN','出版时间','简述'];
	//外围div
	var book_div = $("<div></div>");
	book_div.attr("id","book_div"+num);
	book_div.attr("class","book_div");
	book_div.appendTo($("#bulkForm"));
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
			div_font_1.css("width","16%");
		}
		if(i==5||i==6){
			div_font_1.css("width","20%");
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
				var div =$("<div class='div_value_1'><input type='text' name='blist["+num+"].bname'></div>");
				div.appendTo($("#div_value"+num));//书名
				break;
			case 2:
				var div =$("<div class='div_value_1'><input type='text' name='blist["+num+"].author'></div>");
				div.appendTo($("#div_value"+num));//作者
				break;
			case 3:
				var div =$("<div class='div_value_1'><select id='type' name='blist["+num+"].type'><option value='网络小说' selected>网络小说</option><option value='文学作品'>文学作品</option><option value='社会科学'>社会科学</option></select></div>");
				div.appendTo($("#div_value"+num));//分类
				break;
			case 4:
				var div =$("<div class='div_value_1' style='width:16%;'><input type='text' name='blist["+num+"].ISBN'></div>");
				div.appendTo($("#div_value"+num));//ISBN
				break;
			case 5:
				var div =$("<div id='publish' class='div_value_1' style='width:20%;'><input type='text' name='ylist["+num+"]' placeholder='1990'>年<input type='text' name='mlist["+num+"]' placeholder='1'>月<input type='text' name='dlist["+num+"]' placeholder='1'>日</div>");
				div.appendTo($("#div_value"+num));//出版时间
				break;
			case 6:
				var div = $("<div class='div_value_1' style='width:20%;'><textarea name='blist["+num+"].description' placeholder='书本概述'></textarea></div>");
				div.appendTo($("#div_value"+num));//概述
				break;
			default:break;
		}
	}
}