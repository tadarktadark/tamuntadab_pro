<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<%@ include file="./shared/_head_css.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
	
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
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
                                            <h5 class="text-primary fs-20">회원가입</h5>
                                            <p class="text-muted">배움의 기회를 타문타답과 함께 하세요</p>
                                        </div>
                                        <div class="p-2 mt-4">
                                            <form class="needs-validation auth-input" novalidate action="/">

                                                <div class="mb-3">
                                                    <label for="useremail" class="form-label">이메일 <span class="text-danger">*</span></label>
                                                   	<input type="email" class="form-control" id="useremail" placeholder="Enter email address" required>
                                                    <div class="hstack flex-wrap gap-2">
													    <button type="button" class="btn btn-primary" id="borderedToast1Btn">이메일 중복 확인</button>
													</div>
													<div style="z-index: 11">
												    <div id="borderedToast1" class="toast toast-border-primary overflow-hidden mt-3" role="alert" aria-live="assertive" aria-atomic="true">
												        <div class="toast-body">
												            <div class="d-flex align-items-center">
												                <div class="flex-shrink-0 me-2">
												                    <i class="ri-user-smile-line align-middle"></i>
												                </div>
												                <div class="flex-grow-1">
												                    <h6 class="mb-0">이메일 인증에 성공하였습니다.</h6>
												                </div>
												            </div>
												        </div>
												    </div>
													</div>
                                                    <div class="invalid-feedback">
                                                        이메일을 입력해 주세요
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="username" class="form-label">이름 <span class="text-danger">*</span></label>
                                                    <input type="text" class="form-control" id="username" placeholder="Enter username" required>
                                                    <div class="invalid-feedback">
                                                        이름을 입력해 주세요
                                                    </div>
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label" for="password-input">비밀번호</label>
                                                    <div class="position-relative auth-pass-inputgroup">
                                                        <input type="password" class="form-control pe-5 password-input" onpaste="return false" placeholder="Enter password" id="password-input" aria-describedby="passwordInput" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
                                                        <button class="btn btn-link position-absolute end-0 top-0 text-decoration-none text-muted password-addon h-100" type="button" id="password-addon"><i class="ri-eye-fill align-middle"></i></button>
                                                        <div class="invalid-feedback">
                                                            비밀번호를 입력해주세요
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label" for="password-input">비밀번호 확인</label>
                                                    <div class="position-relative auth-pass-inputgroup">
                                                        <input type="password" class="form-control pe-5 password-input" onpaste="return false" placeholder="Enter password" id="password-input" aria-describedby="passwordInput" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
                                                        <button class="btn btn-link position-absolute end-0 top-0 text-decoration-none text-muted password-addon h-100" type="button" id="password-addon"><i class="ri-eye-fill align-middle"></i></button>
                                                    	<div class="invalid-feedback">
                                                            비밀번호 확인을 입력해주세요
                                                        </div>
                                                    </div>
                                                </div>

                                                <div id="password-contain" class="p-3 bg-light mb-2 rounded">
                                                    <h5 class="fs-13">※비밀번호 형식※</h5>
                                                    <p id="pass-length" class="invalid fs-12 mb-2">최소 <b>8자리 입력해야합니다.</b></p>
                                                    <p id="pass-lower" class="invalid fs-12 mb-2">반드시 <b>소문자를 포함하여야 합니다.</b></p>
                                                    <p id="pass-upper" class="invalid fs-12 mb-2">반드시 <b>대문자를 포함하여야 합니다.</b></p>
                                                    <p id="pass-number" class="invalid fs-12 mb-0">반드시 <b>숫자를 포함하여야 합니다.</b></p>
                                                </div>

                                                <div class="mt-4">
                                                    <button class="btn btn-primary w-100" type="submit">회원가입</button>
                                                </div>

                                                <div class="mt-4 text-center">
                                                    <div class="signin-other-title">
                                                        <h5 class="fs-13 mb-4 title text-muted">소셜 가입</h5>
                                                    </div>

                                                    <div>
                                                        <button type="button" class="btn btn-soft-primary btn-icon "><i class="ri-facebook-fill fs-16"></i></button>
                                                        <button type="button" class="btn btn-soft-danger btn-icon "><i class="ri-google-fill fs-16"></i></button>
                                                        <button type="button" class="btn btn-soft-dark btn-icon "><i class="ri-github-fill fs-16"></i></button>
                                                        <button type="button" class="btn btn-soft-info btn-icon "><i class="ri-twitter-fill fs-16"></i></button>
                                                    </div>
                                                </div>
                                            </form>

                                            <div class="text-center mt-5">
                                                <p class="mb-0">이미 계정을 가지고 있습니까? <a href="./loginForm.do" class="fw-semibold text-primary text-decoration-underline"> 로그인 </a> </p>
                                            </div>
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
                                                            <h5 class="font-size-20 mt-4 text-white mb-0">
                                                                “I've learned that
                                                                people forget what you”
                                                            </h5>
                                                            <p class="font-size-15 text-white-50 mt-2 pb-4">
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
                                                Design by Themesbrand & Develop by TadakTadak
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
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
    <!-- validation init -->
    <script src="./assets/js/pages/form-validation.init.js"></script>
    <!-- password create init -->
    <script src="./assets/js/pages/passowrd-create.init.js"></script>
    <script type="text/javascript" src="./assets/js/pages/notifications.init.js"></script>
</body>
</html>