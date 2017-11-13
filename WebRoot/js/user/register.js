/*function checkuid() {
	var uid = document.getElementById("uid").value;
	var cpath = document.getElementById("contextPath").value;
	if (uid != null && uid != "") {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera,
			// Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var message = xmlhttp.responseText;
				if(message!=" "){
					alert(message);
				}
			}

		}
		xmlhttp.open("POST", cpath + "/userAction_checkuid?uid=" + uid, true);
		xmlhttp.send();
		 xmlhttp.send({"uid":uid}); 
	}
}
 */function checkform() {// 检查表单必填数据
	/*
	 * var a = form.email.indexOf("@"); var dot = form.email.indexOf(".") if(a<1||dot-a<2){
	 * alert("邮箱不合法"); return false; } if(form.uid==""){ alert("用户ID不能为空！");
	 * form.username.focus(); return false; } if(form.username==""){
	 * alert("用户名不能为空！"); form.username.focus(); return false; }
	 * if(form.name==""){ alert("姓名不能为空！"); form.name.focus(); return false; }
	 * if(form.password==""){ alert("密码输入不能为空"); form.password.focus(); return
	 * false; } if(form.r_password==""){ alert("第二次密码输入不能为空");
	 * form.r_password.focus(); return false; }
	 * if(form.password!=""&&form.r_password!=""){ if(password!=r_password){
	 * alert("两次密码不一样！"); form.r_password.focus(); return false; } }
	 * if(form.ICDN==""){ alert("身份证号码不能为空"); form.ICDN.focus(); return false; }
	 * if(form.email==""){ alert("邮箱不能为空"); form.r_password.focus(); return
	 * false; } }
	 */
	/*	
	 $(document).ready(function(){ $("#uid").blur(function(){
	 if($("#uid").target.value == ""){ alert("用户ID不能为空"); } });
	 $("#username").blur(function(){ if($("#username").target.value == ""){
	 alert("用户名不能为空"); } }); $("#name").blur(function(){
	 if($("#name").target.value == ""){ alert("姓名不能为空"); } });
	 $("#password").blur(function(){ if($("#password").target.value == ""){
	 alert("密码不能为空"); } }); $("#r_password").blur(function(){
	 if($("#r_password").target.value == ""){ alert("第二次密码输入不能为空"); } });
	 $("#ICDN").blur(function(){ if($("#ICDN").target.value == ""){
	 alert("身份证号码不能为空"); } }); $("#email").blur(function(){
	 if($("#email").target.value == ""){ alert("邮箱不能为空"); } });
	 */
	var flag = true;
	$("#form input[type='text'],input[type='password']").each(function() {
		if(this.name!="地址"&&this.name!="电话"){
		if (this.value.match(/\S+/) == null) {//若匹配不到非空白字符
			alert(this.name + "不能为空");
			this.focus();
			flag = false;
			return false;
		}
		}
	});
	checkpw();
	checkemail();
	checkuid();
	return flag;
}
function checkpw(){
	if($("#password").val()!=$("#r_password").val()){
		alert("两次密码不一样！");
		return false;
	}
}
function checkuid() {
	if ($("#uid").val().match(/[a-zA-Z0-9_]{1,16}/) != null) {//限制输入的用户ID只能有数字英文字符下划线组成的1到16位
		var path = $("#contextPath").val();
		$.ajax({
			url : path + '/user/userAction_checkuid',
			type : "POST",
			data : {uid : $("#uid").val()},
			dataType : "json",
			timeout : 1000,
			cache : false,
			/*beforeSend : function sendBefore() {// 发送之前执行的方法
				alert("发送中...");
			},*/
			success : function messageSuccess(data) {// 返回时的方法
				if(data.check_uid!=undefined){
				$("#uidwarnning").html("*"+data.check_uid);
				$("#uid").focus();
				}else{
					$("#uidwarnning").html("*");
				}
			},
			
		})
	}
}
function checkemail(){
	var str = "/\w+[@]{1}\w+[.]\w+/";//任意数字字符下划线+@+数字字符下划线+.+数字字符下划线
	if(str.test(("#email").val())==false){
		return false;
	}
}
