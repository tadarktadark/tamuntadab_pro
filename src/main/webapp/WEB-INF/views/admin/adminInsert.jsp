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
<script src="../js/adminInsert.js"></script>
</head>
<body>
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>
		<div class="vertical-overlay"></div>
		<div class="main-content">
			<div class="page-content">
				<%@ include file="./shared/_page_title.jsp"%>
				<div class="container-fluid">
				<div class="row">
				<div class="col-xxl-6">
                               <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title mb-0">관리자 추가</h4>
                                    </div><!-- end card header -->
                                    <div class="card-body">
                                        <form action="./addAdmin.do" method="post" class="row g-3 addAdmin">
                                            <div class="col-md-6">
                                                <label for="addAdminID" class="form-label"><b>ID</b></label>
                                                <input type="text" class="form-control addAdminID" id="addAdminID" name="adminId" placeholder="아이디">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="addAdminName" class="form-label"><b>이름</b></label>
                                                <input type="text" class="form-control addAdminName" id="addAdminName" name="adminName" placeholder="관리자 이름">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="addAdminPW" class="form-label"><b>비밀번호</b></label>
                                                <input type="password" class="form-control addAdminPW" id="addAdminPW" name="adminPW" placeholder="비밀번호">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="addAdminAuth" class="form-label"><b>권한</b></label>
                                                <select id="addAdminAuth" name ="auth" class="form-select addAdminAuth" data-choices data-choices-sorting="true">
                                                    <option selected>권한을 선택해주세요...</option>
                                                    <option>총관리자</option>
                                                    <option>회원관리자</option>
                                                    <option>결제관리자</option>
                                                    <option>게시판관리자</option>
                                                </select>
                                            </div>
                                            <div class="col-12">
                                                <div class="text-end">
                                                    <button type="submit" class="btn btn-primary addAdminBtn">추가하기</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                             </div>
                      </div>
					<div class="col-xxl-6">
	                               <div class="card">
	                                    <div class="card-header">
	                                        <h4 class="card-title mb-0">관리자 IP추가하기</h4>
	                                    </div><!-- end card header -->
	                                    <div class="card-body">
	                                        <form action="./addIp.do" method="post" class="row g-3 addIP">
	                                            <div class="col-md-6">
	                                                <label for="addIPID" class="form-label">ID</label>
	                                                <input type="text" class="form-control addIPID" id="addIPID" name ="adminID" placeholder="등록 할 아이디를 입력해주세요">
	                                            </div>
	                                            <div class="col-md-6">
	                                                <label for="addIP" class="form-label">등록 IP</label>
	                                                <input type="text" class="form-control addIPIP" id="addIP" name ="ip" placeholder="등록할 IP를 입력해주세요">
	                                            </div>
	                                            <div class="col-12">
	                                                <div class="text-end">
	                                                    <button type="submit" class="btn btn-primary addIPBtn">추가하기</button>
	                                                </div>
	                                            </div>
	                                        </form>
	                                    </div>
	                             </div>
	                      </div>
                   </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>