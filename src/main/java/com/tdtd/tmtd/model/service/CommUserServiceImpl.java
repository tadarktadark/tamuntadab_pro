package com.tdtd.tmtd.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdtd.tmtd.model.mapper.ICommUserDao;
import com.tdtd.tmtd.model.mapper.IInstrDao;
import com.tdtd.tmtd.vo.UserProfileVo;

@Service
public class CommUserServiceImpl implements ICommUserService {
	
	@Autowired
	private ICommUserDao cdao;
	
	@Autowired
	private IInstrDao idao;
	
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
	
	@Transactional
	@Override
	public Map<String, Object> commLogin(Map<String, String> userInput) {
		Map<String,Object> result = new HashMap<String, Object>();
		if(cdao.commLogin(userInput) !=null) {
			UserProfileVo uservo = cdao.commLogin(userInput);
			if(uservo.getUserChadanRegistDate() != null) {
				//사용자가 로그인을 성공 했지만 해당 유저가 차단되어 있는 상태일 경우
				String time = cdao.checkUserChadanDate(uservo.getUserEmail()); 
				if(time.equals("782898")) {
					if(uservo.getUserDelflag().equals("H")) {
						result.put("status","human");
						return result;
					}else{
					cdao.restoreUserChadanDate(uservo.getUserEmail());
					result.put("status","success");
					result.put("userInfo", uservo);
					cdao.restoreUserChadanDate(uservo.getUserEmail());
					cdao.restoreUserChadanCount(uservo);
					cdao.updateTime(uservo);
					}
				}else {
					result.put("status", "printDate");
					result.put("time", time);
				}
				return result;
			}else{
				if(uservo.getUserDelflag().equals("H")) {
					result.put("status","human");
					return result;
				}else {
				result.put("status","success");
				result.put("userInfo", uservo);
				cdao.restoreUserChadanDate(uservo.getUserEmail());
				cdao.restoreUserChadanCount(uservo);
				cdao.updateTime(uservo);
				return result;
				}
			}
		}else {
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
					int updateResult = cdao.updateUserChadanDate(userInput.get("userEmail"));
					if(updateResult>0) {
						result.put("status", "chadan");
						result.put("chadanCnt", cnt+1);
					}
				}
				return result;
			}
		}
	}

	@Override
	public int updateTime(UserProfileVo userInfo) {
		int timenum = cdao.updateTime(userInfo);
		int cntnum = cdao.updateChadanCnt(userInfo.getUserAccountId());
		return timenum + cntnum ==2 ? 1:0;
	}

	@Override
	public UserProfileVo autoLogin(String userAutoLoginToken) {
		return cdao.autoLogin(userAutoLoginToken);
	}

	@Override
	public int updateUserInfo(Map<String, Object> updateInfo) {
		return cdao.updateUserInfo(updateInfo);
	}

	@Override
	public int updateResetPwToken(Map<String, Object> resetPassword) {
		return cdao.updateResetPwToken(resetPassword);
	}

	@Override
	public int updateUserPassword(Map<String, Object> resetPassword) {
		int n = cdao.updateUserPassword(resetPassword);
		if(n>0) {
			n += cdao.deleteResetPwToken(resetPassword);
		}
		return n==2?1:0;
	}
	
	@Override
	public int checkPassword(Map<String, Object> resetPassword) {
		return cdao.checkPassword(resetPassword);
	}

	@Override
	public String searchNickName(Map<String,Object> map) {
		boolean isc = cdao.searchNickName(map);
		if (isc==false) {
			boolean hasProfile = idao.hasInprProfile(map);
			if(hasProfile) {
				int m = idao.updateInprRegdate(map);
				if(m == 0) {
					return "false";
				}
			}
			
			int n = cdao.updateUserInfo(map);
			
			if(n>0) {
				return "true";
			}else {
				return "false";
			}
			
		}
		return "false";
	}

	@Transactional
	@Override
	public int updatedelflag(Map<String, Object> userToken) {
		int n = cdao.updatedelflag(userToken);
		if(n>0) {
			n += cdao.deleteResetPwToken(userToken);
		}
		return n>1?1:0;
	}

	@Transactional
	@Override
	public int updateUserDelflagToY(UserProfileVo vo) {
		int n = cdao.insertUserDelTable(vo);
		int m = cdao.updateUserDelflagToY(vo);
		return (n+m)>1?1:0;
	}
	
	@Transactional
	@Override
	public String checkPayment(UserProfileVo vo) {
		if(vo.getUserAuth().equals("S")) {
			int n = cdao.searchUserGyeolje(vo);
			if(n>0) {
				return "false";
			}else {
				return "true";
			}
		}else{
			int n = cdao.searchUserJeongSan(vo);
			if(n>0) {
				return "false";
			}else {
				return "true";
			}
		}
	}

	@Override
	public String jeongjidate(UserProfileVo userInfo) {
		return cdao.jeongjidate(userInfo);
	}

}