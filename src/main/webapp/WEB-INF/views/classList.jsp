<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>



		<div class="container-fluid">

			<div class="main-content">
				<div class="page-content">
					<div class="container-fluid">
						<%@ include file="./shared/_page_title.jsp"%>
					</div>
				</div>
				<%@ include file="./shared/_footer.jsp"%>
			</div>


			<div class="row pb-4 gy-3">
				<div class="col-sm-4">
					<button class="btn btn-primary addMembers-modal"
						data-bs-toggle="modal" data-bs-target="#addmemberModal" onclick="location.href='./classWrite.do'">
						<i class="bx bx-plus fs-16 align-middle me-1"></i> 새 클래스 개설
					</button>
				</div>

				<div class="col-sm-auto ms-auto">
					<div class="d-flex gap-3">
						<div class="search-box">
							<input type="text" class="form-control" id="searchMemberList"
								placeholder="Search for name or designation..."> <i
								class="bx bx-search search-icon fs-16"></i>
						</div>
						<div class="">
							<button type="button" id="dropdownMenuLink1"
								data-bs-toggle="dropdown" aria-expanded="false"
								class="btn btn-soft-info btn-icon fs-14">
								<i class="bx bx-dots-vertical-rounded fs-18"></i>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink1">
								<li><a class="dropdown-item" href="#">All</a></li>
								<li><a class="dropdown-item" href="#">Last Week</a></li>
								<li><a class="dropdown-item" href="#">Last Month</a></li>
								<li><a class="dropdown-item" href="#">Last Year</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-danger-subtle text-danger   member-designation me-2">Deactivate</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-9.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Ross Jordan</h5>
								</a>
								<p class="text-muted mb-0">Project Manager</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Development</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">17 Oct, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Rossjordan@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+31
								509-329-3984
							</p>
						</div>
					</div>
				</div>

				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-success-subtle text-success  member-designation me-2">Active</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-1.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Peggy Lineberger</h5>
								</a>
								<p class="text-muted mb-0">Project Manager</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Marketing</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">31 Jan, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Peggylineberger@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+02
								334-671-7141
							</p>
						</div>
					</div>
				</div>

				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-success-subtle text-success  member-designation me-2">Active</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-2.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Christine Wilson</h5>
								</a>
								<p class="text-muted mb-0">UI Designer</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Design</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">10 Dec, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Christinewilson@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+31
								509-329-3984
							</p>
						</div>
					</div>
				</div>

				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-danger-subtle text-danger   member-designation me-2">Deactivate</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-3.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Bryant Diaz</h5>
								</a>
								<p class="text-muted mb-0">Project Manager</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Digital</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">25 Apr, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Bryantdiaz@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+22
								602-301-4187
							</p>
						</div>
					</div>
				</div>

				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-success-subtle text-success  member-designation me-2">Active</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-4.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Jordan Villareal</h5>
								</a>
								<p class="text-muted mb-0">Back End Developer</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Development</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">31 May, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Jordanvillareal@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+11
								920-231-5532
							</p>
						</div>
					</div>
				</div>

				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-danger-subtle text-danger   member-designation me-2">Deactivate</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-5.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Damon Boxter</h5>
								</a>
								<p class="text-muted mb-0">Web Designer</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Design</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">23 Feb, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Damonboxter@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+10
								662-574-4035
							</p>
						</div>
					</div>
				</div>

				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-success-subtle text-success  member-designation me-2">Active</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-6.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Bryant Diaz</h5>
								</a>
								<p class="text-muted mb-0">UI Designer</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Marketing</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">17 Jun, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Bryantdiaz@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+34
								601-489-5813
							</p>
						</div>
					</div>
				</div>

				<div class="col-xxl-3 col-md-6">
					<div class="card team-box">
						<div class="card-body p-4">
							<div class="row mb-3">
								<div class="col">
									<div class="flex-shrink-0 me-2">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value=""
												id="auth-remember-check">
										</div>
									</div>
								</div>

								<div class="col-auto text-end dropdown">
									<span
										class="badge bg-success-subtle text-success  member-designation me-2">Active</span>
									<a href="javascript:void(0);" data-bs-toggle="dropdown"
										aria-expanded="false"> <i
										class="bx bx-dots-horizontal-rounded fs-18 align-middle"></i>
									</a>

									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item edit-list" href="#"> <i
												class="bx bx-pencil fs-16 me-2 text-muted"></i>Edit
										</a></li>
										<li><a class="dropdown-item remove-list" href="#"> <i
												class="bx bx-trash fs-16 me-2 text-muted"></i>Remove
										</a></li>
									</ul>
								</div>
							</div>

							<div class="text-center mb-3">
								<div class="avatar-md mx-auto">
									<img src="assets/images/users/avatar-8.jpg" alt=""
										class="member-img img-fluid d-block rounded-circle">
								</div>
							</div>

							<div class="text-center">
								<a href="" class="member-name">
									<h5 class="fs-16 mb-1">Patience harrington</h5>
								</a>
								<p class="text-muted mb-0">Digital Marketing</p>

								<div class="row">
									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Department</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">Digital</h5>
										</div>
									</div>

									<div class="col-6">
										<div class="mt-3">
											<p class="mb-0 text-muted">Join Date</p>
											<h5 class="mt-1 fs-16 mb-0 text-truncate">25 Apr, 2022</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer pt-3 border-top px-4">
							<p class="mb-1 text-muted text-truncate">
								<i class="bx bx-envelope fs-18 align-middle me-2 pe-1"></i>Patienceharrington@gmail.com
							</p>
							<p class="mb-0 text-muted">
								<i class="bx bx-phone fs-18 align-middle me-2 pe-1"></i>+24
								704-587-2054
							</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row align-items-center mb-4 gy-3">
				<div class="col-sm-auto ms-auto">
					<nav aria-label="...">
						<ul class="pagination mb-0">
							<li class="page-item disabled"><span class="page-link">Previous</span>
							</li>
							<li class="page-item active"><a class="page-link" href="#">1</a></li>
							<li class="page-item" aria-current="page"><span
								class="page-link">2</span></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">Next</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>



		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp"%>
</body>
</html>