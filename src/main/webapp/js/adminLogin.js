$(document).ready(function() {
	const $form = $("#loginForm");
	const $adminId = $("#adminId");
	const $adminPW = $("#adminPW");

	$form.on("submit", function(event) {
		event.preventDefault();
		$.ajax({
			url: "./adminLogin.do",
			data: { adminId: $adminId.val(), adminPW:$adminPW.val() },
			type: "post",
			dataType: "json",
			success: function(result) {
				if (result.status == 'success') {
					location.href="./adminMain.do"
				} else if (result.status == 'setPW') {
						Swal.fire({
							position: 'top-center',
							icon: 'warning',
							title: '비밀번호를 설정해주세요',
							showConfirmButton: false,
							timer: 1500,
							showCloseButton: true
						}).then(() => {
							$adminId.val("");
							$adminPW.val("");
							location.href="./adminSetPassword.do"
						});
				} else if (result.status == 'fail') {
					print("잘못된 정보입니다.");
					$adminId.val("");
					$adminPW.val("");
				};
			}, error: function(error) {
				$adminId.val("");
				$adminPW.val("");
				print("잘못된 접근입니다.");
			}
		});
	});
});

function print(val){
		Swal.fire({
		position: 'top-center',
		icon: 'warning',
		title: val,
		showConfirmButton: false,
		timer: 1500,
		showCloseButton: true
	})
}
