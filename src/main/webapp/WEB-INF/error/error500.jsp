<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error500 | 타문타답</title>
<%@ include file="../views/shared/_head_css.jsp" %>
<link href="./css/error.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<section class="auth-page-wrapper py-5 position-relative d-flex align-items-center justify-content-center min-vh-100 bg-light">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="card overflow-hidden">
                        <div class="row g-0">
                            <div class="col-lg-7">
                                <div class="mb-0 border-0 shadow-none">
                                    <div class="p-4 p-sm-5 m-lg-4">
                                        <div class="error-img text-center px-5 m-4">
                                            <img src="./assets/images/error500.png" class="img-fluid" alt="">
                                        </div>
                                        <div class="mt-4 text-center pt-3">
                                            <div class="position-relative">
                                                <h3 class="error-subtitle text-uppercase mb-0">페이지가 작동하지 않습니다.</h3>
                                                <p class="fs-15 text-muted mt-3">
                                                    현재 http://localhost:8080/tamuntadab_pro 에서 요청을 처리할 수 없습니다.
                                                </p>
                                                <div class="mt-4">
                                                    <a href="/" class="btn btn-primary"><i class="mdi mdi-home me-1"></i>홈으로 돌아가기</a>
                                                </div>
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
                                            <img src="./assets/images/logo-light-full.png" alt="" height="26" />
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
                                                </div>
                                            </div>
                                        </div>
                                        <div class="text-center text-white-75">
                                            <p class="mb-0">
                                                &copy;
                                                <script>document.write(new Date().getFullYear())</script> TamunTadap.
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
            <!--end row-->
        </div>
        <!--end container-->
    </section>
</body>
</html>