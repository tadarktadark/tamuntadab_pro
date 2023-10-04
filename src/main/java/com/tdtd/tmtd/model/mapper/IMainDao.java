package com.tdtd.tmtd.model.mapper;

import java.util.List;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;

/**
 * 메인 페이지 관련 DAO
 * @author 문희애
 *
 */
public interface IMainDao {
	
	/**
	 * 과목 전체 리스트 조회
	 */
	public List<SubjectTagVo> getAllSubjectTag();
	
	/**
	 * 모집중인 클래스 전체 조회
	 */
	public List<ClassVo> getAllClass();
	
	/**
	 * 인기강사 전체 조회
	 */
	public List<InstrVo> getIngiInstr();
 
}
