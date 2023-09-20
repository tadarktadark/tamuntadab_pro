package com.tdtd.tmtd.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.model.mapper.IJayuDao;
import com.tdtd.tmtd.vo.BoardVo;

@Service
public class JayuServiceImpl implements IJayuService {

	@Autowired
	private IJayuDao dao;

	@Override
	public int getJayuCount() {
		return dao.getJayuCount();
	}

	@Override
	public List<BoardVo> getJayuList(Map<String, Object> map) {
		return dao.getJayuList(map);
	}

	@Override
	public BoardVo getJayuDetail(Map<String, Object> map) {
		String accountId = (String)map.get("accountId");
		
		if(!accountId.equals("TMTD0")) {	
			String list = dao.getJayuViewUser((String)map.get("id"));		
			Map<String, Object> view = LikeViewUtils.view(accountId, list);
			if((int)view.get("update")==1) {
				Map<String, Object> data = new HashMap<String, Object>(){{
					put("viewUser", view.get("list"));
					put("viewCount", view.get("count"));
					put("id", (String)map.get("id"));
				}};
				dao.updateJayuViewUser(data);
			}
		}
		return dao.getJayuDetail(map);
	}

	@Override
	public String getJayuLikeUser(String id) {
		return dao.getJayuLikeUser(id);
	}

	@Override
	public int updateJayuLikeUser(Map<String, Object> map) {
		return dao.updateJayuLikeUser(map);
	}

	@Override
	public int getMyJayuCount(String accountId) {
		return dao.getMyJayuCount(accountId);
	}

	@Override
	public List<BoardVo> getMyJayuList(Map<String, Object> map) {
		return dao.getMyJayuList(map);
	}

	@Override
	public int getLikeJayuCount(String accountId) {
		return dao.getLikeJayuCount(accountId);
	}

	@Override
	public List<BoardVo> getLikeJayuList(Map<String, Object> map) {
		return dao.getLikeJayuList(map);
	}

	@Override
	public int insertJayu(BoardVo vo) {
		return dao.insertJayu(vo);
	}

	@Override
	public BoardVo getJayuUpdateData(String id) {
		return dao.getJayuUpdateData(id);
	}
	
	@Override
	public int updateJayu(BoardVo vo) {
		return dao.updateJayu(vo);
	}

	@Override
	public int deleteJayu(String id) {
		return dao.deleteJayu(id);
	}
	
	
}
