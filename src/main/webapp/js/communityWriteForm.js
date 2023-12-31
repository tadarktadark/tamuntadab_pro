
/**
	이미지 삭제를 위한 옵저버 설정
 */
$(document).ready(function(){
		
	// 1. 옵저버 감시 대상 설정
	const target = document.getElementsByClassName("ck-content")[0]
	
	// 2. 옵저버 콜백 생성
	const callback = (mutationList, observer) => {
		if(mutationList[0].removedNodes[0]){			
			const node = mutationList[0].removedNodes[0].childNodes[0]
		  	if(node.localName == "img"){
				$.ajax({
					type:"post",
					url:"./communityRemoveImage.do",
					data: "saveName="+node.currentSrc.substring(node.currentSrc.lastIndexOf("/")+1),
					error: function(){
						alert("잘못된 요청입니다.");
					}
				});
			}
		}
	};
	
	// 3. 옵저버 인스턴스 생성
	observer = new MutationObserver(callback); // 타겟에 변화가 일어나면 콜백함수를 실행하게 된다.
	
	// 4. DOM의 어떤 부분을 감시할지를 옵션 설정
	const config = { 
	    childList: true, // 자식노드 추가/제거 감지
	};
	
	// 5. 감지 시작
	observer.observe(target, config);
	
});

/**
	클래스 변경시 과목 변경
 */
$(document).on("change", "#class", function(e){
	if($(e.target).val()=='none'){
		$("#class-selected").remove();
		$(".choices").css("display","block");
		$("#subjectCode").val("");
	} else {
		$.ajax({
			type:"post",
			url:"./getJilmunClassList.do",
			data:{
				"clasId":$(e.target).val()
			},
			success: function(data){
				$("#subjectCode").val(data.code);
				$("#class-selected").remove();
				$(".choices").css("display","none");
				
				var options = [];
				var subjects = [];
				for(i=0; i<data.title.length;i++){
					options.push({"option":data.title[i]});
					subjects.push({"no":i,"subject":data.title[i]});
				}
					
				var selSource = $("#select-template").html();
				var selTemplate = Handlebars.compile(selSource);
				var selData = {
					"options":options,
					"subjects":subjects
				}
				var selItem = selTemplate(selData);
				
				$("#write-top").append(selItem);
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});
	}
})

/**
	글 작성
 */
$(document).on("click","#write-btn",function(){
	// 에디터 내용 가져오기
	const data = editor.getData();
	// hidden form 요소에 할당
	$("#ckeditor").text(data);
	// 6. 감지 중지
	observer.disconnect();
	
	if($('#selectedSubjects div').length>0){		
		var subCode = '[';
		$('#selectedSubjects div').each(function() {
			subCode += '"'+$(this).attr('data-value')+'",'
	    })
	    subCode = subCode.slice(0, -1);
		subCode += ']';
		
		$("#subjectCode").val(subCode);	    
	}
	
	if(($("#title").length>0 && $("#title").val()=='') ||
		($("#ckeditor").length>0 && $("#ckeditor").val()=='') ||
		($("#viewGroup").length>0 && $("#viewGroup").val()=='N') ||
		($("#downloadGroup").length>0 && $("#downloadGroup").val()=='N') ||
		($("#subjectCode").length>0 && $("#subjectCode").val()=='')){
		$("#sa-basic").click();
	} else {
		$("form").submit();
	}
	
});

/** 
	파일 업로드 유효값 검사
*/
$(document).on("change", "#inputGroupFile01", function(){
	if(this.files.length>10){
		$("#sa-warning-length").click();
		this.value='';
	} else {		
		for(i=0; i<this.files.length; i++){
			var ext = this.files[i].name.toLowerCase();
			var regex = /\.(exe|msi|bat|vbs)$/i;
			
			if(this.files[i].size>41943040){
				$("#sa-warning-size").click();
				this.value='';
			} else if(ext.match(regex)){
				$("#sa-warning-extension").click();
				this.value='';
			}
		}
	}
})

/**
	파일 업로드 크기 alert
 */
$(document).on("click","#sa-warning-size",function(){
	Swal.fire({
	    title: "5MB 이하 파일만 업로드 가능합니다.",
	    icon: "warning",
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2 me-2',
	    },
	    confirmButtonText: "확인",
	    buttonsStyling: false,
	    showCloseButton: true
	})
});

/**
	파일 업로드 확장자 alert
 */
$(document).on("click","#sa-warning-extension",function(){
	Swal.fire({
	    title: "[exe,msi,bat,vbs] 파일은 업로드 할 수 없습니다.",
	    icon: "warning",
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2 me-2',
	    },
	    confirmButtonText: "확인",
	    buttonsStyling: false,
	    showCloseButton: true
	})
});

/**
	파일 최대 개수 alret
 */
$(document).on("click","#sa-warning-length",function(){
	Swal.fire({
	    title: "파일은 최대 10개 업로드 가능합니다.",
	    icon: "warning",
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2 me-2',
	    },
	    confirmButtonText: "확인",
	    buttonsStyling: false,
	    showCloseButton: true
	})
});

/**
	과목 태그 생성
 */
$(function() {
	if($("#subjects").length > 0){
		$("#subjects")
				.autocomplete(
						{
							minLength : 1,
							source : function(request, response) {
								subjectSearch(request,response)
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
	과목 삭제
 */
$(document).on("click", ".subject-remove-btn", function(e){
	$(e.target).closest("div").remove();
})

/**
	과목 검색
 */
function subjectSearch(request, response) {
	$.ajax({
		url : './json/subjectSearch.json',
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
	파일 삭제
 */
$(document).on("click", ".remove-btn", function(e){
	$.ajax({
		url : './removeFile.do',
		type : 'get',
		data : {
			"save":$(e.target).attr("id")
		},
		success : function(data){
			if(data==1){
				$(e.target).closest("div").remove();
			} else {
				alert("삭제 실패");
			}
		},
		error : function() {
			response([]);
		}
	});
});

/**
	게시글 수정
 */
$(document).on("click","#update-btn",function(){
	// 에디터 내용 가져오기
	const data = editor.getData();
	// hidden form 요소에 할당
	$("#ckeditor").text(data);
	// 6. 감지 중지
	observer.disconnect();
	
	if($('#selectedSubjects div').length>0){		
		var subCode = '[';
		$('#selectedSubjects div').each(function() {
			subCode += '"'+$(this).attr('data-value')+'",'
	    })
	    subCode = subCode.slice(0, -1);
		subCode += ']';
		
		$("#subjectCode").val(subCode);	    
	}
	
	if(($("#title").length>0 && $("#title").val()=='') ||
		($("#ckeditor").length>0 && $("#ckeditor").val()=='') ||
		($("#viewGroup").length>0 && $("#viewGroup").val()=='N') ||
		($("#downloadGroup").length>0 && $("#downloadGroup").val()=='N') ||
		($("#subjectCode").length>0 && $("#subjectCode").val()=='')){
		$("#sa-basic").click();
	} else {
		$("form").submit();
	}
	
});

/**
	취소(뒤로 가기)
 */
$(document).on("click","#cancel-btn", function(){
	history.back(-1);
})