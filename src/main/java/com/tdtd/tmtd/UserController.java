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
/**
 * WOON 회원 관련 흐름처리를 위한 컨트롤러 클래스
 * 
 * @author 임정운
 * 
 * @since 2023.09.08
 *
 */
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
	
	
	/**
	 * WOON 사용자 삭제 처리를 위한 메소드
	 * 설명 : 해당 사용자가 결제 내역 확인 후 삭제 처리하며 세션삭제
	 * 
	 * @param session 사용자 정보를 담고있는 HttpSession 객체
	 * 
	 * @return 사용자의 삭제처리 상태를 반환
	 *  
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value="/deleteUser.do",method=RequestMethod.GET)
	@ResponseBody
	public String deleteUser(HttpSession session) {
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		String isc = commUserService.checkPayment(userInfo);
		if(isc.equals("false")) {
			return "payment";
		}
		int m = commUserService.insertUserDelTable(userInfo);
		userInfo.setDeletedCount(commuserDao.countDeluser());
		int n = commUserService.updateUserDelflagToY(userInfo);
		if(n+m>1) {
			session.invalidate();
			return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * WOON 사용자의 닉네임 변경을 위한 메소드
	 * 설명 : 사용자가 입력한 닉네임의 중복여부를 확인 후 변경 진행
	 * 
	 * @param nickname 사용자가 입력한 변경 닉네임
	 * 
	 * @param session 사용자의 정보를 담고있는 HttpSession객체
	 * 
	 * @return 닉네임 변경 성공 유무 
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
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

	/**
	 * WOON 사용자 프로필 사진 변경을 위한 메소드
	 * 
	 * @param file 사용자가 전송한 File 객체
	 * 
	 * @param session 사용자의 정보를 담고 있는 Session 객체
	 * 
	 * @param req 서버 정보를 담고 있는 Request객체
	 * 
	 * @return 이미지 변경 성공 여부
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
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

	/**
	 * WOON 사용자 비밀번호 변경을 위한 메소드
	 * 설명 : 사용자의 비밀번호를 현재 비밀번호와 비교 후 변경
	 * 
	 * @param map 사용자가 입력한 비밀번호를 담은 객체
	 * 
	 * @param session 사용자의 정보가 담겨있는 객체
	 * 
	 * @return 사용자의 변경 성공 여부
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "updatePassword.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String updatePassword(@RequestParam Map<String, Object> map, HttpSession session) throws UnsupportedEncodingException {
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

	/**
	 * WOON 비밀번호 변경 화면으로 이동을 위한 메소드
	 * 
	 * @return updatePasswordForm.jsp
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "updatePassword.do", method = RequestMethod.GET)
	public String updatePasswordForm() {
		return "updatePasswordForm";
	}

	
	/**
	 * WOON 로그인 시 비밀번호 분실로 인해 비밀번호 초기화를 위해 메일을 전송하는 메소드
	 * 
	 * @param userInfo 사용자의 정보를 담은 객체 
	 * 
	 * @param req 서버정보를 담고있는 객체
	 * 
	 * @return 메일 전송 성공여부
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
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
	
	/**
	 * WOON 비밀번호 초기화에 필요한 이메일 입력 페이지 이동을 위한 메소드
	 * @return resetPasswordForm.jsp
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.GET)
	public String resetPasswrod() {
		return "resetPasswordForm";
	}

	/**
	 * WOON 로그아웃을 위한 메소드
	 * 
	 * @param session 사용자의 정보 삭제를 위한 객체
	 * 
	 * @return 세션 제거 후 main으로 전송
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:main.do";
	}

	/**
	 * WOON 로그인 화면으로 이동하는 메소드
	 * 
	 * @return loginForm.jsp
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/loginForm.do")
	public String loginForm() {
		return "loginForm";
	}

	/**
	 * WOON 사용자 로그인을 위한 메소드
	 * 설명 : 사용자가 입력한 정보를 비교하여 계정 유무를 판단 하며 해당 계정의 
	 * 		상태를 반환 혹은 해당 조회된 정보를 반환하는 메소드이다
	 * 		휴면계정일 경우 해당 메일로 이메일ㅇ ㅣ전송된다.
	 * 
	 * @param map 사용자가 입력한 값을 담은 객체이다
	 * 
	 * @param session 해당 사용자의 정보를 담기위한 객체
	 * 
	 * @param req 서버의 정보를 담은 객체 
	 * 
	 * @return 로그인 성공여부를 반환하는 객체
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
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

	/**
	 * WOON 사용자가 자동로그인 처리를 위한 메소드
	 * 설명 : 사용자의 토큰을 받아 해당 정보를 조회한다.
	 * 
	 * @param userAutoLoginToken 사용자의 자동로그인 토큰
	 * 
	 * @param session 조회된 사용자의 정보를 담기위한 객체
	 * 
	 * @return 로그인 성공 여부
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
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

	/**
	 * WOON 회원가입 페이지로 이동하기 위한 메소드
	 * @return regist.jsp
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/regist.do", method = RequestMethod.GET)
	public String registForm() {
		return "regist";
	}

	/**
	 * WOON 이메일의 중복 여부를 판단하기 위한 메소드
	 * 
	 * @param map 사용자가 입력한 값을 담은 객체
	 * 
	 * @return 중복 여부
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/searchEmail.do", method = RequestMethod.POST)
	@ResponseBody
	public Boolean searchEmail(@RequestParam Map<String, String> map) {
		return commUserService.searchEmailService(map);
	}

	/**
	 * WOON 사용자 이메일 인정을 위한 메소드
	 * 설명: 사용자가 입력한 값을 받아 메일은 전송하는 메소드이다.
	 * 
	 * @param map 사용자가 입력한 값을 가지고 있는 객체
	 * 
	 * @return 메일 전송 성공여부
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/sendMail.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendmail(@RequestParam Map<String, String> map) {
		MimeMessage msg = mailsender.createMimeMessage();

		try {
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, false, "UTF-8");
			map.put("code", UUID.randomUUID().toString().substring(0, 8));
			msgHelper.setFrom("juojuo9809@naver.com");
			msgHelper.setTo(map.get("userEmail"));
			msgHelper.setSubject("타문타답 인증번호 : " + map.get("code"));
			msgHelper.setText("타문타답 인증번호 : " + map.get("code"));
			map.put("isc", "true");
			mailsender.send(msg);
		} catch (MessagingException e) {
			map.put("isc", "false");
		}
		Gson gson = new Gson();
		String result = gson.toJson(map);
		return result;
	}

	/**
	 * WOON 사용자 휴대폰 인증을 위한 메소드
	 * 설명: 사용자가 입력한 번호를 받아 해당 번호에 문자를 전송하는 메소드
	 * 
	 * @param phoneNumber 사용자가 입력한 핸드폰 번호
	 *
	 * @return 문자 전송 성공 여부 및 성공시 전송 번호
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/sendSMS.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(@RequestParam String phoneNumber) {
		Map<String, String> sendMap = new HashMap<String, String>();

		sendMap.put("code", "" + ((int)(Math.random() * 9999) + 1));
		// coolSMS API사용
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSZADE5ZEC1DZR3",
				"HQMRDEF5F5WUBE15UHVGMYSOY4PVFBJS", "https://api.coolsms.co.kr");
		Message message = new Message();
		message.setFrom("01073780203");
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

	/**
	 * WOON 사용자의 회원가입 처리를 위한 메소드
	 * 설명 : 사용자 입력 값을 받고 자동로그인 토큰을 생성 후 서비스로 전송
	 * 
	 * @param map 사용자가 입력한 값을 담은 객체
	 *  
	 * @param resp 실패시 에러처리를 위한 객체
	 * 
	 * @return 성공시 main으로 전송
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
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

	/**
	 * WOON 회원 가입 페이지로 이동하기 위한 메소드
	 * 
	 * @return registform.jsp
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/registForm.do", method = RequestMethod.GET)
	public String registinputForm() {
		return "registform";
	}

	/**
	 * WOON 사용자가 사용자의 정보를 조회하기 위한 메소드
	 * 설명 : 사용자의 정볼를 조회
	 * @param session 사용자 정보를 담고있는 객체
	 * 
	 * @param model 조회결과를 담기 위한 객체
	 * 
	 * @return maPage.jsp
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value = "/mypage.do")
	public String mypage(HttpSession session, Model model) {
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

	/**
	 * WOON 해당 유저의 정지 여부를 판단하는 메소드
	 * 설명 : 사용자의 정지 유무를 판단하고 결과를 반환하는 메소드
	 * 
	 * @param session 사용자의 정보를 담은 객체
	 * 
	 * @return 사용자의 정지 유무
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value = "/jeongji.do")
	@ResponseBody
	public String jeongji(HttpSession session) throws IOException {
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
	
	/**
	 * WOON 휴면 해제 처리를 위한 메소드
	 * 설명 : 메일에 전송된 휴면 토큰을 이용해 해당 사용자의 delflag를 변경하는 메소드
	 * 
	 * @param tokenValue 사용자가 메일에 전송됐던 토큰
	 * 
	 * @param resp 에러처리를 위한 객체
	 * 
	 * @return 성공시 main으로 반환
	 *   
	 * @author 임정운
	 * 
	 * @since 2023.09.08
	 *
	 */
	@RequestMapping(value="updatehuman.do", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String updatehuman(@RequestParam Map<String,Object> tokenValue, HttpServletResponse resp) throws IOException {
		int n = commUserService.updatedelflag(tokenValue);
		if(n>0) {
			return "<script>alert('해제 되었습니다.');location.href='./main.do';</script>";
		}else {
			resp.sendError(500);
			return null;
		}
	}
}