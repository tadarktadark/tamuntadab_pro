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
										        <script type="text/javascript">
										        	function confirmMail(){
										        		var email = document.getElementById("email").value;
										        		console.log(email);
										        		if(email == ""){
									        		        Swal.fire({
									        		            position: 'top-center',
									        		            icon: 'warning',
									        		            title: '이메일을 입력해주세요',
									        		            showConfirmButton: false,
									        		            timer: 1500,
									        		            showCloseButton: true
									        		        })
										        		}else{
										        			$.ajax({
										    					url: "./sendMail.do",
										    					data: { userEmail: email},
										    					type: "POST",
										    					dataType: "json",
										    					success: function(result) {
										    						if(result.isc == "true"){
										    					        Swal.fire({
										    					        	html: '<div class="mt-3">' +
										    				                '<div class="avatar-lg mx-auto">' +
										    				                '<div class="avatar-title bg-light text-success display-5 rounded-circle">' +
										    				                '<i class="ri-mail-send-fill"></i>' +
										    				                '</div>' +
										    				                '</div>' +
										    				                '<div class="mt-4 pt-2 fs-15">' +
										    				                '<h4 class="fs-20 fw-semibold">인증번호를 입력해주세요</h4>' +
										    				                '</div>' +
										    				                '</div>',
										    				                input:'text',
										    				                timer:300000,
										    				                timerProgressBar: true,
										    					            customClass: {
										    					                confirmButton: 'btn btn-primary w-xs mb-2',
										    					            },
										    					            confirmButtonText: 'Register <i class="ri-arrow-right-line ms-1 align-bottom"></i>',
										    					            buttonsStyling: false,
										    					            showCloseButton: true,
										    					            preConfirm:function(inputcode){
										    					            		if(inputcode==result.code){
										    					            			Swal.fire({
																        		            icon: 'info',
																        		            title: '인증번호에 성공하였습니다!',
																        		            showConfirmButton: true,
																        		            timer: 1500,
																        		            showCloseButton: false
																        		        })
										    					            		}else{
										    					            			Swal.fire({
																        		            icon: 'warning',
																        		            title: '인증번호을 실패하였습니다.',
																        		            showConfirmButton: false,
																        		            timer: 1500,
																        		            showCloseButton: true
																        		        })
										    					            		}
										    					            }
										    					        })
										    						}else{
										    							Swal.fire({
												        		            position: 'top-center',
												        		            icon: 'warning',
												        		            title: '인증번호 전송을 실패하였습니다.',
												        		            showConfirmButton: false,
												        		            timer: 1500,
												        		            showCloseButton: true
												        		        })
										    						}
										    					},
										    					error: function(result) {
										    					}
										        			})
										        		}
										        	}
										        </script>
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
										        <button type="button" class="btn btn-primary w-lg">핸드폰 인증</button>
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
	<script type="text/javascript" src="./js/regist.js"></script>
	</body>
</html>