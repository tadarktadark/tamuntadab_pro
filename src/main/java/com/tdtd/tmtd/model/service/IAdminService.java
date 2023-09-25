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
	
	public int countAdmin();
	
	public List<UserProfileVo> getUserList(Map<String,Object> map);
}
