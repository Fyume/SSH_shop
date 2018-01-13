/**
 * 
 */
function getData(user){
	var row = $("#row").val();
	if(!user){
		
	}
	$("#row"+row).css("background-color","#ffff80");
	for (var int = 0; int < array.length; int++) {
		if(int!=row){
			$("#row"+int).css("background-color","white");
		}
	}
}