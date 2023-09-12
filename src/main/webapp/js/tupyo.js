$(document).ready(function() { 
	var userId = $('input[name="accountId"]').val(); // 현재 로그인된 유저 ID
	var tupyoSeq = $('input[name="tupyoSeq"]').val();

	$.ajax({
		url: './checkVoted.do',
		type: 'GET',
		data: {
			"tuusAccountId": userId,
			"tuopTupySeq": tupyoSeq,
		},
		success: function(response) {
			if (response == "voted") {
				$("#tupyoList").hide();
				$("#tupyoResult").show();
				$("#reTupyo").show();
				tupyoResult();
			} else {
				var teacherCount = $('input[name=teacher]').length;

				if (teacherCount === 1) {
					$("#tupyoList").show();
					$('#agree-disagree-group').show();
					$('#list-group').hide();
				} else {
					$("#tupyoList").show();
					$('#agree-disagree-group').hide();
					$('#list-group').show();
				}

				$("#tupyoResult").hide();
			}
		},
		error: function(error) {
			console.log("오류");
			console.log(error);
		}
	});
});


function tupyoResult(){
	
	var teacherCount = $('input[name="teacher"]').length;
	var tupySeq = $('input[name="tupyoSeq"]').val();
	var tuusOptionSeq = $('input[name="agreeTupyoOptionSeq"]').val();

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
				"tuusOptionSeq":tuusOptionSeq
			},
			success: function(response) {
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
	var selectedVote = $("input[name='vote']:checked").val();
	var tuusOptionSeq = $('input[name="agreeTupyoOptionSeq"]').val();

	//선택 투표일 때 입력하는거 고쳐야함
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
		$.ajax({
			url: './insertTupyoUser.do',
			type: 'POST',
			data:{
				"tuusOptionSeq":tuusOptionSeq,
				"tuusAccountId":userId
			},
			success:function(){
				
				tupyoResult();
			},
			error:function(error){
				console.log("오류");
				console.log(error);
			}
		});

	} else if ($('#agree-disagree-group').is(':visible')) {  // 찬반 투표일 때
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
			data:{
				"tuusOptionSeq":tuusOptionSeq,
				"tuusAccountId":userId,
				"tuusAgree":selectedVote
			},
			success:function(response){
				
				console.log(response.agreeCount);
				console.log(response.disagreeCount);
				
				console.log(selectedVote);
				
				$("#selectInstrTitle").css('display', 'none');
				$("#tupyoResultTitle").css('display', 'block');


				$("#tupyoList").css('display', 'none');
				$("#tupyoResult").css('display', 'block');
				$("#reTupyo").css('display', 'inline-block');

				var userList = new Array();
				userList.push(response.agreeCount);
				userList.push(response.disagreeCount);
				console.log(userList);
				var ctx = document.getElementById("myChart").getContext('2d');

				if (myChart != '') {
					myChart.destroy();
				}
				
				myChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: ["찬성", "반대"],
						datasets: [{
							label: '득표수',
							data: userList,
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
}


function reTupyo() {

	
	var userId = "TMTD1"
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

		},
		error: function(error) {
			console.log("오류");
			console.log(error);
		}
	});

}