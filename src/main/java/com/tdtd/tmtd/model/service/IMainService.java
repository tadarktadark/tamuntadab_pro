package com.tdtd.tmtd.model.service;

import java.util.List;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;

public interface IMainService {
	
	public List<SubjectTagVo> getAllSubjectTag();
	
	public List<ClassVo> getAllClass();
	
	public List<InstrVo> getIngiInstr();

}
