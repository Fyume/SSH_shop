$(document).ready(function(){
	$("#cont_showMenu").click();
});
function page2(count){
	var page = $("#page").val();
	$("#a_"+page).css("color","red");
	$("#a_"+page).css("font-weight","700");
	for (var int = 0; int < count; int++) {
		if(int!=page){
			$("#a_"+int).css("color","#0080c0");
			$("#a_"+page).css("font-weight","0");
		}
	}
}
function createPageNum(){//加入每一页的入口 单独提比较方便多次调用
	var count = window.count_r;
	$("#menu_page").html("");//清掉 多次调用就不用再写一遍了
	var p_page = Math.round(Math.round(count/100)/48);//书本页数在菜单中显示所需要的总页数   (每48个页数按钮一页)
	button(p_page);//调整按钮
	var M_page = parseInt($("#Menu_page").val());//菜单当前页数    默认为1
	/*alert("M_page:"+M_page+";p_page:"+p_page);*/
	if(M_page==p_page){//最后一页
		for(var i = ((M_page-1)*48+1);i<=Math.round(count/100);i++){
			/*var page = "<div class='page_div' onclick='history("+user+","+sg+","+(i*M_page)+")'></div>";*/
			var page = $('<div>'+i+'</div>');
			page.innerHTML=i;
			page.attr('id',i);
			page.attr("class","page_div");
			page.bind("click",{i:i},function(event){
				var user = window.user_r;
				var msg = window.msg_r;
				var count = window.count_r;
				var num = parseInt(event.data.i);
				if(user=="false"){
					var n = msg.indexOf(";");
					var font = msg.substring(0, n);
					var id = msg.substr(n+1, msg.length);
					var json = {
							font : font,
							id : id,
							page : num
					}
					$.ajax({
						url : '/SSH_test/userAction_history',
						type : "POST",
						dataType : 'json',
						data : {
							json : JSON.stringify(json)
						},
						async : false,
						cache : false,
						success : function(){
							window.location.href='/SSH_test/pages/user/read.jsp?page='+num;
						},
					});
				}else{
					window.location.href='/SSH_test/pages/user/read.jsp?page='+num;
				}
			});
			page.appendTo($("#menu_page"));
		}
	}else{//一般情况的展示
		for(var i =((M_page-1)*48+1);i<=(M_page*48);i++){
			/*var page = "<div class='page_div' onclick='history("+user+","+sg+","+(i*M_page)+")'></div>";*/
			var page = $('<div>'+i+'</div>');
			page.innerHTML=i;
			page.attr('id',i);
			page.attr("class","page_div");
			page.bind("click",{i:i},function(event){
				var user = window.user_r;
				var msg = window.msg_r;
				var count = window.count_r;
				var num = parseInt(event.data.i);
				if(user=="false"){
					var n = msg.indexOf(";");
					var font = msg.substring(0, n);
					var id = msg.substr(n+1, msg.length);
					var json = {
							font : font,
							id : id,
							page : num
					}
					$.ajax({
						url : '/SSH_test/userAction_history',
						type : "POST",
						dataType : 'json',
						data : {
							json : JSON.stringify(json)
						},
						async : false,
						cache : false,
						success : function(){
							window.location.href='/SSH_test/pages/user/read.jsp?page='+num;
						},
					});
				}else{
					window.location.href='/SSH_test/pages/user/read.jsp?page='+num;
				}
			});
			page.appendTo($("#menu_page"));
		}
	}
}

function button(p_page){//控制分页按钮
	var M_page = parseInt($("#Menu_page").val());//菜单当前页数    默认为1
	if(M_page==1){//上一页按钮不可用
		$("#menu_dec").attr("disabled","true");
		$("#menu_dec").attr("class","menu_bottom_btn2");
	}else{
		$("#menu_dec").removeAttr("disabled");
		$("#menu_dec").attr("class","menu_bottom_btn1");
	}
	if(M_page==p_page){//下一页按钮不可用
		$("#menu_add").attr("disabled","true");
		$("#menu_add").attr("class","menu_bottom_btn2");
	}else{
		$("#menu_add").removeAttr("disabled");
		$("#menu_add").attr("class","menu_bottom_btn1");
	}
}
$(function(){
	$("#content_div").bind("contextmenu", function(){//禁止div的右键默认操作（打开菜单）
	    return false;
	})
	$("#content_div").dblclick(function(){
		var page = parseInt($("#page").val());
		window.location.href='/SSH_test/pages/user/read.jsp?page='+(page+1);
	});
});
function showMenu(user,msg,count){//可能做得复杂了 想过用两个c:foreach生成各个div 然后各个div之间切换 不过都写了就不改了
	if($("#cont_menu").css("display")=="none"){
		$("#show_Flag").attr("class","glyphicon glyphicon-chevron-right");
		$("#cont_menu").css("display","block");
		window.user_r = user;//将变量存到window域方便跨操作
		window.msg_r = msg;
		window.count_r = count;
		createPageNum();
	}else{
		$("#menu_page").html("");//清掉 不然会叠加
		$("#cont_menu").css("display","none");
		$("#show_Flag").attr("class","glyphicon glyphicon-chevron-left");
	}
}
function addpage(){
	var M_page = parseInt($("#Menu_page").val())+1;//菜单当前页数    默认为1
	$("#Menu_page").val(M_page);
	createPageNum();
}
function decpage(){
	var M_page = parseInt($("#Menu_page").val())-1;//菜单当前页数    默认为1
	$("#Menu_page").val(M_page);
	createPageNum();
}