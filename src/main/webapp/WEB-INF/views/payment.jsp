<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp"%>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
					<%@ include file="./shared/_page_title.jsp" %>
					
					
					
					<div class="row">
                        <div class="col-12">
                        	<div class ="btns">
                        		<input type="button" id="check2" onclick="request_pay1()" value="이니시스">
								<input type="button" id="check3" onclick="request_pay2()" value="토스">
								<input type="button" id="check4" onclick="requestPay3()" value="카카오페이">
								<h1><a href="javascript:doCheck()">정보확인</a></h1>
								<a href="javascript:doF()">결제 취소</a>
								<button onclick="cancelPay()">환불하기</button>
							</div>       	
                        </div>
                    </div>
		        	
		        	
		        	
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script type="text/javascript" src="./js/payment.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
</body>
</html>