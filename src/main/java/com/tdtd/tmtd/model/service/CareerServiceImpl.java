package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ICareerDao;
import com.tdtd.tmtd.vo.CareerVo;


@Service
public class CareerServiceImpl implements ICareerService {
	
	@Autowired
	private ICareerDao dao;
	
	
	@Override
	public String selectMaxIdToday(String datePrefix) {
		return dao.selectMaxIdToday(datePrefix);
	}

	
	@Override
	public int insertCareer(Map<String, Object> map) {
		return dao.insertCareer(map);
	}
	
	
	@Override
	public int getMyCareerCount(String userAccountId) {
		return dao.getMyCareerCount(userAccountId);
	}

	
	@Override
	public List<CareerVo> getMyCareerList(Map<String, Object> map) {
		return dao.getMyCareerList(map);
	}
	
	
	@Override
	public int getCareerCount() {
		return dao.getCareerCount();
	}

	
	@Override
	public List<CareerVo> getCareerList(Map<String, Object> map) {
		return dao.getCareerList(map);
	}

	
	@Override
	public int updateCareer(CareerVo vo) {
		return dao.updateCareer(vo);
	}

	
	@Override
	public int updateCareerS(Map<String, Object> map) {
		int m = dao.updateCareerS(map);
		int n = dao.updateCareerCert(map);
		return (m>0||n>0)? 1:0;
	}

	
	@Override
	public int updateCareerR(Map<String, Object> map) {
		return dao.updateCareerR(map);
	}
	
	
	@Override
	public int updateCareerB(Map<String, Object> map) {
		return dao.updateCareerB(map);
	}

	
	@Override
	public int updateCareerD(String careId) {
		return dao.updateCareerD(careId);
	}

	
	@Override
	public int deleteCareer(String careId) {
		return dao.deleteCareer(careId);
	}
	
	
	@Override
	public int deleteCareerCron() {
		return dao.deleteCareerCron();
	}

	
	@Override
	public List<CareerVo> getOneInstrCareer(String userAccountId) {
		return dao.getOneInstrCareer(userAccountId);
	}

}
