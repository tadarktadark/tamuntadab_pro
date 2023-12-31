/**
	페이지 로딩시 모달 이벤트 실행
 */
$(document).ready(function() {
    $('#zoomInModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var reason = button.data('reason'); // Extract info from data-* attributes
        var modal = $(this);
        modal.find('.modal-body h5').text(reason);
    });
    
});

/**
	메뉴 변경
 */
$(document).on("click","#show-myBoard",function(){
	$(".myPilgi").click();
	$(".myPilgi").tab('show');
})

/** 
	필기 버튼 클릭시 필기 목록 조회
*/
$(document).on("click", ".myPilgi", function(){
	getMyPilgi(1);
})

/**
	필기 목록 조회
 */
function getMyPilgi(page){
	$("#write-pilgi-tbody").children().remove();
	$.ajax({
    	url: "./myPilgi.do",
        method: "get",
        data:{
			"page":page
		},
        success: function(data){
			var board = data.bList;
			var page = data.pList;
			var listSource = $("#pilgi-list-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<board.length;i++){
		 		(function(i) {
		            Handlebars.registerHelper('viewI', function(options) {
		                if (board[i].viewGroup == '나') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateN', function(options) {
		                if (board[i].state == 'N') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateY', function(options) {
		                if (board[i].state == 'Y') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateP', function(options) {
		                if (board[i].state == 'P') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateD', function(options) {
		                if (board[i].state == 'D') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		           
		        })(i);
				           
				var listData = {
					"id":board[i].id,
					"title":board[i].title,
					"viewGroup":board[i].viewGroup, 
					"downloadGroup":board[i].downloadGroup
				}
				var listItem = listTemplate(listData);
				$("#write-pilgi-tbody").append(listItem);
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

/**
	상세보기 클릭시 필기 상세 페이지 이동
 */
$(document).on("click", ".view-pilgi", function(e){
	location.href="./communityDetails.do?board=pilgi&id="+$(e.target).attr('id');
})

/**
	필기 복원
 */
$(document).on("click",".pilgi-restore", function(e){
	var page = $(".comm-page").eq(0).find(".active").eq(0).find(".comm-link").attr("id")
	$.ajax({
    	url: "./restorePilgi.do",
        method: "post",
        data: {
			"id":$(e.target).attr("id")
		},
        success: function(data){
			if(data==1){
				getMyPilgi(page);
				$("#completion-btn").click();
			}
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
})

/**
	완료 alert창
 */
$(document).on("click", "#completion-btn", function(e){
	Swal.fire({
        title: '완료했습니다!',
        icon: 'success',
        customClass: {
            confirmButton: 'btn btn-primary w-xs mt-2  me-2'
        },
        buttonsStyling: false,
        showCloseButton: true
    })
})

/**
	필기 완전 삭제
 */
$(document).on("click", ".pilgi-delete", function(e){
	var page = $(".comm-page").eq(0).find(".active").eq(0).find(".comm-link").attr("id")
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
	        $.ajax({
		    	url: "./deletePilgi.do",
		        method: "post",
		        data: {
					"id":$(e.target).attr("id")
				},
		        success: function(data){
					if(data==1){
						getMyPilgi(page);
						$("#completion-btn").click();
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
	내 질문 버튼 클릭시 질문 목록 보기
 */
$(document).on("click", ".myJilmun", function(){
	getMyJilmun(1);
})

/**
	내 질문 목록 보기
 */
function getMyJilmun(page){
	$("#write-jilmun-tbody").children().remove();
	$.ajax({
    	url: "./myJilmun.do",
        method: "get",
        data:{
			"page":page
		},
        success: function(data){
			var board = data.bList;
			var page = data.pList;
			var listSource = $("#jilmun-list-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<board.length;i++){
		 		(function(i) {
		            Handlebars.registerHelper('chaetaekY', function(options) {
		                if (board[i].chaetaek == 'Y') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateN', function(options) {
		                if (board[i].state == 'N') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateY', function(options) {
		                if (board[i].state == 'Y') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateP', function(options) {
		                if (board[i].state == 'P') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateD', function(options) {
		                if (board[i].state == 'D') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		           
		        })(i);
				           
				var listData = {
					"id":board[i].id,
					"title":board[i].title
				}
				var listItem = listTemplate(listData);
				$("#write-jilmun-tbody").append(listItem);
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

/**
	질문 상세 보기
 */
$(document).on("click", ".view-jilmun", function(e){
	location.href="./communityDetails.do?board=jilmun&id="+$(e.target).attr('id');
})

/**
	자유 버튼 클릭시 내 자유 목록 보기
 */
$(document).on("click", ".myJayu", function(){
	getMyJayu(1);
})

/**
	자유 목록 보기
 */
function getMyJayu(page){
	$("#write-jayu-tbody").children().remove();
	$.ajax({
    	url: "./myJayu.do",
        method: "get",
        data:{
			"page":page
		},
        success: function(data){
			var board = data.bList;
			var page = data.pList;
			var listSource = $("#jayu-list-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<board.length;i++){
		 		(function(i) {
		            Handlebars.registerHelper('stateN', function(options) {
		                if (board[i].state == 'N') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateY', function(options) {
		                if (board[i].state == 'Y') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateP', function(options) {
		                if (board[i].state == 'P') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateD', function(options) {
		                if (board[i].state == 'D') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		           
		        })(i);
				           
				var listData = {
					"id":board[i].id,
					"title":board[i].title
				}
				var listItem = listTemplate(listData);
				$("#write-jayu-tbody").append(listItem);
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

/**
	자유 상세 보기
 */
$(document).on("click", ".view-jayu", function(e){
	location.href="./communityDetails.do?board=jayu&id="+$(e.target).attr('id');
})

/**
	내 댓글 선택시 댓글 목록 보기
 */
$(document).on("click", ".myReply", function(){
	getMyReply(1);
})

/**
	댓글 목록 보기
 */
function getMyReply(page){
	$("#write-reply-tbody").children().remove();
	$.ajax({
    	url: "./myReply.do",
        method: "get",
        data:{
			"page":page
		},
        success: function(data){
			var board = data.bList;
			var page = data.pList;
			var listSource = $("#reply-list-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<board.length;i++){
		 		(function(i) {
		            Handlebars.registerHelper('stateN', function(options) {
		                if (board[i].state == 'N') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateY', function(options) {
		                if (board[i].state == 'Y') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateP', function(options) {
		                if (board[i].state == 'P') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('stateD', function(options) {
		                if (board[i].state == 'D') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('boardI', function(options) {
		                if (board[i].chaetaek == 'I') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('boardY', function(options) {
		                if (board[i].chaetaek == 'Y') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('boardD', function(options) {
		                if (board[i].chaetaek == 'D') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('boardN', function(options) {
		                if (board[i].chaetaek == 'N') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('boardC', function(options) {
		                if (board[i].chaetaek == 'C') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		            Handlebars.registerHelper('boardG', function(options) {
		                if (board[i].chaetaek == 'G') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });
		           
		        })(i);
				           
				var listData = {
					"id":board[i].boardId,
					"seq":board[i].seq,
					"title":board[i].writerId,
					"content":board[i].content
				}
				var listItem = listTemplate(listData);
				$("#write-reply-tbody").append(listItem);
				$(".reply-content").eq(i).html($(".reply-content").eq(i).text());
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

/**
	댓글 상세 보기 페이지 이동
 */
$(document).on("click", ".view-reply", function(e){
	var boardId = $(e.target).attr('id');
	if(boardId.substring(0,2)=='PI'){
		location.href="./communityDetails.do?board=pilgi&id="+boardId;
	} else if(boardId.substring(0,2)=='JI'){
		location.href="./communityDetails.do?board=jilmun&id="+boardId;
	} else if(boardId.substring(0,2)=='JA'){				
		location.href="./communityDetails.do?board=jayu&id="+boardId;
	}
})

/**
	내 좋아요 클릭시 좋아요 목록 보기
 */
$(document).on("click", ".myLike", function(){
	getMyLike(1);
})

/**
	좋아요 목록 보기
 */
function getMyLike(page){
	$("#like-comm-tbody").children().remove();
	$.ajax({
    	url: "./myLike.do",
        method: "get",
        data:{
			"page":page
		},
        success: function(data){
			var board = data.bList;
			var page = data.pList;
			var listSource = $("#like-list-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<board.length;i++){				           
				var listData = {
					"id":board[i].id,
					"title":board[i].title,
					"regdate":board[i].regdate.substring(0,4)+"-"+board[i].regdate.substring(4,6)+"-"+board[i].regdate.substring(6),
					"update":board[i].update
				}
				var listItem = listTemplate(listData);
				$("#like-comm-tbody").append(listItem);
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

/**
	좋아요 상세 보기
 */
$(document).on("click", ".view-like", function(e){
	var boardId = $(e.target).attr('id');
	if(boardId.substring(0,2)=='PI'){
		location.href="./communityDetails.do?board=pilgi&id="+boardId;
	} else if(boardId.substring(0,2)=='JI'){
		location.href="./communityDetails.do?board=jilmun&id="+boardId;
	} else if(boardId.substring(0,2)=='JA'){				
		location.href="./communityDetails.do?board=jayu&id="+boardId;
	}
})

/**
	페이지 이동
 */
$(document).on("click",".comm-link",function(e){
	if($("#myComm").find(".active").attr("href")=='#write-pilgi'){		
		getMyPilgi($(e.target).attr("id"));	
	} else if($("#myComm").find(".active").attr("href")=='#write-jilmun'){		
		getMyJilmun($(e.target).attr("id"));	
	} else if($("#myComm").find(".active").attr("href")=='#write-jayu'){		
		getMyJayu($(e.target).attr("id"));	
	} else if($("#myComm").find(".active").attr("href")=='#write-reply'){		
		getMyReply($(e.target).attr("id"));	
	} else if($("#myComm").find(".active").attr("href")=='#like-comm'){		
		getMyLike($(e.target).attr("id"));	
	}
})

/**
	좋아요 취소
 */
$(document).on("click",".myLike-do",function(e){
	e.stopPropagation();
	var boardId = $(e.target).attr('id');
	var board = "";
	if(boardId.substring(0,2)=='PI'){
		board="pilgi";
	} else if(boardId.substring(0,2)=='JI'){
		board="jilmun";
	} else if(boardId.substring(0,2)=='JA'){				
		board="jayu";
	}
	Swal.fire({
        title: "좋아요를 취소하시겠습니까?",
        text: '취소한 글은 좋아요 목록에서 사라집니다.',
        icon: 'question',
         customClass: {
            confirmButton: 'btn btn-primary w-xs mt-2',
            cancelButton: 'btn btn-light w-xs mt-2 ms-2',
        },
        confirmButtonText: "취소",
	    cancelButtonText: "유지",
        buttonsStyling: false,
        showCloseButton: true,
        showCancelButton: true,
    }).then(function (result) {
		if (result.value) {
			$.ajax({
				type:"post",
				url:"./communityLike.do",
				data:{
					"type":$(e.target).attr("src").split("_")[1].split(".")[0],
					"id": $(e.target).attr("id"),
					"board":board
				},
				success: function(data){
					var page = $(".comm-page").eq(0).find(".active").eq(0).find(".comm-link").attr("id");
					getMyLike(page);
				},
				error: function(){
					alert("잘못된 요청입니다.");
				}
			});	
	    }
	   
	});
})

/**
	질문, 작성 완전 삭제
 */
$(document).on("click", ".comm-delete", function(e){
	var boardId = $(e.target).attr('id');
	var board = "";
	if(boardId.substring(0,2)=='JI'){
		board="jilmun";
	} else if(boardId.substring(0,2)=='JA'){				
		board="jayu";
	}
	var page = $(".comm-page").eq(0).find(".active").eq(0).find(".comm-link").attr("id")
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
	        $.ajax({
		    	url: "./myCommDelete.do",
		        method: "post",
		        data: {
					"id":$(e.target).attr("id"),
					"board":board
				},
		        success: function(data){
					if(data==1){
						if(boardId.substring(0,2)=='JI'){
							getMyJilmun(page);
						} else if(boardId.substring(0,2)=='JA'){				
							getMyJayu(page);
						}
						$("#completion-btn").click();
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
	댓글 완전 삭제
 */
$(document).on("click", ".reply-delete", function(e){
	var boardId = $(e.target).attr("id");
	var seq = $(e.target).closest("div").attr("id");
	var board = "";
	if(boardId.substring(0,2)=='PI'){
		board = "pilgi";
	} else if(boardId.substring(0,2)=='JI'){
		board = "jilmun";
	} else if(boardId.substring(0,2)=='JA'){				
		board = "jayu";
	}
	var page = $(".comm-page").eq(0).find(".active").eq(0).find(".comm-link").attr("id")
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
	        $.ajax({
		    	url: "./myReplyDelete.do",
		        method: "post",
		        data: {
					"seq":seq,
					"boardId":boardId,
					"board":board
				},
		        success: function(data){
					if(data==1){
						getMyReply(page);
						$("#completion-btn").click();
					}
				},
				error: function(){
					alert("잘못된 요청입니다.");
				}
		    });
	    }
	});
})