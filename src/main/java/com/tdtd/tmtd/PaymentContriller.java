package com.tdtd.tmtd;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.tdtd.tmtd.model.service.IClassService;
import com.tdtd.tmtd.model.service.IPaymentService;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.SugangryoVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PaymentContriller {
	
	@Autowired
	private IClassService cService;
	
	@Autowired
	private IPaymentService pService;


	//페이지 이동
	@GetMapping("/payment.do")
	public String payment(Model model, HttpSession session, 
									@RequestParam("clasId") String clasId) {
		model.addAttribute("title", "결제");
		model.addAttribute("pageTitle", "결제 처리");
		
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		if (userInfo != null) {
			log.info("PaymentContriller payment 세션의 유저 정보: {}", userInfo);
		} else {
			log.info("PaymentContriller payment 세션의 유저 정보 : 정보없음");
		}
		String userAccountId = (userInfo != null) ? userInfo.getUserAccountId() : null;
		
		//결제 테이블 조회
		Map<String, Object> gMap = new HashMap<String, Object>();
		gMap.put("gyeoAccountId", userAccountId);
		gMap.put("gyeoDaesangId", clasId);
		GeoljeVo gyeoljeVo = pService.getGyeoInfo(gMap);
		log.info("PaymentContriller payment 결제 테이블의 정보 : {} :", gyeoljeVo);
		
		//결제자 정보 조회
		UserProfileVo userVo = pService.getGyeoljejaInfo(userAccountId);
		log.info("PaymentContriller payment 결제자 정보 : {} :", userVo);
		
		SugangryoVo sugangryoVo = cService.getRequestedSugangryo(clasId);
		
		try {
			String gyeoRegdateStr = (gyeoljeVo != null) ? gyeoljeVo.getGyeoRegdate() : null; 
			if (gyeoRegdateStr != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime gyeoRegdate = LocalDateTime.parse(gyeoRegdateStr, formatter);
				LocalDateTime paymentDueDate = gyeoRegdate.plusDays(7);

				// 요일, 시간 등을 포함한 포맷으로 변환
				DateTimeFormatter dueDateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm", Locale.KOREAN);
				String formattedDueDate = paymentDueDate.format(dueDateFormatter);

				// 남은 일자 계산
				long daysRemaining = ChronoUnit.DAYS.between(LocalDateTime.now(), paymentDueDate);
				
				model.addAttribute("formattedDueDate", formattedDueDate);
				model.addAttribute("daysRemaining", daysRemaining);
			} else {
				// gyeoRegdateStr가 null인 경우 처리 로직(로그 출력, 예외 처리 등)
				log.warn("PaymentContriller payment gyeoRegdateStr is null");
			}
		} catch (DateTimeParseException e) {
			// 날짜 변환에 실패한 경우 처리 로직(로그 출력, 예외 처리 등)
			log.error("PaymentContriller payment DateTimeParseException: {}", e.getMessage());
		}
		
		//사용될 클래스 정보
		ClassVo classVo = cService.getClassDetail(clasId); 
		
		model.addAttribute("gyeoljeVo", gyeoljeVo);
		model.addAttribute("sugangryoVo", sugangryoVo);
		model.addAttribute("userVo", userVo);
		model.addAttribute("classVo", classVo);
		
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
			case 401 : log.info("PaymentContriller getToken 401 에러 발생"); break;
			case 500 : log.info("PaymentContriller getToken 500 에러 발생"); break;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//결제한 후 서버로 데이터 전송 및 DB 입력 위해 값 받아오기
	@PostMapping(value="/doPay.do")
	public void getPay(String merchant_uid, String imp_uid, String clasId, String accountId) {
		log.info("merchant_uid : {}", merchant_uid);
		log.info("imp_uid : {}", imp_uid);
		this.getToken();
		
		GyeoljeVo geoljeVo = new GyeoljeVo();
		geoljeVo.setGyeoStatus("P");
		geoljeVo.setGyeoUid(imp_uid);
		geoljeVo.setGyeoMid(merchant_uid);
		geoljeVo.setGyeoDaesangId(clasId);
		geoljeVo.setGyeoAccountId(accountId);
		pService.updatePayStatusInPayment(geoljeVo);
	}
	
	//환불
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


	//지불 후 이동 페이지(결과창)
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
