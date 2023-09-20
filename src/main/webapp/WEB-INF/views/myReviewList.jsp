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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script
	src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<%@ include file="./shared/_head_css.jsp"%>
<!-- rater-js plugin -->
<script src="./assets/libs/rater-js/index.js"></script>
<!-- rating init -->
<script src="./assets/js/pages/rating.init.js"></script>
<script src="./js/myReviewList.js"></script>
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
				<input type="hidden" id="userAccountId"
					value="${userInfo.userAccountId}">
				<div id="review" style="width: 40%; margin: auto 30%;">
					<div id="reviewContent">
						<!-- <div class="vstack gap-3">
							<div class="bg-light border p-1 px-2" style="height: 50px; display: flex; justify-content: space-between;">
								<div style="display:flex; align-items:center;">
								<input class="form-check-input" type="checkbox" value=""
									id="cardtableCheck"> &nbsp;전체 선택
								</div>
								<button type="button" class="btn btn-danger w-xs"
									onclick="#">
									<p>삭제</p>
								</button>
							</div>
						</div> -->
						<div id="moreList"
							style="width: auto; height: 700px; overflow: auto;">
							<c:forEach var="review" items="${lists}">
								<div class="card">
									<div class="card-body">
										<div>
											<button type="button" class="btn btn-outline-danger w-xs"
												onclick="deleteReview(${review.reviSeq}, ${review.chamyeoVo[0].clchId})">삭제</button>
										</div>
										<div class="row mt-4">
											<div class="col-7">
												<button type="button" class="btn btn-success w-sm"
													disabled="disabled">클래스</button>
												${review.classVo[0].clasTitle}
											</div>
											<div class="col-5">
												<button type="button" class="btn btn-success w-sm"
													disabled="disabled">작성일</button>
												${review.reviRegdate}
											</div>
										</div>
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
										<div class="row mt-4"
											style="background-color: #EAEAEA; padding: 20px; magin: auto 10px;">
											<div class="col-12 table-info">${review.reviDetail}</div>
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
</body>
<script id="review-template" type="text/x-handlebars-template">
	{{#each this}}
											<div class="card">
											<div class="card-body">
											<div>
												<button type="button" class="btn btn-outline-danger w-xs"
													onclick="deleteReview({{reviSeq}}, {{chamyeoVo.[0].clchId}})">삭제</button>
											</div>
											<div class="row mt-4">
											<div class="col-7">
												<button type="button" class="btn btn-success w-sm"
													disabled="disabled">클래스</button>
												{{classVo.[0].clasTitle}}
											</div>
											<div class="col-5">
												<button type="button" class="btn btn-success w-sm"
													disabled="disabled">작성일</button>
												{{review.reviRegdate}}
											</div>
										</div>
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
										<div class="row mt-4"
											style="background-color: #EAEAEA; padding: 20px; magin: auto 10px;">
											<div class="col-12 table-info">{{reviDetail}}</div>
										</div>
									</div>
								</div>
{{/each}}
</script>
</html>