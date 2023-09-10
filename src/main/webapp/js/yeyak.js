$(document).ready(function(){
	getGangeuisilSidoList();
	getGangeuisilList("1","all", "전체");
});

$(document).on('click', '.sido-dropdown', function(e){
	$("#multiCollapseExample2").removeClass("show");
	$("#btn-sigungu").remove();
	$("#sido-title").text(e.target.text);			
	if(e.target.text.substr(0,2)!="전체"){
		$("#sido-title").removeClass("btn-primary");
		$("#sido-title").addClass("btn-light");
		getGangeuisilList("1","sido", e.target.text.substr(0,2));
		$.ajax({
			type:"get",
			url:"./getGangeuisilSigunguList.do",
			data:{
				"sido":e.target.text.substr(0,2)
			},
			success: function(data){
				$("#btn-sido").after('<div class="btn-group" id="btn-sigungu">'+
									    '<button id="sigungu-title" class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
									        '전체('+data.count+')'+
									    '</button>'+
									    '<div class="dropdown-menu dropdownmenu-primary sigungu-group" id="sigungu-group" data-simplebar data-simplebar-track="primary">')
				for(var i=0; i<data.list.length; i++){
					$("#sigungu-group").append('<a class="dropdown-item sigungu-dropdown click-btn">'+data.list[i]+'</a>'
									)
				}
				$("#btn-sigungu").after('</div>'+
									'</div>')
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});
	} else {
		$("#sido-title").removeClass("btn-light");
		$("#sido-title").addClass("btn-primary");
		getGangeuisilList("1","all", e.target.text.substr(0,2));
	}
});
$(document).on('click', '.sigungu-dropdown', function(e) {
	$("#multiCollapseExample2").removeClass("show");
	$("#sigungu-title").text(e.target.text);
	if(e.target.text.substr(0,2)!="전체"){
		getGangeuisilList("1","sigungu", e.target.text.substr(0,e.target.text.indexOf("(")));
	} else {
		getGangeuisilList("1","sido", $("#sido-title").text().substr(0,2));
	}		

});

$(document).on('click', '.show-gangeuisilDetails', function(e) {
	$("#multiCollapseExample2").addClass("show");
	$.ajax({
		type:"get",
		url:"./getGangeuisilDetailList.do",
		data:{
			"gacoId":e.target.value
		},
		success: function(data){
			$("#"+data[0].gacoId).children().remove();
			$("#"+data[0].gacoId).append('<tr>'+
								            '<th scope="col"><i class="ri-home-wifi-fill"></i>&nbsp;강의실</th>'+
								            '<th scope="col"><i class="ri-group-line"></i>&nbsp;최대</th>'+
								            '<th scope="col"><i class="ri-hand-coin-line"></i>&nbsp;1시간</th>'+
								            '<th scope="col"></th>'+
								    	'</tr>');
			for(i=0; i<data.length; i++){
				$("#"+data[i].gacoId).append('<tr>'+		            
											 	'<td scope="row">'+data[i].gagaName+'</td>'+
									            '<td scope="row">'+data[i].gagaMax+'</td>'+
									            '<td scope="row">'+data[i].gagaHourPrice+'</td>'+												            
									            '<td><a id="'+data[i].gagaId+'" class="link-success click-btn yeyak-btn">예약 <i class="ri-arrow-right-line align-middle"></i></a></td>'+
									        '</tr>');					
			}
			
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});
});
$(document).on('click', '.page-link', function(e) {
	$("#multiCollapseExample2").removeClass("show");
	var sido = $("#sido-title").text().substr(0,2);
	var sigungu = $("#sigungu-title").text().substr(0,$("#sigungu-title").text().indexOf("("));
	if(sido == "전체"){		
		getGangeuisilList(e.target.id, "all", "전체");
	} else if(sigungu == "전체" || sigungu == ""){
		getGangeuisilList(e.target.id, "sido", sido);
	} else {
		getGangeuisilList(e.target.id, "sigungu", sigungu);		
	}
});
function getGangeuisilSidoList(){
	$.ajax({
		type:"get",
		url:"./getGangeuisilSidoList.do",
		success: function(data){
			var html = '<div class="btn-group" id="btn-sido">'+
					    '<button id="sido-title" class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
					        '전체('+data.count+')'+
					    '</button>'+
					    '<div class="dropdown-menu dropdownmenu-primary sido-group" data-simplebar data-simplebar-track="primary">'+
					        '<a class="dropdown-item sido-dropdown click-btn">전체('+data.count+')</a>';
							for(var i=0; i<data.list.length;i++){
						        html+='<a class="dropdown-item sido-dropdown click-btn">'+data.list[i]+'</a>';								
							}				
				html +=	'</div>'+
					'</div>'+
					'<br>'+
					'<br>'+
					'<div class="row">'+
					    '<div class="col">'+
	 						'<div class="accordion custom-accordionwithicon-plus collapse multi-collapse show" id="accordionWithplusicon">'+
							'</div>'+
							'<br>'+
							'<ul id="pagination" class="pagination pagination-rounded justify-content-center">'+
							'</ul>'+
					    '</div>'+
					    '<div class="col">'+
					        '<div class="collapse multi-collapse" id="multiCollapseExample2">'+
					            '<div class="card card-body mb-0" id="multiCollapseExample2-content">'+
					            '<div>hi</div>'+
					            '</div>'+
					        '</div>'+
					    '</div>'+
					'</div>';
			$("#yeyak-container").append(html);
			changePage(data.page.startPage, data.count, data.page.endPage, data.page.page);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});		
}

$(document).on('click', '.yeyak-btn', function(e){
	var gagaName = e.target.parentNode.parentNode.firstChild.textContent;
	if(gagaName=='예약 '){
		gagaName=e.target.parentNode.parentNode.parentNode.firstChild.textContent;
	}
	var html = '<b class="ri-home-wifi-fill"> '+gagaName+'</b>';
	$('.form-header').children().remove();
	$('.form-header').append(html);
	$("#multiCollapseExample2-content").children().remove();
	$("#multiCollapseExample2-content").append($("#yeyak-form").html());
	if(e.target.id){
		var gagaId = e.target.id;
	} else {		
		var gagaId = e.target.parentNode.id;
	}
	$("#gayeGagaId").text(gagaId);
	console.log($("#gayeGagaId").text());
	$.ajax({
		type:"post",
		url:"./getYeyakDateList.do",
		data:{
			"gagaId":gagaId
		},
		success: function(data){
			console.log(data);
			flatpickr.localize(flatpickr.l10ns.ko);
			$('.flatpickr').flatpickr({
				local: 'ko',
			    disable: [
			    //몇개 적을일 없는 경우
			    "2023-09-20", 
			    ],
			    dateFormat: "Y-m-d",
			    minDate: "today", // 오늘부터 시작
    			maxDate: new Date().fp_incr(28) // 오늘부터 28일 이내
			  });
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});
});

function getGangeuisilList(page, type, sendData){
	$.ajax({
		type:"post",
		url:"./getGangeuisilList.do",
		data:{
			"page":page,
			"type":type,
			"sendData":sendData
		},
		success: function(data){
			$("#accordionWithplusicon").children().remove();
			for(var i=0; i<data.list.length; i++){
				$("#accordionWithplusicon").append('<div class="accordion-item">'+
												        '<h2 class="accordion-header" id="accordionwithplusExample'+(i+1)+'">'+
												            '<button class="accordion-button collapsed show-gangeuisilDetails" type="button"'+ 
												            		'data-bs-toggle="collapse" data-bs-target="#accor_plusExamplecollapse'+(i+1)+'"'+ 
												            		'aria-expanded="true" aria-controls="accor_plusExamplecollapse'+(i+1)+'"'+
												            		'value="'+data.list[i].gacoId+'">'+
												                data.list[i].gacoName+
												            '</button>'+
												        '</h2>'+
												        '<div id="accor_plusExamplecollapse'+(i+1)+'" class="accordion-collapse collapse" aria-labelledby="accordionwithplusExample'+(i+1)+'" data-bs-parent="#accordionWithplusicon">'+
												            '<div class="accordion-body">'+
												            	'<table class="table table-nowrap">'+
												            		'<thead>'+
																        '<tr>'+
																            '<th scope="col" colspan="4"><i class="ri-map-pin-line"></i>&nbsp;'+data.list[i].gacoJuso+'</th>'+
																        '</tr>'+
																        '<tr>'+
																            '<th scope="col"><i class="ri-haze-line"></i>&nbsp;오픈</th>'+
																            '<td scope="row">'+data.list[i].gacoOpen.substr(0,2)+':'+data.list[i].gacoOpen.substr(2,4)+'</td>'+
																            '<th scope="col"><i class="ri-moon-clear-line"></i>&nbsp;마감</th>'+
																            '<td scope="row">'+data.list[i].gacoClose.substr(0,2)+':'+data.list[i].gacoClose.substr(2,4)+'</td>'+
																        '</tr>'+
																    '</thead>'+
																    '<tbody id="'+data.list[i].gacoId+'">'+												    
																    '</tbody>'+
																'</table>'+
												            '</div>'+
												        '</div>'+
												    '</div>');
			}
			changePage(data.page.startPage, data.count, data.page.endPage, data.page.page);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});	
}

function changePage(start, count, end, page){
	$("#pagination").children().remove()
	var html =	'<li class="page-item">'+
		        '<a id="'+(start-count)+'" class="page-link click-btn"><i class="mdi mdi-chevron-left"></i></a>'+
		    '</li>';
		for(var i=start; i<=end;i++){
			html +=
			    '<li class="page-item '+((i==page)?"active":"")+'">'+
			        '<a id="'+i+'" class="page-link click-btn">'+i+'</a>'+
			    '</li>'									
			;
		}
		    html +=
		    '<li class="page-item">'+
		        '<a id="'+(start+count)+'"class="page-link click-btn"><i class="mdi mdi-chevron-right"></i></a>'+
		    '</li>';
	$("#pagination").append(html);
}