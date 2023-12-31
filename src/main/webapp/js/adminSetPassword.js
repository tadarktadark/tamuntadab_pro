/** 
	비밀번호 변경시 유효값을 검사하고 유효된 값일 경우 submit을 동작시키는 js
*/

const $adminPW = $("#adminPW");
const $adminPWConfirm = $("#adminPWConfirm");
const $passwordCheck = $("#passwordCheck");
const $confirmPasswordCheck = $("#confirmPasswordCheck");
const $passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

const $form = $("#loginForm");

$(document).ready(function(){
	//비밀번호 변경시 이벤트
	$adminPW.on("change keyup paste", function(){
    	if (!$passwordRegex.test($adminPW.val())) {
		$passwordCheck.removeClass('invisible');
    	}else{
		$passwordCheck.addClass('invisible');
		}
	});
	//비밀번호 확인 변경시 이벤트
	$adminPWConfirm.on("change keyup paste", function(){
       	if($adminPW.val() !== $adminPWConfirm.val()) {
		$confirmPasswordCheck.removeClass('invisible');
		}else{
		$confirmPasswordCheck.addClass('invisible');
		}
	})
	
	$form.on("submit",function(event){
  	 	if(!(!($adminPW.val() !== $adminPWConfirm.val()) && ($passwordRegex.test($adminPW.val())))) {
			event.preventDefault();
		}
	});
});