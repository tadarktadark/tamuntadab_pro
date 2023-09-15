 package com.tdtd.tmtd.comm;

import java.util.HashMap;
import java.util.Map;

import com.tdtd.tmtd.vo.PagingVo;


/**
 * 게시물을 출력시 페이징 처리를 위한 PagingUtils
 * @author H.A Moon
 * @since 2023-08-25
 *
 */
public class PagingUtils {
	
	/**
	 * 페이지에 따라 출력할 범위(ROWNUM BETWEEN #{start} AND #{end})를 지정해줌. 
	 * @param page 현재 페이지 (화면에서 받아온 값)
	 * @param totalCount 데이터의 총 개수
	 * @param countList 한번에 출력될 게시글의 개수
	 * @param countPage 한번에 보일 페이지 그룹 개수
	 * @return result.get("start") = 시작row / result.get("end") = 끝row / result.get("page") = page 객체
	 */
	public static Map<String, Object> paging(String page, int totalCount, int countList, int countPage){
		Map<String, Object> result = new HashMap<>();
        
        if(page == null) {
            page="1";
        }
        
        int selectPage = Integer.parseInt(page);
        
        PagingVo p = new PagingVo();
        
        //총 게시물의 갯수
        p.setTotalCount(totalCount);
        
        //출력될 총 게시글의 갯수
        p.setCountList(countList);
        
        //화면에 몇개의 페이지그룹
        p.setCountPage(countPage);
        
        // 총 페이지의 갯수
         p.setTotalPage(p.getTotalCount());
         
         // 요청되는 페이지
         p.setPage(selectPage);
         
         // 시작, 끝 페이지 번호
         p.setStartPage(selectPage);
         p.setEndPage(selectPage);

         
         result.put("start", p.getPage()*p.getCountList()-(p.getCountList()-1));
         result.put("end", p.getPage()*p.getCountList());
         result.put("page", p);
         
         return result;
	}

}
