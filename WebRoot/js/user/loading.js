var num = 3;
var path = $("#path");
function show(){
	$("#num").html(num);
	show = setInterval("dec()", 1000);
}
function dec(){
	num--;
	$("#num").html(num);
	if(num<=0){
		clearInterval(show);
		window.location.href=$("#path");
	}
}
