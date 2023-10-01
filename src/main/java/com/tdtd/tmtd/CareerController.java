package com.tdtd.tmtd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IAlarmService;
import com.tdtd.tmtd.model.service.ICareerService;
import com.tdtd.tmtd.model.service.IFileService;
import com.tdtd.tmtd.vo.CareerVo;
import com.tdtd.tmtd.vo.FileVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 강사 경력 인증 관련 Controller
 * 
 * @author 문희애
 */
@Controller
@Slf4j
public class CareerController {

	@Autowired
	private IFileService fileService;

	@Autowired
	private ICareerService service;
	
	@Autowired
	private IAlarmService alarmService;
	
	/**
	 * 경력 인증 요청 페이지 이동
	 */
	@GetMapping("/instrCareer.do")
	public String instrCareer(Model model) {
		model.addAttribute("title", "프로필");
		model.addAttribute("pageTitle", "경력 인증");
		return "instrCareer";
	}

	/**
	 * 강사 개인 경력 인증 요청 목록 조회
	 * 
	 * @param session 로그인 세션
	 * @param page    페이지 번호
	 * @return - title, pageTitle 페이지 제목 / lists 인증 요청 리스트 / page 현재 페이지 vo
	 */
	@GetMapping("/myCareerList.do")
	public String myCareerList(Model model, HttpSession session, String page) {
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		String accountId = userInfo.getUserAccountId();

		int totalCount = service.getMyCareerCount(accountId);

		Map<String, Object> paging = PagingUtils.paging(page, totalCount, 3, 3);
		paging.put("userAccountId", accountId);

		List<CareerVo> lists = service.getMyCareerList(paging);

		model.addAttribute("title", "프로필");
		model.addAttribute("pageTitle", "경력 인증 리스트");
		model.addAttribute("lists", lists);
		model.addAttribute("page", paging.get("page"));

		return "myCareerList";
	}

	/**
	 * 경력증명서 양식 다운로드
	 */
	@RequestMapping(value = "/careerFileDownload.do", method = RequestMethod.GET)
	public void careerFiledownload(HttpServletResponse resp, HttpServletRequest req) throws IOException {
		log.info("CareerController careerFiledownload 실행");

		String filePath = req.getSession().getServletContext().getRealPath("/json/타문타답_경력증명서.docx");
		String fileName = "타문타답_경력증명서.docx";

		FileInputStream in = null;
		ServletOutputStream out = null;

		try {
			File file = new File(filePath); // 물리적인 공간에서 파일을 읽어옴
			System.out.println("읽어온 파일: " + file);

			byte[] b = new byte[(int) file.length()]; // 파일의 크기로 byte의 Array를 생성함
			System.out.println("읽어온 파일 byte: " + Arrays.toString(b));

			resp.reset(); // 브라우저로 응답할 때 header에 있는 정보를 초기화함 (생략가능)

			resp.setContentType("application/octet-stream");

			// 파일명 인코딩
			String encoding = new String(fileName.getBytes("UTF-8"), "8859_1");

			resp.setHeader("Content-Disposition", "attachment; filename=" + encoding);

			in = new FileInputStream(file);
			out = resp.getOutputStream();

			int numRead = 0;
			while ((numRead = in.read(b, 0, b.length)) != -1) {
				out.write(b, 0, numRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
			in.close();
		}
	}

	/**
	 * 경력증명서 파일 업로드
	 * 
	 * @param file    업로드한 파일
	 * @param session 로그인 정보
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@ResponseBody
	@PostMapping("/careerUpload.do")
	public Map<String, Object> fileUpload(HttpServletRequest request, List<MultipartFile> file, HttpSession session)
			throws IOException {
		log.info("CareerController fileUpload 실행");
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		String accountId = userInfo.getUserAccountId();
		Map<String, Object> response = new HashMap<>();

		for (MultipartFile f : file) {
			try {
				String saveName = (String) fileService.fileSave(f, request).get("saveName");
				String originName = (String) fileService.fileSave(f, request).get("originalName");
				String savedFilePath = (String) fileService.fileSave(f, request).get("path") + "/" + saveName;
				List<String> pngFileNames = fileService.convertPdfToPng(savedFilePath,
						request.getSession().getServletContext().getRealPath("/storage") + "/");
				for (String pngFileName : pngFileNames) {
					try {
						Map<String, Object> ocrMap = fileService.extractTextFromAreas(
								request.getSession().getServletContext().getRealPath("/storage") + "/" + pngFileName);
						ocrMap.put("careId", createId());
						ocrMap.put("careAccountId", accountId);

						String companyName = ocrMap.get("careCompany").toString();
						log.info("새로 등록한 companyName : {}", companyName);

						for (Object value : ocrMap.values()) {
							if (value == null || "".equals(value.toString().trim())) {
								response.put("errorMessage", "모든 항목은 반드시 기재되어야 합니다.");
								return response;
							}
						}

						Map<String, Object> fileMap = new HashMap<String, Object>();
						fileMap.put("fileRefPk", ocrMap.get("careId").toString());
						fileMap.put("fileOriginName", originName);
						fileMap.put("fileSaveName", saveName);

						int m = fileService.insertFile(fileMap);
						log.info("@@@@@@@@@@insertFile 성공 여부 : {}", (m > 0) ? "true" : "false");

						List<CareerVo> allCareer = service.getMyCareerList(new HashMap<String, Object>() {
							{
								put("userAccountId", accountId);
							}
						});

						for (CareerVo sameCareer : allCareer) {
							log.info("기존 companyName : {}", sameCareer.getCareCompany().trim());
							log.info("비교 companyName : {}", companyName.trim());
							if (sameCareer.getCareCompany().trim().equals(companyName.trim())) {
								int s = service.updateCareerR(sameCareer.getCareId());
								log.info("R 업데이트 성공 여부 : {}", (s > 0) ? "true" : "false");
							}
						}

						int n = service.insertCareer(ocrMap);

						if (n > 0 || m > 0) {
							response.put("successMessage", "관리자에게 승인 요청되었습니다.");
							return response;
						} else {
							response.put("errorMessage", "오류 발생 (형식이 맞지 않습니다");
							return response;
						}
					} catch (Exception e) {
						response.put("errorMessage", "Google Cloud Vision API 실행 중 오류 발생");
						return response;
					}
				}

				// OCR 처리와 DB 저장이 끝나면 PNG 파일 삭제
				fileService.deletePngFiles(pngFileNames,
						request.getSession().getServletContext().getRealPath("/storage"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * CAREER 테이블의 ID 생성 메소드
	 */
	private String createId() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

		String datePrefix = "CA" + sdf.format(date);

		String maxIdToday = service.selectMaxIdToday(datePrefix);

		int seq;

		if (maxIdToday == null) {
			seq = 1;
		} else {
			seq = Integer.parseInt(maxIdToday.substring(8)) + 1;
		}
		return datePrefix + String.format("%03d", seq);
	}

	/**
	 * 관리자 - 경력 인증 페이지 이동
	 * 
	 * @param page 현재 페이지
	 * @return title, pageTitle 페이지 제목 / lists 요청 리스트 / page 현재 페이지 VO
	 */
	@GetMapping("/admin/managerCareer.do")
	public String managerCareer(Model model, String page) {
		int totalCount = service.getCareerCount();
		Map<String, Object> paging = PagingUtils.paging(page, totalCount, 5, 5);
		log.info("paging : {} totalCount : {}", paging, totalCount);
		List<CareerVo> lists = service.getCareerList(paging);

		model.addAttribute("title", "강사 관리 페이지");
		model.addAttribute("pageTitle", "경력 인증 요청 승인");
		model.addAttribute("lists", lists);
		model.addAttribute("page", paging.get("page"));

		return "/admin/adminCareeerCert";
	}

	/**
	 * 관리자 - 승인 버튼 클릭시 (요청 승인 업데이트 및 경력 인증된 강사로 업데이트), 알람
	 * 
	 * @param map careId 경력요청 id / userAccountId 요청강사 id
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@GetMapping("/admin/updateS.do")
	@ResponseBody
	public Map<String, Object> updateS(@RequestParam Map<String, Object> map) {
		log.info("updateS 받아온 값 : {}", map);

		Map<String, Object> response = new HashMap<>();

		int n = service.updateCareerS(map);
		if (n > 0) {
			String content = "경력 인증 요청이 승인되었습니다";
			String accountId = (String)map.get("userAccountId");
			String url = "myCareerList.do";
			int m = alarmService.insertAlarm(alarmMap(content, accountId, url));
			if(m > 0) {
				response.put("successMessage", "승인 처리가 완료되었습니다");
			} else {
				response.put("errorMessage", "알람이 정상적으로 가지 않음");
			}
		} else {
			response.put("errorMessage", "승인이 정상 처리가 되지 않았습니다");
		}

		return response;
	}

	/**
	 * 관리자 - 경력 인증 요청시 제출한 PDF 파일 다운로드
	 * 
	 * @param careId 경력 요청 ID
	 */
	@GetMapping("/admin/downloadPdf.do")
	public void downloadPdf(@RequestParam String careId, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		log.info("downloadPdf 받아온 값 : {}", careId);

		FileVo fVo = fileService.getFile(careId);
		String fileName = fVo.getFileOriginName();

		FileInputStream in = null;
		ServletOutputStream out = null;

		try {
			File file = fileService.fileDownload(req, fVo.getFileSaveName());

			System.out.println("읽어온 파일: " + file);

			byte[] b = new byte[(int) file.length()]; // 파일의 크기로 byte의 Array를 생성함
			System.out.println("읽어온 파일 byte: " + Arrays.toString(b));

			resp.reset();

			resp.setContentType("application/pdf");

			String encoding = new String(fileName.getBytes("UTF-8"), "8859_1");

			resp.setHeader("Content-Disposition", "inline; filename=" + encoding);
			in = new FileInputStream(file);
			out = resp.getOutputStream();

			int numRead = 0;
			while ((numRead = in.read(b, 0, b.length)) != -1) {
				out.write(b, 0, numRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
			in.close();
		}

	}

	/**
	 * 관리자 - 목록 삭제 버튼 클릭시 ajax (PDF 파일 삭제 및 STATUS D로 변경)
	 * 
	 * @param careId 경력 요청 ID
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@GetMapping("/admin/updateD.do")
	@ResponseBody
	public Map<String, Object> updateD(@RequestParam String careId, HttpServletRequest req) throws IOException {
		log.info("updateD 받아온 값 : {}", careId);

		Map<String, Object> response = new HashMap<>();

		FileVo fVo = fileService.getFile(careId);

		File file = fileService.fileDownload(req, fVo.getFileSaveName());

		boolean isDeleted = file.delete();

		if (!isDeleted) {
			response.put("errorMessage", "파일 삭제에 실패했습니다");
			return response;
		}

		int n = service.updateCareerD(careId);

		if (n > 0) {
			response.put("successMessage", "정상 처리 되었습니다");
		} else {
			response.put("errorMessage", "정상 처리가 되지 않았습니다");
		}

		return response;
	}

	/**
	 * 관리자 - 경력 DB 삭제
	 * 
	 * @param careId 경력 요청 ID
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@GetMapping("/admin/deleteCareer.do")
	@ResponseBody
	public Map<String, Object> deleteCareer(@RequestParam String careId) {
		log.info("updateS 받아온 값 : {}", careId);

		Map<String, Object> response = new HashMap<>();

		int n = service.deleteCareer(careId);
		if (n > 0) {
			response.put("successMessage", "정상 처리 되었습니다");
		} else {
			response.put("errorMessage", "정상 처리가 되지 않았습니다");
		}
		return response;
	}

	/**
	 * 관리자 - 경력 내용 수정
	 * 
	 * @param data - CareerVo
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@GetMapping("/admin/careerEdit.do")
	@ResponseBody
	public Map<String, Object> careerEdit(@ModelAttribute CareerVo data) {
		log.info("careerEdit 받아온 값 : {}", data);

		Map<String, Object> response = new HashMap<>();

		int n = service.updateCareer(data);

		if (n > 0) {
			response.put("successMessage", "수정이 완료 되었습니다");
		} else {
			response.put("errorMessage", "정상 처리가 되지 않았습니다");
		}
		return response;
	}

	/**
	 * 관리자 - 반려시 업데이트 실행 및 알람
	 * 
	 * @param data careId 경력 요청 아이디 / careAccountId 경력 요청자 아이디 / careReason 반려 사유
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@PostMapping("/admin/updateB.do")
	@ResponseBody
	public Map<String, Object> updateB(@RequestParam Map<String, Object> data) {
		log.info("updateB 받아온 값 : {}", data);

		Map<String, Object> response = new HashMap<>();

		int n = service.updateCareerB(data);

		if (n > 0) {
			String content = "경력 인증이 반려되었습니다";
			String accountId = data.get("careAccountId").toString();
			String url = "myCareerList.do";
			
			int m = alarmService.insertAlarm(alarmMap(content, accountId, url));
			if(m > 0) {
				response.put("successMessage", "정상 처리 되었습니다");
			} else {
				response.put("errorMessage", "알람이 정상적으로 가지 않음");
			}
			
		} else {
			response.put("errorMessage", "정상 처리가 되지 않았습니다");
		}
		return response;
	}
	
	/**
	 * 관리자 경력 인증 / 반려시 알람 보내기 위한 map 반환 메소드
	 * @param content 내용
	 * @param accountId 알람 받는 사용자 ID
	 * @param url 알람을 누르면 이동할 주소
	 * @return
	 */
	private Map<String, Object> alarmMap(String content, String accountId, String url) {
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formattedDate = currentDate.format(formatter);
		String alarId = "AT_I"+formattedDate;
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("alarId", alarId);
		insertMap.put("alarContent", content);
		insertMap.put("alarAccountId", accountId);
		insertMap.put("alarReplySeq", url);
		
		return insertMap;
	}

}
