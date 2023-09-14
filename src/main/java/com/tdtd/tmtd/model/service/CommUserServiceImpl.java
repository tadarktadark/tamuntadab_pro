package com.tdtd.tmtd.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ICommUserDao;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommUserServiceImpl implements ICommUserService {
	
	@Autowired
	private ICommUserDao cdao;
	
	public Boolean searchEmailService(Map<String, String> map) {
		return cdao.emailCheck(map);
	}

	@Override
	public int registCommUser(Map<String, Object> userprofile) {
		return cdao.registCommUser(userprofile);
	}

	@Override
	public int searchJeongJi(UserProfileVo userInfo) {
		return cdao.searchJeongJi(userInfo);
	}

	@Override
	public Map<String, Object> commLogin(Map<String, String> userInput) {
		Map<String,Object> result = new HashMap<String, Object>();
		
		UserProfileVo uservo = cdao.commLogin(userInput);
		if(uservo == null) {
			int n = cdao.updateChadanCnt(userInput.get("userEmail"));
			if(n==0) {
				result.put("status","noEmail");
				return result;
			}else{
				result.put("status", "updateChadan");
				//그 이메일의 차단 정보를 가져온다.
				int cnt = cdao.checkUserChadanCount(userInput.get("userEmail"));
				result.put("chadanCnt", cnt);
				if(cnt > 4 ) {
					//업데이트 해주기
					cdao.updateUserChadanDate(userInput.get("userEmail"));
					result.put("status", "chadan");
					result.put("chadanCnt", cnt+1);
				}
				return result;
			}
		}else {
			if(uservo.getUserChadanRegistDate() != "") {
				//사용자가 로그인을 성공 했지만 해당 유저가 차단되어 있는 상태일 경우
				String time = cdao.checkUserChadanDate(uservo.getUserEmail()); 
				log.info("{}",time);
				if(time.equals("782898")) {
					cdao.restoreUserChadanDate(uservo.getUserEmail());
					result.put("status","success");
					result.put("userInfo", uservo);
					return result;
				}else {
					result.put("status", "chadanDate");
					result.put("time", time);
				}
				return result;
			}else {
				result.put("status","success");
				result.put("userInfo", uservo);
				return result;
			}
		}
	}
}
