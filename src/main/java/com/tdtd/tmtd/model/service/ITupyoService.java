package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;

public interface ITupyoService {

	//TDT066 투표 생성
	public int insertTupyo(Map<String, Object> map);
	//TDT067 투표 조회
	public TupyoVo getTupyo(int tupyClasId);
	//TDT068 선택지 조회
	public List<TupyoOptionVo> getAllTupyoOption(int tuopTupySeq);
	//TDT069 선택지 생성
	public int insertTupyoOption(Map<String, Object> map);
	//투표 유무 판단
	public List<TupyoUserVo> tupyoUserChk(Map<String, Object> map);
	//TDT070 투표 진행
	public int insertTupyoUser(Map<String, Object> map);
	//TDT071 투표 결과 조회
	public List<TupyoUserVo> getTupyoResult(int tuopTupySeq);
	//TDT072 재투표
	public int delTupyoUser(TupyoUserVo vo);
	//TDT073 투표 종료
	public int endTupyo(int tuusSeq);
	//TDT074 투표 인원수 변경
	public int updateTupyo(int tuusSeq);
	//TDT075 찬반 투표 구분
	public List<TupyoUserVo> getAgreeUser(Map<String, Object> map);
	//TDT076 찬반 투표 반영
	public int updateAgreeTupyo(Map<String, Object> map);
	
}
