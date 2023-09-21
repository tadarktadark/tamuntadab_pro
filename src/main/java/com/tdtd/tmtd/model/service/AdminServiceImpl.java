package com.tdtd.tmtd.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IAdminDao;
import com.tdtd.tmtd.vo.AdminVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao;
	
	@Override
	public int checkIP(String accessIP) {
		return adminDao.checkIP(accessIP);
	}

	@Override
	public AdminVo adminLogin(Map<String, Object> adminInput) {
		return adminDao.adminLogin(adminInput);
	}

	@Override
	public int updateAdminAccTime(AdminVo adminInfo) {
		return adminDao.updateAdminAccTime(adminInfo);
	}
	@Override
	public int updateAdminPw(AdminVo adminInfo, Map<String, Object> adminInput) {
		int n = adminDao.updateAdminPw(adminInput);
		int m = adminDao.updateAdminAccTime(adminInfo);
		return (n>0||m>0)?1:0;
	}
}
