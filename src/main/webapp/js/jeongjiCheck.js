$.ajax({
	url: "./jeongji.do",
	success: function(suc) {
		if (suc == 'true') {
			Swal.fire({
				position: 'top-center',
				icon: 'warning',
				title: '정지된 계정입니다.',
				showConfirmButton: false,
				timer: 1500,
				showCloseButton: false
			}).then(() => {
				location.href = "./";
			});
		}
	},
	error: function() {
	}
});