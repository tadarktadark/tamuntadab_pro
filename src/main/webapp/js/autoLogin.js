/**
	자동로그인 토큰 유무에 따라 자동로그인 동작 시키는 기능을 가진 js
 */

window.onload=function(){
	
if (localStorage.getItem("autoLoginToken") != null) {
	$.ajax({
		url: './autoLogin.do',
		data: { userAutoLoginToken: localStorage.getItem("autoLoginToken") },
		type: "POST",
		success: function(result) {
			if (result == 'true') {
				location.href = './main.do';
			} else {
				location.href = './loginForm.do';
			}
		},
		error: function() {
			alert('잘못된 접근입니다.');
			}
		});
	}
}
