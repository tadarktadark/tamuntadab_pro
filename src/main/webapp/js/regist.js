window.onload = function() {
	document.getElementById("email").addEventListener("click", function() {
		Swal.fire({
			html: '<div class="mt-3">' +
                '<div class="avatar-lg mx-auto">' +
                '<div class="avatar-title bg-light text-success display-5 rounded-circle">' +
                '<i class="ri-mail-send-fill"></i>' +
                '</div>' +
                '</div>' +
                '<div class="mt-4 pt-2 fs-15">' +
                '<h4 class="fs-20 fw-semibold">이메일을 입력해 주세요</h4>' +
                '</div>' +
                '</div>',
			input: 'email',
			showCancelButton: true,
			confirmButtonText: '중복확인',
			cancelButtonText: '취소',
			showLoaderOnConfirm: true,
			customClass: {
				confirmButton: 'btn btn-primary w-xs me-2',
				cancelButton: 'btn btn-danger w-xs',
			},
			buttonsStyling: false,
			showCloseButton: true,
			preConfirm: function(email) {
				$.ajax({
					url: "./searchEmail.do", // 클라이언트가 요청을 보낼 서버의 URL 주소
					data: { userEmail: email, site: "T" },                // HTTP 요청과 함께 서버로 보낼 데이터
					type: "POST",                             // HTTP 요청 방식(GET, POST)
					dataType: "json",                        // 서버에서 보내줄 데이터의 타입
					success: function(result) {
						if (result == true) {
							Swal.fire({
								icon: 'error',
								title: '사용할 수 없는 이메일입니다.',
								customClass: {
									confirmButton: 'btn btn-primary w-xs',
								},
								buttonsStyling: false,
								html: email + '은 사용할 수 없는 이메일 입니다.'
							})
						} else {
							Swal.fire({
								icon: 'success',
								title: '사용 가능한 이메일 입니다.',
								customClass: {
									confirmButton: 'btn btn-primary w-xs',
								},
								buttonsStyling: false,
								html: email + '은 사용할 수 있는 이메일 입니다.',
								preConfirm: function() {
									writeMail(email);
								}
							})
						}
					},
					error: function(result) {
					}
				})
			},
			allowOutsideClick: false
		})
	});
}
function writeMail(email){
	document.getElementById("email").value=email;
}
