<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
 $(document).ready(function(){
		/* $("#file").on("change",function(){
			var file = this.files;
			for(var i=0;i<file.length;i++){
				alert(file[i].name);
			}
		}); */
		alert("111");
		$("#aaa").on("click",function(e){
			alert("222");
			e.stopPropagation();
		});
}); 
</script>

	<%-- <a href="${pageContext.request.contextPath }/pages/test/test.jsp">test</a> --%>
	<%-- <form action="${pageContext.request.contextPath }/bookAction_test" enctype="multipart/form-data" method="post">
		<input id="file" type="file" name="test" multiple="multiple">
		<input type="submit" value="111">
	</form> --%>
	<div id="aaa" style="width:1000px;height:800px;border:1px black solid;">
		<p>6666666666666<br>
		66666666666666666
		</p>
	</div>
