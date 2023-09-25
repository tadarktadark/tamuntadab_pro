package com.tdtd.tmtd;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PaymentContriller {

	//페이지 이동
	@GetMapping("/payment.do")
	public String payment(Model model) {
		model.addAttribute("title", "결제");
		model.addAttribute("pageTitle", "결제 처리");
		
		String gyeoId = "cl10000006";
		
		model.addAttribute(gyeoId);
		return "payment";
	}
	
	//Iamport 객체 생성
	IamportClient client;
	
	//클라이언트 생성
	IamportClient getTmtdClient() {
		String test_api_key = "7067762145628325"; 
		String test_api_secret = "04sZnp1EjV1czvg3EMEbG7WuClONdywb8I4UoA5Utmu6ySihOLzjows52ig4A6eEgU3oegXQhFjsXneI";
		return new IamportClient(test_api_key, test_api_secret);
	}
	
	public PaymentContriller() {
		this.client = this.getTmtdClient();
	}
	
	//토큰 받아 오기
	void getToken() {
		try {
			IamportResponse<AccessToken> auth_response = client.getAuth();
			assertNotNull(auth_response.getResponse());
			assertNotNull(auth_response.getResponse().getToken());
			
			System.out.println("get token str: " + auth_response.getResponse().getToken());
			
		}catch(IamportResponseException e){
			System.out.println(e.getMessage());
			
			switch(e.getHttpStatusCode()) {
			case 401 : System.out.println("401");break;
			case 500 : System.out.println("500");break;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//결제한 후 서버로 데이터 전송 및 DB 입력 위해 값 받아오기
	@PostMapping(value="/doPay.do")
	public void getPay(String merchant_uid, String imp_uid) {
		log.info("merchant_uid : {}", merchant_uid);
		log.info("imp_uid : {}", imp_uid);
		this.getToken();
	}
	
	@RequestMapping(value = "/cancelPay.do", method = RequestMethod.POST)
	public String CancelPayment() {
        String cancelUid = "imp_390490419583"; //imp_uid
        CancelData cancel_data = new CancelData(cancelUid, true); 
        System.out.println("환불 완료");
        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);
           
            if(payment_response.getResponse() == null) {
            	System.out.println("이미 처리된 환불입니다");
            }
        } catch (IamportResponseException e) {
            System.out.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    break;
                case 500:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "beforePay";
    }


	@RequestMapping(value = "/beforePay.do" ,method = RequestMethod.GET)
	public String nextPage() {
		log.info("beforePay 진입");
		return "beforePay";
	}

	
	@PostMapping(value = "/payInfo.do")
	@ResponseBody
	public String getPayInfo() {
		
		String test_Imp_uid = "imp_390490419583";
		
		try {
			IamportResponse<Payment> pay_response = client.paymentByImpUid(test_Imp_uid);
			
			System.out.println(pay_response.getResponse().getMerchantUid()); //주문번호 o
			System.out.println(pay_response.getResponse().getBuyerName()); //구매자이름 o
			System.out.println(pay_response.getResponse().getName()); // 주문명 o
			System.out.println(pay_response.getResponse().getAmount()); // 가격 o
			System.out.println(pay_response.getResponse().getStatus()); // 주문상태 o
			System.out.println(pay_response.getResponse().getPayMethod()); // 결제수단 o
			System.out.println(pay_response.getResponse().getPaidAt()); // 결제시각 o
			
		} catch (IamportResponseException e) {
			
			System.out.println(e.getMessage());
			
			switch(e.getHttpStatusCode()) {
			case 401: System.out.println("401");break;
			case 500: System.out.println("500");break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "paySuccess";
	}
}
