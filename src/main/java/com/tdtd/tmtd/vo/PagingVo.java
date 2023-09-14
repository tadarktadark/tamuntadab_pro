package com.tdtd.tmtd.vo;

public class PagingVo {
	
	private int page; // 화면에서 선택된 페이지의 숫자
	private int countList;// 한 페이지에 보여지는 게시글의 갯수
	/*
	 * 전체 글의 갯수가 17, 한 페이지에 8개의 row를 보여준다면
	 * 1page|2page : 8개, 3page : 1개
	 */
	private int totalCount; // 총 게시글의 갯수
	
	private int countPage; // 화면에 몇 개의 페이지 그룹을 보여줄지
	private int totalPage; // 게시글에서 연산된 보여줘야 할 총 page의 갯수
	
	private int startPage; // 현재 페이지의 시작 번호
	private int endPage; // 현재 페이지의 끝 번호
	
	public PagingVo() {
		
	}

	@Override
	public String toString() {
		return "Paging [page=" + page + ", countList=" + countList + ", totalCount=" + totalCount + ", countPage="
				+ countPage + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		// page의 갯수보다 큰 값을 url에 parameter로 전송받는다면 끝 page로 이동
		// 끝 페이지는 totalPage
		if(page<=1) {
			this.page = 1;
		} else if(page>totalPage) {
			this.page = totalPage;
		} else {
			this.page = page;			
		}
	}

	public int getCountList() {
		return countList;
	}

	public void setCountList(int countList) {
		this.countList = countList;
	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		/*
		 * 총 게시글의 갯수(totalCount)/페이지당 보여줄 글 갯수(countList)
		 * if) totalCount==27 && countList==9 -> totalPage==3
		 * 						&& countList==8 -> totalPage==4
		 * 나머지가 있다면 +1의 결과가 totalPage
		 */
		int totalPageResult = totalCount/countList;
		this.totalPage = (totalCount%countList!=0)?totalPageResult+1:totalPageResult;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		/*
		 * 화면에서 보여지는 페이지 그룹의 시작 --- 1 2 3 4 5 --- => 1
		 * ex) 현재 페이지 번호가 3 => 현재 그룹의 페이지의 시작은 1
		 * 		보이는 페이지의 개수와 같은 범위라면 다음 페이지로 넘어가야 함
		 * 		현재 페이지에 -1
		 * 
		 *  	현재 페이지 3-1 화면에 보여지는 페이지 3
		 *  	0*3
		 *  
		 *  	최종 연산 흐름 : 연산은 몫을 계산하기 위해서 -1, 화면에 출력될 때는 +1
		 */
		
		int startPageResult = ((page-1)/countPage)*countPage+1;
								/*
								 * --- 1 2 3 ---
								 * ((현재페이지-1)/페이지그룹)*페이지그룹+1 == 시작번호 1
								 * 	1) ((1-1)/3)*3+1 == 1
								 * 	2) ((2-1)/3)*3+1 == 1
								 *  3) ((3-1)/3)*3+1 == 1 
								 */
		this.startPage = startPageResult;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		/*
		 * 화면에 보이는 끝 페이지 번호
		 * 화면에 보이는 시작 페이지 4 라면
		 * 화면에 보이는 끝 페이지는 countPage를 더하면 for(int s=4; s<4+3; s++)이 되기 때문에 6
		 * 
		 * 연산된 끝 페이지의 번호가 글의 총 갯수 페이지보다 크다면 최대 크기는 총 갯수의 페이지
		 */
		int endPageResult = startPage+countPage-1;
		this.endPage = endPageResult>totalPage?totalPage:endPageResult;
	}

}
