/**
	해당 유저의 정지 여부를 판단하는 기능
 */
$.ajax({
	url: "./jeongji.do",
	dataType:'json',
	success: function(result) {
		if(result != null){
			if (result.status == 'true') {
				Swal.fire({
					position: 'top-center',
					icon: 'warning',
					title: result.date+'까지 정지된 계정입니다.',
					showConfirmButton: false,
					timer: 1500,
					showCloseButton: false
				}).then(() => {
					localStorage.removeItem("autoLoginToken");
					location.href = "./main.do";
					location.reload();
				});
			}
		}
	},
	error: function() {
	}
});