package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.InstrVo;

public interface IInstrService {

		//TDT003 getMyInstrProfile
		public InstrVo getMyInstrProfile(String userAccountId);
		
		//TDT004 insertInstrProfile
		//TDT005 insertInstrEdulevel
		public int insertInstrProfile(InstrVo vo);
		
		//TDT006 updateInstrProfile
		//TDT007 updateInstrEdulevel
		public int updateInstrProfile(InstrVo vo);
		
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
