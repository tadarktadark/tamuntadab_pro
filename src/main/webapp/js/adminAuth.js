//function check(adminAuth, ableAuth1, ableAuth2, ableAuth3, ableAuth4, event) {
//    if (adminAuth == "T" || adminAuth == ableAuth1 ||
//        adminAuth == ableAuth2 || adminAuth == ableAuth3 ||
//        adminAuth == ableAuth4) {
//        return true;
//    } else {
//        Swal.fire({
//            position: 'top-center',
//            icon: 'warning',
//            title: '권한이 없습니다.',
//            showConfirmButton: true,
//            showCloseButton: true
//        }).then((result) => {
//            if (result.isConfirmed) {
//                location.href = "./adminMain.do";
//            }
//        });
//        event.preventDefault(); // 링크 이동을 막습니다.
//        return false;
//    }
//}
