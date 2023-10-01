package com.tdtd.tmtd.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.mapper.IYeyakDao;
import com.tdtd.tmtd.vo.ClassVo;
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

//	@Override
//	public List<GangeuisilVo> getYeyakDateList(String gagaId) {
//		return dao.getYeyakDateList(gagaId);
//	}
//
//	@Override
//	public List<GangeuisilVo> getYeyakTimeList(String gagaId) {
//		return dao.getYeyakTimeList(gagaId);
//	}
	
	@Override
	public GangeuisilVo getYeoyuTime(String gagaId) {
		return dao.getYeoyuTime(gagaId);
	}

	@Override
	public int insertYeakInfo(YeyakVo yVo, GyeoljeVo gVo) {
		StringBuffer user = new StringBuffer();
		List<String> list = new ArrayList<String>();
		if(yVo.getGayeClasId()>0 && yVo.getGayeGyeoljeType()!=null) {
			list = dao.getYeyakGyeoljeAcountIdList(yVo.getGayeClasId());
			user.append("{");
			for (int i = 0; i < list.size()-1; i++) {
				user.append("\""+list.get(i)+"\":\"N\",");
			}
			user.append("\""+list.get(list.size()-1)+"\":\"N\"}");
		} else {
			list.add(yVo.getGayeAccountId());
			user.append("{\""+yVo.getGayeAccountId()+"\":\"N\"}");
		}
		yVo.setGayeGyeoljeUser(user.toString());			
		int n = dao.insertYeakInfo(yVo);
		
		GangeuisilVo gaVo = dao.getYeoyuTime(yVo.getGayeGagaId());
	   
	    Gson gson = new Gson();
	    Map<String, Object> yeoyuTime = gson.fromJson(gaVo.getGagaYeoyuTime(),Map.class);
	    List<String> oldList = (List<String>)yeoyuTime.get(yVo.getGayeYeyakDate());
	    List<String> newList = new ArrayList<String>();
	    int add = 0;
	    for (String s : oldList) {
			if(s.equals(yVo.getGayeStartTime())&&add!=yVo.getGayeHours()) {
				yVo.setGayeStartTime((Integer.parseInt(yVo.getGayeStartTime())+100)<1000?"0"+(Integer.parseInt(yVo.getGayeStartTime())+100):""+(Integer.parseInt(yVo.getGayeStartTime())+100));
				add++;
			} else {
				newList.add(s);
			}
		}
	    
	    yeoyuTime.put(yVo.getGayeYeyakDate(), newList);
	    Map<String, Object> update = new HashMap<String, Object>();
	    update.put("gagaId", yVo.getGayeGagaId());
	    update.put("gagaYeoyuTime", gson.toJson(yeoyuTime));
	    n += dao.updateYeoyuTime(update);
	    
		int m = 0;
		int total = gVo.getGyeoGeumaek();
		gVo.setGyeoGeumaek(gVo.getGyeoGeumaek()/list.size());
		gVo.setGyeoDaesangId(yVo.getGayeId());
		List<String> gyeoList = new ArrayList<String>();
		for (String id : list) {
			gVo.setGyeoAccountId(id);
			m += dao.insertYeakGyeoljeInfo(gVo);
			gyeoList.add(gVo.getGyeoId());
		}
		yVo.setGayeGyeoId(gson.toJson(gyeoList, List.class));
		n += dao.updateGyeoId(yVo);
		return (n>0||m>list.size()-1)?total-(gVo.getGyeoGeumaek()*list.size()):-1;
	}
	
	@Override
	public int getMyYeyakCount(String gayeAccountId) {
		return dao.getMyYeyakCount(gayeAccountId);
	}

	@Override
	public List<YeyakVo> getMyYeyakList(Map<String, Object> map) {
		return dao.getMyYeyakList(map);
	}

	@Override
	public int yeyakCancel(YeyakVo vo) {
		int n = 0;
		n += dao.updateYeyakDelflag(vo.getGayeId());
		
		GangeuisilVo gaVo = dao.getYeoyuTime(vo.getGayeGagaId());
		   
	    Gson gson = new Gson();
	    Map<String, Object> yeoyuTime = gson.fromJson(gaVo.getGagaYeoyuTime(),Map.class);
	    List<String> list = (List<String>)yeoyuTime.get(vo.getGayeYeyakDate());
	    
	    for (int i = 0; i < vo.getGayeHours(); i++) {
			list.add(vo.getGayeStartTime());
			String startTime = (Integer.parseInt(vo.getGayeStartTime())+100)<1000?"0"+(Integer.parseInt(vo.getGayeStartTime())+100):""+(Integer.parseInt(vo.getGayeStartTime())+100);
			System.out.println(startTime);
			vo.setGayeStartTime(startTime);
		}
	    
	    yeoyuTime.put(vo.getGayeYeyakDate(), list);
	    
	    Map<String, Object> update = new HashMap<String, Object>();
	    update.put("gagaId", vo.getGayeGagaId());
	    update.put("gagaYeoyuTime", gson.toJson(yeoyuTime));
	    
	    n += dao.updateYeoyuTime(update);
	    
	    String gyeoId = dao.getGyeoId(vo.getGayeId());
	    List<String> gyeoList = gson.fromJson(gyeoId, List.class);
	    
	    int m = 0;
	    for (String g : gyeoList) {
			m += dao.updateYeakGyeoljeStatus(g);
		}
	    
		return (n>0&&m>=gyeoList.size())?1:0;
	}
	
	@Override
	public void updateYeoyuTimeDaily() {
		List<String> gaga = dao.getAllGaebyeol();
		for (String ga : gaga) {
			GangeuisilVo gVo = dao.getYeoyuTime(ga);
			int open = Integer.parseInt(gVo.getGacoOpen());
			int close = Integer.parseInt(gVo.getGacoClose());
			List<String> times = new ArrayList<String>();
			String t = "";
			for(int i=open; i<close; i+=100) {
				t = String.valueOf(i);
				t = (t.length()==3)?"0"+t:t;
				times.add(t);
			}
			Gson gson = new Gson();
			Map<String, List<String>> dateMap = gson.fromJson(gVo.getGagaYeoyuTime(), Map.class);
			Calendar c = Calendar.getInstance(); 
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = "";
			c.add(Calendar.DATE, -1);
			date = format.format(c.getTime());
			dateMap.remove(date);
			c.add(Calendar.DATE, 29);
			date = format.format(c.getTime());
			dateMap.put(date, times);
			
			String yeoyuTime = gson.toJson(dateMap);
			Map<String,Object> update = new HashMap<String, Object>(); 
			update.put("gagaId", ga);
			update.put("gagaYeoyuTime", yeoyuTime);
			dao.updateYeoyuTime(update);
		}
	}
		
	@Override
	public List<ClassVo> getchamyeoClassList(String accountId) {
		return dao.getchamyeoClassList(accountId);
	}
}
