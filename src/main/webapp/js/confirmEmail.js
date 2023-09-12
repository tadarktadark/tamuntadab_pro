/* �� �� */
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
				'<h4 class="fs-20 fw-semibold">�̸����� �Է��� �ּ���</h4>' +
				'</div>' +
				'</div>',
			input: 'email',
			showCancelButton: true,
			confirmButtonText: '�ߺ�Ȯ��',
			cancelButtonText: '���',
			showLoaderOnConfirm: true,
			customClass: {
				confirmButton: 'btn btn-primary w-xs me-2',
				cancelButton: 'btn btn-danger w-xs',
			},
			buttonsStyling: false,
			showCloseButton: true,
			preConfirm: function(email) {
				$.ajax({
					url: "./searchEmail.do", // Ŭ���̾�Ʈ�� ��û�� ���� ������ URL �ּ�
					data: { userEmail: email, site: "T" },                // HTTP ��û�� �Բ� ������ ���� ������
					type: "POST",                             // HTTP ��û ���(GET, POST)
					dataType: "json",                        // �������� ������ �������� Ÿ��
					success: function(result) {
						if (result == true) {
							Swal.fire({
								icon: 'error',
								title: '����� �� ���� �̸����Դϴ�.',
								customClass: {
									confirmButton: 'btn btn-primary w-xs',
								},
								buttonsStyling: false,
								html: email + '�� ����� �� ���� �̸��� �Դϴ�.'
							})
						} else {
							Swal.fire({
								icon: 'success',
								title: '��� ������ �̸��� �Դϴ�.',
								customClass: {
									confirmButton: 'btn btn-primary w-xs',
								},
								buttonsStyling: false,
								html: email + '�� ����� �� �ִ� �̸��� �Դϴ�.',
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
function writeMail(email) {
	document.getElementById("email").value = email;
}
function confirmMail() {
	var inputemail =document.getElementById("email");
	var email = document.getElementById("email").value;
	console.log(email);
	if (email == "") {
		Swal.fire({
			position: 'top-center',
			icon: 'warning',
			title: '�̸����� �Է����ּ���',
			showConfirmButton: false,
			timer: 1500,
			showCloseButton: true
		})
	} else {
		$.ajax({
			url: "./sendMail.do",
			data: { userEmail: email },
			type: "POST",
			dataType: "json",
			success: function(result) {
				if (result.isc == "true") {
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
						timer: 300000,
						timerProgressBar: true,
						customClass: {
							confirmButton: 'btn btn-primary w-xs mb-2',
						},
						confirmButtonText: '���� <i class="ri-arrow-right-line ms-1 align-bottom"></i>',
						buttonsStyling: false,
						showCloseButton: true,
						preConfirm: function(inputcode) {
							if (inputcode == result.code) {
								var checkbox= document.getElementById("confirmEmailCheck");
								var checkboxLabel= document.getElementById("confirmEmail");
								checkbox.checked=true;
								checkbox.removeAttribute('hidden');
								checkboxLabel.innerText='������ �Ϸ�Ǿ����ϴ�.';
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
								inputemail.value="";
							}
						}
					})
				} else {
					Swal.fire({
						position: 'top-center',
						icon: 'warning',
						title: '������ȣ ������ �����Ͽ����ϴ�.',
						showConfirmButton: false,
						timer: 1500,
						showCloseButton: true
					})
				}
			},
			error: function(result) {
			}
		})
	}
}