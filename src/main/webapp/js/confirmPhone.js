/* �� �� */
function confirmPhone(){
	console.log()
        var phoneNumber = document.getElementById("PhoneNumber").value;
        // ���Խ��� ����Ͽ� ���� üũ
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
							'<h4 class="fs-20 fw-semibold">������ȣ�� �Է����ּ���</h4>' +
							'</div>' +
							'</div>',
						input: 'text',
						timer: 30000,
						timerProgressBar: true,
						customClass: {
							confirmButton: 'btn btn-primary w-xs mb-2',
						},
						confirmButtonText: '���� <i class="ri-arrow-right-line ms-1 align-bottom"></i>',
						buttonsStyling: false,
						showCloseButton: true,
						preConfirm: function(inputcode) {
						var checkbox= document.getElementById("confirmPhoneCheck");
						var checkboxLabel= document.getElementById("confirmPhone");
						checkbox.checked=true;
						checkbox.removeAttribute('hidden');
						checkboxLabel.innerText='������ �Ϸ�Ǿ����ϴ�.';
							if (inputcode == result.code) {
								Swal.fire({
									icon: 'info',
									title: '������ �����Ͽ����ϴ�!',
									showConfirmButton: true,
									timer: 1500,
									showCloseButton: false
								})
							} else {
								Swal.fire({
									icon: 'warning',
									title: '������ �����Ͽ����ϴ�.',
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
					title: '������ȣ ��û�� �����Ͽ����ϴ�..',
					customClass: {
						confirmButton: 'btn btn-danger w-xs',
					},
					buttonsStyling: false,
					html:'�����ڿ��� �������ּ���'
					})
					document.getElementById("PhoneNumber").value="";
				}
			},
			error:function(){
				Swal.fire({
					icon: 'error',
					title: '�߸��� ��û�Դϴ�.',
					customClass: {
						confirmButton: 'btn btn-danger w-xs',
					},
					buttonsStyling: false,
					html:'�߸��� ��û�Դϴ�.'
				})
			}
		})
	}
function failalert(){
	Swal.fire({
		icon: 'error',
		title: '�߸��� ������ �Է°� �Դϴ�.',
		customClass: {
			confirmButton: 'btn btn-primary w-xs',
		},
		buttonsStyling: false,
		html:'��ȭ��ȣ�� - �� �����ϰ� �Է����ּ���.'
	})
}

function writenum(){
	Swal.fire({
		icon: 'error',
		title: '�Է� �� �������ּ���.',
		customClass: {
			confirmButton: 'btn btn-danger w-xs',
		},
		buttonsStyling: false,
		html:'��ȭ��ȣ�� - �� �����ϰ� �Է����ּ���.'
	})
}