package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.SingoDaesangVo;
import com.tdtd.tmtd.vo.SingoSayuVo;

public interface ISingoService {

	/**
	 * 신고 카테코리 조회 
	 * @return ID(categoryId), CATEGORY
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public List<SingoSayuVo> getSingoCategory();
	
	/**
	 * 유저 신고 : state가 n인 신고 대상 조회<br>
	 * 				SIDA_ID가 없을 경우 : 신고 대상에 추가<br>
	 * 				SIDA_ID가 있는 경우 : 신고 대상 count 업데이트<br>
	 * 				만약 원래 count==4 였다면 state='P' 로 변경<br>
	 * 									대상(보드) 상태 변경<br>
	 * 				신고사유 입력 - SCAT10일 경우 CONTENT도 삽입
	 * @param daesangId 대상id
	 * @param board board
	 * @param sVo category(category id), content
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int userSingo(String daesangId, String board, SingoSayuVo sVo);
	
	/**
	 * 5회 이상 신고된 게시글 개수 조회
	 * @return 5회 이상 신고된 게시글 개수
	 * @author SoHyeon
	 * @since 2023.09.26
	 */
	public int getMaxCount();
	
	/**
	 * 5회 신고된 게시글 조회
	 * @return id(sidaId), daesangId, daesangContent, accountId,<br>
	 * 			sayuList(sayuAccountId, category, content, regdate)
	 * @param map start, end
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public List<SingoDaesangVo> getMaxSingo(Map<String, Object> map);
	
	/**
	 * 관리자 처리 : 5회 신고된 게시글 처리<br>
	 * 				신고된 게시글 처리 후 신고 유저 조회<br>
	 * 				없으면 신고 유저 추가<br>
	 * 				있으면 신고 유저 count 업데이트<br>
	 * 				원래 count==4 이면 state='Y' 변경
	 * @param vo(id, state, accountId)
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int adminAction(SingoDaesangVo vo);
	
}
