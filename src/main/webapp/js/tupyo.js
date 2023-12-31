$(document).ready(function() { 
	var hasTupyo = $('input[name="hasTupyo"]').val();
	if(hasTupyo == 'true'){
		var userId = $('input[name="accountId"]').val();
		var tupyoSeq = $('input[name="tupyoSeq"]').val();
		var tupyoStatus = $('input[name="tupyoStatus"]').val();
		if(tupyoStatus=='P'){
		$.ajax({
			url: './checkVoted.do',
			type: 'GET',
			data: {
				"tuusAccountId": userId,
				"tuopTupySeq": tupyoSeq
			},
			success: function(response) {
				if (response == "voted") {
					if($("#finishTupyo").length>0){
						$("#finishTupyo").show();
					}
					$("#tupyoList").hide();
					$("#tupyoResult").show();
					$("#selectComplete").hide();
					$("#reTupyo").show();
					tupyoResult();
				} 
				else {
					var teacherCount = $('input[name=teacher]').length;
					$("#selectInstrTitle").css('display', 'block');
					$("#tupyoResultTitle").css('display', 'none');
	
					if (teacherCount === 1) {
						$("#tupyoList").show();
						$('#agree-disagree-group').show();
						$('#list-group').hide();
					} else {
						$("#tupyoList").show();
						$('#agree-disagree-group').hide();
						$('#list-group').show();
					}
					if($("#finishTupyo").length>0){
						$("#finishTupyo").hide();
					}
					$("#tupyoResult").hide();
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
		}
		else if(tupyoStatus=='F'){
			$.ajax({
			url: './finishedTupyo.do',
			type: 'GET',
			data: {
				"tuopTupySeq": tupyoSeq
				},
			success:function(){
			
				$("#tupyoList").hide();
				$("#tupyoResult").show();
				$("#selectComplete").hide();
				$("#reTupyo").hide();
				$("#finishTupyo").hide();
				tupyoResult();
				},
			error:function(error){
				console.log(error);
				}
			});
		}
	}
	else if(hasTupyo=='false'){
		$("#masterBtn").show();
	}
});




//날짜 유효값 처리
document.addEventListener("DOMContentLoaded", function() {
	const dateInput = document.getElementById("dateInput");
	if(dateInput!=null){
		let today = new Date();
        let dd = String(today.getDate()).padStart(2, '0');
        let mm = String(today.getMonth() + 1).padStart(2, '0');
        let yyyy = today.getFullYear();
        
        today = yyyy + '-' + mm + '-' + dd;
        
        dateInput.setAttribute('min', today);
		dateInput.addEventListener("change", function() {
		const selectedDate = new Date(dateInput.value);
		const today = new Date();
		const diffDays = Math.abs(selectedDate - today) / (1000 * 60 * 60 * 24);
		if (selectedDate < today) {
			Swal.fire({
				icon: 'error',
				title: '오늘 이후의 날짜만 선택할 수 있습니다.',
				text: '',
			});
			dateInput.value = ""; // 입력 영역 초기화
		}else if(diffDays>7){
			Swal.fire({
				icon: 'error',
				title: '투표 기간은 1주일을 넘을 수 없습니다.',
				text: '',
			});
			dateInput.value = "";
		}
	});
	}
});







function tupyoResult(){
	
	var teacherCount = $('input[name="teacher"]').length;
	var tupySeq = $('input[name="tupyoSeq"]').val();
	var tuusOptionSeq = $('input[name="agreeTupyoOptionSeq"]').val();
	var tupyoStatus = $('input[name="tupyoStatus"]').val();
	if(tupyoStatus=='P'){
		if($("#finishTupyo").length>0){
			$("#finishTupyo").show();
		}
	}else{
		if($("#finishTupyo").length>0){
			$("#finishTupyo").hide();
		}
		
	}

	if(teacherCount==1){
		$.ajax({
			url: './agreeTupyoResult.do',
			type: 'GET',
			contentType: "application/json;charset=UTF-8",
			data: {
				"tupySeq":tupySeq,
				"tuusOptionSeq":tuusOptionSeq
			},
			success: function(response) {
				$("#selectInstrTitle").css('display', 'none');
				$("#tupyoResultTitle").css('display', 'block');
				
				var tupyoResultArray = new Array();
				tupyoResultArray.push(response.agreeCount);
				tupyoResultArray.push(response.disagreeCount);

				var ctx = document.getElementById("myChart").getContext('2d');

				if (myChart != '') {
					myChart.destroy();
				}

				myChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: ['찬성','반대'],
						datasets: [{
							label: '득표수',
							data: tupyoResultArray,
							backgroundColor: '#8977ad'
						}]
					},
					options: {
						indexAxis: 'y',
						maintainAspectRatio: false,
						scales: {
							x: {
								beginAtZero: true,
								ticks: {
									stepSize: 1
								}
							}
						}
					}
				});
			},
			error:function(error){
				console.log(error);
			}
		});
	}else{
		$.ajax({
			url: './tupyoResult.do',
			type: 'GET',
			contentType: "application/json;charset=UTF-8",
			data: {
				"tupySeq":tupySeq,
				"tuopSeq":tuusOptionSeq
			},
			success: function(response) {
				$("#selectInstrTitle").css('display', 'none');
				$("#tupyoResultTitle").css('display', 'block');
				
				var tupyoInstrsArray = new Array();
				for (let i = 0; i < response.tupyoOptionList.length; i++) {
					tupyoInstrsArray.push(response.tupyoOptionList[i].tuopInstr);
				}

				var tupyoResultArray = new Array();
				for (let i = 0; i < response.tupyoOptionList.length; i++) {
					tupyoResultArray.push(response.resultList[i].count);
				}
				var ctx = document.getElementById("myChart").getContext('2d');

				if (myChart != '') {
					myChart.destroy();
				}

				myChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: tupyoInstrsArray,
						datasets: [{
							label: '득표수',
							data: tupyoResultArray,
							backgroundColor: '#8977ad'
						}]
					},
					options: {
						indexAxis: 'y',
						maintainAspectRatio: false,
						scales: {
							x: {
								beginAtZero: true,
								ticks: {
									stepSize: 1
								}
							}
						}
					}
				});
				
			},
			error:function(error){
				console.log(error);
			}
		});
	}
};


var myChart = '';


function tupyoComplete() {

	var userId = $('input[name="accountId"]').val();

	// 강사 선택 투표일 때
	if ($('#list-group').is(':visible')) { 
		var radios = document.getElementsByName('teacher');
		var isChecked = false;
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked) {
				isChecked = true;
				break;
			}
		}

		if (!isChecked) {
			alert('강사를 선택해주세요.');
			return false;
		}
		
		var tuusOptionSeq = $('input[name="teacher"]:checked').val();
		
		$.ajax({
			url: './insertTupyoUser.do',
			type: 'POST',
			contentType: "application/json;charset=UTF-8",
			data:JSON.stringify({
				"tuusOptionSeq":tuusOptionSeq,
				"tuusAccountId":userId
			}),
			success:function(response){
				if(response==='finishedTupyo'){
					alert('이미 종료된 투표입니다. 페이지를 새로고침해주세요');
					return false;
				}else{
					$('#selectComplete').hide();
					$("#tupyoList").css('display', 'none');
					$("#tupyoResult").css('display', 'block');
					$("#reTupyo").css('display', 'inline-block');
					tupyoResult();
				}
			},
			error:function(error){
				console.log(error);
			}
		});

	} 
	// 찬반 투표일 때
	else if ($('#agree-disagree-group').is(':visible')) {  
		var tuusOptionSeq = $('input[name="agreeTupyoOptionSeq"]').val();
		var selectedVote = $("input[name='vote']:checked").val();
		var voteRadios = document.getElementsByName('vote');
		var isVoteChecked = false;
		
		for (var j = 0; j < voteRadios.length; j++) {
			if (voteRadios[j].checked) {
				isVoteChecked = true;
				break;
			}
		}

		if (!isVoteChecked) {
			alert('찬성 혹은 반대를 선택해주세요.');
			return false;
		}
		$.ajax({
			url: './agreeTupyo.do',
			type: 'POST',
			contentType: "application/json;charset=UTF-8",
			data:JSON.stringify({
				"tuusOptionSeq":tuusOptionSeq,
				"tuusAccountId":userId,
				"tuusAgree":selectedVote
			}),
			success:function(){

				$("#tupyoList").hide();
				$("#tupyoResult").show();
				$("#reTupyo").show();
				$('#selectComplete').hide();
				tupyoResult();
			},
			error:function(error){
				console.log(error);
			}
		});
	}
}


function reTupyo() {

	
	var userId = $('input[name="accountId"]').val();
	var teacherCount = $('input[name="teacher"]').length;
	var tupyClasId = $('input[name="tupyoClassId"]').val();
	var tupySeq = $('input[name="tupyoSeq"]').val();
	

	$.ajax({
		url: './reTupyo.do',
		type: 'GET',
		data: {
			"tuusAccountId": userId,
			"tupyClasId":tupyClasId,
			"tupySeq":tupySeq
		},
		success: function() {
			$("#tupyoList").css('display', 'block');
			$('#selectComplete').show();
			if(teacherCount==1){
				$('#agree-disagree-group').css('display', 'block');
				$('#list-group').css('display', 'none');
			}else {
				$('#agree-disagree-group').css('display', 'none');
				$('#list-group').css('display', 'block');
			}
			$("#tupyoResult").css('display', 'none');
			$("#reTupyo").css('display', 'none');
			
			$("#selectInstrTitle").css('display', 'block');
			$("#tupyoResultTitle").css('display', 'none');
			if($("#finishTupyo").length>0){
				$("#finishTupyo").hide();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});

}



function finishTupyo(){
	var tupyClasId = $('input[name="tupyoClassId"]').val();
	var tupySeq = $('input[name="tupyoSeq"]').val();
	var tuopSeq = $('input[name="agreeTupyoOptionSeq"]').val();
	$.ajax({
		url:'./finishTupyo.do',
		type:'GET',
		data:{
			"tupyClasId":tupyClasId,
			"tupySeq":tupySeq,
			"tuopSeq":tuopSeq
		},
		success:function(response){
			if(response==='confirm'){
				
			}else if(response==='disagree'){
				alert("찬성 투표수가 과반수를 넘기지 못했습니다\n투표를 다시 생성해주세요");
			}else if(response==='same'){
				alert("투표를 다시 진행해 주세요");
			}
			location.reload();

		},
		error:function(error){
			console.log(error);
		}
	});
	
}





function makeTupyo(){
	
	var dateInput = $('#dateInput').val();
	
	if(dateInput==''){
		alert("종료 일자를 선택해주세요");
		return false;
	}
	var tupyClasId = $('input[name="clasId"]').val();
	$.ajax({
		url:'./makeTupyo.do',
		type:'POST',
		data:{
			"tupyClasId":tupyClasId,
			"tupyEnddate":dateInput
		},
		success:function(){
			location.reload();
		},
		error:function(){}
	});
}


function closeWindow(){
	window.close();
}

