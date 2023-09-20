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

$(document).on("click", "#delete-btn",function(){
	if($("#boardId").text().charAt(0)=='P'){
		$("#sa-pilgi-delete").click();
	} else {
		$("#sa-delete").click();		
	}
})

$(document).on("click", "#update-btn",function(){
	location.href="./communityUpdateForm.do?id="+$("#boardId").text();
})

$(document).on("click","#sa-delete",function(){
	Swal.fire({
	    title: "삭제하시면 내용을 복구하실 수 없습니다.",
	    text: "삭제하시겠습니까?",
	    icon: "warning",
	    showCancelButton: true,
	    customClass: {
	        confirmButton: 'btn btn-danger w-xs mt-2 me-2',
	        cancelButton: 'btn btn-light w-xs mt-2',
	    },
	    confirmButtonText: "삭제",
	    cancelButtonText: "취소",
	    buttonsStyling: false,
	    showCloseButton: true
	}).then(function (result) {
	    if (result.value) {
	        location.href="./communityDelete.do?id="+$("#boardId").text();
	    }
	});
});

$(document).on("click","#sa-pilgi-delete",function(){
	Swal.fire({
	        html: '<div class="mt-3">' +
	            '<lord-icon src="https://cdn.lordicon.com/gsqxdxog.json" trigger="loop" colors="primary:#f7b84b,secondary:#f06548" style="width:100px;height:100px"></lord-icon>' +
	            '<div class="mt-4 pt-2 fs-15 mx-5">' +
	            '<h4>필기를 삭제하시겠습니까?</h4>' +
	            '<p class="text-muted mx-4 mb-0">[마이페이지]-[내 글 목록]에서<br>복원할 수 있습니다.</p>' +
	            '</div>' +
	            '</div>',
	        showCancelButton: true,
	        customClass: {
	            confirmButton: 'btn btn-danger w-xs mt-2 me-2',
	            cancelButton: 'btn btn-light w-xs mt-2',
	        },
	        confirmButtonText: '삭제',
	        cancelButtonText: "취소",
	        buttonsStyling: false,
	        showCloseButton: true
	    }).then(function (result) {
	    if (result.value) {
	        location.href="./communityDelete.do?id="+$("#boardId").text();
	    }
		})
});