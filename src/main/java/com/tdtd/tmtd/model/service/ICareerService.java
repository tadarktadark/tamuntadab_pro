package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.CareerVo;

public interface ICareerService {

		public String selectMaxIdToday(String datePrefix);
	
		//TDT008 insertCareer
		public int insertCareer(Map<String, Object> map);
		
		public int getMyCareerCount(String userAccountId);
		
		//TDT009 getMyCareerList
		public List<CareerVo> getMyCareerList(Map<String, Object> map);
		
		//TDT010 getCareerList
		public List<CareerVo> getCareerList(Map<String, Object> map);
		
		//TDT011 updateCareer
		public int updateCareer(CareerVo vo);
		
		//TDT012 updateCareerS
		//TDT013 updateCareerCert
		public int updateCareerS(Map<String, Object> map);
		
		//TDT014 updateCareerB
		public int updateCareerB(Map<String, Object> map);
		
		//TDT015 updateCareerD
		public int updateCareerD(String careId);
		
		//TDT016 deleteCareer
		public int deleteCareer(String careId);
		
		//TDT023 getOneInstrCareer
		public CareerVo getOneInstrCareer(String userAccountId);
	
}
