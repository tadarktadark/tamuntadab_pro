/**
	사용자 리스트를 비동기 식으로 불러오는 기능
 */
 
 // 필터처리를 위한 변수 선언
var filter = {
    userGender: undefined,
    userSite: undefined,
    userDelflag: undefined,
    userJeongJiSangTae: undefined,
    siusState: undefined,
    userAuth: undefined,
    column: undefined,
    value:	undefined
};

// 검색 기능을 위한 변수 선언
var search ={
	tag : undefined,
	searchValue : undefined
};

$(document).ready(function(){
	// 첫 화면에서 일단 모든 사용자 리스트를 불러온다
	getList(filter, search, 1);
	
	//초기화 버튼 클릭시 모든 기능을 초기화 시키고 값을 불러오는 기능 실행
	$(".remove-filter").on('click',function(){
		 $('.filter').prop('checked', false);
		 filter = {
		    userGender: undefined,
		    userSite: undefined,
		    userDelflag: undefined,
		    userJeongJiSangTae: undefined,
		    siusState: undefined,
		    userAuth: undefined,
	      	column: undefined,
   	 		value:	undefined
			};
		search={
			tag : undefined,
			searchvalue : undefined
		};
		getList(filter,search,1);
	});
	
	// 적용 버튼 클릭시 해당 값을 받아와 필터를 적용시키고 값을 불러오는 기능 실행
	$(".comfirm-filter").on('click',function(){
	filter.userGender = $('.filter-gender:checked').val();
	filter.userAuth = $('.filter-auth:checked').val();
	filter.userSite = $('.filter-site:checked').val();
	filter.userDelflag = $('.filter-delflag:checked').val();
	filter.userJeongJiSangTae = $('.filter-jeongji:checked').val();
	filter.siusState = $('.filter-siusState:checked').val();
	if($(".filter-sort:checked").val() != null){
		var sort = JSON.parse($(".filter-sort:checked").val());
		filter.column = sort.column;
		filter.value = sort.value;
	}else{
		filter.column = undefined;
		filter.value = undefined;
	}
	 search={
			tag : undefined,
			searchvalue : undefined
		};
	 getList(filter,search,1);
	});
	
	// 검색어와 검색 유형을 적용시키고 불러오는 기능
	$(".search").on('click',function(){
		search.tag=$('.tag').val();
		search.searchValue=$('.search-value').val();
		if(search.searchValue != ''){
			$('.search-value').val("");
			getList(filter,search,1);			
		}else{
			$('.search-value').val("");
			search.searchValue= undefined;
			search.tag= undefined;
			getList(filter,search,1);
		}
	});
});

// 비동기식 처리를 위한 function이며 해당 메소드 실행시 핸들바 js를 동작시켜 값을 뿌러준다.
function getList(filter,search,page){
	    $.ajax({
        url: './getUserList.do',
        type: 'GET', 
        data:{
			"userGender":filter.userGender,
			"userAuth":filter.userAuth,
			"userSite":filter.userSite,
			"userDelflag":filter.userDelflag,
			"userJeongJiSangTae":filter.userJeongJiSangTae,
			"siusState":filter.siusState,
			"column":filter.column,
			"value":filter.value,
			"tag":search.tag,
			"searchValue":search.searchValue,
			"page":page
		},
        dataType: 'json',
        success: function(data) {
				var source = $("#userList-template").html(); 
				var template = Handlebars.compile(source); 
				var html = template(data);
				$(".userList").empty();
				$('.userList').append(html);
	            pageChange(data.page.startPage,data.page.endPage,data.page.page,data.page.totalPage)
        },
        error: function(error) {
            console.error('Ajax 요청 실패:', error);
        }
    });
}

//변경된 페이징을 적용시키는 메소드
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
	getList(filter,search,$(e.target).attr("id"));	
})

function print(val){
		Swal.fire({
		position: 'top-center',
		icon: 'warning',
		title: val,
		showConfirmButton: false,
		timer: 1500,
		showCloseButton: true
	})
}