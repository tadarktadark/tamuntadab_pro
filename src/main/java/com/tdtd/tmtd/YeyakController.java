package com.tdtd.tmtd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.tdtd.tmtd.vo.YeyakVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class YeyakController {
	
	@Autowired
	private IYeyakService service;
	
	@RequestMapping(value="/yeyak.do", method = RequestMethod.GET)
	public String yeyak(Model model) {
		log.info("@@@@@@@@@@@@@@@ 예약 페이지 이동");
		model.addAttribute("title","예약");
		model.addAttribute("pageTitle", "예약하기");
		return "yeyak";
	}
	
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

	@RequestMapping(value="/getGangeuisilDetailList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<GangeuisilVo> getGangeuisilDetailList(String gacoId) {
		log.info("@@@@@@@@@@@@@@@ 개별 강의실 목록 조회 : {}", gacoId);
		List<GangeuisilVo> list = service.getGangeuisilDetailList(gacoId);
		return list;
	}
	
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
				html += "<option value='"+cVo.getClasId()+"'>"+cVo.getClasTitle()+" ("+cVo.getClasHyeonjaeInwon()+")</option>";
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
			for (int i = 0; i < times.size(); i++) {
				if(dMap.get(date).contains(times.get(i))) {
					html+="<button type='button' id='"+times.get(i)+"' class='btn btn-outline-primary time-btn'>"+times.get(i).substring(0,2)+":"+times.get(i).substring(2)+"</button>";										
				} else {
					html+="<button type='button' class='btn btn-light time-btn' disabled='disabled'>"+times.get(i).substring(0,2)+":"+times.get(i).substring(2)+"</button>";															
				}
			}
			return html;
		}
	}
	
}
