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
						                                                <%
						                                                	if(userInfo.getUserSite().equals("T")){
						                                                		%>
						                                                		 <li><a class="dropdown-item" href="#">비밀번호 변경</a></li>
						                                           			     <li>
							                                                    <a class="dropdown-item" href="#">회원 탈퇴</a>
						                                                		<%
						                                                	}else{
						                                                		%>
																				<li>
						                                                		<a class="dropdown-item" href="#">연동 해제</a>
						                                                		<%
						                                                	}
						                                                %>
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
													<label for="updateprofile">
													  <span class="btn btn-primary waves-effect waves-light btn-sm">
													    <i class="ri-camera-line me-1 align-middle"></i><b>프로필 변경</b>
													  </span>
													  <input type="file" hidden="" id="updateprofile">
													</label>
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
										aria-selected="true"> <i class="bi bi-book fs-20"></i>
											<span class="d-none d-sm-block">클래스</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#info" role="tab"
										aria-selected="false"> <i class="bx bx-user-circle fs-20"></i>
											<span class="d-none d-sm-block">내 정보 수정</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#payment" role="tab"
										aria-selected="false"> <i class="bx bx-clipboard fs-20"></i>
											<span class="d-none d-sm-block">결제 내역</span>
									</a></li>
									
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#scheduel" role="tab"
										aria-selected="false"> <i class="ri ri-calendar-line fs-20"></i>
											<span class="d-none d-sm-block">일정</span>
									</a></li>
										<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#boardlist" role="tab"
										aria-selected="false"> <i class="ri ri-file-list-line fs-20"></i>
											<span class="d-none d-sm-block">내 글 목록</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#likelist" role="tab"
										aria-selected="false"> <i class="ri ri-heart-add-line fs-20"></i>
											<span class="d-none d-sm-block">내 좋아요 목록</span>
									</a></li>
									<% if(userInfo.getUserAuth().equals("I")){
										%>
										<li class="nav-item">
											<a class="nav-link px-3" data-bs-toggle="tab" href="#instrInfo" role="tab" aria-selected="false"> 
												<i class="ph ph-chalkboard-teacher-bold fs-20"></i>
											<span class="d-none d-sm-block">강사 정보</span>
											</a>
										</li>
										<%
									}
									%>
								</ul>
								<!-- 클래스 구간 -->
								<div class="tab-content">
									<div class="tab-pane active" id="class" role="tabpanel">
										<div class="p-4">
											<div>
												클래스 구간
											</div>
											<!-- end card body -->
										</div>
										<!-- end card -->
									</div><!-- 클래스 끝나는 구간 -->
									
									<!-- 내 정보 수정 구간 -->
									<div class="tab-pane" id="info" role="tabpanel">
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
									<!-- 내정보 끝 -->
									<!-- 결제 내역 -->
									<div class="tab-pane" id="payment" role="tabpanel">
										<div class="p-4">
											결제 구간 입니다.
										</div>
										<!-- end card -->
									</div>
									<!-- 결제 끝 -->
									<!-- 일정 -->
									<div class="tab-pane" id="scheduel" role="tabpanel">
										<div class="p-4">
											일정 구간 입니다.
										</div>
										<!-- end card -->
									</div>
									<!-- 일정 끝 -->		
									
									<!-- 내 글 목록 -->
									<div class="tab-pane" id="boardlist" role="tabpanel">
										<div class="p-4">
											내 글 목록 구간 입니다.
										</div>
										<!-- end card -->
									</div>
									<!-- 내글 목록 끝 -->
									
									<!-- 내 좋아요 목록 -->
									<div class="tab-pane" id="likelist" role="tabpanel">
										<div class="p-4">
											내 좋아요 구간 입니다.
										</div>
										<!-- end card -->
									</div>
									<!-- 내 좋아요 끝 -->
									<%
										if(userInfo.getUserAuth().equals("I")){
											%>
												<!-- 강사 정보 목록 -->
												<div class="tab-pane" id="instrInfo" role="tabpanel">
													<div class="p-4">
														강사 정보 구간 입니다.
													</div>
													<!-- end card -->
												</div>
												<!-- 강사 정보 끝 -->
											<%
										}
									%>
							
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
	<script type="text/javascript" src="./js/myPage.js"></script>
</body>
</html>