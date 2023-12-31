/**
	관리자가 유저를 정지 시킬 때 swal을 통해 비동기식 진행을 위한 js
 */

function userjeongji(userId){
	Swal.fire({
				html: '<div class="mt-3">' +
					'<div class="avatar-lg mx-auto">' +
					'<div class="avatar-title bg-light text-danger display-5 rounded-circle">' +
					'<i class="ri-lock-2-fill"></i>' +
					'</div>' +
					'</div>' +
					'<div class="mt-4 pt-2 fs-15">' +
					'<h4 class="fs-20 fw-semibold">정지 일수를 입력해주세요</h4>' +
					'</div>' +
					'</div>',
				input: 'text',
				showCancelButton: true,
				confirmButtonText: '정지',
				cancelButtonText: '취소',
				showLoaderOnConfirm: true,
				customClass: {
					confirmButton: 'btn btn-primary w-xs me-2',
					cancelButton: 'btn btn-danger w-xs',
				},
				buttonsStyling: false,
				showCloseButton: true,
				preConfirm: function(day) {
					$.ajax({
						url: "./userJeongji.do", // 클라이언트가 요청을 보낼 서버의 URL 주소
						data: { "userId": userId, "jeongji_day":day, "state":'Y' },                // HTTP 요청과 함께 서버로 보낼 데이터
						type: "POST",                             // HTTP 요청 방식(GET, POST)
						dataType: "json",                        // 서버에서 보내줄 데이터의 타입
						success: function(result) {
							if(result == true){
								Swal.fire({
									icon: 'success',
									title: '정지 완료되었습니다.',
									customClass: {
										confirmButton: 'btn btn-primary w-xs',
									},
									buttonsStyling: false,
								})
							}else{
								Swal.fire({
									icon: 'error',
									title: '다시 시도해주세요.',
									customClass: {
										confirmButton: 'btn btn-primary w-xs',
									},
									buttonsStyling: false,
								})
							}
						},
						error: function() {
						}
					})
				},
				allowOutsideClick: false
			})
}