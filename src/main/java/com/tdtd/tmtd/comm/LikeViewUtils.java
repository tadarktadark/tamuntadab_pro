package com.tdtd.tmtd.comm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 좋아요, 조회수 기능 구현 LikeViewUtils
 * @author SoHyeon
 * @since 2023.09.14
 */
public class LikeViewUtils {

	/**
	 * 좋아요
	 * @param type cancel(좋아요 취소), do(좋아요, 조회수)
	 * @param accountId 좋아요 유저ID
	 * @param list 기존의 유저 리스트({"accountID":"yyyymmdd", ...})
	 * @return Map<String, Object><br>	
	 * "update" : 1 성공(업데이트 필요), 0 실패(좋아요 한 상태인데 또 좋아요 한 경우/취소한 상태인데 또 취소한 경우)<br>
	 * "type" : cancel(좋아요 된 상태), do(좋아요 취소된 상태)<br>
	 * "list" : 새로운 유저 리스트({"accountID":"yyyymmdd", ...}), update가 0인 경우에는 기존 유저 리스트<br>
	 * "count" : 좋아요 개수
	 */
	public static Map<String, Object> like(String type, String accountId, String list){
		Gson gson = new Gson();
		Map<String, Object> origin = gson.fromJson(list, Map.class);
		if(origin==null) {
			origin = new HashMap<String, Object>();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if(type.equals("cancel") && origin.containsKey(accountId)) {
			origin.remove(accountId);
			result.put("update", 1);			
		} else if(type.equals("do") && (!origin.containsKey(accountId))){
		    Calendar c = Calendar.getInstance(); 
		    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		    String date = format.format(c.getTime());
			origin.put(accountId,date);
			result.put("update", 1);			
		} else {
			result.put("update", 0);			
		}
		type=type.equals("cancel")?"do":"cancel";
		result.put("count", origin.size());
		result.put("type", type);
		result.put("list", gson.toJson(origin));
		return result;
	}
	
	/**
	 * 조회수
	 * @param accountId 조회한 유저ID
	 * @param list 기존의 유저 리스트({"accountID":"yyyymmdd", ...})
	 * @return Map<String, Object><br>	
	 * "update" : 1 성공(업데이트 필요), 0 실패(이미 조회한 유저)<br>
	 * "list" : 새로운 유저 리스트({"accountID":"yyyymmdd", ...}), update가 0인 경우에는 기존 유저 리스트<br>
	 * "count" : 조회수
	 */
	public static Map<String, Object> view(String accountId, String list){
		Gson gson = new Gson();
		Map<String, Object> origin = gson.fromJson(list, Map.class);
		if(origin==null) {
			origin = new HashMap<String, Object>();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if(!origin.containsKey(accountId)) {
			Calendar c = Calendar.getInstance(); 
		    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		    String date = format.format(c.getTime());
			origin.put(accountId,date);
			result.put("update", 1);			
		} else {
			result.put("update", 0);			
		}
		result.put("count", origin.size());
		result.put("list", gson.toJson(origin));
		return result;
	}
}
