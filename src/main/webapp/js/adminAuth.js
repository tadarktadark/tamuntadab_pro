function check(event, adminAuth, ableAuth1, ableAuth2, ableAuth3, ableAuth4) {
	console.log(adminAuth);
	console.log(ableAuth1);
	console.log(ableAuth2);
	console.log(ableAuth3);
	console.log(ableAuth4);
	console.log(event);
    if (adminAuth == "T" || adminAuth == ableAuth1 ||
        adminAuth == ableAuth2 || adminAuth == ableAuth3 ||
        adminAuth == ableAuth4) {
        return true;
    } else {
        Swal.fire({
            position: 'top-center',
            icon: 'warning',
            title: '권한이 없습니다.',
            showConfirmButton: true,
            showCloseButton: true
        })
        event.preventDefault(); // 링크 이동을 막습니다.
    }
}
