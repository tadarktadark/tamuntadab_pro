//회원가입 form이동
$("#doRegist").on("click",function(){
	$chkisc = $("#check0").is(':checked');
	$chkisc1 = $("#check1").is(':checked');
	
	if($chkisc&&$chkisc1){
		location.href="./registForm.do";
	}else{
		Swal.fire({
			icon: 'info',
			title: '필수 동의를 모두 체크해 주세요.',
			customClass: {
				confirmButton: 'btn btn-primary w-xs',
			},
			buttonsStyling: false,
		})
	}
});

//네이버 가입 버튼
$("#naverRegist").on("click",function(){
});

//카카오 가입 버튼
$("#kakaoRegist").on("click",function(){
});

//구글 가입 버튼
$("#googleRegist").on("click",function(){
});

$(document).ready(function(){
	
});