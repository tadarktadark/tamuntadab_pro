Handlebars.registerHelper('calculatePercentage', function(value) {
	    return value / 5.0 * 100;
	});


$(document).ready(function() {

 $(window).scroll(function() {
   if ($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
       if ($('#classTab').hasClass('active')) { // 클래스 이력 탭이 활성화되어 있는 경우
           loadMoreClasses(); // 클래스 이력 로드
       } else if ($('#reviewTab').hasClass('active')) { // 리뷰가 보이는 경우
       	   var order = $('#orderSelect').val();
           loadMoreReviews(order); // 리뷰 로드
       }
   }
});
// 정렬 방식 변경 시 처리
$('#orderSelect').change(function() {
	reviewPageNum = 1; // 페이지 번호 초기화
	hasMoreReviews = true; // 리뷰 로딩 가능 상태로 설정
	
	$('#reviewContent').empty(); // 기존 리뷰 내용 제거
	
	var order = $(this).val();
	
	loadMoreReviews(order, reviewPageNum, hasMoreReviews); 
});
	
	$('#content > div').hide();

		$('#profile').show();

		$('.nav-tabs a').click(function(e) {
			e.preventDefault();
			var tab_id = $(this).attr('href');
			
			$(".nav-item a").removeClass("active");
	
			$(this).addClass("active");

			$('#content > div').hide();

			$(tab_id).show();

			return false;
		});
	
    $('a[data-bs-target="#qrModal"]').on('click', function(e) {
        e.preventDefault();
        $("#qrcode").empty(); // 기존 QR 코드 삭제
        $("#qrcode").qrcode({
            text: '${simpleVo.inprSiteMobile}', // URL 값을 가져와서 QR 코드 생성
            width: 128,
            height: 128
        });
    });
    
     $('#careerTab').on('click', function() {
	var userAccountId = $('#userAccountId').val();
        $.ajax({
            url: './instrCareerDetail.do',
            method: 'GET',
            data:{userAccountId: userAccountId},
            success: function(data) {
                var source = $("#career-template").html();
                var template = Handlebars.compile(source);
                
                var html = template(data);
                
                $('#career').html(html).show();
            },
            error: function(err) {
               console.error(err);
           }
       });
   });
   
   $('#reviewTab').on('click', function(){
		var reviewPageNum = 1; // 현재 페이지 번호
		var hasMoreReviews = true; // 더 이상 로드할 리뷰가 있는지 여부
		var order = $('#orderSelect').val();
		$('#reviewContent').empty();
		loadMoreReviews(order, reviewPageNum, hasMoreReviews);
})

$('#classTab').on('click', function(){
	var pageNum = 1; // 현재 페이지 번호
	var hasMore = true;
	$('#classContent').empty();
	loadMoreClasses(pageNum, hasMore);
})
   
});

  function loadMoreClasses(pageNum, hasMore) {
	var userAccountId = $('#userAccountId').val();
		if (!hasMore) return;
        $.ajax({
            url: './instrClassDetail.do',
            method: 'GET',
            data: { userAccountId: userAccountId,
					start: (pageNum - 1) * 5 + 1,
                	end: pageNum * 5 }, 
            success: function(data) {
				var classVo = data.historyVo;
		        classVo.forEach(function(history) {
		            // subjCode 문자열을 JSON 배열로 변환
		            history.subjectVo[0].subjCode = JSON.parse(history.subjectVo[0].subjCode);
		        });
	
                var source = $("#class-history-template").html();
                var template = Handlebars.compile(source);
                
                var html = template(classVo);
                
                $('#classContent').append(html); // 새로운 내용 추가
                
                pageNum++; // 페이지 번호 증가
                
                hasMore = data.hasMore;
            },
            error: function(err) {
               console.error(err);
           }
       });
   }
 
function loadMoreReviews(order, reviewPageNum, hasMoreReviews) {
	var userAccountId = $('#userAccountId').val();
    if (!hasMoreReviews) return; // 더 이상 로드할 리뷰가 없으면 종료
	
	console.log("loadMoreReviews 보내는 값: ", order, userAccountId);
    $.ajax({
        url: './instrReviewDetail.do',
        method: 'GET',
        data: { userAccountId: userAccountId,
                start: (reviewPageNum - 1) * 5 + 1,
                end: reviewPageNum * 5,
                order: order }, 
        success: function(data) {
            var source = $("#review-template").html();
            var template = Handlebars.compile(source);
            
            var html = template(data.instrReviewVo); 
            
            $('#reviewContent').append(html); // 새로운 내용 추가
            
            reviewPageNum++; // 페이지 번호 증가

			hasMoreReviews = data.hasMore; // 다음 페이지의 존재 여부 업데이트
        },
        error: function(err) {
           console.error(err);
       }
   });
}
   