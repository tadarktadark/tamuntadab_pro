function confirmPhone(){
	console.log()
        var phoneNumber = document.getElementById("PhoneNumber").value;
        // 정규식을 사용하여 패턴 체크
        var pattern = /^01[016789]\d{7,8}$/;

		if(phoneNumber ==""){
			writenum();
		}else{
			
        if(pattern.test(phoneNumber)) {
            isc = true;
        } else {
            isc = false;
        }

        if(isc==true){
			confirmSMS(phoneNumber);
		}else{
			failalert();
			document.getElementById("PhoneNumber").value="";
		}
	}
}

function confirmSMS(phoneNumber){
	$.ajax({
			url: "./sendSMS.do",
			data: {phoneNumber:phoneNumber},
			type: "POST",
			dataType: "json",
			success: function(result) {
				if(result.isc == "true"){
					Swal.fire({
						html: '<div class="mt-3">' +
							'<div class="avatar-lg mx-auto">' +
							'<div class="avatar-title bg-light text-success display-5 rounded-circle">' +
							'<i class="ri-mail-send-fill"></i>' +
							'</div>' +
							'</div>' +
							'<div class="mt-4 pt-2 fs-15">' +
							'<h4 class="fs-20 fw-semibold">인증번호를 입력해주세요</h4>' +
							'</div>' +
							'</div>',
						input: 'text',
						timer: 30000,
						timerProgressBar: true,
						customClass: {
							confirmButton: 'btn btn-primary w-xs mb-2',
						},
						confirmButtonText: '인증 <i class="ri-arrow-right-line ms-1 align-bottom"></i>',
						buttonsStyling: false,
						showCloseButton: true,
						preConfirm: function(inputcode) {
						var checkbox= document.getElementById("confirmPhoneCheck");
						var checkboxLabel= document.getElementById("confirmPhone");
						checkbox.checked=true;
						checkbox.removeAttribute('hidden');
						checkboxLabel.innerText='인증이 완료되었습니다.';
							if (inputcode == result.code) {
								Swal.fire({
									icon: 'info',
									title: '인증에 성공하였습니다!',
									showConfirmButton: true,
									timer: 1500,
									showCloseButton: false
								})
							} else {
								Swal.fire({
									icon: 'warning',
									title: '인증에 실패하였습니다.',
									showConfirmButton: false,
									timer: 1500,
									showCloseButton: true
								})
								document.getElementById("PhoneNumber").value="";
							}
						}
					})
				}else if(result.isc=="false"){
					Swal.fire({
					icon: 'error',
					title: '인증번호 요청을 실패하였습니다..',
					customClass: {
						confirmButton: 'btn btn-danger w-xs',
					},
					buttonsStyling: false,
					html:'관리자에게 문의해주세요'
					})
					document.getElementById("PhoneNumber").value="";
				}
			},
			error:function(){
				Swal.fire({
					icon: 'error',
					title: '잘못된 요청입니다.',
					customClass: {
						confirmButton: 'btn btn-danger w-xs',
					},
					buttonsStyling: false,
					html:'잘못된 요청입니다.'
				})
			}
		})
	}
function failalert(){
	Swal.fire({
		icon: 'error',
		title: '잘못된 형식의 입력값 입니다.',
		customClass: {
			confirmButton: 'btn btn-primary w-xs',
		},
		buttonsStyling: false,
		html:'전화번호는 - 를 제외하고 입력해주세요.'
	})
}

function writenum(){
	Swal.fire({
		icon: 'error',
		title: '입력 후 인증해주세요.',
		customClass: {
			confirmButton: 'btn btn-danger w-xs',
		},
		buttonsStyling: false,
		html:'전화번호는 - 를 제외하고 입력해주세요.'
	})
}