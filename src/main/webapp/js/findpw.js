/** 
	비밀번호 초기화 시 이메일 유효값 검색 후 메일 전송 하는 기능
*/
const $sendMail = $("#sendMail");
const $email = $("#email");

const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

$sendMail.on("click", function() {
	if($email.val()==""){
		print("이메일을 입력해주세요.");
	}
	else if (!emailRegex.test($email.val())) {
		print("올바른 이메일을 입력해주세요.");
	} else {
		$.ajax({
			url: "./sendToken.do",
			data: { userEmail: $email.val()},
			type: "POST",
			success: function(result) {
				if(result=='true'){
					successprint("메일을 확인해주세요");
				}else{
					print("등록된 메일이 없습니다.");
				}
			}, error: function(error) {
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

function successprint(val) {
	Swal.fire({
		position: 'top-center',
		icon: 'success',
		title: val,
		showConfirmButton: false,
		timer: 1500,
		showCloseButton: true
	})
}