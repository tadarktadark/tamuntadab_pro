package com.tdtd.tmtd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.ICareerService;
import com.tdtd.tmtd.model.service.IFileService;
import com.tdtd.tmtd.vo.CareerVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CareerController {
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private ICareerService service;
	
	@GetMapping("/instrCareer.do")
	public String instrCareer(Model model) {
		model.addAttribute("title","프로필");
		model.addAttribute("pageTitle", "경력 인증");
		return "instrCareer";
	}
	
	@GetMapping("/myCareerList.do")
	public String myCareerList(Model model, HttpSession session, String page) {
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String accountId = userInfo.getUserAccountId();
//		String accountId = "TMTD101";
		
		int totalCount = service.getMyCareerCount(accountId);
		
		Map<String, Object> paging = PagingUtils.paging(page, totalCount, 3, 3);
		paging.put("userAccountId", accountId);
		
		List<CareerVo> lists = service.getMyCareerList(paging);
		
		model.addAttribute("title","프로필");
		model.addAttribute("pageTitle", "경력 인증 리스트");
		model.addAttribute("lists", lists);
		model.addAttribute("page", paging.get("page"));
		
		return "myCareerList";
	}
	
	@RequestMapping(value = "/careerFileDownload.do", method = RequestMethod.GET)
	public void careerFiledownload(HttpServletResponse resp) throws IOException {
		log.info("CareerController careerFiledownload 실행");
		
		String filePath = servletContext.getRealPath("/json/타문타답_경력증명서.docx");
		String fileName = "타문타답_경력증명서.docx";
		
		FileInputStream in = null;
		ServletOutputStream out = null;
		
		try {
			File file = new File(filePath); //물리적인 공간에서 파일을 읽어옴
			System.out.println("읽어온 파일: "+file);
			
			byte[] b = new byte[(int)file.length()]; //파일의 크기로 byte의 Array를 생성함
			System.out.println("읽어온 파일 byte: "+ Arrays.toString(b));
			
			resp.reset(); // 브라우저로 응답할 때 header에 있는 정보를 초기화함 (생략가능)
			
			resp.setContentType("application/octet-stream");
			
			// 파일명 인코딩
			String encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
			
			//파일 다운로드 버튼을 눌렀을 때 서버에서 전송받은 데이터를 어떻게 처리할 지 브라우저에 알려줘야 함
			resp.setHeader("Content-Disposition", "attachment; filename="+encoding);
			
			in = new FileInputStream(file);
			out = resp.getOutputStream();
			
			int numRead = 0;
			while((numRead = in.read(b,0,b.length))!=-1) {
				out.write(b,0,numRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
			in.close();
		}
	}

	@ResponseBody
	@PostMapping("/careerUpload.do")
	public Map<String, Object> fileUpload(HttpServletRequest request,
			List<MultipartFile> file, HttpSession session) throws IOException {
		log.info("CareerController fileUpload 실행");
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String accountId = userInfo.getUserAccountId();
		Map<String, Object> response = new HashMap<>();

	    for (MultipartFile f : file) {
	        try {
	        	String savedFilePath = (String)fileService.fileSave(f, request).get("path")+"/"+fileService.fileSave(f, request).get("saveName");
	            List<String> pngFileNames = fileService.convertPdfToPng(savedFilePath, request.getSession().getServletContext().getRealPath("/storage")+"/");
	            for (String pngFileName : pngFileNames) {
	                try {
						Map<String, Object> ocrMap = fileService.extractTextFromAreas(request.getSession().getServletContext().getRealPath("/storage")+"/"+pngFileName);
						ocrMap.put("careId", createId());
						ocrMap.put("careAccountId", accountId);
						
						for (Object value : ocrMap.values()) {
						    if (value == null || "".equals(value.toString().trim())) {
						        response.put("errorMessage", "모든 항목은 반드시 기재되어야 합니다.");
						        return response;
						    }
						}

						int n = service.insertCareer(ocrMap);
						if(n > 0) {
							System.out.println("insert 성공!!!!!");
						} else {
							response.put("errorMessage", "오류 발생 (형식이 맞지 않습니다");
						}
					} catch (Exception e) {
						response.put("errorMessage", "Google Cloud Vision API 실행 중 오류 발생");
						 return response;
					}
	            }
	            
	            // OCR 처리와 DB 저장이 끝나면 PNG 파일 삭제
                fileService.deletePngFiles(pngFileNames,request.getSession().getServletContext().getRealPath("/storage"));

	            
	        } catch (Exception e) { 
	            e.printStackTrace();
	        }
	    }

	    return response; 
	}
	
	private String createId() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		
		String datePrefix = "CA"+ sdf.format(date);
		
		String maxIdToday = service.selectMaxIdToday(datePrefix);
		
		int seq;
		
		if(maxIdToday == null) {
			seq = 1;
		} else {
			seq = Integer.parseInt(maxIdToday.substring(8))+1;
		}
		return datePrefix + String.format("%03d", seq);
	}
	

}
