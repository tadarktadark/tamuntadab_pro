package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.HwanbulVo;

public interface IPaymentDao {

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
	public int updatePayStatusInPayment(GyeoljeVo vo);
	
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
	public List<GyeoljeVo> myPageClassPaymentList(Map<String, Object> map);	
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
	public List<GyeoljeVo> myPageRoomPaymentList(Map<String, Object> map);
	
	/**
	 * 강의실 대여료 결제 내역 조회 (마이페이지)
	 * @param map
	 * @return
	 */
	public int myPageRoomPaymentListCount(String gyeoAccountId);
	
	
}
