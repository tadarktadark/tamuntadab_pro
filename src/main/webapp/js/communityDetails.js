/**
	우클릭 비활성화 및 댓글 옵저버 설정
 */
$(document).ready(function(){
	
	if($("#boardId").text().substring(0,2)=='PI'){
		document.oncontextmenu = function(){
			Swal.fire({
			    title: "필기 상세보기에서 우클릭은 비활성화됩니다.",
			    icon: "warning",
			    customClass: {
			        confirmButton: 'btn btn-danger w-xs mt-2 me-2',
			    },
			    confirmButtonText: "확인",
			    buttonsStyling: false,
			    showCloseButton: true
			})
			
			return false;
		}
	}
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
	댓글 작성
 */
$(document).on("click","#reply-write-btn",function(){

	$('#updateSeq').removeAttr('name');
	// 에디터 내용 가져오기
	const data = editor.getData();
	
	if($("#loginUser").text()==''){
		$("#sa-warning").click();
	}else if(data==''){
		$("#sa-basic").click();
	}else{
		// hidden form 요소에 할당
		$("#ckeditor").text(data);
		// 6. 감지 중지
		observer.disconnect();
		$("#rootReplyWrite").submit();
	}		
});

/**
	신고로 숨겨진 내용 보기
 */
$(document).on("click",".contentShow",function(e){
	if($(e.target).closest("a").attr('aria-expanded')=='true'){
		$(e.target).closest("a").children("i").eq(0).removeClass("ri-arrow-down-circle-line").addClass("ri-arrow-up-circle-line");
		$(e.target).closest("a").children("span").eq(0).text(" 내용 숨기기");	
	} else if($(e.target).closest("a").attr('aria-expanded')=='false'){
		$(e.target).closest("a").children("i").eq(0).removeClass("ri-arrow-up-circle-line").addClass("ri-arrow-down-circle-line");
		$(e.target).closest("a").children("span").eq(0).text(" 신고되어 처리중인 게시글입니다. 내용을 확인하시려면 클릭해주세요.");
	}
});

/**
	pdf 다운로드
 */
$(document).on("click", "#pdf-btn", function(){
	html2canvas(document.getElementById("ck-content")).then(canvas => {
	  // base64 url 로 변환
	  var imgData = canvas.toDataURL('image/jpeg');
	
	  var imgWidth = 200; // 이미지 가로 길이(mm) A4 기준
	  var pageHeight = imgWidth * 1.414; // 출력 페이지 세로 길이 계산 A4 기준
	  var imgHeight = canvas.height * imgWidth / canvas.width;
	  var heightLeft = imgHeight;
	  var margin = 2;
	
	  var doc = new jsPDF('p', 'mm', 'a4');
	  var position = 5;
	
	  // 첫 페이지 출력
	  doc.addImage(imgData, 'jpeg', margin, position, imgWidth, imgHeight);
	  heightLeft -= pageHeight;
	
	  // 한 페이지 이상일 경우 루프 돌면서 출력
	  while (heightLeft >= 20) {
	    position = heightLeft - imgHeight + 5;
	    doc.addPage();
	    doc.addImage(imgData, 'jpeg', margin, position, imgWidth, imgHeight);
	    heightLeft -= pageHeight;
	  }
	
	  // 파일 저장
	  doc.save($("#title").text().substring(0,$("#title").text().indexOf('['))+'.pdf');
	});
	
})

/**
	필기 임시 삭제
 */
$(document).on("click", "#delete-btn",function(){
	if($("#boardId").text().charAt(0)=='P'){
		$("#sa-pilgi-delete").click();
	} else {
		$("#sa-delete").click();		
	}
})

/**
	수정 form 이동
 */
$(document).on("click", "#update-btn",function(){
	location.href="./communityUpdateForm.do?id="+$("#boardId").text();
})

/**
	완전 삭제 alert
 */
$(document).on("click","#sa-delete",function(){
	Swal.fire({
	    title: "삭제하시면 내용을 복구하실 수 없습니다.",
	    text: "삭제하시겠습니까?",
	    icon: "warning",
	    showCancelButton: true,
	    customClass: {
	        confirmButton: 'btn btn-danger w-xs mt-2 me-2',
	        cancelButton: 'btn btn-light w-xs mt-2',
	    },
	    confirmButtonText: "삭제",
	    cancelButtonText: "취소",
	    buttonsStyling: false,
	    showCloseButton: true
	}).then(function (result) {
	    if (result.value) {
	        location.href="./communityDelete.do?id="+$("#boardId").text();
	    }
	});
});


/**
	임시 삭제 alert
 */
$(document).on("click","#sa-pilgi-delete",function(){
	Swal.fire({
        html: '<div class="mt-3">' +
            '<lord-icon src="https://cdn.lordicon.com/gsqxdxog.json" trigger="loop" colors="primary:#f7b84b,secondary:#f06548" style="width:100px;height:100px"></lord-icon>' +
            '<div class="mt-4 pt-2 fs-15 mx-5">' +
            '<h4>필기를 삭제하시겠습니까?</h4>' +
            '<p class="text-muted mx-4 mb-0">[마이페이지]-[내 글 목록]에서<br>복원할 수 있습니다.</p>' +
            '</div>' +
            '</div>',
        showCancelButton: true,
        customClass: {
            confirmButton: 'btn btn-danger w-xs mt-2 me-2',
            cancelButton: 'btn btn-light w-xs mt-2',
        },
        confirmButtonText: '삭제',
        cancelButtonText: "취소",
        buttonsStyling: false,
        showCloseButton: true
    }).then(function (result) {
	    if (result.value) {
	        location.href="./communityDelete.do?id="+$("#boardId").text();
	    }
	})
});

/**
	신고 버튼 클릭시 신고 모달창
 */
$(document).on("click",".singo-btn",function(e){
	$("#daesangId").val($(e.target).closest("div").children("div").text());
	if($(".dropdown-header")[0]!=undefined){
		$.ajax({
	    	url: "./communitySingo.do",
	        method: "get",
	        success: function(data){
				$("#singo-list").children().remove();
				$("#sayu-insert").css("display","none");
				var singoSource = $("#singo-category-template").html();
				var singoTemplate = Handlebars.compile(singoSource);
				var count = 0;
				
				for(i=0; i<data.length; i++){
					var singoData = {
						"no":count++,
						"id":data[i].id,
						"category":data[i].category
					}
					var singoItem = singoTemplate(singoData);
					$("#singo-list").append(singoItem);
				}
				
				$("#modal-btn").click();
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
	    });
	} else {
		$("#sa-warning").click();
	}
})

/** 
	신고 카테고리 변경
*/
$(document).on("change", ".category", function(e){
	if($(e.target).val()=="SCAT10"){
		$("#sayu-insert").css("display","block");
	} else {
		$("#sayu-insert").css("display","none");
	}
})

/**
	신고하기
 */
$(document).on("click", "#singo-submit", function(){
	var data = $("#user-singo").serializeArray();	
	var result = {};
	
	data.forEach(function (item) {
		if(item.value!=''){			
	        result[item.name] = item.value;
		}
    });
    
    if(result["category"]==undefined ||
    	(result["category"]=="SCAT10" && result["content"]==undefined)){	
		$("#sa-basic").click();
	} else {
		$.ajax({
	    	url: "./userSingo.do",
	        method: "post",
	        data: $("#user-singo").serializeArray(),
	        success: function(data){
				if(data==1){
					Swal.fire({
			            title: '신고가 완료되었습니다!',
			            icon: 'success',
			            customClass: {
			                confirmButton: 'btn btn-primary w-xs mt-2  me-2',
			                cancelButton: 'btn btn-danger w-xs mt-2',
			            },
			            buttonsStyling: false,
			            showCloseButton: true
			        })
				}
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
	    });
	}
})

/**
	내용 유효값 alert
 */
$(document).on("click","#sa-basic",function(){
	Swal.fire({
	    title: '모든 값을 입력해주세요',
	    buttonsStyling: false,
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2',
	    },
	    showCloseButton: true
    })
});

/**
	댓글 보기 버튼 클릭
 */
$(document).on("click","#reply-btn",function(e){
	if($(e.target).closest('a').text()=="댓글보기 "){
		$("#reply-btn").html('댓글닫기&ensp;<i class="ri-arrow-up-s-line"></i>');
		$("#reply-list").css("display","block");
		getReplyList(1);		
	} else {
		$("#reply-btn").html('댓글보기&ensp;<i class="ri-arrow-down-s-line"></i>');
		$("#reply-list").css("display","none");
	}
})

/**
	댓글 조회
 */
function getReplyList(page){
	$.ajax({
    	url: "./getReplyList.do",
        method: "get",
        data:{
			"page":page,				
			"boardId":$("#boardId").text()
		},
        success: function(data){
			var root = data.rootReply;
			var re = data.reReply;
			var page = data.page;
			var reSeq = 0;
			
			if(root.length==0){
				$("#reply-group").children().remove();
				$("#reply-group").append("<div>작성된 댓글이 없습니다.</div>");
			} else {
				$("#reply-group").children().remove();
				
				var replySource = $("#reply-list-template").html();
				var replyTemplate = Handlebars.compile(replySource);
				for(i=0; i<root.length;i++){
					 (function(i) {
			            Handlebars.registerHelper('isDel', function(options) {
			                if (root[i].writerId != 'DEL@') {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			
			            Handlebars.registerHelper('isNotWriter', function(options) {
			                if ($("#loginUser").text() != root[i].writerId) {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			
			            Handlebars.registerHelper('isChaetaek', function(options) {
			                if (root[i].rootSeq == 0) {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			
			            Handlebars.registerHelper('isUpdate', function(options) {
			                if (root[i].update != root[i].regdate) {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			            
			            Handlebars.registerHelper('notChaetaek', function(options) {
			                if ($("#loginUser").text() == root[i].writerId
			                	&& root[i].chaetaek != "Y") {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			            
			            Handlebars.registerHelper('noChaetaek', function(options) {
			                if ($("#chaetaek").text() != "Y"
			                	&& $("#loginUser").text() == $("#boardAccountId").text()
			                	&& $("#loginUser").text() != root[i].writerId) {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			            
			            Handlebars.registerHelper('stateN', function(options) {
			                if (root[i].state == "N") {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			            
			            Handlebars.registerHelper('stateP', function(options) {
			                if (root[i].state == "P") {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			            
			            Handlebars.registerHelper('stateD', function(options) {
			                if (root[i].state == "D") {
			                    return options.fn(this);
			                } else {
			                    return options.inverse(this);
			                }
			            });
			            
			        })(i);
					
					var reData = [];
					for(j=reSeq; j<re.length; j++){
						
						if(re[j].rootSeq==root[i].rootSeq){
							reData.push({"re-content":re[j].content,
										"re-seq":re[j].seq,
										"re-writerId":re[j].writerId,
										"re-update":re[j].update,
										"re-regdate":re[j].regdate,
										"re-state":re[j].state});
							reSeq++;
						}
						
					}
					
		              Handlebars.registerHelper('isReDel', function(options){
		                  if(this['re-writerId'] != 'DEL@'){
		                      return options.fn(this); 
		                  }else{
		                      return options.inverse(this); 
		                  }
		              });
		              
		              Handlebars.registerHelper('isReWriter', function(options){
		                  if($("#loginUser").text() != this['re-writerId']){
		                      return options.fn(this); 
		                  }else{
		                      return options.inverse(this); 
		                  }
		              });
		              
		              Handlebars.registerHelper('isReUpdate', function(options){
		                  if(this['re-update'] !== this['re-regdate']){
		                      return options.fn(this); 
		                  }else{
		                      return options.inverse(this); 
		                  }
		              });
		              
		              Handlebars.registerHelper('reStateN', function(options) {
			              if (this['re-state'] == "N") {
			                  return options.fn(this);
			              } else {
			                  return options.inverse(this);
			              }
			          });
			          
			          Handlebars.registerHelper('reStateP', function(options) {
			              if (this['re-state'] == "P") {
			                  return options.fn(this);
			              } else {
			                  return options.inverse(this);
			              }
			          });
			          
			          Handlebars.registerHelper('reStateD', function(options) {
			              if (this['re-state'] == "D") {
			                  return options.fn(this);
			              } else {
			                  return options.inverse(this);
			              }
			          });
		            
					
					var replyData = {
						"content":root[i].content,
						"seq":root[i].seq,
						"writerId":root[i].writerId, 
						"update":root[i].update,
						"regdate":root[i].regdate,
						"re-reply":reData
					}
					var replyItem = replyTemplate(replyData);
					$("#reply-group").append(replyItem);
				}
				pageChange(page.startPage, page.endPage, page.page, page.totalPage);
			}
			
			$(function () {
			    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
			    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
			        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
		    	})
			})	
						
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

/**
	댓글 페이지 이동
 */
$(document).on("click",".page-link",function(e){
	getReplyList($(e.target).attr("id"));	
})

/**
	대댓글 달기
 */
$(document).on("click", ".re-reply-btn", function(e){
	if($("#loginUser").text()==''){
		$("#sa-warning").click();
	}else{
		$("#re-insert").remove();
		
		var reSource = $("#re-reply-template").html();
		var reTemplate = Handlebars.compile(reSource);
		
		var reItem = reTemplate();
		$(e.target).closest("div").closest(".reply-group").after(reItem);
	}
	
})

/**
	대댓글 글자수 제한
 */
$(document).on("keyup","#re-textBox", function(e){
	let content = $(e.target).val();
    
    // 글자수 세기
    if (content.length == 0 || content == '') {
    	$('#textCount').text('0');
    } else {
    	$('#textCount').text(content.length);
    }
    
    // 글자수 제한
    if (content.length > 300) {
    	// 300자 부터는 타이핑 되지 않도록
        $(this).val($(this).val().substring(0, 300));
        $('#textCount').text(300);
        // 300자 넘으면 알림창 뜨도록
        $("#sa-reply-length").click();
    };
});

/**
	글자수 제한 alert
 */
$(document).on("click","#sa-reply-length",function(){
	Swal.fire({
	    title: '글자수는 300자까지 입력 가능합니다.',
	    buttonsStyling: false,
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2',
	    },
	    showCloseButton: true
    })
});

/**
	댓글 수정 정보 가져오기
 */
$(document).on("click", ".reply-update-btn", function(e){
	$("#reply-write-btn-group").css("display","none");
	$("#reply-update-btn-group").css("display","block");
	$("#rootReplyWrite").attr("id","rootReplyUpdate");
	$("#rootReplyUpdate").prop("action","./replyUpdate.do");
	$("#rootReplyUpdate").attr("name","seq");
	$("#updateSeq").val($(e.target).closest("div").children("div").text());
	$.ajax({
    	url: "./getUpdateContent.do",
        method: "post",
        data: {
			"seq":$("#updateSeq").val()
		},
        success: function(data){
			editor.setData(data);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
})

/**
	댓글 수정 취소
 */
$(document).on("click","#reply-cancel-btn", function(){
	$("#reply-write-btn-group").css("display","block");
	$("#reply-update-btn-group").css("display","none");
	$("#rootReplyUpdate").attr("id","rootReplyWrite");
	$("#rootReplyWrite").prop("action","./replyWrite.do");
	editor.setData("");
})


/**
	댓글 수정하기
 */
$(document).on("click", "#reply-update-btn", function(e){
	// 에디터 내용 가져오기
	const data = editor.getData();
	
	if($("#loginUser").text()==''){
		$("#sa-warning").click();
	}else if(data==''){
		$("#sa-basic").click();
	}else{
		// hidden form 요소에 할당
		$("#ckeditor").text(data);
		
		// 6. 감지 중지
		observer.disconnect();
		
		$("#rootReplyUpdate").submit();
	}
})

/**
	댓글 삭제
 */
$(document).on("click", ".reply-delete-btn", function(e){
	Swal.fire({
	    title: "삭제하시면 내용을 복구하실 수 없습니다.",
	    text: "삭제하시겠습니까?",
	    icon: "warning",
	    showCancelButton: true,
	    customClass: {
	        confirmButton: 'btn btn-danger w-xs mt-2 me-2',
	        cancelButton: 'btn btn-light w-xs mt-2',
	    },
	    confirmButtonText: "삭제",
	    cancelButtonText: "취소",
	    buttonsStyling: false,
	    showCloseButton: true
	}).then(function (result) {
	    if (result.value) {
	        location.href="./replyDelete.do?boardId="+$("#boardId").text()+"&seq="+$(e.target).closest("div").children("div").text();
	    }
	});
})

/**
	대댓글 취소
 */
$(document).on("click","#re-cancel-btn",function(){
	$("#re-insert").remove();
})

/**
	대댓글 작성
 */
$(document).on("click","#re-write-btn", function(e){
	e.preventDefault();
	$("#re-boardId").val($("#boardId").text());
	$("#re-rootSeq").val($(e.target).closest("#re-insert").siblings("div").eq(1).children().eq(0).children().eq(0).text());
	
	if($("#loginUser").text()==''){
		$("#sa-warning").click();
	}else if($("#re-textBox").val()==''){
		$("#sa-basic").click();
	}else{
		$("#re-write-form").submit();
	}
})

/**
	대댓글 업데이트 정보 조회
 */
var text;
var rootSeq;
var html; 
var div; 
$(document).on("click", ".re-update-btn", function(e){
	text = $(e.target).closest(".list-group-item").find("p").text();
	rootSeq = $(e.target).closest("div").find("div").text();
	html = $(e.target).closest(".list-group-item").html();
	div = $(e.target).closest(".list-group-item")
	div.children().remove();
	
	var reSource = $("#re-update-template").html();
	var reTemplate = Handlebars.compile(reSource);				
	var reData = {
		"content":text,
		"length":text.length
	}
	var reItem = reTemplate(reData);
	div.append(reItem);
})

/**
	대댓글 작성 취소
 */
$(document).on("click","#re-cancel", function(e){
	e.preventDefault();
	div.children().remove();
	div.append(html);
})

/** 
	대댓글 수정
*/
$(document).on("click","#re-update", function(e){
	e.preventDefault();
	$("#re-seq").val(rootSeq);
	$("#re-boardId").val($("#boardId").text());
	
	if($("#loginUser").text()==''){
		$("#sa-warning").click();
	}else if($("#re-textBox").val()==''){
		$("#sa-basic").click();
	}else{
		$("#re-update-form").submit();
	}
})

/**
	댓글 채택
 */
$(document).on("click",".reply-chaetaek-btn", function(e){
	var boardId = $("#boardId").text();
	var seq = $(e.target).closest("div").children("div").text();
	Swal.fire({
        title: "해당 댓글을 채택하시겠습니까?",
        text: '채택은 취소할 수 없으며, 채택된 댓글이 있는 게시글은 수정 및 삭제가 불가능합니다.',
        icon: 'question',
         customClass: {
            confirmButton: 'btn btn-primary w-xs mt-2',
            cancelButton: 'btn btn-light w-xs mt-2',
        },
        confirmButtonText: "채택",
	    cancelButtonText: "취소",
        buttonsStyling: false,
        showCloseButton: true,
        showCancelButton: true,
    }).then(function (result) {
	    if (result.value) {
			location.href="./replyChaetaek.do?boardId="+boardId+"&seq="+seq;
	    }
	});
})