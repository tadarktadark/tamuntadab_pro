/**
	로그인 시도에 대한 상태 출력 및 로그인 시도에 대한 유효값을 검사하는 기능
 */
const $doLogin = $("#doLogin");
const $email = $("#email");
const $password = $("#password");
const $autoLoginCheckBox = $("#autoLoginCheckBox");

const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

$(document).ready(function() {
	 $("#password").on("keyup",function(key){
        if(key.keyCode==13) {
			$doLogin.click();
        }
    });
 	$("#email").on("keyup",function(key){
        if(key.keyCode==13) {
			$doLogin.click();
        }
    });
});


$doLogin.on("click", function() {
	if(!emailRegex.test($email.val())) {
		print("올바른 이메일을 입력해주세요.");
	}
	else if (!passwordRegex.test($password.val())) {
		print("올바른 비밀번호를 입력해주세요.");
	} else {
		$.ajax({
			url: "./login.do",
			data: { userEmail: $email.val(), site: "T", userPassword: $password.val() },
			type: "POST",
			dataType: "json",
			success: function(result) {
				if (result.status == 'success') {
					if ($autoLoginCheckBox.is(":checked")) {
					localStorage.setItem("autoLoginToken", result.userInfo.userAutoLoginToken);
					}
					location.href = document.referrer;
				}else if(result.status == 'human'){
					printstatus('휴면 정지된 계정입니다.', '이메일을 확인해주세요.');
					$email.val("");
					$password.val("");
				}else if(result.status == 'printDate'){
					printstatus('현재 잠금 상태입니다.', '잔여시간 : '+result.time+'분');
					$email.val("");
					$password.val("");
				} else if (result.status == 'noEmail') {
					printstatus('Error', '등록된 정보가 없습니다.');
					$email.val("");
					$password.val("");
				} else if (result.status == 'updateChadan'){
					console.log(result.chadanCnt);
					printstatus('틀린 비밀번호 입니다.','현재 누적 :'+parseInt(result.chadanCnt)+' / 남은 횟수 : '+parseInt(5-(parseInt(result.chadanCnt))));
					$email.val("");
					$password.val("");
				} else if (result.status == 'chadan') {
					printstatus('WARNING','현재 계정은 10분간 차단되었습니다.');
					$email.val("");
					$password.val("");
				}
			}, error: function(error) {
				console.log(error)
				alert("잘못된 접근입니다.");
			}
		})
	}
});

function print(val) {
	Swal.fire({
		position: 'top-center',
		icon: 'warning',
		title: val,
		showConfirmButton: false,
		timer: 1500,
		showCloseButton: true
	})
}

function printstatus(status,detail) {
		 Swal.fire({
            html: '<div class="mt-3">' +
                '<lord-icon src="https://cdn.lordicon.com/tdrtiskw.json" trigger="loop" colors="primary:#f06548,secondary:#f7b84b" style="width:120px;height:120px"></lord-icon>'+
                '<div class="mt-4 pt-2 fs-15 mx-5">' +
                '<h4>'+status+'</h4>' +
                '<p class="text-muted mx-4 mb-0">'+detail+'</p>' +
                '</div>' +
                '</div>',
            customClass: {
                confirmButton: 'btn btn-primary w-xs mt-2 me-2 mb-1',
                cancelButton: 'btn btn-danger w-xs mb-1',
            },
            timer:1500,
            timerProgressBar: true,
            buttonsStyling: false,
    });
}

