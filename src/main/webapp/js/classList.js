//페이지 로드 되자마자 
$(function() {
	var page=1;
	ajaxPaging(page);
});

function ajaxPaging(page) {
	$.ajax({
		url: "./classListLoad.do",
		type: "get",
		async: true,
		data: {
			"page": page
		},
		dataType: "JSON",
		success: function(resp) {
			console.log(resp.pVo);      // 페이지 정보에 접근
			console.log(resp.classList); // 클래스 리스트에 접근

			var normalClass = $("#normalClass");
			var classList = resp.classList; // 서버에서 받은 클래스 리스트
			normalClass.empty();
			for (var i = 0; i < classList.length; i++) {
				
				norma
				
			}
		},
		error: function() {
			alert("통신에 실패하였습니다");
		}
	});
}

