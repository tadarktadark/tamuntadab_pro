const $logoutbtn = $("#logout");
$logoutbtn.on("click",function(){
	localStorage.removeItem("autoLoginToken");
});
