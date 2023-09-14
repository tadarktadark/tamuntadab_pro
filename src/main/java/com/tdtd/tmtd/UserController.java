package com.tdtd.tmtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.service.ICommUserService;
import com.tdtd.tmtd.vo.ClientVo;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
@Slf4j
public class UserController {
	

	Gson gson = new Gson();
	
	@Autowired
	private ICommUserService commUserService;
	
	
	@Autowired
	private JavaMailSender mailsender;
	
	@RequestMapping(value = "/regist.do", method=RequestMethod.GET)
	public String registForm() {
		return "regist";
	}
	
	@RequestMapping(value = "/searchEmail.do", method =RequestMethod.POST)
	@ResponseBody
	public Boolean searchEmail(@RequestParam Map<String,String> map) {
		return commUserService.searchEmailService(map);
	}
	
	@RequestMapping(value = "/sendMail.do", method =RequestMethod.POST)
	@ResponseBody
	public String sendmail(@RequestParam Map<String,String> map) {
		log.info("받은 매일 {}",map.get("userEmail"));
		
		Map<String,String> sendmap = new HashMap<String, String>();
		
		MimeMessage msg = mailsender.createMimeMessage();
		
		try {
			MimeMessageHelper msgHelper
					= new MimeMessageHelper(msg,false,"UTF-8");
			map.put("code", UUID.randomUUID().toString().substring(0,8));
			msgHelper.setFrom("juojuo9809@naver.com");
			msgHelper.setTo(map.get("userEmail"));
			msgHelper.setSubject("타문타답 인증번호"+map.get("code"));
			msgHelper.setText("타문타답 인증번호"+map.get("code"));
			map.put("isc", "true");
			mailsender.send(msg);
		} catch (MessagingException e) {
			map.put("isc", "false");
		}
		Gson gson = new Gson();
		String result = gson.toJson(map);
		return result;
	}
	
	@RequestMapping(value="/sendSMS.do", method=RequestMethod.POST)
	@ResponseBody
	public String sendSMS(@RequestParam String phoneNumber) {
		Map<String,String> sendMap = new HashMap<String, String>();
		
		//랜덤 난수 발생
		Random ran = new Random();
		sendMap.put("code", ""+ran.nextInt(10000));
		//coolSMS API사용
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSLH5XMRHNML98D", "SCDV2ABM6KXGAUPYYEWZEL1C92RU0NOJ", "https://api.coolsms.co.kr");
		Message message = new Message();
		message.setFrom("01022546438");
		message.setTo(phoneNumber);
		message.setText("타문타답 문자 인증 번호 : "+sendMap.get("code"));
		try {
		  messageService.send(message);
		  sendMap.put("isc", "true");
		} catch (NurigoMessageNotReceivedException exception) {
		  System.out.println(exception.getFailedMessageList());
		  System.out.println(exception.getMessage());
		} catch (Exception exception) {
			sendMap.put("isc", "false");
		  System.out.println(exception.getMessage());
		}
		String result = gson.toJson(sendMap);
		return result;
	}
	
	@RequestMapping(value = "/registration.do", method = RequestMethod.POST)
	public String registration(@RequestParam Map<String,Object> map) {
			map.put("userAutoLoginToken",(UUID.randomUUID())+(map.get("userPassword").toString().substring(0, 4))+(map.get("userGender"))+(map.get("userAuth"))+(map.get("userEmail").toString().substring(0, 2)));
			int n = commUserService.registCommUser(map);
			if(n!=1) {
				return "redirect:/regist.do";
			}else {
				return "redirect:/";
			}
	}
	
	@RequestMapping(value="/registForm.do",method= RequestMethod.GET)
	public String registinputForm() {
		return "registform";
	}
	
	@RequestMapping(value="/mypage.do")
	public String mypage() {
		return "myPage";
	}
	
	@RequestMapping(value="jeongji.do")
	public String jeongji(HttpServletResponse resp, HttpSession session) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print("<script>alert('정지된 계정입니다.');</script>");
		out.flush();
		session.removeAttribute("userInfo");
		return "index";
	}
	
	@RequestMapping(value="human.do")
	public String human(HttpServletResponse resp, HttpSession session) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print("<script>alert('휴면 정지된 계정입니다.');</script>");
		out.flush();
		session.removeAttribute("userInfo");
		return "human";
	}
}