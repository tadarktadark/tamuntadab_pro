<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script type="text/javascript" src="./js/instrList.js"></script>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid" style="margin: auto 150px;">
					<%@ include file="./shared/_page_title.jsp"%>
			<div class="card">
			<div class="card-body">
			<form class="row g-3 search-form" id="searchForm">
				<div class="subjectsAuto col-md-6">
					<label for="subjects" class="form-label">과목</label> 
					<div id="selectedSubjects"
									class="choices__list choices__list--multiple"></div>
					<input type="search" class="form-control" id="subjects" name="subjects" placeholder="과목명을 입력하세요">
				</div>
				<div class="col-md-6">
					<label for="nickname" class="form-label">이름(닉네임)</label> <input
						type="text" class="form-control" id="nickname" name="nickname"
						placeholder="찾으시는 강사의 닉네임을 입력하세요">
				</div>
				<div class="col-md-6">
					<label for="fee" class="form-label">수업료 (단위: 만원)</label> 
					<input type="number" class="form-control fee" id="feeGt" name="feeGt" placeholder="최소">
					<input type="number" class="form-control fee" id="feeLt" name="feeLt" placeholder="최대">
				</div>
				<div class="col-md-3">
					<label for="gender" class="form-label">성별</label>
					<div>
					<input class="form-check-input" type="radio" name="gender"
									id="gender" value="All" checked> 
					<label class="form-check-label"	for="gender"> 전체 </label> 
					<input class="form-check-input"	type="radio" name="gender" id="gender" value="M"> 
					<label class="form-check-label" for="gender"> 남 </label>
					<input class="form-check-input" type="radio" name="gender" id="gender" value="F"> 
					<label class="form-check-label" for="gender"> 여 </label>
					</div><br>
					<div>
					<input class="form-check-input" type="radio" name="sort" id="order" value="like" checked> 
					<label class="form-check-label"	for="sortType"> 인기순 </label> 
					<input class="form-check-input"	type="radio" name="sort" id="order" value="reg"> 
					<label class="form-check-label" for="sortType"> 등록일순 </label>
					<input class="form-check-input" type="radio" name="sort" id="order" value="basic"> 
					<label class="form-check-label" for="sortType"> 정확도순 </label>
					</div>
				</div>
				<div class="col-3" style="margin-top: 80px;">
					<div class="text-end">
						<button type="submit" class="btn btn-primary">검색</button>
						<button type="button" class="btn btn-warning">초기화</button>
					</div>
				</div>
			</form>
			</div>
			</div>
			<select id="orderSelect" class="form-select rounded-pill mb-3" style="width: 300px;" aria-label="Default select example">
			    <option selected>인기순</option>
			    <option value="view">조회순</option>
			    <option value="reg">등록일순</option>
			</select>

			<div class="row output-area" style="width: auto; height: 450px; overflow: auto;">
			<c:forEach var="instr" items="${lists}" varStatus="vs">
				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row output-area mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
									<c:if test="${instr.inprCert eq 'S' || instr.inprCert eq 'D'}">
										<span title="경력이 인증된 강사" data-bs-toggle="tooltip" data-bs-placement="top"
											class="badge bg-success-subtle text-success member-designation me-2">
											<i class=" bx bxs-user-check" style="font-size: 20px;"></i>
										</span>
									</c:if>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
								<span>&nbsp;</span>
								<c:if test="${instr.ingi eq 'HOT'}">
									<span
										class="badge bg-danger-subtle text-danger   member-designation me-2" style="font-size: 15px;">HOT</span>
								</c:if>
								<c:if test="${instr.reviewCount > 0}">
									<span
										class="badge bg-info-subtle text-info   member-designation me-2" style="font-size: 15px;">후기있음</span>
								</c:if>
								</div>
							</div>
							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="${not empty userInfo? inst.userProfileVo[0].userProfileFile : 'assets/images/users/avatar-9.jpg'}" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">${instr.userProfileVo[0].userNickname}</h5>
									<span class="text-muted fs-13 mt-1 text-truncate">만 ${instr.inprAge} 세</span>
								</a>
								<c:set var="subjectsMajorTitleList" value='${fn:split(instr.subjectsMajorTitle, ",")}'/>
								<div class="row output-area">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">전문분야</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">${fn:trim(subjectsMajorTitleList[0])}</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">최소 수업료(만원)</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">${instr.inprFee == 0 ? '미등록':instr.inprFee}</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
						
							<p class="mb-1 text-muted text-truncate">
								<i class="mdi mdi-book"></i>전문분야: 
							<c:forEach var="major" items="${subjectsMajorTitleList}">
								${fn:trim(major)}
							</c:forEach>
							</p>
							<p class="mb-1 text-muted text-truncate">
								<i class="mdi mdi-book"></i>가능한 과목: 
						<c:forEach var="subject" items="${instr.subjectsTitle}">
								${subject}
						</c:forEach>
							</p>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<button id="scrollToTopButton" class="btn rounded-pill btn-secondary" style="position: fixed; right: 10px; bottom: 70px;">
			<i class="mdi mdi-arrow-up-bold"></i></button>
			<div id="more" style="display: none; text-align: center;">
				<button type="button" class="btn btn-outline-secondary">검색 결과 더보기</button>
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
		$("#subjects")
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

		$('#subjects').on('keydown', function(event) {
			if (event.key === 'Enter') {
				event.preventDefault();
				return false;
			}
		});

	});
	
	$(function () {
	    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
	    })
	})
	
	$('button.btn.btn-warning').click(function(e) {
	    e.preventDefault(); 
	    $('#searchForm')[0].reset();
	});
	
	$('.fee').on('input', function () {
        if ($(this).val().length > 4) {
            $(this).val($(this).val().slice(0, 4));
        }
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
