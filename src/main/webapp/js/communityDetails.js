$(document).on("click","#contentShow",function(){
	if($("#contentShow").attr('aria-expanded')=='true'){
		$("#contentShow i").removeClass("ri-arrow-down-circle-line").addClass("ri-arrow-up-circle-line");
		$("#contentShow span").text(" 내용 숨기기");	
	} else if($("#contentShow").attr('aria-expanded')=='false'){
		$("#contentShow i").removeClass("ri-arrow-up-circle-line").addClass("ri-arrow-down-circle-line");
		$("#contentShow span").text(" 신고되어 처리중인 게시글입니다. 내용을 확인하시려면 클릭해주세요.");
	}
});

$(document).on("click", "#pdf-btn", function(){
	$.ajax({
    	url: "./pilgiPdfDownload.do",
        method: "post",
        data: {
			"id":$("#boardId").text(),
			"content":$("#ck-content").prop("outerHTML")
		}
    });
})
