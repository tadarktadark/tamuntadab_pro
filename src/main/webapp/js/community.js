$(document).on("click",".like-do",function(e){
	if($(".dropdown-header")[0]!=undefined){
		e.stopPropagation();
		$.ajax({
			type:"post",
			url:"./commynityLike.do",
			data:{
				"type":$(e.target).attr("src").split("_")[1].split(".")[0],
				"id": $(e.target).attr("id")
			},
			success: function(data){
				const src = e.target.getAttribute("src");
				const newSrc = src.substring(0, src.indexOf("_") + 1) + data.type + ".png";
				$(e.target).attr('src', newSrc);
				$(e.target).parent().next().find('span').text(data.count);
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});	
	} else {
		e.stopPropagation();
		$("#sa-warning").click();
	}
})

$(document).on("click","#sa-warning",function(){
	Swal.fire({
	    title: "로그인시 이용할 수 있습니다.",
	    text: "로그인 하시겠습니까?",
	    icon: "warning",
	    showCancelButton: true,
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2 me-2',
	        cancelButton: 'btn btn-danger w-xs mt-2',
	    },
	    confirmButtonText: "로그인",
	    cancelButtonText: "취소",
	    buttonsStyling: false,
	    showCloseButton: true
	}).then(function (result) {
	    if (result.value) {
	        location.href="./loginForm.do";
	    }
	});
});

$(document).on("click",".view-detail",function(e){
	location.href="./communityDetails.do?id="+$(e.target).closest('.view-detail').attr("id");
});

