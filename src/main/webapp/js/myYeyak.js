/**
	최초 로딩시 내 예약 목록 조회
 */
$(document).on("click",".scheduel",function(){
	getMyYeyakList(1);
})

/**
	예약 목록 가져오기
 */
function getMyYeyakList(page){
	$("#yeyak-tbody").children().remove();
	$.ajax({
    	url: "./getMyYeyakList.do",
        method: "get",
        data:{
			"page":page
		},
        success: function(data){
			var board = data.board;
			var page = data.page;
			var listSource = $("#yeyak-list-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<board.length;i++){
		 		(function(i) {
		            Handlebars.registerHelper('stateN', function(options) {
		                if (board[i].gayeState == 'N') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateY', function(options) {
		                if (board[i].gayeState == 'Y') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateD', function(options) {
		                if (board[i].gayeState == 'D') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		           
		        })(i);
		        
		        var date = board[i].gayeYeyakDate.substring(0,4)+"년 "+
		        			board[i].gayeYeyakDate.substring(4,6)+"월 "+
		        			board[i].gayeYeyakDate.substring(6)+"일 "+
		        			board[i].gayeStartTime.substring(0,2)+"시 ~ "+
		        			(Number(board[i].gayeStartTime.substring(0,2))+Number(board[i].gayeHours))+"시";
				           
				var listData = {
					"i":i,
					"name":board[i].gayeAccountId,
					"date":date,
					"class":board[i].gayeGyeoId,
					"gayeId":board[i].gayeId, 
					"gayeGagaId":board[i].gayeGagaId,
					"gayeYeyakDate":board[i].gayeYeyakDate,
					"gayeStartTime":board[i].gayeStartTime,
					"gayeHours":board[i].gayeHours
				}
				var listItem = listTemplate(listData);
				$("#yeyak-tbody").append(listItem);
			}
			yeyakPageChange(page.startPage, page.endPage, page.page, page.totalpage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

/**
	예약 클릭시 예약 목록 조회
 */
$(document).on("click",".yeyak-link",function(e){	
	getMyYeyakList($(e.target).attr("id"));	
})

/**
	예약 취소
 */
$(document).on("click", ".cancel-btn", function(e){
	var page = $(".yeyak-page").eq(0).find(".active").eq(0).find(".yeyak-link").attr("id");
	Swal.fire({
	    title: "예약을 취소하시겠습니까?",
	    icon: "warning",
	    showCancelButton: true,
	    customClass: {
	        confirmButton: 'btn btn-danger w-xs mt-2 me-2',
	        cancelButton: 'btn btn-light w-xs mt-2',
	    },
	    confirmButtonText: "취소",
	    cancelButtonText: "유지",
	    buttonsStyling: false,
	    showCloseButton: true
	}).then(function (result) {
	    if (result.value) {
	        $.ajax({
		    	url: "./yeyakCancel.do",
		        method: "post",
		        data: {
					"gayeId":$(e.target).closest("td").children(".gayeId").eq(0).val(),
					"gayeGagaId":$(e.target).closest("td").children(".gayeGagaId").eq(0).val(),
					"gayeYeyakDate":$(e.target).closest("td").children(".gayeYeyakDate").eq(0).val(),
					"gayeStartTime":$(e.target).closest("td").children(".gayeStartTime").eq(0).val(),
					"gayeHours":$(e.target).closest("td").children(".gayeHours").eq(0).val(),
					"gayeGyeoljeUser":$(e.target).closest("tr").find("td").eq(2).text()
				},
		        success: function(data){
					if(data==1){
						getMyYeyakList(page);
					}
				},
				error: function(){
					alert("잘못된 요청입니다.");
				}
		    });
	    }
	});
})

/**
	내 예약 페이지 페이지 이동시 페이지 목록 변경
 */
function yeyakPageChange(startPage, endPage, page, totalPage){
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
	if(startPage<((totalPage/5)-5+1)){
		pageList.push({"id":postId,"value":"<i id='"+postId+"' class='ri-arrow-right-s-line'></i>"});
		pageList.push({"id":totalPage,"value":"<i id='"+totalPage+"' class='mdi mdi-page-last'></i>"});
	}
	
	var pageSource = $("#yeyak-page-template").html();
	var pageTemplate = Handlebars.compile(pageSource);
	
	// Handlebars.js 템플릿 컴파일 시, htmlOrText 헬퍼 함수 등록
	Handlebars.registerHelper('htmlOrText', function(value) {
	    // value가 HTML 태그를 포함하는지 여부 확인
	    const containsHtmlTags = /<[a-z][\s\S]*>/i.test(value);
	
	    // value가 HTML 태그를 포함하면서 안전한 방법으로 출력하기 위해 {{{}}} 사용
	    if (containsHtmlTags) {
	        return new Handlebars.SafeString(value);
	    }
	
	    // value가 일반 문자열인 경우 {{}} 사용하여 출력
	    return value;
	});
	
	$(".yeyak-page").children().remove();
	
	var pageData = {
		"page":pageList
	}
	
	var pageItem = pageTemplate(pageData);
	$(".yeyak-page").append(pageItem);
}

/**
	결제 상태 조회
 */
$(document).on("click", ".pay-btn", function(e){
	$("#gyeolje-list").children().remove();
	$.ajax({
    	url: "./getGyeojeStatus.do",
        method: "post",
        data: {
			"gayeId":$(e.target).closest("tr").find(".gayeId").eq(0).val()
		},
        success: function(data){
			var listSource = $("#modal-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<data.length;i++){
		 		(function(i) {
		            Handlebars.registerHelper('stateP', function(options) {
		                if (data[i].gyeoStatus == 'P') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateW', function(options) {
		                if (data[i].gyeoStatus == 'W') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateC', function(options) {
		                if (data[i].gyeoStatus == 'C') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateR', function(options) {
		                if (data[i].gyeoStatus == 'R') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('myInfo', function(options) {
		                if (data[i].gyeoId == '나') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		           
		        })(i);
		        
		        var geumaek = Number(data[i].gyeoGeumaek).toLocaleString('ko-KR');
				var listData = {
					"nickname":data[i].gyeoId,
					"geumaek":geumaek,
					"accountId":data[i].gyeoAccountId,
					"daesangId":data[i].gyeoDaesangId
				}
				var listItem = listTemplate(listData);
				$("#gyeolje-list").append(listItem);
			}
			$("#show-modal").click();
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
})

/**
	결제 요청 알람 보내기
 */
$(document).on("click", ".send-btn", function(e){
	var content = "[결제 요청] " + $(e.target).closest("div").children("input").eq(1).val() + ", " + $("#geumaek").text() + " 결제 바랍니다."; 
	sendAlarm(content, $(e.target).closest("div").children("input").eq(0).val())
})

/**
	결제 요청 알람 보내기
 */
function sendAlarm(content, accountId){
	var gubun = 'AT_P';//구분은 slack 참고
	var content = content;//보낼 내용
	var accountId = accountId;//받는 사용자 ID
	var url = 'mypage.do';//알림을 누르면 이동할 주소, ?로 값전달해서 보내도됨
	$.ajax({
		url:'./insertAlarm.do',
		type:'POST',
		data:{
			"gubun":gubun,
			"content":content,
			"accountId":accountId,
			"url":url
		},
		success:function(data){
			console.log(data);
			Swal.fire({
		        title: '결제 요청 알림을 보냈습니다!',
		        icon: 'success',
		        customClass: {
		            confirmButton: 'btn btn-primary w-xs mt-2  me-2'
		        },
		        buttonsStyling: false,
		        showCloseButton: true
		    })
		},
		error:function(){}
	})
}