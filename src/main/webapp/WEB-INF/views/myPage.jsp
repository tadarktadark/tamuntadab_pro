<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xxl-3">
							<div class="user-sidebar">
								<div class="card">
									<div class="card-body p-0">
					                    <div class="user-profile-img">
					                        <img src="./assets/images/profile-bg.jpg"
					                             class="profile-img profile-foreground-img rounded-top" style="height: 120px;"
					                             alt="">
					                        <div class="overlay-content rounded-top">
					                            <div>
					                                <div class="user-nav p-3">
					                                    <div class="d-flex justify-content-end">
					                                        <div class="dropdown">
					                                            <a href="#" role="button"
					                                               data-bs-toggle="dropdown" aria-expanded="false">
					                                                <i class="bx bx-dots-vertical-rounded text-white fs-18"></i>
					                                            </a>
					
					                                            <ul class="dropdown-menu dropdown-menu-end">
					                                                <li><a class="dropdown-item" href="#">비밀번호 변경</a></li>
					                                                <li>
					                                                    <a class="dropdown-item" href="#">회원 탈퇴</a>
					                                                </li>
					                                            </ul>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
										<!-- end user-profile-img -->

										<div class="mt-n5 position-relative">
											<div class="text-center">
												<img src="<%=userInfo.getUserProfileFile()%>" alt="<%=userInfo.getUserName()%>의 프로필 사진"
													class="avatar-lg rounded-circle img-thumbnail">

												<div class="mt-3">
													<h5 class="mb-2"><b><%=userInfo.getUserName()%></b></h5>
													<div class="mt-4">
														<a href=""
															class="btn btn-primary waves-effect waves-light btn-sm"><i
															class="ri-camera-line me-1 align-middle"></i><b>프로필 변경</b></a>
													</div>
												</div>

											</div>
										</div>

										<div class="p-3 mt-3">
											<div class="row text-center">
												<div class="col-6 border-end">
													<div class="p-1">
														<h5 class="mb-1">3</h5>
														<p class="text-muted mb-0">진행 대기 클래스</p>
													</div>
												</div>
												<div class="col-6">
													<div class="p-1">
														<h5 class="mb-1">105</h5>
														<p class="text-muted mb-0">완료 클래스</p>
													</div>
												</div>
											</div>


										</div>
									</div>
									<!-- end card body -->
								</div>
								<!-- end card -->

								<div class="card">
									<div class="card-header">
										<h5 class="card-title mb-0">여기는 뭐 넣을까여?</h5>
									</div>

									<div class="card-body pt-2">
										<div class="table-responsive">
											<table class="table align-middle table-nowrap mb-0">
												<tbody>
													<tr>
														<td style="width: 50px;"><img
															src="~/assets/images/users/avatar-2.jpg"
															class="rounded-circle avatar-xs" alt=""></td>
														<td><h5 class="fs-15 m-0">
																<a href="javascript: void(0);" class="text-body">Daniel
																	Canales</a>
															</h5></td>
														<td>
															<div>
																<a href="javascript: void(0);"
																	class="badge bg-primary-subtle  text-primary fs-11">Frontend</a>
															</div>
														</td>
													</tr>
													<tr>
														<td><img src="~/assets/images/users/avatar-1.jpg"
															class="rounded-circle avatar-xs" alt=""></td>
														<td><h5 class="fs-15 m-0">
																<a href="javascript: void(0);" class="text-body">Jennifer
																	Walker</a>
															</h5></td>
														<td>
															<div>
																<a href="javascript: void(0);"
																	class="badge bg-primary-subtle  text-primary fs-11">UI
																	/ UX</a>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="avatar-xs">
																<span
																	class="avatar-title rounded-circle bg-primary text-white fs-14">
																	C </span>
															</div>
														</td>
														<td><h5 class="fs-15 m-0">
																<a href="javascript: void(0);" class="text-body">Carl
																	Mackay</a>
															</h5></td>
														<td>
															<div>
																<a href="javascript: void(0);"
																	class="badge bg-primary-subtle  text-primary fs-11">Backend</a>
															</div>
														</td>
													</tr>
													<tr>
														<td><img src="~/assets/images/users/avatar-4.jpg"
															class="rounded-circle avatar-xs" alt=""></td>
														<td><h5 class="fs-15 m-0">
																<a href="javascript: void(0);" class="text-body">Janice
																	Cole</a>
															</h5></td>
														<td>
															<div>
																<a href="javascript: void(0);"
																	class="badge bg-primary-subtle  text-primary fs-11">Frontend</a>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="avatar-xs">
																<span
																	class="avatar-title rounded-circle bg-primary text-white fs-14">
																	T </span>
															</div>
														</td>
														<td><h5 class="fs-15 m-0">
																<a href="javascript: void(0);" class="text-body">Tony
																	Brafford</a>
															</h5></td>
														<td>
															<div>
																<a href="javascript: void(0);"
																	class="badge bg-primary-subtle  text-primary fs-11">Backend</a>
															</div>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
<!-- 								end card -->
							</div>
						</div>

						<div class="col-xxl-9">
						<div class="card">
								<ul class="nav nav-tabs-custom nav-justified" id="pills-tab"
									role="tablist">
									<li class="nav-item"><a class="nav-link px-3 active"
										data-bs-toggle="tab" href="#class" role="tab"
										aria-selected="true"> <i class="bx bx-user-circle fs-20"></i>
											<span class="d-none d-sm-block">클래스</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3 active"
										data-bs-toggle="tab" href="#about" role="tab"
										aria-selected="false"> <i class="bx bx-user-circle fs-20"></i>
											<span class="d-none d-sm-block">내 정보 수정</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#task" role="tab"
										aria-selected="false"> <i class="bx bx-clipboard fs-20"></i>
											<span class="d-none d-sm-block">Tasks</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#massages" role="tab"
										aria-selected="false"> <i class="bx bx-mail-send fs-20"></i>
											<span class="d-none d-sm-block">Messages</span>
									</a></li>
								</ul>

								<div class="tab-content">
									<div class="tab-pane active" id="#class" role="tabpanel">
										<div class="p-4">
											<div>
												<div class="pb-3">
														<div class=" pt-3 px-3">
														    <div class="input-group w-50">
													    		<span class="input-group-text"><b>이 메 일</b></span>
															    <input type="text" class="form-control" value="<%=userInfo.getUserEmail()%>" readonly="readonly" aria-describedby="basic-addon1">
															</div>
														</div>
												</div>
												
												<div class="pb-3">
														<div class=" pt-1 px-3">
														    <div class="input-group w-50">
															    <label for="nickname" class="form-label input-group-text"><span><b>닉 네 임</b></span></label>
															    <input type="text" class="form-control" id="nickname"value="<%=userInfo.getUserNickname()%>" aria-describedby="basic-addon1">
															    <button class="btn btn-primary">변경하기</button>
															</div>
														</div>
												</div>
												<div class="pb-3">
														<div class=" pt-1 px-3">
														    <div class="input-group w-50">
															    <label for="nickname" class="form-label input-group-text"><span><b>생 일</b></span></label>
															    <input type="text" class="form-control" id="nickname"value="<%=userInfo.getUserBirth()%>" aria-describedby="basic-addon1">
															    <button class="btn btn-primary">변경하기</button>
															</div>
														</div>
												</div>
											</div>
											<!-- end card body -->
										</div>
										<!-- end card -->
									</div>
									<!-- end tab pane -->
							<div class="card">
								<ul class="nav nav-tabs-custom nav-justified" id="pills-tab"
									role="tablist">
									<li class="nav-item"><a class="nav-link px-3 active"
										data-bs-toggle="tab" href="#about" role="tab"
										aria-selected="true"> <i class="bx bx-user-circle fs-20"></i>
											<span class="d-none d-sm-block">내 정보 수정</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#task" role="tab"
										aria-selected="false"> <i class="bx bx-clipboard fs-20"></i>
											<span class="d-none d-sm-block">Tasks</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#massages" role="tab"
										aria-selected="false"> <i class="bx bx-mail-send fs-20"></i>
											<span class="d-none d-sm-block">Messages</span>
									</a></li>
								</ul>

								<div class="tab-content">
									<div class="tab-pane active" id="about" role="tabpanel">
										<div class="p-4">
											<div>
												<div class="pb-3">
														<div class=" pt-3 px-3">
														    <div class="input-group w-50">
													    		<span class="input-group-text"><b>이 메 일</b></span>
															    <input type="text" class="form-control" value="<%=userInfo.getUserEmail()%>" readonly="readonly" aria-describedby="basic-addon1">
															</div>
														</div>
												</div>
												
												<div class="pb-3">
														<div class=" pt-1 px-3">
														    <div class="input-group w-50">
															    <label for="nickname" class="form-label input-group-text"><span><b>닉 네 임</b></span></label>
															    <input type="text" class="form-control" id="nickname"value="<%=userInfo.getUserNickname()%>" aria-describedby="basic-addon1">
															    <button class="btn btn-primary">변경하기</button>
															</div>
														</div>
												</div>
												<div class="pb-3">
														<div class=" pt-1 px-3">
														    <div class="input-group w-50">
															    <label for="nickname" class="form-label input-group-text"><span><b>생 일</b></span></label>
															    <input type="text" class="form-control" id="nickname"value="<%=userInfo.getUserBirth()%>" aria-describedby="basic-addon1">
															    <button class="btn btn-primary">변경하기</button>
															</div>
														</div>
												</div>
											</div>
											<!-- end card body -->
										</div>
										<!-- end card -->
									</div>
									<!-- end tab pane -->

									<div class="tab-pane" id="task" role="tabpanel">
										<div class="p-4">
											<div>
												<h5 class="fs-16 mb-3">Active</h5>
												<div class="table-responsive">
													<table class="table table-nowrap table-centered">
														<tbody>
															<tr>
																<td style="width: 60px;">
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-activeCheck2"> <label
																			class="form-check-label" for="tasks-activeCheck2"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">Ecommerce
																		Product Detail</a></td>

																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		3
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Product Design</p>
																</td>

																<td>27 May, 2021</td>
																<td style="width: 160px;"><span
																	class="badge bg-primary-subtle text-primary  fs-12">Active</span></td>

															</tr>
															<tr>
																<td>
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-activeCheck1"> <label
																			class="form-check-label" for="tasks-activeCheck1"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">Ecommerce
																		Product</a></td>

																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		7
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Web Development</p>
																</td>

																<td>26 May, 2021</td>
																<td><span
																	class="badge bg-primary-subtle text-primary  fs-12">Active</span></td>

															</tr>
														</tbody>
													</table>
												</div>

												<h5 class="fs-16 my-3">Upcoming</h5>

												<div class="table-responsive">
													<table class="table table-nowrap table-centered">
														<tbody>
															<tr>
																<td style="width: 60px;">
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-upcomingCheck3"> <label
																			class="form-check-label" for="tasks-upcomingCheck3"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">Chat
																		app Page</a></td>

																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		2
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Web Development</p>
																</td>

																<td>-</td>
																<td style="width: 160px;"><span
																	class="badge bg-secondary-subtle text-secondary  fs-12">Waiting</span></td>

															</tr>
															<tr>
																<td>
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-upcomingCheck2"> <label
																			class="form-check-label" for="tasks-upcomingCheck2"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">Email
																		Pages</a></td>

																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		1
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Illustration</p>
																</td>

																<td>04 June, 2021</td>
																<td><span
																	class="badge bg-primary-subtle text-primary  fs-12">Approved</span></td>

															</tr>
															<tr>
																<td>
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-upcomingCheck1"> <label
																			class="form-check-label" for="tasks-upcomingCheck1"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">Contacts
																		Profile Page</a></td>
																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		6
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Product Design</p>
																</td>

																<td>-</td>
																<td><span
																	class="badge bg-secondary-subtle text-secondary  fs-12">Waiting</span></td>

															</tr>
														</tbody>
													</table>
												</div>

												<h5 class="fs-16 my-3">Complete</h5>

												<div class="table-responsive">
													<table class="table table-nowrap table-centered">
														<tbody>
															<tr>
																<td style="width: 60px;">
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-completeCheck3"> <label
																			class="form-check-label" for="tasks-completeCheck3"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">UI
																		Elements</a></td>

																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		6
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Product Design</p>
																</td>

																<td>27 May, 2021</td>
																<td style="width: 160px;"><span
																	class="badge bg-success-subtle text-success  fs-12">Complete</span></td>

															</tr>
															<tr>
																<td>
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-completeCheck2"> <label
																			class="form-check-label" for="tasks-completeCheck2"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">Authentication
																		Pages</a></td>

																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		2
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Illustration</p>
																</td>

																<td>27 May, 2021</td>
																<td><span
																	class="badge bg-success-subtle text-success  fs-12">Complete</span></td>

															</tr>
															<tr>
																<td>
																	<div class="form-check fs-16 text-center">
																		<input type="checkbox" class="form-check-input"
																			id="tasks-completeCheck1"> <label
																			class="form-check-label" for="tasks-completeCheck1"></label>
																	</div>
																</td>
																<td><a href="#" class="fw-medium text-body">Admin
																		Layout</a></td>

																<td>
																	<p class="ml-4 text-muted mb-0">
																		<i
																			class="mdi mdi-comment-outline align-middle text-muted fs-16 me-1"></i>
																		3
																	</p>
																</td>
																<td>
																	<p class="ml-4 mb-0">Product Design</p>
																</td>

																<td>26 May, 2021</td>
																<td><span
																	class="badge bg-success-subtle text-success  fs-12">Complete</span></td>

															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<!-- end card -->
									</div>
									<!-- end tab pane -->

									<div class="tab-pane" id="massages" role="tabpanel">
										<div class="p-4">
											<h5 class="fs-16 mb-4">Review</h5>
											<div class="mx-n4 px-3" data-simplebar
												style="max-height: 380px;">
												<div class="d-flex align-items-start border-bottom pb-4">
													<div class="flex-shrink-0 me-3">
														<img class="rounded-circle avatar-sm"
															src="~/assets/images/users/avatar-3.jpg"
															alt="avatar-3 images">
													</div>

													<div class="flex-grow-1">
														<h5 class="fs-15 mb-1">
															Marion Walker <small class="text-muted float-end">1
																hr ago</small>
														</h5>
														<p class="text-muted">Maecenas non vestibulum ante,
															nec efficitur orci. Duis eu ornare mi, quis bibendum
															quam. Etiam imperdiet aliquam purus sit amet rhoncus.
															Vestibulum pretium consectetur leo, in mattis ipsum
															sollicitudin eget. Pellentesque vel mi tortor. Nullam
															vitae maximus dui dolor sit amet, consectetur adipiscing
															elit.</p>

														<a href="javascript: void(0);"
															class="text-muted font-13 d-inline-block"><i
															class="mdi mdi-reply"></i> Reply</a>

														<div class="d-flex align-items-start mt-4">
															<div class="flex-shrink-0 me-3">
																<img class="rounded-circle avatar-sm"
																	src="~/assets/images/users/avatar-4.jpg"
																	alt="avatar-4 images">
															</div>

															<div class="flex-grow-1">
																<h5 class="fs-15 mb-1">
																	Shanon Marvin <small class="text-muted float-end">1
																		hr ago</small>
																</h5>
																<p class="text-muted">It will be as simple as in
																	fact, it will be Occidental. To it will seem like
																	simplified .</p>
																<a href="javascript: void(0);"
																	class="text-muted font-13 d-inline-block"> <i
																	class="mdi mdi-reply"></i> Reply
																</a>
															</div>
														</div>
													</div>
												</div>

												<div class="d-flex align-items-start border-bottom py-4">
													<div class="flex-shrink-0 me-3">
														<img class="rounded-circle avatar-sm"
															src="~/assets/images/users/avatar-5.jpg"
															alt="avatar-5 images">
													</div>
													<div class="flex-grow-1">
														<h5 class="fs-15 mb-1">
															Janice Morgan <small class="text-muted float-end">2
																hrs ago</small>
														</h5>
														<p class="text-muted">Cras ac condimentum velit.
															Quisque vitae elit auctor quam egestas congue. Duis eget
															lorem fringilla, ultrices justo consequat, gravida lorem.
															Maecenas orci enim, sodales id condimentum et, nisl arcu
															aliquam velit, sit amet vehicula turpis metus cursus
															dolor cursus eget dui.</p>
														<a href="javascript: void(0);"
															class="text-muted font-13 d-inline-block"><i
															class="mdi mdi-reply"></i> Reply</a>
													</div>
												</div>
												<div class="d-flex align-items-start border-bottom py-4">
													<div class="flex-shrink-0 me-3">
														<img class="rounded-circle avatar-sm"
															src="~/assets/images/users/avatar-7.jpg"
															alt="avatar-7 images">
													</div>
													<div class="flex-grow-1">
														<h5 class="fs-15 mb-1">
															Patrick Petty <small class="text-muted float-end">3
																hrs ago</small>
														</h5>
														<p class="text-muted">Aliquam sit amet eros eleifend,
															tristique ante sit amet, eleifend arcu. Cras ut diam
															quam. Fusce quis diam eu augue semper ullamcorper vitae
															sed massa. Mauris lacinia, massa a feugiat mattis, leo
															massa porta eros, sed congue arcu sem nec orci. In ac
															consectetur augue. Nullam pulvinar risus non augue
															tincidunt blandit.</p>
														<a href="javascript: void(0);"
															class="text-muted font-13 d-inline-block"><i
															class="mdi mdi-reply"></i> Reply</a>
													</div>
												</div>
											</div>
											<div class="border rounded mt-4">
												<form action="#">
													<div class="px-2 py-1 bg-light">

														<div class="btn-group" role="group">
															<button type="button"
																class="btn btn-sm btn-link text-body text-decoration-none">
																<i class="bx bx-link fs-15"></i>
															</button>
															<button type="button"
																class="btn btn-sm btn-link text-body text-decoration-none">
																<i class="bx bx-smile fs-15"></i>
															</button>
															<button type="button"
																class="btn btn-sm btn-link text-body text-decoration-none">
																<i class="bx bx-at fs-15"></i>
															</button>
														</div>

													</div>
													<textarea rows="3"
														class="form-control border-0 resize-none"
														placeholder="Your Message..."></textarea>
												</form>
											</div>
											<!-- end .border-->
											<div class="text-end mt-3">
												<button type="button"
													class="btn btn-success w-sm text-truncate ms-2">
													Send <i class="bx bx-send ms-2 align-middle"></i>
												</button>
											</div>

										</div>
										<!-- end card -->
									</div>
									<!-- end tab pane -->
								</div>
								<!-- end tab content -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp"%>
</body>
</html>