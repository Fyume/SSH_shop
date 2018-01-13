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