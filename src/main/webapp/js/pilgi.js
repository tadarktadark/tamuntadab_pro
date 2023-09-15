$(document).ready(function(){
	getPilgiList("date",1);	
});

$(document).on("change", "#order-by", function(){
	getPilgiList($("#order-by").val(),1);
	
});

$(document).on("click",".view-detail",function(e){
	location.href="./getPilgiDetail.do?id="+$(e.target).closest('.view-detail').attr("id");
});

$(document).on("click",".like-do",function(e){
	e.stopPropagation();
	$.ajax({
		type:"post",
		url:"./pilgiLikeUser.do",
		data:{
			"type":$(e.target).attr("src").split("_")[1].split(".")[0],
			"id":$(e.target).attr("id").split("_")[1]	
		},
		success: function(data){
			const src = e.target.getAttribute("src");
			const newSrc = src.substring(0, src.indexOf("_") + 1) + data.type + ".png";
			$(e.target).attr('src', newSrc);
			$(e.target).parent().next().find('span').text(data.count);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});	
})

$(document).on("click",".page-link",function(e){
	getPilgiList($("#order-by").val(),$(e.target).attr("id"));	
})

function getPilgiList(orderBy, page){
	$.ajax({
		type:"post",
		url:"./getPilgiList.do",
		data:{
			"orderBy":orderBy,
			"page":page		
		},
		success: function(data){
			var color = ["success","danger","warning","info"];
			
			var boardSource = $("#board-list-template").html();
			var boardTemplate = Handlebars.compile(boardSource);
			var count = 0;
			
			$(".list-group").children().remove();
			
			for(i=0; i<data.b.length;i++){
				var value = JSON.parse(data.b[i].subjectCode);
				var subjects = [];
				for(j=0; j<value.length;j++){
					subjects.push({"color":color[count++%4],"subject":value[j]});
				}
				var boardData = {
					"id":data.b[i].id,
					"accountId":data.b[i].accountId, 
					"heart":data.b[i].likeUser=="1"?"cancel":"do", 
					"title":data.b[i].title,
					"regdate":data.b[i].regdate,
					"likeCount":data.b[i].likeCount,
					"viewCount":data.b[i].viewCount,
					"subjects":subjects
				}
				var boardItem = boardTemplate(boardData);
				$(".list-group").append(boardItem);
			}
			pageChange(data.p.startPage, data.p.endPage, data.p.page, data.p.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});	
}

function pageChange(startPage, endPage, page, totalPage){
	const pageList = []
	if(startPage!=1){		
		pageList.push({"id":1,"value":"<i class='mdi mid-page-first'></i>"});
		pageList.push({"id":(startPage-5)<1?1:(startPage-5),"value":"<i class='ri-arrow-left-s-line'></i>"});
	}
	for(i=startPage;i<endPage+1;i++){
		pageList.push({"id":i,"value":i,"state":i==page?"active":""});		
	}
	if(endPage<totalPage){		
		pageList.push({"id":(startPage+5)>totalPage?totalPage:(startPage+5),"value":"<i class='ri-arrow-right-s-line'></i>"});
		pageList.push({"id":totalPage,"value":"<i class='mdi mid-page-last'></i>"});
	}
	
	var pageSource = $("#page-list-template").html();
	var pageTemplate = Handlebars.compile(pageSource);
	
	$(".pagination").children().remove();
	
	var pageData = {
		"page":pageList
	}
	
	var pageItem = pageTemplate(pageData);
	$(".pagination").append(pageItem);
}