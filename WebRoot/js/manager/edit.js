/**
 * 
 */

function infoon(){
	$("#user_info").css("display","inline");
}
function infooff(){
	$("#user_info").css("display","none");
}
function alter_U(num){
	$.ajax({
		url : 'http://localhost:8080/SSH_test/managerAction_alter_U',
		type : "POST",
		timeout : 1000,
		data :{json:JSON.stringify(getJsonData1(num))},
		async:false,//取消异步请求
		dataType : "json",
		cache : false,
		success : function messageSuccess(data) {// 返回时的方法
			alert($("#u_permission"+num).val());
		},
	})
}
function alter_B(num){
	$.ajax({
		url : 'http://localhost:8080/SSH_test/managerAction_alter_B',
		type : "POST",
		timeout : 1000,
		data :{json:JSON.stringify(getJsonData2(num))},
		async:false,//取消异步请求
		dataType : "json",
		cache : false,
		success : function messageSuccess(data) {// 返回时的方法
			alert($("#u_permission"+num).val());
		},
	})
}
function getJsonData1(num){
	var json={
			uid:$("#uid"+num).val(),
			username:$("#username"+num).val(),
			u_permission:$("#u_permission"+num).val()
	}
	return json;
}
function getJsonData2(num){
	var json={
			bid:$("#bid"+num).val(),
			bname:$("#bname"+num).val(),
			ISBN:$("#ISBN"+num).val(),
			publish:$("#publish"+num).val(),
			description:$("#description"+num).val(),
			type:$("#type"+num).val()
	}
	return json;
}
function page(){
	var page = $("#page").val();
	$("#a_"+page).css("color","red");
	for (var int = 0; int < array.length; int++) {
		if(int!=page){
			$("#a_"+int).css("color","black");
		}
	}
}
function page2(count){
	var page = $("#page").val();
	$("#a_"+page).css("color","red");
	$("#a_"+page).css("font-weight","700");
	for (var int = 0; int < count; int++) {
		if(int!=page){
			$("#a_"+int).css("color","black");
			$("#a_"+page).css("font-weight","0");
		}
	}
}
function alter_Img(ID,bid){
	window.Iid=ID;
	window.bid=bid;//全局方便操作
	var src = $("#"+ID)[0].src;//选择器选择img标签的结果是个数组
	$("#B-I").css("display","block");
	$("#B-I").css("background",'rgba(0, 0, 0, 0.5)');
	$("#B-img").css("opacity",1);
	$("#B-img").attr('src',src);
	adjust();
/*	alert($("#B-img").width()+";"+$("#B-img").height());*/
	$("#B-img").css('margin-left','20%');
}
function adjust(){//修改大图的比例
	var width=$("#B-img").width();
	var height=$("#B-img").height();
	if(width>0&&height>0){
		var ratio = 800/width;//大致比例(默认大图宽800px)
		height = height*ratio;
		$("#B-img").attr('width','800px');
		$("#B-img").attr('height',height+"px");
	}
}
function disapper(path){//恢复正常视图
	var str = $("#image")[0].value;
	if(str!=""){
		var point = str.indexOf(".");
		var len = str.length-point;
		/*alert("len:"+len);*/
		if(len>3&&len<6){
			var suffix = str.substring(point+1, str.length);
			/*alert("suffix:"+suffix);*/
			if(suffix=="jpg"||suffix=="jpeg"){
				if(confirm("确定修改封面吗？")){
					/*alert($("#image")[0].value);*/
					var bid = window.bid;
					if(bid!=null){
						var formData = new FormData($("#ImageForm")[0]);
						formData.append("bid", bid);//后台直接getparameter试过可以
						$.ajax({
							url:path+'/bookAction_updateI',
							type:'POST',
							data:formData,
							async: false,
							cache: false,
							contentType: false,
							processData:false,
							success:function (data){
							//刷新重新设置image的src(这里涉及到tomcat和eclipse的文件部署问题，已在server.xml加入相应配置，详情看浏览器收藏夹)
							/*	var image = $("#"+window.Iid);//获取修改的图片
								var src1 = image[0].src;
								var sign = src1.indexOf("\\");
								image[0].src=src1.substring(0, sign)+data;
								alert("数据："+data);
								alert("image[0].src:"+image[0].src);*/
								alert("修改成功");
								window.location.reload();
							},
							error:function (){
								alert("上传错误");
							}
						});
					}
				}
			}
		}
	}
	$("#image")[0].value="";//清空选择的文件 
	$("#B-I").css("display","none");
}
function checkForm(){
	$("#image").click();
}