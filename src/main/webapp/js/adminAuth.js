/**
	해당 관리자의 권한을 비교하여 접속을 차단하는 기능
 */
function check(event, adminAuth, ableAuth1, ableAuth2, ableAuth3, ableAuth4) {
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
