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
		<div class="main-content">
			<section class="auth-page-wrapper py-5 position-relative d-flex align-items-center justify-content-center min-vh-100 bg-light">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="card overflow-hidden">
	                        <div class="row g-0">
	                            <div class="col-lg-7">
	                                <div class="mb-0 border-0 shadow-none">
	                                    <div class="p-4 p-sm-5 m-lg-4">
	                                        <div class="text-center mt-2">
	                                            <h5 class="text-primary fs-20"><b>새 비밀번호 생성</b></h5>
	                                            <p class="text-muted">새로운 비밀번호를 입력해 주세요.</p>
	                                        </div>
	                                        <div class="p-2 mt-4">
	                                            <form action="./updatePassword.do?<%=request.getQueryString()%>" method="post" class="auth-input">
	                                                <div class="mb-3">
	                                                    <label class="form-label" for="password-input">비밀번호</label>
	                                                    <div class="position-relative auth-pass-inputgroup">
	                                                        <input type="password" class="form-control pe-5 password-input"  name="userPassword "onpaste="return false" placeholder="Enter password" id="userPassword" aria-describedby="passwordInput" required="">
	                                                        <button class="btn btn-link position-absolute end-0 top-0 text-decoration-none text-muted password-addon h-100" type="button" id="password-addon"><i class="ri-eye-fill align-middle"></i></button>
	                                                    </div>
	                                                    <div id="passwordInput" class="form-text">비밀번호는 8자리 이상이여야 하며 영문,숫자,특수문자가 반드시 포함되어야 합니다.</div>
	                                                </div>
	
	                                                <div class="mb-3">
	                                                    <label class="form-label" for="confirm-password-input">비밀번호 확인</label>
	                                                    <div class="position-relative auth-pass-inputgroup mb-3">
	                                                        <input type="password" class="form-control pe-5 password-input" name="userPassword" onpaste="return false" placeholder="Confirm password" id="passwordconfirm" required="">
	                                                        <button class="btn btn-link position-absolute end-0 top-0 text-decoration-none text-muted password-addon h-100" type="button" id="confirm-password-input"><i class="ri-eye-fill align-middle"></i></button>
	                                                    </div>
	                                                </div>
	
	                                                <div class="mt-4">
	                                                    <button class="btn btn-primary w-100" type="submit">비밀번호 변경</button>
	                                                </div>
	                                            </form>
	                                        </div>
	                                    </div><!-- end card body -->
	                                </div><!-- end card -->
	                            </div>
	                            <!--end col-->
	
	                            <div class="col-lg-5">
	                                <div class="card rounded-0 auth-card bg-primary h-100 border-0 shadow-none p-sm-3 overflow-hidden mb-0">
	                                    <div class="bg-overlay bg-primary"></div>
	                                    <div class="card-body p-4 d-flex justify-content-between flex-column position-relative">
	                                        <div class="auth-image mb-3">
	                                            <img src="~/assets/images/logo-light-full.png" alt="" height="26" />
	                                        </div>
	
	                                        <div class="my-auto">
	                                            <!-- Swiper -->
	                                            <div class="swiper pagination-dynamic-swiper rounded">
	                                                <div class="swiper-wrapper">
	                                                    <div class="swiper-slide">
	                                                        <div class="text-center">
	                                                            <h5 class="fs-20 mt-4 text-white mb-0">
	                                                                “I feel confident imposing on myself”
	                                                            </h5>
	                                                            <p class="fs-15 text-white-50 mt-2 pb-4">
	                                                                Vestibulum auctor orci in risus iaculis consequat suscipit felis rutrum aliquet iaculis
	                                                                augue sed tempus In elementum ullamcorper lectus vitae pretium Nullam ultricies diam
	                                                                eu ultrices sagittis.
	                                                            </p>
	                                                        </div>
	                                                    </div>
	                                                    <div class="swiper-slide">
	                                                        <div class="text-center">
	                                                            <h5 class="fs-20 mt-4 text-white mb-0">
	                                                                “Our task must be to
	                                                                free widening circle”
	                                                            </h5>
	                                                            <p class="fs-15 text-white-50 mt-2 pb-4">
	                                                                Curabitur eget nulla eget augue dignissim condintum Nunc imperdiet ligula porttitor commodo elementum
	                                                                Vivamus justo risus fringilla suscipit faucibus orci luctus
	                                                                ultrices posuere cubilia curae ultricies cursus.
	                                                            </p>
	                                                        </div>
	                                                    </div>
	                                                    <div class="swiper-slide">
	                                                        <div class="text-center">
	                                                            <h5 class="fs-20 mt-4 text-white mb-0">
	                                                                “I've learned that
	                                                                people forget what you”
	                                                            </h5>
	                                                            <p class="fs-15 text-white-50 mt-2 pb-4">
	                                                                Pellentesque lacinia scelerisque arcu in aliquam augue molestie rutrum Fusce dignissim dolor id auctor accumsan
	                                                                vehicula dolor
	                                                                vivamus feugiat odio erat sed  quis Donec nec scelerisque magna
	                                                            </p>
	                                                        </div>
	                                                    </div>
	                                                </div>
	                                                <div class="swiper-pagination dynamic-pagination"></div>
	                                            </div>
	                                        </div>
	                                        <div class="text-center text-white-75">
	                                            <p class="mb-0">
	                                                &copy;
	                                                <script>document.write(new Date().getFullYear())</script> Nomzie. Crafted with <i class="mdi mdi-heart text-danger"></i> by Themesbrand
	                                            </p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <!--end col-->
	                        </div>
	                        <!--end row-->
	                    </div>
	                </div>
	                <!--end col-->
	            </div>
	        </div><!--end container-->
	    </section>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="./js/newPassword.js"></script>
	</body>
</html>