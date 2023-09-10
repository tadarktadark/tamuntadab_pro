package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.mapper.ICommUserDao;
import com.tdtd.tmtd.model.service.ICommUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private ICommUserService commUserService;
	
	@Autowired
	private JavaMailSender mailsender;
	
	@RequestMapping(value = "/regist.do", method=RequestMethod.GET)
	public String registForm() {
		log.info("###regist.do###");
		return "registpage";
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
	
	@RequestMapping(name="/confirmPhone.do", method=RequestMethod.POST)
	public String sendSMS(String phonenumber) {
		return "";
	}
}