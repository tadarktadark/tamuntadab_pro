package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.SubjectVo;

public interface ISubjectService {

	/**
	 * @return 페이징 처리된 과목 리스트 select
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public List<SubjectVo> getSubjectList();
	
	/**
	 * @return 페이징 처리할 전체 과목 갯수
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int getSubjectTagListCount();
	
	/**
	 * @return 업데이트에 성공한 과목 갯수 int
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int updateSubject(SubjectVo vo);
	
	/**
	 * @return 업데이트에 성공한 클래스 갯수 int
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int updateClass (Map<String, Object> map);
	
}
