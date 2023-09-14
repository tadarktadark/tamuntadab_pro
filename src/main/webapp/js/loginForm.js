//로그인 체크 기능
const $form = document.querySelector('form');
const $email = $("#email");
const $password = $("#password");
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

$form.addEventListener("submit",(event) =>{
	
    if (!emailRegex.test($email.val())) {
        print("올바른 이메일을 입력해주세요.");
        event.preventDefault();
        return;
    }
    if (!passwordRegex.test($password.val())) {
        print("올바른 비밀번호를 입력해주세요.");
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