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
			</div>
			<!-- 			<form id="searchForm"> -->
			<!-- 				<div class="ageRange choices__inner choices hstack gap-3" -->
			<!-- 					style="width: 600px; display: inline-flex; justify-content: space-evenly;"> -->
			<!-- 						<label for="age"> 나이 </label> -->
			<!-- 					<div class="nameSearch form-floating" style="width: 100px;"> -->
			<!-- 						<input type="number" class="form-control" id="ageGt" name="ageGt"> -->
			<!-- 						<label for="ageGt">최소</label> -->
			<!-- 					</div> -->
			<!-- 					~ -->
			<!-- 					<div class="nameSearch form-floating" style="width: 100px;"> -->
			<!-- 						<input type="number" class="form-control" id="ageLt" name="ageLt"> -->
			<!-- 						<label for="ageLt">최대</label> -->
			<!-- 					</div> -->
			<!-- 				</div> -->
			<!-- 				<input type="submit" value="검색"> -->
			<!-- 			</form> -->
			<div class="card">
			<div class="card-body">
			<form action="javascript:void(0);" class="row g-3">
				<div class="subjectsAuto col-md-6">
					<label for="inprSubjects" class="form-label">과목</label> 
					<div id="selectedSubjects"
									class="choices__list choices__list--multiple"></div>
					<input type="search" class="form-control" id="inprSubjects" name="inprSubjects" placeholder="과목명을 입력하세요">
				</div>
				<div class="col-md-6">
					<label for="nickname" class="form-label">이름(닉네임)</label> <input
						type="text" class="form-control" id="nickname" name="nickname"
						placeholder="이름 또는 닉네임을 입력하세요">
				</div>
				<div class="col-md-6">
					<label for="age" class="form-label">연령대</label> 
					<input type="number" class="form-control" id="ageGt" placeholder="최소">
					<input type="number" class="form-control" id="ageLt" placeholder="최대">
				</div>
				<div class="col-md-3">
					<label for="gender" class="form-label">성별</label>
					<div>
					<input class="form-check-input" type="radio" name="gender"
									id="gender" value="All"> 
					<label class="form-check-label"	for="gender"> 전체 </label> 
					<input class="form-check-input"	type="radio" name="gender" id="gender" value="M"> 
					<label class="form-check-label" for="gender"> 남 </label>
					<input class="form-check-input" type="radio" name="gender" id="gender" value="F"> 
					<label class="form-check-label" for="gender"> 여 </label>
					</div><br>
					<div>
					<input class="form-check-input" type="radio" name="order" id="order" value="like"> 
					<label class="form-check-label"	for="order"> 인기순 </label> 
					<input class="form-check-input"	type="radio" name="order" id="order" value="view"> 
					<label class="form-check-label" for="order"> 조회순 </label>
					<input class="form-check-input" type="radio" name="order" id="order" value="reg"> 
					<label class="form-check-label" for="order"> 등록일순 </label>
					</div>
				</div>
				<div class="col-3" style="margin-top: 80px;">
					<div class="text-end">
						<button type="submit" class="btn btn-primary">검색</button>
					</div>
				</div>
			</form>
			</div>
			</div>

			<div class="row">
				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<span
											class="badge bg-success-subtle text-success member-designation me-2">
											<i class=" bx bxs-user-check" style="font-size: 15px;"></i>
										</span>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-danger-subtle text-danger   member-designation me-2">HOT</span>
									<span
										class="badge bg-info-subtle text-info   member-designation me-2">후기있음</span>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-9.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Ross Jordan</h5>
								</a>
								<p class="text-muted mb-0">Project Manager</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Development</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">17 Oct, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Rossjordan@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+31
								509-329-3984
							</p>
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
	function searchElastic(query, callback) {
		$.ajax({
			url : 'http://192.168.8.164:9200/subject_tag/_search',
			type : 'POST',
			dataType : 'json',
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa('elastic:elastic')
			},
			data : JSON.stringify({
				suggest : {
					"title-suggestion" : {
						"prefix" : query,
						"completion" : {
							"field" : "title"
						}
					}
				}
			}),
			success : function(response) {
				var results = response.suggest['title-suggestion'][0].options
						.map(function(option) {
							return {
								label : option._source.title[0],
								value : option._source.code
							};
						});
				callback(results);
			},
			error : function() {
				callback([]);
			}
		});
	}

	$(function() {
		$("#inprSubjects")
				.autocomplete(
						{
							minLength : 1,
							source : function(request, response) {
								searchElastic(request.term, function(results) {
									response(results);
								});
							},
							focus : function() {
								return false;
							},
							select : function(event, ui) {
								this.value = '';

								// 선택된 값들이 추가될 div 요소
								var $selectedSubjects = $('#selectedSubjects');

								// 이미 추가된 값인지 확인
								if ($selectedSubjects.find('li[data-value="'
										+ ui.item.value + '"]').length > 0) {
									return false;
								}

								// 새로운 div 요소 생성 및 추가
								var $div = $('<div>')
										.addClass(
												'choices choices__item choices__item--selectable')
										.attr('data-value', ui.item.value)
										.attr('data-type', 'select-multiple')
										.text(ui.item.label).appendTo(
												$selectedSubjects);

								// 삭제 버튼 생성 및 추가
								var $removeButton = $('<button>').addClass(
										'choices__button').attr('aria-label',
										"Remove item: '" + ui.item.label + "'")
										.attr('data-button', '').text(
												"Remove item").on('click',
												function() {
													$div.remove();
												}).appendTo($div);

								return false;
							},
						}).data("ui-autocomplete")._renderItem = function(ul,
				item) {
			return $("<li>").append("<a>" + item.label + "</a>").appendTo(ul);
		};

		$('#inprSubjects').on('keydown', function(event) {
			if (event.key === 'Enter') {
				event.preventDefault();
				return false;
			}
		});

	});
</script>
<style type="text/css">
.ui-autocomplete {
	background-color: #f9f9f9;
	max-height: 200px;
	max-width: 150px;
	overflow-y: auto;
	overflow-x: hidden;
}

.ui-autocomplete li {
	list-style-type: none;
}
</style>

</html>
