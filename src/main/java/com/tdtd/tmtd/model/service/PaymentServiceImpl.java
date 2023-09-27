package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tdtd.tmtd.model.mapper.IPaymentDao;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.HwanbulVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private IPaymentDao dao;
	
	@Override
	public int newPayment(GyeoljeVo vo) {
		log.info("PaymentServiceImpl newPayment 실행");
		return dao.newPayment(vo);
	}

	@Override
	public int updatePayStatusInChamyeo(Map<String, Object> map) {
		log.info("PaymentServiceImpl updatePayStatusInChamyeo 실행");
		return dao.updatePayStatusInChamyeo(map);
	}

	@Override
	public int updatePayStatusInPayment(GyeoljeVo vo) {
		log.info("PaymentServiceImpl updatePayStatusInPayment 실행");
		return dao.updatePayStatusInPayment(vo);
	}

	@Override
	public int insertHwanbul(HwanbulVo vo) {
		log.info("PaymentServiceImpl insertHwanbul 실행");
		return dao.insertHwanbul(vo);
	}

	@Override
	public HwanbulVo getUserHwanbul(String gyeoAccountId) {
		log.info("PaymentServiceImpl getUserHwanbul 실행");
		return dao.getUserHwanbul(gyeoAccountId);
	}

	@Override
	public List<GyeoljeVo> myPageClassPaymentList(Map<String, Object> map) {
		log.info("PaymentServiceImpl myPageClassPaymentList 실행");
		return dao.myPageClassPaymentList(map);
	}

	@Override
	public int myPageClassPaymentListCount(String gyeoAccountId) {
		log.info("PaymentServiceImpl myPageClassPaymentListCount 실행");
		return dao.myPageClassPaymentListCount(gyeoAccountId);
	}

	@Override
	public List<GyeoljeVo> myPageRoomPaymentList(Map<String, Object> map) {
		log.info("PaymentServiceImpl myPageRoomPaymentList 실행");
		return dao.myPageRoomPaymentList(map);
	}

	@Override
	public int myPageRoomPaymentListCount(String gyeoAccountId) {
		log.info("PaymentServiceImpl myPageRoomPaymentListCount 실행");
		return dao.myPageRoomPaymentListCount(gyeoAccountId);
	}

}
