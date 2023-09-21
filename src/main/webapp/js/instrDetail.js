Handlebars.registerHelper('calculatePercentage', function(value) {
	    return value / 5.0 * 100;
	});

//채팅 버튼 반응형 사이즈 지정
window.addEventListener("resize", function(){
	var chatting = $('#chatting');
	var xPos = ($(window).width() / 1.3);
	var yPos = ($(window).height() / 6.5);
	    chatting.css({ "left": xPos, "top": yPos });
})

$(document).ready(function() {
	
	//해당 강사의 좋아요, 조회수 숫자 반환
	currentCountChange();
	
	var chatting = $('#chatting');
	var xPos = ($(window).width() / 1.3);
	var yPos = ($(window).height() / 6.5);
	    chatting.css({ "left": xPos, "top": yPos });
	
//스크롤 페이징
 $(window).scroll(function() {
   if ($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
       if ($('#classTab').hasClass('active')) { // 클래스 이력 탭이 활성화되어 있는 경우
       	if(hasMoreClass){
           loadMoreClasses(); // 클래스 이력 로드
         }
       } else if ($('#reviewTab').hasClass('active')) { // 리뷰가 보이는 경우
       	if(hasMoreReviews){
       	   var order = $('#orderSelect').val();
           loadMoreReviews(order); //리뷰 로드
          }
       }
   }
});

// 정렬 방식 변경 시 처리
$('#orderSelect').change(function() {
	reviewPageNum = 1; // 페이지 번호 초기화
	hasMoreReviews = true; // 리뷰 로딩 가능 상태로 설정
	
	$('#reviewContent').empty(); // 기존 리뷰 내용 제거
	
	var order = $(this).val();
	
	loadMoreReviews(order); 
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
	
	//모바일 사이트 클릭시 QR 모달창 오픈	
	var siteMobile = $('#siteMobile').val();
    $('a[data-bs-target="#qrModal"]').on('click', function(e) {
        e.preventDefault();
        $("#qrcode").empty(); // 기존 QR 코드 삭제
        $("#qrcode").qrcode({
            text: siteMobile, // URL 값을 가져와서 QR 코드 생성
            width: 128,
            height: 128
        });
    });
    
    //경력사항 탭 클릭시 ajax
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
   
// 좋아요 이미지 클릭시
 $(document).on('click', '#heartDiv', function(){
        heartImgChange();
    });
   
});


function currentCountChange(){
	var inprAccountId = $('#userAccountId').val();
	var loginId = $('#loginId').val();
	
	$.ajax({
		type:"get",
		url:"./viewCount.do",
		data:{
			inprAccountId : inprAccountId,
			loginId : loginId,
		},
		success: function(data){
			var source = $("#current-count-template").html();
            var template = Handlebars.compile(source);
                
            var html = template(data);
            
            $('#currentCount').empty();    
            $('#currentCount').append(html);
		},
		error: function(){
			alert("오류");
		}
	})	
	
}

function heartImgChange(){
	console.log("heartImgChange 실행");
	var inprAccountId = $('#userAccountId').val();
	var loginId = $('#loginId').val();
	var type = $('.heartType').val();
	
	if(!loginId){
		alert("로그인 후 이용가능합니다.");
		return;
	}
	console.log("inprAccountId : ", inprAccountId);
	console.log("loginId : ", loginId);
	console.log("type : ", type);
	$.ajax({
		type:"get",
		url:"./userLike.do",
		data:{
			inprAccountId : inprAccountId,
			loginId : loginId,
			type: type
		},
		success: function(data){
			var source = $("#heart-template").html();
            var template = Handlebars.compile(source);
                
            var html = template(data);
            
            $('#heartDiv').empty();    
            $('#heartDiv').append(html);
		},
		error: function(){
			alert("오류");
		}
	})
}

var hasMoreClass = true;
var classPageNum = 2;
var isAjaxProcessing = false;

 function loadMoreClasses() {
	var userAccountId = $('#userAccountId').val();
	console.log("hasMoreClass 상태 : ", hasMoreClass);
		if (!hasMoreClass || isAjaxProcessing) return; // 더 이상 로드할 리뷰가 없으면 종료
		isAjaxProcessing = true;
        $.ajax({
            url: './instrClassDetail.do',
            method: 'GET',
            data: { userAccountId: userAccountId,
					start: (classPageNum - 1) * 5 + 1,
                	end: classPageNum * 5 }, 
            success: function(data) {
				var classVo = data.historyVo;
		        classVo.forEach(function(history) {
		            // subjCode 문자열을 JSON 배열로 변환
		            history.subjectVo[0].subjCode = JSON.parse(history.subjectVo[0].subjCode);
		        });
	
                var source = $("#class-history-template").html();
                var template = Handlebars.compile(source);
                
                var html = template(classVo);
                
                $('#classContent').append(html);
                
                hasMoreClass = data.hasMore; // 다음 페이지의 존재 여부 업데이트
                
                if(hasMoreClass){
					classPageNum++; // 페이지 번호 증가
				}
				isAjaxProcessing = false;
                
            },
            error: function(err) {
               console.error(err);
               isAjaxProcessing = false;
           }
       });
   }

var hasMoreReviews = true;
var reviewPageNum = 2;

function loadMoreReviews(order) {
	var userAccountId = $('#userAccountId').val();
    if (!hasMoreReviews || isAjaxProcessing) return; // 더 이상 로드할 리뷰가 없으면 종료
	isAjaxProcessing = true;
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
            
            $('#reviewContent').append(html); 
            
            hasMoreReviews = data.hasMore; // 다음 페이지의 존재 여부 업데이트
			if (hasMoreReviews){
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
   