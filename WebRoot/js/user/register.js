function openRG(){
	$("#register_cover_div").css("display","block");
}
function closeRG(){
	$("#register_cover_div").css("display","none");
}
function checkformRG() {// 检查表单必填数据
	var flag = true;
/*	$("#form input[type='text'],input[type='password']").each(function() {
		if (this.value.match(/\S+/) == null) {// 若匹配不到非空白字符
			alert(this.name + "不能为空");
			this.focus();
			flag = false;
			return false;// 跳出循环而不是返回值
		}
	});*/
	checkuid_RG();
	if ($("#uidwarnning").text() != "") {
		return false;
	}
	if (flag == true) {
		if (checkUserName()==false||checkpw() == false || checkemail() == false) {
			return false;
		}
	}

	return flag;
}

function checkuid_RG() {
	if ($("#RG_uid").val().match(/[a-zA-Z0-9_]{1,16}/) != null) {// 限制输入的用户ID只能有数字英文字符下划线组成的1到16位
		$.ajax({
			url : '/SSH_test/userAction_checkuid',
			type : "POST",
			data : {
				uid : $("#RG_uid").val()
			},
			dataType : "json",
			timeout : 1000,
			cache : false,
			/*
			 * beforeSend : function sendBefore() {// 发送之前执行的方法 alert("发送中..."); },
			 */
			success : function messageSuccess(data) {// 返回时的方法
				if (data.check_uid != undefined) {
					$("#uidwarnning").html(data.check_uid);
					$("#RG_uid").focus();
				} else {
					$("#uidwarnning").html("");
				}
			},

		})
	}else{
		$("#uidwarnning").html("用户ID不能为空！");
	}
}
function checkUserName(){
	if($("#username").val().match(/\S+/) == null){
		$("#unwarnning").html("用户名不能为空！！");
		return false;
	}else{
		$("#unwarnning").html("");
		return true;
	}
}

/*function checkname(){
	var str =/^[\u4E00-\u9FA5]{2,4}$/;//2个到4个中文
	if ($("#user.name").val() != "") {
		if (str.test($("#name").val()) == false) {
			alert("姓名填写有误");
			$("#user.name").focus();
			return false;
		}
	}
	return true;
}*/
function checkpw() {
	var r_ps = $("#r_password").val();
	var ps = $("#RG_password").val();
	if(ps.match(/\S+/) == null){
		$("#pswarnning").html("密码不能为空");
		return false;
	}else{
		$("#pswarnning").html("");
		if (r_ps != ps ) {
			$("#rpswarnning").html("两次密码不一样");
			return false;
		}else{
			
			$("#rpswarnning").html("");
			return true;
		}
	}
}
/*function checkICDN() {
	//18位身份证号码的校验
	//查询过前6位地址码是110000到659001
	//6位地址码+8位年月日+3位顺序码+1位数字校验码
	var str = /^[1-6][0-9]{5}(18|19|20)\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
	if ($("#user.IDCN").val() != "") {
		if (str.test($("#user.IDCN").val()) == false) {
			alert("身份证填写有误");
			$("#user.IDCN").focus();
			return false;
		}
	}
	return true;
}*/
/*function checktelnum() {
	var str = /^1[3|4|5|8][0-9]\d{8}/;
	if ($("#user.telnum").val() != "") {
		if (str.test($("#user.telnum").val()) == false) {
			alert("电话填写有误！");
			$("#user.telnum").focus();
			return false;
		}
	}
	return true;
}*/
function checkemail() {
	var email = $("#RG_email").val();
	if(email.match(/\S+/) != null){
		var str = /\w+[@]{1}\w+[.]\w+/;// 任意数字字符下划线+@+数字字符下划线+.+数字字符下划线
		if (str.test(email) == false) {
			$("#emailwarnning").html("邮箱填写有误！");
			$("#RG_email").focus();
			return false;
		}else{
			$("#emailwarnning").html("");
			return true;
		}
	}else{
		$("#emailwarnning").html("邮箱不能为空");
		return false;
	}
}
