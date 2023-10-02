<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<%@ include file="./shared/_logout.jsp"%>
<link href="./assets/libs/sweetalert2/sweetalert2.min.css"
	rel="stylesheet" type="text/css" />
<%@ include file="./shared/_head_css.jsp"%>
<script
	src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script src="./js/index.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js "></script>
<link
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.css"
	rel="stylesheet">
<link href="./assets/libs/swiper/swiper-bundle.min.css" rel="stylesheet"
	type="text/css" />
<!--Swiper slider js-->
<script src="./assets/libs/swiper/swiper-bundle.min.js"></script>
<!-- swiper.init js -->
<script src="./assets/js/pages/swiper.init.js"></script>
<style type="text/css">
.card-fixed-height::-webkit-scrollbar {
	width: 10px;
}

.card-fixed-height::-webkit-scrollbar-track {
	background: #f1f1f1;
}

.card-fixed-height::-webkit-scrollbar-thumb {
	background: #888;
}

.card-fixed-height::-webkit-scrollbar-thumb:hover {
	background: #555;
}

.flex-container {
	margin-bottom: 10px;
	display: flex;
	justify-content: space-between;
	display: flex;
}

.team-box {
	width: 90%;
}

a {
	text-decoration: none;
	color: inherit;
}

#subjectDiv {
	text-align: left;
	margin: 15px;
}

#subjectTagSlider {
	width: 100%;
	align-self: center;
	vertical-align: middle;
}

.slick-slide {
	margin-right: 10px;
}

#subjectDiv .badge {
	width: 30px;
	padding: 5px 10px;
}

.mousewheel-control-swiper {
	width: 500px;
	height: 300px;
}

.card-fixed-height {
	height: 230px;
	overflow-y: auto;
	cursor: pointer;
}

.team-box {
	cursor: pointer;
}
</style>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp"%>

		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid" style="text-align: center;">
					<div>
						<span style="font-size: 1.4em;">타문타답에서 우리만의 커리큘럼을 만드세요!</span>
					</div>
					<form style="margin-top: 10px;" id="indexForm">
						<div class="position-relative w-100">
							<input type="text" class="form-control form-control-lg border-2"
								name="searchQuery" placeholder="모집중인 클래스 검색" autocomplete="off"
								id="search-options" value="">
								<button type="submit"
								style="border: none; background: none; position: absolute; top: 50%; right: 10px; transform: translateY(-50%);">
								<span class="bx bx-search search-widget-icon fs-19"></span>
							</button>
						</div>
					</form>
						<span
							class="badge bg-secondary-subtle text-secondary  badge-border"
							style="width: 300px; text-align: left; display: block; margin-top: 2%; margin-bottom: 10px; font-size: 1.2em;""><i
							class="bx bxs-bookmark" style="vertical-align: middle;"></i>&nbsp;이런
							과목들을 배울 수 있어요 </span>
					<div id="subjectDiv">
						<div id="subjectTagSlider"></div>
					</div>
					<span class="badge bg-success-subtle text-success  badge-border"
						style="width: 300px; text-align: left; display: block; margin-top: 3%; margin-bottom: 10px; font-size: 1.2em;">
						<i class="bx bxs-book-reader" style="vertical-align: middle;"></i>&nbsp;모집
						중인 클래스
					</span>
					<div id="classDiv">
						<div id="classSlider" class="swiper effect-coverflow-swiper"
							style="width: 100%;">
							<div class="swiper-wrapper" style="height: 230px;">
								<c:forEach var="regClass" items="${classes}">
									<div class="swiper-slide">
										<div class="card card-fixed-height"
											onclick="location.href='./classDetail.do?clasId=${regClass.clasId}'">
											<div class="card-body">
												<div class="col-12">
													<b>예정 수업일 : ${regClass.clasSueopNaljja}</b>
												</div>
												<div style="text-align: left;">
													<div class="row mt-4">
														<div class="col-12">
															<span class="badge rounded-pill text-bg-success">강의명</span>
															&nbsp;${regClass.clasTitle}
														</div>
													</div>
													<div class="row mt-4">
														<div class="col-12">
															<span class="badge rounded-pill text-bg-info">과목</span>&nbsp;
															<c:forEach var="subject"
																items="${regClass.subjVo[0].subjTitle}">
																<span class="badge bg-secondary-subtle text-secondary">
																	${subject}</span>
															</c:forEach>
														</div>
													</div>
													<div class="row mt-4">
														<div class="col-12">
															<span class="badge rounded-pill text-bg-warning">지역</span>&nbsp;
															${regClass.clasLocation}
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<div
								class="swiper-pagination swiper-pagination-dark swiper-pagination-clickable swiper-pagination-bullets swiper-pagination-horizontal swiper-pagination-bullets-dynamic"
								style="width: 150px;"></div>
						</div>
					</div>

					<span class="badge bg-danger-subtle text-danger badge-border"
						style="width: 300px; text-align: left; display: block; margin-top: 3%; margin-bottom: 10px; font-size: 1.2em;">
						<i class="bx bxs-hot" style="vertical-align: middle;"></i>&nbsp;인기강사
					</span>
					<div class="flex-container">
						<div id="ingiInstrDiv1">
							<div id="instrSlider" class="swiper mousewheel-control-swiper">
								<div class="swiper-wrapper">
									<c:forEach var="instr" items="${instrs}">
										<div class="swiper-slide">
											<div class="card team-box"
												onclick="location.href='./instrDetail.do?seq=${instr.inprSeq}&loginId=${userInfo.userAccountId}'">
												<div class="card-body p-2">
													<div class="row output-area mb-3">
														<div class="col-auto text-end dropdown">
															<span>&nbsp;</span> <span
																class="badge bg-danger-subtle text-danger   member-designation me-2"
																style="font-size: 15px;">HOT</span>
														</div>
													</div>
													<div class="text-center mb-3">
														<div class="avatar-lg mx-auto">
															<img
																src="${not empty userInfo? instr.userProfileVo[0].userProfileFile : './image/profile.png'}"
																alt=""
																data-bs-toggle="${empty userInfo? 'tooltip' : ''}"
																title="${empty userInfo? '로그인 후 볼 수 있습니다.' : ''}"
																class="member-img img-fluid d-block rounded-circle">
														</div>
													</div>
													<div class="text-center">
														<h5 class="fs-16 mb-1">${instr.userProfileVo[0].userNickname}</h5>
														<c:set var="subjectsMajorTitleList"
															value='${fn:split(instr.subjectsMajorTitle, ",")}' />
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
							</div>
						</div>
						<div id="ingiInstrDiv2">
							<div id="instrSliderReverse"
								class="swiper mousewheel-control-swiper">
								<div class="swiper-wrapper">
									<c:forEach var="instr" items="${instrsReverse}">
										<div class="swiper-slide">
											<div class="card team-box"
												onclick="location.href='./instrDetail.do?seq=${instr.inprSeq}&loginId=${userInfo.userAccountId}'">
												<div class="card-body p-2">
													<div class="row output-area mb-3">
														<div class="col-auto text-end dropdown">
															<span>&nbsp;</span> <span
																class="badge bg-danger-subtle text-danger   member-designation me-2"
																style="font-size: 15px;">HOT</span>
														</div>
													</div>
													<div class="text-center mb-3">
														<div class="avatar-lg mx-auto">
															<img
																src="${not empty userInfo? instr.userProfileVo[0].userProfileFile : './image/profile.png'}"
																alt=""
																data-bs-toggle="${empty userInfo? 'tooltip' : ''}"
																title="${empty userInfo? '로그인 후 볼 수 있습니다.' : ''}"
																class="member-img img-fluid d-block rounded-circle">
														</div>
													</div>
													<div class="text-center">
														<h5 class="fs-16 mb-1">${instr.userProfileVo[0].userNickname}</h5>
														<c:set var="subjectsMajorTitleList"
															value='${fn:split(instr.subjectsMajorTitle, ",")}' />
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
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<%@ include file="./shared/_vender_scripts.jsp"%>
</body>
<script type="text/javascript">
$('#indexForm').on('submit', function(e) {
    e.preventDefault(); // 기본 동작 중단

    var searchQuery = $('#search-options').val(); // 검색어 값 가져오기

    // 현재 페이지를 classList.do로 변경하고 검색어 값을 쿼리 파라미터로 전달
    window.location.href = './classList.do?subjects=' + searchQuery;
});



	$(document).ready(function() {
		
		var swiper1 = new Swiper("#classSlider", {
			loop : true,
			effect : "coverflow",
			grabCursor : true,
			centeredSlides : true,
			slidesPerView : "4",
			coverflowEffect : {
				rotate : 50,
				stretch : 0,
				depth : 100,
				modifier : 1,
				slideShadows : true,
			},
			autoplay : {
				delay : 2500,
				disableOnInteraction : false,
			},
			pagination : {
				el : ".swiper-pagination",
				clickable : true,
				dynamicBullets : true,
			},
		});

		var swiper2 = new Swiper("#instrSlider", {
			loop : true,
			direction : "vertical",
			mousewheel : true,
			slidesPerView : 'auto',
			mousewheel : {
				forceToAxis : true,
				releaseOnEdges : true,
				invert : true,
			},
			autoplay : {
				delay : 2500,
				disableOnInteraction : false,
				reverseDirection : true,
			},
		});

		var swiper3 = new Swiper("#instrSliderReverse", {
			loop : true,
			direction : "vertical",
			mousewheel : true,
			slidesPerView : 'auto',
			mousewheel : {
				forceToAxis : true,
				releaseOnEdges : true,
			},
			autoplay : {
				delay : 2500,
				disableOnInteraction : false,
			},
		});
	});

	$(function() {
		var tooltipTriggerList = [].slice.call(document
				.querySelectorAll('[data-bs-toggle="tooltip"]'))
		var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
			return new bootstrap.Tooltip(tooltipTriggerEl, {
				delay : {
					"show" : 100,
					"hide" : 100
				}
			})
		})
	})
</script>
<script id="subjects-template" type="text/x-handlebars-template">
{{#each this}}
	<span class="badge rounded-pill text-bg-secondary"
		style="font-size: 1em; width: 30px; padding: auto 10px;">{{sutaTitle}}</span>
{{/each}}
</script>
</html>