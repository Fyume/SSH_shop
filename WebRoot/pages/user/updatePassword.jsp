<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div onmouseover="u_checkPs()" style="width:95%;height:100%;">
	<div class="User_title">修改密码</div>
	<div class="arrow_div" style="border-top-left-radius:25px;"></div>
	<div style="width:100%;padding-top:10px;text-align:center;">
		<form id="PsForm">
			<span class="User_font">旧密码：</span>
			<span class="User_value"><input  style="border:1px #c0c0c0 solid;" id="ps1" name="user.password" type="password" value=""onblur="u_checkPs()"></span><span id="U_flag1" class="U_flag"></span>
			<br><br>
			<span class="User_font">新密码：</span>
			<span class="User_value"><input  style="border:1px #c0c0c0 solid;" id="ps2" name="NewPassword" type="password" value=""onblur="u_checkPs()"></span><span id="U_flag2" class="U_flag"></span>
			<br><br>
			<span class="User_font">确认密码：</span>
			<span class="User_value"><input  style="border:1px #c0c0c0 solid;" id="ps3" type="password" value=""onblur="u_checkPs()"></span><span id="U_flag3" class="U_flag"></span>
			<br><br>
			<input class="btn btn-info" style="width:100px;" type="button" onclick="submitPs()" value="确认修改">
		</form>
	</div>
</div>