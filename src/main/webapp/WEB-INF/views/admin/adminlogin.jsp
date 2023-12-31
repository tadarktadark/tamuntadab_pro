<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼 | 관리자</title>
<!--     @Html.Partial("~/Views/Shared/_title_meta.cshtml") -->
    <%@ include file="./shared/_head_css.jsp" %>
    <%@ include file="./shared/_vender_scripts.jsp" %>
    <link href="../assets/libs/swiper/swiper-bundle.min.css" rel="stylesheet" type="text/css" />
    <link href="../assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
    <script src="../assets/libs/sweetalert2/sweetalert2.min.js"></script>
    <script type="text/javascript" src="../js/adminLogin.js"></script>
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
                                        <div class="text-center mt-2">
                                            <h5 class="text-primary fs-20 fw-bolder">관리자 로그인</h5>
                                        </div>
                                        <div class="p-2 mt-4">
                                            <form class="auth-input" id="loginForm">
                                                <div class="mb-3">
                                                    <label for="adminId" class="form-label fw-bolder">관리자 아이디</label>
                                                    <input type="text" class="form-control"name="adminID" id="adminId" placeholder="아이디를 입력하세요">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label fw-bolder" for="adminPW">비밀번호</label>
                                                    <div class="position-relative auth-pass-inputgroup mb-3">
                                                        <input type="password" name="adminPW" class="form-control pe-5 password-input" placeholder="비밀번호를 입력하세요" id="adminPW">
                                                        <button class="btn btn-link position-absolute end-0 top-0 text-decoration-none text-muted password-addon h-100" type="button" id="password-addon"><i class="ri-eye-fill align-middle"></i></button>
                                                    </div>
                                                </div>
                                                <div class="mt-4">
                                                    <button class="btn btn-primary w-100" type="submit">접속하기</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!-- end card body -->
                                </div><!-- end card -->
                            </div>
                            <!--end col-->

                            <div class="col-lg-5">
                                <div class="card rounded-0 auth-card bg-primary h-100 border-0 shadow-none p-sm-3 overflow-hidden mb-0">
                                    <div class="bg-overlay bg-dark"></div>
                                    <div class="card-body p-4 d-flex justify-content-between flex-column position-relative">
                                        <div class="auth-image mb-3">
                                        </div>

                                        <div class="my-auto">
                                            <!-- Swiper -->
                                            <div class="swiper pagination-dynamic-swiper rounded">
                                                <div class="swiper-wrapper">
                                                    <div class="swiper-slide">
                                                        <div class="text-center">
                                                            <h5 class="fs-15 mt-4 text-white mb-0">
                                                                “타문타답 관리자 페이지에
                                                                 오신걸 환영합니다.”
                                                            </h5>
                                                            <p class="fs-15 text-white-50 mt-2 pb-4">
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="swiper-slide">
                                                        <div class="text-center">
                                                            <h5 class="fs-20 mt-4 text-white mb-0">
                                                                “비밀번호를 까먹었다면?”
                                                            </h5>
                                                            <p class="fs-15 text-white-50 mt-2 pb-4">
                                                                총관리자에게 문의 하세요.
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="swiper-slide">
                                                        <div class="text-center">
                                                            <h5 class="fs-20 mt-4 text-white mb-0">
                                                                “오늘의 목표”
                                                            </h5>
                                                            <p class="fs-15 text-white-50 mt-2 pb-4">
                                                                오늘도 밥 값만 하자!
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
                                                <script>document.write(new Date().getFullYear())</script> Nomzie. Crafted with by Themesbrand & Develop by <a href="./backToMain.do">TamumTaDap</a>
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
    <!--Swiper slider js-->
    <script src="../assets/libs/swiper/swiper-bundle.min.js"></script>
    <!-- swiper.init js -->
    <script src="../assets/js/pages/auth.init.js"></script>

    <script src="../assets/js/pages/password-addon.init.js"></script>
</body>
</html>