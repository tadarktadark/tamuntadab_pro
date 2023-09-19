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
</head>
				<div style="width: 800px; margin: 10% auto;">
					<c:choose>
						<c:when test="${empty lists}">
							<div class="alert alert-warning" role="alert"
								style="text-align: center;">
								<strong> 요청한 내역이 없습니다 </strong>
							</div>
						</c:when>
						<c:otherwise>
							<table class="table table-striped"
								style="height: 300px; overflow-y: auto;">
								<thead>
									<tr style="text-align: center;">
										<th scope="col">회사명</th>
										<th scope="col">소속</th>
										<th scope="col">직무</th>
										<th scope="col">상태</th>
										<th scope="col">상태변경일</th>
										<th scope="col">반려사유</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="career" items="${lists}" varStatus="vs">
										<tr style="text-align: center; vertical-align: middle;">
											<td>${career.careCompany}</td>
											<td>${career.careSosok}</td>
											<td>${career.careJob}</td>
											<c:choose>
												<c:when test="${career.careStatus eq 'N'}">
													<td><span class="badge bg-warning">승인대기중</span></td>
												</c:when>
												<c:when test="${career.careStatus eq 'S'}">
													<td><span class="badge bg-success">승인</span></td>
												</c:when>
												<c:otherwise>
													<td><span class="badge bg-danger">반려</span></td>
												</c:otherwise>
											</c:choose>
											<td>${career.careStatusDate}</td>
											<c:choose>
												<c:when test="${not empty career.careReason}">
													<td>
														<button type="button" class="btn btn-info reasonButton"
														style="padding: 2px 6px; font-size: 12px; line-height: 1;"
															data-bs-toggle="modal" data-bs-target="#zoomInModal"
															data-reason="${career.careReason}">사유 보기</button>
													</td>
												</c:when>
												<c:otherwise>
													<td>-</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- Pagination Rounded -->
							<ul class="pagination pagination-rounded justify-content-center">
								<c:choose>
									<c:when test="${page.page > 1}">
										<li class="page-item"><a
											href="./mypage.do?page=${page.endPage-page.countPage}"
											class="page-link"> <i class="mdi mdi-chevron-left"></i>
										</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item disabled"><a href="#"
											class="page-link"> <i class="mdi mdi-chevron-left"></i>
										</a></li>
									</c:otherwise>
								</c:choose>
								<c:forEach var="i" begin="${page.startPage}"
									end="${page.endPage}">
									<c:choose>
										<c:when test="${i == page.page}">
											<li class="page-item active"><a
												href="./mypage.do?page=${i}" class="page-link">${i}</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a
												href="./mypage.do?page=${i}" class="page-link">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${page.page<page.totalPage}">
										<li class="page-item"><a
											href="./mypage.do?page=${page.page + 1}"
											class="page-link"> <i class="mdi mdi-chevron-right"></i></a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item disabled"><a href="#"
											class="page-link"> <i class="mdi mdi-chevron-right"></i></a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div id="zoomInModal" class="modal fade zoomIn" tabindex="-1"
					aria-labelledby="zoomInModalLabel" aria-hidden="true"
					style="display: none;">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="zoomInModalLabel">반려 사유</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<h5 class="fs-16"></h5>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-light"
									data-bs-dismiss="modal">닫기</button>
								<button type="button" class="btn btn-primary " onclick="location.href='./instrCareer.do'">재등록</button>
							</div>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
</body>
<script type="text/javascript">
$(document).ready(function() {
    $('#zoomInModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var reason = button.data('reason'); // Extract info from data-* attributes
        var modal = $(this);
        modal.find('.modal-body h5').text(reason);
    });
});
</script>
</html>