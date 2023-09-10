package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IYeyakService;
import com.tdtd.tmtd.vo.GangeuisilVo;

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
		model.addAttribute("pageTitle", "예약");
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
	
	@RequestMapping(value="/getGangeuisilDetailList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<GangeuisilVo> getGangeuisilDetailList(String gacoId) {
		log.info("@@@@@@@@@@@@@@@ 개별 강의실 목록 조회");
		List<GangeuisilVo> list = service.getGangeuisilDetailList(gacoId);
		return list;
	}
}
