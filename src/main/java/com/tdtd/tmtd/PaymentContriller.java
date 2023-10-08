package com.tdtd.tmtd;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IClassService;
import com.tdtd.tmtd.model.service.IPaymentService;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.PagingVo;
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
	
	@GetMapping("/rentPayment.do")
	public String rentPayment(Model model, HttpSession session, 
									@RequestParam("gayeId") String gayeId) {
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
		gMap.put("gyeoDaesangId", gayeId);
		GeoljeVo gyeoljeVo = pService.getGangGyeoInfo(gMap);
		log.info("PaymentContriller payment 강의실 결제 테이블의 정보 : {} :", gyeoljeVo);
		
		//결제자 정보 조회
		UserProfileVo userVo = pService.getGyeoljejaInfo(userAccountId);
		log.info("PaymentContriller payment 결제자 정보 : {} :", userVo);
		
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
		
		String jsonStr = gyeoljeVo.getYeyakVo().get(0).getGayeGyeoljeUser(); // 데이터베이스로부터 가져온 JSON 문자열

	    Gson gson = new Gson();
	    Map<String, String> map = gson.fromJson(jsonStr, new TypeToken<Map<String, String>>(){}.getType());
	    int users = map.size(); // JSON 키의 개수
		
		
		//사용될 강의실 정보, 결제 정보
		model.addAttribute("gyeoljeVo", gyeoljeVo);
		model.addAttribute("userVo", userVo);
		model.addAttribute("users", users);
		
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
	@ResponseBody
	@PostMapping(value="/doPay.do")
	public Map<String, String> getPay(String merchant_uid, String imp_uid, String clasId, String accountId, String method) {
		log.info("merchant_uid : {}", merchant_uid);
		log.info("imp_uid : {}", imp_uid);
		log.info("clasId : {}", clasId);
		log.info("accountId : {}", accountId);
		this.getToken();
		
		if(method.equals("kakaopay.TC0ONETIME")) {
		    method="K";
		} else if(method.equals("tosspay")) {
		    method="P";
		} else if(method.equals("html5_inicis")) {
		    method="G";
		}
		Map<String, Object> chMap = new HashMap<String, Object>();
		chMap.put("clchGyeoljeStatus", "Y");
		chMap.put("clchClasId", clasId);
		chMap.put("clchAccountId", accountId);
		pService.updatePayStatusInChamyeo(chMap);
		
		GeoljeVo geoljeVo = new GeoljeVo();
		geoljeVo.setGyeoStatus("P");
		geoljeVo.setGyeoUid(imp_uid);
		geoljeVo.setGyeoMid(merchant_uid);
		geoljeVo.setGyeoDaesangId(clasId);
		geoljeVo.setGyeoAccountId(accountId);
		geoljeVo.setGyeoBangbeop(method);
		pService.updatePayStatusInPayment(geoljeVo);
		
		
		List<ChamyeoVo> chamyeoList = cService.getChamyeojas(clasId);
		List<ChamyeoVo> studentList;
		studentList = new ArrayList<>();
		for (ChamyeoVo item : chamyeoList) {
		    if (!"I".equals(item.getClchYeokal())) {
		        studentList.add(item);
		    }
		}
		boolean allMatched = true;
		for (ChamyeoVo student : studentList) {
		    if (!"Y".equals(student.getClchGyeoljeStatus())) {
		        allMatched = false;
		        break;
		    }
		}
		if (allMatched) {
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("clasStatus", "진행");
		    map.put("clasId", clasId);
		    cService.updateClassStatus(map);
		}
		
		Map<String, String> response = new HashMap<>();
	    response.put("status", "Success");
	    return response;
	}
	
	@ResponseBody
	@PostMapping(value="/doRentPay.do")
	public Map<String, String> getRentPay(String merchant_uid, String imp_uid, String gayeId, String accountId, String method) {
		log.info("merchant_uid : {}", merchant_uid);
		log.info("imp_uid : {}", imp_uid);
		log.info("gayeId : {}", gayeId);
		log.info("accountId : {}", accountId);
		this.getToken();
		
		if(method.equals("kakaopay.TC0ONETIME")) {
		    method="K";
		} else if(method.equals("tosspay")) {
		    method="P";
		} else if(method.equals("html5_inicis")) {
		    method="G";
		}
		
		GeoljeVo geoljeVo = new GeoljeVo();
		geoljeVo.setGyeoStatus("P");
		geoljeVo.setGyeoUid(imp_uid);
		geoljeVo.setGyeoMid(merchant_uid);
		geoljeVo.setGyeoDaesangId(gayeId);
		geoljeVo.setGyeoAccountId(accountId);
		geoljeVo.setGyeoBangbeop(method);
		int n =pService.updatePayStatusInPayment(geoljeVo);
		
		if (n == 1) {
			log.info("getRentPay updateYeyakStatusInPayment 결제 테이블 결제상태 업데이트 성공");
		}else {
			log.info("getRentPay updateYeyakStatusInPayment 결제 테이블 결제상태 업데이트 실패");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("gayeId", gayeId);
		int m = pService.updateYeyakStatusInPayment(map);
		
		if (m == 1) {
			log.info("updateYeyakStatusInPayment 예약 테이블 결제상태 업데이트 성공");
		}else {
			log.info("updateYeyakStatusInPayment 예약 테이블 결제상태 업데이트 실패");
		}
		
		Map<String, String> response = new HashMap<>();
	    response.put("status", "Success");
	    return response;
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
	
	@PostMapping(value = "/getClassPaymentList.do")
	@ResponseBody
	public String getClassPaymentList(Model model, HttpSession session, 
			@RequestParam("page") String pageAttr) {
		
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		if (userInfo != null) {
			log.info("PaymentContriller getClassPaymentList 세션의 유저 정보: {}", userInfo);
		} else {
			log.info("PaymentContriller getClassPaymentList 세션의 유저 정보 : 정보없음");
		}
		String userAccountId = (userInfo != null) ? userInfo.getUserAccountId() : null;
		
		log.info("PaymentContriller getClassPaymentList 가져온 현재 페이지 = {}", pageAttr);
		int thisPage = 0;
		if (pageAttr == null) {
			thisPage = 1;
		} else {
			thisPage = Integer.parseInt(pageAttr);
		}
		
		int totalCount = pService.myPageClassPaymentListCount(userAccountId);
		
		log.info("PaymentContriller getClassPaymentList 형변환한 페이지 : {}",thisPage);
		// 페이지에 사용될 정보 담기
		PagingVo pVo = new PagingVo();
		pVo.setTotalCount(totalCount);
		pVo.setCountList(10);
		pVo.setCountPage(5);
		pVo.setTotalPage(pVo.getTotalPage());
		pVo.setPage(thisPage);
		pVo.setStartPage(pVo.getPage());
		pVo.setEndPage(pVo.getPage());
		
		log.info("PaymentContriller getClassPaymentList 가져온 페이지 정보 : {}", pVo);
		Map<String, Object> pagingMap = PagingUtils.paging(pageAttr, pVo.getTotalCount(), pVo.getCountList(),
				pVo.getCountPage());
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("first", pagingMap.get("start"));
		listMap.put("last", pagingMap.get("end"));
		listMap.put("gyeoAccountId", userAccountId);
		
		List<GeoljeVo> gVoList = pService.myPageClassPaymentList(listMap);
		
		Map<String, Object> result = new HashMap<>();
		result.put("gVoList", gVoList);
		result.put("pVo", pVo);
		
		Gson gson = new Gson();

		return gson.toJson(result);
	}
	
	
	@PostMapping(value = "/getYeyakPaymentList.do")
	@ResponseBody
	public String getYeyakPaymentList(Model model, HttpSession session, 
			@RequestParam("page") String pageAttr) {
		
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		if (userInfo != null) {
			log.info("PaymentContriller getClassPaymentList 세션의 유저 정보: {}", userInfo);
		} else {
			log.info("PaymentContriller getClassPaymentList 세션의 유저 정보 : 정보없음");
		}
		String userAccountId = (userInfo != null) ? userInfo.getUserAccountId() : null;
		
		log.info("PaymentContriller getClassPaymentList 가져온 현재 페이지 = {}", pageAttr);
		int thisPage = 0;
		if (pageAttr == null) {
			thisPage = 1;
		} else {
			thisPage = Integer.parseInt(pageAttr);
		}
		
		int totalCount = pService.myPageRoomPaymentListCount(userAccountId);
		
		log.info("PaymentContriller getClassPaymentList 형변환한 페이지 : {}",thisPage);
		// 페이지에 사용될 정보 담기
		PagingVo pVo = new PagingVo();
		pVo.setTotalCount(totalCount);
		pVo.setCountList(10);
		pVo.setCountPage(5);
		pVo.setTotalPage(pVo.getTotalPage());
		pVo.setPage(thisPage);
		pVo.setStartPage(pVo.getPage());
		pVo.setEndPage(pVo.getPage());
		
		log.info("PaymentContriller getClassPaymentList 가져온 페이지 정보 : {}", pVo);
		Map<String, Object> pagingMap = PagingUtils.paging(pageAttr, pVo.getTotalCount(), pVo.getCountList(),
				pVo.getCountPage());
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("first", pagingMap.get("start"));
		listMap.put("last", pagingMap.get("end"));
		listMap.put("gyeoAccountId", userAccountId);
		
		List<GeoljeVo> gVoList = pService.myPageRoomPaymentList(listMap);
		log.info("PaymentContriller getClassPaymentList 리스트 정보 : {}", gVoList);
		
		Map<String, Object> result = new HashMap<>();
		result.put("gVoList", gVoList);
		result.put("pVo", pVo);
		
		Gson gson = new Gson();

		return gson.toJson(result);
	}
}
