package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ReplyVo;

public interface IReplyDao {

	/**
	 * 게시글의 총 댓글 수 조회
	 * @param boardId 게시글id
	 * @return 총 댓글 수
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int getReplyCount(String boardId);
		
	/**
	 * 게시글의 댓글 목록 조회
	 * @param map boardId 게시글id<br>
	 * 				start<br>
	 * 				end
	 * @return List seq, writerId, content, rootSeq, step, depth, chaetaek, regdate, update, state
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public List<ReplyVo> getReplyList(Map<String, Object> map);
		
	/**
	 * 댓글 작성 : 댓글 STEP 업데이트
	 * @param rootSeq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateReplyStep(int rootSeq);
	
	/**
	 * 댓글 작성 : 삭제 댓글 STEP 업데이트
	 * @param rootSeq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateSakjeStep(int rootSeq);
	
	/**
	 * 댓글 작성
	 * @param vo boardId, writerId, content, rootSeq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int insertReply(ReplyVo vo);
	
	/**
	 * 댓글 수정
	 * @param vo content, seq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateReply(ReplyVo vo);
	
	/**
	 * 댓글 삭제
	 * @param seq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int deleteReply(int seq);
	
	/**
	 * 삭제 댓글 정보 조회
	 * @param seq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public ReplyVo getReplyDetail(int seq);
	
	/**
	 * 삭제 댓글 입력
	 * @param vo seq, boardId, rootSeq, step, depth
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int insertSakje(ReplyVo vo);
				
	/**
	 * 댓글 채택
	 * @param seq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateChaetaekY(int seq);
	
	/**
	 * 채택 보드 업데이트
	 * @param boardId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateChaetaekBoard(String boardId);
}
