package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.HwanbulVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class PaymentDaoImpl implements IPaymentDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "com.tdtd.tmtd.model.mapper.PaymentDaoImpl.";
	
	@Override
	public int newPayment(GyeoljeVo vo) {
		log.info("PaymentDaoImpl newPayment 실행");
		return sqlSession.insert(NS+"newPayment",vo);
	}

	@Override
	public int updatePayStatusInChamyeo(Map<String, Object> map) {
		log.info("PaymentDaoImpl updatePayStatusInChamyeo 실행");
		return sqlSession.update(NS+"updatePayStatusInChamyeo",map);
	}

	@Override
	public int updatePayStatusInPayment(GeoljeVo vo) {
		log.info("PaymentDaoImpl updatePayStatusInPayment 실행");
		return sqlSession.update(NS+"updatePayStatusInPayment",vo);
	}

	@Override
	public int insertHwanbul(HwanbulVo vo) {
		log.info("PaymentDaoImpl insertHwanbul 실행");
		return sqlSession.insert(NS+"insertHwanbul",vo);
	}

	@Override
	public HwanbulVo getUserHwanbul(String gyeoAccountId) {
		log.info("PaymentDaoImpl getUserHwanbul 실행");
		return sqlSession.selectOne(NS+"getUserHwanbul", gyeoAccountId);
	}

	@Override
	public List<GeoljeVo> myPageClassPaymentList(Map<String, Object> map) {
		log.info("PaymentDaoImpl myPageClassPaymentList 실행");
		return sqlSession.selectList(NS+"myPageClassPaymentList",map);
	}

	@Override
	public int myPageClassPaymentListCount(String gyeoAccountId) {
		log.info("PaymentDaoImpl myPageClassPaymentListCount 실행");
		return sqlSession.selectOne(NS+"myPageClassPaymentListCount",gyeoAccountId);
	}

	@Override
	public List<GeoljeVo> myPageRoomPaymentList(Map<String, Object> map) {
		log.info("PaymentDaoImpl myPageRoomPaymentList 실행");
		return sqlSession.selectList(NS+"myPageRoomPaymentList",map);
	}

	@Override
	public int myPageRoomPaymentListCount(String gyeoAccountId) {
		log.info("PaymentDaoImpl myPageRoomPaymentListCount 실행");
		return sqlSession.selectOne(NS+"myPageRoomPaymentListCount",gyeoAccountId);
	}

	@Override
	public GeoljeVo getGyeoInfo(Map<String, Object> map) {
		log.info("PaymentDaoImpl getGyeoInfo 실행");
		return sqlSession.selectOne(NS+"getGyeoInfo",map);
	}

	@Override
	public UserProfileVo getGyeoljejaInfo(String userAccountId) {
		log.info("PaymentDaoImpl getGyeoljejaInfo 실행");
		return sqlSession.selectOne(NS+"getGyeoljejaInfo",userAccountId);
	}

	@Override
	public GeoljeVo getGangGyeoInfo(Map<String, Object> map) {
		log.info("PaymentDaoImpl getGangGyeoInfo 실행");
		return sqlSession.selectOne(NS+"getGangGyeoInfo",map);
	}

	@Override
	public int updateYeyakStatusInPayment(Map<String, Object> map) {
		log.info("PaymentDaoImpl updateYeyakStatusInPayment 실행");
		return sqlSession.update(NS+"updateYeyakStatusInPayment",map);
	}

	@Override
	public int updatePayStatusInChamyeoinClass(String clasId) {
		log.info("ClassDaoImpl updatePayStatusInChamyeoinClass 실행");
		return sqlSession.update(NS+"updatePayStatusInChamyeoinClass",clasId);
	}

	@Override
	public int updatePayStatusInGyeoljeinClass(String clasId) {
		log.info("ClassDaoImpl updatePayStatusInGyeoljeinClass 실행");
		return sqlSession.update(NS+"updatePayStatusInGyeoljeinClass",clasId);
	}
}
