package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ISubjectDao;
import com.tdtd.tmtd.vo.SubjectVo;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private ISubjectDao dao;
	
	@Override
	public List<SubjectVo> getSubjectList(Map<String, Object> map) {
		return dao.getSubjectList(map);
	}

	@Override
	public int getSubjectListCount() {
		return dao.getSubjectListCount();
	}

	@Override
	public int updateSubject(SubjectVo vo) {
		return 0;
	}

	@Override
	public int updateClass(Map<String, Object> map) {
		return 0;
	}

}
