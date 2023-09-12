<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
	charset="UTF-8"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	charset="UTF-8"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script type="text/javascript" src="./js/instrCareer.js" charset="UTF-8"></script>
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
						<div class="input-group" style="width: 400px;">
							<input type="text" class="form-control" placeholder="타문타답_경력증명서.doc" readonly="readonly">
							<button type="button" class="btn btn-info w-lg" onclick="location.href='./careerFileDownload.do'">다운로드</button>
						</div>
						<form class="was-validated" enctype="multipart/form-data">
							<div style="width: 600px;">
								<input type="file" class="form-control"
									aria-label="file example" name="file" required>
								<div class="invalid-feedback">파일 첨부는 필수값입니다.</div>
							</div>
						</form>
							<div style="display: flex; justify-content: center;">
								<button class="btn btn-primary" type="submit" disabled style="margin-right: 10px;">인증 요청</button>
								<button class="btn btn-secondary" type="button" onclick="location.href='./myCareerList.do'">기존 인증 요청 목록</button>
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
    $('form').on('submit', function(event) {
        event.preventDefault(); // 기본 form 제출 동작 중단

        var formData = new FormData(this); // form 데이터 생성
        
        if ($('input[type=file]')[0].files.length === 0) {
            alert("파일을 선택해주세요.");
            event.preventDefault(); 
            return;
        }

        $.ajax({
            url: './careerUpload.do',
            type: 'POST',
            data: formData,
            processData: false,  // 필수 옵션
            contentType: false,  // 필수 옵션
            success: function(response) {
            	$("#loading").hide();
                if (response.errorMessage) {
                    alert(response.errorMessage);  // 에러 메시지가 있다면 표시
                } else {
                    alert("관리자에게 승인 요청되었습니다");  // 성공 메시지 표시
                    window.history.back();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	$("#loading").hide();
                console.error(textStatus + " " + errorThrown);
                alert("오류가 발생했습니다");
            }
        });
    });
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
</style>
</html>