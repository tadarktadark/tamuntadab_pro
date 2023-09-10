package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;

public interface ITupyoDao {
	
	//투표 생성
	public int insertTupyo(Map<String, Object> map);
	//투표 조회
	public TupyoVo getTupyo(int tupyClasId);
	//선택지 조회
	public List<TupyoOptionVo> getAllTupyoOption(int tuopTupySeq);
	//선택지 생성
	public int insertTupyoOption(Map<String, Object> map);
	//투표 진행
	public int insertTupyoUser(Map<String, Object> map);
	//투표 유무 판단
	public List<TupyoUserVo> tupyoUserChk(Map<String, Object> map);
	//투표 결과 조회
	public List<TupyoUserVo> getTupyoResult(int tuopTupySeq);
	//재투표
	public int delTupyoUser(TupyoUserVo vo);
	//투표 종료
	public int endTupyo(int tuusSeq);
	//투표 인원수 변경
	public int updateTupyo(int tuusSeq);
	//찬반 투표 구분
	public List<TupyoUserVo> getAgreeUser(Map<String, Object> map);
	//찬반 투표 반영
	public int updateAgreeTupyo(Map<String, Object> map);

}
