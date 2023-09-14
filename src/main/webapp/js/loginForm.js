//로그인 체크 기능
const $doLogin = $("#doLogin");
const $email = $("#email");
const $password = $("#password");
const $autoLoginCheckBox = $("#autoLoginCheckBox");

const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

$(document).ready(function() {
	if(localStorage.getItem("email")!=null){
	}
});


$doLogin.on("click",function(){
	if (!emailRegex.test($email.val())) {
        print("올바른 이메일을 입력해주세요.");
    }
    else if(!passwordRegex.test($password.val())) {
        print("올바른 비밀번호를 입력해주세요.");
    }else{
		$.ajax({
		url:"./login.do",
		data:{userEmail:$email.val(),site:"T",userPassword:$password.val()},
		type:"POST",
		dataType:"json",
		success: function(result){
			if(result.status==success){
				location.href="./";
			}
//			if($autoLoginCheckBox.is(":checked")){
//			}
		},error:function(){
			alert("잘못된 접근입니다.");			
		}
	})		
	}
})
	
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

