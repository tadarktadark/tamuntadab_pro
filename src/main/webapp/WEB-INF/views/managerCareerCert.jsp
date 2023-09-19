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
<script src="./js/managerCert.js"></script>
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
				<div style="width: 90%; margin: 10% auto;">
					<c:choose>
						<c:when test="${empty lists}">
							<div class="alert alert-warning" role="alert"
								style="text-align: center;">
								<strong> 요청한 내역이 없습니다 </strong>
							</div>
						</c:when>
						<c:otherwise>
							<div
								class="accordion custom-accordionwithicon custom-accordion-border accordion-border-box accordion-secondary"
								id="accordionBordered">
								<c:forEach var="career" items="${lists}" varStatus="vs">
									<div class="accordion-item mt-2">
										<h2 class="accordion-header"
											id="accordionborderedExample${vs.index+1}">
											<button class="accordion-button collapsed" type="button"
												data-bs-toggle="collapse"
												data-bs-target="#accor_borderedExamplecollapse${vs.index+1}"
												aria-expanded="false"
												aria-controls="accor_borderedExamplecollapse${vs.index+1}">
												<c:choose>
													<c:when test="${career.careStatus eq 'R'}">
														<span class="badge bg-warning">반려</span>
													</c:when>
													<c:when test="${career.careStatus eq 'S'}">
														<span class="badge bg-info">승인</span>
													</c:when>
												</c:choose>
												&nbsp;요청자 ID: ${career.careAccountId}
											</button>
											<input type="hidden" id="userAccountId"
												value="${career.careAccountId}"> <input
												type="hidden" id="careId" value="${career.careId}">
										</h2>
										<div id="accor_borderedExamplecollapse${vs.index+1}"
											class="accordion-collapse collapse"
											aria-labelledby="accordionborderedExample${vs.index+1}"
											data-bs-parent="#accordionBordered">
											<div class="accordion-body">
												<div class="table-responsive">
													<table class="table table-striped"
														style="height: 100px; overflow-y: auto;">
														<thead>
															<tr style="text-align: center;">
																<th scope="col">회사명</th>
																<th scope="col">발급담당자명</th>
																<th scope="col">발급담당자연락처</th>
																<th scope="col">성명</th>
																<th scope="col">연락처</th>
																<th scope="col">소속</th>
																<th scope="col">직위</th>
																<th scope="col">재직기간</th>
																<th scope="col">담당업무</th>
																<th scope="col">승인</th>
																<th scope="col">반려</th>
																<th scope="col">삭제</th>
																<th scope="col">파일보기</th>
																<th scope="col">수정하기</th>
															</tr>
														</thead>
														<tbody>
															<tr style="text-align: center; vertical-align: middle;">
																<td>${career.careCompany}</td>
																<td>${career.careIssuer}</td>
																<td>${career.careIssuerContact}</td>
																<td>${career.careName}</td>
																<td>${career.careContact}</td>
																<td>${career.careSosok}</td>
																<td>${career.carePosition}</td>
																<td>${career.carePeriod}</td>
																<td>${career.careJob}</td>
																<c:choose>
																	<c:when test="${career.careStatus eq 'N'}">
																		<td><button type="button"
																				class="btn btn-success w-xs update-button"
																				onclick="updateS()">승인</button></td>
																		<td><button type="button"
																				class="btn btn-warning w-xs" onclick="updateB()">반려</button></td>
																		<td>-</td>
																	</c:when>
																	<c:when test="${career.careStatus eq 'S'}">
																		<td>-</td>
																		<td>-</td>
																		<td><button type="button"
																				class="btn btn-danger w-xs" onclick="updateD()">목록삭제</button></td>
																	</c:when>
																	<c:when test="${career.careStatus eq 'R'}">
																		<td>-</td>
																		<td>-</td>
																		<td><button type="button"
																				class="btn btn-danger w-xs" onclick="deleteDB()">DB삭제</button></td>
																	</c:when>
																</c:choose>
																<td>
																	<button type="button" class="btn btn-info w-xs"
																		onclick="location.href='./downloadPdf.do?careId=${career.careId}'">
																		<i class="mdi mdi-tray-arrow-down"></i> 다운로드
																	</button>
																</td>
																<td>
																	<button type="button" class="btn btn-secondary w-xs"
																		onclick="updateContent()">수정</button>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<ul
								class="pagination pagination-rounded justify-content-center mt-4">
								<c:choose>
									<c:when test="${page.page > 1}">
										<li class="page-item"><a
											href="./managerCareer.do?page=${page.endPage-page.countPage}"
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
												href="./managerCareer.do?page=${i}" class="page-link">${i}</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a
												href="./managerCareer.do?page=${i}" class="page-link">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${page.page<page.totalPage}">
										<li class="page-item"><a
											href="./managerCareer.do?page=${page.page + 1}"
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
				<!-- Varying modal content -->
				<div class="modal fade" id="varyingcontentModal" tabindex="-1"
					aria-labelledby="varyingcontentModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="varyingcontentModalLabel">New
									message</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form>
									<div class="mb-3">
										<label for="recipient-name" class="col-form-label">Recipient:</label>
										<input type="text" class="form-control" id="recipient-name">
									</div>
									<div class="mb-3">
										<label for="message-text" class="col-form-label">Message:</label>
										<textarea class="form-control" id="message-text"></textarea>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-light"
									data-bs-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Send
									message</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>
</body>
</html>