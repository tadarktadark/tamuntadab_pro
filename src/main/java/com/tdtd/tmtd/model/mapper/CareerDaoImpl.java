package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.CareerVo;

@Repository
public class CareerDaoImpl implements ICareerDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.CareerDaoImpl.";
	
	@Override
	public int insertCareer(Map<String, Object> map) {
		return 0;
	}

	@Override
	public List<CareerVo> getMyCareerList(Map<String, Object> map) {
		return null;
	}

	@Override
	public List<CareerVo> getCareerList(Map<String, Object> map) {
		return null;
	}

	@Override
	public int updateCareer(CareerVo vo) {
		return 0;
	}

	@Override
	public int updateCareerS(String careId) {
		return 0;
	}

	@Override
	public int updateCareerCert(String userAccountId) {
		return 0;
	}

	@Override
	public int updateCareerB(Map<String, Object> map) {
		return 0;
	}

	@Override
	public int updateCareerD(String careId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCareer(String careId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCareerCron() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CareerVo getOneInstrCareer(String userAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

}
