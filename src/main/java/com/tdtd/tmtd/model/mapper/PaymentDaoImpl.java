package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.HwanbulVo;

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
	public int updatePayStatusInPayment(GyeoljeVo vo) {
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
	public List<GyeoljeVo> myPageClassPaymentList(Map<String, Object> map) {
		log.info("PaymentDaoImpl myPageClassPaymentList 실행");
		return sqlSession.selectList(NS+"myPageClassPaymentList",map);
	}

	@Override
	public int myPageClassPaymentListCount(String gyeoAccountId) {
		log.info("PaymentDaoImpl myPageClassPaymentListCount 실행");
		return sqlSession.selectOne(NS+"myPageClassPaymentListCount",gyeoAccountId);
	}

	@Override
	public List<GyeoljeVo> myPageRoomPaymentList(Map<String, Object> map) {
		log.info("PaymentDaoImpl myPageRoomPaymentList 실행");
		return sqlSession.selectList(NS+"myPageRoomPaymentList",map);
	}

	@Override
	public int myPageRoomPaymentListCount(String gyeoAccountId) {
		log.info("PaymentDaoImpl myPageRoomPaymentListCount 실행");
		return sqlSession.selectOne(NS+"myPageRoomPaymentListCount",gyeoAccountId);
	}

}
