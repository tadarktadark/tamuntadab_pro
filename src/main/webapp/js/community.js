$(document).on("click",".like-do",function(e){
	if($(".dropdown-header")[0]!=undefined){
		e.stopPropagation();
		$.ajax({
			type:"post",
			url:"./commynityLike.do",
			data:{
				"type":$(e.target).attr("src").split("_")[1].split(".")[0],
				"id": $(e.target).attr("id")
			},
			success: function(data){
				const src = e.target.getAttribute("src");
				const newSrc = src.substring(0, src.indexOf("_") + 1) + data.type + ".png";
				$(e.target).attr('src', newSrc);
				$(e.target).parent().next().find('span').text(data.count);
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});	
	} else {
		e.stopPropagation();
		$("#sa-warning").click();
	}
})

$(document).on("click","#sa-warning",function(){
	Swal.fire({
	    title: "로그인시 이용할 수 있습니다.",
	    text: "로그인 하시겠습니까?",
	    icon: "warning",
	    showCancelButton: true,
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2 me-2',
	        cancelButton: 'btn btn-danger w-xs mt-2',
	    },
	    confirmButtonText: "로그인",
	    cancelButtonText: "취소",
	    buttonsStyling: false,
	    showCloseButton: true
	}).then(function (result) {
	    if (result.value) {
	        location.href="./loginForm.do";
	    }
	});
});

$(document).on("click",".view-detail",function(e){
	location.href="./communityDetails.do?id="+$(e.target).closest('.view-detail').attr("id");
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
				    // 배열인 경우, 배열의 첫 번째 요소에 대해 필터링 조건을 적용
				    var firstTitle = item.title[0];
				    return firstTitle && firstTitle.toLowerCase().includes(request.term.toLowerCase());
				} else {
				    // 배열이 아닌 경우, 기존 방식으로 필터링 조건 적용
				    console.log("ㅎㅎ")
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
    
		console.log($('#selectedSubjects div'));
    $('#selectedSubjects div').each(function() {
		if($(this).attr('data-value').length>5){
			classname.push($(this).attr('data-value'));
		} else if ($(this).attr('data-value').length<=5) {			
			subject.push($(this).attr('data-value'));
		}
    });
    
    if(title.length > 0){
        formData['title'] = title;
    } else if(classname.length > 0){
		formData['classname'] = classname;
	} else if(subject.length > 0){
		formData['subject'] = subject;
	}

 	console.log(formData);
//    $.ajax({
//    	url: "./instrSearch.do",
//        method: "post",
//        dataType: 'json',
//        contentType: 'application/json',
//        data: JSON.stringify(formData),
//        success: function (data) {
//        },
//        error: function(){
//        	alert("오류");
//        }
//    });
});