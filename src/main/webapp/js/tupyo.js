$(document).ready(function() {
	var userId = "TMTD1"; // 현재 로그인된 유저 ID

	$.ajax({
		url: './checkVoted.do',
		type: 'GET',
		data: {
			"tuusAccountId": userId,
			"tuopTupySeq": 2,//투표 seq 받아야함
		},
		success: function(response) {
			if (response == "false") {
				$("#tupyoList").hide();
				$("#tupyoResult").show();
				$("#reTupyo").show();
			} else {
				var teacherCount = $('input[name=teacher]').length;

				if (teacherCount === 1) {
					$('#list-group').hide();
					$('#agree-disagree-group').show();
					$("#tupyoList").show();
				} else {
					$("#tupyoList").show();
					$('#agree-disagree-group').hide();
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





var myChart = '';


function tupyoComplete() {

	var selectedTeacher = $("input[name='teacher']:checked").val();
	var selectedVote = $("input[name='vote']:checked").val();
	var tupyoOptionList = $("input[name='list']").val();

	var userId = "TMTD1";

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
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify({
				"tuusOptionSeq": selectedTeacher,
				"tuusAccountId": userId
			}),
			success: function(response) {
				console.log(selectedTeacher, userId);
				$("#tupyoList").css('display', 'none');
				$("#tupyoResult").css('display', 'block');
				$("#reTupyo").css('display', 'inline-block');

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
			error: function(error) {
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
				"tuusOptionSeq":4,
				"tuusAccountId":userId,
				"tuusAgree":selectedVote//셋 다 값 받아줘야함
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

//	if(	){
		
//	}	

	var selectedTeacher = $("input[name='teacher']:checked").val();

	$.ajax({
		url: './reTupyo.do',
		type: 'GET',
		data: {
			"tuusAccountId": userId,
			"tuusOptionSeq": selectedTeacher
		},
		success: function(response) {
			$("#tupyoList").css('display', 'block');
			$("#tupyoResult").css('display', 'none');
			$("#reTupyo").css('display', 'none');

		},
		error: function(error) {
			console.log("오류");
			console.log(error);
		}
	});

}