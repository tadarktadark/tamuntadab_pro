package com.tdtd.tmtd.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.model.mapper.IPilgiDao;
import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

@Service
public class PilgiServiceImpl implements IPilgiService {
	
	@Autowired
	private IPilgiDao dao;

	@Override
	public int getPilgiCount(String accountId) {
		return dao.getPilgiCount(accountId);
	}

	@Override
	public List<BoardVo> getPilgiList(Map<String, Object> map) {
		return dao.getPilgiList(map);
	}

	@Override
	public BoardVo getPilgiDetail(Map<String, Object> map) {
		String list = dao.getPilgiViewUser((String)map.get("id"));		
		Map<String, Object> view = LikeViewUtils.view((String)map.get("id"), list);
		if((int)view.get("update")==1) {
			Map<String, Object> data = new HashMap<String, Object>(){{
				put("viewUser", view.get("list"));
				put("viewCount", view.get("count"));
				put("id", (String)map.get("id"));
			}};
			dao.updatePilgiViewUser(data);
		}
		return dao.getPilgiDetail(map);
	}

	@Override
	public List<BoardVo> getYeongwanList(Map<String, Object> map) {
		return dao.getYeongwanList(map);
	}

	@Override
	public String getPilgiLikeUser(String id) {
		return dao.getPilgiLikeUser(id);
	}

	@Override
	public int updatePilgiLikeUser(Map<String, Object> map) {
		return dao.updatePilgiLikeUser(map);
	}

	@Override
	public int getMyPilgiCount(String accountId) {
		return dao.getMyPilgiCount(accountId);
	}

	@Override
	public List<BoardVo> getMyPilgiList(Map<String, Object> map) {
		return dao.getMyPilgiList(map);
	}

	@Override
	public int getLikePilgiCount(String accountId) {
		return dao.getLikePilgiCount(accountId);
	}

	@Override
	public List<BoardVo> getLikePilgiList(Map<String, Object> map) {
		return dao.getLikePilgiList(map);
	}

	@Override
	public ClassVo getPilgiClassDetail(String clasId) {
		return dao.getPilgiClassDetail(clasId);
	}

	@Override
	public int insertPilgi(BoardVo vo, Map<String, Object> map) {
		int n = 0;
		n += dao.insertPilgi(vo);
		n += dao.updateClchPilgiState(map);
		return (n==0)?1:0;
	}

	@Override
	public int insertPilgiImsi(BoardVo vo) {
		return dao.insertPilgiImsi(vo);
	}

	@Override
	public List<BoardVo> getPilgiImsiList(Map<String, Object> map) {
		return dao.getPilgiImsiList(map);
	}

	@Override
	public BoardVo getPilgiImsiDetail(String id) {
		return dao.getPilgiImsiDetail(id);
	}

	@Override
	public int deletePilgiImsi(String id) {
		return dao.deletePilgiImsi(id);
	}

	@Override
	public int updatePilgi(BoardVo vo) {
		return dao.updatePilgi(vo);
	}

	@Override
	public int updatePilgiState(Map<String, Object> bMap, Map<String, Object> cMap) {
		int n = 0;
		n += dao.updatePilgiState(bMap);
		n += dao.updateClchPilgiState(cMap);
		return (n==2)?1:0;
	}

	@Override
	public int deletePilgi(String id) {
		return dao.deletePilgi(id);
	}

}
