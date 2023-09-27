var filter = {
    userGender: undefined,
    userSite: undefined,
    userDelflag: undefined,
    userJeongJiSangTae: undefined,
    userAuth: undefined
};

var search ={
	tag : undefined,
	value : undefined
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
		getList(filter,null,1);
	});
	$(".comfirm-filter").on('click',function(){
	 filter.userGender = $('.filter-gender:checked').val();
	 filter.userAuth = $('.filter-auth:checked').val();
	 filter.userSite = $('.filter-site:checked').val();
	 filter.userDelflag = $('.filter-delflag:checked').val();
	 filter.userJeongJiSangTae = $('.filter-jeongji:checked').val();
	 getList(filter,null,1);
	});
	$(".search").on('click',function(){
		search.tag=$('.tag').val();
		search.value=$('.search-value').val();
		if(search.value != null){
			console.log(search.tag);
			console.log(search.value);
		}else{
			print('검색어를 입력해주세요');
		}
	});
});

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
			"tag":search.tag,
			"value":search.value,
			"page":page
		},
        dataType: 'json',
        success: function(data) {
				var source = $("#userList-template").html(); 
				var template = Handlebars.compile(source); 
				var html = template(data);
				$(".userList").empty();
				$('.userList').append(html);
//	            pageChange(data.page.startPage,data.page.endPage,data.page.page,data.page.totalPage)
        },
        error: function(error) {
            console.error('Ajax 요청 실패:', error);
        }
    });
}

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