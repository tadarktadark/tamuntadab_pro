package com.tdtd.tmtd.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IAdminDao;
import com.tdtd.tmtd.vo.AdminVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Service
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

	@Override
	public List<AdminVo> getAdminList(Map<String, Object> map) {
		return adminDao.getAdminList(map);
	}
	@Override
	public int countAdmin(Map<String,Object>map) {
		return adminDao.countAdmin(map);
	}

	@Override
	public List<UserProfileVo> getUserList(Map<String, Object> map) {
		return adminDao.getUserList(map);
	}

	@Override
	public int countUser(Map<String, Object> map) {
		return adminDao.countUser(map);
	}

	@Override
	public UserProfileVo getuserDetail(String userId) {
		return adminDao.getuserDetail(userId);
	}

	@Override
	public int setuserJeongji(Map<String, Object> map) {
		int n = adminDao.setuserJeongji(map);
		int m = adminDao.updatesisu(map);
		return (n+m)>1?1:0;
	}

	@Override
	public int addAdmin(Map<String, Object> map) {
		return adminDao.addAdmin(map);
	}

	@Override
	public int addIp(Map<String, Object> map) {
		return adminDao.addIP(map);
	}

	@Override
	public int adminIdCheck(Map<String, Object> map) {
		return adminDao.adminIdCheck(map);
	}

	@Override
	public Map<String,Object> adminDetail(String userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("adminVo", adminDao.adminDetail(userId));
		map.put("adminIp", adminDao.adminipDetail(userId));
		return map;
	}

	@Override
	public int delAdmin(String adminId) {
		int n = adminDao.delAdmin(adminId);
		n += adminDao.delAdmin_IP(adminId);
		return n>0?1:0;
	}

	@Override
	public int delIP(Map<String, Object> map) {
		return adminDao.delIP(map);
	}

	@Override
	public int restoreAdmin(String adminId) {
		return adminDao.restoreAdmin(adminId);
	}

	@Override
	public int resetAdminPw(String adminId) {
		return adminDao.resetAdminPw(adminId);
	}

}
