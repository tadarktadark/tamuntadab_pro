package com.tdtd.tmtd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tdtd.tmtd.model.service.IFileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CareerController {
	
	@Autowired
	private IFileService fileService;
	
	@GetMapping("/instrCareer.do")
	public String instrCareer(Model model) {
		model.addAttribute("title","프로필");
		model.addAttribute("pageTitle", "경력 인증");
		return "instrCareer";
	}
	
	@GetMapping("/myCareerList.do")
	public String myCareerList(Model model) {
		model.addAttribute("title","프로필");
		model.addAttribute("pageTitle", "경력 인증 리스트");
		return "myCareerList";
	}
	
	@RequestMapping(value = "/careerFileDownload.do", method = RequestMethod.GET)
	public void careerFiledownload(HttpServletResponse resp) throws IOException {
		String filePath = "C:\\jenkins_git\\tamuntadab_pro\\src\\main\\webapp\\json\\타문타답_경력증명서.docx";
		String fileName = "타문타답_경력증명서.docx";
		
		FileInputStream in = null;
		ServletOutputStream out = null;
		
		try {
			File file = new File(filePath); //물리적인 공간에서 파일을 읽어옴
			System.out.println("읽어온 파일: "+file);
			
			byte[] b = new byte[(int)file.length()]; //파일의 크기로 byte의 Array를 생성함
			System.out.println("읽어온 파일 byte: "+ Arrays.toString(b));
			
			resp.reset(); // 브라우저로 응답할 때 header에 있는 정보를 초기화함 (생략가능)
			
			// MIME 타입
			// 전달하는 파일의 특성에 맞춰 header 정보를 변경해 줌
			// 어떤 타입인지 모를 경우 application/octet-stream
			// 만약에 타입을 알고 있다면 제공해주는 타입을 사용하면 된다 ex) application/msword
			
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
	public String fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
		String response = "false";
		
		Map<String, Object> fileVo = fileService.fileSave(file, request);
		String path = (String)fileVo.get("path");
		String saveName = (String)fileVo.get("saveName");
		
		if(path != null) {
			log.info("#@@@@@@@@@@@@@@@@#!@#!@#!@#!@#파일 저장된곳!!! {} 저장명: {}",path, saveName);
			response = "true";
		}
		
		return response;
	}
	

}
