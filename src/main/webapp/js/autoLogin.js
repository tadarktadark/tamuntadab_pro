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