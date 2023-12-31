window.addEventListener("resize", function(){
	var topButton = $('#scrollToTopButton');
	var xPos = ($(window).width() / 1.26);
	var yPos = ($(window).height() / 1.12);
	    topButton.css({"left": xPos, "top": yPos});
})


$(document).ready(function() {
	
	var topButton = $('#scrollToTopButton');
	var xPos = ($(window).width() / 1.26);
	var yPos = ($(window).height() / 1.12);
	    topButton.css({"left": xPos, "top": yPos});
	
	
	 $('#scrollToTopButton').click(function() {
    	    $('#moreList').animate({ scrollTop: 0 }, 'slow');
    	});
    	
$('#moreList').scroll(function() {
   if ($('#moreList').scrollTop() + $('#moreList').innerHeight() >= this.scrollHeight - 10) {
       if ($("#orderSelect").is(':visible')) {
       	if(hasMoreList){
			var order = $('#orderSelect').val();
			console.log("Scrolled API 실행 order: ", order);
            loadMoreList(order);
         }
       } 
   }
});

$('#orderSelect').change(function() {
	listPage = 1; // 페이지 번호 초기화
	hasMoreList = true; // 리뷰 로딩 가능 상태로 설정
	
	$('#moreList').empty(); // 기존 리뷰 내용 제거
	
	var order = $(this).val();
	
	loadMoreList(order); 
});
	
	var page = 0;
	
        function loadMoreData() {
	        page++; // 페이지 번호 증가
	        $('form').trigger('submit'); // form 제출 이벤트 발생시킴
	    }
	    
	    $('#more').click(function(e) { 
	        e.preventDefault();
	        loadMoreData(); 
	    });
	
	//검색할 때 엘라스틱 작동
	$('form').on('submit', function (event) {
        if (event.originalEvent) { // 폼 제출에 의해 이벤트가 발생한 경우, 페이지번호 초기화
	        page = 0;
	    }
	    
        event.preventDefault();
        
        
        var formDataArray = $("#searchForm").serializeArray();
        var formData = {};
        
        // 과목 수집
	    var subjects = [];
	    $('#selectedSubjects div').each(function() {
	        subjects.push($(this).attr('data-value'));
	    });
	    
	    if(subjects.length > 0){
	        formData['subjects'] = subjects;
	    }
        
        formDataArray.forEach(function (item) {
            if(item.name === 'feeLt' || item.name === 'feeGt'){
            	formData['fee'] = formData['fee'] || {};
                formData['fee'][item.name === 'feeLt' ? 'lt' : 'gt'] = parseInt(item.value);
            } else if (item.value && item.name !== "subjects") {
		        	formData[item.name] = item.value;
            }
        });
        
        formData['page'] = page;
        
        console.log("보내는 값: ",JSON.stringify(formData));
        
        $.ajax({
        url: './instrSearch.do',
        method: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(data) {
			$("#orderSelect").hide();
			
			var lists = data.lists;
			
			if (lists == null) {
            	 $('#moreList').html("<p>검색 조건에 해당하는 강사를 찾을 수 없습니다.</p>");
            } else {
			
			
			lists.forEach(function(subject){
				subject.subjectsTitle = JSON.parse(subject.subjectsTitle);
				subject.subjectsMajorTitle = JSON.parse(subject.subjectsMajorTitle);
			});
	
            var source = $("#instrList-template").html();
            var template = Handlebars.compile(source);
            
             var combinedData = {
		        lists: lists,
		        userInfo: data.userInfo
		    };
            
            var html = template(combinedData);
            
            if (event.originalEvent) { // 폼 제출에 의해 이벤트가 발생한 경우, 출력 영역 초기화
            	 $('#moreList').empty();
				 $('#moreList').append(html);
				 $('#moreList').animate({ scrollTop: 0 }, 'slow');
			} else {
				 $('#moreList').append(html);
			}
            
			 $(function () {
				    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
				    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
				        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
				    })
				})	
            }
        },
        error: function(err) {
           console.error(err);
       }
   });
        
	});
	
});

var listPage = 2;
var hasMoreList = true;
var isAjaxProcessing = false;

function loadMoreList(order){
    if (!hasMoreList || isAjaxProcessing) return; // 더 이상 로드할 리뷰가 없으면 종료
	isAjaxProcessing = true;
	console.log("loadMoreList 보내는 값: ", order, listPage);
    $.ajax({
        url: './instrMoreList.do',
        method: 'GET',
        data: { 
				start: (listPage-1)*9+1,
                end: listPage*9,
                order: order 
               }, 
        success: function(data) {
			var lists = data.lists;
			
			lists.forEach(function(subject){
				subject.subjectsTitle = JSON.parse(subject.subjectsTitle);
				subject.subjectsMajorTitle = JSON.parse(subject.subjectsMajorTitle);
			});
	
            var source = $("#instrList-template").html();
            var template = Handlebars.compile(source);
            
            var combinedData = {
		        lists: lists,
		        userInfo: data.userInfo
		    };
            
            var html = template(combinedData); 
            
            $('#moreList').append(html); // 새로운 내용 추가
            
            hasMoreList = data.hasMore; // 다음 페이지의 존재 여부 업데이트
			if (hasMoreList){
				listPage++; // 페이지 번호 증가
				console.log("증가하는 다음 페이지 :",listPage);
			}
			isAjaxProcessing = false;
			 $(function () {
				    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
				    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
				        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
				    })
				})	
            
        },
        error: function(err) {
           console.error(err);
           isAjaxProcessing = false;
       }
   });
}
