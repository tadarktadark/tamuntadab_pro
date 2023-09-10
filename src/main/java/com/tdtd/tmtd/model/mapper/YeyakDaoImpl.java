package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

@Repository
public class YeyakDaoImpl implements IYeyakDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int getGangeuisilCount(Map<String, Object> map) {
		return session.selectOne(NS+"getGangeuisilCount",map);
	}

	private final String NS = "com.tdtd.tmtd.model.mapper.YeyakDaoImpl.";
	
	@Override
	public List<String> getGangeuisilSidoList() {
		return session.selectList(NS+"getGangeuisilSidoList");
	}

	@Override
	public List<String> getGangeuisilSigunguList(String gacoSido) {
		return session.selectList(NS+"getGangeuisilSigunguList",gacoSido);
	}

	@Override
	public List<GangeuisilVo> getGangeuisilList(Map<String, Object> map) {
		return session.selectList(NS+"getGangeuisilList",map);
	}

	@Override
	public List<GangeuisilVo> getGangeuisilDetailList(String gagaGacoId) {
		return session.selectList(NS+"getGangeuisilDetailList",gagaGacoId);
	}

//	@Override
//	public List<GangeuisilVo> getYeyakDateList(String gagaId) {
//		return session.selectList(NS+"getYeyakDateList",gagaId);
//	}
//
//	@Override
//	public List<GangeuisilVo> getYeyakTimeList(String gagaId) {
//		return session.selectList(NS+"getYeyakTimeList",gagaId);
//	}
	
	@Override
	public GangeuisilVo getYeoyuTime(String gagaId) {
		return session.selectOne(NS+"getYeoyuTime",gagaId);
	}

	@Override
	public int insertYeakInfo(YeyakVo vo) {
		return session.insert(NS+"insertYeakInfo",vo);
	}

	@Override
	public List<String> getYeyakGyeoljeAcountIdList(int gayeClasId) {
		return session.selectList(NS+"getYeyakGyeoljeAcountIdList",gayeClasId);
	}
	
	@Override
	public int insertYeakGyeoljeInfo(GyeoljeVo vo) {
		return session.insert(NS+"insertYeakGyeoljeInfo",vo);
	}

	@Override
	public List<YeyakVo> getMyYeyakList(String gayeAccountId) {
		return session.selectList(NS+"getMyYeyakList",gayeAccountId);
	}

	@Override
	public int updateYeyakDelflag(String gayeId) {
		return session.update(NS+"updateYeyakDelflag",gayeId);
	}
	
	@Override
	public int updateYeoyuTime(Map<String, Object> map) {
		return session.update(NS+"updateYeoyuTime",map);
	}
	
	@Override
	public List<ClassVo> getchamyeoClassList(String accountId) {
		return session.selectList(NS+"getchamyeoClassList",accountId);
	}
	
	
}
