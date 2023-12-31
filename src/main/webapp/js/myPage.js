/**
	myPage에서 동작하는 닉네임, 프로필 변경, 계정 삭제에 대한 상태를 알려주는 js
 */
$updateprofile = $("#updateprofile");
$updateNickName = $("#updateNickName");
$delCommUser = $("#delCommUser");
$fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
$nickname = $("#nickname");
$nicknameRegex = /^[A-Za-z가-힣0-9]{2,6}$/;
$maxSize = 5 * 1024 * 1024; // 5MB

$(document).ready(function() {
	
	$delCommUser.on("click",function(event){
		event.preventDefault();
      Swal.fire({
        html: '<div class="mt-3">' +
                '<lord-icon src="https://cdn.lordicon.com/gsqxdxog.json" trigger="loop" colors="primary:#f7b84b,secondary:#f06548" style="width:100px;height:100px"></lord-icon>' +
                '<div class="mt-4 pt-2 fs-15 mx-5">' +
                '<h4>Are you Sure ?</h4>' +
                '<p class="text-muted mx-4 mb-0">진짜 삭제 하시겠습니까? </p>' +
                '</div>' +
                '</div>',
        showCancelButton: true,
        confirmButtonText: '삭제',
        cancelButtonText: '취소',
        showLoaderOnConfirm: true,
        customClass: {
          confirmButton: 'btn btn-danger w-xs me-2 bg-opacity-50',
          cancelButton: 'btn btn-success w-xs bg-opacity-50',
        },
        buttonsStyling: false,
        showCloseButton: true,
        preConfirm: function() {
           $.ajax({
            url: "./deleteUser.do",
            success: function(suc) {
              if(suc=='true'){
					Swal.fire({
					    position: 'top-center',
					    icon: 'success',
					    title: '삭제 완료되었습니다.',
					    timer: 2000,
					    allowOutsideClick: false
					 }).then(() => {
					   	 location.href="./main.do"; 
						});
				}else if(suc=='payment'){
					print("미완료된 결제 혹은 정산건이 있습니다. 확인해주세요.")
				}
				else{
					print("삭제 실패하였습니다. 관리자에게 문의해주세요")
				}
            },
            error: function() {
              print("관리자에게 문의 하세요");
            }
          });
        },
        allowOutsideClick: false
      }).then((result) => {
        if (result.dismiss === Swal.DismissReason.cancel) {
			print("취소 되었습니다");
        }
      });
	});
	
	
	
  $updateNickName.on("click", function(){
	if ($nicknameRegex.test($nickname.val()) == false) {
		print("닉네임은 2글자 부터 6글자까지 입니다.");
		return;
	}
   $.ajax({
        url: "./updateNickname.do",
        type: "POST",
        data: {nickname:$nickname.val()},
        success: function(suc) {
          if(suc=='true'){
				location.href="./mypage.do";
			}else{
				print("중복된 닉네임이 있습니다.")
			}
        },
        error: function() {
          print("관리자에게 문의 하세요");
        }
      });
	})
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
           $.ajax({
            url: "./uploadImg.do",
            enctype: "multipart/form-data",
            type: "POST",
            data: formData,
            contentType: false, 
            processData: false, 
            success: function(suc) {
              if(suc=='true'){
					location.href="./mypage.do";
				}else{
					print("변경 실패!!!!")
				}
            },
            error: function() {
              print("관리자에게 문의 하세요");
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

$(document).on("click", "a", function(){
	$(function () {
	    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
    	})
	})	
})