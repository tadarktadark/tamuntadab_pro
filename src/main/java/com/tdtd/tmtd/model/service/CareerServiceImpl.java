package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ICareerDao;
import com.tdtd.tmtd.vo.CareerVo;

/**
 * ICarerDao 관련 service
 * @author 문희애
 *
 */
@Service
public class CareerServiceImpl implements ICareerService {
	
	@Autowired
	private ICareerDao dao;
	
	/**
	 * careId 생성시 오늘 기준 max id 조회
	 */
	@Override
	public String selectMaxIdToday(String datePrefix) {
		return dao.selectMaxIdToday(datePrefix);
	}

	/**
	 * (개인)경력 인증 내용 등록
	 */
	@Override
	public int insertCareer(Map<String, Object> map) {
		return dao.insertCareer(map);
	}
	
	/**
	 * (개인)경력 인증 요청 전체 개수 조회
	 */
	@Override
	public int getMyCareerCount(String userAccountId) {
		return dao.getMyCareerCount(userAccountId);
	}

	/**
	 * (개인)경력 인증 요청 리스트 조회
	 */
	@Override
	public List<CareerVo> getMyCareerList(Map<String, Object> map) {
		return dao.getMyCareerList(map);
	}
	
	/**
	 * (관리자)경력 요청 총 개수
	 */
	@Override
	public int getCareerCount() {
		return dao.getCareerCount();
	}

	/**
	 * (관리자)경력 요청 전체 리스트 조회
	 */
	@Override
	public List<CareerVo> getCareerList(Map<String, Object> map) {
		return dao.getCareerList(map);
	}

	/**
	 * (관리자)경력 내용 수정
	 */
	@Override
	public int updateCareer(CareerVo vo) {
		return dao.updateCareer(vo);
	}

	/**
	 * (관리자)경력 인증시 업데이트
	 */
	@Override
	public int updateCareerS(Map<String, Object> map) {
		int m = dao.updateCareerS(map);
		int n = dao.updateCareerCert(map);
		return (m>0||n>0)? 1:0;
	}

	/**
	 * (개인)기존 동일 회사명의 경력증명서 등록시 status 업데이트 
	 */
	@Override
	public int updateCareerR(Map<String, Object> map) {
		return dao.updateCareerR(map);
	}
	
	/**
	 * (관리자)반려시 status 및 반려사유 업데이트
	 */
	@Override
	public int updateCareerB(Map<String, Object> map) {
		return dao.updateCareerB(map);
	}

	/**
	 * (관리자)경력 승인 후 목록 삭제시 status 업데이트
	 */
	@Override
	public int updateCareerD(String careId) {
		return dao.updateCareerD(careId);
	}

	/**
	 * (관리자)경력 내용 DB 삭제
	 */
	@Override
	public int deleteCareer(String careId) {
		return dao.deleteCareer(careId);
	}
	
	/**
	 * (Cron)반려 후 10일 이내 재등록 안된 요청 자동 삭제
	 */
	@Override
	public int deleteCareerCron() {
		return dao.deleteCareerCron();
	}

	/**
	 * (강사 게시판) 강사의 경력 사항 조회
	 */
	@Override
	public List<CareerVo> getOneInstrCareer(String userAccountId) {
		return dao.getOneInstrCareer(userAccountId);
	}

}
