package com.tdtd.tmtd.model.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IYeyakDao;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

@Service
public class YeyakServiceImpl implements IYeyakService {

	@Autowired
	private IYeyakDao dao;
	
	@Override
	public int getGangeuisilCount(Map<String, Object> map) {
		return dao.getGangeuisilCount(map);
	}
	
	@Override
	public List<String> getGangeuisilSidoList() {
		return dao.getGangeuisilSidoList();
	}

	@Override
	public List<String> getGangeuisilSigunguList(String gacoSido) {
		return dao.getGangeuisilSigunguList(gacoSido);
	}

	@Override
	public List<GangeuisilVo> getGangeuisilList(Map<String, Object> map) {
		return dao.getGangeuisilList(map);
	}

	@Override
	public List<GangeuisilVo> getGangeuisilDetailList(String gagaGacoId) {
		return dao.getGangeuisilDetailList(gagaGacoId);
	}

	@Override
	public List<YeyakVo> getYeyakDateList(String gayeGagaId) {
		return dao.getYeyakDateList(gayeGagaId);
	}

	@Override
	public List<YeyakVo> getYeyakTimeList(Map<String, Object> map) {
		return dao.getYeyakTimeList(map);
	}

	@Override
	public int insertYeakInfo(YeyakVo yVo, GyeoljeVo gVo) {
		List<String> list = dao.getYeyakGyeoljeAcountIdList(yVo.getGayeClasId());
		StringBuffer user = new StringBuffer();
		user.append("{");
		for (int i = 0; i < list.size()-1; i++) {
			user.append("\""+list.get(i)+"\":\"N\",");
		}
		user.append("\""+list.get(list.size()-1)+"\":\"N\"}");
		yVo.setGayeGyeoljeUser(user.toString());
		int n = dao.insertYeakInfo(yVo);
		int m = 0;
		gVo.setGyeoGeumaek(gVo.getGyeoGeumaek()/list.size());
		gVo.setGyeoDaesangId(yVo.getGayeId());
		for (String id : list) {
			gVo.setGyeoAccountId(id);
			m += dao.insertYeakGyeoljeInfo(gVo);
		}
		return (n>0||m>list.size())?gVo.getGyeoGeumaek()*list.size():-1;
	}

	@Override
	public List<YeyakVo> getMyYeyakList(String gayeAccountId) {
		return dao.getMyYeyakList(gayeAccountId);
	}

	@Override
	public int updateYeyakDelflag(String gayeId) {
		return dao.updateYeyakDelflag(gayeId);
	}

}
