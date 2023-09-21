package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import com.tdtd.tmtd.vo.AdminVo;

public interface IAdminDao {
	
	public int checkIP(String accessIP);
	
	public AdminVo adminLogin(Map<String,Object> adminInput);
	
	public int updateAdminAccTime(AdminVo adminInfo);
}
