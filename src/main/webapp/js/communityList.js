$(document).ready(function(){
	getCommunityList("date",1);	
});

$(document).on("click", ".order-by", function(e){
	$(".order-by i").each(function(){
		$(this).removeClass("text-primary");
		$(this).addClass("text-muted");
	})
	$(e.target).children('i').removeClass("text-muted");
	$(e.target).children('i').addClass("text-primary");
	getCommunityList($(e.target).attr("id"),1);
});

$(document).on("click",".page-link",function(e){
	getCommunityList($(".order-by .text-primary").parent().attr("id"),$(e.target).attr("id"));	
})

function getCommunityList(orderBy, page){
	$.ajax({
		type:"post",
		url:"./getCommunityList.do",
		data:{
			"orderBy":orderBy,
			"page":page		
		},
		success: function(data){
			var board = data.board;
			var page = data.page;
			
			var color = ["success","danger","warning","info"];
			
			var boardSource = $("#board-list-template").html();
			var boardTemplate = Handlebars.compile(boardSource);
			var count = 0;
			
			$(".list-group").children().remove();
			
			for(i=0; i<board.length;i++){
				var value = JSON.parse(board[i].subjectCode);
				if(value==null){
					value="";
				}
				var subjects = [];
				for(j=0; j<value.length;j++){
					subjects.push({"color":color[count++%4],"subject":value[j]});
				}
				var boardData = {
					"id":board[i].id,
					"accountId":board[i].accountId,
					"heart":board[i].likeUser=="1"?"cancel":"do", 
					"title":board[i].title,
					"regdate":board[i].regdate,
					"likeCount":board[i].likeCount,
					"viewCount":board[i].viewCount,
					"subjects":subjects
				}
				var boardItem = boardTemplate(boardData);
				$(".list-group").append(boardItem);
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});	
}

function pageChange(startPage, endPage, page, totalPage){
	const pageList = []
	const preId = (startPage-5)<1?1:(startPage-5);
	const postId = (startPage+5)>totalPage?totalPage:(startPage+5);
	if(startPage!=1){		
		pageList.push({"id":1,"value":"<i id='1' class='mdi mdi-page-first'></i>"});
		pageList.push({"id":preId,"value":"<i id='"+preId+"' class='ri-arrow-left-s-line'></i>"});
	}
	for(i=startPage;i<endPage+1;i++){
		pageList.push({"id":i,"value":i,"state":i==page?"active":""});		
	}
	if(endPage<totalPage){		
		pageList.push({"id":postId,"value":"<i id='"+postId+"' class='ri-arrow-right-s-line'></i>"});
		pageList.push({"id":totalPage,"value":"<i id='"+totalPage+"' class='mdi mdi-page-last'></i>"});
	}
	
	var pageSource = $("#page-list-template").html();
	var pageTemplate = Handlebars.compile(pageSource);
	
	// Handlebars.js 템플릿 컴파일 시, htmlOrText 헬퍼 함수 등록
	Handlebars.registerHelper('htmlOrText', function(value) {
	    // value가 HTML 태그를 포함하는지 여부 확인
	    const containsHtmlTags = /<[a-z][\s\S]*>/i.test(value);
	
	    // value가 HTML 태그를 포함하면서 안전한 방법으로 출력하기 위해 {{{}}} 사용
	    if (containsHtmlTags) {
	        return new Handlebars.SafeString(value);
	    }
	
	    // value가 일반 문자열인 경우 {{}} 사용하여 출력
	    return value;
	});
	
	$(".pagination").children().remove();
	
	var pageData = {
		"page":pageList
	}
	
	var pageItem = pageTemplate(pageData);
	$(".pagination").append(pageItem);
}

$(document).on("click","#write-btn",function(e){
	if($(".dropdown-header")[0]!=undefined){
		location.href="./commynityWriteForm.do"
	} else {
		$("#sa-warning").click()
	}
});