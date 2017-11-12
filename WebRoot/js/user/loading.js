var num = 3;
function show(){
	$("#num").html(num);
	interval = setInterval("dec()", 1000);
}
function dec(){
	num--;
	$("#num").html(num);
	if(num<=0){
		clearInterval(interval);
		window.location.href=$("#path").val();
	}
}
