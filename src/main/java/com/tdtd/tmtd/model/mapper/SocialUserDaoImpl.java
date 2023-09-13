package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.UserProfileVo;

@Repository
public class SocialUserDaoImpl implements ISocialUserDao {
	
	private final String NS = "com.tdtd.tmtd.model.mapper.SocialUserDaoImpl.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int registNaverUser(Map<String, String> userProfile) {
		return sqlSession.insert(NS+"naverRegist",userProfile);
	}

	@Override
	public int registKakaoUser(Map<String, String> userProfile) {
		return sqlSession.insert(NS+"kakaoRegist",userProfile);
	}

	@Override
	public int registGoogleUser(Map<String, String> userProfile) {
		return sqlSession.insert(NS+"googleRegist",userProfile);
	}

	@Override
	public int updateRefToken(Map<String, String> userProfile) {
		return sqlSession.update(NS+"updateRefreshToken",userProfile);
	}

	@Override
	public UserProfileVo SocialLogin(Map<String, String> userProfile) {
		return sqlSession.selectOne(NS+"socialLogin",userProfile);
	}

}
