package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.SubjectTagVo;
import com.tdtd.tmtd.vo.SubjectVo;

public interface IClassService {

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
	 * 기존 과목을 통한 클래스 개설 후, 해당 클래스에 개설자를 클래스장으로 포함시킨다
	 * 사용 Dao = addClass, addChamyeoja Dao
	 * @param vo 개설될 클래스의 정보가 담긴 vo
	 * @return 생성 성공 여부 1 = 성공 0 = 실패
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addClass(ClassVo clVo, ChamyeoVo chVo);
	
	/**
	 * 새로운 과목을 통해 클래스를 추가할 경우, 관리자의 승인을 얻어야 함. 해당 클래스에 개설자를 클래스장으로 포함시킨다.
	 * 클래스 + 과목 + 과목태그를 관리자만 볼 수 있는 상태로 insert 하는 service
	 * 사용 Dao = addClass, addSubject, addSubjectTag, addChamyeoja
	 * @param vo 추가할 과목의 정보가 담긴 Vo
	 * @return 생성 성공 여부 1 = 성공 0 = 실패
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addClassWithSub
	(ClassVo cVo, Map<String, Object> subMap,Map<String, Object> subTagMap);
}
