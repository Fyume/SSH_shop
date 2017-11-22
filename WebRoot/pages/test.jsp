<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
function clickit(){
window.location.href=" ${pageContext.request.contextPath }/test/testAction_show";
}
</script>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/test/testAction_upload" method="post" enctype="multipart/form-data">
    	<input type="file" id="upload" name="upload">
    	<input type="submit" value="提交">
    </form>
     <input type="button" value="显示" onclick="clickit()">
     <c:forEach items="${sessionScope.list }" var="str">
     	${str }<br/>
     </c:forEach>
  </body>
</html>
