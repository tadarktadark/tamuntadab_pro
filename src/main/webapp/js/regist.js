/**
	회원가입 시 유효값 검사 및 해당 비밀번호를 text로 바꿔주는 기능을 가진 js
 */
const $form = document.querySelector('form');
const $email = $("#confirmEmailCheck");
const $name = $("#name");
const $password = $("#password");
const $passwordconfirm = $("#passwordconfirm");
const $confirmPhoneCheck = $("#confirmPhoneCheck");
const $gender = $("input:radio[name='userGender']");
const $auth = $("input:radio[name='userAuth']");
const $birth = $("#birth");
const $nameCheck = $("#nameCheck");
const $passwordCheck = $("#passwordCheck");
const $confirmPasswordCheck = $("#confirmPasswordCheck");

const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const nameRegex = /^[A-Za-z가-힣]{2,}$/;
const birthRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;
const date = new Date();
const $today = date.getFullYear() +"-"+  ("0" + (1 + date.getMonth())).slice(-2) +"-"+ ("0" + date.getDate()).slice(-2);

$(document).ready(function(){
	document.getElementById("birth").setAttribute("max", $today);
	
	//이름 변경시 이벤트
	$name.on("change keyup paste", function(){
      if(!nameRegex.test($name.val())) {
 	 	$nameCheck.removeClass('invisible');
  		}else{
		$nameCheck.addClass('invisible');
		}
	});
	
	//비밀번호 변경시 이벤트
	$password.on("change keyup paste", function(){
    	if (!passwordRegex.test($password.val())) {
		$passwordCheck.removeClass('invisible');
    	}else{
		$passwordCheck.addClass('invisible');
		}
	});
	
	//비밀번호 확인 변경시 이벤트
	$passwordconfirm.on("change keyup paste", function(){
       	if($password.val() !== $passwordconfirm.val()) {
		$confirmPasswordCheck.removeClass('invisible');
		}else{
		$confirmPasswordCheck.addClass('invisible');
		}
	})
});

$('#password-addon').on("click",function(){
	if($password[0].attributes.type.value=='password'){
		$password[0].attributes.type.value='text';	
	}else{
		$password[0].attributes.type.value='password';
	}
});
$('#confirm-password-addon').on("click",function(){
	if($passwordconfirm[0].attributes.type.value=='password'){
		$passwordconfirm[0].attributes.type.value='text';	
	}else{
		$passwordconfirm[0].attributes.type.value='password';
	}
});


$form.addEventListener("submit", (event) => {
    // 이메일 확인
    if (!$email.is(':checked')) {
        print("이메일 인증을 완료해주세요.");
        event.preventDefault();
        return;
    }

    // 이름 확인
    if (!nameRegex.test($name.val())) {
        print("올바른 이름을 입력해주세요.");
        event.preventDefault();
        return;
    }

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
    // 권한 확인
    if ($auth.filter(':checked').length === 0) {
        print("권한을 선택해주세요.");
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