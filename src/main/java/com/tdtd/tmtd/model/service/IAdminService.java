package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.AdminVo;
import com.tdtd.tmtd.vo.UserProfileVo;

public interface IAdminService {
	
	public int checkIP(String accessIP);
	
	public AdminVo adminLogin(Map<String,Object> adminInput);
	
	public int updateAdminAccTime(AdminVo adminInfo);
	
	public int updateAdminPw(AdminVo adminInfo, Map<String,Object> adminInput);
	
	public List<AdminVo> getAdminList(Map<String,Object> map);
	
	public int countAdmin(Map<String,Object>map);
	
	public List<UserProfileVo> getUserList(Map<String,Object> map);

	public int countUser(Map<String, Object> map);
	
	public UserProfileVo getuserDetail(String userId);
	
	public int setuserJeongji(Map<String,Object> map);
	
	public int addAdmin(Map<String,Object> map);
	
	public int addIp(Map<String,Object> map);
	
	public int adminIdCheck(Map<String,Object>map);
	
	public Map<String,Object> adminDetail(String userId);
	
	public int delAdmin(String adminId);
	
	public int delIP(Map<String,Object> map);
	
	public int restoreAdmin(String adminId);
	
	public int resetAdminPw(String adminId);
	
}
