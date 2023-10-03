package com.tdtd.tmtd.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ISingoDao;
import com.tdtd.tmtd.vo.SingoDaesangVo;
import com.tdtd.tmtd.vo.SingoSayuVo;

@Service
public class SingoServiceImpl implements ISingoService {
	
	@Autowired
	private ISingoDao dao;

	@Override
	public List<SingoSayuVo> getSingoCategory() {
		return dao.getSingoCategory();
	}

	@Override
	public int userSingo(String daesangId, String board, SingoSayuVo sVo) {
		SingoDaesangVo dVo = dao.getSingoDaesangId(daesangId);
		int n = 0;
		if(dVo!=null) {
			n += dao.updateSingo(dVo);
			if(dVo.getCount()==4) {				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("board", board);
				map.put("daesangId", daesangId);
				map.put("state", "P");
				n += dao.updateBoardState(map);
			}
		} else {
			dVo = new SingoDaesangVo();
			dVo.setDaesangId(daesangId);
			n += dao.insertSingoDaesang(dVo);
		}
		sVo.setId(dVo.getId());
		int m = dao.insertSingoSayu(sVo);
		return (n>0&&m>0)?1:0;
	}
	
	@Override
	public int getMaxCount() {
		return dao.getMaxCount();
	}

	@Override
	public List<SingoDaesangVo> getMaxSingo(Map<String, Object> map) {
		return dao.getMaxSingo(map);
	}
	
	@Override
	public int adminAction(SingoDaesangVo vo) {
		SingoDaesangVo dVo = dao.getSingoDaesang(vo.getId());
		int n = dao.updateSingoDaesangState(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("daesangId", dVo.getDaesangId());
		if(dVo.getDaesangId().substring(0,2).equals("PI")) {
			map.put("board", "pilgi");
		} else if(dVo.getDaesangId().substring(0,2).equals("JI")) {
			map.put("board", "jilmun");
		} else if(dVo.getDaesangId().substring(0,2).equals("JA")) {
			map.put("board", "jayu");
		} else {
			map.put("board", "reply");
		}
		if (vo.getState().equals("D")) {			
			SingoDaesangVo result = dao.getSingoUser(dVo.getAccountId());
			int m = 0;
			if(result != null) {
				m += dao.updateSingoUserCount(result.getId());
			} else {
				m += dao.insertSingoUser(dVo.getAccountId());
			}
			map.put("state", "D");
		} else {			
			map.put("state", "N");
		}
		dao.updateBoardState(map);
		return (n>0)?1:0;
	}
}
