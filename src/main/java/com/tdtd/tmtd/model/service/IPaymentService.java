package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.HwanbulVo;
import com.tdtd.tmtd.vo.UserProfileVo;

public interface IPaymentService {

	
	/**
	 * 결제 생성
	 * @param vo
	 * @return
	 */
	public int newPayment(GyeoljeVo vo);
	
	/**
	 * 결제 상태 변경 (참여자 테이블)
	 * @param map clchGyeoljeStatus, clchClasId, clchAccountId
	 * @return
	 */
	public int updatePayStatusInChamyeo(Map<String, Object> map);

	/**
	 * 결제 상태 변경 (결제 테이블)
	 * @param GyeoljeVo
	 * @return
	 */
	public int updatePayStatusInPayment(GeoljeVo vo);
	
	/**
	 * 환불 테이블 insert
	 * @param vo
	 * @return
	 */
	public int insertHwanbul(HwanbulVo vo);
	
	/**
	 * 단일 환불 내역 조회
	 * @param gyeoAccountId
	 * @return
	 */
	public HwanbulVo getUserHwanbul(String gyeoAccountId);
	
	/**
	 * 수강료 결제 내역 조회 (마이페이지)
	 * @param map
	 * @return
	 */
	public List<GeoljeVo> myPageClassPaymentList(Map<String, Object> map);	
	/**
	 * 위 메소드 결과의 ROW 갯수 반환
	 * @param gyeoAccountId
	 * @return
	 */
	public int myPageClassPaymentListCount(String gyeoAccountId);
	
	/**
	 * 강의실 대여료 결제 내역 조회 (마이페이지)
	 * @param map
	 * @return
	 */
	public List<GeoljeVo> myPageRoomPaymentList(Map<String, Object> map);
	
	/**
	 * 강의실 대여료 결제 내역 조회 (마이페이지)
	 * @param map
	 * @return
	 */
	public int myPageRoomPaymentListCount(String gyeoAccountId);
	
	/**
	 * 결제 테이블 조회
	 * @param map
	 * @return
	 */
	public GeoljeVo getGyeoInfo(Map<String, Object> map);
	
	/**
	 * 결제자 정보 조회
	 * @param userAccountId
	 * @return
	 */
	public UserProfileVo getGyeoljejaInfo(String userAccountId);
	
	/**
	 * 강의실 결제를 위한 결제정보 조회
	 * @param map
	 * @return
	 */
	public GeoljeVo getGangGyeoInfo(Map<String, Object> map);

	/**
	 * 결제 상태 변경 (예약 테이블)
	 * @param map
	 * @return
	 */
	public int updateYeyakStatusInPayment(Map<String, Object> map);
	
	/**
	 * <!-- 참여자 결제상태 변경 (클래스 전체) -->
	 * @param clasId
	 * @return
	 */
	public int updatePayStatusInChamyeoinClass(String clasId);
	
	/**
	 * <!-- 결제테이블 결제상태 변경 (클래스 전체) -->
	 * @param clasId
	 * @return
	 */
	public int updatePayStatusInGyeoljeinClass(String clasId);
}
