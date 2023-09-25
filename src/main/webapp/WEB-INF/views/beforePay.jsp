<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp"%>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					
					
					
					<div class="row">
                        <div class="col-12">
                        	<div id="paymentInfo">
						        <p><strong>주문 번호:</strong> <span id="merchantUid"></span></p>
						        <p><strong>구매자 이름:</strong> <span id="buyerName"></span></p>
						        <p><strong>주문명:</strong> <span id="name"></span></p>
						        <p><strong>가격:</strong> <span id="amount"></span></p>
						        <p><strong>주문 상태:</strong> <span id="status"></span></p>
						        <p><strong>결제 수단:</strong> <span id="payMethod"></span></p>
						        <p><strong>결제 시각:</strong> <span id="paidAt"></span></p>
					    	</div>     	
                        </div>
                    </div>
		        	
		        	
		        	
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp" %>
	 <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        function doCheck() {
            $.ajax({
                type: "post",
                url: "./payInfo.do",
                dataType: "json",
                success: function(result) {
                    console.log(result);
                    // 서버로부터 받은 결제 정보를 페이지에 표시
                    $('#merchantUid').text(result.merchantUid);
                    $('#buyerName').text(result.buyerName);
                    $('#name').text(result.name);
                    $('#amount').text(result.amount);
                    $('#status').text(result.status);
                    $('#payMethod').text(result.payMethod);
                    $('#paidAt').text(new Date(result.paidAt * 1000).toLocaleString());
                }
            });
        }
        
        // 페이지 로딩 시 결제 정보 확인
        $(document).ready(function() {
            doCheck();
        });
    </script>
</body>
</html>