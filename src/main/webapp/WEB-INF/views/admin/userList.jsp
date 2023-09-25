<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="vertical" data-topbar="light"
	data-sidebar="light" data-sidebar-size="lg" data-sidebar-image="none"
	data-preloader="disable" data-layout-style="default"
	data-bs-theme="light" data-layout-width="fluid">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<link href="../assets/libs/sweetalert2/sweetalert2.min.css"
	rel="stylesheet" type="text/css" />
<head>
<link href="../assets/libs/jsvectormap/css/jsvectormap.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/libs/swiper/swiper-bundle.min.css"
	rel="stylesheet" type="text/css" />
<%@ include file="./shared/_head_css.jsp"%>
<%@ include file="./shared/_vender_scripts.jsp"%>
</head>
<body>
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>
		<div class="vertical-overlay"></div>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<div class="row px-5">
						<div class="col-xl-12">
							<div class="card">
								<div class="card-body">
									<div class="row g-4 mb-3">
										<div class="col-sm-auto">
											<div>
												<button class="btn btn-soft-danger" onClick="deleteMultiple()">
													<i class="ri-delete-bin-2-line"></i>
												</button>
											</div>
										</div>
										<div class="col-sm">
											<div class="d-flex justify-content-sm-end">
												<div class="search-box ms-2">
													<input type="text" class="form-control search" placeholder="Search..."> 
													<i class="ri-search-line search-icon"></i>
												</div>
											</div>
										</div>
									</div>
									<div class="table-responsive table-card mt-3 mb-1">
										<table
											class="table table-hover table-nowrap align-middle mb-0">
											<thead class="table-light">
												<tr>
													<th scope="col" style="width: 50px;">
														<div class="form-check">
															<input class="form-check-input" type="checkbox"
																id="checkAll" value="option">
														</div>
													</th>
													<th class="sort" data-sort="id">ID</th>
													<th class="sort" data-sort="name">이름</th>
													<th class="sort" data-sort="date">최근 접속일</th>
													<th class="sort" data-sort="auth">관리자 권한</th>
													<th data-sort="action">동작</th>
												</tr>
											</thead>
											<tbody class="adminList">
											</tbody>
										</table>
										<!-- end table -->
										<div class="d-flex justify-content-center mt-2">
											<div class="pagination-wrap hstack gap-2">
												<ul class="pagination listjs-pagination mb-0">
												</ul>
											</div>
										</div>
									</div>
									<!-- end table responsive -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>