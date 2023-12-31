var boardId = [];

/**
	클래스 입력시 태그 생성 및 삭제
 */
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

/**
	정렬
 */
$(document).on("click", ".order-by", function(e){
	getOrderByList($(e.target), boardId);
});

/**
	페이지 이동
 */
$(document).on("click",".page-link",function(e){
	getCommunityList($(".order-by .text-primary").parent().attr("id"),$(e.target).attr("id"),boardId);	
})

/**
	정렬시 목록 조회
 */
function getOrderByList(orderBy, boardId){
	$(".order-by i").each(function(){
		$(this).removeClass("text-primary");
		$(this).addClass("text-muted");
	})
	orderBy.children('i').removeClass("text-muted");
	orderBy.children('i').addClass("text-primary");
	getCommunityList(orderBy.attr("id"),1,boardId);
}

/** 
	목록 조회
*/
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
				boardId = [];
				$("#sa-error").click();
				$("#reset-btn").click();
				getOrderByList($("#date"), boardId);
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
					Handlebars.registerHelper('isChaetaek', function(options) {
					  if (board[i].chaetaek=="Y") {
					    return options.fn(this);
					  } else {
					    return options.inverse(this);
					  }
					});
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

/**
	글 작성 폼 이동
 */
$(document).on("click","#write-btn",function(e){
	if($(".dropdown-header")[0]!=undefined){
		location.href="./communityWriteForm.do"
	} else {
		$("#sa-warning").click()
	}
});

/**
	과목 검색어 입력시 태그 생성
 */
$(function() {
		if($("#subjects").length>0){
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
		}
	});

/**
	검색어 자동 생성
 */
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

/**
	검색 버튼 클릭시
 */
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
	
	if(title.length == 0 && classname.length == 0 && subject.length == 0){
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
					getOrderByList($("#date"), boardId);
				}
	        },
	        error: function(){
	        	alert("잘못된 요청입니다.");
	        }
	    });
	}
	
});

/**
	검색 결과 없음 alert
 */
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

/** 
	검색어 초기화
*/
$(document).on("click", "#reset-btn", function(){
	boardId = [];
	$('#selectedSubjects').children().remove();
	$('form')[0].reset();
})