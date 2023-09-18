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
			<section class="auth-page-wrapper py-5 position-relative d-flex align-items-center justify-content-center min-vh-100 bg-light">
		        <div class="container">
		            <div class="row">
		                <div class="col-lg-12">
		                    <div class="card overflow-hidden">
		                        <div class="row g-0">
		                        <div class="mt-4">
		                        	<div class="text-center mt-2">
	                        		</div>
		                        </div>
                            	<div class="row-2">
	                                <div class="mb-0 border-0 shadow-none px-2">
                                		<div class="col-xl-12">
									        <div class="card">
									            <div class="card-header col-xl-12 row" style="margin-left: 0;">
									            	<input class="form-check-input col-xl-1" type="checkbox" id="check0">
									                <h4 class="card-title mb-0 col-xl-11">이용약관 동의(필수)</h4>
						           				 </div><!-- end card header -->
									            <div class="card-body">
									                <div class="mx-n3" id="firstScroll">
									                </div>
									            </div><!-- end card-body -->
									        </div><!-- end card -->
							  			  </div>
                               		 </div><!-- end card -->
                                </div>
                                <div class="row-2">
	                                <div class="mb-0 border-0 shadow-none px-2">
                                		<div class="col-xl-12">
									        <div class="card">
									            <div class="card-header col-xl-12 row" style="margin-left: 0;">
									            	<input class="form-check-input col-xl-1" type="checkbox" id="check1">
									                <h4 class="card-title mb-0 col-xl-11">개인정보 수집 및 이용 동의(필수)</h4>
						           				 </div><!-- end card header -->
									            <div class="card-body">
									                <div class="mx-n3" id="secondScroll" >
									                   
									                </div>
									            </div><!-- end card-body -->
									        </div><!-- end card -->
							  			  </div>
                               		 </div><!-- end card -->
                                </div>
		                            </div>
		                            <!--end col-->
		                            <div>
										<div class="mt-4 text-center">
										        <button class="btn btn-primary col-7" id="doRegist" type="button">회원가입</button>
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
										    <p class="mb-0">이미 회원이신가요? <a href="loginForm.do" class="fw-semibold text-primary text-decoration-underline"> 로그인 </a> </p>
										</div>
		                            </div>
		                        <!--end row-->
		                    </div>
		                </div>
		                <!--end col-->
		            </div>
		        </div><!--end container-->
		    </section>
		<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="./js/registPage.js"></script>
	</body>
</html>