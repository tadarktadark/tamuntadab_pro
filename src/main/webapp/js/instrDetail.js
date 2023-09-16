$(document).ready(function() {
    $('a[data-bs-target="#qrModal"]').on('click', function(e) {
        e.preventDefault();
        $("#qrcode").empty(); // 기존 QR 코드 삭제
        $("#qrcode").qrcode({
            text: '${simpleVo.inprSiteMobile}', // URL 값을 가져와서 QR 코드 생성
            width: 128,
            height: 128
        });
    });
});