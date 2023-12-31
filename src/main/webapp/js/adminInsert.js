/**
	관리자 계정 추가 시 해당 입력 값에 대한 유효값 검사
 */

const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const nameRegex = /^[A-Za-z가-힣]{2,}$/;
const IDRegex = /^[a-zA-Z0-9_]{3,12}$/;
const ipRegex = /^[0-9.]{3,}$/;
var $addAdminID ;
var $addAdminName;
var $addAdminPW;
var $addAdminAuth;
var $addIPID;
var $addIP;

$(document).ready(function() {
	 $addAdminID = $(".addAdminID");
	 $addAdminName = $(".addAdminName");
	 $addAdminPW = $(".addAdminPW");
	 $addAdminAuth = $(".addAdminAuth");
	 $addIPID = $(".addIPID");
	 $addIP = $(".addIPIP");
	$(".addIPBtn").on("click",function(){
		var isc = addIp();
		return isc;
	});
	$(".addAdminBtn").on("click",function(){
		var isc = addAdmin();
		return isc;
	});
});

function addIp(){
	if(!IDRegex.test($addIPID.val())){
		print("ID는 영문숫자 3~10자리까지 입니다.");
		return false;
	}
	if(!ipRegex.test($addIP.val())){
		print("정확한 IP를 입력해주세요");
		return false;
	}
	return true;
}
function addAdmin(){
	
	if(!IDRegex.test($addAdminID.val())){
		print("ID는 영문숫자 3~10자리까지 입니다.");
		return false;
	}
	if(!nameRegex.test($addAdminName.val())){
		print("이름은 한글2자리 이상으로 입력할 수 있습니다.");
		return false;
	}
	if(!passwordRegex.test($addAdminPW.val())){
		print("영문 숫자 특수문자를 포함한 8자리 이상의 비밀번호를 입력해주세요");
		return false;
	}
	if($addAdminAuth.val()=='권한을 선택해주세요...'){
		print("선택된 권한이 없습니다.");
		return false;
	}
	return true;
}


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