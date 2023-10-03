package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

@Repository
public class YeyakDaoImpl implements IYeyakDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.YeyakDaoImpl.";
	
	@Override
	public int getGangeuisilCount(Map<String, Object> map) {
		return session.selectOne(NS+"getGangeuisilCount",map);
	}
	
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
	
	@Override
	public int updateYeoyuTime(Map<String, Object> map) {
		return session.update(NS+"updateYeoyuTime",map);
	}
	
	@Override
	public GangeuisilVo getYeoyuTime(String gagaId) {
		return session.selectOne(NS+"getYeoyuTime",gagaId);
	}

	@Override
	public List<ClassVo> getchamyeoClassList(String accountId) {
		return session.selectList(NS+"getchamyeoClassList",accountId);
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
	public int insertYeakGyeoljeInfo(GeoljeVo vo) {
		return session.insert(NS+"insertYeakGyeoljeInfo",vo);
	}
	
	@Override
	public int updateGyeoId(YeyakVo vo) {
		return session.update(NS+"updateGyeoId",vo);
	}
	
	@Override
	public int getMyYeyakCount(String gayeAccountId) {
		return session.selectOne(NS+"getMyYeyakCount",gayeAccountId);
	}

	@Override
	public List<YeyakVo> getMyYeyakList(Map<String, Object> map) {
		return session.selectList(NS+"getMyYeyakList",map);
	}
	
	@Override
	public List<GeoljeVo> getGyeojeStatus(String gayeId) {
		return session.selectList(NS+"getGyeojeStatus",gayeId);
	}

	@Override
	public int updateYeyakDelflag(String gayeId) {
		return session.update(NS+"updateYeyakDelflag",gayeId);
	}
	
	@Override
	public String getGyeoId(String gayeId) {
		return session.selectOne(NS+"getGyeoId",gayeId);
	}
	
	@Override
	public int updateYeakGyeoljeStatus(String gyeoId) {
		return session.update(NS+"updateYeakGyeoljeStatus",gyeoId);
	}
	
	@Override
	public List<String> getAllGaebyeol() {
		return session.selectList(NS+"getAllGaebyeol");
	}
	
	@Override
	public List<String> getAllChamyeoja(String gayeId) {
		return session.selectList(NS+"getAllChamyeoja",gayeId);
	}
}
