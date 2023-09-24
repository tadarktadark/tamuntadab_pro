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
	public List<SingoDaesangVo> getMaxSingo() {
		return dao.getMaxSingo();
	}
	
	@Override
	public int adminAction(SingoDaesangVo vo) {
		int n = dao.updateSingoDaesangState(vo);
		SingoDaesangVo result = dao.getSingoUser(vo.getAccountId());
		int m = 0;
		if(result != null) {
			m += dao.updateSingoUserCount(result.getId());
		} else {
			m += dao.insertSingoUser(vo.getAccountId());
		}
		return (n>0&&m>0)?1:0;
	}
}
