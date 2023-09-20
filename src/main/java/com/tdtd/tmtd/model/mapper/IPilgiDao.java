package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

/**
 * 필기 관련 기능 2023.09.16 수정
 * @author SoHyeon
 * @since 2023.09.14
 * @version 2.0
 */
public interface IPilgiDao {
	
	/**
	 * 필기 조회 가능한 게시글 개수	 
	 * @param accountId 현재 로그인한 계정
	 * @return 조회 가능한 게시글 개수
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int getPilgiCount(String accountId);      
	
	/**
	 * 필기 목록 조회(정렬)
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				orderBy={"like","view","reply"} defalut="date" 정렬, <br> 
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 필기 목록(게시글ID, 작성자, 제목(클래스명), 댓글수, 과목, 좋아요 여부, 좋아요 개수, 조회수, 등록일)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public List<BoardVo> getPilgiList(Map<String, Object> map);
	
	/**
	 * 필기 상세 조회
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				id 필기 게시글ID
	 * @return 필기 상세(게시글ID, 작성자, 제목(클래스명), 내용, 댓글수, 과목, 좋아요 여부, 좋아요 개수, 조회수, 등록일, 수정일, 상태)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public BoardVo getPilgiDetail(Map<String, Object> map);
	
	/**
	 * 연관 필기 목록 조회
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				id 필기 게시글ID
	 * @return 연관 필기 목록 5개(게시글ID, 제목)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public List<BoardVo> getYeongwanList(Map<String, Object> map);
	
	/**
	 * 필기 좋아요 유저 조회
	 * @param id 게시글ID
	 * @return 좋아요한 유저 String JSON {"accountId":"yyyymmdd", ...}
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public String getPilgiLikeUser(String id);
	
	/**
	 * 필기 좋아요 업데이트                   
	 * @param map likeUser 좋아요한 유저 String JSON {"accountId":"yyyymmdd", ...} <br>
	 * 				id 필기 게시글ID <br>
	 * 				likeCount 좋아요 유저수
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int updatePilgiLikeUser(Map<String, Object> map);
	
	/**
	 * 필기 조회한 유저 조회                      
	 * @param id 게시글ID
	 * @return 조회한 유저 String JSON {"accountId":"yyyymmdd", ...}
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public String getPilgiViewUser(String id);
	
	/**
	 * 필기 조회한 유저 업데이트                   
	 * @param map viewUser 조회한 유저 String JSON {"accountId":"yyyymmdd", ...} <br>
	 * 				id 필기 게시글ID <br>
	 * 				viewCount 조회수
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int updatePilgiViewUser(Map<String, Object> map);
	
	/**(마이페이지)내가 쓴 필기 개수           
	 * 
	 * @param accountId	현재 로그인한 계정
	 * @return 내가 쓴 필기 개수
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int getMyPilgiCount(String accountId);    
	
	/**
	 * (마이페이지)내가 쓴 필기 목록 조회        
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 내가 쓴 필기 목록(게시물ID, 제목(클래스명), 과목, 댓글수, 좋아요여부, 좋아요수, 조회수, 등록일, 수정일, 상태)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public List<BoardVo> getMyPilgiList(Map<String, Object> map);
	
	/**
	 * (마이페이지)좋아요한 필기 개수           
	 * @param accountId 현재 로그인한 계정
	 * @return 좋아요한 필기 개수
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int getLikePilgiCount(String accountId);
	
	/**
	 * (마이페이지)좋아요한 필기 목록 조회       
	 * @param map accountId 현재 로그인한 계정, <br>
	 * 				start 시작 게시글, <br>
	 * 				end 종료 게시글
	 * @return 좋아요한 목록(게시물ID, 제목(클래스명), 작성자, 좋아요여부, 좋아요한 날짜, 등록일)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public List<BoardVo> getLikePilgiList(Map<String, Object> map);
	
	/**
	 * 필기 작성시 기본 정보 조회              
	 * @param clasId 클래스ID
	 * @return 클래스 정보(클래스ID, 클래스명, 과목)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public ClassVo getPilgiClassDetail(String clasId);
	
	/**
	 * 필기 작성
	 * @param vo accountId, clasId, content, viewGroup, downloadGroup
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int insertPilgi(BoardVo vo);
	
	/**
	 * 클래스 참여자 상태 변경
	 * @param map state 필기 작성 여부 Y=작성, N=미작성<br>
	 * 				accountId 작성자<br>
	 * 				id 게시글id
	 * @return
	 * @author SoHyeon
	 * @since 2023.09.16
	 */
	public int updateClchPilgiState(Map<String, Object> map);
	
	/**
	 * 필기 임시저장                        
	 * @param vo accountId, clasId, content, viewGroup, downloadGroup
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int insertPilgiImsi(BoardVo vo);
	
	/**
	 * 필기 임시저장 목록 조회
	 * @param map accountId 계정<br>
	 * 				clasId 클래스 ID
	 * @return 임시 저장 목록(임시저장seq, SUBSTR(내용,15)||...) 
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public List<BoardVo> getPilgiImsiList(Map<String, Object> map);
	
	/**
	 * 필기 임시저장 데이터 가져오기            
	 * @param id 임시저장글 seq
	 * @return 임시 저장 상세(내용, 공개그룹, 다운로드그룹)
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public BoardVo getPilgiImsiDetail(String id); 
	
	/**
	 * 필기 임시저장 삭제하기
	 * @param id 임시저장글 seq
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int deletePilgiImsi(String id);
	
	/**
	 * 필기 수정 데이터 조회
	 * @param id 필기 id
	 * @return BoardVo 제목, 과목, 내용, 파일, 조회 그룹, 다운로드 그룹 
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public BoardVo getPilgiUpdateData(String id);
	
	/**
	 * 필기 수정
	 * @param vo content, viewGroup, downloadGroup, id
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int updatePilgi(BoardVo vo);   
	
	/**
	 * 필기 수정시 파일 삭제
	 * @param save 삭제할 파일 저장 이름
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.20
	 */
	public int deletePilgiFile(String save);
	
	/**
	 * 필기 임시 삭제 또는 복원
	 * @param map id 게시글ID<br>
	 * 			state Y=임시삭제, N=복원
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int updatePilgiState(Map<String, Object> map); 
	 
	
	/**
	 * 필기 완전 삭제						  
	 * @param id 게시글ID
	 * @return 성공 1, 실패 0
	 * @author SoHyeon
	 * @since 2023.09.14
	 */
	public int deletePilgi(String id);
}
