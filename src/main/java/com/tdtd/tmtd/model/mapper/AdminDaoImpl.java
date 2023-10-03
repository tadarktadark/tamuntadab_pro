package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.AdminVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AdminDaoImpl implements IAdminDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String NS = "com.tdtd.tmtd.model.mapper.AdminDaoImpl.";
	
	@Override
	public int checkIP(String accessIP) {
		return sqlSession.selectOne(NS+"searchIP",accessIP);
	}

	@Override
	public AdminVo adminLogin(Map<String, Object> adminInput) {
		return sqlSession.selectOne(NS+"adminLogin",adminInput);
	}

	@Override
	public int updateAdminAccTime(AdminVo adminInfo) {
		return sqlSession.update(NS+"updateAdminAccTime",adminInfo);
	}

	@Override
	public int updateAdminPw(Map<String, Object> setPassword) {
		return sqlSession.update(NS+"updateAdminPw",setPassword);
	}

	@Override
	public List<AdminVo> getAdminList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getAdminList",map);
	}

	@Override
	public int countAdmin(Map<String,Object>map) {
		return sqlSession.selectOne(NS+"countAdmin",map);
	}

	@Override
	public List<UserProfileVo> getUserList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getUserList",map);
	}

	@Override
	public int countUser(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"countUser",map);
	}

	@Override
	public UserProfileVo getuserDetail(String userId) {
		return sqlSession.selectOne(NS+"getuserDetail",userId);
	}

	@Override
	public int setuserJeongji(Map<String, Object> map) {
		return sqlSession.insert(NS+"setuserJeongji",map);
	}

	@Override
	public int updatesisu(Map<String, Object> map) {
		return sqlSession.update(NS+"updatesisu",map);
	}

	@Override
	public int addAdmin(Map<String, Object> map) {
		return sqlSession.insert(NS+"addAdmin",map);
	}

	@Override
	public int addIP(Map<String, Object> map) {
		return sqlSession.insert(NS+"addIP",map);
	}

	@Override
	public AdminVo adminDetail(String userId) {
		return sqlSession.selectOne(NS+"adminDetail",userId);
	}

	@Override
	public List<AdminVo> adminipDetail(String userId) {
		return sqlSession.selectList(NS+"adminipDetail",userId);
	}

	@Override
	public int adminIdCheck(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"adminIdCheck",map);
	}

	@Override
	public int delAdmin(String adminId) {
		return sqlSession.update(NS+"delAdmin",adminId);
	}

	@Override
	public int delAdmin_IP(String adminId) {
		return sqlSession.delete(NS+"delAdmin_IP",adminId);
	}

	@Override
	public int delIP(Map<String, Object> map) {
		return sqlSession.delete(NS+"delIP",map);
	}

	@Override
	public int restoreAdmin(String adminId) {
		return sqlSession.delete(NS+"restoreAdmin",adminId);
	}

	@Override
	public int resetAdminPw(String adminId) {
		return sqlSession.delete(NS+"resetAdminPw",adminId);
	}
	
}
