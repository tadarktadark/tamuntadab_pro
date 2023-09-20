<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_vender_scripts.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@ include file="./shared/_head_css.jsp"%>
<!-- rater-js plugin -->
<script src="./assets/libs/rater-js/index.js"></script>
<!-- rating init -->
<script src="./assets/js/pages/rating.init.js"></script>
</head>
<body>
	<div id="layout-wrapper">
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp"%>
					<div class="align-items-center">
						<form action="javascript:void(0);" class="row g-3">
							<div class="col-md-6">
								<label for="reviPro" class="form-label">전문성</label><br>
								<div id="reviPro" dir="ltr" class="star-rating"
									style="height: 30px; background-size: 30px;">
								</div>
							</div>
							<div class="col-md-6">
								<label for="reviPrepare" class="form-label">준비도</label><br>
								<div id="reviPrepare" dir="ltr" class="star-rating"
									style="height: 30px; background-size: 30px;">
								</div>
							</div>
							<div class="col-md-6">
								<label for="reviAbil" class="form-label">강의력</label><br>
								<div id="reviAbil" dir="ltr" class="star-rating"
									style="height: 30px; background-size: 30px;">
								</div>
							</div>
							<div class="col-md-6">
								<label for="reviSigan" class="form-label">시간준수</label><br>
								<div id="reviSigan" dir="ltr" class="star-rating"
									style="height: 30px; background-size: 30px;">
								</div>
							</div>
							<div class="col-12 mb-3">
								<label for="VertimeassageInput" class="form-label">상세 내용</label><br>
								<textarea class="form-control" id="detailText" rows="10"
									placeholder="상세 내용을 작성해주세요"></textarea>
									<span class="detailSpan">0</span>/300
							</div>
							<div class="col-12">
								<div class="text-end">
									<button type="submit" class="btn btn-primary">등록</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	// 글자수 제한하기
	$("#detailText").on('input', function () {
        var text_length = $(this).val().length;
        if(text_length > 300) {
            alert('300자까지만 작성 가능합니다.');
            $(this).val($(this).val().substring(0, 300));
            $(".detailSpan").text(300);
        } else {
            $(".detailSpan").text(text_length);
        }
    });
})

	var reviPro = raterJs({
		starSize : 30,
		rating : 3,
		element : document.querySelector("#reviPro"),
		rateCallback : function rateCallback(rating, done) {
			this.setRating(rating);
			done();
		}
	});

	var reviPrepare = raterJs({
		starSize : 30,
		rating : 3,
		element : document.querySelector("#reviPrepare"),
		rateCallback : function rateCallback(rating, done) {
			this.setRating(rating);
			done();
		}
	});

	var reviAbil = raterJs({
		starSize : 30,
		rating : 3,
		element : document.querySelector("#reviAbil"),
		rateCallback : function rateCallback(rating, done) {
			this.setRating(rating);
			done();
		}
	});

	var reviSigan = raterJs({
		starSize : 30,
		rating : 3,
		element : document.querySelector("#reviSigan"),
		rateCallback : function rateCallback(rating, done) {
			this.setRating(rating);
			done();
		}
	});
</script>
</html>