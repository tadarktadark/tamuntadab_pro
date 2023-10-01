<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm" 
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<%@ include file="./shared/_head_css.jsp"%>
<%@ include file="./shared/_logout.jsp" %>
<link href="./assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xxl-12">
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
						                                                		 <li><a class="dropdown-item" href="./updatePassword.do?tokenValue=mypage">비밀번호 변경</a></li>
						                                           			     <li>
							                                                    <a class="dropdown-item" id="delCommUser" href="#">회원 탈퇴</a>
						                                                		<%
						                                                	}else{
						                                                		%>
																				<li>
						                                                		<a class="dropdown-item" id="delCommUser" href="#">연동 해제</a>
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
													<h5 class="mb-2"><b><%=userInfo.getUserName()%>(<%=userInfo.getUserNickname() %>)</b></h5>
													<div>
					                                    <span class="badge rounded-pill bg-success-subtle text-success m-1 userAuth"><%=userInfo.getUserAuth().equals("S")?"학생":"강사" %></span>
					                                </div>
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
												<%if(userInfo.getUserAuth().equals("S")){
														%>
														<div class="p-1">
															<h5 class="mb-1">72</h5>
															<p class="text-muted mb-0">진행 예정 클래스</p>
														</div>
														<%
														}else{
														%>
														<div class="p-1">
															<button class="btn rounded-pill btn-primary" onclick="location.href='./instrProfileForm.do'">강사 프로필 등록 및 수정</button>
														</div>	
														<%
														}
														%>
												</div>
												<div class="col-6">
													<%if(userInfo.getUserAuth().equals("S")){
														%>
													<div class="p-1">
														<h5 class="mb-1">105</h5>
														<p class="text-muted mb-0">완료 클래스</p>
													</div>
														<%
													}else{
														%>
													<div class="p-1">
														<button class="btn rounded-pill btn-primary" onclick="location.href='./instrCareer.do'">인증 요청 페이지</button>
													</div>	
														<%
													}
														%>
												</div>
											</div>
										</div>
									</div>
									<!-- end card body -->
								</div>
								<!-- end card -->
							</div>
						</div>

						<div class="col-xxl-12">
						<div class="card">
								<ul class="nav nav-tabs-custom nav-justified" id="pills-tab"
									role="tablist">
									<li class="nav-item"><a class="nav-link px-3 active"
										data-bs-toggle="tab" href="#class" role="tab"
										aria-selected="true"> <i class="ri ri-apps-2-line fs-20"></i>
											<span class="d-none d-sm-block">클래스</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#info" role="tab"
										aria-selected="false"> <i class="ri ri-account-circle-line fs-20"></i>
											<span class="d-none d-sm-block">내 정보</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#payment" role="tab"
										aria-selected="false"> <i class="ri ri-bank-card-2-line fs-20"></i>
											<span class="d-none d-sm-block">결제 내역</span>
									</a></li>
									
									<li class="nav-item scheduel"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#scheduel" role="tab"
										aria-selected="false"> <i class="ri ri-calendar-line fs-20"></i>
											<span class="d-none d-sm-block">일정</span>
									</a></li>
										<li class="nav-item myPilgi"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#boardlist" role="tab"
										aria-selected="false"> <i class="ri ri-pages-line fs-20"></i>
											<span class="d-none d-sm-block">내 커뮤니티</span>
									</a></li>
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#reviewlist" role="tab"
										aria-selected="false"> <i class="ri ri-star-line fs-20"></i>
											<span class="d-none d-sm-block">내 후기</span>
									</a></li>
								</ul>
								<!-- 클래스 구간 -->
								<div class="tab-content">
									<div class="tab-pane active" id="class" role="tabpanel">
										<div class="p-4">
											<div>
												<%@ include file="./myPageClass.jsp"%>
											</div>
											<!-- end card body -->
										</div>
										<!-- end card -->
									</div><!-- 클래스 끝나는 구간 -->
									
									<!-- 내 정보 수정 구간 -->
									<div>
									</div>
									<div class="tab-pane row" id="info" role="tabpanel">
										<div class="p-4">
											<div class="container-fluid">
												    <div class="row">
												        <div class="col-6">
												            <div class="mb-3">
												                <label class="form-label"><b>이메일</b></label>
												                <input type="text" class="form-control bg-light-subtle " readonly="readonly" value="<%=userInfo.getUserEmail()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-6">
												            <div class="mb-3">
												                <label class="form-label"><b>이름</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getUserName()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-6">
												            <div class="mb-3">
												                <label for="compnayNameinput" class="form-label"><b>닉네임</b></label>
												                <div class="input-group">
												                	<input type="text" class="form-control" placeholder="변경 할 닉네임을 입력하세요" id="nickname">
												                	<button class="input-group-button btn btn-primary" id="updateNickName">변경하기</button>
												                </div>
												            </div>
												        </div><!--end col-->
												        <div class="col-3">
												            <div class="mb-3">
												                <label class="form-label">핸드폰 번호</label>
												                <%
																	String userPhoneNumber = userInfo.getUserPhoneNumber();
																	if (userPhoneNumber.length() == 10) {
																	    userPhoneNumber = userPhoneNumber.replaceFirst("(\\d{3})(\\d{4})(\\d{3})", "$1-$2-$3");
																	} else if (userPhoneNumber.length() == 11) {
																	    userPhoneNumber = userPhoneNumber.replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
																	}
																%>
												                <input type="text" class="form-control bg-light-subtle" readonly="readonly" value="<%=userPhoneNumber%>" >
												            </div>
												        </div><!--end col-->
												        <div class="col-3">
												            <div class="mb-3">
												                <label class="form-label">성별</label>
												                <input type="text" class="form-control bg-light-subtle" readonly="readonly" value="<%=userInfo.getUserGender().equals("M")?"남자":"여자" %>">
												            </div>
												        </div><!--end col-->
												    </div><!--end row-->
											</div>
										</div>
									</div>
									<!-- 내정보 끝 -->
									<!-- 결제 내역 -->
									<div class="tab-pane" id="payment" role="tabpanel">
										<div class="p-4">
											<%@ include file="./myPayment.jsp"%>
										</div>
										<!-- end card -->
									</div>
									<!-- 결제 끝 -->
									<!-- 일정 -->
									<div class="tab-pane" id="scheduel" role="tabpanel">
										<div class="p-4">
											<%@ include file="./myYeyak.jsp"%>
										</div>
										<!-- end card -->
									</div>
									<!-- 일정 끝 -->		
									
									<!-- 내 글 목록 -->
									<div class="tab-pane" id="boardlist" role="tabpanel">
										
											<%@ include file="./myBoard.jsp"%>
										
										<!-- end card -->
									</div>
									<!-- 내글 목록 끝 -->
									<!-- 내 후기 목록 -->
									<div class="tab-pane" id="reviewlist" role="tabpanel">
										<div class="p-4">
											<%@ include file="./myReviewList.jsp"%>
										</div>
										<!-- end card -->
									</div>
									<!-- 내 좋아요 끝 -->
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