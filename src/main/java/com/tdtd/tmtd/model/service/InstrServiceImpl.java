package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IInstrDao;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;

@Service
public class InstrServiceImpl implements IInstrService {
	
	@Autowired
	private IInstrDao dao;

	@Override
	public InstrVo getMyInstrProfile(String userAccountId) {
		return dao.getMyInstrProfile(userAccountId);
	}

	@Override
	public int insertInstrProfile(InstrVo vo) {
		int m = dao.insertInstrProfile(vo);
		if (vo.getInstrEduVo() != null && !vo.getInstrEduVo().isEmpty()) {
			System.out.println("$$$$$$$$$$$$$insertInstrEdulevel실행");
			System.out.println("$$$$$$$$$$$$$ seq:"+vo.getInprSeq());
	        List<InstrEduVo> eduLevels = vo.getInstrEduVo();
	        for (InstrEduVo eduLevel : eduLevels) {
	            eduLevel.setInedInprSeq(vo.getInprSeq());
	            
	            int result = dao.insertInstrEdulevel(eduLevel);
	            if (result == 0) {
	                return 0; 
	            }
	        }
	        
	        return 1; 
	    }
		return m;
	}

	@Override
	public int updateInstrProfile(InstrVo vo) {
		int m = dao.updateInstrProfile(vo);
		if (vo.getInstrEduVo() != null && !vo.getInstrEduVo().isEmpty()) {
	        List<InstrEduVo> eduLevels = vo.getInstrEduVo();
	        for (InstrEduVo eduLevel : eduLevels) {
	            eduLevel.setInedInprSeq(vo.getInprSeq());
	            
	            int result = dao.updateInstrEdulevel(eduLevel);
	            if (result == 0) {
	            	result = dao.insertInstrEdulevel(eduLevel);
	            }
	        }
	        
	        return 1;  // 성공적으로 업데이트되었을 경우 반환값 변경 가능
	    }
	    
	    return m;
	}
	
	@Override
	public int deleteInstrEdulevel(String inedSeq) {
		return dao.deleteInstrEdulevel(inedSeq);
	}

	@Override
	public List<InstrVo> getAllInstr(Map<String, Object> map) {
		return dao.getAllInstr(map);
	}
	
	@Override
	public int getAllInstrCount() {
		return dao.getAllInstrCount();
	}

	@Override
	public int updateInstrLike(Map<String, Object> map) {
		return dao.updateInstrLike(map);
	}

	@Override
	public int updateInstrView(Map<String, Object> map) {
		return dao.updateInstrView(map);
	}
	
	@Override
	public InstrVo getlikeViewUser(String inprAccountId) {
		return dao.getlikeViewUser(inprAccountId);
	}

	@Override
	public InstrVo getOneInstrSimple(Map<String, Object> map) {
		return dao.getOneInstrSimple(map);
	}

	@Override
	public InstrVo getOneInstrProfile(String userAccountId) {
		return dao.getOneInstrProfile(userAccountId);
	}

	@Override
	public List<InstrVo> getOneInstrClass(Map<String, Object> map) {
		return dao.getOneInstrClass(map);
	}
	
	@Override
	public int getCountClassCancel(String userAccountId) {
		return dao.getCountClassCancel(userAccountId);
	}

	@Override
	public List<ReviewVo> getOneIntrReview(Map<String, Object> map) {
		return dao.getOneIntrReview(map);
	}
	
	@Override
	public int classTotalCount(String userAccountId) {
		return dao.classTotalCount(userAccountId);
	}
	
	@Override
	public int reviewTotalCount(String userAccountId) {
		return dao.reviewTotalCount(userAccountId);
	}

}
