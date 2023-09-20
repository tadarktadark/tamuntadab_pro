Handlebars.registerHelper('calculatePercentage', function(value) {
	    return value / 5.0 * 100;
	});
	
$(document).ready(function() {
$('#moreList').scroll(function() {
   if ($('#moreList').scrollTop() + $('#moreList').innerHeight() >= this.scrollHeight) {
       	if(hasMoreList){
			console.log("Scrolled API 실행");
            loadMoreList();
         }
   }
});
		
});
	
var isAjaxProcessing = false;
var hasMoreList = true;
var reviewPageNum = 2;

function loadMoreList() {
	var userAccountId = $('#userAccountId').val();
    if (!hasMoreList || isAjaxProcessing) return; // 더 이상 로드할 리뷰가 없으면 종료
	isAjaxProcessing = true;
	console.log("loadMoreList 보내는 값: ", userAccountId);
    $.ajax({
        url: './getMoreReview.do',
        method: 'GET',
        data: { userAccountId: userAccountId,
                start: (reviewPageNum - 1) * 5 + 1,
                end: reviewPageNum * 5,
               }, 
        success: function(data) {
            var source = $("#review-template").html();
            var template = Handlebars.compile(source);
            
            var html = template(data.lists); 
            
            $('#reviewContent').append(html); // 새로운 내용 추가
            
            hasMoreList = data.hasMore; // 다음 페이지의 존재 여부 업데이트
			if (hasMoreList){
				reviewPageNum++; // 페이지 번호 증가	
			}
			isAjaxProcessing = false;
            
        },
        error: function(err) {
           console.error(err);
           isAjaxProcessing = false;
       }
   });
}


function deleteReview(seq, clchId){
	$.ajax({
		url: "./deleteReview.do",
		method: "get",
		data: {seq : seq, clchId : clchId},
		success: function(response){
			 if (response.successMessage) {
				Swal.fire({
						        title: response.successMessage,
						        text: '',
						        icon: 'success',
						        customClass:{
						            confirmButton:'btn btn-primary w-xs mt-2'
						        }
						    }).then((result) => {
						       location.reload();
						});
		      }
		     if (response.errorMessage) {
				Swal.fire({
                            title: response.errorMessage,
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
			}
		},
		error: function(err){
			console.error(err);
			Swal.fire({
                            title: '에러발생',
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
		}
		
	})
}