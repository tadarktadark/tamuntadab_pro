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
<link href="./assets/libs/sweetalert2/sweetalert2.min.css"
	rel="stylesheet" type="text/css" />
<%@ include file="./shared/_vender_scripts.jsp"%>
<%@ include file="./shared/_head_css.jsp"%>
<script
	src="
https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js
"></script>
<link
	href="
https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.css
"
	rel="stylesheet">
<link href="./assets/libs/swiper/swiper-bundle.min.css" rel="stylesheet"
	type="text/css" />
<!--Swiper slider js-->
<script src="./assets/libs/swiper/swiper-bundle.min.js"></script>
<!-- swiper.init js -->
<script src="./assets/js/pages/swiper.init.js"></script>
<style type="text/css">
.flex-container {
	width: 500px;
	margin: auto 20%;
    display: flex;
    justify-content: space-between;
}

a {
	text-decoration: none;
	color: inherit;
}

#subjectDiv {
	text-align: left;
	margin: 15px 20%;
}

#subjectDiv>span {
	display: block; /* 이를 통해 span 요소가 새 줄에서 시작됩니다. */
	margin-bottom: 10px; /* 이를 통해 텍스트와 슬라이더 사이에 간격이 생깁니다. */
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
	font-size: 1.2em;
	width: 30px;
	padding: 5px 10px; /* padding 값 수정: 자동으로 조정되는 값을 수치로 변경 */
}

.mousewheel-control-swiper {
  width: 500px;
  height: 300px;
}

.card-fixed-height {
    height: 230px; /* 원하는 높이로 설정 */
    overflow-y: auto; /* 내용이 넘치면 스크롤이 생기도록 함 */
    cursor: pointer;
}

.team-box {
	cursor: pointer;
}
</style>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>

		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid" style="text-align: center;">

					<span style="font-size: 1.2em;">타문타답에서 연봉을 올리세요!</span>
					<form action="./classList.do" method="get" style="margin: auto 20%; margin-top: 10px;">
					    <div class="position-relative w-100">
					        <input type="text" class="form-control form-control-lg border-2" name="searchQuery" placeholder="모집중인 클래스 검색" autocomplete="off" id="search-options" value="">
					        <input type="hidden" name="page" value="1">
					        <button type="submit" style="border: none; background: none; position: absolute; top: 50%; right: 10px; transform: translateY(-50%);">
					            <span class="bx bx-search search-widget-icon fs-19"></span>
					        </button>
					    </div>
					</form>
<!-- 					<div id="searchModal" style="margin: auto 20%; margin-top: 10px;"> -->
<!-- 						<div class="position-relative w-100"> -->
<!-- 							<input type="text" class="form-control form-control-lg border-2" -->
<!-- 								placeholder="모집중인 클래스 검색" autocomplete="off" id="search-options" -->
<!-- 								value=""> <span -->
<!-- 								class="bx bx-search search-widget-icon fs-19"></span> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div id="subjectDiv">
						<span class="badge bg-secondary-subtle text-secondary  badge-border" style="width: 300px;">
					이런 과목들을 배울 수 있어요</span>
						<div id="subjectTagSlider">
							<c:forEach var="tags" items="${subjects}">
								<span class="badge rounded-pill text-bg-secondary"
									style="font-size: 1.1em; width: 30px; padding: auto 10px;">${tags.sutaTitle}</span>
							</c:forEach>
						</div>
					</div>
					<span class="badge bg-success-subtle text-success  badge-border" style="width:300px; display:block; margin-top: 3%; margin-bottom: 10px; margin-left: 20%; margin-right: 20%; font-size: 1.2em;">
					모집 중인 클래스<i class="bx bx-book-bookmark"></i></span>
					<div id="classDiv">
						<div id="classSlider" class="swiper effect-coverflow-swiper" style="width: 90%;">
							<div class="swiper-wrapper" style="height: 230px;">
								<c:forEach var="regClass" items="${classes}">
									<div class="swiper-slide">
										<div class="card card-fixed-height" onclick="location.href='./classDetail.do?clasId=${regClass.clasId}'">
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
					
					<span class="badge bg-danger-subtle text-danger badge-border" style="width:300px; display:block; margin-top: 3%; margin-bottom: 10px; margin-left: 20%; margin-right: 20%; font-size: 1.2em;">
					인기강사<i class="bx bxs-hot"></i></span>
					<div class="flex-container">
					<div id="ingiInstrDiv1">
						<div id="instrSlider" class="swiper mousewheel-control-swiper">
							<div class="swiper-wrapper">
								<c:forEach var="instr" items="${instrs}">
									<div class="swiper-slide">
										<div class="card team-box" onclick="location.href='./instrDetail.do?seq=${instr.inprSeq}&loginId=${userInfo.userAccountId}'">
											<div class="card-body p-4">
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
															src="${not empty userInfo? instr.userProfileVo[0].userProfileFile : './assets/images/users/user-dummy-img.jpg'}"
															alt="" data-bs-toggle="${empty userInfo? 'tooltip' : ''}"
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
						<div id="instrSliderReverse" class="swiper mousewheel-control-swiper">
							<div class="swiper-wrapper">
								<c:forEach var="instr" items="${instrsReverse}">
									<div class="swiper-slide">
										<div class="card team-box" onclick="location.href='./instrDetail.do?seq=${instr.inprSeq}&loginId=${userInfo.userAccountId}'">
											<div class="card-body p-4">
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
															src="${not empty userInfo? instr.userProfileVo[0].userProfileFile : './assets/images/users/user-dummy-img.jpg'}"
															alt="" data-bs-toggle="${empty userInfo? 'tooltip' : ''}"
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
	<%
	// 세션에 값이 없으면 자바스크립트 코드 실행
	if (session.getAttribute("userInfo") == null) {
	%>
	<script src="./js/autoLogin.js"></script>
	<%
	} else if ((session.getAttribute("userInfo") != null)) {
	%>
	<script src="./js/jeongjiCheck.js"></script>
	<%
	}
	%>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#subjectTagSlider').slick({
			infinite : true,
			slidesToShow : 5,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 2000,
			arrows : false
		});

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
		    loop: true,
		    direction: "vertical",
		    mousewheel: true,
		    slidesPerView: 'auto',
		    mousewheel: {
		        forceToAxis: true,        // 추가
		        releaseOnEdges: true,     // 추가
		        invert: true,
		    },
		    autoplay: {
		        delay: 2500,
		        disableOnInteraction: false,
		        reverseDirection: true,
		    },
		});
		
		var swiper3 = new Swiper("#instrSliderReverse", {
		    loop: true,
		    direction: "vertical",
		    mousewheel: true,
		    slidesPerView: 'auto',
		    mousewheel: {
		        forceToAxis: true,        // 추가
		        releaseOnEdges: true,     // 추가
		    },
		    autoplay: {
		        delay: 2500,
		        disableOnInteraction: false,
		    },
		});
});
	
	$(function () {
	    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
	    })
	})
</script>
</html>