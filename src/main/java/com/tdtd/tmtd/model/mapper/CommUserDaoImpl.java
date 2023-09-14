package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.UserProfileVo;

@Repository
public class CommUserDaoImpl implements ICommUserDao {
	
	private final String NS = "com.tdtd.tmtd.model.mapper.CommUserDaoImpl.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Boolean emailCheck(Map<String,String> map) {
		return sqlSession.selectOne(NS+"searchEmail",map);
	}
	@Override
	public boolean searchNickName(String userNickName) {
		return sqlSession.selectOne(NS+"searchNickName",userNickName);
	}

	@Override
	public int registCommUser(Map<String,Object> userprofile) {
		return sqlSession.insert(NS+"commRegist",userprofile);
	}
	@Override
	public int searchJeongJi(UserProfileVo userInfo) {
		return sqlSession.selectOne(NS+"searchJeongJi",userInfo);
	}
	@Override
	public UserProfileVo commLogin(Map<String, String> userInput) {
		return sqlSession.selectOne(NS+"commLogin",userInput);
	}
	@Override
	public int updateChadanCnt(String userEmail) {
		return sqlSession.update(NS+"updatechadanCnt",userEmail);
	}
	@Override
	public int checkUserChadanCount(String userEmail) {
		return sqlSession.selectOne(NS+"checkUserChadanCount",userEmail);
	}
	@Override
	public int updateUserChadanDate(String userEmail) {
		return sqlSession.update(userEmail);
	}
	@Override
	public String checkUserChadanDate(String userEmail) {
		return sqlSession.selectOne(NS+"checkUserChadanDate",userEmail);
	}
	@Override
	public int restoreUserChadanDate(String userEmail) {
		return sqlSession.update(NS+"restoreUserChadanDate",userEmail);
	}
	
	

	
}
