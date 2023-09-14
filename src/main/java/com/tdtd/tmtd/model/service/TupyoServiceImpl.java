package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ITupyoDao;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;

@Service
public class TupyoServiceImpl implements ITupyoService {
	
	@Autowired
	private ITupyoDao dao;

	@Override
	public int insertTupyo(Map<String, Object> map) {
		return dao.insertTupyo(map);
	}

	@Override
	public TupyoVo getTupyo(int tupyClasId) {
		return dao.getTupyo(tupyClasId);
	}

	@Override
	public List<TupyoOptionVo> getAllTupyoOption(int tuopTupySeq) {
		return dao.getAllTupyoOption(tuopTupySeq);
	}

	
	@Override
	public int insertTupyoOption(Map<String, Object> map) {
		return dao.insertTupyoOption(map);
	}
	
	@Override
	public ChamyeoVo getClassMaster(int clchClasId) {
		return dao.getClassMaster(clchClasId);
	}

	@Override
	public List<ChamyeoVo> getClassMember(Map<String, Object> map) {
		return dao.getClassMember(map);
	}

	@Override
	public int countTotalClassMember(int clchClasId) {
		return dao.countTotalClassMember(clchClasId);
	}
	
	@Override
	public List<TupyoUserVo> tupyoUserChk(Map<String, Object> map) {
		return dao.tupyoUserChk(map);
	}


	@Override
	public int insertTupyoUser(Map<String, Object> map) {
		return dao.insertTupyoUser(map);
	}

	@Override
	public List<TupyoUserVo> getTupyoResult(int tuopTupySeq) {
		return dao.getTupyoResult(tuopTupySeq);
	}

	@Override
	public int delTupyoUser(TupyoUserVo vo) {
		return dao.delTupyoUser(vo);
	}

	@Override
	public int endTupyo(int tuusSeq) {
		return dao.endTupyo(tuusSeq);
	}

	@Override
	public int updateTupyo(int tuusSeq) {
		return dao.updateTupyo(tuusSeq);
	}

	@Override
	public List<TupyoUserVo> getAgreeUser(Map<String, Object> map) {
		return dao.getAgreeUser(map);
	}

	@Override
	public int updateAgreeTupyo(Map<String, Object> map) {
		return dao.updateAgreeTupyo(map);
	}

	@Override
	public List<ChamyeoVo> getAllInstr(int clchClasId) {
		return dao.getAllInstr(clchClasId);
	}

	@Override
	public List<ChamyeoVo> getAllClassMember(int clchClasId) {
		return dao.getAllClassMember(clchClasId);
	}

	@Override
	public List<TupyoUserVo> getAllVotedStudents(int tuopTupySeq) {
		return dao.getAllVotedStudents(tuopTupySeq);
	}

	@Override
	public TupyoOptionVo getTupyoOption(int tuopSeq) {
		return dao.getTupyoOption(tuopSeq);
	}

	@Override
	public int updateClasAccountId(Map<String, Object> map) {
		return dao.updateClasAccountId(map);
	}

	



}
