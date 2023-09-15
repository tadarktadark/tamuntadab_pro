$(document).ready(function() { 
	var hasTupyo = $('input[name="hasTupyo"]').val();
	if(hasTupyo == 'true'){
		var userId = $('input[name="accountId"]').val();
		var tupyoSeq = $('input[name="tupyoSeq"]').val();
		var tupyoStatus = $('input[name="tupyoStatus"]').val();
		console.log("투표상태",tupyoStatus);
		$.ajax({
			url: './checkVoted.do',
			type: 'GET',
			data: {
				"tuusAccountId": userId,
				"tuopTupySeq": tupyoSeq
			},
			success: function(response) {
				if(tupyoStatus=='P'){
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
				}else if(tupyoStatus=='F'){
					$("#tupyoList").hide();
					$("#tupyoResult").show();
					$("#selectComplete").hide();
					$("#reTupyo").hide();
					$("#finishTupyo").hide();
					tupyoResult();
				}
			},
			error: function(error) {
				console.log("오류");
				console.log(error);
			}
		});
	}else if(hasTupyo=='false'){
		$("#masterBtn").show();
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

				console.log(tupyoResultArray);
				
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
									stepSize: 1 // X축의 눈금 단위 설정
								}
							}
						}
					}
				});
			},
			error:function(error){
				console.log("오류");
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
				"tuusOptionSeq":tuusOptionSeq
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

				console.log(tupyoInstrsArray);
				console.log(tupyoResultArray);

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
									stepSize: 1 // X축의 눈금 단위 설정
								}
							}
						}
					}
				});
				
			},
			error:function(error){
				console.log("오류");
				console.log(error);
			}
		});
	}
};


var myChart = '';


function tupyoComplete() {

	var userId = $('input[name="accountId"]').val();

	
	if ($('#list-group').is(':visible')) { // 강사 선택 투표일 때
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
			success:function(){
				$('#selectComplete').hide();
				$("#tupyoList").css('display', 'none');
				$("#tupyoResult").css('display', 'block');
				$("#reTupyo").css('display', 'inline-block');
				tupyoResult();
			},
			error:function(error){
				console.log("오류");
				console.log(error);
			}
		});

	} else if ($('#agree-disagree-group').is(':visible')) {  // 찬반 투표일 때
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
				
				
				console.log(selectedVote);

				$("#tupyoList").hide();
				$("#tupyoResult").show();
				$("#reTupyo").show();
				$('#selectComplete').hide();
				tupyoResult();
			},
			error:function(error){
				console.log("오류");
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
			console.log("오류");
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
		success:function(){
			$("#tupyoList").hide();
			$("#tupyoResult").show();
			$("#reTupyo").hide();
			$('#selectComplete').hide();
			$("#finishTupyo").hide();
			tupyoResult();
		},
		error:function(error){
			console.log("오류");
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
	console.log(dateInput);
	var tupyClasId = $('input[name="clasId"]').val();
	console.log(tupyClasId);
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
		error:function(){
			
		}
	});
}



