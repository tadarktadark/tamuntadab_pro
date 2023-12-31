/**
	구글 가입시 입력해야되는 값들의 유효성을 검사하는 기능
 */ 
const $form = document.querySelector('form');
const $name = $("#name");
const $confirmPhoneCheck = $("#confirmPhoneCheck");
const $gender = $("input:radio[name='userGender']");
const $birth = $("#birth");

const nameRegex = /^[A-Za-z가-힣]{2,}$/;
const birthRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;
const date = new Date();
const $today = date.getFullYear() +"-"+  ("0" + (1 + date.getMonth())).slice(-2) +"-"+ ("0" + date.getDate()).slice(-2);

$form.addEventListener("submit", (event) => {

    // 이름 확인
    if (!nameRegex.test($name.val())) {
        print("올바른 이름을 입력해주세요.");
        event.preventDefault();
        return;
    }


    // 핸드폰 인증 확인
    if (!$confirmPhoneCheck.is(':checked')) {
        print("핸드폰 인증을 완료해주세요.");
        event.preventDefault();
        return;
    }

    // 성별 확인
    if ($gender.filter(':checked').length === 0) {
        print("성별을 선택해주세요.");
        event.preventDefault();
        return;
    }

    // 생년월일 확인
    if (!birthRegex.test($birth.val())||($birth.val() >= $today)) {
        print("올바른 생년월일을 입력해주세요.");
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