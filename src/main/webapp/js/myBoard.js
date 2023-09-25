$(document).ready(function() {
    $('#zoomInModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var reason = button.data('reason'); // Extract info from data-* attributes
        var modal = $(this);
        modal.find('.modal-body h5').text(reason);
    });
    
});

$(document).on("click", ".myPilgi", function(){
	getMyPilgi(1);
})

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
			pageChange(page.startPage, page.endPage, page.page, page.totalpage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

$(document).on("click", ".view-pilgi", function(e){
	location.href="./communityDetails.do?board=pilgi&id="+$(e.target).attr('id');
})

$(document).on("click",".pilgi-restore", function(e){
	var page = $(".comm-page").eq(0).find(".active").eq(0).find(".page-link").attr("id")
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

$(document).on("click", ".pilgi-delete", function(e){
	var page = $(".comm-page").eq(0).find(".active").eq(0).find(".page-link").attr("id")
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

$(document).on("click", ".myJilmun", function(){
	getMyJilmun(1);
})

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
			pageChange(page.startPage, page.endPage, page.page, page.totalpage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

$(document).on("click", ".view-jilmun", function(e){
	location.href="./communityDetails.do?board=jilmun&id="+$(e.target).attr('id');
})

$(document).on("click", ".myJayu", function(){
	getMyJayu(1);
})

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
			pageChange(page.startPage, page.endPage, page.page, page.totalpage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

$(document).on("click", ".view-jayu", function(e){
	location.href="./communityDetails.do?board=jayu&id="+$(e.target).attr('id');
})

$(document).on("click", ".myReply", function(){
	getMyReply(1);
})

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
		           
		        })(i);
				           
				var listData = {
					"id":board[i].boardId,
					"title":board[i].writerId,
					"content":board[i].content
				}
				var listItem = listTemplate(listData);
				$("#write-reply-tbody").append(listItem);
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalpage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

$(document).on("click", ".view-reply", function(e){
	console.log($(e.target).attr('id'));
//	location.href="./communityDetails.do?board=jayu&id="+$(e.target).attr('id');
})

$(document).on("click",".page-link",function(e){
	if($("#myComm").find(".active").attr("href")=='#write-pilgi'){		
		getMyPilgi($(e.target).attr("id"));	
	} else if($("#myComm").find(".active").attr("href")=='#write-jilmun'){		
		getMyJilmun($(e.target).attr("id"));	
	} else if($("#myComm").find(".active").attr("href")=='#write-jayu'){		
		getMyJayu($(e.target).attr("id"));	
	} else if($("#myComm").find(".active").attr("href")=='#write-reply'){		
		getMyReply($(e.target).attr("id"));	
	} 
})