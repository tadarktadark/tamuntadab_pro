package com.tdtd.tmtd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.ElasticsearchService;
import com.tdtd.tmtd.model.service.IFileService;
import com.tdtd.tmtd.model.service.IJayuService;
import com.tdtd.tmtd.model.service.IJilmunService;
import com.tdtd.tmtd.model.service.IPilgiService;
import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.PagingVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommunityController {

	@Autowired
	private IPilgiService pService;
	
	@Autowired
	private IJilmunService jmService;
	
	@Autowired
	private IJayuService jyService;
	
	@Autowired
	private IFileService fService;
	
	@Autowired
	private ElasticsearchService eService;
		
	@RequestMapping(value="/community.do", method=RequestMethod.GET)
	public String community(Model model, HttpSession session, String board) {
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 이동 : board {}", board);
		model.addAttribute("title","커뮤니티");
		
		if(board.equals("pilgi")) {
			model.addAttribute("pageTitle", "필기");
			session.setAttribute("community","pilgi");
		}else if(board.equals("jilmun")) {
			model.addAttribute("pageTitle", "질문");
			session.setAttribute("community","jilmun");			
		}else if(board.equals("jayu")) {
			model.addAttribute("pageTitle", "자유");
			session.setAttribute("community","jayu");
		}	
		
		return "communityList";
	}
	
	@RequestMapping(value="/getCommunityList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCommunityList(HttpSession session, String orderBy, String page){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 목록 조회 : board {}, orderBy {}, page {}", board, orderBy, page);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		int pageCount; // 전체 게시글 수
		Map<String, Object> pMap = new HashMap<String, Object>(); // page 객체 및 start, end
		Map<String, Object> bMap = new HashMap<String, Object>(); // board 관련 accountId, orderBy, start, end
		bMap.put("accountId", userInfo.getUserAccountId());
		bMap.put("orderBy", orderBy);
		
		if(board.equals("pilgi")) {
			pageCount = pService.getPilgiCount(userInfo.getUserAccountId());
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		} else if(board.equals("jilmun")) {
			pageCount = jmService.getJilmunCount();
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		} else if(board.equals("jayu")) {
			pageCount = jyService.getJayuCount();
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		}
		
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pMap.get("page"));
		
		if(board.equals("pilgi")) {
			result.put("board", pService.getPilgiList(bMap));
		} else if(board.equals("jilmun")) {
			result.put("board", jmService.getJilmunList(bMap));
		} else if(board.equals("jayu")) {
			result.put("board", jyService.getJayuList(bMap));
		}
		
		return result;
	}
	
	@RequestMapping(value="/communityLike.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> communityLike(HttpSession session, String type, String id){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 좋아요 : board {}, type {}, id {}", board, type, id);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		String list = "";
		
		if(board.equals("pilgi")) {
			list = pService.getPilgiLikeUser(id);
		} else if(board.equals("jilmun")) {
			list = jmService.getJilmunLikeUser(id);
		} else if(board.equals("jayu")) {
			list = jyService.getJayuLikeUser(id);
		}
		
		Map<String, Object> like = LikeViewUtils.like(type, userInfo.getUserAccountId(), list);
		if((int)like.get("update")==1) {
			Map<String, Object> data = new HashMap<String, Object>(){{
				put("likeUser", like.get("list"));
				put("likeCount", like.get("count"));
				put("id", id);
			}};
			if(board.equals("pilgi")) {
				pService.updatePilgiLikeUser(data);
			} else if(board.equals("jilmun")) {
				jmService.updateJilmunLikeUser(data);
			} else if(board.equals("jayu")) {
				jyService.updateJayuLikeUser(data);
			}
		}
		
		Map<String, Object> result = new HashMap<String, Object>(){{
			put("type",like.get("type"));
			put("count",like.get("count"));
		}};
		return result;
	}
	
	@RequestMapping(value="/communityDetails.do", method=RequestMethod.GET)
	public String communityDetails(HttpSession session, Model model, String id){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 상세조회 : board {}, id {}", board, id);
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		Map<String, Object> detail = new HashMap<String, Object>();
		detail.put("accountId",userInfo.getUserAccountId());
		detail.put("id",id );
		
		BoardVo bVo = new BoardVo();
		
		if(board.equals("pilgi")) {
			bVo = pService.getPilgiDetail(detail);
			model.addAttribute("pageTitle", "필기");
			model.addAttribute("yList", pService.getYeongwanList(detail));
		} else if(board.equals("jilmun")) {
			bVo = jmService.getJilmunDetail(detail);
			model.addAttribute("pageTitle", "질문");
		} else if(board.equals("jayu")) {
			bVo = jyService.getJayuDetail(detail);
			model.addAttribute("pageTitle", "자유");
		}
		
		String str = "";
		String[] array = null;
		
		if(bVo.getSubjectCode()!=null) {
			str = bVo.getSubjectCode().substring(1, bVo.getSubjectCode().length() - 1); // 대괄호 제거
			array = str.split(","); // 쉼표를 기준으로 분리

			for (int i = 0; i < array.length; i++) {
			    array[i] = array[i].replaceAll("\"", "").trim(); // 큰따옴표 제거 및 공백 제거
			}
		}

		model.addAttribute("bVo", bVo);
		model.addAttribute("subArr", array);
		return "communityDetails";
	}
	
	@RequestMapping(value="/communityWriteForm.do", method=RequestMethod.GET)
	public String communityWriteForm(Model model, HttpSession session, String id) {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 새 글 작성 Form 이동 : board {}, clasId {}", board, id);
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		if(board.equals("pilgi")) {
			model.addAttribute("pageTitle", "필기");
			
			ClassVo cVo = pService.getPilgiClassDetail(id);
			String str = "";
			String[] array = null;
			
			if(cVo.getClasSubjectJeongbo()!=null) {
				str = cVo.getClasSubjectJeongbo().substring(1, cVo.getClasSubjectJeongbo().length() - 1); // 대괄호 제거
				array = str.split(","); // 쉼표를 기준으로 분리

				for (int i = 0; i < array.length; i++) {
				    array[i] = array[i].replaceAll("\"", "").trim(); // 큰따옴표 제거 및 공백 제거
				}
			}
			
			model.addAttribute("classVo",cVo); 
			model.addAttribute("subArr",array); 
		} else if(board.equals("jilmun")) {
			model.addAttribute("pageTitle", "질문");
			model.addAttribute("classList",jmService.getJilmunClassList(userInfo.getUserAccountId()));
		} else if(board.equals("jayu")) {
			model.addAttribute("pageTitle", "자유");
		}
		
		return "communityWriteForm";
	}
	
	@RequestMapping(value="/getJilmunClassList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getJilmunClassList(HttpSession session, String clasId) {
		log.info("@@@@@@@@@@@@@@@ 질문 작성시 선택 클래스 과목 조회 : clasId {}", clasId);
		
		ClassVo cVo = jmService.getJilmunSubject(clasId);
		
		String str = "";
		String[] array = null;
		
		str = cVo.getClasContent().substring(1, cVo.getClasContent().length() - 1); // 대괄호 제거
		array = str.split(","); // 쉼표를 기준으로 분리

		for (int i = 0; i < array.length; i++) {
		    array[i] = array[i].replaceAll("\"", "").trim(); // 큰따옴표 제거 및 공백 제거
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", cVo.getClasSubjectJeongbo());
		result.put("title", array);
		
		return result;
	}
	
	@RequestMapping(value="/communityWrite.do", method=RequestMethod.POST)
	public String communityWrite(Model model, HttpSession session, HttpServletRequest request, BoardVo bVo, @RequestParam(value = "file", required = false) MultipartFile[] files) throws IOException {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 게시글 작성 : board{} boardVo {} file {}", board, bVo, files);
		
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		} 
		
		bVo.setAccountId(userInfo.getUserAccountId());
		
		if(board.equals("pilgi")) {
			model.addAttribute("pageTitle", "필기");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", "Y");
			map.put("accountId", userInfo.getUserAccountId());
			pService.insertPilgi(bVo, map, files, request);
		} else if(board.equals("jilmun")) {
			model.addAttribute("pageTitle", "질문");
			if(bVo.getClasId().equals("none")) {
				bVo.setClasId(null);
			}
			jmService.insertJilmun(bVo);
		} else if(board.equals("jayu")) {
			model.addAttribute("pageTitle", "자유");
			jyService.insertJayu(bVo);
		}
				
		return "redirect:/communityDetails.do?id="+bVo.getId();
	}
		
	@RequestMapping(value="/communityUploadImage.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> communityUploadImage(MultipartFile upload, HttpServletRequest req) {
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 이미지 업로드");
		
		String ext = upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "")+ext;
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String path="";
		
		try {
			// 파일읽기
			inputStream = upload.getInputStream();
			
			// 저장 위치 문자열 만들기(상대경로)
			path = WebUtils.getRealPath(req.getSession().getServletContext(),"/ckupload");
			System.out.println(path);
			
			// 저장 위치가 존재하지 않으면 폴더 생성
			File storage = new File(path);
			if(!storage.exists()) {
				storage.mkdir();
			}
			
			// 저장할 파일이 해당 위치에 없다면 만들어주고 아니면 오버라이드 함
			File newFile = new File(path+"/"+saveName);
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			
			// client에서 받아온 파일(upload)를 쓸 대상(newFile) 지정
			outputStream = new FileOutputStream(newFile);
			
			// 파일(upload)를 읽어 대상(newFile)에 씀
			int read = 0;
			byte[] b = new byte[(int)upload.getSize()];
			while((read=inputStream.read(b))!=-1) {
				outputStream.write(b,0,read);
			}
			
		} catch (IOException e) {
			log.error("!!!!!!!!!!!!!!!! uploadImage read Error : \n"+e.getMessage());
		} finally {
				try {
					inputStream.close();
					outputStream.close();
				} catch (IOException e) {
					log.error("!!!!!!!!!!!!!!!! uploadImage close Error : \n"+e.getMessage());
					e.printStackTrace();
				}
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "./ckupload/"+saveName);
		
		return map;
	}
	
	@RequestMapping(value="/communityRemoveImage.do", method = RequestMethod.POST)
	@ResponseBody
	public void removeImage(String saveName, HttpServletRequest req) {
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 이미지 삭제 : saveName {}", saveName);		
		
		String path = "";
		
		try {
			path = WebUtils.getRealPath(req.getSession().getServletContext(),"/ckupload");
			File oldFile = new File(path+"/"+saveName);
			// 파일이 존재하면 삭제
			if(oldFile.exists()) {
				oldFile.delete();
			}
		} catch (FileNotFoundException e) {
			log.error("!!!!!!!!!!!!!!!! removeImage Error : \n"+e.getMessage());
		}
	}
	
	@RequestMapping(value="/communityDownload.do", method = RequestMethod.GET)
	public void communityDownload(HttpServletResponse resp, HttpServletRequest req, String save, String name) throws IOException {
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 다운로드 : fileSaveName {}", save);		
		
		FileInputStream in = null;
		ServletOutputStream out = null;
		
		try {
			File file = fService.fileDownload(req,save);
			System.out.println("읽어온 파일: "+file);
			
			byte[] b = new byte[(int)file.length()]; //파일의 크기로 byte의 Array를 생성함
			System.out.println("읽어온 파일 byte: "+ Arrays.toString(b));
			
			resp.reset(); // 브라우저로 응답할 때 header에 있는 정보를 초기화함 (생략가능)
			
			resp.setContentType("application/octet-stream");
			
			// 파일명 인코딩
			String encoding = new String(name.getBytes("UTF-8"), "8859_1");
			
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
	
	@RequestMapping(value="/communitySearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void communitySearch(HttpSession session, @RequestBody Map<String, Object> formData) throws IOException {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 목록 검색 : board {}, formData {}",board, formData);		
		
		List<Map<String, Object>> resultList = eService.search(formData, board, 10);
		
		Gson gson = new Gson();
		System.out.println("result : "+gson.toJson(resultList));
		
	}
	
	@RequestMapping(value="/pilgiPdfDownload.do", method = RequestMethod.POST)
	public void pilgiPdfDownload(HttpServletResponse response, HttpServletRequest request, String id, String content) throws DocumentException, IOException {
		log.info("@@@@@@@@@@@@@@@ 필기 PDF 다운로드 : id {}, content \n{}",id, content);
		System.out.println(content.contains("<img"));
//		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
//		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
//		writer.setInitialLeading(12.5f);
//		
//		response.setContentType("application/pdf");
//		String fileName = URLEncoder.encode("테스트파일","UTF-8");
//		
//		response.setHeader("Content-Transper-Encoding", "binary");
//		response.setHeader("Content-Disposition", "inline; filename="+fileName+".pdf");
//		
//		document.open();
//		XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
//		
//		CSSResolver cssResolver = new StyleAttrCSSResolver();
//		String cssPath = request.getServletContext().getRealPath("css/communityDetails.css");
//		CssFile cssFile = helper.getCSS(new FileInputStream(cssPath));
//		cssResolver.addCss(cssFile);
//		
//		XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
//		String fontPath = request.getServletContext().getRealPath("font/MALGUN.TTF");
//		fontProvider.register(fontPath,"gothic");
//		CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
//		
//		HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
//		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
//		
//		PdfWriterPipeline pdf = new PdfWriterPipeline(document,writer);
//		HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
//		CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
//		
//		XMLWorker worker = new XMLWorker(css, true);
//		XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
//		
//		String htmlStr = "<html><head></head>"
//						+ content
//						+ "</body></html>";
//		StringReader strReader = new StringReader(htmlStr);
//		
//		xmlParser.parse(strReader);
//		document.close();
//		writer.close();
	}
	
	@RequestMapping(value="/communityUpdateForm.do", method=RequestMethod.GET)
	public String communityUpdateForm(Model model, HttpSession session, String id) {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 글 수정 Form 이동 : board {}, boardId {}", board, id);
		
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
				
		BoardVo bVo = null;
		String str = "";
		String[] array = null;
		
		if(board.equals("pilgi")) {			
			bVo = pService.getPilgiUpdateData(id);
		} else if(board.equals("jilmun")) {
			bVo = jmService.getJilmunUpdateData(id);
			model.addAttribute("classList",jmService.getJilmunClassList(userInfo.getUserAccountId()));
		} else if(board.equals("jayu")) {
			bVo = jyService.getJayuUpdateData(id);
		}
		
		model.addAttribute("bVo",bVo);
		model.addAttribute("boardId",id);
		
		if(bVo.getSubjectCode()!=null) {
			str = bVo.getSubjectCode().substring(1, bVo.getSubjectCode().length() - 1); // 대괄호 제거
			array = str.split(","); // 쉼표를 기준으로 분리

			for (int i = 0; i < array.length; i++) {
			    array[i] = array[i].replaceAll("\"", "").trim(); // 큰따옴표 제거 및 공백 제거
			}
		}
		model.addAttribute("subArr",array); 
		
		return "communityUpdateForm";
	}
	
	@RequestMapping(value="/removeFile.do", method=RequestMethod.GET)
	@ResponseBody
	public int removeFile(Model model, HttpSession session, String save) {
		log.info("@@@@@@@@@@@@@@@ 필기 파일 삭제 : save {}", save);
		
		return pService.deletePilgiFile(save);
	}
	
	@RequestMapping(value="/communityUpdate.do", method=RequestMethod.POST)
	public String communityUpdate(Model model, HttpSession session, HttpServletRequest request, BoardVo bVo, @RequestParam(value = "file", required = false) MultipartFile[] files) throws IOException {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 게시글 수정 : board{} boardVo {} file {}", board, bVo, files);
		
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		} 
		
		bVo.setAccountId(userInfo.getUserAccountId());
		
		if(board.equals("pilgi")) {
			pService.updatePilgi(bVo, files, request);
		} else if(board.equals("jilmun")) {
			if(bVo.getClasId().equals("none")) {
				bVo.setClasId(null);
			}
			if(bVo.getSubjectCode().equals("none")) {
				bVo.setClasId(null);
			}
			jmService.updateJilmun(bVo);
		} else if(board.equals("jayu")) {
			model.addAttribute("pageTitle", "자유");
			jyService.updateJayu(bVo);
		}
				
		return "redirect:/communityDetails.do?id="+bVo.getId();
	}
	
	@RequestMapping(value="/communityDelete.do", method=RequestMethod.GET)
	public String communityDelete(Model model, HttpSession session, String id){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 게시글 삭제 : board{} id {}", board, id);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		if(board.equals("pilgi")) {
			Map<String, Object> bMap = new HashMap<String, Object>();
			bMap.put("id", id);
			bMap.put("state", "Y");
			Map<String, Object> cMap = new HashMap<String, Object>();
			cMap.put("state", "N");
			cMap.put("accountId", userInfo.getUserAccountId());
			cMap.put("id", id);
			pService.updatePilgiState(bMap, cMap);
		} else if(board.equals("jilmun")) {
			jmService.deleteJilmun(id);
		} else if(board.equals("jayu")) {
			jyService.deleteJayu(id);
		}
		
		return "redirect:/community.do?board="+board;
	}
	
	@RequestMapping(value="/getMyWriteList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getMyWriteList(HttpSession session, String board, String page){
		log.info("@@@@@@@@@@@@@@@ 마이페이지 내 글 목록 조회 : board {}, page {}", board, page);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		int pageCount; // 전체 게시글 수
		Map<String, Object> pMap = new HashMap<String, Object>(); // page 객체 및 start, end
		Map<String, Object> bMap = new HashMap<String, Object>(); // board 관련 accountId, start, end
		bMap.put("accountId", userInfo.getUserAccountId());
		
		if(board.equals("pilgi")) {
			pageCount = pService.getMyPilgiCount(userInfo.getUserAccountId());
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		} else if(board.equals("jilmun")) {
			pageCount = jmService.getMyJilmunCount(userInfo.getUserAccountId());
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		} else if(board.equals("jayu")) {
			pageCount = jyService.getMyJayuCount(userInfo.getUserAccountId());
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		}
		
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pVo", pMap.get("page"));
		
		if(board.equals("pilgi")) {
			result.put("board", "pilgi");
			result.put("bVo", pService.getMyPilgiList(bMap));
		} else if(board.equals("jilmun")) {
			result.put("board", "jilmun");
			result.put("bVo", jmService.getMyJilmunList(bMap));
		} else if(board.equals("jayu")) {
			result.put("board", "jayu");
			result.put("bVo", jyService.getMyJayuList(bMap));
		}
		
		return result;
	}
}
