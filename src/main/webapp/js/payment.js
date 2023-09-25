
$(function(){
	var IMP = window.IMP; // 생략 가능
	IMP.init("imp20843676"); // 가맹점 식별코드, (아임포트 서버에서 발급받아 변경)
});

function requestPay1() {
    IMP.request_pay({
      pg: "kcp",
      pay_method: "card",
      merchant_uid: "ORD20180131-0000011",   // 주문번호
      name: "노르웨이 회전 의자",
      amount: 64900,                         // 숫자 타입
      buyer_email: "gildong@gmail.com",
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    }, function (rsp) { // callback
      //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
    });
  }

function requestPay3() {
	// IMP.request_pay(param, callback) 결제창 호출
	IMP.request_pay({ // param
		pg: "kakaopay.TC0ONETIME", // 카카오페이 방식
		pay_method: "card",
		merchant_uid: "TMTDKAKA20230925-00012526", // 주문번호
		name: "kakaoApi결제 테스트",
		amount: 100,
		buyer_email: "loveluv777@gmail.com",
		buyer_name: "김기훈",
		buyer_tel: "010-3103-8087"
	}, function(rsp) { // callback
		if (rsp.success) {
			var msg = '결제가 완료되었습니다.';
			msg += '\n고유ID : ' + rsp.imp_uid;
			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
			msg += '\n결제 금액 : ' + rsp.paid_amount;
			alert(msg);
			jQuery.ajax({
				url: "./doPay.do",
				type: 'POST',
				dataType: 'json',
				data: {
					imp_uid: rsp.imp_uid,
					merchant_uid: rsp.merchant_uid
				}
			}).done(function(data) {
				alert("결제 성공");
			});
		} else {
			alert("실패 : " + rsp.error_msg);
		}
	});
}


function request_pay2() {//토스 결제방식 호출
	IMP.request_pay({
		pg: "tosspay",
		pay_method: "card",
		amount: 180,
		name: "TMTDTOSS20230925-00012526",
		buyer_name: "김기훈",
		buyer_tel: "010-3103-8087",
		buyer_email: "loveluv777@gmail.com"
	}, function(rsp) {
		if (rsp.success) {
			var msg = '결제가 완료되었습니다.';
			msg += '\n고유ID : ' + rsp.imp_uid;
			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
			msg += '\n결제 금액 : ' + rsp.paid_amount;
			msg += '\n카드 승인번호 : ' + rsp.apply_num;
			alert(msg);
			//서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
			jQuery.ajax({
				url: "./doPay.do",
				type: 'POST',
				dataType: 'json',
				data: {
					imp_uid: rsp.imp_uid,
					merchant_uid: rsp.merchant_uid
					//기타 필요한 데이터가 있으면 추가 전달
				}
			}).done(function(data) {
				alert("결제완료");
			});
			//다음페이지 이동코드 작성
			location.href = './beforePay';
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
        merchant_uid: "TMTDKAKA20230925-00012526",
        cancel_request_amount: 180, // 환불금액
        reason: "테스트 결제 환불",
        refund_holder: "김기훈", 
        refund_bank: "20",
       	refund_account: "1002359356742"
      }),
      dataType: "json"
    });
  }