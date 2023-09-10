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
				<div class="container  bg-white">
				   <section class="auth-page-wrapper py-5 position-relative d-flex align-items-center justify-content-center">
				        <div class="container">
				        	<div class="row">
				        		<div class="text-center">
					   				<h1>회원가입</h1>
					   			</div>
				        		<div class="col-lg-12">
				        			<form action="javascript:void(0);" class="row g-3 mt-1">
									    <div class="col-md-12 ">
									   		<label for="inputEmail4" class="form-label"><b>이메일</b></label>
									   		<div class="input-group">
										        <input type="email" name="email" class="form-control" id="email" placeholder="Email">
										        <button type="button" class="btn btn-primary w-lg" onclick="confirmMail()">이메일 인증</button>
									        </div>
									    </div>
									    <div class="col-md-6">
									           <label for="fullnameInput" class="form-label"><b>비밀번호</b></label>
									        <input type="password" class="form-control" id="inputPassword2" placeholder="Password">
									    </div>
									    <div class="col-md-6">
									        <label for="inputPassword4" class="form-label"><b>비밀번호 확인</b></label>
									        <input type="password" class="form-control" id="inputPassword4" placeholder="Password Confirm">
									    </div>
									    <div class="col-md-6">
									        <label for="inputPhoneNumber" class="form-label"><b>핸드폰 번호</b></label>
									        <div class="input-group">
									       		<input type="text" class="form-control" id="inputPhoneNumber" placeholder="Enter Your Phone NUmber">
										        <button type="button" class="btn btn-primary w-lg" onclick="confirmPhone()">핸드폰 인증</button>
									        </div>
									    </div>
									    <div class="col-md-2">
									    	<label for="" class="form-label"><b>성별</b></label>
									    		<div class="form-check">
												    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
												    <label class="form-check-label" for="flexRadioDefault1">
												        Male
												    </label>
												</div>
												<div class="form-check">
												    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
												    <label class="form-check-label" for="flexRadioDefault2">
												        Female
												    </label>
												</div>
									    </div>
									    <div class="col-md-4">
									        <label for="inputZip" class="form-label"><b>생년월일</b></label>
										    <input type="date" class="form-control" id="exampleInputdate">
									    </div>
	    						        <div class="text-center">
					           				 <button type="submit" class="btn btn-primary col-lg-12">회원가입</button>
					     			   </div>
									</form>
				        		</div><!-- end col-7 -->
				        	</div>
				        </div><!--end container-->
    				</section><!--end section -->
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" src="./js/confirmEmail.js"></script>
	<script type="text/javascript" src="./js/confirmPhone.js"></script>
	</body>
</html>