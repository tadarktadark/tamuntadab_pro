package com.tdtd.tmtd.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.model.mapper.IJilmunDao;
import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

@Service
public class JilmunServiceImpl implements IJilmunService {

	@Autowired
	private IJilmunDao dao;	
	
	@Override
	public int getJilmunCount() {
		return dao.getJilmunCount();
	}

	@Override
	public List<BoardVo> getJilmunList(Map<String, Object> map) {
		return dao.getJilmunList(map);
	}

	@Override
	public BoardVo getJilmunDetail(Map<String, Object> map) {
		String accountId = (String)map.get("accountId");
		
		if(!accountId.equals("TMTD0")) {	
			String list = dao.getJilmunViewUser((String)map.get("id"));		
			Map<String, Object> view = LikeViewUtils.view(accountId, list);
			if((int)view.get("update")==1) {
				Map<String, Object> data = new HashMap<String, Object>(){{
					put("viewUser", view.get("list"));
					put("viewCount", view.get("count"));
					put("id", (String)map.get("id"));
				}};
				dao.updateJilmunViewUser(data);
			}
		}
		return dao.getJilmunDetail(map);
	}

	@Override
	public String getJilmunLikeUser(String id) {
		return dao.getJilmunLikeUser(id);
	}

	@Override
	public int updateJilmunLikeUser(Map<String, Object> map) {
		return dao.updateJilmunLikeUser(map);
	}

	@Override
	public int getMyJilmunCount(String accountId) {
		return dao.getMyJilmunCount(accountId);
	}

	@Override
	public List<BoardVo> getMyJilmunList(Map<String, Object> map) {
		return dao.getMyJilmunList(map);
	}

	@Override
	public int getLikeJilmunCount(String accountId) {
		return dao.getLikeJilmunCount(accountId);
	}

	@Override
	public List<BoardVo> getLikeJilmunList(Map<String, Object> map) {
		return dao.getLikeJilmunList(map);
	}

	@Override
	public List<ClassVo> getJilmunClassList(String accountId) {
		return dao.getJilmunClassList(accountId);
	}

	@Override
	public ClassVo getJilmunSubject(String clasId) {
		return dao.getJilmunSubject(clasId);
	}
	
	@Override
	public int insertJilmun(BoardVo vo) {
		return dao.insertJilmun(vo);
	}
	
	@Override
	public BoardVo getJilmunUpdateData(String id) {
		return dao.getJilmunUpdateData(id);
	}

	@Override
	public int updateJilmun(BoardVo vo) {
		return dao.updateJilmun(vo);
	}

	@Override
	public int deleteJilmun(String id) {
		return dao.deleteJilmun(id);
	}

}
