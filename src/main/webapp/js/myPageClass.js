$(function(){
	ajaxClass(1);
});

function ajaxClass(page){
	$.ajax({
		url: "./myPageClass.do",
		type: "post",
		async: true,
		data: {
			"page": page
		},
		dataType: "JSON",
		success: function(resp) {
			console.log(resp);
			$("#endTitle").append(resp);
		},error:{
			
		}
	});
}