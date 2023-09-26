var filter = {
    userGender: undefined,
    userSite: undefined,
    userDelflag: undefined,
    userJeongJiSangTae: undefined,
    userAuth: 'S'
};

var search ={
	keyword : undefined,
	type : undefined
};

$(document).ready(function(){
	getList(filter, search, 1);
	
	$(".remove-filter").on('click',function(){
		 $('.filter').prop('checked', false);
		 filter = {
		    userGender: undefined,
		    userSite: undefined,
		    userDelflag: undefined,
		    userJeongJiSangTae: undefined,
		    userAuth: undefined
			};
	});
	$(".comfirm-filter").on('click',function(){
	 filter.userGender = $('.filter-gender:checked').val();
	 filter.userAuth = $('.filter-auth:checked').val();
	 filter.userSite = $('.filter-site:checked').val();
	 filter.userDelflag = $('.filter-delflag:checked').val();
	 filter.userJeongJiSangTae = $('.filter-jeongji:checked').val();
	});
});

function getList(filter,search,page){
	    $.ajax({
        url: './getUserList.do',
        type: 'GET', 
        data:{
			"filter":filter,
			"search":search,
			"page":page
		},
        dataType: 'json',
        success: function(data) {
		 var source = $("#userList-template").html(); 
	            var template = Handlebars.compile(source); 
	            var html = template(data);
	            $('.userList').append(html);
//	            pageChange(data.page.startPage,data.page.endPage,data.page.page,data.page.totalPage)
        },
        error: function(error) {
            console.error('Ajax 요청 실패:', error);
        }
    });
}