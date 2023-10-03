package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

/**
 * 예약 관련 기능
 * @author SoHyeon
 * @since 2023.09.09
 * @version 1.0
 */
public interface IYeyakDao {

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
	 * @param map gacoSido, gacoSigungu, start, end
	 * @return 강의실 목록(gacoId, gacoName, gacoJuso, gacoLat, gacoLon, gacoOpen, gacoClose)
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public List<GangeuisilVo> getGangeuisilList(Map<String, Object> map);

	/**
	 * 선택한 강의실의 상세 조회
	 * @param gagaGacoId 강의실 공통 id
	 * @return 강의실 상세 조회 리스트 Vo(gagaId, gacoId, gagaName, gagaMax, gagaHourPrice)
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public List<GangeuisilVo> getGangeuisilDetailList(String gagaGacoId);

	/**
	 * 사용자가 참여한 클래스 조회
	 * @param accountId 사용자 id
	 * @return 클래스 목록(clasId, clasTitle, clasHyeonjaeInwon)
	 * @author SoHyeon
	 * @since 2023.09.11
	 */
	public List<ClassVo> getchamyeoClassList(String accountId);
	
	/**
	 * 개별 강의실의 예약 가능한 날짜 및 시간 조회
	 * @param gagaId 강의실 개별 id
	 * @return 강의실vo(gagaId, gacoOpen, gacoClose, gagaYeoyuTime)
	 * @author SoHyeon
	 * @since 2023.09.10
	 */
	public GangeuisilVo getYeoyuTime(String gagaId);
	
	/**
	 * 결제 유저 업데이트를 위한 클래스 참여 유저(강사 제외)의 accountId 조회
	 * @param gayeClasId 클래스 id
	 * @return accountId 목록
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public List<String> getYeyakGyeoljeAcountIdList(int gayeClasId);
	
	/**
	 * 예약 정보 입력
	 * @param yVo 예약 정보(gayeGagaId,gayeAccountId,gayePhoneNumber,gayeYeyakDate,gayeStartTime,gayeHours,[gayeClasId],gayeGyeoljeType,gayeGyeoljeUser) 
	 * @return 성공 1, 실패 0, selectKey gayeId
	 * @author SoHyeon
	 * @since 2023.09.09
	 */
	public int insertYeakInfo(YeyakVo vo);

	/**
	 * 개별 강의실 여유 시간 업데이트
	 * @param map gagaYeoyuTime, gagaId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.10
	 */
	public int updateYeoyuTime(Map<String, Object> map);
	
	/**
	 * 예약시 결제 유저 추가
	 * @param vo(gyeoGeumaek,gyeoDaesangId,gyeoAccountId)
	 * @return 성공 1, 실패 0, selectKey gyeoId
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public int insertYeakGyeoljeInfo(GeoljeVo vo);
	
	/**
	 * 결제 정보 입력 후 해당 id 예약 테이블에 추가
	 * @param vo(gayeGyeoId,gayeId)
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public int updateGyeoId(YeyakVo vo);

	/**
	 * 예약 알림 전송을 위한 클래스 참여자(강사 포함) 조회
	 * @param gayeId
	 * @return accountId 목록
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public List<String> getAllChamyeoja(String gayeId);
	
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
	 * @param map gayeAccountId, start, end 
	 * @return 예약 정보 리스트 List
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
	 * 예약 취소시 상태 변경(Y 에서 N)
	 * @param gayeId
	 * @return 성공시 1, 실패시 0
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public int updateYeyakDelflag(String gayeId);
	
	/**
	 * 예약 취소시 결제 유저 id 조회
	 * @param gayeId
	 * @return gayeGyeoId(String)
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public String getGyeoId(String gayeId);
	
	/**
	 * 예약 취소시 결제 테이블 상태 변경
	 * @param gyeoId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.10.01
	 */
	public int updateYeakGyeoljeStatus(String gyeoId);
	
	/**
	 * 개별 강의실 id 조회(데일리 업데이트에 사용)
	 * @return gagaId
	 * @author SoHyeon
	 * @since 2023.09.10
	 */
	public List<String> getAllGaebyeol();
	
}
