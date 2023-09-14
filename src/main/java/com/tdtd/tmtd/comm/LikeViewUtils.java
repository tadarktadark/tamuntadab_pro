package com.tdtd.tmtd.comm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 좋아요, 조회수를 위한 LikeViewUtils
 * @author SoHyeon
 * @since 2023.09.14
 */
public class LikeViewUtils {

	/**
	 * 좋아요, 조회수 성공 여부 및 유저 리스트 반환
	 * @param type cancel(좋아요 취소), do(좋아요, 조회수)
	 * @param accountId 좋아요/조회한 유저ID
	 * @param list 기존의 유저 리스트({"accountID":"yyyymmdd", ...})
	 * @return Map<String, Object>	"result" : 1 성공, 0 실패(좋아요 한 상태인데 또 좋아요 한 경우/취소한 상태인데 또 취소한 경우)
	 * 								"list" : 새로운 유저 리스트({"accountID":"yyyymmdd", ...}), 0인 경우에는 기존 유저 리스트
	 */
	public static Map<String, Object> likeView(String type, String accountId, String list){
		Gson gson = new Gson();
		Map<String, Object> origin = gson.fromJson(list, Map.class);
		Map<String, Object> result = new HashMap<String, Object>();
		if(type.equals("cancel") && origin.containsKey(accountId)) {
			origin.remove(accountId);
			result.put("result", 1);
		} else if(type.equals("do") && !origin.containsKey(accountId)){
		    Calendar c = Calendar.getInstance(); 
		    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		    String date = format.format(c.getTime());
			origin.put(accountId,date);
			result.put("result", 1);
		} else {
			result.put("result", 0);
		};
		result.put("list", gson.toJson(origin));
		return result;
	}
}
