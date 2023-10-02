package com.tdtd.tmtd;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.model.service.ElasticsearchService;
import com.tdtd.tmtd.model.service.ICareerService;
import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.vo.CareerVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 강사 게시판(전체 조회, 상세조회) 관련 컨트롤러
 * 
 * @author 문희애
 * 
 */
@Controller
@Slf4j
public class InstrController {

	@Autowired
	private ElasticsearchService searchService;

	@Autowired
	private IInstrService service;

	@Autowired
	private ICareerService careerService;

	/**
	 * 강사 게시판 페이지 이동
	 * 
	 * @param start 페이징 시작 행 번호 (default 1)
	 * @param end   페이징 끝 행 번호 (default 9)
	 * @return - title, pageTitle 페이지 제목 / lists 전체 강사 리스트
	 */
	@GetMapping("/instrList.do")
	public String instrList(Model model, @RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "end", defaultValue = "9") int end) {
		log.info("InstrController instrList 이동");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", "like");
		map.put("start", start);
		map.put("end", end);

		List<InstrVo> lists = service.getAllInstr(map);

		// 과목 값 추출 및 만나이 추출
		for (InstrVo instrVo : lists) {
			String subjectsMajorTitle = instrVo.getSubjectsMajorTitle();
			String subjectsTitle = instrVo.getSubjectsTitle();
			subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
			subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");

			instrVo.setSubjectsMajorTitle(subjectsMajorTitle);
			instrVo.setSubjectsTitle(subjectsTitle);

			String birthDateString = instrVo.getUserProfileVo().get(0).getUserBirth();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime birthDateTime = LocalDateTime.parse(birthDateString, formatter);

			LocalDate birthDate = birthDateTime.toLocalDate();

			int age = Period.between(birthDate, LocalDate.now()).getYears();
			instrVo.setInprAge(age);
		}
		;

		model.addAttribute("title", "강사 조회");
		model.addAttribute("pageTitle", "강사 전체 리스트");
		model.addAttribute("lists", lists);
		return "instrList";
	}

	/**
	 * 강사 전체 조회 페이징 (스크롤 API) ajax
	 * 
	 * @param map - order(조회순서), start(시작 행 번호), end(끝 행 번호)
	 * @param session 로그인 세션
	 * @return - lists 전체 강사 리스트 / userInfo 로그인 정보 / hasMore 끝페이지 여부
	 */
	@GetMapping("/instrMoreList.do")
	@ResponseBody
	public Map<String, Object> instrMoreList(@RequestParam Map<String, Object> map, HttpSession session) {
		log.info("instrMoreList 받아온 map: {}", map);

		List<InstrVo> lists = service.getAllInstr(map);

		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");

		for (InstrVo instrVo : lists) {

			String birthDateString = instrVo.getUserProfileVo().get(0).getUserBirth();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime birthDateTime = LocalDateTime.parse(birthDateString, formatter);

			LocalDate birthDate = birthDateTime.toLocalDate();

			int age = Period.between(birthDate, LocalDate.now()).getYears();
			instrVo.setInprAge(age);
		}
		;

		int end = Integer.parseInt(map.get("end").toString());
		int totalCount = service.getAllInstrCount();

		Map<String, Object> response = new HashMap<>();
		response.put("lists", lists);
		response.put("userInfo", userInfo);
		response.put("hasMore", end < totalCount);
		log.info("%%%%%%%%%%%hasMore {}", response.get("hasMore"));
		return response;
	}

	/**
	 * 강사 게시판 검색시 elasticsearch 실행
	 * 
	 * @param formData subjects 과목 / nickname 닉네임 / fee(Gt/Lt) 수업료(최소/최대) gender 성별
	 *                 / order 조회순서
	 * @param session  로그인 세션
	 * @return - lists 검색된 강사 리스트 / userInfo 로그인 정보
	 */
	@ResponseBody
	@PostMapping(value = "/instrSearch.do", produces = "application/json;charset=UTF-8")
	public Map<String, Object> instrSearch(@RequestBody Map<String, Object> formData, HttpSession session)
			throws IOException {
		log.info("########### instrSearch.do 받아온 값: {}", formData);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		// elasticsearch의 모든 검색 결과를 가져옴
		while (true) {
			List<Map<String, Object>> resultOne = searchService.search(formData, "instr_profile", 10);
			if (resultOne.size() > 0) {
				resultList.addAll(resultOne);
				formData.put("page", (Integer) formData.get("page") + 1);
			} else {
				break;
			}
		}

		// 검색 결과로 가져온 강사 아이디를 List에 담음
		List<String> idsList = new ArrayList<String>();
		for (Map<String, Object> result : resultList) {
			log.info("%%%%%%%%%%%%%%%%%%%%%% result : {}", result);
			idsList.add((String) result.get("inpr_account_id"));
		}

		if (idsList.size() == 0) {
			Map<String, Object> response = new HashMap<>();
			response.put("lists", null);
			return response;
		}

		String order = (String) formData.get("order");

		// 조회된 강사 아이디로 DB의 데이터 불러오기
		List<InstrVo> lists = service.getAllInstr(new HashMap<String, Object>() {
			{
				put("inprIds", idsList);
				put("start", 1);
				put("end", idsList.size());
				put("order", order);
			}
		});

		for (InstrVo instrVo : lists) {

			String birthDateString = instrVo.getUserProfileVo().get(0).getUserBirth();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime birthDateTime = LocalDateTime.parse(birthDateString, formatter);

			LocalDate birthDate = birthDateTime.toLocalDate();

			int age = Period.between(birthDate, LocalDate.now()).getYears();
			instrVo.setInprAge(age);
		}
		;

		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		log.info("userInfo : {}", userInfo);

		Map<String, Object> response = new HashMap<>();
		response.put("lists", lists);
		response.put("userInfo", userInfo);

		log.info("검색 결과 : {}", response);
		return response;
	}

	/**
	 * 강사 상세 조회 페이지 이동
	 * 
	 * @param map - seq 강사 프로필 seq / loginId 로그인 아이디
	 * @return - title, pageTitle 페이지 제목 / simpleVo 강사 간략프로필 / profileVo 강사 상세 프로필
	 *         classVo 강사 클래스이력 / instrReviewVo 강사 후기 / allClass 강사 매칭된 클래스 전체수
	 *         successRate 강사 클래스 성사율
	 */
	@GetMapping("/instrDetail.do")
	public String instrDetail(Model model, @RequestParam(required = false) Map<String, Object> map) {
		if (map.get("loginId") == null || map.get("loginId").equals("")) {
			map.put("loginId", "null");
		}

		log.info("InstrController /instrDetail.do 실행 받아온 강사 seq : {} 로그인한 아이디 : {}", map.get("seq"),
				map.get("loginId"));
		InstrVo simpleVo = service.getOneInstrSimple(map);
		InstrVo profileVo = service.getOneInstrProfile((String) map.get("seq"));
		String userAccountId = simpleVo.getInprAccountId();
		Map<String, Object> classMap = new HashMap<String, Object>();
		classMap.put("userAccountId", userAccountId);
		classMap.put("start", 1);
		classMap.put("end", 5);

		String subjectsMajorTitle = profileVo.getSubjectsMajorTitle();
		String subjectsTitle = simpleVo.getSubjectsTitle();
		subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
		subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");

		simpleVo.setSubjectsTitle(subjectsTitle);
		profileVo.setSubjectsMajorTitle(subjectsMajorTitle);

		List<InstrVo> classVo = service.getOneInstrClass(classMap);
		int cancelCount = service.getCountClassCancel(userAccountId);

		for (InstrVo instrVo : classVo) {
			instrVo.getSubjectVo().get(0).setSubjCode(subjectsTitle);
		}

		double successRate = (double) classVo.size() / (classVo.size() + cancelCount) * 100;
		String formattedSuccessRate = String.format("%.2f", successRate);

		int allClass = classVo.size() + cancelCount;

		Map<String, Object> reviewMap = new HashMap<String, Object>();
		reviewMap.put("userAccountId", userAccountId);
		reviewMap.put("order", "recent");
		reviewMap.put("start", 1);
		reviewMap.put("end", 5);

		List<ReviewVo> instrReviewVo = service.getOneIntrReview(reviewMap);

		String nickname = simpleVo.getUserProfileVo().get(0).getUserNickname();

		model.addAttribute("title", "강사 조회");
		model.addAttribute("pageTitle", nickname + "님의 프로필");
		model.addAttribute("simpleVo", simpleVo);
		model.addAttribute("profileVo", profileVo);
		model.addAttribute("classVo", classVo);
		model.addAttribute("instrReviewVo", instrReviewVo);
		model.addAttribute("allClass", allClass);
		model.addAttribute("successRate", formattedSuccessRate);

		return "instrDetail";
	}

	/**
	 * 강사 상세 - 경력사항 탭 클릭시 ajax
	 * 
	 * @param userAccountId 강사 아이디
	 * @return careerVo 강사 경력 사항
	 */
	@GetMapping("/instrCareerDetail.do")
	@ResponseBody
	public List<CareerVo> instrCareer(@RequestParam String userAccountId) {
		log.info("instrCareer.do 실행 받아온 강사 아이디: {}", userAccountId);
		List<CareerVo> careerVo = careerService.getOneInstrCareer(userAccountId);
		return careerVo;
	}

	/**
	 * 강사 상세 - 클래스 이력 탭 클릭시 페이징
	 * 
	 * @param map - start 시작 행 번호 / end 끝 행 번호
	 * @return - historyVo 클래스 이력 리스트 / hasMore 끝 페이지 여부
	 */
	@GetMapping("/instrClassDetail.do")
	@ResponseBody
	public Map<String, Object> instrClass(@RequestParam Map<String, Object> map) {
		log.info("instrClassDetail.do 받아온 map : {}", map);
		List<InstrVo> classVo = service.getOneInstrClass(map);

		int end = Integer.parseInt(map.get("end").toString());
		int totalCount = service.classTotalCount(map.get("userAccountId").toString());

		Map<String, Object> response = new HashMap<>();

		response.put("historyVo", classVo);
		response.put("hasMore", end < totalCount);
		log.info("hasMore : {}", response.get("hasMore"));
		return response;
	}

	/**
	 * 강사 상세 - 후기 탭 페이징 및 조회순서 변경
	 * 
	 * @param map - order 조회순서 / start 시작 행 번호 / end 끝 행 번호
	 * @return - instrReviewVo 후기 리스트 / hasMore 마지막 페이지 여부
	 */
	@GetMapping("/instrReviewDetail.do")
	@ResponseBody
	public Map<String, Object> instrReview(@RequestParam Map<String, Object> map) {
		log.info("instrReviewDetail.do 받아온 map : {}", map);

		List<ReviewVo> instrReviewVo = service.getOneIntrReview(map);

		int end = Integer.parseInt(map.get("end").toString());
		int totalCount = service.reviewTotalCount(map.get("userAccountId").toString());

		Map<String, Object> response = new HashMap<>();

		response.put("instrReviewVo", instrReviewVo);
		response.put("hasMore", end < totalCount);

		return response;
	}

	/**
	 * 강사 상세 - 좋아요 클릭시 update 실행
	 * 
	 * @param map - inprAccountId 강사 아이디 / loginId 로그인 아이디
	 * @return - type 좋아요 이미지 타입 / count 좋아요 개수 / inprAccountId 강사 아이디
	 */
	@ResponseBody
	@GetMapping("/userLike.do")
	public Map<String, Object> instrLike(@RequestParam Map<String, Object> map) {
		log.info("instrLike 받아온 map : {}", map);
		String inprAccountId = map.get("inprAccountId").toString();
		InstrVo users = service.getlikeViewUser(inprAccountId);

		String type = map.get("type").toString();

		String accountId = map.get("loginId").toString();

		String likeUsers = (users != null) ? users.getInprLike() : "";

		log.info("getlikeViewUser 출력값 : {}", users);
		Map<String, Object> likeMap = LikeViewUtils.like(type, accountId, likeUsers);
		likeMap.put("inprAccountId", inprAccountId);

		service.updateInstrLike(likeMap);

		Map<String, Object> result = new HashMap<String, Object>() {
			{
				put("type", likeMap.get("type"));
				put("count", likeMap.get("count"));
				put("inprAccountId", likeMap.get("inprAccountId"));
			}
		};

		return result;
	}

	/**
	 * 강사 상세 - 강사 조회수 update 및 첫 페이지 로드시 좋아요, 조회수 반환
	 * 
	 * @param map - inprAccountId 강사 아이디 / loginId 로그인 아이디
	 * @return - viewCount 조회수 / likeCount 좋아요수
	 */
	@ResponseBody
	@GetMapping("/viewCount.do")
	public Map<String, Object> viewCount(@RequestParam Map<String, Object> map) {
		log.info("viewCount.do 실행");

		String userId = map.get("loginId").toString();
		if (userId == null || userId.equals("")) {
			userId = "";
		}

		String inprAccountId = map.get("inprAccountId").toString();

		InstrVo users = service.getlikeViewUser(inprAccountId);

		if (users == null) {
			Map<String, Object> result = new HashMap<String, Object>() {
				{
					put("viewCount", 0);
					put("likeCount", 0);
				}
			};

			return result;
		}

		String viewUsers = users.getInprView();
		int likeCount = users.getInprLikeCount();
		int viewCount = users.getInprViewCount();

		if (!userId.isEmpty()) {
			Map<String, Object> view = LikeViewUtils.view(userId, viewUsers);
			view.put("inprAccountId", inprAccountId);
			log.info("view 내용 : {}", view);
			int n = service.updateInstrView(view);

			if (n > 0) {
				log.info("view 업데이트 성공");
				Map<String, Object> result = new HashMap<String, Object>() {
					{
						put("viewCount", view.get("count"));
						put("likeCount", likeCount);
					}
				};
				return result;
			}

		} else {
			log.info("userId가 비어있어서 view 업데이트 안함");
		}

		Map<String, Object> result = new HashMap<String, Object>() {
			{
				put("viewCount", viewCount);
				put("likeCount", likeCount);
			}
		};

		return result;
	}

}
