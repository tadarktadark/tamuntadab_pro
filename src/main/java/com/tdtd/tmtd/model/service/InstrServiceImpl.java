package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IInstrDao;
import com.tdtd.tmtd.vo.InstrVo;

@Service
public class InstrServiceImpl implements IInstrService {
	
	@Autowired
	private IInstrDao dao;

	@Override
	public InstrVo getMyInstrProfile(String userAccountId) {
		return dao.getMyInstrProfile(userAccountId);
	}

	@Override
	public int insertInstrProfile(InstrVo vo) {
		int m = dao.insertInstrProfile(vo);
		if(vo.getInedStage() != 0) {
			int n = dao.insertInstrEdulevel(vo);
			return (m>0 || n>0) ? 1:0;
		}
		return m;
	}

	@Override
	public int updateInstrProfile(InstrVo vo) {
		int m = dao.updateInstrProfile(vo);
		int n = dao.updateInstrEdulevel(vo);
		return (m>0 || n>0) ? 1:0;
	}

	@Override
	public List<InstrVo> getAllInstr(String order) {
		return dao.getAllInstr(order);
	}

	@Override
	public int updateInstrLike(InstrVo vo) {
		return dao.updateInstrLike(vo);
	}

	@Override
	public int updateInstrView(InstrVo vo) {
		return dao.updateInstrView(vo);
	}

	@Override
	public InstrVo getOneInstrSimple(String userAccountId) {
		return dao.getOneInstrSimple(userAccountId);
	}

	@Override
	public InstrVo getOneInstrProfile(String userAccountId) {
		return dao.getOneInstrProfile(userAccountId);
	}

	@Override
	public List<InstrVo> getOneInstrClass(String userAccountId) {
		return dao.getOneInstrClass(userAccountId);
	}

	@Override
	public List<InstrVo> getOneIntrReview(Map<String, Object> map) {
		return dao.getOneIntrReview(map);
	}

}
