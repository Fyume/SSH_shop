$(document).ready(function(){//展示分页菜单
	$("#cont_showMenu").click();
	if($("#content").val()=="true"){
		window.location.href="/SSH_test/pages/user/book.jsp";
	}
});
$(function(){//阅读操作 双击下一页以及禁用左右键默认功能
	$("#content_div").bind({
		contextmenu:function(){//禁止div的右键默认操作（打开菜单）
	    return false;
	},selectstart:function(){
		return false;
		}}
	);
	$("#content_div").dblclick(function(){
		var page = parseInt($("#page").val());//书本的第几页
		var M_page = window.M_page_r;//当前菜单页面是第几页
		if(window.page_r!=null&&window.page_r>page){
			if(page==(M_page*48)){
				addpage();
				$("#"+(page+1)).click();
			}else{
				$("#"+(page+1)).click();
			}
		}else{
			alert("没有下一页啦！");
		}
	});
	$("#content_div").mousedown(function(e){
		if(3 == e.which){//1是左键 2是中键 3是右键
			var page = parseInt($("#page").val());
			var M_page = window.M_page_r;//当前菜单页面是第几页
			if(page>1){
				if(page==((M_page-1)*48+1)){
					decpage();
					$("#"+(page-1)).click();
				}else{
					$("#"+(page-1)).click();
				}
			}else{
				alert("到顶啦！");
			}
		}
	});
});
function showMenu(user,msg,count,pageNum){//展示分页菜单   可能做得复杂了 想过用两个c:foreach生成各个div 然后各个div之间切换 不过都写了就不改了
	if($("#cont_menu").css("display")=="none"){
		$("#show_Flag").attr("class","glyphicon glyphicon-chevron-right");
		$("#cont_menu").css("display","block");
		if(count!=""){//如果有读取到书本
			window.page_r = Math.ceil(count/100);
			window.p_page_r = Math.ceil(window.page_r/48);//书本页数在菜单中显示所需要的总页数   (每48个页数按钮一页 向上取整)
			window.pageNum_r = pageNum;//将当前页数放到window
			MenuPage();//将菜单当前页数存到window里面
			window.user_r = user;//将变量存到window域方便跨操作
			window.msg_r = msg;//需要提交到后台处理的数据字符串 类似"bid;bid的值"
			window.count_r = parseInt(count);//书本内容的行数也就是list的长度
			createPageNum();
		}
	}else{
		$("#menu_page").html("");//清掉 不然会叠加
		$("#cont_menu").css("display","none");
		$("#show_Flag").attr("class","glyphicon glyphicon-chevron-left");
	}
}
//根据当前页数（其实也就是url传过来的page属性的值）修改当前菜单页面的值
function MenuPage(){
	var pageNum = window.pageNum_r;
	if(pageNum!=null||pageNum>0){
		window.M_page_r = Math.ceil(pageNum/48);//往上取整
	}else{
		window.M_page_r = parseInt($("#Menu_page").val());//菜单当前页数    默认为1
	}
}
//加入每一页的入口 单独提出来比较方便多次调用
function createPageNum(){
	var count = window.count_r;
	$("#menu_page").html("");//清掉 多次调用就不用再写一遍了
	var p_page = window.p_page_r;//获取分页菜单的页数
	button();//调整按钮
	var M_page = window.M_page_r;//获取当前分页菜单的页数
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
					var id = parseInt(msg.substr(n+1, msg.length));
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
					var id = parseInt(msg.substr(n+1, msg.length));
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
	P_page();//修改当前页面在菜单中的样式
}
//控制分页按钮
function button(){
	var p_page = window.p_page_r;
	var M_page = window.M_page_r;//菜单当前页数    默认为1
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
//菜单中书本页面的样式修改
function P_page(){
	var pageNum = window.pageNum_r;
	$("#menu_page div").each(function(){
		var id = $(this).attr("id");
		if(id==pageNum){
			$(this).attr("class","page_div2");//修改样式
		}
	});
}
//菜单 下一页
function addpage(){
	window.M_page_r = window.M_page_r+1;//菜单当前页数    默认为1
	createPageNum();
}
//菜单 上一页
function decpage(){
	window.M_page_r = window.M_page_r-1;//菜单当前页数    默认为1
	createPageNum();
}
//添加收藏 仅适用于read.jsp 由于用的是全局变量
function addFavour(){
	var user = window.user_r;
	var msg = window.msg_r;
	var count = window.count_r;
	var num = parseInt(event.data.i);
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = parseInt(msg.substr(n+1, msg.length));
		var json = {
				font : font,
				id : id,
				where : "read"
		}
		$.ajax({
			url : '/SSH_test/userAction_addF',
			type : "POST",
			dataType : 'json',
			data : {
				json : JSON.stringify(json)
			},
			async : false,
			cache : false,
			success : function(){
				$("#font_favour").html("取消收藏");
				$("#font_favour").attr("class","glyphicon glyphicon-star");
			},
		});
	}else{//虽然有c:choose控制 万一session过期
		if(confirm("还没登录呢 要前往登录吗？")){
			window.location.href='/SSH_test/pages/user/login.jsp';
		}
	}
}
//取消收藏
function cancFavour(){
	var user = window.user_r;
	var msg = window.msg_r;
	var count = window.count_r;
	var num = parseInt(event.data.i);
	if(user=="false"){
		var n = msg.indexOf(";");
		var font = msg.substring(0, n);
		var id = parseInt(msg.substr(n+1, msg.length));
		var json = {
				font : font,
				id : id,
				where : "read"
		}
		$.ajax({
			url : '/SSH_test/userAction_delF',
			type : "POST",
			dataType : 'json',
			data : {
				json : JSON.stringify(json)
			},
			async : false,
			cache : false,
			success : function(){
				$("#font_favour").html("添加收藏");
				$("#font_favour").attr("class","glyphicon glyphicon-star-empty");
			},
		});
	}else{//虽然有c:choose控制 万一session过期
		if(confirm("还没登录呢 要前往登录吗？")){
			window.location.href='/SSH_test/pages/user/login.jsp';
		}
	}
}