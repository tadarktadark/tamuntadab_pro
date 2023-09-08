package com.tdtd.tmtd.model.mapper;

import java.util.Map;

public interface ICommUserDao {

	public Boolean emailCheck(Map<String,String> map);
	
	public Boolean searchNickName(String userNickName);
}
