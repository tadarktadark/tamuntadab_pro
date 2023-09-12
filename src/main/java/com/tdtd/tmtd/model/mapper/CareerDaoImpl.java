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
	public String selectMaxIdToday(String datePrefix) {
		return sqlSession.selectOne(NS+"selectMaxIdToday",datePrefix);
	}
	
	@Override
	public int insertCareer(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertCareer", map);
	}
	
	@Override
	public int getMyCareerCount(String userAccountId) {
		return sqlSession.selectOne(NS+"getMyCareerCount", userAccountId);
	}

	@Override
	public List<CareerVo> getMyCareerList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getMyCareerList", map);
	}

	@Override
	public List<CareerVo> getCareerList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getCareerList", map);
	}

	@Override
	public int updateCareer(CareerVo vo) {
		return sqlSession.update(NS+"updateCareer", vo);
	}

	@Override
	public int updateCareerS(Map<String, Object> map) {
		return sqlSession.update(NS+"updateCareerS",map);
	}

	@Override
	public int updateCareerCert(Map<String, Object> map) {
		return sqlSession.update(NS+"updateCareerCert", map);
	}

	@Override
	public int updateCareerB(Map<String, Object> map) {
		return sqlSession.update(NS+"updateCareerB", map);
	}

	@Override
	public int updateCareerD(String careId) {
		return sqlSession.update(NS+"updateCareerD", careId);
	}

	@Override
	public int deleteCareer(String careId) {
		return sqlSession.delete(NS+"deleteCareer" ,careId);
	}

	@Override
	public int deleteCareerCron() {
		return sqlSession.delete(NS+"deleteCareerCron");
	}

	@Override
	public CareerVo getOneInstrCareer(String userAccountId) {
		return sqlSession.selectOne(NS+"getOneInstrCareer", userAccountId);
	}

}
