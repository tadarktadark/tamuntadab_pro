
/**
	로드시 시도 리스트 및 강의실 리스트 가져오기
 */
$(document).ready(function(){
	getGangeuisilSidoList();
	getGangeuisilList("1","all", "전체");
});

/*
	강의실이 존재하는 시도 목록을 가져옴
 */
function getGangeuisilSidoList(){
	$.ajax({
		type:"get",
		url:"./getGangeuisilSidoList.do",
		success: function(data){
			$(".sido-title span").text(data.count);
			var html = "";
			for(var i=0; i<data.list.length;i++){				
				html += '<a class="dropdown-item sido-dropdown click-btn">'+data.list[i]+'</a>';
			}
			$(".sido-group .simplebar-content").append(html);
			pageChange(data.page.startPage, data.page.endPage, data.page.page, data.page.totalPage);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});		
}

/*
	해당 페이지, 시도/시군구에 맞는 강의실 리스트를 가져옴
*/
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
			var html = "";
			for(var i=0; i<data.list.length; i++){
				html += '<div class="accordion-item">'+
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
										        '<tr id="'+data.list[i].gacoLat+'&'+data.list[i].gacoLon+'">'+
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
						    '</div>';
			}
			$("#gangeuisilList").html(html);
			pageChange(data.page.startPage, data.page.endPage, data.page.page, data.page.totalPage)
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});	
}

/*
	시도 변경시 시군구 리스트 가져옴
 */
$(document).on('click', '.sido-dropdown', function(e){
	$("#multiCollapseExample2").removeClass("show");
	$("#btn-sigungu").remove();
	$("#sido-title").text($(e.target).text());			
	if(e.target.text.substring(0,2)!="전체"){
		$("#sido-title").removeClass("btn-primary");
		$("#sido-title").addClass("btn-soft-primary");
		getGangeuisilList("1","sido", e.target.text.substr(0,2));
		$.ajax({
			type:"get",
			url:"./getGangeuisilSigunguList.do",
			data:{
				"sido":e.target.text.substr(0,2)
			},
			success: function(data){
				$("#btn-sido").after('<div class="btn-group mb-1 ms-1" id="btn-sigungu">'+
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
		$("#sido-title").removeClass("btn-soft-primary");
		$("#sido-title").addClass("btn-primary");
		getGangeuisilList("1","all", e.target.text.substr(0,2));
	}
});

/*
	시군구 변경시 목록 변경
*/
$(document).on('click', '.sigungu-dropdown', function(e) {
	$("#multiCollapseExample2").removeClass("show");
	$("#sigungu-title").text(e.target.text);
	if(e.target.text.substr(0,2)!="전체"){
		getGangeuisilList("1","sigungu", e.target.text.substr(0,e.target.text.indexOf("(")));
	} else {
		getGangeuisilList("1","sido", $("#sido-title").text().substr(0,2));
	}		
});

/*
	개별 강의실 클릭시 상세보기
 */
$(document).on('click', '.show-gangeuisilDetails', function(e) {
	
	$(e.target).closest("div").siblings().find("button").addClass("collapsed");
	$(e.target).closest("div").siblings().find("h2").next().removeClass("show");
	
	if($(e.target).hasClass("collapsed")&&$("#multiCollapseExample2").hasClass("show")){		
		$("#multiCollapseExample2-content").children().remove();
		$("#multiCollapseExample2").removeClass("show");
	} else {		
		$("#multiCollapseExample2").addClass("show");
		$("#multiCollapseExample2-content").children().remove();
		
		var addr = $(e.target).closest(".accordion-item").find("thead").children("tr").eq(0).attr("id");
		var mapSource = $("#map-template").html();
		var mapTemplate = Handlebars.compile(mapSource);
		var mapItem = mapTemplate();
		$("#multiCollapseExample2-content").append(mapItem);
		initMap(addr.substring(addr.indexOf("&")+1),addr.substring(0,addr.indexOf("&")))
	}
	
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

/*
	페이지 변경시 리스트 변경
 */
$(document).on("click",".page-link",function(e){
	$("#multiCollapseExample2").removeClass("show");
	var sido = $("#sido-title").text().substr(0,2);
	var sigungu = $("#sigungu-title").text().substr(0,$("#sigungu-title").text().indexOf("("));
	if(sido == "전체"){		
		getGangeuisilList($(e.target).attr("id"), "all", "전체");
	} else if(sigungu == "전체" || sigungu == ""){
		getGangeuisilList($(e.target).attr("id"), "sido", sido);
	} else {
		getGangeuisilList($(e.target).attr("id"), "sigungu", sigungu);		
	}
	$("#multiCollapseExample2-content").children().remove();
	$("#multiCollapseExample2").removeClass("show");
})

/**
	예약 버튼 클릭시 예약 실행
 */
$(document).on('click', '.yeyak-btn', function(e){
	var gagaName = e.target.parentNode.parentNode.firstChild.textContent;
	var gyeoljeHour = "";
	if(gagaName=='예약 '){
		gagaName=e.target.parentNode.parentNode.parentNode.firstChild.textContent;
		gyeoljeHour=e.target.parentNode.parentNode.parentNode.childNodes[2].textContent;
	} else {		
		gyeoljeHour=e.target.parentNode.parentNode.childNodes[2].textContent;
	}
	if(e.target.id){
		var gagaId = e.target.id;
	} else {		
		var gagaId = e.target.parentNode.id;
	}
	$("#multiCollapseExample2-content").children().remove();
	
	if($(".dropdown-header")[0]!=undefined){
		var formSource = $("#form-template").html();
		var formTemplate = Handlebars.compile(formSource);
		var formData = {
			"gayeAccountId":gagaName,
			"gayeGagaId":gagaId,
			"gagaName":gagaName,
			"gyeoljeHour":gyeoljeHour
		}
		var formItem = formTemplate(formData);
		$("#multiCollapseExample2-content").append(formItem);
		
		$.ajax({
			type:"post",
			url:"./getYeyakInfo.do",
			data:{
				"gagaId":gagaId,
				"accountId":$("#gayeAccountId").val(),
			},
			success: function(data){
				flatpickr.localize(flatpickr.l10ns.ko);
				$('.flatpickr').flatpickr({
					local: 'ko',
				    disable: [
					    data.date 
				    ],
				    dateFormat: "Y-m-d",
				    minDate: "today", // 오늘부터 시작
	    			maxDate: new Date().fp_incr(28) // 오늘부터 28일 이내
				  });
				$("#classSelect").html(data.html);
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});
	} else {
		$("#sa-warning").click();
	}
});

/**
	날짜 변동시 여유시간 변경
 */
$(document).on('change', '#dateInput', function(e){
	$.ajax({
		type:"post",
		url:"./getYeyakInfo.do",
		data:{
			"gagaId":$("#gayeGagaId").val(),
			"date":e.target.value
		},
		success: function(data){
			$("#timeInput").html(data);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});
});

/**
	클래스 선택시 함께 결제하기 활성화
 */
$(document).on('change', '#classSelect', function(e){
	if(e.target.value!="선택 안함"){
		$("#payment").prop("checked", true);
		$("#payment").attr("disabled", false);
	} else {
		$("#payment").prop("checked", false);
		$("#payment").attr("disabled", true);	
	}
});

/**
	시간 선택시 금액, 종료시간 자동 계산
 */
$(document).on('click', '.time-btn', function(e){
	var id = "#"+e.target.id
	var length = $("#timeInput .btn-primary").length;
	if($(id).hasClass("btn-outline-primary")){
		$(id).removeClass("btn-outline-primary");
		$(id).addClass("btn-primary");
		var start = Number($("#timeInput .btn-primary").eq(0).attr("id"));
		var end = Number($("#timeInput .btn-primary").eq(length).attr("id"));
		for(var i=start+100;i<end;i+=100 ){
			id="#"+i;
			$(id).removeClass("btn-outline-primary");
			$(id).addClass("btn-primary");				
		}
	} else {
		$(id).removeClass("btn-primary");
		$(id).addClass("btn-outline-primary");
		var target = Number(e.target.id);
		var end = Number($("#timeInput .btn-primary").eq(length-2).attr("id"));
		for(var i=target;i<=end;i+=100){
			id="#"+i;
			$(id).removeClass("btn-primary");
			$(id).addClass("btn-outline-primary");
		}
	}
	var first = Number($("#timeInput .btn-primary").eq(0).attr("id"));
	var last = Number($("#timeInput .btn-primary").last().attr("id"))+100;
	$("#endInput").val(String(last).substring(0,2)+":"+String(last).substring(2));
	var gyeoljeWon = ((last-first)/100*Number($("#gyeolje-hour").val())).toLocaleString('ko-KR')+" 원";
	$("#gyeolje-won").val(gyeoljeWon);
});

/**
	예약하기
 */
$(document).on('click','#yeyakSubmit', function(e){
	e.preventDefault();
	$("#contactNumber").attr("disabled", false);
	$("#gyeolje-won").attr("disabled", false);
	
	formArray = $("#yeyakForm").serializeArray()
	
	var first = Number($("#timeInput .btn-primary").eq(0).attr("id"));
	var last = Number($("#timeInput .btn-primary").last().attr("id"))+100;
	var hours = last-first
	
	formArray[formArray.length] = {"name": "gayeStartTime", "value": (first.toString().length<4)?"0"+first.toString():first.toString()};
	formArray[formArray.length] = {"name": "gayeHours", "value": hours/100};
	
	for (let i = 0; i < formArray.length; i++) {
		if (formArray[i].name === "gayeYeyakDate") {
	    	formArray[i].value = formArray[i].value.replace(/-/g, "");
	    	continue;
		} else if (formArray[i].name === "gayeClasId"){
			if(formArray[i].value === "선택 안함"){
				formArray[i].value = 0;				
			} else {
				var className = $("#"+formArray[i].value).text();
				className = className.substring(0,className.indexOf("(")-1);
				formArray[formArray.length] = {"name": "gayeGyeoljeUser", "value": className};
			}
			continue;
		} else if (formArray[i].name === "gyeoGeumaek"){
			formArray[i].value = formArray[i].value.replace(",","");
			formArray[i].value = formArray[i].value.replace(" 원","");
			formArray[i].value = Number(formArray[i].value);
			continue;
		} else if (formArray[i].name === "gayeGyeoljeType" && formArray[i].value === "on"){
			formArray[i].value = "B";
			continue;
		}
	}
		
	$.ajax({
    	url: "./insertYeyakInfo.do",
        method: "post",
        data:formArray,
        success: function(data){
			if(data >0){
				Swal.fire({
			        title: '예약이 완료되었습니다!',
			        text: data+" 원은 타문타답이 보태드립니다! 입실 전까지 결제를 완료해주세요!",
			        icon: 'success',
			        customClass: {
			            confirmButton: 'btn btn-primary w-xs mt-2  me-2'
			        },
			        buttonsStyling: false,
			        showCloseButton: true
			    })
			} else if (data==0){
				Swal.fire({
			        title: '예약이 완료되었습니다!',
			        text: "입실 전까지 결제를 완료해주세요!",
			        icon: 'success',
			        customClass: {
			            confirmButton: 'btn btn-primary w-xs mt-2  me-2'
			        },
			        buttonsStyling: false,
			        showCloseButton: true
			    })
			}
			$("#yeyakForm")[0].reset();
			var html = '<input type="text" class="form-control flatpickr" placeholder="날짜 선택시 버튼이 생성됩니다." disabled="disabled">';
			$("#timeInput").html(html);

		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
    });
});

/**
	지도 불러오기
 */
function initMap(lon,lat){
	
	var position = new naver.maps.LatLng(lon, lat);

	var map = new naver.maps.Map('map', {
	    center: position,
	    zoom: 15
	});
	
	var markerOptions = {
	    position: position,
	    map: map,
	    icon: './image/location-pin.png'
	};
	
	var marker = new naver.maps.Marker(markerOptions);
	
}