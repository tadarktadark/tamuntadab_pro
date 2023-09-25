<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="com.tdtd.tmtd.vo.ClientVo"%>
<%@page import="com.tdtd.tmtd.vo.URLVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<link href="./assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<%@ include file="./shared/_head_css.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
		<div class="main-content">
		<div class="page-content">
			<section class="auth-page-wrapper py-5 position-relative d-flex align-items-center justify-content-center bg-light">
		        <div class="container w-50">
		            <div class="row rounded-pill ">
		                <div class="col-lg-12">
		                    <div class="card overflow-hidden">
		                        <div class="row g-0 px-5">
		                        <div class="mt-4">
		                        	<div class="text-center mt-2">
	                            		<h3 class="text-primary fs-20">로그인</h3>
	                        		</div>
		                        </div>
                                <div class="row-2 px-2">
	                                <div class="mb-0 border-0 shadow-none mt-3">
                                		<div class="col-xl-12">
												<div class="">
												    <label for="email" class="form-label"><b>이메일</b></label>
												    <input type="text" name="userEmail" class="form-control rounded-pill" id="email" placeholder="이메일을 입력해주세요">
												</div>
							  			  </div>
                               		 </div>
                                </div>
                                <div class="row-2">
	                                <div class="mb-0 border-0 shadow-none mt-3">
                                		<div class="col-xl-12">
												<div>
												    <label for="password" class="form-label"><b>비밀번호</b></label>
												    <input type="password" name="password" class="form-control rounded-pill" id="password" placeholder="비밀번호를 입력해주세요">
												</div>
							  			  </div>
                               		 </div>
                                </div>
                                <div class="row-2">
	                                <div class="mb-0 border-0 shadow-none mt-3">
                                		<div class="col-xl-6">
											<div class="form-check form-switch text-right">
											    <input class="form-check-input" name="autoLogin" type="checkbox" role="switch" id="autoLoginCheckBox">
											    <label class="form-check-label" for="autoLoginCheckBox"><b>자동 로그인</b></label>
											</div>
							  			  </div>
                               		 </div>
                                </div>
		                            </div>
		                            <!--end col-->
		                            <div>
										<div class="mt-4 text-center px-5">
										        <button class="btn btn-primary col-12 rounded-pill" id="doLogin" type="button">로그인</button>
									    </div>
										    <div class="mt-4 text-center">
										        <div class="signin-other-title">
										            <h5 class="fs-13 mb-4 title text-muted">또는</h5>
										        </div>
										
										        <div>
										        <%
										        	URLVo uvo = new URLVo();
													SecureRandom random = new SecureRandom();
													String state = new BigInteger(130, random).toString();
													ClientVo cvo = new ClientVo();
										        %>
										        	<!-- 네이버 -->
										           <a href="<%=uvo.getNaverUrl()+"&client_id="+cvo.getNaverClientID()+"&redirect_uri="+uvo.getNaverRedirect()+"&state="+state%>">
										           <button type="button" class="btn btn-ghost-dark btn-icon" id="naverRegist" 
										            style="background-image: url('./image/naver_icon.png'); background-repeat: no-repeat; background-size: cover; "></button>
										            </a>
										            
										            <!-- 카카오 -->
										            <a href="<%= uvo.getKakaoUrl()+"&redirect_uri="+uvo.getKakaoRedirect()+"&state="+state+"&client_id="+cvo.getKakaoClientID()%>">
										            <button type="button" class="btn btn-ghost-dark btn-icon" id="kakaoRegist" 
										            style="background-image: url('./image/kakao_icon.png'); background-repeat: no-repeat; background-size: cover; "></button>
										            </a>
										            
										            <!-- 구글 -->
										            <a href ="<%=uvo.getGoogleUrl()+"?response_type=code&scope=profile%20email&access_type=offline"+"&client_id="+cvo.getGoogleClientID()+"&redirect_uri="+uvo.getGoogleRedirect()+"&state="+state%>">
										            <button type="button" class="btn btn-ghost-dark btn-icon" id="googleRegist" 
										            style="background-image: url('./image/google_icon.png'); background-repeat: no-repeat; background-size: cover; "></button>
										            </a>
										        </div>
										    </div>
										
										<div class="text-center my-3">
										    <p class="mb-0">계정이 없으신가요? <a href="regist.do" class="fw-semibold text-primary text-decoration-underline"> 회원가입 </a> </p>
										    <p class="mt-3"><a href="resetPassword.do" class="fw-semibold text-primary text-decoration-underline">비밀번호를 잊어버리셨나요?</a> </p>
										</div>
		                            </div>
		                        <!--end row-->
		                    </div>
		                </div>
		                <!--end col-->
		            </div>
		        </div><!--end container-->
		    </section>
		    </div>
		<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="./js/loginForm.js"></script>
	</body>
</html>