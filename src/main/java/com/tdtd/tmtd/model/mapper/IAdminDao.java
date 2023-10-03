package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.AdminVo;
import com.tdtd.tmtd.vo.UserProfileVo;

public interface IAdminDao {
	
	public int checkIP(String accessIP);
	
	public AdminVo adminLogin(Map<String,Object> adminInput);
	
	public int updateAdminAccTime(AdminVo adminInfo);
	
	public int updateAdminPw(Map<String,Object> setPassword);
	
	public List<AdminVo> getAdminList(Map<String,Object> map);
	
	public int countAdmin(Map<String,Object>map);
	
	public List<UserProfileVo> getUserList(Map<String,Object> map);

	public int countUser(Map<String, Object> map);
	
	public UserProfileVo getuserDetail(String userId);
	
	public int setuserJeongji(Map<String,Object> map);
	
	public int updatesisu(Map<String,Object> map);
	
	public int addAdmin(Map<String,Object> map);
	
	public int addIP(Map<String,Object> map);
	
	public AdminVo adminDetail(String userId);
	
	public List<AdminVo> adminipDetail(String userId);
	
	public int adminIdCheck(Map<String,Object> map);
	
	public int delAdmin(String adminId);
	
	public int delAdmin_IP(String adminId);
	
	public int delIP(Map<String,Object> map);
	
	public int restoreAdmin(String adminId);
	
	public int resetAdminPw(String adminId);
	
}
