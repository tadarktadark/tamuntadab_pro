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
		</div>
	</div>
	<%@ include file="./shared/_footer.jsp"%>
</body>
</html>