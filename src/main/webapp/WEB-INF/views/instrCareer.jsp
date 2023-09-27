<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" charset="UTF-8"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	charset="UTF-8"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>

		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp"%>
				</div>
				<div class="card">
					<div class="card-body">
						<!-- Info Alert -->
						<div class="alert alert-borderless alert-info" role="alert">
							경력 증명을 위해 타문타답의 전용 경력인증서 양식을 다운받아 작성 후 PDF로 업로드 해주세요.</div>
						<div class="input-group" style="width: 600px; margin-top: -160px;">
							<input type="text" class="form-control"
								placeholder="타문타답_경력증명서.doc" readonly="readonly">
							<button type="button" class="btn btn-info w-lg"
								onclick="location.href='./careerFileDownload.do'">다운로드 
								<i class="mdi mdi-tray-arrow-down"></i>
								</button>
						</div>
						<form class="was-validated" action="./careerUpload.do"
							method="post" enctype="multipart/form-data">
							<div style="width: 600px; margin-bottom: 200px;">
								<input type="file" class="form-control"
									aria-label="file example" name="file" required>
								<div class="invalid-feedback">파일 첨부는 필수값입니다.</div>
							</div>
							<div style="display: flex; justify-content: center;">
								<button class="btn btn-primary" type="submit" disabled
									style="margin-right: 10px;">인증 요청</button>
								<button class="btn btn-secondary" type="button"
									onclick="location.href='./myCareerList.do'">기존 인증 요청
									목록</button>
							</div>
						</form>
						<div id="overlay"></div>

						<div align="center" class="spinner-border text-primary"
							id="loading" role="status">
							<span class="visually-hidden">Loading...</span>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp"%>
</body>
<script type="text/javascript">
	$(document).ready(function() {

		$('input[type=file]').change(function() {
			var file = this.files[0]; // 선택된 파일

			if (file) {
				var ext = file.name.split('.').pop().toLowerCase(); // 확장자 추출

				if (ext !== 'pdf') { // PDF 파일이 아니면 경고 메시지 출력
					Swal.fire("PDF 파일만 가능합니다.");
					this.value = ''; // 파일 입력 필드 초기화
					$('.btn-primary').prop('disabled', true); // 인증 요청 버튼 비활성화
				} else { // PDF 파일이면 인증 요청 버튼 활성화
					$('.btn-primary').prop('disabled', false);
				}
			}
		});

		$('form').on('submit', function(event) {
			event.preventDefault(); // 기본 form 제출 동작 중단

			var formData = new FormData(this); // form 데이터 생성

			$.ajax({
				url : './careerUpload.do',
				type : 'POST',
				data : formData,
				processData : false, // 필수 옵션
				contentType : false, // 필수 옵션
				success : function(response) {
					$("#loading").hide();
					if (response.errorMessage) {
						Swal.fire({
                            title: response.errorMessage,
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
						$('input[type=file]').val('');
					} else if(response.successMessage) {
						 Swal.fire({
						        title: response.successMessage,
						        text: '',
						        icon: 'success',
						        customClass:{
						            confirmButton:'btn btn-primary w-xs mt-2'
						        }
						    }).then((result) => {
						      if (result.isConfirmed) {
						          window.history.back();
						      }
						});
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#loading").hide();
					console.error(textStatus + " " + errorThrown);
					Swal.fire({
                        title:'오류가 발생했습니다',
                        text:'',
                        icon:'error',
                        customClass:{
                            confirmButton:'btn btn-primary w-xs mt-2'
                        }
                     });
				}
			});
		});
	});

	$(document).ajaxStart(function() {
	     $("#overlay").show(); // Show the overlay
	     $("#loading").show(); // Show the loading spinner
	}).ajaxStop(function(){
	     $("#overlay").hide(); // Hide the overlay
	     $("#loading").hide(); // Hide the loading spinner
	});
</script>
<style type="text/css">
body {
	padding: 30px;
}

.card-body {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
	height: 600px; /* Adjust as needed */
}

#overlay {
    display: none;
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(255,255,255,0.8);
    z-index: 2; /* Sit on top */
}

#loading {
   display:none; 
   position:absolute; 
   left :50%; 
   top :50%; 
   margin-left :-24px; 
   margin-top :-24px;
   z-index :3; 
}
</style>
</html>