$(document).ready(function(){
	
	getMaxSingoList(1)
});

function getMaxSingoList(page){
	$("#write-pilgi-tbody").children().remove();
	$.ajax({
    	url: "./getMaxSingoList.do",
        method: "get",
        data:{
			"page":page
		},
        success: function(data){
			var board = data.bList;
			var page = data.pList;
			
			$("#singo-list-tbody").children().remove();
			
			var listSource = $("#singo-list-template").html();
			var listTemplate = Handlebars.compile(listSource);
			for(i=0; i<board.length;i++){
		 		(function(i) {
		            Handlebars.registerHelper('stateB', function(options) {
		                if (board[i].state == 'B') {
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
		            Handlebars.registerHelper('stateP', function(options) {
		                if (board[i].state == 'P') {
		                    return options.fn(this);
		                } else {
		                    return options.inverse(this);
		                }
		            });		           
		        })(i);
				
				var sList = board[i].sayuList;
				var sayuData = [];
				for(j=0; j<sList.length; j++){
					sayuData.push({"sayuAccountId":sList[j].sayuAccountId,
								"category":sList[j].category,
								"content":sList[j].content,
								"regdate":sList[j].regdate});				
				}
				var listData = {
					"id":board[i].id,
					"daesangId":board[i].daesangId,
					"accountId":board[i].accountId, 
					"i":i,
					"daesangContent":board[i].daesangContent,
					"sayu":sayuData
				}
				var listItem = listTemplate(listData);
				$("#singo-list-tbody").append(listItem);
			}
			pageChange(page.startPage, page.endPage, page.page, page.totalpage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
}

$(document).on("click",".page-link",function(e){
	getMaxSingoList($(e.target).attr("id"));
})

$(document).on("click",".viewDetail", function(e){
	if($(e.target).closest("tr").hasClass("click")){
		$(e.target).closest("tr").removeClass("click");
		$(".accordion-collapse").removeClass("show");
		$(".view-btn").removeClass("bg-primary-subtle")
		$(".view-btn").removeClass("text-primary")
		$(".view-btn").addClass("bg-primary")
		$(".view-btn").text("열기")
	} else {
		$(e.target).closest("tr").addClass("click");
		$(".accordion-collapse").removeClass("show");
		$(e.target).closest("tr").next().addClass("show");		
		$(e.target).closest("tr").find(".view-btn").addClass("bg-primary-subtle")
		$(e.target).closest("tr").find(".view-btn").addClass("text-primary")
		$(e.target).closest("tr").find(".view-btn").removeClass("bg-primary")
		$(e.target).closest("tr").find(".view-btn").text("닫기")
	}
})

$(document).on("click", ".updateB", function(e){
	Swal.fire({
        title: "반려하시겠습니까?",
        text: '반려시 해당 게시물이 게시됩니다.',
        icon: 'question',
         customClass: {
            confirmButton: 'btn btn-primary w-xs mt-2',
            cancelButton: 'btn btn-light w-xs mt-2 ms-2',
        },
        confirmButtonText: "반려",
	    cancelButtonText: "취소",
        buttonsStyling: false,
        showCloseButton: true,
        showCancelButton: true,
    }).then(function (result) {
		if (result.value) {
			adminAction($(e.target).closest("table").closest("tr").prev().attr("id"), "B");
	    }
	   
	});
})

$(document).on("click", ".updateD", function(e){
	Swal.fire({
	    title: "수리하시겠습니까?",
	    text: "수리시 해당 게시글이 삭제됩니다.",
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
			adminAction($(e.target).closest("table").closest("tr").prev().attr("id"), "D");
	    }
	});
	  
})

function adminAction(id, state){
	
	$.ajax({
		type:"post",
		url:"./adminAction.do",
		data:{
			"id":id,
			"state": state
		},
		success: function(data){
			if(data>0){
				getMaxSingoList(1);
			}
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});	
}