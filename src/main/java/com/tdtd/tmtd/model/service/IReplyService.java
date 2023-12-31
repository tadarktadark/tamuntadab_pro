package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ReplyVo;

public interface IReplyService {

	/**
	 * 게시글의 총 댓글 수 조회
	 * @param boardId 게시글id
	 * @return 총 댓글 수
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int getReplyCount(String boardId);
		
	/**
	 * 게시글의 ROOT 댓글 목록 조회
	 * @param map boardId 게시글id<br>
	 * 				start<br>
	 * 				end
	 * @return List seq, writerId, content, rootSeq, step, chaetaek, regdate, update, state
	 * @author SoHyeon
	 * @since 2023.09.23
	 */
	public List<ReplyVo> getRootReplyList(Map<String, Object> map);
	
	/**
	 * 게시글의 대댓글 목록 조회
	 * @param map boardId 게시글id<br>
	 * 				rootSeq String[] 조회할 root 댓글의 seq
	 * @return List seq, writerId, content, rootSeq, step, chaetaek, regdate, update, state
	 * @author SoHyeon
	 * @since 2023.09.23
	 */
	public List<ReplyVo> getReReplyList(Map<String, Object> map);
		
	/**
	 * ROOT 댓글 작성 및 개수 수정
	 * @param vo boardId, writerId, content
	 * @param map board, boardId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.23
	 */
	public int insertRootReply(ReplyVo vo, Map<String, Object> map);
	
	/**
	 * 대댓글 작성
	 * @param vo boardId, writerId, content, rootSeq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.23
	 */
	public int insertReReply(ReplyVo vo);
	
	/**
	 * 수정 댓글 데이터 조회
	 * @param seq 수정 댓글 seq
	 * @return 수정 댓글 content
	 * @author SoHyeon
	 * @since 2023.09.24
	 */
	public String getUpdateContent(String seq);
	
	/**
	 * 댓글 수정
	 * @param vo content, seq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateReply(ReplyVo vo);
	
	/**
	 * 댓글 삭제 : reply tt 삭제<br>
	 * 			삭제된 댓글 정보 조회<br>
	 * 			sakge reply tt 입력
	 * @param map seq, board, boardId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int deleteReply(Map<String, Object> map);
					
	/**
	 * 댓글 채택 및 채택 보드 업데이트
	 * @param seq
	 * @param boardId
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	public int updateChaetaek(int seq, String boardId);
	
	/**
	 * 내가 작성한 필기 개수 조회
	 * @param accountId 로그인 계정
	 * @return 필기 개수
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public int getWritePilgiCount(String accountId);
	
	/**
	 * 내가 작성한 질문 개수 조회
	 * @param accountId 로그인 계정
	 * @return 질문 개수
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public int getWriteJilmunCount(String accountId);
		
	/**
	 * 내가 작성한 자유 개수 조회
	 * @param accountId 로그인 계정
	 * @return 자유 개수
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public int getWriteJayuCount(String accountId);
	
	/**
	 * 내가 작성한 댓글 개수 조회
	 * @param accountId 로그인 계정
	 * @return 댓글 개수
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public int getWriteReplyCount(String accountId);
	
	/**
	 * 좋아요한 글 개수 조회
	 * @param accountId 로그인 계정
	 * @return 좋아요한 글 개수
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public int getLikeCommCount(String accountId);
	
	/**
	 * 내가 작성한 필기 목록 조회
	 * @param map accountId 로그인 계정, start, end
	 * @return 필기 목록
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public List<BoardVo> getWritePilgiList(Map<String, Object> map);
	
	/**
	 * 내가 작성한 질문 목록 조회
	 * @param map accountId 로그인 계정, start, end
	 * @return 질문 목록
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public List<BoardVo> getWriteJilmunList(Map<String, Object> map);
		
	/**
	 * 내가 작성한 자유 목록 조회
	 * @param map accountId 로그인 계정, start, end
	 * @return 자유 목록
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public List<BoardVo> getWriteJayuList(Map<String, Object> map);
	
	/**
	 * 내가 작성한 댓글 목록 조회
	 * @param map accountId 로그인 계정, start, end
	 * @return 댓글 목록
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public List<ReplyVo> getWriteReplyList(Map<String, Object> map);
	
	/**
	 * 좋아요한 글 목록 조회
	 * @param map accountId 로그인 계정, start, end
	 * @return 좋아요 목록
	 * @author SoHyeon
	 * @since 2023.09.25
	 */
	public List<BoardVo> getLikeCommList(Map<String, Object> map);
	
	/**
	 * 알람 전송을 위한 게시판 정보 조회
	 * @param id 보드id
	 * @return id, title, accountId
	 * @author SoHyeon
	 * @since 2023.10.03
	 */
	public BoardVo getBoardAlramInfo(String id);
	
	/**
	 * 알람 전송을 위한 댓글 정보 조회
	 * @param seq 댓글seq
	 * @return boardId, content, writerId
	 * @author SoHyeon
	 * @since 2023.10.03
	 */
	public ReplyVo getReplyAlramInfo(String seq);
}
