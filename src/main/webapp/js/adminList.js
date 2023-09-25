$(document).ready(function(){
	getList(1,null);
});
function getList(page,orderBy){
	    $.ajax({
        url: './searchAdminList.do',
        type: 'GET', 
        data:{
			"page":page,
			"orderBy":orderBy,
		},
        dataType: 'json',
        success: function(data) {
            var source = $("#adminList-template").html(); 
            var template = Handlebars.compile(source); 
            var html = template(data);
            $('.adminList').append(html);
            pageChange(data.page.startPage,data.page.endPage,data.page.page,data.page.totalPage)
        },
        error: function(error) {
            console.error('Ajax 요청 실패:', error);
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
	
	var pageSource = $("#page-List-template").html();
	var pageTemplate = Handlebars.compile(pageSource);
	
	$(".pagination").children().remove();
	
	var pageData = {
		"page":pageList
	}
	var pageItem = pageTemplate(pageData);
	$(".pagination").append(pageItem);
}
$(document).on("click",".page-link",function(e){
	$(".adminList").empty();
	getList($(e.target).attr("id"));	
})