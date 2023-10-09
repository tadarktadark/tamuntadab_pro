package com.tdtd.tmtd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IYeyakService;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.UserProfileVo;
import com.tdtd.tmtd.vo.YeyakVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 예약 관련 컨트롤러
 * @author SoHyeon
 * @since 2023.09.09
 */
@Controller
@Slf4j
public class YeyakController {
	
	@Autowired
	private IYeyakService service;
	
	/**
	 * 예약 페이지 이동
	 * @param model
	 * @return 예약jsp
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	@RequestMapping(value="/yeyak.do", method = RequestMethod.GET)
	public String yeyak(Model model) {
		log.info("@@@@@@@@@@@@@@@ 예약 페이지 이동");
		model.addAttribute("title","예약");
		model.addAttribute("pageTitle", "예약하기");
		return "yeyak";
	}
	
	/**
	 * 시도 강의실 목록 조회
	 * @return 강의실 시도 list
	 * @author SoHyeon 
	 * @since 2023.09.09
	 */
	@RequestMapping(value="/getGangeuisilSidoList.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getGangeuisilSidoList() {
		log.info("@@@@@@@@@@@@@@@ 시도 강의실 목록 조회");
		int count = service.getGangeuisilCount(null);
		Map<String, Object> pMap = PagingUtils.paging("1", count, 5, 5); 
		Map<String, Object> result = new HashMap<String, Object>(){{
			put("list", service.getGangeuisilSidoList());
			put("count", count);
			put("page", pMap.get("page"));
		}};
		return result;
	}
	
	/**
	 * 공통 강의실 목록 조회
	 * @param page 페이지
	 * @param type 시도/시군구
	 * @param sendData 
	 * @return 강의실 개수, 강의실list, 페이지
	 * @author SoHyeon 
	 * @since 2023.09.09
	 */
	@RequestMapping(value="/getGangeuisilList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGangeuisilList(String page, String type, String sendData){
		log.info("@@@@@@@@@@@@@@@ 공통 강의실 목록 조회 :{}, {}, {}", page, type, sendData);
		Map<String, Object> map = new HashMap<String, Object>();
		if(type.equals("sido")) {
			map.put("gacoSido", sendData);
		} else if(type.equals("sigungu")){
			map.put("gacoSigungu", sendData);
		}
		int count = service.getGangeuisilCount(map);
		Map<String, Object> pMap = PagingUtils.paging(page, count, 5, 5);
		map.put("start", pMap.get("start") );
		map.put("end", pMap.get("end"));
		List<GangeuisilVo> list = service.getGangeuisilList(map);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>(){{
			put("count", count);
			put("list", list);
			put("page", pMap.get("page"));
		}};
		return result;
	}
	
	/**
	 * 시군구 목록 조회
	 * @param sido
	 * @return 강의실list, 페이지
	 * @author SoHyeon 
	 * @since 2023.09.09
	 */
	@RequestMapping(value="/getGangeuisilSigunguList.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getGangeuisilSigunguList(String sido){
		log.info("@@@@@@@@@@@@@@@ 시군구 강의실 목록 조회 : {}", sido);
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("gacoSido", sido);
		}};
		int count = service.getGangeuisilCount(map);
		List<String> list = service.getGangeuisilSigunguList(sido);
		Map<String, Object> result = new HashMap<String, Object>(){{
			put("count", count);
			put("list", list);
		}};
		return result;
	}

	/**
	 * 개별 강의실 목록 조회
	 * @param gacoId 공통 강의실 id
	 * @return 해당 공통 강의실이 개별 강의실 list
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	@RequestMapping(value="/getGangeuisilDetailList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<GangeuisilVo> getGangeuisilDetailList(String gacoId) {
		log.info("@@@@@@@@@@@@@@@ 개별 강의실 목록 조회 : {}", gacoId);
		List<GangeuisilVo> list = service.getGangeuisilDetailList(gacoId);
		return list;
	}
	
	/**
	 * 예약자 정보 조회
	 * @param accountId 계정id
	 * @param gagaId 선택한 개별 강의실 id
	 * @param date 선택한 날짜
	 * @return 선택 가능 클래스/해당 강의실 여유 시간
	 * @author SoHyeon
	 * @since 2023.09.11
	 */
	@RequestMapping(value="/getYeyakInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getYeoyuTime(String accountId, String gagaId, String date) {
		log.info("@@@@@@@@@@@@@@@ 예약 정보 조회(예약자 정보, 예약 가능한 날짜, 시간) : {}, {}, {}", accountId, gagaId, date);
		GangeuisilVo gVo = service.getYeoyuTime(gagaId);
		Gson gson = new Gson();
		Map<String, List<String>> dMap = gson.fromJson(gVo.getGagaYeoyuTime(), Map.class);
		
		if(date==null) {
			List<String> dList = new ArrayList<String>();
			for (String key : dMap.keySet()) {
				if(dMap.get(key).size()==0) {
					dList.add(key.substring(0,4)+"-"+key.substring(4,6)+"-"+key.substring(6));
				}
			}
			String dJson = gson.toJson(dList, List.class);
			
			List<ClassVo> clist = service.getchamyeoClassList(accountId);
			String html = "";
			html += "<option selected>선택 안함</option>";
			for (ClassVo cVo : clist) {
				html += "<option id="+cVo.getClasId()+" value='"+cVo.getClasId()+"'>"+cVo.getClasTitle()+" ("+cVo.getClasHyeonjaeInwon()+")</option>";
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("date", dJson.substring(1,dJson.length()-1));
			result.put("html", html);
			return result;
		} else {
		    int open = Integer.parseInt(gVo.getGacoOpen());
		    int close = Integer.parseInt(gVo.getGacoClose());
		    List<String> times = new ArrayList<String>();
		    String t = "";
		    
		    for(int i=open; i<close; i+=100) {
	    		t = String.valueOf(i);
		    	t = (t.length()==3)?"0"+t:t;	    		
		    	times.add(t);
		    }
			date=date.replaceAll("-", "");
			String html = "";
			
			Calendar c = Calendar.getInstance(); 
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
			String currHour = hourFormat.format(c.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String currDate = dateFormat.format(c.getTime());
			for (int i = 0; i < times.size(); i++) {
				if(currDate.equals(date) && Integer.parseInt(times.get(i).substring(0,2)) <= Integer.parseInt(currHour)) {
					html+="<button type='button' class='btn btn-light time-btn' disabled='disabled'>"+times.get(i).substring(0,2)+":"+times.get(i).substring(2)+"</button>";
				} else if(dMap.get(date).contains(times.get(i))) {
					html+="<button type='button' id='"+times.get(i)+"' class='btn btn-outline-primary time-btn'>"+times.get(i).substring(0,2)+":"+times.get(i).substring(2)+"</button>";										
				} else {
					html+="<button type='button' class='btn btn-light time-btn' disabled='disabled'>"+times.get(i).substring(0,2)+":"+times.get(i).substring(2)+"</button>";															
				}
			}
			return html;
		}
	}
	
	/**
	 * 예약 정보 입력
	 * @param yVo 예약vo
	 * @param gVo 강의실 vo
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.11
	 */
	@RequestMapping(value="/insertYeyakInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public int insertYeyakInfo(YeyakVo yVo, GeoljeVo gVo) {
		log.info("@@@@@@@@@@@@@@@ 예약 : yVo {}, gVo {}", yVo, gVo);
		return service.insertYeakInfo(yVo, gVo);
	}
	
	/**
	 * 마이페이지 예약 일정 조회
	 * @param session
	 * @param page 현재 페이지
	 * @return 페이지, 예약 vo list
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	@RequestMapping(value="/getMyYeyakList.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getMyYeyakList(HttpSession session, String page) {
		log.info("@@@@@@@@@@@@@@@ 내 강의실 예약 조회 : page {}", page);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
				
		int pageCount; // 전체 게시글 수
		Map<String, Object> pMap = new HashMap<String, Object>(); // page 객체 및 start, end
		Map<String, Object> bMap = new HashMap<String, Object>(); // board 관련 accountId, orderBy, start, end
		bMap.put("accountId", userInfo.getUserAccountId());
		
		pageCount = service.getMyYeyakCount(userInfo.getUserAccountId());
		pMap = PagingUtils.paging(page, pageCount, 5, 5);
		
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pMap.get("page"));
		result.put("board", service.getMyYeyakList(bMap));
		
		return result;
	}
	
	/**
	 * 예약 취소
	 * @param session
	 * @param vo 예약 vo
	 * @return 성공1, 실패0
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	@RequestMapping(value="/yeyakCancel.do", method = RequestMethod.POST)
	@ResponseBody
	public int yeyakCancel(HttpSession session, YeyakVo vo) {
		log.info("@@@@@@@@@@@@@@@ 강의실 예약 취소 : vo {}", vo);
		
		return service.yeyakCancel(vo);
	}
	
	/**
	 * 예약 결제 상태 조회
	 * @param session
	 * @param gayeId 예약 id
	 * @return 결제vo
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	@RequestMapping(value="/getGyeojeStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public List<GeoljeVo> getGyeojeStatus(HttpSession session, String gayeId) {
		log.info("@@@@@@@@@@@@@@@ 예약 결제 상태 조회 : gayeId {}", gayeId);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		List<GeoljeVo> list = service.getGyeojeStatus(gayeId);
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getGyeoAccountId().equals(userInfo.getUserAccountId())) {
				list.get(i).setGyeoId("나");
				break;
			}
		}
		
		return list;
	}
}
