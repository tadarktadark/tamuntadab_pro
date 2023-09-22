package com.tdtd.tmtd.model.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.model.mapper.IFileDao;
import com.tdtd.tmtd.model.mapper.IPilgiDao;
import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

@Service
public class PilgiServiceImpl implements IPilgiService {
	
	@Autowired
	private IPilgiDao dao;
	
	@Autowired
	private IFileService fSerivce;

	@Override
	public int getPilgiCount(Map<String, Object> map) {
		return dao.getPilgiCount(map);
	}

	@Override
	public List<BoardVo> getPilgiList(Map<String, Object> map) {
		return dao.getPilgiList(map);
	}

	@Override
	public BoardVo getPilgiDetail(Map<String, Object> map) {
		String accountId = (String)map.get("accountId");
		
		if(!accountId.equals("TMTD0")) {			
			String list = dao.getPilgiViewUser((String)map.get("id"));		
			Map<String, Object> view = LikeViewUtils.view(accountId, list);
			if((int)view.get("update")==1) {
				Map<String, Object> data = new HashMap<String, Object>(){{
					put("viewUser", view.get("list"));
					put("viewCount", view.get("count"));
					put("id", (String)map.get("id"));
				}};
				dao.updatePilgiViewUser(data);
			}
		}
		return dao.getPilgiDetail(map);
	}

	@Override
	public List<BoardVo> getYeongwanList(Map<String, Object> map) {
		return dao.getYeongwanList(map);
	}

	@Override
	public String getPilgiLikeUser(String id) {
		return dao.getPilgiLikeUser(id);
	}

	@Override
	public int updatePilgiLikeUser(Map<String, Object> map) {
		return dao.updatePilgiLikeUser(map);
	}

	@Override
	public int getMyPilgiCount(String accountId) {
		return dao.getMyPilgiCount(accountId);
	}

	@Override
	public List<BoardVo> getMyPilgiList(Map<String, Object> map) {
		return dao.getMyPilgiList(map);
	}

	@Override
	public int getLikePilgiCount(String accountId) {
		return dao.getLikePilgiCount(accountId);
	}

	@Override
	public List<BoardVo> getLikePilgiList(Map<String, Object> map) {
		return dao.getLikePilgiList(map);
	}

	@Override
	public ClassVo getPilgiClassDetail(String clasId) {
		return dao.getPilgiClassDetail(clasId);
	}

	@Override
	public int insertPilgi(BoardVo vo, Map<String, Object> map, MultipartFile[] files, HttpServletRequest request) throws IOException {
		int n = 0;
		n += dao.insertPilgi(vo);
		map.put("id", vo.getId());
		n += dao.updateClchPilgiState(map);
				
		if(files[0].getSize() > 0) {
			for (MultipartFile f : files) {
				Map<String, Object> fMap = fSerivce.fileSave(f, request);
				fMap.put("fileRefPk", vo.getId());
				fMap.put("fileOriginName", (String)fMap.get("originalName"));
				fMap.put("fileSaveName", (String)fMap.get("saveName"));
				n += fSerivce.insertFile(fMap);
			}
		}
		return (n>2)?1:0;
	}

	@Override
	public int insertPilgiImsi(BoardVo vo) {
		return dao.insertPilgiImsi(vo);
	}

	@Override
	public List<BoardVo> getPilgiImsiList(Map<String, Object> map) {
		return dao.getPilgiImsiList(map);
	}

	@Override
	public BoardVo getPilgiImsiDetail(String id) {
		return dao.getPilgiImsiDetail(id);
	}

	@Override
	public int deletePilgiImsi(String id) {
		return dao.deletePilgiImsi(id);
	}
	
	@Override
	public BoardVo getPilgiUpdateData(String id) {
		return dao.getPilgiUpdateData(id);
	}

	@Override
	public int updatePilgi(BoardVo vo, MultipartFile[] files, HttpServletRequest request) throws IOException {
		int n = 0;
		n += dao.updatePilgi(vo);
				
		if(files[0].getSize() > 0) {
			for (MultipartFile f : files) {
				Map<String, Object> fMap = fSerivce.fileSave(f, request);
				fMap.put("fileRefPk", vo.getId());
				fMap.put("fileOriginName", (String)fMap.get("originalName"));
				fMap.put("fileSaveName", (String)fMap.get("saveName"));
				n += fSerivce.insertFile(fMap);
			}
		}
		return (n>2)?1:0;
	}
	
	@Override
	public int deletePilgiFile(String save) {
		return dao.deletePilgiFile(save);
	}

	@Override
	public int updatePilgiState(Map<String, Object> bMap, Map<String, Object> cMap) {
		int n = 0;
		n += dao.updatePilgiState(bMap);
		n += dao.updateClchPilgiState(cMap);
		return (n==2)?1:0;
	}

	@Override
	public int deletePilgi(String id) {
		return dao.deletePilgi(id);
	}

}
