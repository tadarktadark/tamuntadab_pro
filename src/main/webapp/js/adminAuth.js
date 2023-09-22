function check(adminAuth, ableAuth1, ableAuth2, ableAuth3, ableAuth4, event) {
	if (adminAuth == ("T") || adminAuth == (ableAuth1) ||
		adminAuth == (ableAuth2) || adminAuth == (ableAuth3) ||
		adminAuth == (ableAuth4)) {
		return true;
	} else {
		Swal.fire({
			position: 'top-center',
			icon: 'warning',
			title: '권한이 없습니다.',
			showConfirmButton: false,
			timer: 1500,
			showCloseButton: true
		}).then(() => {
			location.href = "./adminMain.do"
			return false;
		});
	}
}
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