package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;

public interface ITupyoDao {
	
	//투표 생성
	public int insertTupyo(Map<String, Object> map);
	//클래스 참여 중인 강사 조회
	public List<ChamyeoVo> getAllInstr(int clchClasId);
	//투표 조회
	public TupyoVo getTupyo(int tupyClasId);
	//선택지 조회
	public List<TupyoOptionVo> getAllTupyoOption(int tuopTupySeq);
	//특정 선택지 조회
	public TupyoOptionVo getTupyoOption(int tuopSeq);
	//선택지 생성
	public int insertTupyoOption(Map<String, Object> map);
	//클래스 개설자 조회
	public ChamyeoVo getClassMaster(int clchClasId);
	//클래스 모든 참여자 조회
	public List<ChamyeoVo> getAllClassMember(int clchClasId);
	//클래스 참여 중인 학생인지 판단
	public List<ChamyeoVo> getClassMember(Map<String, Object> map);
	//클래스 참여 중인 학생 수 계산
	public int countTotalClassMember(int clchClasId);
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
	//투표한 참가자 조회
	public List<TupyoUserVo> getAllVotedStudents(int tuopTupySeq);
	//강사 확정
	public int updateClasAccountId(Map<String, Object> map);
	//투표 삭제
	public int delTupyo(int tupySeq);
	//투표옵션 삭제
	public int delTupyoOption(int tupySeq);
	//투표유저 삭제
	public int delAllTupyoUser(int tupySeq);
	
}
