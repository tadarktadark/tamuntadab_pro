 /**
 	비밀번호 초기화 시 해당 비밀번호의 유효값을 검사하는 기능을 가진 js
  */
const $form = document.querySelector('form');
const $password = $("#userPassword");
const $passwordconfirm = $("#passwordconfirm");

const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

$form.addEventListener("submit", (event) => {
    // 비밀번호 확인
    if (!passwordRegex.test($password.val())) {
        print("올바른 비밀번호를 입력해주세요.");
        event.preventDefault();
        return;
    }

    // 비밀번호 확인
    if ($password.val() !== $passwordconfirm.val()) {
        print("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        event.preventDefault();
        return;
    }

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