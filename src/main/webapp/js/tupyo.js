
var myChart = '';


function tupyoComplete() {

	var selectedTeacher = $("input[name='teacher']:checked").val();
	var tupyoOptionList = $("input[name='list']").val();
	var tupyoOptionList = $("input[name='list']").val();

	var userId = "TMTD1";

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
			$("#reTupyo").css('display', 'block');

			var tupyoInstrsArray = new Array();
			for (let i = 0; i < response.tupyoOptionList.length; i++) {
				tupyoInstrsArray.push(response.tupyoOptionList[i].tuopInstr);
			}

			var tupyoResultArray = new Array();
			for (let i = 0; i < response.tupyoOptionList.length; i++) {
				if (response.resultList[i] == null) {
					tupyoResultArray.push(0);
				} else {
					tupyoResultArray.push(response.resultList[i].count);
				}
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
							beginAtZero: true
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
}


function reTupyo() {

	var selectedTeacher = $("input[name='teacher']:checked").val();
	var userId = document.getAttribute("accountId");

	$.ajax({
		url: './tupyoPage.do',
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