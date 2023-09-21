<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="smprofile"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_head_css.jsp"%>
<style type="text/css">
.custom-margin {
	margin-left: 16px;
}

.custom-card {
	/* 카드 중앙 정렬 */
	display: flex;
	justify-content: center;
	align-items: center;
}

.custom-card-body {
	/* 카드 바디 설정 */
	width: 800px;
	/* 컨텐츠 왼쪽 정렬 */
	text-align: left;
}

.class-detail-content {
	margin: 40px;
}

.align-items-center-detail {
	margin-top: 50px;
}

style>table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
}

.icon-cell {
	width: 50px;
	text-align: center;
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

					<div class="row ">
						<div class="d-flex">
							<!-- left Sidebar -->
							<div class="email-rightbar col-md-10 col-lg-10 custom-margin">
								<div class="card custom-card">
									<div class="card-body custom-card-body">
										<div style="text-align: left;">
											<div class="d-flex align-items-center-detail mb-5">
												<div class="flex-shrink-0 me-3">
													<img src="${cVo.userVo[0].userProfileFile}" alt="userProfileFile"
														class="rounded-circle avatar-xl">
												</div>
												<table>
													<tr>
														<td class="icon-cell"><i class="bi bi-person"></i></td>
														<td style="width: 150px;">개설자</td>
														<td><small class="fs-15 mb-0">
																${cVo.userVo[0].userNickname}</small></td>
													</tr>
													<tr>
														<td class="icon-cell"><i class="bi bi-pencil"></i></td>
														<td style="width: 150px;">클래스 제목</td>
														<td><small class="fs-15"> ${cVo.clasTitle} </small><span class="bg-warning badge me-2">${cVo.clasStatus}</span></td>
													</tr>
													<tr>
														<td class="icon-cell"><i class="bi bi-book"></i></td>
														<td style="width: 150px;">과목</td>
														<td><small class="text-muted fs-15">
																${cVo.subjVo[0].subjTitle}</small></td>
													</tr>
												</table>
											</div>
											<hr>
											<div class="class-detail-content">
												<h4 class="fs-20">클래스 소개</h4>
												<p>
													<i class="bi bi-arrow-return-right"></i> ${cVo.clasContent}
												</p>
											</div>
											<hr>
											<div class="class-detail-content">
												<h4 class="fs-20">진행 지역</h4>
												<p>
													<i class="bi bi-arrow-return-right"></i> ${cVo.clasLocation}
												</p>
											</div>
											<hr>
											<div class="class-detail-content">
												<h4 class="fs-20">예정 수업 일</h4>
												<p>
													<i class="bi bi-arrow-return-right"></i> ${cVo.clasSueopNaljja}
												</p>
											</div>
											<hr>
											<div class="class-detail-content">
												<h4 class="fs-20">인원 - 현재 인원 / 희망 인원</h4>
												<p>
													<i class="bi bi-arrow-return-right"></i> ${cVo.clasHyeonjaeInwon}/${cVo.clasHuimangInwon} 명
												</p>
											</div>
											<hr>
											<div class="class-detail-content">
												<h4 class="fs-20">희망 수강료</h4>
												<c:choose>
													<c:when test="${cVo.clasChoidaeSugangnyo==0}">
														<p>
															<i class="bi bi-arrow-return-right">최소 수강료 : </i> 희망 최소 수강료를 설정하지 않았습니다
														</p>
													</c:when>
													<c:otherwise>
														<p>
													    	<i class="bi bi-arrow-return-right">최소 수강료 : </i> ${cVo.clasChoisoSugangnyo}
														</p>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${cVo.clasChoidaeSugangnyo==0}">
														&nbsp;&nbsp;&nbsp;&nbsp;최대 수강료 : 희망 최대 수강료를 설정하지 않았습니다
													</c:when>
													<c:otherwise>
													    &nbsp;&nbsp;&nbsp;&nbsp;최대 수강료 : ${cVo.clasChoidaeSugangnyo}
													</c:otherwise>
												</c:choose>
											</div>
											<hr>
											<div class="class-detail-content">
												<h4 class="fs-20">제한 사항</h4>
												<c:choose>
													<c:when test="${cVo.clasSeongbyeolJehan == 'MONLY'}">
														<p>
															<i class="bi bi-arrow-return-right">성별 제한사항 : </i> 남자만
														</p>
													</c:when>
													<c:when test="${cVo.clasSeongbyeolJehan == 'FONLY'}">
														<p>
															<i class="bi bi-arrow-return-right">성별 제한사항 : </i> 여자만
														</p>
													</c:when>
													<c:otherwise>
														<p>
													    	<i class="bi bi-arrow-return-right">성별 제한사항 : </i> 성별 무관
														</p>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty cVo.clasNaiJehan}">
														<p>
															<i class="bi bi-arrow-return-right">연령 제한사항 : </i> 연령 무관
														</p>
													</c:when>
													<c:otherwise>
														<p>
															<c:set var="ageLimits" value="${fn:split(cVo.clasNaiJehan, ':')}" />
															<c:set var="minAge" value="${ageLimits[0]}" />
															<c:set var="maxAge" value="${ageLimits[1]}" />
															<i class="bi bi-arrow-return-right">연령 제한사항 : </i> ${minAge}세 이상 ${maxAge}세 이하
														</p>
													</c:otherwise>
												</c:choose>
											</div>
											<hr>
										</div>
									</div>
								</div>
							</div>
							<!-- end-->

							<!-- right sidebar -->
							<div class="email-leftbar col-md-3 col-lg-3"
								style="margin-left: 16px; margin-right: 16px;">
								<div class="card">
									<div class="card-body">
										<a href="./myClass.do?clasId=${cVo.clasId}" id="joinButton"
											class="btn btn-secondary waves-effect" style="width: 250px;">참여하기</a>

										<h5 class="mt-3 fs-15 text-uppercase">클래스 요약</h5>

										<div class="card p-0 overflow-hidden mt-3 shadow-none">
											<div class="mail-list">
												<a class="border-bottom">
													<span class="mdi mdi-arrow-right-drop-circle text-black"></span>
													제한 사항 
													<c:choose>
														<c:when test="${cVo.clasSeongbyeolJehan == 'MONLY'}">
															<p>
																<i class="bi bi-arrow-return-right">성별 제한사항 : </i> 남자만
															</p>
														</c:when>
														<c:when test="${cVo.clasSeongbyeolJehan == 'FONLY'}">
															<p>
																<i class="bi bi-arrow-return-right">성별 제한사항 : </i> 여자만
															</p>
														</c:when>
														<c:otherwise>
															<p>
														    	<i class="bi bi-arrow-return-right">성별 제한사항 : </i> 성별 무관
															</p>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${empty cVo.clasNaiJehan}">
															<p>
																<i class="bi bi-arrow-return-right">연령 제한사항 : </i> 연령 무관
															</p>
														</c:when>
														<c:otherwise>
															<p>
																<c:set var="ageLimits" value="${fn:split(cVo.clasNaiJehan, ':')}" />
																<c:set var="minAge" value="${ageLimits[0]}" />
																<c:set var="maxAge" value="${ageLimits[1]}" />
																<i class="bi bi-arrow-return-right">연령 제한사항 : </i> ${minAge}세 이상 ${maxAge}세 이하
															</p>
														</c:otherwise>
													</c:choose>
												</a> 
												<a class="border-bottom">
													<span class="mdi mdi-arrow-right-drop-circle text-black"></span> 
													평균 연령 : 구현해야됨
												</a>
												<a class="border-bottom">
													<span class="mdi mdi-arrow-right-drop-circle text-black"></span>
													수업 날짜 : ${cVo.clasSueopNaljja}
												</a>
												<a class="border-bottom">
													<span class="mdi mdi-arrow-right-drop-circle text-black"></span>
													위치 : ${cVo.clasLocation} 
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- End right sidebar -->
						</div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
			<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
			<script>
				var userGender = "${chamyeoVo.userVo[0].userGender}";
				var genJehan = "${cVo.clasSeongbyeolJehan}";
				var userAge = "${chamyeoVo.userVo[0].userBirth}";
				var naiJehan = "${cVo.clasNaiJehan}";
				var isSession = "${not empty chamyeoVo}" === "true" ? true : false;
			</script>
			<script type="text/javascript" src="./js/classDetail.js"></script>
		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp"%>
</body>
</html>