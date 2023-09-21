package com.tdtd.tmtd.model.mapper;

import java.util.List;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;

public interface IMainDao {
	
	public List<SubjectTagVo> getAllSubjectTag();
	
	public List<ClassVo> getAllClass();
	
	public List<InstrVo> getIngiInstr();
 
}
