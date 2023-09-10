package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;

public interface IInstrDao {

	//TDT003 getMyInstrProfile
	public InstrVo getMyInstrProfile(String userAccountId);
	
	//TDT004 insertInstrProfile
	public int insertInstrProfile(InstrVo vo);
	
	//TDT005 insertInstrEdulevel
	public int insertInstrEdulevel(InstrEduVo vo);
	
	//TDT006 updateInstrProfile
	public int updateInstrProfile(InstrVo vo);
	
	//TDT007 updateInstrEdulevel
	public int updateInstrEdulevel(InstrEduVo vo);
	
	//TDT018 getAllInstr
	public List<InstrVo> getAllInstr(String order);
	
	//TDT019 updateInstrLike
	public int updateInstrLike(InstrVo vo);
	
	//TDT020 updateInstrView
	public int updateInstrView(InstrVo vo);
	
	//TDT021 getOneInstrSimple
	public InstrVo getOneInstrSimple(String userAccountId);
	 
	//TDT022 getOneInstrProfile
	public InstrVo getOneInstrProfile(String userAccountId);
	
	//TDT024 getOneInstrClass
	public List<InstrVo> getOneInstrClass(String userAccountId);
	
	//TDT025 getOneIntrReview
	public List<InstrVo> getOneIntrReview(Map<String, Object> map);
	
}
