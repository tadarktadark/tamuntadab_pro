package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tdtd.tmtd.model.mapper.IPaymentDao;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.HwanbulVo;
import com.tdtd.tmtd.vo.UserProfileVo;

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
	public int updatePayStatusInPayment(GeoljeVo vo) {
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
	public List<GeoljeVo> myPageClassPaymentList(Map<String, Object> map) {
		log.info("PaymentServiceImpl myPageClassPaymentList 실행");
		return dao.myPageClassPaymentList(map);
	}

	@Override
	public int myPageClassPaymentListCount(String gyeoAccountId) {
		log.info("PaymentServiceImpl myPageClassPaymentListCount 실행");
		return dao.myPageClassPaymentListCount(gyeoAccountId);
	}

	@Override
	public List<GeoljeVo> myPageRoomPaymentList(Map<String, Object> map) {
		log.info("PaymentServiceImpl myPageRoomPaymentList 실행");
		return dao.myPageRoomPaymentList(map);
	}

	@Override
	public int myPageRoomPaymentListCount(String gyeoAccountId) {
		log.info("PaymentServiceImpl myPageRoomPaymentListCount 실행");
		return dao.myPageRoomPaymentListCount(gyeoAccountId);
	}

	@Override
	public GeoljeVo getGyeoInfo(Map<String, Object> map) {
		log.info("PaymentServiceImpl getGyeoInfo 실행");
		return dao.getGyeoInfo(map);
	}

	@Override
	public UserProfileVo getGyeoljejaInfo(String userAccountId) {
		log.info("PaymentServiceImpl getGyeoljejaInfo 실행");
		return dao.getGyeoljejaInfo(userAccountId);
	}

	@Override
	public GeoljeVo getGangGyeoInfo(Map<String, Object> map) {
		log.info("PaymentServiceImpl getGangGyeoInfo 실행");
		return dao.getGangGyeoInfo(map);
	}

	@Override
	public int updateYeyakStatusInPayment(Map<String, Object> map) {
		log.info("PaymentServiceImpl updateYeyakStatusInPayment 실행");
		return dao.updateYeyakStatusInPayment(map);
	}

	@Override
	public int updatePayStatusInChamyeoinClass(String clasId) {
		log.info("ClassServiceImpl 실행 - updatePayStatusInChamyeoinClass");
		return dao.updatePayStatusInChamyeoinClass(clasId);
	}

	@Override
	public int updatePayStatusInGyeoljeinClass(String clasId) {
		log.info("ClassServiceImpl 실행 - updatePayStatusInGyeoljeinClass");
		return dao.updatePayStatusInGyeoljeinClass(clasId);
	}
}
