<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_logout.jsp" %>
<%@ include file="./shared/_head_css.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="./assets/libs/fullcalendar/main.min.css" rel="stylesheet"
	type="text/css" />
<script>
    function openTupyo() {
        var xPos = (document.body.offsetWidth/2) - (500/2);
        xPos += window.screenLeft;
        var yPos = (document.body.offsetHeight/2) - (500/2);
        var userId = $('input[name="accountId"]').val();
        var clasId = $('input[name="clasId"]').val();
        console.log(userId,clasId);
        window.open("./tupyoPage.do?clasId="+clasId+"&accountId="+userId, "_blank", "left="+xPos+",top="+yPos+",width=500,height=500");
    }
</script>
<style type="text/css">
.fixed-width-icon {
	width: 40px;
	text-align: center;
	margin-right: 10px;
}

.btn-soft-secondary {
	margin-bottom: 8px;
}

.scrollable-content {
	max-height: 300px;
	overflow-y: auto;
}
</style>
</head>
<body class="twocolumn-panel">
	<input name="accountId" type="hidden" value="${userInfo.userAccountId}">
	<input name="clasId" type="hidden" value="${param.clasId}">
	<!-- 지우지마 형 -->
	<div id="layout-wrapper">
	
	<script>
    var userInfo = "${userInfo}";
    if (!userInfo || userInfo === null || userInfo === "") {
        // 경고 메시지를 표시합니다.
        alert("비정상적인 접근입니다. \n로그인 후 이용해주세요");
        window.location.href = "./classList.do";
    }
	</script>
	<c:if test="${param.dealSugangryo == 'true'}">
	  <script>
	    Swal.fire(
	      '성공!',
	      '수강료 요청이 전달 되었습니다.',
	      'success'
	    )
	  </script>
	</c:if>
	<c:if test="${param.dealSugangryo == 'false'}">
	  <script>
	    Swal.fire(
	      '오류!',
	      '수강료 요청 작업 중 오류가 발생했습니다.',
	      'error'
	    )
	  </script>
	</c:if>
		<%@ include file="./shared/_menu.jsp" %>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp"%>
					<div class="row">
						<div class="col-12">
							<div class="row">
								<div class="col-xl-4">
									<div class="card card-h-100">
										<div class="card-body">
											
											<c:if test="${matchedChamyeoVo.clchYeokal ne 'I' and classVo.clasStatus eq '매칭'}">
											    <button class="btn btn-soft-secondary w-100" onclick="openTupyo()">
											        <i class="bi bi-person-check-fill fixed-width-icon"></i> 강사 투표하기
											    </button>
											</c:if>
											
											<c:if test="${matchedChamyeoVo.clchYeokal eq 'M' and classVo.clasStatus eq '매칭완료'}">
											    <button class="btn btn-soft-secondary w-100" data-bs-toggle="modal" data-bs-target="#dealSugangryo">
											        <i class="bi bi-person-check-fill fixed-width-icon"></i> 수강료 확정 요청하기
											    </button>
											</c:if>
											
											<c:if test="${matchedChamyeoVo.clchYeokal eq 'I' and sugangryoVo ne null and classVo.clasStatus eq '매칭완료'}">
											    <button class="btn btn-soft-secondary w-100" onclick="">
											        <i class="bi bi-person-check-fill fixed-width-icon"></i> 수강료 확정하기
											    </button>
											</c:if>
											
											<c:if test="${matchedChamyeoVo.clchYeokal ne 'I' and sugangryoVo.sugaYocheongStatus eq 'A' and classVo.clasStatus eq '매칭완료'}">
											    <button class="btn btn-soft-secondary w-100" onclick="location.href='./payment.do'">
											        <i class="bi bi-person-check-fill fixed-width-icon"></i> 수강료 결제하기
											    </button>
											</c:if>
											
											<c:if test="${matchedChamyeoVo.clchYeokal eq 'M' and chamyeoList.size() >= 2}">
											    <button class="btn btn-soft-secondary w-100" onclick="">
											        <i class="bi bi-person-check-fill fixed-width-icon"></i> 클래스장 권한 위임 (아직)
											    </button>
											</c:if>
											
											<button class="btn btn-soft-secondary w-100"
												onclick="location.href='./classChatRoom.do?clasId=${param.clasId}'">
												<i class="bi bi-person-check-fill fixed-width-icon"></i> 채팅방 이동
											</button>
											
											<c:if test="${matchedChamyeoVo.clchYeokal ne 'I'}">
											<button class="btn btn-soft-secondary w-100" data-bs-toggle="modal" data-bs-target="#cancel">
												<i class="bi bi-person-check-fill fixed-width-icon"></i> 클래스 취소
											</button>
											</c:if>
											
											<!-- 수강료 확정 모달 -->
											<!-- Varying modal content -->
											<div class="modal fade" id="dealSugangryo" tabindex="-1" aria-labelledby="varyingcontentModalLabel" aria-hidden="true">
											    <div class="modal-dialog">
											        <div class="modal-content">
											            <div class="modal-header">
											                <h5 class="modal-title" id="varyingcontentModalLabel">수강료 확정 요청하기</h5>
											                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											            </div>
											            <div class="modal-body">
											                <!-- form 태그에 action과 method 속성을 추가합니다. -->
											                <form action="./dealSugangryo.do" method="post">
											                    <div class="mb-3">
											                        <label for="recipient-name" class="col-form-label">기존 요청 금액</label>
											                        <input type="text" class="form-control" id="recipient-name" readonly="readonly" value="${empty sugangryoVo || sugangryoVo.sugaYocheongStatus == '' ? '요청된 값 없음' : sugangryoVo.sugaYocheongGeumaek}">
											                    </div>
											                    <div class="mb-3">
											                        <label for="message-text" class="col-form-label">요청할 금액</label>
											                        <!-- 입력 필드에 name 속성을 추가하여 서버에서 값을 식별할 수 있도록 합니다. -->
											                        <input type="number" class="form-control" id="sugangryo" name="sugangryo">
											                    </div>
													            <div class="modal-footer">
													                <button type="button" class="btn btn-light" data-bs-dismiss="modal">취소</button>
													                <button type="submit" class="btn btn-secondary">요청</button>
													            </div>
											            	</form>
											            </div>
											        </div>
											    </div>
											</div>
											
											<!-- 클래스 취소 확인 모달 -->
											<div class="modal fade" id="cancel" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
											    <div class="modal-dialog modal-dialog-centered" role="document">
											        <div class="modal-content">
											            <div class="modal-body text-center p-5">
											                <lord-icon
											                    src="https://cdn.lordicon.com/tdrtiskw.json"
											                    trigger="loop"
											                    colors="primary:#f7b84b,secondary:#405189"
											                    style="width:130px;height:130px">
											                </lord-icon>
											                
											                <div class="mt-4">
											                    <h4 class="mb-3">해당 클래스 참여를 취소합니다</h4>
											                    <p class="text-muted mb-4">클래스 취소시, 이미 결제된 수강료는 환불되지 않습니다. 취소하시겠습니까?</p>
											                    <div class="hstack gap-2 justify-content-center">
											                        <a href="javascript:void(0);" class="btn btn-link link-success fw-medium" data-bs-dismiss="modal"><i class="ri-close-line me-1 align-middle"></i> 아니오</a>
											                        <a href="./cancelClass.do?clasId=${param.clasId}" class="btn btn-warning">네. 취소합니다</a>
											                    </div>
											                </div>
											            </div>
											        </div>
											    </div>
											</div>
											<div id="external-events">
												<br>
												<p class="text-muted">달력 라벨</p>
												<div
													class="external-event fc-event bg-danger-subtle  text-danger"
													data-class="bg-success-subtle " style="cursor:auto;">
													<i class="mdi mdi-checkbox-blank-circle font-size-11 me-2"></i>
													이 클래스의 일정
												</div>
												<div
													class="external-event fc-event bg-info-subtle  text-info"
													data-class="bg-info-subtle " style="cursor:auto;">
													<i class="mdi mdi-checkbox-blank-circle font-size-11 me-2"></i>
													다른 모든 클래스의 일정
												</div>
												<div
													class="external-event fc-event bg-warning-subtle  text-warning"
													data-class="bg-warning-subtle " style="cursor:auto;">
													<i class="mdi mdi-checkbox-blank-circle font-size-11 me-2"></i>
													강의실 예약 일정
												</div>
											</div>
										</div>
									</div>

									<div class="card">
										<div class="card-body bg-success-subtle ">
											<div class="d-flex">
												<div class="flex-shrink-0">
													<i data-feather="calendar"
														class="text-success icon-dual-success"></i>
												</div>
												<div class="flex-grow-1 ms-3">
													<h6 class="fs-15">클래스 상세 정보</h6>
													<p class="text-muted mb-0 fs-12">
														진행 지역 : ${classVo.clasLocation} <br>
														클래스 수업일 : ${classVo.clasSueopNaljja} <br>
														인원 : ${classVo.clasHyeonjaeInwon} / ${classVo.clasHuimangInwon} <br>
														클래스 개설일 : ${classVo.clasRegdate} <br>
														<c:choose>
														    <c:when test="${classVo.clasSeongbyeolJehan eq 'GFREE'}">
														        클래스 성별제한 : 제한 없음
														    </c:when>
														    <c:when test="${classVo.clasSeongbyeolJehan eq 'FONLY'}">
														        클래스 성별제한 : 여자만
														    </c:when>
														    <c:when test="${classVo.clasSeongbyeolJehan eq 'MONLY'}">
														        클래스 성별제한 : 남자만
														    </c:when>
														    <c:otherwise>
														        클래스 성별제한 : 알 수 없음
														    </c:otherwise>
														</c:choose> <br>
														클래스 모집 마감일 : ${classVo.clasMagamGihan} <br>
														모집연장 가능횟수 : ${classVo.clasMojipYeonjangHoitsu}
													</p>
												</div>
											</div>
										</div>
									</div>

									<div>
										<h5 class="mb-1">다가오는 일정</h5>
										<p class="text-muted">
											예정된 클래스 일정을 확인 하세요<i class="ri-user-smile-line"></i>
										</p>
										<div class="pe-2 me-n1 mb-3 simplebar-scrollable-y"
											data-simplebar="init" style="height: 400px">
											<div class="simplebar-wrapper"
												style="margin: 0px -8px 0px 0px;">
												<div class="simplebar-height-auto-observer-wrapper">
													<div class="simplebar-height-auto-observer"></div>
												</div>
												<div class="simplebar-mask">
													<div class="simplebar-offset"
														style="right: 0px; bottom: 0px;">
														<div class="simplebar-content-wrapper" tabindex="0"
															role="region" aria-label="scrollable content"
															style="height: 100%; overflow: hidden scroll;">
															<div class="simplebar-content"
																style="padding: 0px 8px 0px 0px;">
																<div id="upcoming-event-list">
																	<div class="card mb-3">
																		<div class="card-body">
																			<div class="d-flex mb-3">
																				<div class="flex-grow-1">
																					<i
																						class="mdi mdi-checkbox-blank-circle me-2 text-subtle "></i>
																					<span class="fw-medium">4 Jan 2022 </span>
																				</div>
																				<div class="flex-shrink-0">
																					<small
																						class="badge bg-primary-subtle text-primary  ms-auto">9:00
																						AM to 12:00 AM</small>
																				</div>
																			</div>
																			<h6 class="card-title fs-16">World Braille Day</h6>
																			<p class="text-muted text-truncate-two-lines mb-0">
																			</p>
																		</div>
																	</div>
																	<div class="card mb-3">
																		<div class="card-body">
																			<div class="d-flex mb-3">
																				<div class="flex-grow-1">
																					<i
																						class="mdi mdi-checkbox-blank-circle me-2 text-subtle "></i>
																					<span class="fw-medium">30 Jan 2022 </span>
																				</div>
																				<div class="flex-shrink-0">
																					<small
																						class="badge bg-primary-subtle text-primary  ms-auto">9:00
																						AM to 12:00 AM</small>
																				</div>
																			</div>
																			<h6 class="card-title fs-16">World Leprosy Day</h6>
																			<p class="text-muted text-truncate-two-lines mb-0">
																			</p>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="simplebar-placeholder"
													style="width: 293px; height: 2744px;"></div>
											</div>
											<div class="simplebar-track simplebar-horizontal"
												style="visibility: hidden;">
												<div class="simplebar-scrollbar"
													style="width: 0px; display: none;"></div>
											</div>
											<div class="simplebar-track simplebar-vertical"
												style="visibility: visible;">
												<div class="simplebar-scrollbar"
													style="height: 58px; display: block; transform: translate3d(0px, 0px, 0px);"></div>
											</div>
										</div> 
									</div><!-- simple bar의 끝 -->
								</div><!-- 왼쪽 사이드바의 끝-->

								<div class="col-xl-8"> <!-- 오른쪽 사이드바의 시작-->
									<div class="card"> <!-- 달력 윗쪽 카드-->
										
										<div class="card-header">
		                                    <h5 class="card-title mb-0">
			                                    <span class="fs-15 m-0">${classVo.clasTitle} </span>
			                                    <span class="badge bg-success-subtle text-success"> ${classVo.clasStatus}</span>
		                                    </h5>
		                                </div>
		                                <div class="card-body">
		                                	<h6 class="card-title">배정된 강사 : 
											    <span class="card-text text-muted mb-0">
											        <c:choose>
											            <c:when test="${empty clasVo.clasAccountId}">
											                배정된 강사 없음
											            </c:when>
											            <c:otherwise>
											                <c:forEach var="participant" items="${chamyeoList}" varStatus="status">
											                    <c:if test="${participant.clchAccountId == clasVo.clasAccountId}">
											                        ${not empty participant.userVo[0].userNickname ? participant.userVo[0].userNickname : '전민균'}
											                    </c:if>
											                </c:forEach>
											            </c:otherwise>
											        </c:choose>
											    </span>
											</h6>
                                            <h6 class="card-title">과목 : <span class="card-text text-muted mb-0">${classVo.subjVo[0].subjTitle}</span></h6>
                                            <br>
                                            <h6 class="card-title">클래스 내용 </h6>
                                            <p class="card-text text-muted mb-0">
                                            	${classVo.clasContent}
                                            </p>
                                        </div>
		                                <div class="card p-0 overflow-hidden mt-3 mb-1 shadow-none" style="margin:16px;">
										    <div class="card-header">
										        <h5 class="card-title mb-0">참여 중인 클래스원</h5>
										    </div>
										    <div class="card-body pt-2">
										        <div class="table-responsive">
										            <table class="table align-middle table-nowrap mb-1">
										                <tbody>
										                    <!-- Display participants where clchYeokal is M first -->
										                    <c:forEach var="participant" items="${chamyeoList}" varStatus="status">
										                        <c:if test="${participant.clchYeokal != 'I' && participant.clchYeokal == 'M'}">
										                            <tr>
										                                <td style="width: 50px;"><img src="${not empty participant.userVo[0].userProfileFile ? participant.userVo[0].userProfileFile : './image/profile.png'}" class="rounded-circle avatar-xs"></td>
										                                <td><h5 class="fs-15 m-0"><i class="ri-vip-crown-2-line">&nbsp;</i><a href="javascript: void(0);" class="text-body">${not empty participant.userVo[0].userNickname ? participant.userVo[0].userNickname : 'N/A'}</a></h5></td>
										                                <td>${not empty participant.userVo[0].userGender ? (participant.userVo[0].userGender == 'M' ? '남자' : '여자') : 'N/A'}</td>
										                                <td>${not empty participant.userVo[0].userBirth ? participant.userVo[0].userBirth : 'N/A'}세</td>
										                                <td>
										                                    <c:choose>
										                                        <c:when test="${participant.clchGyeoljeStatus == 'Y'}">
										                                            <span class="badge bg-success-subtle text-success">결제 완료</span>
										                                        </c:when>
										                                        <c:otherwise>
										                                            <span class="badge bg-warning-subtle text-warning">결제 미완료</span>
										                                        </c:otherwise>
										                                    </c:choose>
										                                </td>
										                            </tr>
										                        </c:if>
										                    </c:forEach>
										                    <c:forEach var="participant" items="${chamyeoList}" varStatus="status">
										                        <c:if test="${participant.clchYeokal != 'I' && participant.clchYeokal != 'M'}">
										                            <tr>
										                                <td style="width: 50px;"><img src="${not empty participant.userVo[0].userProfileFile ? participant.userVo[0].userProfileFile : './image/profile.png'}" class="rounded-circle avatar-xs"></td>
										                                <td><h5 class="fs-15 m-0"><a href="javascript: void(0);" class="text-body">${not empty participant.userVo[0].userNickname ? participant.userVo[0].userNickname : 'N/A'}</a></h5></td>
										                                <td>${not empty participant.userVo[0].userGender ? (participant.userVo[0].userGender == 'M' ? '남자' : '여자') : 'N/A'}</td>
										                                <td>${not empty participant.userVo[0].userBirth ? participant.userVo[0].userBirth : 'N/A'}세</td>
										                                <td>
										                                    <c:choose>
										                                        <c:when test="${participant.clchGyeoljeStatus == 'Y'}">
										                                            <span class="badge bg-success-subtle text-success">결제 완료</span>
										                                        </c:when>
										                                        <c:otherwise>
										                                            <span class="badge bg-warning-subtle text-warning">결제 미완료</span>
										                                        </c:otherwise>
										                                    </c:choose>
										                                </td>
										                            </tr>
										                        </c:if>
										                    </c:forEach>
										                </tbody>
										            </table>
										        </div>
										    </div>
										</div>
		                            </div>
									<div class="card card-h-100"> <!-- 캘린더 카드 -->
										<div class="card-body">
											<div id="calendar"
												class="fc fc-media-screen fc-direction-ltr fc-theme-bootstrap">
												<div class="fc-header-toolbar fc-toolbar fc-toolbar-ltr">
													<div class="fc-toolbar-chunk">
														<div class="btn-group">
															<button type="button" title="Previous month"
																aria-pressed="false"
																class="fc-prev-button btn btn-primary">
																<span class="fa fa-chevron-left"></span>
															</button>
															<button type="button" title="Next month"
																aria-pressed="false"
																class="fc-next-button btn btn-primary">
																<span class="fa fa-chevron-right"></span>
															</button>
														</div>
														<button type="button" title="This month"
															aria-pressed="false"
															class="fc-today-button btn btn-primary">today</button>
													</div>
													<div class="fc-toolbar-chunk">
														<h2 class="fc-toolbar-title" id="fc-dom-1">September
															2023</h2>
													</div>
													<div class="fc-toolbar-chunk">
														<div class="btn-group">
															<button type="button" title="month view"
																aria-pressed="true"
																class="fc-dayGridMonth-button btn btn-primary active">month</button>
															<button type="button" title="week view"
																aria-pressed="false"
																class="fc-timeGridWeek-button btn btn-primary">week</button>
															<button type="button" title="day view"
																aria-pressed="false"
																class="fc-timeGridDay-button btn btn-primary">day</button>
															<button type="button" title="list view"
																aria-pressed="false"
																class="fc-listMonth-button btn btn-primary">list</button>
														</div>
													</div>
												</div>
												<div aria-labelledby="fc-dom-1"
													class="fc-view-harness fc-view-harness-active"
													style="height: 655.556px;">
													<div class="fc-daygrid fc-dayGridMonth-view fc-view">
														<table role="grid"
															class="fc-scrollgrid table-bordered fc-scrollgrid-liquid">
															<thead role="rowgroup">
																<tr role="presentation"
																	class="fc-scrollgrid-section fc-scrollgrid-section-header ">
																	<th role="presentation"><div
																			class="fc-scroller-harness">
																			<div class="fc-scroller" style="overflow: hidden;">
																				<table role="presentation" class="fc-col-header "
																					style="width: 883px;">
																					<colgroup></colgroup>
																					<thead role="presentation">
																						<tr role="row">
																							<th role="columnheader"
																								class="fc-col-header-cell fc-day fc-day-sun"><div
																									class="fc-scrollgrid-sync-inner">
																									<a aria-label="Sunday"
																										class="fc-col-header-cell-cushion ">Sun</a>
																								</div></th>
																							<th role="columnheader"
																								class="fc-col-header-cell fc-day fc-day-mon"><div
																									class="fc-scrollgrid-sync-inner">
																									<a aria-label="Monday"
																										class="fc-col-header-cell-cushion ">Mon</a>
																								</div></th>
																							<th role="columnheader"
																								class="fc-col-header-cell fc-day fc-day-tue"><div
																									class="fc-scrollgrid-sync-inner">
																									<a aria-label="Tuesday"
																										class="fc-col-header-cell-cushion ">Tue</a>
																								</div></th>
																							<th role="columnheader"
																								class="fc-col-header-cell fc-day fc-day-wed"><div
																									class="fc-scrollgrid-sync-inner">
																									<a aria-label="Wednesday"
																										class="fc-col-header-cell-cushion ">Wed</a>
																								</div></th>
																							<th role="columnheader"
																								class="fc-col-header-cell fc-day fc-day-thu"><div
																									class="fc-scrollgrid-sync-inner">
																									<a aria-label="Thursday"
																										class="fc-col-header-cell-cushion ">Thu</a>
																								</div></th>
																							<th role="columnheader"
																								class="fc-col-header-cell fc-day fc-day-fri"><div
																									class="fc-scrollgrid-sync-inner">
																									<a aria-label="Friday"
																										class="fc-col-header-cell-cushion ">Fri</a>
																								</div></th>
																							<th role="columnheader"
																								class="fc-col-header-cell fc-day fc-day-sat"><div
																									class="fc-scrollgrid-sync-inner">
																									<a aria-label="Saturday"
																										class="fc-col-header-cell-cushion ">Sat</a>
																								</div></th>
																						</tr>
																					</thead>
																				</table>
																			</div>
																		</div></th>
																</tr>
															</thead>
															<tbody role="rowgroup">
																<tr role="presentation"
																	class="fc-scrollgrid-section fc-scrollgrid-section-body  fc-scrollgrid-section-liquid">
																	<td role="presentation"><div
																			class="fc-scroller-harness fc-scroller-harness-liquid">
																			<div class="fc-scroller fc-scroller-liquid-absolute"
																				style="overflow: hidden auto;">
																				<div
																					class="fc-daygrid-body fc-daygrid-body-unbalanced "
																					style="width: 883px;">
																					<table role="presentation"
																						class="fc-scrollgrid-sync-table"
																						style="width: 883px; height: 614px;">
																						<colgroup></colgroup>
																						<tbody role="presentation">
																							<tr role="row">
																							</tr>
																							<tr role="row">
																							</tr>
																							<tr role="row">
																							</tr>
																							<tr role="row">
																							</tr>
																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div></td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- end col -->
							</div>
							<!--end row-->

							<div style="clear: both"></div>

							<!-- Add New Event MODAL -->
							<div class="modal fade" id="event-modal" tabindex="-1"
								style="display: none;" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content border-0">
										<div class="modal-header p-3 bg-info-subtle ">
											<h5 class="modal-title" id="modal-title">Add Event</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-hidden="true"></button>
										</div>
										<div class="modal-body p-4">
											<form class="needs-validation" name="event-form"
												id="form-event">
												<div class="text-end" style="display: none;">
													<a href="#" class="btn btn-sm btn-soft-primary"
														id="edit-event-btn" data-id="new-event"
														onclick="editEvent(this)" role="button" hidden="true">Edit</a>
												</div>
												<div class="event-details">
													<div class="d-flex mb-2">
														<div class="flex-grow-1 d-flex align-items-center">
															<div class="flex-shrink-0 me-3">
                                                                <i class="ri-calendar-event-line text-muted fs-16"></i>
                                                            </div>
															<div class="flex-grow-1">
																<h6 class="d-none fw-semibold mb-0"
																	id="event-start-date-tag"></h6>
															</div>
														</div>
													</div>
													<div style="display: none;">
														<div class="d-flex align-items-center mb-2">
															<div class="flex-shrink-0 me-3">
																<i class="ri-time-line text-muted fs-16"></i>
															</div>
															<div class="flex-grow-1">
																<h6 class="d-block fw-semibold mb-0">
																	<span id="event-timepicker1-tag"></span> - <span
																		id="event-timepicker2-tag"></span>
																</h6>
															</div>
														</div>
													</div>
													<div class="d-flex align-items-center mb-2">
														<div class="flex-shrink-0 me-3">
															<i class="ri-map-pin-line text-muted fs-16"></i>
														</div>
														<div class="flex-grow-1">
															<h6 class="d-block fw-semibold mb-0">
																<span id="event-location-tag"></span>
															</h6>
														</div>
													</div>
													<div class="d-flex mb-3">
														<div class="flex-shrink-0 me-3">
															<i class="ri-discuss-line text-muted fs-16"></i>
														</div>
														<div class="flex-grow-1">
															<p class="d-none text-muted mb-0"
																id="event-description-tag"></p>
														</div>
													</div>
												</div>
												<div class="row event-form">
													<div class="col-12">
														<div class="mb-3">
															<label class="form-label">Type</label>
															<div class="choices" data-type="select-one" tabindex="0"
																role="listbox" aria-haspopup="true"
																aria-expanded="false">
																<div class="choices__inner">
																	<select class="form-select d-block choices__input"
																		name="category" id="event-category" hidden=""
																		tabindex="-1" data-choice="active"><option
																			value="bg-danger-subtle"
																			data-custom-properties="[object Object]">Danger</option></select>
																	<div class="choices__list choices__list--single">
																		<div class="choices__item choices__item--selectable"
																			data-item="" data-id="1"
																			data-value="bg-danger-subtle"
																			data-custom-properties="[object Object]"
																			aria-selected="true">Danger</div>
																	</div>
																</div>
																<div class="choices__list choices__list--dropdown"
																	aria-expanded="false">
																	<div class="choices__list" role="listbox">
																		<div id="choices--event-category-item-choice-1"
																			class="choices__item choices__item--choice is-selected choices__item--selectable is-highlighted"
																			role="option" data-choice="" data-id="1"
																			data-value="bg-danger-subtle "
																			data-select-text="Press to select"
																			data-choice-selectable="" aria-selected="true">Danger</div>
																		<div id="choices--event-category-item-choice-2"
																			class="choices__item choices__item--choice choices__item--selectable"
																			role="option" data-choice="" data-id="2"
																			data-value="bg-dark-subtle "
																			data-select-text="Press to select"
																			data-choice-selectable="">Dark</div>
																		<div id="choices--event-category-item-choice-3"
																			class="choices__item choices__item--choice choices__item--selectable"
																			role="option" data-choice="" data-id="3"
																			data-value="bg-info-subtle "
																			data-select-text="Press to select"
																			data-choice-selectable="">Info</div>
																		<div id="choices--event-category-item-choice-4"
																			class="choices__item choices__item--choice choices__item--selectable"
																			role="option" data-choice="" data-id="4"
																			data-value="bg-primary-subtle "
																			data-select-text="Press to select"
																			data-choice-selectable="">Primary</div>
																		<div id="choices--event-category-item-choice-5"
																			class="choices__item choices__item--choice choices__item--selectable"
																			role="option" data-choice="" data-id="5"
																			data-value="bg-success-subtle "
																			data-select-text="Press to select"
																			data-choice-selectable="">Success</div>
																		<div id="choices--event-category-item-choice-6"
																			class="choices__item choices__item--choice choices__item--selectable"
																			role="option" data-choice="" data-id="6"
																			data-value="bg-warning-subtle "
																			data-select-text="Press to select"
																			data-choice-selectable="">Warning</div>
																	</div>
																</div>
															</div>
															<div class="invalid-feedback">Please select a valid
																event category</div>
														</div>
													</div>
													<!--end col-->
													<div class="col-12">
														<div class="mb-3">
															<label class="form-label">Event Name</label> <input
																class="form-control d-block"
																placeholder="Enter event name" type="text" name="title"
																id="event-title" value="">
															<div class="invalid-feedback">Please provide a
																valid event name</div>
														</div>
													</div>
													<!--end col-->
													<div class="col-12">
														<div class="mb-3">
															<label class="form-label">Event Date</label>
															<div class="input-group">
																<input type="text" id="event-start-date"
																	class="form-control flatpickr flatpickr-input"
																	placeholder="Select date" readonly="readonly">
																<span class="input-group-text"><i
																	class="ri-calendar-event-line"></i></span>
															</div>
														</div>
													</div>
													<!--end col-->
													<div class="col-12" id="event-time">
														<div class="row">
															<div class="col-6">
																<div class="mb-3">
																	<label class="form-label">Start Time</label>
																	<div class="input-group">
																		<input id="timepicker1" type="text"
																			class="form-control flatpickr flatpickr-input"
																			placeholder="Select start time" readonly="readonly">
																		<span class="input-group-text"><i
																			class="ri-time-line"></i></span>
																	</div>
																</div>
															</div>
															<div class="col-6">
																<div class="mb-3">
																	<label class="form-label">End Time</label>
																	<div class="input-group">
																		<input id="timepicker2" type="text"
																			class="form-control flatpickr flatpickr-input"
																			placeholder="Select end time" readonly="readonly">
																		<span class="input-group-text"><i
																			class="ri-time-line"></i></span>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<!--end col-->
													<div class="col-12">
														<div class="mb-3">
															<label for="event-location" class="form-label">Location</label>
															<div>
																<input type="text" class="form-control d-block"
																	name="event-location" id="event-location"
																	placeholder="Event location">
															</div>
														</div>
													</div>
													<!--end col-->
													<input type="hidden" id="eventid" name="eventid" value="">
													<div class="col-12">
														<div class="mb-3">
															<label class="form-label">Description</label>
															<textarea class="form-control d-block"
																id="event-description" placeholder="Enter a description"
																rows="3" spellcheck="false"></textarea>
														</div>
													</div>
													<!--end col-->
												</div>
												<!--end row-->
												<div class="hstack gap-2 justify-content-end" style="display: none;">
													<button type="button" class="btn btn-soft-danger"
														id="btn-delete-event" hidden="true">
														<i class="ri-close-line align-bottom"></i> Delete
													</button>
													<button type="submit" class="btn btn-success"
														id="btn-save-event">Add Event</button>
												</div>
											</form>
										</div>
									</div>
									<!-- end modal-content-->
								</div>
								<!-- end modal dialog-->
							</div>
							<!-- end modal-->
							<!-- end modal-->
						</div>
					</div>



				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>
	
	<script src="./assets/libs/choices.js/public/assets/scripts/choices.min.js"></script>
	<script src="./assets/libs/fullcalendar/main.min.js"></script>
	<script src="./js/myClass.js"></script>
	<script src="./assets/js/pages/modal.init.js"></script>
	<script src="./assets/libs/flatpickr/flatpickr.min.js"></script>
	<input type="hidden" id="clasId" value="${param.clasId}" />
	<%@ include file="./shared/_vender_scripts.jsp" %>
</body>
	<style>
		.fc .fc-daygrid-day.fc-day-today .fc-daygrid-day-number{
			    background-color: var(--tb-secondary);
    			color: #fff;
		}
		.fc-event{
    			cursor:pointer;
    	}
	</style>
</html>