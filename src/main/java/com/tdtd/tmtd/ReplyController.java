package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IReplyService;
import com.tdtd.tmtd.vo.ReplyVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	@RequestMapping(value="/getReplyList.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReplyList(String page, String boardId) {
		log.info("@@@@@@@@@@@@@@@ 댓글 보기 : boardId {}", boardId);
		
		int pageCount = service.getReplyCount(boardId);
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 5, 5);
		
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("boardId", boardId);
		rMap.put("start", pMap.get("start"));
		rMap.put("end", pMap.get("end"));
		List<ReplyVo> list = service.getReplyList(rMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pMap.get("page"));
		result.put("reply", list);
		return result;
	}
}
