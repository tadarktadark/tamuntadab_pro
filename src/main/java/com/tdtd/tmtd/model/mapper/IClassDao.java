package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.SubjectTagVo;
import com.tdtd.tmtd.vo.SubjectVo;

public interface IClassDao {

	/**
	 * 페이징 처리 후 출력할 클래스 목록 조회
	 * @param 출력할 클래스목록의 시작번호와 끝번호가 담긴 Map
	 * @return 페이징 처리된 클래스 목록
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public List<ClassVo> getClassList(Map<String, Object> map);
	
	/**
	 * 페이징 처리될 전체 클래스 수 조회
	 * @return 페이징 처리될 전체 클래스 갯수
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int getClassListCount();
	
	/**
	 * 클래스 개설
	 * @param vo 개설될 클래스의 정보가 담긴 vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addClass(ClassVo vo);
	
	/**
	 * 과목 추가
	 * @param vo 추가할 과목의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addSubject(Map<String, Object> map);
	
	/**
	 * 과목 추가
	 * @param vo 추가할 과목 태그의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addSubjectTag(Map<String, Object> map);
	
	/**
	 * 참여자 추가
	 * @param vo 추가할 클래스 참여자의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addChamyeoja(ChamyeoVo vo);
}
