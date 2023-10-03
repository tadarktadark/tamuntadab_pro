<%@page import="com.tdtd.tmtd.vo.UserProfileVo"%>
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
		<% UserProfileVo userInfo = (UserProfileVo) request.getAttribute("user"); %>
		<div class="vertical-overlay"></div>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xxl-12">
							<div class="user-sidebar">
								<div class="card">
									<div class="card-body p-0">
					                    <div class="user-profile-img">
					                        <img src="../assets/images/profile-bg.jpg"
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
					                                           	 <%if(userInfo.getSiusState().equals("Y")){
					                                            	%>
																	<li>
				                                                		<a class="dropdown-item" id="delCommUser" href="#">회원 정지</a>
					                                                </li>
					                                            	<%
							                                            }
						                                            %>
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
												<img
												<%if(userInfo.getUserProfileFile().equals("./image/profile.png")){
													%>
														src=".<%=userInfo.getUserProfileFile()%>"
													<%
													}else{
														%>
														src="<%=userInfo.getUserProfileFile()%>"
														<%
													}
												%>
												 alt="<%=userInfo.getUserName()%>의 프로필 사진"
													class="avatar-lg rounded-circle img-thumbnail">
												<div class="mt-3">
													<h5 class="mb-2"><b><%=userInfo.getUserName()%>(<%=userInfo.getUserNickname() %>)</b></h5>
													<div>
					                                    <span class="badge rounded-pill bg-success-subtle text-success m-1 userAuth"><%=userInfo.getUserAuth().equals("S")?"학생":"강사" %></span>
					                                </div>
												</div>
											</div>
										</div>
										<div class="p-3 mt-3">
											<div class="row text-center">
												<div class="col-6 border-end">
												</div>
												<div class="col-6">
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
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#info" role="tab"
										aria-selected="false"> <i class="bx bx-user-circle fs-20"></i>
											<span class="d-none d-sm-block">회원 정보</span>
									</a></li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane row active" id="info" role="tabpanel">
										<div class="p-4">
											<div class="container-fluid">
												    <div class="row">
												        <div class="col-3">
												            <div class="mb-3">
												                <label class="form-label"><b>이메일</b></label>
												                <input type="text" class="form-control bg-light-subtle " readonly="readonly" value="<%=userInfo.getUserEmail()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-3">
												            <div class="mb-3">
												                <label class="form-label"><b>이름</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getUserName()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-3">
												            <div class="mb-3">
												                <label class="form-label"><b>닉네임</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getUserNickname()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-3">
												            <div class="mb-3">
												                <label class="form-label"><b>핸드폰 번호</b></label>
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
												                <label class="form-label"><b>성별</b></label>
												                <input type="text" class="form-control bg-light-subtle" readonly="readonly" value="<%=userInfo.getUserGender().equals("M")?"남자":"여자" %>">
												            </div>
												        </div><!--end col-->
												        <div class="col-1">
												            <div class="mb-3">
												                <label class="form-label"><b>가입경로</b></label>
												                <input type="text" class="form-control bg-light-subtle" readonly="readonly" value="<%=userInfo.getUserSite()%>">
												            </div>
												        </div><!--end col-->
											           <div class="col-2">
												            <div class="mb-3">
												                <label class="form-label"><b>가입일</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getUserJoinDate()%>">
												            </div>
												        </div><!--end col-->
											           <div class="col-2">
												            <div class="mb-3">
												                <label class="form-label"><b>최근 접속일</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getUserLastAccessDate()%>">
												            </div>
												        </div><!--end col-->
											           <div class="col-1">
												            <div class="mb-3">
												                <label class="form-label"><b>계정 상태</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getUserDelflag()%>">
												            </div>
												        </div><!--end col-->
											           <div class="col-1">
												            <div class="mb-3">
												                <label class="form-label"><b>정지 상태</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getUserJeongJiSangTae()%>">
												            </div>
												        </div><!--end col-->
											           <div class="col-1">
												            <div class="mb-3">
												                <label class="form-label"><b>정지 대상</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=userInfo.getSiusState()%>">
												            </div>
												        </div><!--end col-->
												    </div><!--end row-->
											</div>
										</div>
									</div>
									<!-- 내정보 끝 -->
								</div>
								<!-- end tab content -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>