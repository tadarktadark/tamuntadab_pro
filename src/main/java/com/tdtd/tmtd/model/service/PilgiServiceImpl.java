package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public String getPilgiViewUser(String id) {
		return dao.getPilgiViewUser(id);
	}

	@Override
	public int updatePilgiViewUser(Map<String, Object> map) {
		return dao.updatePilgiViewUser(map);
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
	public int insertPilgi(BoardVo vo) {
		return dao.insertPilgi(vo);
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
	public int updatePilgiDel(String id) {
		return dao.updatePilgiDel(id);
	}

	@Override
	public int updatePilgiGesi(String id) {
		return dao.updatePilgiGesi(id);
	}

	@Override
	public int deletePilgi(String id) {
		return dao.deletePilgi(id);
	}

}
