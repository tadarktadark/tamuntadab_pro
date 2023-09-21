package com.tdtd.tmtd.model.service;

import java.util.Map;

import com.tdtd.tmtd.vo.AdminVo;

public interface IAdminService {
	
	public int checkIP(String accessIP);
	
	public AdminVo adminLogin(Map<String,Object> adminInput);
	
	public int updateAdminAccTime(AdminVo adminInfo);
	
	public int updateAdminPw(AdminVo adminInfo, Map<String,Object> adminInput);
}
