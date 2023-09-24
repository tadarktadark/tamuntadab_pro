<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	charset="UTF-8"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script
	src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- rater-js plugin -->
<script src="./assets/libs/rater-js/index.js"></script>
<!-- rating init -->
<script src="./assets/js/pages/rating.init.js"></script>
<!-- simplebarjs -->
<script src="./assets/libs/simplebar/simplebar.min.js"></script>
<script src="./js/myReview.js"></script>
<style type="text/css">
#moreList::-webkit-scrollbar {
    width: 10px;
}

#moreList::-webkit-scrollbar-track {
    background: #f1f1f1; 
}
 
#moreList::-webkit-scrollbar-thumb {
    background: #888; 
}

#moreList::-webkit-scrollbar-thumb:hover {
    background: #555; 
}
</style>
</head>
<div class="bg-light" style="padding:10px;">
				<input type="hidden" id="userAccountId"
					value="${userInfo.userAccountId}">
				<div id="review">
					<div id="reviewContent">
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