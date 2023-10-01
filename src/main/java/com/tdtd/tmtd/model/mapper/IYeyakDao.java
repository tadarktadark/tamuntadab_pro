package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

public interface IYeyakDao {

	public int getGangeuisilCount(Map<String, Object> map);
	
	public List<String> getGangeuisilSidoList();

	public List<String> getGangeuisilSigunguList(String gacoSido);

	public List<GangeuisilVo> getGangeuisilList(Map<String, Object> map);

	public List<GangeuisilVo> getGangeuisilDetailList(String gagaGacoId);

//	public List<GangeuisilVo> getYeyakDateList(String gagaId);
//
//	public List<GangeuisilVo> getYeyakTimeList(String gagaId);

	public GangeuisilVo getYeoyuTime(String gagaId);
	
	public int insertYeakInfo(YeyakVo vo);
	
	public List<String> getYeyakGyeoljeAcountIdList(int gayeClasId);

	public int insertYeakGyeoljeInfo(GeoljeVo vo);
	
	public int updateGyeoId(YeyakVo vo);

	public int getMyYeyakCount(String gayeAccountId);
	
	public List<YeyakVo> getMyYeyakList(Map<String, Object> map);
	
	public List<GeoljeVo> getGyeojeStatus(String gayeId);

	public int updateYeyakDelflag(String gayeId);
	
	public String getGyeoId(String gayeId);
	
	public int updateYeakGyeoljeStatus(String gyeoId);
	
	public List<String> getAllGaebyeol();
	
	public int updateYeoyuTime(Map<String, Object> map);
	
	public List<ClassVo> getchamyeoClassList(String accountId);
	
}
