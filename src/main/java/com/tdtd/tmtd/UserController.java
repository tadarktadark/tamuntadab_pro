package com.tdtd.tmtd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.mapper.ICommUserDao;
import com.tdtd.tmtd.model.service.ICommUserService;
import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.model.service.IReviewService;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;
import com.tdtd.tmtd.vo.UserProfileVo;

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
	private IReviewService reviewService;
	
	@Autowired
	private ICommUserDao commuserDao;

	@Autowired
	private JavaMailSender mailsender;
	
	@RequestMapping(value="/deleteUser.do",method=RequestMethod.GET)
	@ResponseBody
	public String deleteUser(HttpSession session) {
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		String isc = commUserService.checkPayment(userInfo);
		if(isc.equals("false")) {
			return "payment";
		}
		int n = commUserService.updateUserDelflagToY(userInfo);
		if(n>0) {
			session.invalidate();
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/updateNickname.do",method = RequestMethod.POST)
	@ResponseBody
	public String updateNickname(@RequestParam(name = "nickname") String nickname, HttpSession session) {
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		Map<String,Object> updateInfo = new HashMap<String, Object>();
		updateInfo.put("userAccountId", userInfo.getUserAccountId());
		updateInfo.put("userSite", userInfo.getUserSite());
		updateInfo.put("changeNickName", nickname.trim());
		
		String isc = commUserService.searchNickName(updateInfo);
		if(isc.equals("true")) {
			userInfo.setUserNickname(nickname);
			session.setAttribute("userInfo", userInfo);
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "uploadImg.do", method = RequestMethod.POST)
	@ResponseBody
	public String uplodaImg(@RequestParam MultipartFile file, HttpSession session, HttpServletRequest req)
			throws IOException {
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		String originalFileName = file.getOriginalFilename();
		String saveFileName = UUID.randomUUID().toString()
				+ originalFileName.substring(originalFileName.lastIndexOf("."));
		InputStream inputStream = file.getInputStream();
		OutputStream outputStream = null;
		
		log.info("{}",req.getServletContext().toString()+"upload");
		// 2) 저장 위치를 만든다
		String path = WebUtils.getRealPath(req.getSession().getServletContext(), "/userProfile");// 상대경로

		// 3) 저장 위치가 없으면 만든다
		File storage = new File(path);
		if (!storage.exists()) {
			storage.mkdir();
		}
		File newFile = new File(path+"/"+saveFileName);
		
		outputStream = new FileOutputStream(newFile);
		
		//기본 이미지가 아닐 경우 기존 파일 삭제 후 변경
		if (!userInfo.getUserProfileFile().equals("./image/profile.png")) {
			String userProfileFile = userInfo.getUserProfileFile();
			File beforefile = new File(userProfileFile);
			String fileName = beforefile.getName();
			// 삭제할 파일 경로
			String filePath = path;
			// 파일 객체 생성
			File oldProfile = new File(filePath+"/"+fileName);
			log.info("{}",oldProfile);
			oldProfile.delete();
		}
		//파일 업로드 하기
		 int read = 0;
		 byte[] b = new byte[(int)file.getSize()];
		 while ((read=inputStream.read(b))!= -1) {
			 	outputStream.write(b,0,read);
		 		}
		 outputStream.close();
		 
		// 웹 상에서 접근 가능한 상대 경로로 변환
		String webPath = path.substring(path.indexOf("tamuntadab_pro"));

		// 슬래시(/)로 경로를 통일시킵니다
		webPath = webPath.replace("\\", "/");
		
		String newProfile = "/"+webPath+"/"+saveFileName;
		
		Map<String,Object> updateInfo = new HashMap<String, Object>();
		updateInfo.put("userAccountId", userInfo.getUserAccountId());
		updateInfo.put("userSite", userInfo.getUserSite());
		updateInfo.put("changeProfile", newProfile);
		
		int n = commUserService.updateUserInfo(updateInfo);
		if(n>0) {
			userInfo.setUserProfileFile(newProfile);
			session.setAttribute("userInfo", userInfo);
			return "true";
		}
		return "false";
	};

	@RequestMapping(value = "updatePassword.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String updatePassword(@RequestParam Map<String, Object> map, HttpServletResponse resp, HttpServletRequest req, HttpSession session) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		int checkisc = 0;
		if(session.getAttribute("userInfo")!=null) {
			UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
			map.put("userAccountId",userInfo.getUserAccountId());
			
			checkisc = commUserService.checkPassword(map);
			if(checkisc==1) {
				return "<script>alert('기존 비밀번호와 같은 비밀번호로는 변경할 수 없습니다.');location.href=history.back();</script>";
			}
			int m = commuserDao.updateUserPassword(map);
			if(m>0) {
				return "<script>alert('비밀번호가 변경되었습니다.'); location.href='./';</script>";
			}else {
				return "<script>alert('관리자에게 문의해 주세요.');";
			}
		}
		int n = commUserService.updateUserPassword(map);
		if(checkisc==1) {
			return "<script>alert('기존 비밀번호와 같은 비밀번호로는 변경할 수 없습니다.');location.href=history.back();</script>";
		}
		if (n > 0) {
			return "<script>alert('비밀번호가 변경되었습니다.'); location.href='./';</script>";
		} else {
			return "<script>alert('관리자에게 문의해 주세요.');";
		}
	}

	@RequestMapping(value = "updatePassword.do", method = RequestMethod.GET)
	public String updatePasswordForm() {
		return "updatePasswordForm";
	}

	@RequestMapping(value = "sendToken.do")
	@ResponseBody
	public String sendToken(@RequestParam Map<String, Object> userInfo, HttpServletRequest req) {
		userInfo.put("tokenValue", UUID.randomUUID().toString().substring(0, 36));
		int n = commUserService.updateResetPwToken(userInfo);
		MimeMessage msg = mailsender.createMimeMessage();
		try {
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, false, "UTF-8");
			msgHelper.setFrom("juojuo9809@naver.com");
			msgHelper.setTo(userInfo.get("userEmail").toString());
			msgHelper.setSubject("[타문타답] 비밀번호 초기화를 위한 링크입니다.");
			msgHelper.setText(
					"비밀번호 초기화 주소 :<a href='" + req.getRequestURL().substring(0, req.getRequestURL().lastIndexOf("/"))
							+ "/updatePassword.do?tokenValue=" + userInfo.get("tokenValue") + "'>" + ""
							+ req.getRequestURL().substring(0, req.getRequestURL().lastIndexOf("/"))
							+ "/updatePassword.do?tokenValue=" + userInfo.get("tokenValue") + "</a>",
					true);
			mailsender.send(msg);
		} catch (MessagingException e) {
		}
		return n == 1 ? "true" : "false";
	}

	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.GET)
	public String resetPasswrod(HttpServletRequest req) {
		log.info("{}", req.getRequestURL().substring(0, req.getRequestURL().lastIndexOf("/")));
		return "resetPasswordForm";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:main.do";
	}

	@RequestMapping(value = "/loginForm.do")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam Map<String, String> map, HttpSession session, HttpServletRequest req) {
		Map<String, Object> result = commUserService.commLogin(map);
		if (result.get("userInfo") != null) {
			session.setAttribute("userInfo", result.get("userInfo"));
		}else if(result.get("status").equals("human")) {
			Map<String,Object> userInfo = new HashMap<String, Object>();
			userInfo.put("tokenValue", UUID.randomUUID().toString().substring(0, 36));
			userInfo.put("userEmail", map.get("userEmail"));
			commUserService.updateResetPwToken(userInfo);
			MimeMessage msg = mailsender.createMimeMessage();
			try {
				MimeMessageHelper msgHelper = new MimeMessageHelper(msg, false, "UTF-8");
				msgHelper.setFrom("juojuo9809@naver.com");
				msgHelper.setTo(map.get("userEmail"));
				msgHelper.setSubject("[타문타답] 휴면해제를 위한 링크입니다.");
				msgHelper.setText(
						"휴면해제 주소 :<a href='" + req.getRequestURL().substring(0, req.getRequestURL().lastIndexOf("/"))
								+ "/updatehuman.do?tokenValue=" + userInfo.get("tokenValue") + "'>" + ""
								+ req.getRequestURL().substring(0, req.getRequestURL().lastIndexOf("/"))
								+ "/updatehuman.do?tokenValue=" + userInfo.get("tokenValue") + "</a>",
						true);
				mailsender.send(msg);
				} catch (MessagingException e) {
			}
		}
		return gson.toJson(result);
	}

	@RequestMapping(value = "/autoLogin.do")
	@ResponseBody
	public String autoLogin(@RequestParam String userAutoLoginToken, HttpSession session) {
		UserProfileVo userInfo = commUserService.autoLogin(userAutoLoginToken);
		if (userInfo != null) {
			session.setAttribute("userInfo", userInfo);
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping(value = "/regist.do", method = RequestMethod.GET)
	public String registForm() {
		return "regist";
	}

	@RequestMapping(value = "/searchEmail.do", method = RequestMethod.POST)
	@ResponseBody
	public Boolean searchEmail(@RequestParam Map<String, String> map) {
		return commUserService.searchEmailService(map);
	}

	@RequestMapping(value = "/sendMail.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendmail(@RequestParam Map<String, String> map) {
		log.info("받은 매일 {}", map.get("userEmail"));

		Map<String, String> sendmap = new HashMap<String, String>();

		MimeMessage msg = mailsender.createMimeMessage();

		try {
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, false, "UTF-8");
			map.put("code", UUID.randomUUID().toString().substring(0, 8));
			msgHelper.setFrom("juojuo9809@naver.com");
			msgHelper.setTo(map.get("userEmail"));
			msgHelper.setSubject("타문타답 인증번호" + map.get("code"));
			msgHelper.setText("타문타답 인증번호" + map.get("code"));
			map.put("isc", "true");
			mailsender.send(msg);
		} catch (MessagingException e) {
			map.put("isc", "false");
		}
		Gson gson = new Gson();
		String result = gson.toJson(map);
		return result;
	}

	@RequestMapping(value = "/sendSMS.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(@RequestParam String phoneNumber) {
		Map<String, String> sendMap = new HashMap<String, String>();

		// 랜덤 난수 발생
		Random ran = new Random();
		sendMap.put("code", "" + ran.nextInt(10000));
		// coolSMS API사용
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSLH5XMRHNML98D",
				"SCDV2ABM6KXGAUPYYEWZEL1C92RU0NOJ", "https://api.coolsms.co.kr");
		Message message = new Message();
		message.setFrom("01022546438");
		message.setTo(phoneNumber);
		message.setText("타문타답 문자 인증 번호 : " + sendMap.get("code"));
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
	public String registration(@RequestParam Map<String, Object> map, HttpServletResponse resp) throws IOException {
		map.put("userAutoLoginToken", (UUID.randomUUID()) + (map.get("userPassword").toString().substring(0, 4))
				+ (map.get("userGender")) + (map.get("userAuth")) + (map.get("userEmail").toString().substring(0, 2)));
		int n = commUserService.registCommUser(map);
		if (n != 1) {
			resp.sendError(500);
			return null;
		} else {
			return "redirect:main.do";
		}
	}

	@RequestMapping(value = "/registForm.do", method = RequestMethod.GET)
	public String registinputForm() {
		return "registform";
	}

	@RequestMapping(value = "/mypage.do")
	public String mypage(HttpSession session, Model model) {
		//리뷰 작성 목록 조회를 위한 데이터 전송
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String userAccountId = userInfo.getUserAccountId();
		
		List<ReviewVo> lists = reviewService.getMyReview(new HashMap<String, Object>(){{
								put("userAccountId", userAccountId); 
								put("start", 1); 
								put("end", 5);
							}});
		
		model.addAttribute("lists", lists);
		
		return "myPage";
	}

	@RequestMapping(value = "/jeongji.do")
	@ResponseBody
	public String jeongji(HttpServletResponse resp, HttpSession session) throws IOException {
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		Map<String,Object> result = new HashMap<String, Object>();
			int n = commUserService.searchJeongJi(userInfo);
			if(n>0) {
				String date = commUserService.jeongjidate(userInfo);
				result.put("status", "true");
				result.put("date", date);
				session.invalidate();
				return gson.toJson(result);
			}
		return gson.toJson(result.put("status", "false"));
	}
	@RequestMapping(value="updatehuman.do", method = RequestMethod.GET)
	public String updatehuman(@RequestParam Map<String,Object> tokenValue, HttpServletResponse resp) throws IOException {
		int n = commUserService.updatedelflag(tokenValue);
		if(n>0) {
			return "redirect:main.do";
		}else {
			resp.sendError(500);
			return null;
		}
	}
}