$(function(){
	ajaxClass(1,1);
});

function ajaxClass(ppage, epage){
	$.ajax({
		url: "./myPageClass.do",
		type: "post",
		async: true,
		data: {
			"ppage": ppage,
			"epage": epage
		},
		dataType: "JSON",
		success: function(resp) {
			console.log(resp);
			var pClass = $("#pClassTbody");
			pClass.empty();
			var eClsss = $("#eClassTbody");
			eClsss.empty;
		},
		error:{
			
		}
	});
}