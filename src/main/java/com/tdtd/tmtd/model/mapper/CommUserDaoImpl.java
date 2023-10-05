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
	public boolean searchNickName(Map<String,Object> map) {
		return sqlSession.selectOne(NS+"searchNickName",map);
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
		return sqlSession.update(NS+"updateUserChadanDate",userEmail);
	}
	@Override
	public String checkUserChadanDate(String userEmail) {
		return sqlSession.selectOne(NS+"checkUserChadanDate",userEmail);
	}
	@Override
	public int restoreUserChadanDate(String userEmail) {
		return sqlSession.update(NS+"restoreUserChadanDate",userEmail);
	}
	@Override
	public int updateTime(UserProfileVo userInfo) {
		return sqlSession.update(NS+"updateTime",userInfo);
	}
	@Override
	public int restoreUserChadanCount(UserProfileVo userInfo) {
		return sqlSession.update(NS+"restoreUserChadanCount",userInfo);
	}
	@Override
	public UserProfileVo autoLogin(String userAutoLoginToken) {
		return sqlSession.selectOne(NS+"autoLogin",userAutoLoginToken);
	}
	@Override
	public int updateUserInfo(Map<String, Object> updateInfo) {
		return sqlSession.update(NS+"updateUserInfo",updateInfo);
	}
	@Override
	public int updateResetPwToken(Map<String, Object> resetPassword) {
		return sqlSession.update(NS+"updateResetPwToken",resetPassword);
	}
	@Override
	public int deleteResetPwToken(Map<String, Object> resetPassword) {
		return sqlSession.update(NS+"deleteResetPwToken",resetPassword);
	}
	@Override
	public int updateUserPassword(Map<String, Object> resetPassword) {
		return sqlSession.update(NS+"updateUserPassword",resetPassword);
	}
	@Override
	public int updatedelflag(Map<String, Object> userToken) {
		return sqlSession.update(NS+"updatedelflag",userToken);
	}
	@Override
	public int insertUserDelTable(UserProfileVo vo) {
		return sqlSession.insert(NS+"insertUserDelTable",vo);
	}
	@Override
	public int updateUserDelflagToY(UserProfileVo vo) {
		return sqlSession.update(NS+"updateUserDelflagToY",vo);
	}
	@Override
	public int searchUserGyeolje(UserProfileVo vo) {
		return sqlSession.selectOne(NS+"searchUserGyeolje",vo);
	}
	@Override
	public int searchUserJeongSan(UserProfileVo vo) {
		return sqlSession.selectOne(NS+"searchUserJeongSan",vo);
	}
	@Override
	public int checkPassword(Map<String, Object> resetPassword) {
		return sqlSession.selectOne(NS+"checkPassword",resetPassword);
	}
	@Override
	public String jeongjidate(UserProfileVo userInfo) {
		return sqlSession.selectOne(NS+"jeongjidate",userInfo);
	}
	@Override
	public int countDeluser() {
		return sqlSession.selectOne(NS+"countDeluser");
	}
}
