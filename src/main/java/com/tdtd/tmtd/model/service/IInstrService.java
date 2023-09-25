package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;

public interface IInstrService {

		//TDT003 getMyInstrProfile
		public InstrVo getMyInstrProfile(String userAccountId);
		
		//TDT004 insertInstrProfile
		//TDT005 insertInstrEdulevel
		public int insertInstrProfile(InstrVo vo);
		
		//TDT006 updateInstrProfile
		//TDT007 updateInstrEdulevel
		public int updateInstrProfile(InstrVo vo);
		
		public int deleteInstrEdulevel(String inedSeq);
		
		//TDT018 getAllInstr
		public List<InstrVo> getAllInstr(Map<String, Object> map);
		
		public int getAllInstrCount();
		
		//TDT019 updateInstrLike
		public int updateInstrLike(Map<String, Object> map);
		
		//TDT020 updateInstrView
		public int updateInstrView(Map<String, Object> map);
		
		public InstrVo getlikeViewUser(String inprAccountId);
		
		//TDT021 getOneInstrSimple
		public InstrVo getOneInstrSimple(Map<String, Object> map);
		 
		//TDT022 getOneInstrProfile
		public InstrVo getOneInstrProfile(String userAccountId);
		
		//TDT024 getOneInstrClass
		public List<InstrVo> getOneInstrClass(Map<String, Object> map);
		
		public int getCountClassCancel(String userAccountId);
		
		//TDT025 getOneIntrReview
		public List<ReviewVo> getOneIntrReview(Map<String, Object> map);
		
		public int classTotalCount(String userAccountId);
		
		public int reviewTotalCount(String userAccountId);
		
}
