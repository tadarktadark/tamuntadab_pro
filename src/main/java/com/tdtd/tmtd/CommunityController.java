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
import java.util.ArrayList;
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
import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.ElasticsearchService;
import com.tdtd.tmtd.model.service.IFileService;
import com.tdtd.tmtd.model.service.IJayuService;
import com.tdtd.tmtd.model.service.IJilmunService;
import com.tdtd.tmtd.model.service.IPilgiService;
import com.tdtd.tmtd.model.service.IReplyService;
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
		
	/**
	 * 커뮤니티 이동
	 * @param model
	 * @param session
	 * @param board 보드 종류
	 * @param clasId 클래스 id
	 * @return 커뮤니티 목록 jsp
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/community.do", method=RequestMethod.GET)
	public String community(Model model, HttpSession session, String board, String clasId) {
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 이동 : board {}, clasId {}", board, clasId);
		model.addAttribute("title","커뮤니티");
		
		if(board.equals("pilgi")) {
			model.addAttribute("pageTitle", "필기");
			if(clasId != null) {				
				model.addAttribute("clasId", clasId);
				model.addAttribute("clasTitle", pService.getPilgiClassDetail(clasId).getClasTitle());
			}
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
	
	/**
	 * 커뮤니티 목록 조회
	 * @param session
	 * @param orderBy 현재 정렬
	 * @param page 현재 페이지
	 * @param boardId boardIdList
	 * @return map(boardVo, pageVo)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/getCommunityList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCommunityList(HttpSession session, String orderBy, String page, @RequestParam(value="boardId[]", required = false) String[] boardId){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 목록 조회 : board {}, orderBy {}, page {}, boardId {}", board, orderBy, page, boardId);
		
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
			if(boardId != null) {				
				bMap.put("boardId", Arrays.asList(boardId));
			}
			pageCount = pService.getPilgiCount(bMap);
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
	
	/**
	 * 커뮤키티 좋아요(목록/상세보기)
	 * @param session
	 * @param type 좋아요/취소
	 * @param id boardId
	 * @param board 게시판 종류
	 * @return 좋아요/취소, boardId
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/communityLike.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> communityLike(HttpSession session, String type, String id, String board){
		if(board==null) {			
			board = (String)session.getAttribute("community");
		}
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
	
	/**
	 * 커뮤니티 상세 조회
	 * @param session
	 * @param model
	 * @param board 게시판 종류
	 * @param id 게시판id
	 * @return boardList, subjectList
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/communityDetails.do", method=RequestMethod.GET)
	public String communityDetails(HttpSession session, Model model, String board, String id){
		if(board == null) {
			board = (String)session.getAttribute("community");
		}
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
	
	/**
	 * 글 작성 폼 이동
	 * @param model
	 * @param session
	 * @param id 작성자 id
	 * @param board 게시판 종류
	 * @return 작성 정보
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/communityWriteForm.do", method=RequestMethod.GET)
	public String communityWriteForm(Model model, HttpSession session, String id, String board) {
		if(board != null) {
			session.setAttribute("community",board);
		}
		board = (String)session.getAttribute("community");
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
	
	/**
	 * 질문 작성 시 선택 클래스 과목 조회
	 * @param session
	 * @param clasId 클래스id
	 * @return 과목 list
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
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
	
	/**
	 * 글 입력
	 * @param model
	 * @param session
	 * @param request
	 * @param bVo boardVo
	 * @param files FileList
	 * @return 글 상세 페이지 이동
	 * @throws IOException
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
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
	
	/**
	 * 필기 작성시 이미지 업로드
	 * @param upload 이미지file
	 * @param req
	 * @return 이미지 저장 경로
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
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
	
	/**
	 * 필기 작성시 이미지 삭제
	 * @param saveName 이미지 저장 경로
	 * @param req
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
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
	
	/**
	 * 파일 다운로드
	 * @param resp
	 * @param req
	 * @param save 파일 저장 경로
	 * @param name 파일 이름
	 * @throws IOException
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
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
	
	/**
	 * 커뮤니티 검색
	 * @param session
	 * @param formData 검색data
	 * @return 검색 결과 list
	 * @throws IOException
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/communitySearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<String> communitySearch(HttpSession session, @RequestBody Map<String, Object> formData) throws IOException {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 목록 검색 : board {}, formData {}",board, formData);		
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		
		while (true) {
			List<Map<String, Object>> resultOne = eService.search(formData, board, 10);
			if(resultOne.size()>0) {				
				resultList.addAll(resultOne);
				formData.put("page",(Integer)formData.get("page")+1);
			} else {
				break;
			}
		}
		
		List<String> boardList = new ArrayList<String>();
		for (Map<String, Object> map : resultList) {
			boardList.add((String)map.get("id"));
		}
		
		return boardList;
	}
	
	/**
	 * 커뮤니티 글 수정 form 이동
	 * @param model
	 * @param session
	 * @param id boardId
	 * @return 커뮤니티 글 수정 form jsp
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
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
		String code = "";
		String[] array = null;
		String[] codeArr = null;
		
		if(board.equals("pilgi")) {			
			bVo = pService.getPilgiUpdateData(id);
			bVo.setId(id);
		} else if(board.equals("jilmun")) {
			bVo = jmService.getJilmunUpdateData(id);
			model.addAttribute("classList",jmService.getJilmunClassList(userInfo.getUserAccountId()));
		} else if(board.equals("jayu")) {
			bVo = jyService.getJayuUpdateData(id);
		}
		
		model.addAttribute("bVo",bVo);
		model.addAttribute("boardId",id);
		
		System.out.println(bVo.getId());
		if(bVo.getSubjectCode()!=null) {
			str = bVo.getSubjectCode().substring(1, bVo.getSubjectCode().length() - 1); // 대괄호 제거
			array = str.split(","); // 쉼표를 기준으로 분리

			for (int i = 0; i < array.length; i++) {
			    array[i] = array[i].replaceAll("\"", "").trim(); // 큰따옴표 제거 및 공백 제거
			}
			
			if(board.equals("jilmun")) {				
				code = bVo.getId().substring(1, bVo.getId().length() - 1);
				codeArr = code.split(",");
				for (int i = 0; i < array.length; i++) {
				    codeArr[i] = codeArr[i].replaceAll("\"", "").trim();
				}
			}
		}
		model.addAttribute("subArr",array); 
		model.addAttribute("codeArr", codeArr);
		
		return "communityUpdateForm";
	}
	
	/**
	 * 필기 업로드 파일 삭제
	 * @param save 저장 경로
	 * @return 성공 1, 삭제 0
	 * @author SoHyeon
	 * @since 2023.09.20
	 */
	@RequestMapping(value="/removeFile.do", method=RequestMethod.GET)
	@ResponseBody
	public int removeFile(String save) {
		log.info("@@@@@@@@@@@@@@@ 필기 파일 삭제 : save {}", save);
		
		return pService.deletePilgiFile(save);
	}
	
	/**
	 * 커뮤니티 게시글 수정
	 * @param model
	 * @param session
	 * @param request
	 * @param bVo boardVo
	 * @param files 파일List
	 * @return 상세보기 페이지
	 * @throws IOException
	 * @author SoHyeon
	 * @since 2023.09.20
	 */
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
	
	/**
	 * 커뮤니티 게시글 삭제(필기-임시, 질문/자유-완전)
	 * @param model
	 * @param session
	 * @param id boardId
	 * @param board board 종류
	 * @return 목록 페이지 이동
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/communityDelete.do", method=RequestMethod.GET)
	public String communityDelete(Model model, HttpSession session, String id, String board){
		if(board==null) {			
			board = (String)session.getAttribute("community");
		}
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
	
	/**
	 * 커뮤니티 필기 복원
	 * @param session
	 * @param id 필기Id
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/restorePilgi.do", method=RequestMethod.POST)
	@ResponseBody
	public int restorePilgi(HttpSession session, String id){
		log.info("@@@@@@@@@@@@@@@ 필기 복원 : id {}", id);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("id", id);
		bMap.put("state", "N");
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("accountId", userInfo.getUserAccountId());
		cMap.put("state", "Y");
		cMap.put("id", id);
		
		return pService.updatePilgiState(bMap, cMap);
	}
	
	/**
	 * 필기 완전 삭제
	 * @param session
	 * @param id boardId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/deletePilgi.do", method=RequestMethod.POST)
	@ResponseBody
	public int deletePilgi(HttpSession session, String id){
		log.info("@@@@@@@@@@@@@@@ 필기 완전 삭제 : id {} ", id);
		return pService.deletePilgi(id);
	}
	
	/**
	 * 마이페이지에서 게시글 삭제
	 * @param session
	 * @param id boardId
	 * @param board board종류
	 * @return 성공1, 실패0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	@RequestMapping(value="/myCommDelete.do", method=RequestMethod.POST)
	@ResponseBody
	public int myCommDelete(HttpSession session, String id, String board){
		if(board==null) {			
			board = (String)session.getAttribute("community");
		}
		log.info("@@@@@@@@@@@@@@@ 마이페이지 게시글 삭제 : board{} id {}", board, id);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		int n = 0;
		if(board.equals("jilmun")) {
			n = jmService.deleteJilmun(id);
		} else if(board.equals("jayu")) {
			n = jyService.deleteJayu(id);
		}
		
		return n;
	}

}
