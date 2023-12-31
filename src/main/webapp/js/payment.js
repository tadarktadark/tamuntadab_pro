$(function(){
	var IMP = window.IMP; // 생략 가능
	IMP.init("imp20843676"); // 가맹점 식별코드, (아임포트 서버에서 발급받아 변경)
});

function request_pay() {//결제 호출

	var methodElement = document.getElementById('payment-method');
    var methodValue = methodElement.options[methodElement.selectedIndex].value;

    var method;

	 if(methodValue === "선택하세요") {
        alert("결제 방법을 선택하세요");
        return;
    } else if(methodValue === "credit-card") {
        method = "html5_inicis";
    } else if(methodValue === "kakao-pay") {
        method = "kakaopay.TC0ONETIME";
    } else if(methodValue === "toss") {
        method = "tosspay";
    } else {
        alert("올바르지 않은 결제 방법입니다.");
        return;
    }
    
   	var holderName = document.getElementById('card-holder-name').textContent; 
	var totalAmount = document.getElementById('card-total-amount').textContent; 
	var phoneNo = document.getElementById('billing-phone-no').textContent;
	var email = document.getElementById('billing-address-line-1').textContent;
	var clasId = document.getElementById('clasId').innerText;
	var paymentId = document.getElementById('invoice-no').textContent;
	var accountId = document.getElementById('accountId').value;
	
	IMP.request_pay({
		pg: method,
		pay_method: "card",
		amount: totalAmount,
		name: "TMTDPAY"+paymentId,
		buyer_name: holderName,
		buyer_tel: phoneNo,
		buyer_email: email
	}, function(rsp) {
		if (rsp.success) {
			jQuery.ajax({
				url: "./doPay.do",
				type: 'POST',
				dataType: 'json',
				data: {
					imp_uid: rsp.imp_uid,
					merchant_uid: rsp.merchant_uid,
					clasId:clasId,
					accountId: accountId,
					method: method
				}
	        }).done(function(data) {
	            alert("결제가 완료되었습니다");
	            location.href = './justGoMyClass.do?clasId='+clasId;
	        }).fail(function(jqXHR, textStatus, errorThrown) {
			    alert("AJAX 호출 실패: " + textStatus + ", " + errorThrown+", "+jqXHR);
			});
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	        alert(msg);
	    }
	});
}

function request_rentPay() {//결제 호출

	var methodElement = document.getElementById('payment-method');
    var methodValue = methodElement.options[methodElement.selectedIndex].value;

    var method;

	 if(methodValue === "선택하세요") {
        alert("결제 방법을 선택하세요");
        return;
    } else if(methodValue === "credit-card") {
        method = "html5_inicis";
    } else if(methodValue === "kakao-pay") {
        method = "kakaopay.TC0ONETIME";
    } else if(methodValue === "toss") {
        method = "tosspay";
    } else {
        alert("올바르지 않은 결제 방법입니다.");
        return;
    }
    
   	var holderName = document.getElementById('card-holder-name').textContent; 
	var totalAmount = document.getElementById('card-total-amount').textContent; 
	var phoneNo = document.getElementById('billing-phone-no').textContent;
	var email = document.getElementById('billing-address-line-1').textContent;
	var gayeId = document.getElementById('clasId').innerText;
	var paymentId = document.getElementById('invoice-no').textContent;
	var accountId = document.getElementById('accountId').value;
	
	IMP.request_pay({
		pg: method,
		pay_method: "card",
		amount: totalAmount,
		name: "TMTDPAY"+paymentId,
		buyer_name: holderName,
		buyer_tel: phoneNo,
		buyer_email: email
	}, function(rsp) {
	    if (rsp.success) {
	        jQuery.ajax({
	            url: "./doRentPay.do",
	            type: 'POST',
	            dataType: 'json',
	            data: {
	                imp_uid: rsp.imp_uid,
	                merchant_uid: rsp.merchant_uid,
	                gayeId: gayeId,
	                accountId: accountId,
	                method: method
	            }
	        }).done(function(data) {
	            alert("결제가 완료되었습니다");
	            location.href = './mypage.do';
	        }).fail(function(jqXHR, textStatus, errorThrown) {
			    alert("AJAX 호출 실패: " + textStatus + ", " + errorThrown+", "+jqXHR);
			});
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	        alert(msg);
	    }
	});
}

//결제취소 
function doF() {
	$.ajax({
		type: "post",
		url: "./cancelPay.do",
		success: function(result) {
			console.log(result);
			location.href = "./beforePay.do";
		}
	})
}


//결제정보 확인
function doCheck() {
	$.ajax({
		type: "post",
		url: "./payInfo.do",
		success: function(result) {
			console.log(result);
			location.href = "./beforePay.do";
		}
	})
}


//환불
 function cancelPay() {
    jQuery.ajax({
      url: "./cancelPay.do", 
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify({
        merchant_uid: "TMTDKAKA20230925-00012526", //주문번호 하드코딩
        cancel_request_amount: 180,
        reason: "테스트 결제 환불",
        refund_holder: "김기훈", 
        refund_bank: "20",
       	refund_account: "1002359356742" //계좌정보 하드코딩
      }),
      dataType: "json"
    });
  }