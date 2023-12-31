Handlebars.registerHelper('calculatePercentage', function(value) {
	    return value / 5.0 * 100;
	});
	
$(document).ready(function() {
	console.log("myReview 실행")
$('#moreList').scroll(function() {
	console.log("scroll 실행");
	console.log("ScrollTop:", $('#moreList').scrollTop());
    console.log("InnerHeight:", $('#moreList').innerHeight());
    console.log("ScrollHeight:", this.scrollHeight);
   if ($('#moreList').scrollTop() + $('#moreList').innerHeight() >= this.scrollHeight - 10) {
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
            
            $('#moreList').append(html); // 새로운 내용 추가
            
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
    Swal.fire({
        title: "정말로 삭제하시겠습니까?",
        text: "기존의 데이터가 삭제됩니다.",
        icon: "warning",
        showCancelButton: true,
        customClass: {
            confirmButton: 'btn btn-primary w-xs mt-2 me-2',
            cancelButton: 'btn btn-danger w-xs mt-2',
        },
        confirmButtonText: "삭제",
        buttonsStyling: false,
        showCloseButton: true
    }).then((result) => {
      if (result.isConfirmed) {
          $.ajax({
              url: "./deleteReview.do",
              method: "get",
              data: {seq : seq, clchId : clchId},
              success(response){
                  if (response.successMessage) {
                      Swal.fire({
                          title: response.successMessage,
                          text:'',
                          icon:'success',
                          customClass:{
                              confirmButton:'btn btn-primary w-xs mt-2'
                          }
                      }).then((result) => {
                         location.reload();
                      });
                  }
                  if (response.errorMessage) {
                      Swal.fire({
                          title : response.errorMessage,
                          text:'',
                          icon:'error',
                          customClass:{
                              confirmButton:'btn btn-primary w-xs mt-2'
                         }
                     });
                 }
             },
             error(err){
                 console.error(err);
                 Swal.fire({
                     title:'에러 발생',
                     text:'',
                     icon:'error',
                     customClass:{
                         confirmButton:'btn btn-primary w-xs mt-2'
                     }
                 });
             } 
         })
      } 
   })
}