package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IInstrDao;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;

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
	        
	        return 1;  // 성공적으로 저장되었을 경우 반환값 변경 가능
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
	                return 0; 
	            }
	        }
	        
	        return 1;  // 성공적으로 업데이트되었을 경우 반환값 변경 가능
	    }
	    
	    return m;
	}

	@Override
	public List<InstrVo> getAllInstr(String order) {
		return dao.getAllInstr(order);
	}

	@Override
	public int updateInstrLike(InstrVo vo) {
		return dao.updateInstrLike(vo);
	}

	@Override
	public int updateInstrView(InstrVo vo) {
		return dao.updateInstrView(vo);
	}

	@Override
	public InstrVo getOneInstrSimple(String userAccountId) {
		return dao.getOneInstrSimple(userAccountId);
	}

	@Override
	public InstrVo getOneInstrProfile(String userAccountId) {
		return dao.getOneInstrProfile(userAccountId);
	}

	@Override
	public List<InstrVo> getOneInstrClass(String userAccountId) {
		return dao.getOneInstrClass(userAccountId);
	}

	@Override
	public List<InstrVo> getOneIntrReview(Map<String, Object> map) {
		return dao.getOneIntrReview(map);
	}

}
