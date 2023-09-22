<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_vender_scripts.jsp"%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	charset="UTF-8"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery.qrcode@1.0.3/jquery.qrcode.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script
	src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<%@ include file="./shared/_head_css.jsp"%>
<!-- rater-js plugin -->
<script src="./assets/libs/rater-js/index.js"></script>
<!-- rating init -->
<script src="./assets/js/pages/rating.init.js"></script>
<script type="text/javascript" src="./js/instrDetail.js"></script>
<style type="text/css">
a {
	text-decoration: none;
	color: inherit;
}

.modal-dialog {
	max-width: 250px;
}
</style>
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
				<input type="hidden" value="${simpleVo.inprAccountId}"
					id="userAccountId"> <input type="hidden"
					value="${userInfo.userAccountId}" id="loginId">

				<div style="width: 50%; margin: auto;">
						<div class="row g-0 bg-body-secondary position-relative">
							<div class="col-md-3 mb-md-0 p-md-4">
								<img
									src="${not empty userInfo? simpleVo.userProfileVo[0].userProfileFile : './assets/images/users/user-dummy-img.jpg'}"
									alt="" data-bs-toggle="${empty userInfo? 'tooltip' : ''}"
									title="${empty userInfo? '로그인 후 볼 수 있습니다.' : ''}" class="w-100"
									alt="...">
						</div>
						<div class="col-md-6 p-4 ps-md-0">
							<div class="d-flex align-items-center">
								<h5 class="mt-0">${simpleVo.userProfileVo[0].userNickname}</h5>
								<div id="heartDiv" class="flex-shrink-0"
									style="margin-left: 10px;">
									<c:choose>
										<c:when test="${simpleVo.inprLike eq 1}">
											<img src="./image/heart_cancel.png" alt=""
												class="avatar-sm rounded-circle like-do"
												id="${simpleVo.inprAccountId}" />
											<input class="heartType" type="hidden" value="cancel">
										</c:when>
										<c:otherwise>
											<img src="./image/heart_do.png" alt=""
												class="avatar-sm rounded-circle like-do"
												id="${simpleVo.inprAccountId}" />
											<input class="heartType" type="hidden" value="do">
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<p></p>
							<p>${simpleVo.inprIntro}</p>
							가능한 과목&nbsp;
							<c:forEach var="subject" items="${simpleVo.subjectsTitle}">
								<span class="badge bg-secondary-subtle text-secondary">
									${subject}</span>
							</c:forEach>
							<p></p>
							<c:if test="${not empty simpleVo.inprSiteYoutube}">
								<span class="badge badge-label bg-danger"
									style="font-size: 15px;"><i class="bx bxl-youtube"></i><a
									href="${simpleVo.inprSiteYoutube}">&nbsp;YOUTUBE</a></span>
							</c:if>
							<c:if test="${not empty simpleVo.inprSiteWeb}">
								<span class="badge badge-label bg-success"
									style="font-size: 15px;"><i class="bx bx-world"></i><a
									href="${simpleVo.inprSiteWeb}">&nbsp;WEB SITE</a></span>
							</c:if>
							<c:if test="${not empty simpleVo.inprSiteMobile}">
								<span class="badge badge-label bg-warning"
									style="font-size: 15px;"><i class="bx bx-mobile"></i><a
									href="#" data-bs-toggle="modal" data-bs-target="#qrModal">&nbsp;MOBILE
										SITE</a></span>
								<input id="siteMobile" type="hidden"
									value="${simpleVo.inprSiteWeb}">
							</c:if>
							<div id="currentCount">
								<p class="list-text mb-0 fs-12 mt-4">
									<i class="ri-eye-fill"></i>&ensp;${simpleVo.inprViewCount}&ensp;
									<i class="ri-heart-fill"></i>&ensp;<span>${simpleVo.inprLikeCount}</span>&ensp;
								</p>
							</div>
						</div>
					</div>
					<ul class="nav nav-tabs nav-border-top nav-justified">
						<li class="nav-item"><a
							class="nav-link ${empty profileVo ? 'disabled':'active'}"
							aria-current="page" href="#profile">프로필</a></li>
						<li class="nav-item"><a
							class="nav-link ${simpleVo.inprCert eq 'N'? 'disabled':''}"
							id="careerTab">경력사항</a></li>
						<li class="nav-item"><a
							class="nav-link ${empty classVo? 'disabled':''}" id="classTab"
							href="#class-history">클래스이력</a></li>
						<li class="nav-item"><a
							class="nav-link ${empty instrReviewVo ? 'disabled':''}"
							id="reviewTab" href="#review">후기</a></li>
					</ul>

					<div id="content">
						<div id="profile" style="display: none;" class="row mb-3 mt-4">
							<div class="card">
								<div class="card-body">
									<div class="row mb-3 mt-4">
										<div class="col-lg-3">
											<label for="nameInput" class="form-label"><b>전문과목</b></label>
										</div>
										<div class="col-lg-9 d-flex align-items-start">
											<c:forEach var="subject"
												items="${profileVo.subjectsMajorTitle}">
												<span class="badge rounded-pill text-bg-danger"
													style="margin-right: 5px;"> ${subject}</span>
											</c:forEach>
										</div>
									</div>

									<div class="row mb-3 mt-4">
										<div class="col-lg-3">
											<label for="nameInput" class="form-label"><b>최소
													수업료</b></label>
										</div>
										<div class="col-lg-9 d-flex align-items-start">
											<span>${profileVo.inprFee} 만원</span>
										</div>
									</div>
									<div class="col-lg-3">
										<label for="nameInput" class="form-label"><b>학력사항</b></label>
									</div>
									<div class="col-lg-12 table-responsive">
										<table class='education-table table table-nowrap'
											style="display: none;">
											<thead class="table-light">
												<tr style="text-align: center;">
													<th scope="col">구분</th>
													<th scope="col">학교명</th>
													<th scope="col">전공</th>
													<th scope="col">부전공</th>
													<th scope="col">입학년월일</th>
													<th scope="col">졸업년월일</th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${not empty profileVo.instrEduVo}">
													<c:forEach var="edu" items="${profileVo.instrEduVo}"
														varStatus="vs">
														<tr style="text-align: center;" class="isDb">
															<td><c:choose>
																	<c:when test="${edu.inedStage == 1}">대학원(박사)</c:when>
																	<c:when test="${edu.inedStage == 2}">대학원(석사)</c:when>
																	<c:when test="${edu.inedStage == 3}">대학교(4년)</c:when>
																	<c:when test="${edu.inedStage == 4}">대학(2,3년)</c:when>
																	<c:otherwise>고등학교</c:otherwise>
																</c:choose> <input type="hidden"
																name="instrEduVo[${vs.index}].inedSeq"
																value="${edu.inedSeq}"> <input type="hidden"
																name="instrEduVo[${vs.index}].inedStage"
																value="${edu.inedStage}"></td>
															<td>${edu.inedSchool}<input type="hidden"
																name="instrEduVo[${vs.index}].inedSchool"
																value="${edu.inedSchool}">
															</td>
															<td>${edu.inedMajor}<input type="hidden"
																name="instrEduVo[${vs.index}].inedMajor"
																value="${edu.inedMajor}"></td>
															<td>${edu.inedMinor}<input type="hidden"
																name="instrEduVo[${vs.index}].inedMinor"
																value="${edu.inedMinor}"></td>
															<td>${edu.inedStart}<input type="hidden"
																name="instrEduVo[${vs.index}].inedStart"
																value="${edu.inedStart}"></td>
															<td>${edu.inedEnd}<input type="hidden"
																name="instrEduVo[${vs.index}].inedEnd"
																value="${edu.inedEnd}"></td>
														</tr>
													</c:forEach>
													<script type="text/javascript" charset="UTF-8">
														$('.education-table')
																.css('display',
																		'table');
													</script>
												</c:if>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div id="career" style="display: none;" class="mt-4"></div>
						<div id="class-history" style="display: none;">
							<div class="card">
								<div class="card-body">
									<div class="mt-4">
										성사된 강의율 ${successRate}% <br>(총 ${allClass} 강의 중
										${classVo.size()} 강의 완료)
										<div class="progress mb-4 mt-2">
											<div class="progress-bar bg-success" role="progressbar"
												style="width: ${successRate}%" aria-valuenow="50"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</div>
								</div>
							</div>
							<div id="classContent">
							<c:forEach var="history" items="${classVo}">
								<div class="card">
									<div class="card-body">
										<div class="col-12">강의일 :
											${history.classVo[0].clasSueopNaljja}</div>
										<div class="row mt-4">
											<div class="col-6">
												<button type="button" class="btn btn-info w-sm"
													disabled="disabled">강의명</button>
												<a data-bs-toggle="tooltip"
									title="필기 보러가기" class="w-100" href="./community.do?board=pilgi&clasId=${history.classVo[0].clasId}">${history.classVo[0].clasTitle}</a>
											</div>
											<div class="col-6">
												<button type="button" class="btn btn-warning w-sm"
													disabled="disabled">과목</button>
													<c:forEach var="subject" items="${history.subjectVo[0].subjCode}">
												<span
													class="badge bg-secondary-subtle text-secondary">
													${subject}</span>
													</c:forEach>
											</div>
										</div>
										<div class="row mt-4">
											<div class="col-6">
												<button type="button" class="btn btn-warning w-sm"
													disabled="disabled">지역</button>
												${history.classVo[0].clasLocation}
											</div>
											<div class="col-6">
												<button type="button" class="btn btn-success w-sm"
													disabled="disabled">수강료</button>
												${history.classVo[0].clasSugangRyo} (만원)
											</div>
										</div>
									</div>
								</div>
								</c:forEach>
							</div>
						</div>
						<div id="review" style="display: none;">
							<select id="orderSelect" class="form-select rounded-pill mb-3"
								style="width: 300px;" aria-label="Default select example">
								<option selected value="recent">최근 작성일순</option>
								<option value="desc">별점 높은순</option>
								<option value="asc">별점 낮은순</option>
							</select>
							<div id="reviewContent">
								<c:forEach var="classVo" items="${instrReviewVo}">
									<c:forEach var="review" items="${classVo.reviewVo}">
										<div class="card">
											<div class="card-body">
												<div>총점 (${review.avgScore})</div>
												<div class="row mt-4">
													<div class="col-6">
														<div>전문성 (${review.reviPro})</div>
														<div id="basic-rater" dir="ltr" class="star-rating"
															data-rating="3"
															style="width: 110px; height: 22px; background-size: 22px;">
															<div class="star-value"
																style="background-size: 22px; width: ${review.reviPro/5.0*100}%;"></div>
														</div>
													</div>

													<div class="col-6">
														<div>준비도 (${review.reviPrepare})</div>
														<div id="basic-rater" dir="ltr" class="star-rating"
															data-rating="3"
															style="width: 110px; height: 22px; background-size: 22px;">
															<div class="star-value"
																style="background-size: 22px; width:${review.reviPrepare/5.0*100}%;"></div>
														</div>
													</div>
												</div>
												<div class="row mt-4">
													<div class="col-6">
														<div>강의력 (${review.reviAbil})</div>
														<div id="basic-rater" dir="ltr" class="star-rating"
															data-rating="3"
															style="width: 110px; height: 22px; background-size: 22px;">
															<div class="star-value"
																style="background-size: 22px; width: ${review.reviAbil/5.0*100}%;"></div>
														</div>
													</div>
													<div class="col-6">
														<div>시간준수 (${review.reviSigan})</div>
														<div id="basic-rater" dir="ltr" class="star-rating"
															data-rating="3"
															style="width: 110px; height: 22px; background-size: 22px;">
															<div class="star-value"
																style="background-size: 22px; width: ${review.reviSigan/5.0*100};"></div>
														</div>
													</div>
												</div>
												<div class="row mt-4">
													<div class="col-6">
														<button type="button" class="btn btn-success w-sm"
															disabled="disabled">작성자</button>
														${review.reviStudName}
													</div>
													<div class="col-6">
														<button type="button" class="btn btn-success w-sm"
															disabled="disabled">작성일</button>
														${review.reviRegdate}
													</div>
												</div>
												<div class="row mt-4"
													style="background-color: #EAEAEA; padding: 20px; magin: auto 10px;">
													<div class="col-12 table-info">${review.reviDetail}</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:forEach>
							</div>
						</div>
					</div>

					<div id="chatting" style="position: fixed; color: #50AF49;">
						<button type="button" class="btn btn-outline-success btn-icon"
							style="font-size: 1.7em;">
							<a href="./intsrChatRoom.do?instrAccountId=TMTD103&studAccountId=${userInfo.userAccountId}"> <i class="ri-customer-service-2-line"></i></a>
						</button>
						<br> 문의하기
					</div>

					<!-- Modal -->
					<div class="modal fade flip" id="qrModal" tabindex="-1"
						aria-labelledby="qrModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-flip">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="qrModalLabel">모바일 사이트 QR</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								</div>
								<div id='qrcode' class='modal-body'></div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="./shared/_footer.jsp"%>
			</div>
		</div>
</body>
<script type="text/javascript">
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
	});
</script>
<script id="heart-template" type="text/x-handlebars-template">
<img src="./image/heart_{{type}}.png" alt=""
		class="avatar-sm rounded-circle like-do" id="{{inprAccountId}}"/>
<input class="heartType" type="hidden" value="{{type}}">
</script>
<script id="current-count-template" type="text/x-handlebars-template">
	<p class="list-text mb-0 fs-12 mt-4"> 
		<i class="ri-eye-fill"></i>&ensp;{{viewCount}}&ensp;
		<i class="ri-heart-fill"></i>&ensp;<span>{{likeCount}}</span>&ensp;
	</p>
</script>
<script id="career-template" type="text/x-handlebars-template">
    {{#each this}}
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-6">
                    <button type="button" class="btn btn-primary w-sm" disabled>회사명</button>
                    {{careCompany}}
                </div>
                <div class="col-6">
                    <button type="button" class="btn btn-success w-sm" disabled>소속</button>
                    {{careSosok}}
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6">
                    <button type="button" class="btn btn-success w-sm" disabled>직무</button>
                    {{careJob}}
                </div>

                <div class="col-6">
                    <button type="button" class="btn btn-primary w-sm" disabled>기간</button>
                    {{carePeriod}}
                 </div>
             </div>
         </div>
     </div>
{{/each}}
</script>
<script id="class-history-template" type="text/x-handlebars-template">
{{#each this}}
	<div class="card">
		<div class="card-body">
			<div class="col-12">강의일 :
				{{classVo.[0].clasSueopNaljja}}</div>
					<div class="row mt-4">
						<div class="col-6">
							<button type="button" class="btn btn-info w-sm"
								disabled="disabled">강의명</button>
									<a data-bs-toggle="tooltip"
									title="필기 보러가기" class="w-100" href="./community.do?board=pilgi&clasId={{classVo.[0].clasId}}">{{classVo.[0].clasTitle}}</a>
						</div>
						<div class="col-6">
							<button type="button" class="btn btn-warning w-sm"
								disabled="disabled">과목</button>
								{{#each subjectVo.[0].subjCode}}
								<span class="badge bg-secondary-subtle text-secondary">
									{{this}}</span>
								{{/each}}
						</div>
						</div>
							<div class="row mt-4">
								<div class="col-6">
									<button type="button" class="btn btn-warning w-sm"
											disabled="disabled">지역</button>
										{{classVo.[0].clasLocation}}
								</div>
								<div class="col-6">
									<button type="button" class="btn btn-success w-sm"
										disabled="disabled">수강료</button>
											{{classVo.[0].clasSugangRyo}} (만원)
								</div>
						</div>
				</div>
			</div>
{{/each}}
</script>
<script id="review-template" type="text/x-handlebars-template">
{{#each this}}
	{{#each reviewVo}}
											<div class="card">
											<div class="card-body">
												<div>총점 ({{avgScore}})</div>
												<div class="row mt-4">
													<div class="col-6">
														<div>전문성 ({{reviPro}})</div>
														<div id="basic-rater" dir="ltr" class="star-rating"
															data-rating="3"
															style="width: 110px; height: 22px; background-size: 22px;">
															<div class="star-value"
														style="background-size: 22px; width: {{calculatePercentage reviPro}}%;"></div>
												</div>
											</div>

											<div class="col-6">
												<div>준비도 ({{reviPrepare}})</div>
												<div id="basic-rater" dir="ltr" class="star-rating"
													data-rating="3"
													style="width: 110px; height: 22px; background-size: 22px;">
													<div class="star-value"
														style="background-size: 22px; width:{{calculatePercentage reviPrepare}}%;"></div>
												</div>
											</div>
										</div>
										<div class="row mt-4">
											<div class="col-6">
												<div>강의력 ({{reviAbil}})</div>
												<div id="basic-rater" dir="ltr" class="star-rating"
													data-rating="3"
													style="width: 110px; height: 22px; background-size: 22px;">
													<div class="star-value"
														style="background-size: 22px; width: {{calculatePercentage reviAbil}}%;"></div>
												</div>
											</div>
											<div class="col-6">
												<div>시간준수 ({{reviSigan}})</div>
												<div id="basic-rater" dir="ltr" class="star-rating"
													data-rating="3"
													style="width: 110px; height: 22px; background-size: 22px;">
													<div class="star-value"
														style="background-size: 22px; width: {{calculatePercentage reviSigan}}%;"></div>
												</div>
											</div>
										</div>
										<div class="row mt-4">
											<div class="col-6">
												<button type="button" class="btn btn-success w-sm"
													disabled="disabled">작성자</button>
												{{reviStudName}}
											</div>
											<div class="col-6">
												<button type="button" class="btn btn-success w-sm"
													disabled="disabled">작성일</button>
												{{review.reviRegdate}}
											</div>
										</div>
										<div class="row mt-4"
											style="background-color: #EAEAEA; padding: 20px; magin: auto 10px;">
											<div class="col-12 table-info">{{reviDetail}}</div>
										</div>
									</div>
								</div>
	{{/each}}
{{/each}}
</script>
</html>