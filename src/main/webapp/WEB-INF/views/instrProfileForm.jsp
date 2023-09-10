<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/instrProfileForm.js"></script>
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
						<form action="#">
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="meassageInput" class="form-label">한줄 소개</label>
									<textarea class="form-control" id="meassageInput" rows="3"
										placeholder="자신을 소개하는 한마디를 적어주세요(100자 이내)"></textarea>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteYoutube" class="form-label">유튜브 링크</label>
									<input type="url" class="form-control" id="inprSiteYoutube"
										placeholder="소개할 자신의 유튜브 url">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteWeb" class="form-label">웹사이트 링크</label> <input
										type="url" class="form-control" id="inprSiteWeb"
										placeholder="소개할 웹 홈페이지">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteMobile" class="form-label">모바일 사이트
										링크</label> <input type="url" class="form-control" id="inprSiteMobile"
										placeholder="소개할 모바일 홈페이지">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="formtextInput" class="form-label"
										style="margin-right: 10px;">학력</label>
									<button type="button" class="btn btn btn-outline-secondary"
										onclick="addEduLevel()">+ 학력추가</button>

									<table class='education-table table table-nowrap' style="display: none;">
										<thead class="table-light">
											<tr>
												<th scope="col">구분</th>
										            <th scope="col">학교명</th>
										            <th scope="col">전공</th>
										            <th scope="col">부전공</th>
										            <th scope="col" >입학년월일</th>
										 	    <th scope="col">졸업년월일</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<div class="col-lg-3">
										<label for="inprSubjects" class="form-label">가능한 과목</label>
									</div>
									<div id="selectedSubjects"
										class="col-lg-9 choices__list choices__list--multiple"></div>
									<div class="choices__inner choices hstack gap-3"
										data-type="multiple">
										<input type="search" id="inprSubjects" name="inprSubjects"
											class="choices__input choices__input--cloned">
									</div>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<div class="col-lg-3">
										<label for="inprSubjectsMajor" class="form-label">전문
											분야 과목</label>
									</div>
									<div id="selectedSubjectsMajor"
										class="col-lg-9 choices__list choices__list--multiple"></div>
									<div class="col-md-2 choices__inner choices"
										data-type="multiple">
										<input type="search" id="inprSubjectsMajor"
											name="inprSubjectsMajor"
											class="choices__input choices__input--cloned">
									</div>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 350px;">
									<label for="inprFee" class="form-label">최소 수업료</label> <input
										type="number" class="form-control" id="inputZip"
										placeholder="단위(만원)">
								</div>
							</div>

							<div class="text-end">
								<button type="submit" class="btn btn-primary">저장</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="./shared/_footer.jsp"%>
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
		$("#inprSubjectsMajor")
				.autocomplete(
						{
							minLength : 1,
							source : function(request, response) {
								searchElastic(request.term, function(results) {
									response(results);
								});
							},
							select : function(event, ui) {
								this.value = '';

								var $selectedSubjectsMajor = $('#selectedSubjectsMajor');

								if ($selectedSubjectsMajor
										.find('div[data-value="'
												+ ui.item.value + '"]').length > 0) {
									return false;
								}

								var $div = $('<div>')
										.addClass(
												'choices choices__item choices__item--selectable')
										.attr('data-value', ui.item.value)
										.attr('data-type', 'select-multiple')
										.text(ui.item.label).appendTo(
												$selectedSubjectsMajor);

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
	});
</script>
<style type="text/css">
body {
	padding: 30px;
}

.card-body {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.ui-autocomplete {
	background-color: #f9f9f9;
	max-height: 200px;
	max-width: 150px; overflow-y : auto;
	overflow-x: hidden;
	overflow-y: auto;
}

.ui-autocomplete li {
	list-style-type: none;
}
</style>
</html>