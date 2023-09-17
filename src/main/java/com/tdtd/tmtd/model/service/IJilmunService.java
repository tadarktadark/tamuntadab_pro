package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

/**
 * 질문 관련 서비스
 * @author SoHyeon
 * @since 2023.09.16
 * @version 1.0
 */
public interface IJilmunService {
	
	/**
	 * 질문 조회 가능한 게시글 개수	
	 * @return 조회 가능한 게시글 개수
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int getJilmunCount();      
	
	/**
	 * 질문 목록 조회(정렬)
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				orderBy={"like","view","reply"} defalut="date" 정렬, <br> 
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 질문 목록(게시글ID, 작성자, 제목, 클래스명, 댓글수, 과목, 좋아요 여부, 좋아요 개수, 조회수, 채택여부,등록일)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public List<BoardVo> getJilmunList(Map<String, Object> map);
	
	/**
	 * 질문 상세 조회 및 조회수 업데이트
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				id 질문 게시글ID
	 * @return 질문 상세(게시글ID, 작성자, 제목(클래스명), 클래스명, 내용, 댓글수, 과목, 좋아요 여부, 좋아요 개수, 조회수, 등록일, 수정일, 상태)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public BoardVo getJilmunDetail(Map<String, Object> map);
		
	/**
	 * 질문 좋아요 유저 조회
	 * @param id 게시글ID
	 * @return 좋아요한 유저 String JSON {"accountId":"yyyymmdd", ...}
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public String getJilmunLikeUser(String id);
	
	/**
	 * 질문 좋아요 업데이트                   
	 * @param map likeUser 좋아요한 유저 String JSON {"accountId":"yyyymmdd", ...} <br>
	 * 				id 질문 게시글ID <br>
	 * 				likeCount 좋아요 유저수
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int updateJilmunLikeUser(Map<String, Object> map);
		
	/**(마이페이지)내가 쓴 질문 개수           
	 * 
	 * @param accountId	현재 로그인한 계정
	 * @return 내가 쓴 질문 개수
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int getMyJilmunCount(String accountId);    
	
	/**
	 * (마이페이지)내가 쓴 질문 목록 조회        
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 내가 쓴 질문 목록(게시물ID, 제목, 클래스명, 과목, 댓글수, 채택여부, 좋아요여부, 좋아요수, 조회수, 등록일, 수정일, 상태)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public List<BoardVo> getMyJilmunList(Map<String, Object> map);
	
	/**
	 * (마이페이지)좋아요한 질문 개수           
	 * @param accountId 현재 로그인한 계정
	 * @return 좋아요한 질문 개수
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int getLikeJilmunCount(String accountId);
	
	/**
	 * (마이페이지)좋아요한 질문 목록 조회       
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 좋아요한 목록(게시물ID, 제목, 작성자, 좋아요여부, 좋아요한 날짜, 등록일)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public List<BoardVo> getLikeJilmunList(Map<String, Object> map);
	
	/**
	 * 질문 작성 선택 가능한 클래스 목록       
	 * @param accountId 현재 로그인한 계정
	 * @return 클래스 정보(클래스ID, 클래스명)
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public List<ClassVo> getJilmunClassList(String accountId);
	
	/**
	 * 질문 작성 선택한 클래스 과목       
	 * @param clasId 선택한 클래스 ID
	 * @return 과목 정보(과목 코드, 과목 타이틀)
	 * @author SoHyeon
	 * @since 2023.09.17
	 */
	public ClassVo getJilmunSubject(String clasId);
	
	/**
	 * 질문 작성
	 * @param vo 클래스 있을 때 accountId, title, clasId, content, subjectCode<br>
	 * 			클래스 없을 때 accountId, title, content, subjectCode
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int insertJilmun(BoardVo vo);
	
	/**
	 * 질문 수정
	 * @param vo 클래스 있을 때 clasId, content, subjectCode, id<br>
	 * 			클래스 없을 때 content, subjectCode, id
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int updateJilmun(BoardVo vo);        
	
	/**
	 * 질문 완전 삭제						  
	 * @param id 게시글ID
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int deleteJilmun(String id);
}
