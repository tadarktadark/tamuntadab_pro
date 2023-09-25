package com.tdtd.tmtd.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IClassDao;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.SugangryoVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassServiceImpl implements IClassService {
	
	@Autowired
	private IClassDao dao;

	/**
	 * 페이징 처리 후 출력할 클래스 목록 조회
	 * @param 출력할 클래스목록의 시작번호와 끝번호가 담긴 Map
	 * @return 페이징 처리된 클래스 목록
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public List<ClassVo> getAllClassListForS(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - getAllClassListForS - 페이징 할 클래스 가져오기");
		return dao.getAllClassListForS(map);
	}

	/**
	 * 페이징 처리될 전체 클래스 수 조회
	 * @return 페이징 처리될 전체 클래스 갯수
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int getAllClassListForSCount() {
		log.info("ClassServiceImpl 실행 - getAllClassListForSCount");
		return dao.getAllClassListForSCount();
	}

	/**
	 * 기존 과목을 통한 클래스 개설 후, 해당 클래스에 개설자를 클래스장으로 포함시킨다
	 * 사용 Dao = addClass, addChamyeoja Dao
	 * @param vo 개설될 클래스의 정보가 담긴 vo
	 * @return 생성 성공 여부 1 = 성공 0 = 실패
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int addClass(ClassVo clVo, ChamyeoVo chVo) {
		log.info("ClassServiceImpl 실행 - addClass");
		int n = dao.addClass(clVo);
		int m = dao.addChamyeoja(chVo);
		return (n+m==2)?1:0;
	}

	/**
	 * 새로운 과목을 통해 클래스를 추가할 경우, 관리자의 승인을 얻어야 함. 해당 클래스에 개설자를 클래스장으로 포함시킨다.
	 * 클래스 + 과목 + 과목태그를 관리자만 볼 수 있는 상태로 insert 하는 service
	 * 사용 Dao = addClass, addSubject, addSubjectTag, addChamyeoja
	 * @param vo 추가할 과목의 정보가 담긴 Vo
	 * @return 생성 성공 여부 1 = 성공 0 = 실패
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int addClassWithSub(ClassVo cVo,Map<String, Object> submap, Map<String, Object> subTagMap) {
		log.info("ClassServiceImpl 실행 - addClassWithSub");
		int classCnt = dao.addClass(cVo);
		int subjectCnt = dao.addSubject(submap);
		int subjectTagCnt = dao.addSubjectTag(subTagMap);
		return (classCnt+subjectCnt+subjectTagCnt==3)?1:0;
	}

	/**
	 * @author 김기훈
	 * @since 2023-09-15
	 */
	@Override
	public List<String> findSubjId(List<String> titles) {
		log.info("ClassServiceImpl 실행 - findSubjId");
		List<String> SubjId = new ArrayList<String>();
		for (int i = 0; i < titles.size(); i++) {
			SubjId.add(dao.findSubjId(titles.get(i)));
		}
		return SubjId;
	}
	
	/**
	 * 클래스 id에 해당하는 클래스의 정보를 가져오는 메소드
	 * @param clasId
	 * @return 클래스 id에 해당하는 클래스의 정보 Vo
	 * @author 김기훈
	 * @since 2023-09-17
	 */
	public ClassVo getClassDetail(String clasId) {
		log.info("ClassServiceImpl 실행 - getClassDetail");
		return dao.getClassDetail(clasId);
	}

	@Override
	public List<ClassVo> getAllClassListForA(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - getAllClassListForA");
		return dao.getAllClassListForA(map);
	}

	@Override
	public int getAllClassListForACount() {
		log.info("ClassServiceImpl 실행 - getAllClassListForACount");
		return dao.getAllClassListForACount();
	}

	@Override
	public List<ClassVo> getCategoryClassListForS(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - getCategoryClassListForS");
		return dao.getCategoryClassListForS(map);
	}

	@Override
	public int getCategoryClassListForSCount(String category) {
		log.info("ClassServiceImpl 실행 - getCategoryClassListForSCount");
		return dao.getCategoryClassListForSCount(category);
	}

	@Override
	public List<ClassVo> getAllClassListForI(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - getAllClassListForI");
		return dao.getAllClassListForI(map);
	}

	@Override
	public int getAllClassListForICount() {
		log.info("ClassServiceImpl 실행 - getAllClassListForICount");
		return dao.getAllClassListForICount();
	}

	@Override
	public List<ClassVo> getCategoryClassListForI(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - getCategoryClassListForI");
		return dao.getCategoryClassListForI(map);
	}

	@Override
	public int getCategoryClassListForICount(String category) {
		log.info("ClassServiceImpl 실행 - getCategoryClassListForICount");
		return dao.getCategoryClassListForICount(category);
	}

	@Override
	public ChamyeoVo getChamyeojaInfo(String clchAccountId) {
		log.info("ClassServiceImpl 실행 - getChamyeojaInfo");
		return dao.getChamyeojaInfo(clchAccountId);
	}

	@Override
	public List<ChamyeoVo> getChamyeojas(String clasId) {
		log.info("ClassServiceImpl 실행 - getChamyeojas");
		return dao.getChamyeojas(clasId);
	}

	@Override
	public int updateClassPeople(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - updateClassPeople");
		return dao.updateClassPeople(map);
	}

	@Override
	public int updateClassStatus(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - updateClassStatus");
		return dao.updateClassStatus(map);
	}

	@Override
	public int updateChamyeoYeokal(ChamyeoVo vo) {
		log.info("ClassServiceImpl 실행 - updateChamyeoYeokal");
		return dao.updateChamyeoYeokal(vo);
	}

	@Override
	public int delChamyeoja(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - delChamyeoja");
		return dao.delChamyeoja(map);
	}

	@Override
	public int dealSugangryo(SugangryoVo vo) {
		log.info("ClassServiceImpl 실행 - dealSugangryo");
		return dao.dealSugangryo(vo);
	}

	@Override
	public SugangryoVo getSugangryo(String sugaClasId) {
		log.info("ClassServiceImpl 실행 - getSugangryo");
		return dao.getSugangryo(sugaClasId);
	}

	@Override
	public int addChamyeoja(ChamyeoVo vo) {
		log.info("ClassServiceImpl 실행 - addChamyeoja");
		return dao.addChamyeoja(vo);
	}
	
	public List<ClassVo> getChamyeoClass(String clchAccountId){
		log.info("ClassServiceImpl 실행 - getChamyeoClass");
		return dao.getChamyeoClass(clchAccountId);
	}
	
	public int addChamyeojaGeneral(ChamyeoVo vo) {
		log.info("ClassServiceImpl 실행 - addChamyeojaGeneral");
		return dao.addChamyeojaGeneral(vo);
	}
	
	public List<ClassVo> getClassListByStatus(String clchAccountId){
		log.info("ClassServiceImpl 실행 - getClassListByStatus");
		return dao.getClassListByStatus(clchAccountId);
	}
	
	public List<ClassVo> searchClassList(Map<String, Object> map){
		log.info("ClassServiceImpl 실행 - searchClassList");
		return dao.searchClassList(map);
	}
	
	public int searchClassListCount (String subject) {
		log.info("ClassServiceImpl 실행 - searchClassListCount");
		return dao.searchClassListCount(subject);
	}

	@Override
	public List<ClassVo> myPageClassList(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - myPageClassList");
		return dao.myPageClassList(map);
	}

	@Override
	public int myPageClassListCount(String clchAccountId) {
		log.info("ClassServiceImpl 실행 - myPageClassListCount");
		return dao.myPageClassListCount(clchAccountId);
	}

	@Override
	public List<ClassVo> myPageEndClassList(Map<String, Object> map) {
		log.info("ClassServiceImpl 실행 - myPageEndClassList");
		return dao.myPageEndClassList(map);
	}

	@Override
	public int myPageEndClassListCount(String clchAccountId) {
		log.info("ClassServiceImpl 실행 - myPageEndClassListCount");
		return dao.myPageEndClassListCount(clchAccountId);
	}
}
