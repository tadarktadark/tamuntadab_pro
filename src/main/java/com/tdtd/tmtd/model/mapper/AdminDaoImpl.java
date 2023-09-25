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
	public int countAdmin() {
		return sqlSession.selectOne(NS+"countAdmin");
	}

	@Override
	public List<UserProfileVo> getUserList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getUserList",map);
	}
}
