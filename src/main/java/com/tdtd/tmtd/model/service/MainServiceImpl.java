package com.tdtd.tmtd.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IMainDao;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;

/**
 * 메인 페이지 관련 Service
 * @author 문희애
 *
 */
@Service
public class MainServiceImpl implements IMainService {
	
	@Autowired
	private IMainDao dao;
	

	/**
	 * 과목 전체 리스트 조회
	 */
	@Override
	public List<SubjectTagVo> getAllSubjectTag() {
		return dao.getAllSubjectTag();
	}

	/**
	 * 모집중인 클래스 전체 조회
	 */
	@Override
	public List<ClassVo> getAllClass() {
		return dao.getAllClass();
	}

	/**
	 * 인기강사 전체 조회
	 */
	@Override
	public List<InstrVo> getIngiInstr() {
		return dao.getIngiInstr();
	}

}
