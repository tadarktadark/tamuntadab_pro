package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

/**
 * 예약 관련 서비스
 * @author SoHyeon
 * @since 2023.09.09
 * @version 1.0
 */
public interface IYeyakService {
	
	/**
	 * 강의실 개수 조회
	 * @param map key = ["gacoSido", "gacoSigungu"] value=["시도","시군구"]
	 * @return 강의실 개수 int
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public int getGangeuisilCount(Map<String, Object> map);
	
	/**
	 * 강의실이 있는 시도 조회
	 * @return 시/도(갯수)
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public List<String> getGangeuisilSidoList();

	/**
	 * 선택한 시도에서 강의실이 있는 시군구 조회
	 * @param gacoSido 시도
	 * @return 시/군/구(갯수)
	 * @author SoHyeon 
	 * @since 2023.09.09
	 */
	public List<String> getGangeuisilSigunguList(String gacoSido);

	/**
	 * 전체/시도/시군구에 있는 강의실 목록 조회
	 * @param map key = ["gacoSido", "gacoSigungu"] value=["시도","시군구"]
	 * @return 강의실 목록
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public List<GangeuisilVo> getGangeuisilList(Map<String, Object> map);

	/**
	 * 선택한 강의실의 상세 조회
	 * @param gagaGacoId 강의실 공통 id
	 * @return 강의실 상세 조회 리스트
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public List<GangeuisilVo> getGangeuisilDetailList(String gagaGacoId);
	
	/**
	 * 사용자가 참여한 클래스 조회
	 * @param accountId 사용자 id
	 * @return 클래스 목록
	 * @author SoHyeon
	 * @since 2023.09.11
	 */
	public List<ClassVo> getchamyeoClassList(String accountId);
	
	/**
	 * 개별 강의실의 예약 가능한 날짜 및 시간 조회
	 * @param gagaId 강의실 개별 id
	 * @return 강의실 오픈, 마감 시간, 여유 시간
	 * @author SoHyeon
	 * @since 2023.09.10
	 */
	public GangeuisilVo getYeoyuTime(String gagaId);

	/**
	 * 클래스 참여 유저 조회, 예약 정보 입력, 개별 강의실 정보 수정 및 결제 정보 입력
	 * @param yVo 예약 정보(gayeGyeoljeUser, gayeClasId, gayeGyeoljeType, gayeGagaId, gayeHours, gayeStartTime, gayeYeyakDate)
	 * @param gVos 결제 정보(전체 금액) (gyeoGeumaek)
	 * @return 성공시 전체금액에서 모자란 금액(카카오 정산하기), 실패시 -1
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public int insertYeakInfo(YeyakVo yVo, GeoljeVo gVos);
	
	/**
	 * 마이페이지 예약 정보 조회 페이징을 위한 전체 일정 개수 조회
	 * @param gayeAccountId
	 * @return 전체 일정 개수
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public int getMyYeyakCount(String gayeAccountId);
	
	/**
	 * 마이페이지 예약 정보 조회 
	 * @param gayeAccountId 회원 아이디 
	 * @return 예약 정보 리스트 List<YeyakVo>
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public List<YeyakVo> getMyYeyakList(Map<String, Object> map);
	
	/**
	 * 특정 예약 일정 결제 유저들의 결제 상태 조회
	 * @param gayeId
	 * @return vo(gyeoGeumaek, gyeoAccountId, gyeoId(UserNickname), gyeoStatus, gayeDaesangId(강의실 이름)
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public List<GeoljeVo> getGyeojeStatus(String gayeId);

	/**
	 * 예약 취소 및 결제 테이블 상태 변경
	 * @param gayeId 예약 id
	 * @return 성공시 1, 실패시 0
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public int yeyakCancel(YeyakVo vo);
	
	/**
	 * 매일 자정 강의실 예약 가능일 추가
	 * @param gayaId 개별 강의실 id
	 * @author SoHyeon
	 * @since 2023.09.10
	 */
	public void updateYeoyuTimeDaily();
		
}
