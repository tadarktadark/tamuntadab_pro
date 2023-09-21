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
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script type="text/javascript" src="./js/eduLevelForm.js" charset="UTF-8"></script>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body>
	<select class="form-select mb-3" aria-label=".form-select-lg example">
		<option selected>추가 학력 입력*</option>
		<option value="1">고등학교 추가</option>
		<option value="2">대학/대학원 추가</option>
	</select>
	<div id="highSchoolForm"
		class="row row-cols-lg-auto g-3 align-items-center">
		<input type="hidden" name="inedStage" value="5">
		<div class="col-12">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="inedSchoolCheck">
				<label class="form-check-label" for="inedSchoolCheck">대입 검정고시</label>
			</div>
			<div>
				<label for="blackEnd" class="form-label">합격년월일</label> <input
					type="date" class="form-control" id="blackEnd">
			</div>
		</div>
		<!--end col-->
		<div class="col-12">
			<label class="visually-hidden" for="highSchool">학교명</label>
			<div class="input-group">
				<div class="input-group-text">
					<i class="bx bx-search-alt-2"></i>
				</div>
				<input type="text" class="form-control" id="highSchool"
					placeholder="학교명*">
			</div>
		</div>
		<!--end col-->
		<!-- Input Date -->
		<div>
			<label for="highSchoolStart" class="form-label">입학년월일*</label> <input
				type="date" class="form-control" id="highSchoolStart">
		</div>
		<div>
			<label for="highSchoolEnd" class="form-label">졸업년월일*</label> <input
				type="date" class="form-control" id="highSchoolEnd">
		</div>

		<div class="col-12">
			<label class="visually-hidden" for="highSchoolMajor">전공계열*</label> <select
				class="form-select" id="highSchoolMajor">
				<option selected>전공계열</option>
				<option>문과계열</option>
				<option>이과계열</option>
				<option>전문(실업)계</option>
				<option>예체능계</option>
				<option>특성화/마이스터고</option>
				<option>특수목적고</option>
			</select>
		</div>
		<div class="col-12">
			<button type="submit" class="btn btn-primary" onclick="regist()">등록하기</button>
		</div>
	</div>
	<!--end row-->
	<div id="universityForm"
		class="row row-cols-lg-auto g-3 align-items-center">
		<div class="col-12">
			<label class="visually-hidden" for="univStage">대학구분</label> <select
				class="form-select" id="univStage">
				<option selected>대학구분*</option>
				<option value="4">대학(2,3년)</option>
				<option value="3">대학교(4년)</option>
				<option value="2">대학원(석사)</option>
				<option value="1">대학원(박사)</option>
			</select>
		</div>
		<div class="col-12">
			<label class="visually-hidden" for="univSchool">학교명</label>
			<div class="input-group">
				<div class="input-group-text">
					<i class="bx bx-search-alt-2"></i>
				</div>
				<input type="text" class="form-control" id="univSchool"
					placeholder="학교명*">
			</div>
		</div>
		<!--end col-->
		<div class="col-12">
			<label class="visually-hidden" for="major">전공</label>
			<div class="input-group">
				<input type="text" class="form-control" id="major" placeholder="전공*">
			</div>
		</div>
		<div class="col-12">
			<label class="visually-hidden" for="minor">부전공</label>
			<div class="input-group">
				<input type="text" class="form-control" id="minor"
					placeholder="부전공(복수전공)">
			</div>
		</div>
		<!-- Input Date -->
		<div>
			<label for="univStart" class="form-label">입학년월일*</label> <input
				type="date" class="form-control" id="univStart">
		</div>
		<div>
			<label for="univEnd" class="form-label">졸업년월일*</label> <input
				type="date" class="form-control" id="univEnd">
		</div>
		<div class="col-12">
			<button type="submit" class="btn btn-primary" onclick="regist()">등록하기</button>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
    var schoolNames = [];

    // 고등학교 JSON 파일 로드
    $.getJSON('./json/highschools.json', function(data) {
        $.each(data, function(key, val) {
            schoolNames.push(val.SCHUL_NM);
        });

        $('#highSchool').autocomplete({
            source: function(request, response) {
                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(request.term), "i");
                var results = $.grep(schoolNames, function(item){
                    return matcher.test(item);
                });
                if (!results.length) {
                    results = ["검색 결과가 없습니다"];
                }
                response(results);
            },
            minLength: 1,
            select: function(event, ui) {
                if (ui.item.value === "검색 결과가 없습니다") {
                    event.preventDefault();
                }
            },
            focus: function(event, ui) {
                if (ui.item.value === "검색 결과가 없습니다") {
                    event.preventDefault();
                }
            }
        });
    });
    
    var univNames = [];

	// 대학교 JSON 파일 로드
	$.getJSON('./json/university.json', function(data) {
		$.each(data.records, function(index, record) {
			univNames.push(record['학교명']);
		});

		$('#univSchool').autocomplete({
			 source: function(request, response) {
	                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(request.term), "i");
	                var results = $.grep(univNames, function(item){
	                    return matcher.test(item);
	                });
	                if (!results.length) {
	                    results = ["검색 결과가 없습니다"];
	                }
	                response(results);
	            },
	            minLength: 1,
	            select: function(event, ui) {
	                if (ui.item.value === "검색 결과가 없습니다") {
	                    event.preventDefault();
	                }
	            },
	            focus: function(event, ui) {
	                if (ui.item.value === "검색 결과가 없습니다") {
	                    event.preventDefault();
	                }
	            }
		});
   });
	
});
</script>
<style type="text/css">
body {
	padding: 30px;
}

.ui-autocomplete {
	background-color: #f9f9f9;
	max-height: 200px;
	max-width: 590px;
	overflow-y: auto;
	overflow-x: hidden;
}

.ui-autocomplete li {
	list-style-type: none;
}

.ui-helper-hidden-accessible > div {
    visibility: hidden;
}
</style>
</html>