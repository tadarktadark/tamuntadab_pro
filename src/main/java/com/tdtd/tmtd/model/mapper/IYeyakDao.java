package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

public interface IYeyakDao {

	public int getGangeuisilCount(Map<String, Object> map);
	
	public List<String> getGangeuisilSidoList();

	public List<String> getGangeuisilSigunguList(String gacoSido);

	public List<GangeuisilVo> getGangeuisilList(Map<String, Object> map);

	public List<GangeuisilVo> getGangeuisilDetailList(String gagaGacoId);

	public List<YeyakVo> getYeyakDateList(String gayeGagaId);

	public List<YeyakVo> getYeyakTimeList(Map<String, Object> map);

	public int insertYeakInfo(YeyakVo vo);
	
	public List<String> getYeyakGyeoljeAcountIdList(int gayeClasId);

	public int insertYeakGyeoljeInfo(GyeoljeVo vo);

	public List<YeyakVo> getMyYeyakList(String gayeAccountId);

	public int updateYeyakDelflag(String gayeId);
}
