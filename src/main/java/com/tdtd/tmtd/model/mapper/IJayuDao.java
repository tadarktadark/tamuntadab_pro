package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

/**
 * 자유 관련 기능
 * @author SoHyeon
 * @since 2023.09.16
 * @version 1.0
 */
public interface IJayuDao {

	/**
	 * 자유 조회 가능한 게시글 개수	
	 * @return 조회 가능한 게시글 개수
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int getJayuCount();      
	
	/**
	 * 자유 목록 조회(정렬)
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				orderBy={"like","view","reply"} defalut="date" 정렬, <br> 
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 자유 목록(게시글ID, 작성자, 제목, 댓글수, 좋아요 여부, 좋아요 개수, 조회수, 등록일)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public List<BoardVo> getJayuList(Map<String, Object> map);
	
	/**
	 * 자유 상세 조회
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				id 자유 게시글ID
	 * @return 자유 상세(게시글ID, 작성자, 제목 내용, 댓글수, 과목, 좋아요 여부, 좋아요 개수, 조회수, 등록일, 수정일, 상태)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public BoardVo getJayuDetail(Map<String, Object> map);
		
	/**
	 * 자유 좋아요 유저 조회
	 * @param id 게시글ID
	 * @return 좋아요한 유저 String JSON {"accountId":"yyyymmdd", ...}
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public String getJayuLikeUser(String id);
	
	/**
	 * 자유 좋아요 업데이트                   
	 * @param map likeUser 좋아요한 유저 String JSON {"accountId":"yyyymmdd", ...} <br>
	 * 				id 자유 게시글ID <br>
	 * 				likeCount 좋아요 유저수
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int updateJayuLikeUser(Map<String, Object> map);
	
	/**
	 * 자유 조회한 유저 조회                      
	 * @param id 게시글ID
	 * @return 조회한 유저 String JSON {"accountId":"yyyymmdd", ...}
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public String getJayuViewUser(String id);
	
	/**
	 * 자유 조회한 유저 업데이트                   
	 * @param map viewUser 조회한 유저 String JSON {"accountId":"yyyymmdd", ...} <br>
	 * 				id 자유 게시글ID <br>
	 * 				viewCount 조회수
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int updateJayuViewUser(Map<String, Object> map);
	
	/**(마이페이지)내가 쓴 자유 개수           
	 * 
	 * @param accountId	현재 로그인한 계정
	 * @return 내가 쓴 자유 개수
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int getMyJayuCount(String accountId);    
	
	/**
	 * (마이페이지)내가 쓴 자유 목록 조회        
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 내가 쓴 자유 목록(게시물ID, 제목, 댓글수, 좋아요여부, 좋아요수, 조회수, 등록일, 수정일, 상태)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public List<BoardVo> getMyJayuList(Map<String, Object> map);
	
	/**
	 * (마이페이지)좋아요한 자유 개수           
	 * @param accountId 현재 로그인한 계정
	 * @return 좋아요한 자유 개수
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int getLikeJayuCount(String accountId);
	
	/**
	 * (마이페이지)좋아요한 자유 목록 조회       
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 좋아요한 목록(게시물ID, 제목, 작성자, 좋아요여부, 좋아요한 날짜, 등록일)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public List<BoardVo> getLikeJayuList(Map<String, Object> map);
		
	/**
	 * 자유 작성
	 * @param vo accountId, title, content
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int insertJayu(BoardVo vo);
	
	/**
	 * 자유 수정
	 * @param vo content, id
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int updateJayu(BoardVo vo);        
	
	/**
	 * 자유 완전 삭제						  
	 * @param id 게시글ID
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int deleteJayu(String id);
}
