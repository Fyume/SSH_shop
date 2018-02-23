<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function clickit() {
		if (confirm("确认上传？")) {
			window.location.href = " ${pageContext.request.contextPath }/test/testAction_show";
		}
	}
</script>
</head>

<body>
	<iframe id＝"il" name="iname" frameborder="0" src="http://www.bilibili.com"
		style="transform: scaleX(-1);" scrolling="no" width="100%" height="100%" class="mirror_ifrom"></iframe>
	<script type="text/javascript">
		function a() {
			$("iframe").addClass("mirror_ifrom");
			setTimeout(function() {
				$("iframe").attr("src", "http://www.bilibili.com")
			}, 1500)
		}
		function boom() {
			var pwd = $("#pwd").val();
			$.ajax({
				url : "/boom",
				type : "POST",
				data : {
					pwd : pwd
				},
			}).done(function(data) {
				if (data == "2233") {
					$(".boom").hide();
					window.localStorage.show == 1;
					a()
				} else {
					$("#pwd").val("")
				}
			}).fail(function() {
				console.log("error")
			}).always(function() {
				console.log("complete")
			})
		}
		$("#boom").click(function(event) {
			boom()
		});
		if (window.localStorage.show == 1) {
			$(".boom").hide();
			a()
		};
	</script>
</body>
</html>
