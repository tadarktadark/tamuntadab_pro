$(document).ready(function() {
	getMyYeyakList(1);
});

function getMyYeyakList(page){
//	$("#write-pilgi-tbody").children().remove();
//	$.ajax({
//    	url: "./myPilgi.do",
//        method: "get",
//        data:{
//			"page":page
//		},
//        success: function(data){
//			var board = data.bList;
//			var page = data.pList;
//			var listSource = $("#pilgi-list-template").html();
//			var listTemplate = Handlebars.compile(listSource);
//			for(i=0; i<board.length;i++){
//		 		(function(i) {
//		            Handlebars.registerHelper('viewI', function(options) {
//		                if (board[i].viewGroup == '나') {
//		                    return options.fn(this);
//		                } else {
//		                    return options.inverse(this);
//		                }
//		            });
//		            Handlebars.registerHelper('stateN', function(options) {
//		                if (board[i].state == 'N') {
//		                    return options.fn(this);
//		                } else {
//		                    return options.inverse(this);
//		                }
//		            });
//		            Handlebars.registerHelper('stateY', function(options) {
//		                if (board[i].state == 'Y') {
//		                    return options.fn(this);
//		                } else {
//		                    return options.inverse(this);
//		                }
//		            });
//		            Handlebars.registerHelper('stateP', function(options) {
//		                if (board[i].state == 'P') {
//		                    return options.fn(this);
//		                } else {
//		                    return options.inverse(this);
//		                }
//		            });
//		            Handlebars.registerHelper('stateD', function(options) {
//		                if (board[i].state == 'D') {
//		                    return options.fn(this);
//		                } else {
//		                    return options.inverse(this);
//		                }
//		            });
//		           
//		        })(i);
//				           
//				var listData = {
//					"id":board[i].id,
//					"title":board[i].title,
//					"viewGroup":board[i].viewGroup, 
//					"downloadGroup":board[i].downloadGroup
//				}
//				var listItem = listTemplate(listData);
//				$("#write-pilgi-tbody").append(listItem);
//			}
//			pageChange(page.startPage, page.endPage, page.page, page.totalpage);
//		},
//		error: function(){
//			alert("잘못된 요청입니다.");
//		}
//    });
}