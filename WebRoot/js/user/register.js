function checkform() {// 检查表单必填数据
	var flag = true;
	$("#form input[type='text'],input[type='password']").each(function() {
		if (this.name != "user.address" && this.name != "user.telnum") {
			if (this.value.match(/\S+/) == null) {// 若匹配不到非空白字符
				alert(this.name + "不能为空");
				this.focus();
				flag = false;
				return false;// 跳出循环而不是返回值
			}
		}
	});
	checkuid();
	if ($("#uidwarnning").text() != "*") {
		return false;
	}
	if (flag == true) {
		if (checkname() == false || checkpw() == false || checkemail() == false) {
			return false;
		}
	}

	return flag;
}

function checkuid() {
	if ($("#user.uid").val().match(/[a-zA-Z0-9_]{1,16}/) != null) {// 限制输入的用户ID只能有数字英文字符下划线组成的1到16位
		$.ajax({
			url : '/SSH_test/userAction_checkuid',
			type : "POST",
			data : {
				uid : $("#uid").val()
			},
			dataType : "json",
			timeout : 1000,
			cache : false,
			/*
			 * beforeSend : function sendBefore() {// 发送之前执行的方法 alert("发送中..."); },
			 */
			success : function messageSuccess(data) {// 返回时的方法
				if (data.check_uid != undefined) {
					$("#uidwarnning").html("*" + data.check_uid);
					$("#user.uid").focus();
				} else {
					$("#uidwarnning").html("*");
				}
			},

		})
	}
}
function checkname(){
	var str =/^[\u4E00-\u9FA5]{2,4}$/;//2个到4个中文
	if ($("#user.name").val() != "") {
		if (str.test($("#name").val()) == false) {
			alert("姓名填写有误");
			$("#user.name").focus();
			return false;
		}
	}
	return true;
}
function checkpw() {
	if ($("#user.password").val() != $("#r_password").val()) {
		alert("两次密码不一样！");
		return false;
	}
	return true;
}
function checkICDN() {
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
}
function checktelnum() {
	var str = /^1[3|4|5|8][0-9]\d{8}/;
	if ($("#user.telnum").val() != "") {
		if (str.test($("#user.telnum").val()) == false) {
			alert("电话填写有误！");
			$("#user.telnum").focus();
			return false;
		}
	}
	return true;
}
function checkemail() {
	var str = /\w+[@]{1}\w+[.]\w+/;// 任意数字字符下划线+@+数字字符下划线+.+数字字符下划线
	if ($("#user.email").val() != "") {
		if (str.test($("#user.email").val()) == false) {
			alert("邮箱填写有误！");
			$("#user.email").focus();
			return false;
		}
	}
	return true;
}
