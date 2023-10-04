package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.CareerVo;
/**
 * ICarerDao 관련 service
 * @author 문희애
 *
 */
public interface ICareerService {

		/**
		 * careId 생성시 오늘 기준 max id 조회
		 */
		public String selectMaxIdToday(String datePrefix);
		
		/**
		 * (개인)경력 인증 내용 등록
		 */
		public int insertCareer(Map<String, Object> map);
		
		/**
		 * (개인)경력 인증 요청 전체 개수 조회
		 */
		public int getMyCareerCount(String userAccountId);
		
		/**
		 * (개인)경력 인증 요청 리스트 조회
		 */
		public List<CareerVo> getMyCareerList(Map<String, Object> map);
		
		/**
		 * (관리자)경력 요청 총 개수
		 */
		public int getCareerCount();
		
		/**
		 * (관리자)경력 요청 전체 리스트 조회
		 */
		public List<CareerVo> getCareerList(Map<String, Object> map);
		
		/**
		 * (관리자)경력 내용 수정
		 */
		public int updateCareer(CareerVo vo);
		
		/**
		 * (관리자)경력 인증시 업데이트
		 */
		public int updateCareerS(Map<String, Object> map);
		
		/**
		 * (개인)기존 동일 회사명의 경력증명서 등록시 status 업데이트 
		 */
		public int updateCareerR(Map<String, Object> map);
		
		/**
		 * (관리자)반려시 status 및 반려사유 업데이트
		 */
		public int updateCareerB(Map<String, Object> map);
		
		/**
		 * (관리자)경력 승인 후 목록 삭제시 status 업데이트
		 */
		public int updateCareerD(String careId);
		
		/**
		 * (관리자)경력 내용 DB 삭제
		 */
		public int deleteCareer(String careId);
		
		/**
		 * (Cron)반려 후 10일 이내 재등록 안된 요청 자동 삭제
		 */
		public int deleteCareerCron();
		
		/**
		 * (강사 게시판) 강사의 경력 사항 조회
		 */
		public List<CareerVo> getOneInstrCareer(String userAccountId);
	
}
