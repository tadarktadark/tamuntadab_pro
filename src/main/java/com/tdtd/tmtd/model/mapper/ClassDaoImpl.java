package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.SugangryoVo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ClassDaoImpl implements IClassDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "com.tdtd.tmtd.model.mapper.ClassDaoImpl.";
	
	/**
	 * 페이징 처리 후 출력할 클래스 목록 조회
	 * @param 출력할 클래스목록의 시작번호와 끝번호가 담긴 Map
	 * @return 페이징 처리된 클래스 목록
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public List<ClassVo> getAllClassListForS(Map<String, Object> map) {
		log.info("ClassDaoImpl getAllClassListForS 실행");
		return sqlSession.selectList(NS+"getAllClassListForS",map);
	}
	
	/**
	 * 페이징 처리될 전체 클래스 수 조회
	 * @return 페이징 처리될 전체 클래스 갯수
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int getAllClassListForSCount() {
		log.info("ClassDaoImpl getAllClassListForSCount 실행");
		return sqlSession.selectOne(NS+"getAllClassListForSCount");
	}
	
	/**
	 * 클래스 개설
	 * @param vo 개설될 클래스의 정보가 담긴 vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int addClass(ClassVo vo) {
		log.info("ClassDaoImpl addClass 실행");
		int n = sqlSession.insert(NS+"addClass",vo);
		return n;
	}
	
	/**
	 * 과목 추가
	 * @param vo 추가할 과목의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int addSubject(Map<String, Object> map) {
		log.info("ClassDaoImpl addSubject 실행");
		return sqlSession.insert(NS+"addSubject",map);
	}
	
	/**
	 * 과목 추가
	 * @param vo 추가할 과목 태그의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int addSubjectTag(Map<String, Object> map) {
		log.info("ClassDaoImpl addSubjectTag 실행");
		return sqlSession.insert(NS+"addSubjectTag",map);
	}

	/**
	 * 참여자 추가
	 * @param vo 추가할 클래스 참여자의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int addChamyeoja(ChamyeoVo vo) {
		log.info("ClassDaoImpl addChamyeoja 실행");
		return sqlSession.insert(NS+"addChamyeoja",vo);
	}
	
	/**
	 * 과목 이름을 입력하면 과목 id를 반환하는 DAO
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public String findSubjId(String title) {
		log.info("ClassDaoImpl findSubjId 실행");
		return sqlSession.selectOne(NS+"findSubjId", title);
	};
	
	/**
	 * 클래스 id에 해당하는 클래스의 정보를 가져오는 메소드
	 * @param clasId
	 * @return 클래스 id에 해당하는 클래스의 정보 Vo
	 */
	public ClassVo getClassDetail(String clasId) {
		log.info("ClassDaoImpl getClassDetail 실행");
		return sqlSession.selectOne(NS+"getClassDetail", clasId);
	}

	@Override
	public List<ClassVo> getAllClassListForA(Map<String, Object> map) {
		log.info("ClassDaoImpl getAllClassListForA 실행");
		return sqlSession.selectList(NS+"getAllClassListForA",map);
	}

	@Override
	public int getAllClassListForACount() {
		log.info("ClassDaoImpl getAllClassListForACount 실행");
		return sqlSession.selectOne(NS+"getAllClassListForACount");
	}

	@Override
	public List<ClassVo> getCategoryClassListForS(Map<String, Object> map) {
		log.info("ClassDaoImpl getCategoryClassListForS 실행");
		return sqlSession.selectList(NS+"getCategoryClassListForS",map);
	}

	@Override
	public int getCategoryClassListForSCount(String category) {
		log.info("ClassDaoImpl getCategoryClassListForSCount 실행");
		return sqlSession.selectOne(NS+"getCategoryClassListForSCount",category);
	}

	@Override
	public List<ClassVo> getAllClassListForI(Map<String, Object> map) {
		log.info("ClassDaoImpl getAllClassListForI 실행");
		return sqlSession.selectList(NS+"getAllClassListForI",map);
	}

	@Override
	public int getAllClassListForICount() {
		log.info("ClassDaoImpl getAllClassListForICount 실행");
		return sqlSession.selectOne(NS+"getAllClassListForICount");
	}

	@Override
	public List<ClassVo> getCategoryClassListForI(Map<String, Object> map) {
		log.info("ClassDaoImpl getCategoryClassListForI 실행");
		return sqlSession.selectList(NS+"getCategoryClassListForI",map);
	}

	@Override
	public int getCategoryClassListForICount(String category) {
		log.info("ClassDaoImpl getCategoryClassListForICount 실행");
		return sqlSession.selectOne(NS+"getCategoryClassListForICount",category);
	}

	@Override
	public ChamyeoVo getChamyeojaInfo(String clchAccountId) {
		log.info("ClassDaoImpl getChamyeojaInfo 실행");
		return sqlSession.selectOne(NS+"getChamyeojaInfo",clchAccountId);
	}

	@Override
	public List<ChamyeoVo> getChamyeojas(String clasId) {
		log.info("ClassDaoImpl getChamyeojas 실행");
		return sqlSession.selectList(NS+"getChamyeojas",clasId);
	}

	@Override
	public int updateClassPeople(Map<String, Object> map) {
		log.info("ClassDaoImpl updateClassPeople 실행");
		return sqlSession.update(NS+"updateClassPeople", map);
	}

	@Override
	public int updateClassStatus(Map<String, Object> map) {
		log.info("ClassDaoImpl updateClassStatus 실행");
		return sqlSession.update(NS+"updateClassStatus", map);
	}

	@Override
	public int updateChamyeoYeokal(ChamyeoVo vo) {
		log.info("ClassDaoImpl updateChamyeoYeokal 실행");
		return sqlSession.update(NS+"updateChamyeoYeokal", vo);
	}

	@Override
	public int delChamyeoja(Map<String, Object> map) {
		log.info("ClassDaoImpl delChamyeoja 실행");
		return sqlSession.delete(NS+"delChamyeoja", map);
	}

	@Override
	public int dealSugangryo(SugangryoVo vo) {
		log.info("ClassDaoImpl dealSugangryo 실행");
		return sqlSession.insert(NS+"dealSugangryo",vo);
	}

	@Override
	public SugangryoVo getSugangryo(String sugaClasId) {
		log.info("ClassDaoImpl getSugangryo 실행");
		return sqlSession.selectOne(NS+"SugangryoVo",sugaClasId);
	}
	
	public List<ClassVo> getChamyeoClass(String clchAccountId){
		log.info("ClassDaoImpl getChamyeoClass 실행");
		return sqlSession.selectList(NS+"getChamyeoClass",clchAccountId);
	}
	
	public int addChamyeojaGeneral(ChamyeoVo vo) {
		log.info("ClassDaoImpl addChamyeojaGeneral 실행");
		return sqlSession.insert(NS+"addChamyeojaGeneral",vo);
	}
	
	/**
	 * 상태별 클래스 조회
	 * @param map
	 * @return
	 */
	public List<ClassVo> getClassListByStatus(String clchAccountId){
		log.info("ClassDaoImpl getClassListByStatus 실행");
		return sqlSession.selectList(NS+"getClassListByStatus",clchAccountId);
	}
	
	public List<ClassVo> searchClassList(Map<String, Object> map){
		log.info("ClassDaoImpl searchClassList 실행");
		return sqlSession.selectList(NS+"searchClassList",map);
	}
	
	public int searchClassListCount (String subject) {
		log.info("ClassDaoImpl searchClassListCount 실행");
		return sqlSession.selectOne(NS+"searchClassListCount",subject);
	}
}
