package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.SingoDaesangVo;
import com.tdtd.tmtd.vo.SingoSayuVo;

public interface ISingoDao {
	
	/**
	 * 신고 카테코리 조회 
	 * @return ID(categoryId), CATEGORY
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public List<SingoSayuVo> getSingoCategory();
	
	/**
	 * 신고 대상 입력 : state가 n인 신고 대상 조회
	 * @param daesangId 대상id
	 * @return id(sidaId), count
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public SingoDaesangVo getSingoDaesangId(String daesangId);
		
	/**
	 * SIDA_ID가 없을 경우 : 신고 대상에 추가
	 * @param vo(daesangId)
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int insertSingoDaesang(SingoDaesangVo vo);
	
	/**
	 * SIDA_ID가 있는 경우 : 신고 대상 count 업데이트<br>
	 * 					만약 원래 count==4 였다면 state='P' 로 변경<br>
	 * @param vo id sidaId<br>
	 * 				count 원래 count
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateSingo(SingoDaesangVo vo);
	
	/**
	 * SIDA_COUNT가 4였다면?(신고해서 5가 된다면?) : 대상(보드) 상태 변경
	 * @param map board=[pilgi|jilmun|jayu|reply]<br>
	 * 				daesangId 대상id<br>
	 * 				state 'P'(처리대기), 'D' (삭제), 'N' (미대상, 반려)
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateBoardState(Map<String, Object> map);
		
	/**
	 * 신고사유 입력 - SCAT10일 경우 CONTENT도 삽입
	 * @param vo sayuAccountId, id(sidaId), category(category id), content
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int insertSingoSayu(SingoSayuVo vo);
	
	/**
	 * 5회 신고된 게시글 조회
	 * @return id(sidaId), daesangId, daesangContent, accountId,<br>
	 * 			sayuList(sayuAccountId, category, content, regdate)
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public List<SingoDaesangVo> getMaxSingo();
	
	/**
	 * 5회 신고된 게시글 처리
	 * @param vo state 'N'=5회 미만 신고(처리 대상 아님), 'P'=처리 대기, 'B'=반려, 'D'=수리(대상 삭제)<br>
	 * 				id(sidaId)
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateSingoDaesangState(SingoDaesangVo vo);
	
	/** 
	 * 신고된 게시글 처리 후 신고 유저 조회
	 * @param accountId
	 * @return id(siusSeq), accountId, count, state
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public SingoDaesangVo getSingoUser(String accountId);
	
	/**
	 * 없으면 신고 유저 추가
	 * @param accountId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int insertSingoUser(String accountId);
	
	/**
	 * 있으면 신고 유저 count 업데이트<br>
	 * 원래 count==4 이면 state='Y' 변경
	 * @param id siusSeq
	 * @return 성공 1, 실패 0
	 */
	public int updateSingoUserCount(String id);
}
