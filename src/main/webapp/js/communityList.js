var boardId = [];

$(document).ready(function(){
	var clasId = $("#clasId").val();
	var clasTitle = $("#clasTitle").val();
	$("#clasId").remove();
	$("#clasTitle").remove();
	if(clasId == ''){
		getCommunityList("date",1,null);		
	} else {		
		// 선택된 값들이 추가될 div 요소
		var $selectedSubjects = $('#selectedSubjects');

		// 새로운 div 요소 생성 및 추가
		var $div = $('<div>')
				.addClass(
						'choices choices__item choices__item--selectable')
				.attr('data-value', clasId)
				.attr('data-type', 'select-multiple')
				.text(clasTitle).appendTo(
						$selectedSubjects);

		// 삭제 버튼 생성 및 추가
		var $removeButton = $('<button>').addClass(
				'choices__button').attr('aria-label',
				"Remove item: '" + clasTitle + "'")
				.attr('data-button', '').text(
						"Remove item").on('click',
						function() {
							$div.remove();
						}).appendTo($div);
		$('form').submit();
	}
});

$(document).on("click", ".order-by", function(e){
	getOrderByList($(e.target), boardId);
});

$(document).on("click",".page-link",function(e){
	getCommunityList($(".order-by .text-primary").parent().attr("id"),$(e.target).attr("id"),boardId);	
})

function getOrderByList(orderBy, boardId){
	$(".order-by i").each(function(){
		$(this).removeClass("text-primary");
		$(this).addClass("text-muted");
	})
	orderBy.children('i').removeClass("text-muted");
	orderBy.children('i').addClass("text-primary");
	getCommunityList(orderBy.attr("id"),1,boardId);
}

function getCommunityList(orderBy, page, boardId){
	$.ajax({
		type:"post",
		url:"./getCommunityList.do",
		data:{
			"orderBy":orderBy,
			"page":page,
			"boardId":boardId
		},
		success: function(data){
			if(data.board.length == 0){
				$("#sa-error").click();
				$("#reset-btn").click();
			} else {
				var board = data.board;
				var page = data.page;
				
				var color = ["success","danger","warning","info"];
				
				var boardSource = $("#board-list-template").html();
				var boardTemplate = Handlebars.compile(boardSource);
				var count = 0;
				
				Handlebars.registerHelper('isClass', function(options) {
				  if (board[i]!=null) {
				    return options.fn(this);
				  } else {
				    return options.inverse(this);
				  }
				});
				
				$(".list-group").children().remove();
				
				for(i=0; i<board.length;i++){
					var value = JSON.parse(board[i].subjectCode);
					if(value==null){
						value="";
					}
					var subjects = [];
					for(j=0; j<value.length;j++){
						subjects.push({"color":color[count++%4],"subject":value[j]});
					}
					var boardData = {
						"id":board[i].id,
						"accountId":board[i].accountId,
						"heart":board[i].likeUser=="1"?"cancel":"do", 
						"title":board[i].title,
						"regdate":board[i].regdate,
						"likeCount":board[i].likeCount,
						"viewCount":board[i].viewCount,
						"clasId":board[i].clasId,
						"subjects":subjects
					}
					var boardItem = boardTemplate(boardData);
					$(".list-group").append(boardItem);
				}
				pageChange(page.startPage, page.endPage, page.page, page.totalPage);
			}
		},
		error: function(){
			alert("잘못된 요청입니다.");
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
	
	var pageSource = $("#page-list-template").html();
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
	
	$(".pagination").children().remove();
	
	var pageData = {
		"page":pageList
	}
	
	var pageItem = pageTemplate(pageData);
	$(".pagination").append(pageItem);
}

$(document).on("click","#write-btn",function(e){
	if($(".dropdown-header")[0]!=undefined){
		location.href="./communityWriteForm.do"
	} else {
		$("#sa-warning").click()
	}
});

$(function() {
		$("#subjects")
				.autocomplete(
						{
							minLength : 1,
							source : function(request, response) {
								communitySearch(request,response)
							},
							focus : function() {
								return false;
							},
							select : function(event, ui) {
								this.value = '';

								// 선택된 값들이 추가될 div 요소
								var $selectedSubjects = $('#selectedSubjects');

								// 이미 추가된 값인지 확인
								if ($selectedSubjects.find('li[data-value="'
										+ ui.item.value + '"]').length > 0) {
									return false;
								}

								// 새로운 div 요소 생성 및 추가
								var $div = $('<div>')
										.addClass(
												'choices choices__item choices__item--selectable')
										.attr('data-value', ui.item.value)
										.attr('data-type', 'select-multiple')
										.text(ui.item.label).appendTo(
												$selectedSubjects);

								// 삭제 버튼 생성 및 추가
								var $removeButton = $('<button>').addClass(
										'choices__button').attr('aria-label',
										"Remove item: '" + ui.item.label + "'")
										.attr('data-button', '').text(
												"Remove item").on('click',
												function() {
													$div.remove();
												}).appendTo($div);

								return false;
							},
						}).data("ui-autocomplete")._renderItem = function(ul,item) {
							return $("<li>").append("<a>" + item.label + "</a>").appendTo(ul);
						};
		$('#subjects').on('keydown', function(event) {
			if (event.key === 'Enter') {
				event.preventDefault();
				return false;
			}
		});

	});

function communitySearch(request, response) {
	$.ajax({
		url : './json/communitySearch.json',
		type : 'get',
		dataType : 'json',
		success : function(data){
			var filteredData = data.filter(function(item) {
			    if (Array.isArray(item.title)) {
		            return item.title.some(function(element) {
		                return new RegExp(request.term.toLowerCase(), 'i').test(element);
		            });
				} else {
				    // 배열이 아닌 경우, 기존 방식으로 필터링 조건 적용
				    return item.title.toLowerCase().includes(request.term.toLowerCase());
				}
			});
            
			response(
                $.map(filteredData, function(item) {
                    return {
                        label : item.title[0],
                        value : item.id
                    }
                })
            );
		},
		error : function() {
			response([]);
		}
	});
}

$('form').on('submit', function (event) {
	
    event.preventDefault();
    
    var formData = {};
    
    var title = $("#search-basic").val();
    var classname = [];
    var subject = [];
    
    $('#selectedSubjects div').each(function() {
		if($(this).attr('data-value').length>5){
			classname.push(parseInt($(this).attr('data-value')));
		} else if ($(this).attr('data-value').length<=5) {			
			subject.push($(this).attr('data-value'));
		}
    });
    
    if(title.length > 0){
        formData['title'] = title;
    }
    if(classname.length > 0){
		formData['classname'] = classname;
	}
	if(subject.length > 0){
		formData['subjects'] = subject;
	}
	
	if(subject.length == 0 && classname.length == 0 && subject.length == 0){
		getCommunityList("date",1,null);
	} else {
		formData['page'] = 0;
	    $.ajax({
	    	url: "./communitySearch.do",
	        method: "post",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: JSON.stringify(formData),
	        success: function (data) {
				if(data.length>0){
					boardId = data;
					getOrderByList($("#date"), data);
				} else {
					boardId = [];
					$("#sa-error").click();
					$("#reset-btn").click();
					getOrderByList($("#date"), null);
				}
	        },
	        error: function(){
	        	alert("잘못된 요청입니다.");
	        }
	    });
	}
	
});

$(document).on("click", "#sa-error", function(){
	Swal.fire({
        title: '검색 결과가 없습니다!',
        text: '전체 검색 결과로 이동합니다.',
        icon: 'error',
         customClass: {
            confirmButton: 'btn btn-primary w-xs mt-2',
        },
        buttonsStyling: false,
        showCloseButton: true
    })
})

$(document).on("click", "#reset-btn", function(){
	boardId = [];
	$('#selectedSubjects').children().remove();
	$('form')[0].reset();
})