$(document).ready(function(){
	$(".sido-dropdown").click(function(e){
		$("#btn-sigungu").remove();
		$("#sido-title").text(e.target.text);			
		getGangeuisilList("sido", e.target.text.substr(0,2));
		if(e.target.text.substr(0,2)!="전체"){
			$("#sido-title").removeClass("btn-primary");
			$("#sido-title").addClass("btn-light");
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
			getGangeuisilList("all", e.target.text.substr(0,2));
		}		
	});
});

$(document).on('click', '.sigungu-dropdown', function(e) {
	$("#sigungu-title").text(e.target.text);
	if(e.target.text.substr(0,2)!="전체"){
		getGangeuisilList("sigungu", e.target.text.substr(0,e.target.text.indexOf("(")));
	} else {
		getGangeuisilList("sido", $("#sido-title").text().substr(0,2));
	}		

});

$(document).on('click', '.show-gangeuisilDetails', function(e) {
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
								            '<th scope="col"><i class="ri-group-line"></i>&nbsp;최대 인원</th>'+
								            '<th scope="col"><i class="ri-hand-coin-line"></i>&nbsp;1시간</th>'+
								            '<th scope="col"></th>'+
								    	'</tr>');
			for(i=0; i<data.length; i++){
				$("#"+data[i].gacoId).append('<tr>'+												            
											 	'<td scope="row">'+data[i].gagaName+'</td>'+
									            '<td scope="row">'+data[i].gagaMax+'</td>'+
									            '<td scope="row">'+data[i].gagaHourPrice+'</td>'+												            
									            '<td><a class="link-success click-btn">예약 <i class="ri-arrow-right-line align-middle"></i></a></td>'+
									        '</tr>');					
			}
			
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});
});

function getGangeuisilList(type, sendData){
	$.ajax({
		type:"post",
		url:"./getGangeuisilList.do",
		data:{
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
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});	
}