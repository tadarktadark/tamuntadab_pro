$updateprofile = $("#updateprofile");
$fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
$maxSize = 5 * 1024 * 1024; // 5MB

$(document).ready(function() {
  $updateprofile.on("change", function() {
    $file = $(this)[0].files[0];
    if (!($fileForm.test($file.name))) {
      print("이미지만 업로드 가능합니다.");
      $updateprofile.val("");
    } else if ($file.size > $maxSize) {
      print("파일 사이즈는 5MB까지만 가능합니다.");
      $updateprofile.val("");
    } else {
      var formData = new FormData();  // FormData 객체 생성
      formData.append('file', $file); // 파일을 FormData에 추가

      Swal.fire({
        html: '<div class="mt-3">' +
          '<div class="avatar-lg mx-auto">' +
          '<div class="avatar-title bg-light text-success display-5 rounded-circle">' +
          '<i class="ri-mail-send-fill"></i>' +
          '</div>' +
          '</div>' +
          '<div class="mt-4 pt-2 fs-15">' +
          '<h4 class="fs-20 fw-semibold">프로필 사진을 변경하시겠습니까?</h4>' +
          '</div>' +
          '</div>',
        showCancelButton: true,
        confirmButtonText: '사용',
        cancelButtonText: '취소',
        showLoaderOnConfirm: true,
        customClass: {
          confirmButton: 'btn btn-primary w-xs me-2',
          cancelButton: 'btn btn-danger w-xs',
        },
        buttonsStyling: false,
        showCloseButton: true,
        preConfirm: function() {
          return $.ajax({
            url: "./uploadImg.do",
            enctype: "multipart/form-data",
            type: "POST",
            data: formData,
            contentType: false, // 필수
            processData: false, // 필수
            success: function(suc) {
              console.log(suc);
            },
            error: function() {
              console.log("$$$$$$");
            }
          });
        },
        allowOutsideClick: false
      }).then((result) => {
        if (result.dismiss === Swal.DismissReason.cancel) {
          $updateprofile.val("");
        }
      });
    }
  });
})

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
